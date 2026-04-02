package io.bytestreams.codec.iso8583.examples.jpos.cmf;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.packager.GenericPackager;
import org.junit.jupiter.api.Test;

class CMFMessageTest {

  @Test
  void decodePanFromJposPackedMessage() throws Exception {
    GenericPackager packager = new GenericPackager("jar:packager/cmf.xml");
    ISOMsg msg = new ISOMsg("0100");
    msg.setPackager(packager);
    msg.set(2, "400012345678901");
    msg.set(3, "003010");

    byte[] packed = msg.pack();

    CMFMessage decoded = CMFMessage.CODEC.decode(new ByteArrayInputStream(packed));
    assertThat(CMFMessage.MTI.get(decoded)).isEqualTo("0100");
    assertThat(CMFMessage.PAN.get(decoded)).isEqualTo("400012345678901");
    ProcessingCode pc = CMFMessage.PROCESSING_CODE.get(decoded);
    assertThat(pc.getTransactionType()).isEqualTo("00");
    assertThat(pc.getFromAccount()).isEqualTo("30");
    assertThat(pc.getToAccount()).isEqualTo("10");

    ByteArrayOutputStream out = new ByteArrayOutputStream();
    CMFMessage.CODEC.encode(decoded, out);
    assertThat(out.toByteArray()).isEqualTo(packed);

    ISOMsg reparsed = new ISOMsg();
    reparsed.setPackager(packager);
    reparsed.unpack(out.toByteArray());

    assertThat(reparsed.getMTI()).isEqualTo("0100");
    assertThat(reparsed.getString(2)).isEqualTo("400012345678901");
    assertThat(reparsed.getString(3)).isEqualTo("003010");
  }
}
