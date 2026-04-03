package io.bytestreams.codec.iso8583.examples.jpos.cmf;

import static org.assertj.core.api.Assertions.assertThat;

import io.bytestreams.codec.core.Inspector;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Map;
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
    msg.set(4, "8402000000001000");

    byte[] packed = msg.pack();

    CMFMessage decoded = CMFMessage.CODEC.decode(new ByteArrayInputStream(packed));
    assertThat(CMFMessage.MTI.get(decoded)).isEqualTo("0100");
    assertThat(CMFMessage.PAN.get(decoded)).isEqualTo("400012345678901");
    ProcessingCode pc = CMFMessage.PROCESSING_CODE.get(decoded);
    assertThat(pc.getTransactionType()).isEqualTo("00");
    assertThat(pc.getFromAccount()).isEqualTo("30");
    assertThat(pc.getToAccount()).isEqualTo("10");
    TransactionAmount ta = CMFMessage.TRANSACTION_AMOUNT.get(decoded);
    assertThat(ta.getCurrencyCode()).isEqualTo("840");
    assertThat(ta.getDecimalPlaces()).isEqualTo(2);
    assertThat(ta.getAmount()).isEqualTo(1000L);

    @SuppressWarnings("unchecked")
    var inspected = (Map<String, Object>) Inspector.inspect(CMFMessage.CODEC, decoded);
    assertThat(inspected)
        .containsEntry("mti", "0100")
        .hasEntrySatisfying("bitmap", v -> assertThat(v.toString()).isEqualTo("{2, 3, 4}"))
        .containsEntry("pan", "400012******8901")
        .containsEntry(
            "processingCode",
            Map.of("transactionType", "00", "fromAccount", "30", "toAccount", "10"))
        .containsEntry(
            "transactionAmount",
            Map.of("currencyCode", "840", "decimalPlaces", 2, "amount", 1000L));

    ByteArrayOutputStream out = new ByteArrayOutputStream();
    CMFMessage.CODEC.encode(decoded, out);
    assertThat(out.toByteArray()).isEqualTo(packed);

    ISOMsg reparsed = new ISOMsg();
    reparsed.setPackager(packager);
    reparsed.unpack(out.toByteArray());

    assertThat(reparsed.getMTI()).isEqualTo("0100");
    assertThat(reparsed.getString(2)).isEqualTo("400012345678901");
    assertThat(reparsed.getString(3)).isEqualTo("003010");
    assertThat(reparsed.getString(4)).isEqualTo("8402000000001000");
  }
}
