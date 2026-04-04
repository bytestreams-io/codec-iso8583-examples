package io.bytestreams.codec.iso8583.examples.jpos.cmf;

import static org.assertj.core.api.Assertions.assertThat;

import io.bytestreams.codec.core.Inspector;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
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
    msg.set(7, "0402215430");
    msg.set(8, "840200000500");
    msg.set(9, "61234567");
    msg.set(10, "70987654");
    msg.set(11, "000000123456");
    msg.set(12, "20260402215430");
    msg.set(13, "260410");

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
    TransmissionDateTime tdt = CMFMessage.TRANSMISSION_DATE_TIME.get(decoded);
    assertThat(TransmissionDateTime.MONTH_DAY.get(tdt)).isEqualTo(MonthDay.of(4, 2));
    assertThat(TransmissionDateTime.LOCAL_TIME.get(tdt)).isEqualTo(LocalTime.of(21, 54, 30));
    CurrencyAmount cbfa = CMFMessage.CARDHOLDER_BILLING_FEE_AMOUNT.get(decoded);
    assertThat(cbfa.getCurrencyCode()).isEqualTo("840");
    assertThat(cbfa.getDecimalPlaces()).isEqualTo(2);
    assertThat(cbfa.getAmount()).isEqualTo(500L);
    ConversionRate rcr = CMFMessage.RECONCILIATION_CONVERSION_RATE.get(decoded);
    assertThat(rcr.getScale()).isEqualTo(6);
    assertThat(rcr.getValue()).isEqualTo(1234567);
    ConversionRate cbcr = CMFMessage.CARDHOLDER_BILLING_CONVERSION_RATE.get(decoded);
    assertThat(cbcr.getScale()).isEqualTo(7);
    assertThat(cbcr.getValue()).isEqualTo(987654);
    assertThat(CMFMessage.STAN.get(decoded)).isEqualTo("000000123456");
    assertThat(CMFMessage.LOCAL_TRANSACTION_DATE_TIME.get(decoded))
        .isEqualTo(LocalDateTime.of(2026, 4, 2, 21, 54, 30));
    assertThat(CMFMessage.EFFECTIVE_DATE.get(decoded)).isEqualTo(LocalDate.of(2026, 4, 10));

    @SuppressWarnings("unchecked")
    var inspected = (Map<String, Object>) Inspector.inspect(CMFMessage.CODEC, decoded);
    var processingCodeMap = Map.of("transactionType", "00", "fromAccount", "30", "toAccount", "10");
    var amountMap = Map.of("currencyCode", "840", "decimalPlaces", 2, "amount", 1000L);
    var reconAmountMap = Map.of("currencyCode", "978", "decimalPlaces", 2, "amount", 5000L);
    var billingAmountMap = Map.of("currencyCode", "826", "decimalPlaces", 2, "amount", 1200L);
    var transmissionDateTimeMap = Map.of("monthDay", "0402", "hourMinuteSecond", "215430");
    var billingFeeAmountMap = Map.of("currencyCode", "840", "decimalPlaces", 2, "amount", 500L);
    var reconConvRateMap = Map.of("scale", 6, "value", 1234567);
    var billingConvRateMap = Map.of("scale", 7, "value", 987654);
    assertThat(inspected)
        .containsEntry("mti", "0100")
        .hasEntrySatisfying(
            "bitmap", v -> assertThat(v).hasToString("{2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13}"))
        .containsEntry("pan", "400012******8901")
        .containsEntry("processingCode", processingCodeMap)
        .containsEntry("transactionAmount", amountMap)
        .containsEntry("reconciliationAmount", reconAmountMap)
        .containsEntry("cardholderBillingAmount", billingAmountMap)
        .containsEntry("transmissionDateTime", transmissionDateTimeMap)
        .containsEntry("cardholderBillingFeeAmount", billingFeeAmountMap)
        .containsEntry("reconciliationConversionRate", reconConvRateMap)
        .containsEntry("cardholderBillingConversionRate", billingConvRateMap)
        .containsEntry("stan", "000000123456")
        .containsEntry("localTransactionDateTime", "20260402215430")
        .containsEntry("effectiveDate", "260410");

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
    assertThat(reparsed.getString(7)).isEqualTo("0402215430");
    assertThat(reparsed.getString(8)).isEqualTo("840200000500");
    assertThat(reparsed.getString(9)).isEqualTo("61234567");
    assertThat(reparsed.getString(10)).isEqualTo("70987654");
    assertThat(reparsed.getString(11)).isEqualTo("000000123456");
    assertThat(reparsed.getString(12)).isEqualTo("20260402215430");
    assertThat(reparsed.getString(13)).isEqualTo("260410");
  }
}
