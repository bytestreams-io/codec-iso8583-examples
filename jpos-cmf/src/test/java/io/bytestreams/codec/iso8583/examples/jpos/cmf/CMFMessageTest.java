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
    msg.set(5, "9782000000005000");
    msg.set(6, "8262000000001200");

    byte[] packed = msg.pack();

    CMFMessage decoded = CMFMessage.CODEC.decode(new ByteArrayInputStream(packed));
    assertThat(CMFMessage.MTI.get(decoded)).isEqualTo("0100");
    assertThat(CMFMessage.PAN.get(decoded)).isEqualTo("400012345678901");
    ProcessingCode pc = CMFMessage.PROCESSING_CODE.get(decoded);
    assertThat(pc.getTransactionType()).isEqualTo("00");
    assertThat(pc.getFromAccount()).isEqualTo("30");
    assertThat(pc.getToAccount()).isEqualTo("10");
    CurrencyAmount ta = CMFMessage.TRANSACTION_AMOUNT.get(decoded);
    assertThat(ta.getCurrencyCode()).isEqualTo("840");
    assertThat(ta.getDecimalPlaces()).isEqualTo(2);
    assertThat(ta.getAmount()).isEqualTo(1000L);
    CurrencyAmount ra = CMFMessage.RECONCILIATION_AMOUNT.get(decoded);
    assertThat(ra.getCurrencyCode()).isEqualTo("978");
    assertThat(ra.getDecimalPlaces()).isEqualTo(2);
    assertThat(ra.getAmount()).isEqualTo(5000L);
    CurrencyAmount cba = CMFMessage.CARDHOLDER_BILLING_AMOUNT.get(decoded);
    assertThat(cba.getCurrencyCode()).isEqualTo("826");
    assertThat(cba.getDecimalPlaces()).isEqualTo(2);
    assertThat(cba.getAmount()).isEqualTo(1200L);

    @SuppressWarnings("unchecked")
    var inspected = (Map<String, Object>) Inspector.inspect(CMFMessage.CODEC, decoded);
    var processingCodeMap = Map.of("transactionType", "00", "fromAccount", "30", "toAccount", "10");
    var amountMap = Map.of("currencyCode", "840", "decimalPlaces", 2, "amount", 1000L);
    var reconAmountMap = Map.of("currencyCode", "978", "decimalPlaces", 2, "amount", 5000L);
    var billingAmountMap = Map.of("currencyCode", "826", "decimalPlaces", 2, "amount", 1200L);
    assertThat(inspected)
        .containsEntry("mti", "0100")
        .hasEntrySatisfying("bitmap", v -> assertThat(v.toString()).isEqualTo("{2, 3, 4, 5, 6}"))
        .containsEntry("pan", "400012******8901")
        .containsEntry("processingCode", processingCodeMap)
        .containsEntry("transactionAmount", amountMap)
        .containsEntry("reconciliationAmount", reconAmountMap)
        .containsEntry("cardholderBillingAmount", billingAmountMap);

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
    assertThat(reparsed.getString(5)).isEqualTo("9782000000005000");
    assertThat(reparsed.getString(6)).isEqualTo("8262000000001200");
  }
}
