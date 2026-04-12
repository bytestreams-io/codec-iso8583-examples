package io.bytestreams.codec.iso8583.examples.jpos.cmf;

import static org.assertj.core.api.Assertions.assertThat;

import io.bytestreams.codec.core.Inspector;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.YearMonth;
import java.util.List;
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
    msg.set(14, "2812");
    msg.set(15, "20260405");
    msg.set(16, "0402");
    msg.set(17, "0403");
    msg.set(18, "10001004002ABC20002003001DEF");
    msg.set(19, "840");
    msg.set(20, "826");
    ISOMsg tlc = new ISOMsg(21);
    tlc.set(0, "A");
    tlc.set(1, "TRACE1234567890");
    tlc.set(2, "05");
    tlc.set(3, "1234");
    msg.set(tlc);
    org.jpos.iso.PosDataCode jposPdc =
        new org.jpos.iso.PosDataCode(
            org.jpos.iso.PosDataCode.ReadingMethod.ICC.intValue(),
            org.jpos.iso.PosDataCode.VerificationMethod.ONLINE_PIN.intValue(),
            org.jpos.iso.PosDataCode.POSEnvironment.E_COMMERCE.intValue(),
            org.jpos.iso.PosDataCode.SecurityCharacteristic.CHANNEL_ENCRYPTION.intValue());
    msg.set(22, jposPdc.getBytes());
    msg.set(23, "001");
    msg.set(24, "100");
    msg.set(25, "1403");
    msg.set(26, "5411");
    org.jpos.iso.PosCapability jposPosCapability =
        new org.jpos.iso.PosCapability(
            org.jpos.iso.PosCapability.ReadingCapability.ICC.intValue(),
            org.jpos.iso.PosCapability.VerificationCapability.ONLINE_PIN.intValue());
    ISOMsg posCapMsg = new ISOMsg(27);
    posCapMsg.set(0, jposPosCapability.getBytes());
    posCapMsg.set(1, "6");
    posCapMsg.set(2, "200");
    posCapMsg.set(3, "200");
    posCapMsg.set(4, "100");
    posCapMsg.set(5, "100");
    posCapMsg.set(6, "000");
    posCapMsg.set(7, "Y");
    posCapMsg.set(8, "N");
    posCapMsg.set(9, new byte[] {0x04});
    msg.set(posCapMsg);
    msg.set(28, "20260406");
    msg.set(29, "001");
    ISOMsg amountsOriginalMsg = new ISOMsg(30);
    amountsOriginalMsg.set(0, "8402000000001000");
    amountsOriginalMsg.set(1, "9782000000005000");
    msg.set(amountsOriginalMsg);
    msg.set(31, "12345678901234567890123");
    msg.set(32, "123456789");
    msg.set(33, "987654321");
    msg.set(34, new byte[] {0x01, 0x02, 0x03});
    msg.set(35, "4000123456789012=2612101");
    msg.set(36, "TRACK3TESTDATA");
    msg.set(37, "REF123456789");
    msg.set(38, "ABC123");
    msg.set(39, "0500");
    msg.set(40, "101");
    msg.set(41, "TERM0001        ");
    msg.set(42, "MERCHANT123456789");
    ISOMsg calMsg = new ISOMsg(43);
    calMsg.set(2, "My Store");
    calMsg.set(4, "Springfield");
    calMsg.set(7, "840");
    calMsg.set(12, "store@example.com");
    msg.set(calMsg);
    msg.set(44, new byte[] {0x01, 0x02, 0x03, 0x04});
    msg.set(45, "%B4000123456789012^CARDHOLDER/TEST^2612101");
    msg.set(46, "FEESDATA");
    msg.set(47, "NATIONALDATA");
    msg.set(48, "PRIVATEDATA");
    msg.set(50, new byte[] {0x0A, 0x0B, 0x0C});
    msg.set(51, new byte[] {0x0D, 0x0E, 0x0F});
    msg.set(52, new byte[] {0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08});
    msg.set(53, new byte[] {0x11, 0x22, 0x33});
    msg.set(54, "ADDITIONALAMOUNTS");
    msg.set(
        55, new byte[] {(byte) 0x9F, 0x26, 0x08, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08});
    msg.set(56, "01009999999999999901234567890");
    msg.set(57, "001");
    msg.set(58, "12345678901");
    msg.set(59, "TRANSPORTDATA");
    msg.set(60, "NATIONALDATA60");
    msg.set(61, "NATIONALDATA61");
    msg.set(62, "PRIVATEDATA62");
    msg.set(63, "PRIVATEDATA63");
    ISOMsg vdMsg = new ISOMsg(49);
    vdMsg.set(2, "0123");
    vdMsg.set(3, "1234 MAIN ST    ");
    vdMsg.set(4, "12345     ");
    vdMsg.set(5, "1234 MAIN STREET                        ");
    vdMsg.set(6, "Y");
    msg.set(vdMsg);

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
    assertThat(CMFMessage.EXPIRATION_DATE.get(decoded)).isEqualTo(YearMonth.of(2028, 12));
    assertThat(CMFMessage.SETTLEMENT_DATE.get(decoded)).isEqualTo(LocalDate.of(2026, 4, 5));
    assertThat(CMFMessage.CONVERSION_DATE.get(decoded)).isEqualTo(MonthDay.of(4, 2));
    assertThat(CMFMessage.CAPTURE_DATE.get(decoded)).isEqualTo(MonthDay.of(4, 3));
    List<MessageErrorIndicator> mei = CMFMessage.MESSAGE_ERROR_INDICATOR.get(decoded);
    assertThat(mei).hasSize(2);
    assertThat(mei.get(0).getErrorSeverity()).isEqualTo("1");
    assertThat(mei.get(0).getErrorCode()).isEqualTo("0001");
    assertThat(mei.get(0).getErrorDataElement()).isEqualTo("004");
    assertThat(mei.get(0).getErrorDataSubelement()).isEqualTo("002");
    assertThat(mei.get(0).getErrorDataElementValue()).isEqualTo("ABC");
    assertThat(mei.get(1).getErrorSeverity()).isEqualTo("2");
    assertThat(mei.get(1).getErrorCode()).isEqualTo("0002");
    assertThat(mei.get(1).getErrorDataElement()).isEqualTo("003");
    assertThat(mei.get(1).getErrorDataSubelement()).isEqualTo("001");
    assertThat(mei.get(1).getErrorDataElementValue()).isEqualTo("DEF");
    assertThat(CMFMessage.ACQUIRING_INSTITUTION_COUNTRY_CODE.get(decoded)).isEqualTo("840");
    assertThat(CMFMessage.PAN_COUNTRY_CODE.get(decoded)).isEqualTo("826");
    TransactionLifeCycle tlcDecoded = CMFMessage.TRANSACTION_LIFE_CYCLE.get(decoded);
    assertThat(tlcDecoded.getSupportIndicator()).isEqualTo("A");
    assertThat(tlcDecoded.getTraceIdentifier()).isEqualTo("TRACE1234567890");
    assertThat(tlcDecoded.getSequenceNumber()).isEqualTo("05");
    assertThat(tlcDecoded.getAuthenticationToken()).isEqualTo("1234");
    PosDataCode pdc = CMFMessage.POS_DATA_CODE.get(decoded);
    assertThat(pdc.getReadingMethod().has(ReadingMethod.ICC)).isTrue();
    assertThat(pdc.getReadingMethod().has(ReadingMethod.MAGNETIC_STRIPE)).isFalse();
    assertThat(pdc.getVerificationMethod().has(VerificationMethod.ONLINE_PIN)).isTrue();
    assertThat(pdc.getPosEnvironment().has(PosEnvironment.E_COMMERCE)).isTrue();
    assertThat(pdc.getSecurityCharacteristic().has(SecurityCharacteristic.CHANNEL_ENCRYPTION))
        .isTrue();
    assertThat(CMFMessage.CARD_SEQUENCE_NUMBER.get(decoded)).isEqualTo("001");
    assertThat(CMFMessage.FUNCTION_CODE.get(decoded)).isEqualTo("100");
    assertThat(CMFMessage.MESSAGE_REASON_CODE.get(decoded)).isEqualTo("1403");
    assertThat(CMFMessage.MERCHANT_CATEGORY_CODE.get(decoded)).isEqualTo("5411");
    PosCapability posCap = CMFMessage.POS_CAPABILITY.get(decoded);
    assertThat(posCap.getReadingCapability().has(ReadingCapability.ICC)).isTrue();
    assertThat(posCap.getReadingCapability().has(ReadingCapability.MAGNETIC_STRIPE)).isFalse();
    assertThat(posCap.getVerificationCapability().has(VerificationCapability.ONLINE_PIN)).isTrue();
    assertThat(posCap.getVerificationCapability().has(VerificationCapability.MANUAL_SIGNATURE))
        .isFalse();
    assertThat(PosCapability.APPROVAL_CODE_LENGTH.get(posCap)).isEqualTo(6);
    assertThat(PosCapability.CARDHOLDER_RECEIPT_DATA_LENGTH.get(posCap)).isEqualTo(200);
    assertThat(PosCapability.CARD_ACCEPTOR_RECEIPT_DATA_LENGTH.get(posCap)).isEqualTo(200);
    assertThat(PosCapability.CARDHOLDER_DISPLAY_DATA_LENGTH.get(posCap)).isEqualTo(100);
    assertThat(PosCapability.CARD_ACCEPTOR_DISPLAY_DATA_LENGTH.get(posCap)).isEqualTo(100);
    assertThat(PosCapability.ICC_SCRIPT_DATA_LENGTH.get(posCap)).isEqualTo(0);
    assertThat(PosCapability.TRACK3_REWRITE_CAPABILITY.get(posCap)).isEqualTo("Y");
    assertThat(PosCapability.CARD_CAPTURE_CAPABILITY.get(posCap)).isEqualTo("N");
    assertThat(PosCapability.PIN_INPUT_LENGTH.get(posCap)).isEqualTo(4);
    assertThat(CMFMessage.RECONCILIATION_DATE.get(decoded)).isEqualTo(LocalDate.of(2026, 4, 6));
    assertThat(CMFMessage.RECONCILIATION_INDICATOR.get(decoded)).isEqualTo("001");
    AmountsOriginal ao = CMFMessage.AMOUNTS_ORIGINAL.get(decoded);
    CurrencyAmount replacementAmount = AmountsOriginal.REPLACEMENT_AMOUNT.get(ao);
    assertThat(replacementAmount.getCurrencyCode()).isEqualTo("840");
    assertThat(replacementAmount.getDecimalPlaces()).isEqualTo(2);
    assertThat(replacementAmount.getAmount()).isEqualTo(1000L);
    CurrencyAmount replacementReconAmount =
        AmountsOriginal.REPLACEMENT_RECONCILIATION_AMOUNT.get(ao);
    assertThat(replacementReconAmount.getCurrencyCode()).isEqualTo("978");
    assertThat(replacementReconAmount.getDecimalPlaces()).isEqualTo(2);
    assertThat(replacementReconAmount.getAmount()).isEqualTo(5000L);
    assertThat(CMFMessage.ACQUIRER_REFERENCE_NUMBER.get(decoded))
        .isEqualTo("12345678901234567890123");
    assertThat(CMFMessage.ACQUIRER_INSTITUTION_ID_CODE.get(decoded)).isEqualTo("123456789");
    assertThat(CMFMessage.FORWARDING_INSTITUTION_ID_CODE.get(decoded)).isEqualTo("987654321");
    assertThat(CMFMessage.ELECTRONIC_COMMERCE_DATA.get(decoded))
        .isEqualTo(new byte[] {0x01, 0x02, 0x03});
    assertThat(CMFMessage.TRACK2_DATA.get(decoded)).isEqualTo("4000123456789012=2612101");
    assertThat(CMFMessage.TRACK3_DATA.get(decoded)).isEqualTo("TRACK3TESTDATA");
    assertThat(CMFMessage.RETRIEVAL_REFERENCE_NUMBER.get(decoded)).isEqualTo("REF123456789");
    assertThat(CMFMessage.APPROVAL_CODE.get(decoded)).isEqualTo("ABC123");
    assertThat(CMFMessage.ACTION_CODE.get(decoded)).isEqualTo("0500");
    assertThat(CMFMessage.SERVICE_CODE.get(decoded)).isEqualTo("101");
    assertThat(CMFMessage.CARD_ACCEPTOR_TERMINAL_ID.get(decoded)).isEqualTo("TERM0001        ");
    assertThat(CMFMessage.CARD_ACCEPTOR_ID_CODE.get(decoded)).isEqualTo("MERCHANT123456789");
    CardAcceptorNameLocation cal = CMFMessage.CARD_ACCEPTOR_NAME_LOCATION.get(decoded);
    assertThat(cal.getName()).isEqualTo("My Store");
    assertThat(cal.getCity()).isEqualTo("Springfield");
    assertThat(cal.getCountryCode()).isEqualTo("840");
    assertThat(CardAcceptorNameLocation.EMAIL.get(cal)).isEqualTo("store@example.com");
    assertThat(CMFMessage.ADDITIONAL_RESPONSE_DATA.get(decoded))
        .isEqualTo(new byte[] {0x01, 0x02, 0x03, 0x04});
    assertThat(CMFMessage.TRACK1_DATA.get(decoded))
        .isEqualTo("%B4000123456789012^CARDHOLDER/TEST^2612101");
    assertThat(CMFMessage.AMOUNTS_FEES.get(decoded)).isEqualTo("FEESDATA");
    assertThat(CMFMessage.ADDITIONAL_DATA_NATIONAL.get(decoded)).isEqualTo("NATIONALDATA");
    assertThat(CMFMessage.ADDITIONAL_DATA_PRIVATE.get(decoded)).isEqualTo("PRIVATEDATA");
    VerificationData vd = CMFMessage.VERIFICATION_DATA.get(decoded);
    assertThat(vd.getCardVerificationData()).isEqualTo("0123");
    assertThat(vd.getCardholderBillingAddressCompressed()).isEqualTo("1234 MAIN ST    ");
    assertThat(vd.getCardholderBillingPostalCode()).isEqualTo("12345     ");
    assertThat(vd.getCardholderBillingStreetAddress())
        .isEqualTo("1234 MAIN STREET                        ");
    assertThat(vd.getAddressVerificationResultCode()).isEqualTo("Y");
    assertThat(CMFMessage.RESERVED_50.get(decoded)).isEqualTo(new byte[] {0x0A, 0x0B, 0x0C});
    assertThat(CMFMessage.RESERVED_51.get(decoded)).isEqualTo(new byte[] {0x0D, 0x0E, 0x0F});
    assertThat(CMFMessage.PIN_DATA.get(decoded))
        .isEqualTo(new byte[] {0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08});
    assertThat(CMFMessage.SECURITY_RELATED_CONTROL_INFORMATION.get(decoded))
        .isEqualTo(new byte[] {0x11, 0x22, 0x33});
    assertThat(CMFMessage.AMOUNTS_ADDITIONAL.get(decoded)).isEqualTo("ADDITIONALAMOUNTS");
    assertThat(CMFMessage.IC_SYSTEM_RELATED_DATA.get(decoded))
        .isEqualTo(
            new byte[] {(byte) 0x9F, 0x26, 0x08, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08});
    assertThat(CMFMessage.ORIGINAL_DATA_ELEMENTS.get(decoded))
        .isEqualTo("01009999999999999901234567890");
    assertThat(CMFMessage.AUTHORIZATION_LIFE_CYCLE_CODE.get(decoded)).isEqualTo("001");
    assertThat(CMFMessage.AUTHORIZING_AGENT_INSTITUTION_ID_CODE.get(decoded))
        .isEqualTo("12345678901");
    assertThat(CMFMessage.TRANSPORT_DATA.get(decoded)).isEqualTo("TRANSPORTDATA");
    assertThat(CMFMessage.RESERVED_60.get(decoded)).isEqualTo("NATIONALDATA60");
    assertThat(CMFMessage.RESERVED_61.get(decoded)).isEqualTo("NATIONALDATA61");
    assertThat(CMFMessage.RESERVED_62.get(decoded)).isEqualTo("PRIVATEDATA62");
    assertThat(CMFMessage.RESERVED_63.get(decoded)).isEqualTo("PRIVATEDATA63");

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
            "bitmap",
            v ->
                assertThat(v)
                    .hasToString(
                        "{2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21,"
                            + " 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37,"
                            + " 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53,"
                            + " 54, 55, 56, 57, 58, 59, 60, 61, 62, 63}"))
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
        .containsEntry("effectiveDate", "260410")
        .containsEntry("expirationDate", "2812")
        .containsEntry("settlementDate", "20260405")
        .containsEntry("conversionDate", "0402")
        .containsEntry("captureDate", "0403")
        .containsKey("messageErrorIndicator")
        .containsEntry("acquiringInstitutionCountryCode", "840")
        .containsEntry("panCountryCode", "826")
        .containsEntry(
            "transactionLifeCycle",
            Map.of(
                "supportIndicator", "A",
                "traceIdentifier", "TRACE1234567890",
                "sequenceNumber", "05",
                "authenticationToken", "1234"))
        .containsKey("posDataCode")
        .containsEntry("cardSequenceNumber", "001")
        .containsEntry("functionCode", "100")
        .containsEntry("messageReasonCode", "1403")
        .containsEntry("merchantCategoryCode", "5411")
        .containsKey("posCapability")
        .containsEntry("reconciliationDate", "20260406")
        .containsEntry("reconciliationIndicator", "001")
        .containsKey("amountsOriginal")
        .containsEntry("acquirerReferenceNumber", "12345678901234567890123")
        .containsEntry("acquirerInstitutionIdCode", "123456789")
        .containsEntry("forwardingInstitutionIdCode", "987654321")
        .containsKey("electronicCommerceData")
        .containsEntry("track2Data", "4000123456789012=2612101")
        .containsEntry("track3Data", "TRACK3TESTDATA")
        .containsEntry("retrievalReferenceNumber", "REF123456789")
        .containsEntry("approvalCode", "ABC123")
        .containsEntry("actionCode", "0500")
        .containsEntry("serviceCode", "101")
        .containsEntry("cardAcceptorTerminalId", "TERM0001        ")
        .containsEntry("cardAcceptorIdCode", "MERCHANT123456789")
        .containsKey("cardAcceptorNameLocation")
        .containsKey("additionalResponseData")
        .containsEntry("track1Data", "%B4000123456789012^CARDHOLDER/TEST^2612101")
        .containsEntry("amountsFees", "FEESDATA")
        .containsEntry("additionalDataNational", "NATIONALDATA")
        .containsEntry("additionalDataPrivate", "PRIVATEDATA")
        .containsKey("verificationData")
        .containsKey("reserved50")
        .containsKey("reserved51")
        .containsKey("pinData")
        .containsKey("securityRelatedControlInformation")
        .containsEntry("amountsAdditional", "ADDITIONALAMOUNTS")
        .containsKey("icSystemRelatedData")
        .containsEntry("originalDataElements", "01009999999999999901234567890")
        .containsEntry("authorizationLifeCycleCode", "001")
        .containsEntry("authorizingAgentInstitutionIdCode", "12345678901")
        .containsEntry("transportData", "TRANSPORTDATA")
        .containsEntry("reserved60", "NATIONALDATA60")
        .containsEntry("reserved61", "NATIONALDATA61")
        .containsEntry("reserved62", "PRIVATEDATA62")
        .containsEntry("reserved63", "PRIVATEDATA63");

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
    assertThat(reparsed.getString(14)).isEqualTo("2812");
    assertThat(reparsed.getString(15)).isEqualTo("20260405");
    assertThat(reparsed.getString(16)).isEqualTo("0402");
    assertThat(reparsed.getString(17)).isEqualTo("0403");
    assertThat(reparsed.getString(18)).isEqualTo("10001004002ABC20002003001DEF");
    assertThat(reparsed.getString(19)).isEqualTo("840");
    assertThat(reparsed.getString(20)).isEqualTo("826");
    ISOMsg reparsedTlc = (ISOMsg) reparsed.getComponent(21);
    assertThat(reparsedTlc.getString(0)).isEqualTo("A");
    assertThat(reparsedTlc.getString(1)).isEqualTo("TRACE1234567890");
    assertThat(reparsedTlc.getString(2)).isEqualTo("05");
    assertThat(reparsedTlc.getString(3)).isEqualTo("1234");
    assertThat(reparsed.getBytes(22)).isEqualTo(jposPdc.getBytes());
    assertThat(reparsed.getString(23)).isEqualTo("001");
    assertThat(reparsed.getString(24)).isEqualTo("100");
    assertThat(reparsed.getString(25)).isEqualTo("1403");
    assertThat(reparsed.getString(26)).isEqualTo("5411");
    ISOMsg reparsedPosCap = (ISOMsg) reparsed.getComponent(27);
    assertThat(reparsedPosCap.getBytes(0)).isEqualTo(jposPosCapability.getBytes());
    assertThat(reparsedPosCap.getString(1)).isEqualTo("6");
    assertThat(reparsedPosCap.getString(2)).isEqualTo("200");
    assertThat(reparsedPosCap.getString(3)).isEqualTo("200");
    assertThat(reparsedPosCap.getString(4)).isEqualTo("100");
    assertThat(reparsedPosCap.getString(5)).isEqualTo("100");
    assertThat(reparsedPosCap.getString(6)).isEqualTo("000");
    assertThat(reparsedPosCap.getString(7)).isEqualTo("Y");
    assertThat(reparsedPosCap.getString(8)).isEqualTo("N");
    assertThat(reparsedPosCap.getBytes(9)).isEqualTo(new byte[] {0x04});
    assertThat(reparsed.getString(28)).isEqualTo("20260406");
    assertThat(reparsed.getString(29)).isEqualTo("001");
    ISOMsg reparsedAo = (ISOMsg) reparsed.getComponent(30);
    assertThat(reparsedAo.getString(0)).isEqualTo("8402000000001000");
    assertThat(reparsedAo.getString(1)).isEqualTo("9782000000005000");
    assertThat(reparsed.getString(31)).isEqualTo("12345678901234567890123");
    assertThat(reparsed.getString(32)).isEqualTo("123456789");
    assertThat(reparsed.getString(33)).isEqualTo("987654321");
    assertThat(reparsed.getBytes(34)).isEqualTo(new byte[] {0x01, 0x02, 0x03});
    assertThat(reparsed.getString(35)).isEqualTo("4000123456789012=2612101");
    assertThat(reparsed.getString(36)).isEqualTo("TRACK3TESTDATA");
    assertThat(reparsed.getString(37)).isEqualTo("REF123456789");
    assertThat(reparsed.getString(38)).isEqualTo("ABC123");
    assertThat(reparsed.getString(39)).isEqualTo("0500");
    assertThat(reparsed.getString(40)).isEqualTo("101");
    assertThat(reparsed.getString(41)).isEqualTo("TERM0001        ");
    assertThat(reparsed.getString(42)).isEqualTo("MERCHANT123456789");
    ISOMsg reparsedCal = (ISOMsg) reparsed.getComponent(43);
    assertThat(reparsedCal.getString(2)).isEqualTo("My Store");
    assertThat(reparsedCal.getString(4)).isEqualTo("Springfield");
    assertThat(reparsedCal.getString(7)).isEqualTo("840");
    assertThat(reparsedCal.getString(12)).isEqualTo("store@example.com");
    assertThat(reparsed.getBytes(44)).isEqualTo(new byte[] {0x01, 0x02, 0x03, 0x04});
    assertThat(reparsed.getString(45)).isEqualTo("%B4000123456789012^CARDHOLDER/TEST^2612101");
    assertThat(reparsed.getString(46)).isEqualTo("FEESDATA");
    assertThat(reparsed.getString(47)).isEqualTo("NATIONALDATA");
    assertThat(reparsed.getString(48)).isEqualTo("PRIVATEDATA");
    ISOMsg reparsedVd = (ISOMsg) reparsed.getComponent(49);
    assertThat(reparsedVd.getString(2)).isEqualTo("0123");
    assertThat(reparsedVd.getString(3)).isEqualTo("1234 MAIN ST    ");
    assertThat(reparsedVd.getString(4)).isEqualTo("12345     ");
    assertThat(reparsedVd.getString(5)).isEqualTo("1234 MAIN STREET                        ");
    assertThat(reparsedVd.getString(6)).isEqualTo("Y");
    assertThat(reparsed.getBytes(50)).isEqualTo(new byte[] {0x0A, 0x0B, 0x0C});
    assertThat(reparsed.getBytes(51)).isEqualTo(new byte[] {0x0D, 0x0E, 0x0F});
    assertThat(reparsed.getBytes(52))
        .isEqualTo(new byte[] {0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08});
    assertThat(reparsed.getBytes(53)).isEqualTo(new byte[] {0x11, 0x22, 0x33});
    assertThat(reparsed.getString(54)).isEqualTo("ADDITIONALAMOUNTS");
    assertThat(reparsed.getBytes(55))
        .isEqualTo(
            new byte[] {(byte) 0x9F, 0x26, 0x08, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08});
    assertThat(reparsed.getString(56)).isEqualTo("01009999999999999901234567890");
    assertThat(reparsed.getString(57)).isEqualTo("001");
    assertThat(reparsed.getString(58)).isEqualTo("12345678901");
    assertThat(reparsed.getString(59)).isEqualTo("TRANSPORTDATA");
    assertThat(reparsed.getString(60)).isEqualTo("NATIONALDATA60");
    assertThat(reparsed.getString(61)).isEqualTo("NATIONALDATA61");
    assertThat(reparsed.getString(62)).isEqualTo("PRIVATEDATA62");
    assertThat(reparsed.getString(63)).isEqualTo("PRIVATEDATA63");
  }
}
