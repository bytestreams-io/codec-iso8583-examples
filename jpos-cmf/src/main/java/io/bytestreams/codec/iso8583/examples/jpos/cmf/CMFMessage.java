package io.bytestreams.codec.iso8583.examples.jpos.cmf;

import static io.bytestreams.codec.iso8583.examples.jpos.cmf.CMFCodecs.MMDD;
import static io.bytestreams.codec.iso8583.examples.jpos.cmf.CMFCodecs.YYMM;
import static io.bytestreams.codec.iso8583.examples.jpos.cmf.CMFCodecs.YYMMDD;
import static io.bytestreams.codec.iso8583.examples.jpos.cmf.CMFCodecs.YYYYMMDD;
import static io.bytestreams.codec.iso8583.examples.jpos.cmf.CMFCodecs.YYYYMMDDHHMMSS;

import io.bytestreams.codec.core.Codec;
import io.bytestreams.codec.core.Codecs;
import io.bytestreams.codec.core.DataObject;
import io.bytestreams.codec.core.FieldSpec;
import io.bytestreams.codec.iso8583.Bitmapped;
import io.bytestreams.codec.iso8583.BitmappedCodecBuilder;
import io.bytestreams.codec.iso8583.BitmappedFieldSpec;
import io.bytestreams.codec.iso8583.FieldCodecs;
import io.bytestreams.codec.iso8583.MultiBlockBitmap;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.MonthDay;
import java.time.YearMonth;
import java.util.List;

public class CMFMessage extends DataObject implements Bitmapped {
  public static final FieldSpec<CMFMessage, String> MTI = field("mti", Codecs.hex(4));
  public static final FieldSpec<CMFMessage, MultiBlockBitmap> BITMAP =
      field("bitmap", FieldCodecs.multiBlockBitmap(8));
  public static final BitmappedFieldSpec<CMFMessage, String> PAN =
      BitmappedFieldSpec.of(2, field("pan", new PanCodec()));
  public static final BitmappedFieldSpec<CMFMessage, ProcessingCode> PROCESSING_CODE =
      BitmappedFieldSpec.of(3, field("processingCode", ProcessingCode.CODEC));
  public static final BitmappedFieldSpec<CMFMessage, CurrencyAmount> TRANSACTION_AMOUNT =
      BitmappedFieldSpec.of(4, field("transactionAmount", CurrencyAmount.codec(16)));
  public static final BitmappedFieldSpec<CMFMessage, CurrencyAmount> RECONCILIATION_AMOUNT =
      BitmappedFieldSpec.of(5, field("reconciliationAmount", CurrencyAmount.codec(16)));
  public static final BitmappedFieldSpec<CMFMessage, CurrencyAmount> CARDHOLDER_BILLING_AMOUNT =
      BitmappedFieldSpec.of(6, field("cardholderBillingAmount", CurrencyAmount.codec(16)));
  public static final BitmappedFieldSpec<CMFMessage, TransmissionDateTime> TRANSMISSION_DATE_TIME =
      BitmappedFieldSpec.of(7, field("transmissionDateTime", TransmissionDateTime.CODEC));
  public static final BitmappedFieldSpec<CMFMessage, CurrencyAmount> CARDHOLDER_BILLING_FEE_AMOUNT =
      BitmappedFieldSpec.of(8, field("cardholderBillingFeeAmount", CurrencyAmount.codec(12)));
  public static final BitmappedFieldSpec<CMFMessage, ConversionRate>
      RECONCILIATION_CONVERSION_RATE =
          BitmappedFieldSpec.of(9, field("reconciliationConversionRate", ConversionRate.CODEC));
  public static final BitmappedFieldSpec<CMFMessage, ConversionRate>
      CARDHOLDER_BILLING_CONVERSION_RATE =
          BitmappedFieldSpec.of(10, field("cardholderBillingConversionRate", ConversionRate.CODEC));
  public static final BitmappedFieldSpec<CMFMessage, String> STAN =
      BitmappedFieldSpec.of(11, field("stan", Codecs.hex(12)));
  public static final BitmappedFieldSpec<CMFMessage, LocalDateTime> LOCAL_TRANSACTION_DATE_TIME =
      BitmappedFieldSpec.of(12, field("localTransactionDateTime", YYYYMMDDHHMMSS));
  public static final BitmappedFieldSpec<CMFMessage, LocalDate> EFFECTIVE_DATE =
      BitmappedFieldSpec.of(13, field("effectiveDate", YYMMDD));
  public static final BitmappedFieldSpec<CMFMessage, YearMonth> EXPIRATION_DATE =
      BitmappedFieldSpec.of(14, field("expirationDate", YYMM));
  public static final BitmappedFieldSpec<CMFMessage, LocalDate> SETTLEMENT_DATE =
      BitmappedFieldSpec.of(15, field("settlementDate", YYYYMMDD));
  public static final BitmappedFieldSpec<CMFMessage, MonthDay> CONVERSION_DATE =
      BitmappedFieldSpec.of(16, field("conversionDate", MMDD));
  public static final BitmappedFieldSpec<CMFMessage, MonthDay> CAPTURE_DATE =
      BitmappedFieldSpec.of(17, field("captureDate", MMDD));
  public static final BitmappedFieldSpec<CMFMessage, List<MessageErrorIndicator>>
      MESSAGE_ERROR_INDICATOR =
          BitmappedFieldSpec.of(
              18,
              field(
                  "messageErrorIndicator",
                  Codecs.prefixed(Codecs.bcdInt(3), Codecs.listOf(MessageErrorIndicator.CODEC))));
  public static final BitmappedFieldSpec<CMFMessage, String> ACQUIRING_INSTITUTION_COUNTRY_CODE =
      BitmappedFieldSpec.of(19, field("acquiringInstitutionCountryCode", CMFCodecs.hex(3)));
  public static final BitmappedFieldSpec<CMFMessage, String> PAN_COUNTRY_CODE =
      BitmappedFieldSpec.of(20, field("panCountryCode", CMFCodecs.hex(3)));
  public static final BitmappedFieldSpec<CMFMessage, TransactionLifeCycle> TRANSACTION_LIFE_CYCLE =
      BitmappedFieldSpec.of(21, field("transactionLifeCycle", TransactionLifeCycle.CODEC));
  public static final BitmappedFieldSpec<CMFMessage, PosDataCode> POS_DATA_CODE =
      BitmappedFieldSpec.of(22, field("posDataCode", PosDataCode.CODEC));
  public static final BitmappedFieldSpec<CMFMessage, String> CARD_SEQUENCE_NUMBER =
      BitmappedFieldSpec.of(23, field("cardSequenceNumber", CMFCodecs.hex(3)));
  public static final BitmappedFieldSpec<CMFMessage, String> FUNCTION_CODE =
      BitmappedFieldSpec.of(24, field("functionCode", CMFCodecs.hex(3)));
  public static final BitmappedFieldSpec<CMFMessage, String> MESSAGE_REASON_CODE =
      BitmappedFieldSpec.of(25, field("messageReasonCode", Codecs.hex(4)));
  public static final BitmappedFieldSpec<CMFMessage, String> MERCHANT_CATEGORY_CODE =
      BitmappedFieldSpec.of(26, field("merchantCategoryCode", Codecs.hex(4)));
  public static final BitmappedFieldSpec<CMFMessage, PosCapability> POS_CAPABILITY =
      BitmappedFieldSpec.of(27, field("posCapability", PosCapability.CODEC));
  public static final BitmappedFieldSpec<CMFMessage, LocalDate> RECONCILIATION_DATE =
      BitmappedFieldSpec.of(28, field("reconciliationDate", YYYYMMDD));
  public static final BitmappedFieldSpec<CMFMessage, String> RECONCILIATION_INDICATOR =
      BitmappedFieldSpec.of(29, field("reconciliationIndicator", CMFCodecs.hex(3)));
  public static final BitmappedFieldSpec<CMFMessage, AmountsOriginal> AMOUNTS_ORIGINAL =
      BitmappedFieldSpec.of(30, field("amountsOriginal", AmountsOriginal.CODEC));
  public static final BitmappedFieldSpec<CMFMessage, String> ACQUIRER_REFERENCE_NUMBER =
      BitmappedFieldSpec.of(31, field("acquirerReferenceNumber", CMFCodecs.hex(23)));
  public static final BitmappedFieldSpec<CMFMessage, String> ACQUIRER_INSTITUTION_ID_CODE =
      BitmappedFieldSpec.of(
          32, field("acquirerInstitutionIdCode", CMFCodecs.hex(Codecs.bcdInt(2))));
  public static final BitmappedFieldSpec<CMFMessage, String> FORWARDING_INSTITUTION_ID_CODE =
      BitmappedFieldSpec.of(
          33, field("forwardingInstitutionIdCode", CMFCodecs.hex(Codecs.bcdInt(2))));
  public static final BitmappedFieldSpec<CMFMessage, byte[]> ELECTRONIC_COMMERCE_DATA =
      BitmappedFieldSpec.of(34, field("electronicCommerceData", Codecs.binary(Codecs.bcdInt(4))));
  public static final BitmappedFieldSpec<CMFMessage, String> TRACK2_DATA =
      BitmappedFieldSpec.of(35, field("track2Data", Codecs.ascii(Codecs.bcdInt(2))));
  public static final BitmappedFieldSpec<CMFMessage, String> TRACK3_DATA =
      BitmappedFieldSpec.of(36, field("track3Data", Codecs.ascii(Codecs.bcdInt(3))));
  public static final BitmappedFieldSpec<CMFMessage, String> RETRIEVAL_REFERENCE_NUMBER =
      BitmappedFieldSpec.of(37, field("retrievalReferenceNumber", Codecs.ascii(12)));
  public static final BitmappedFieldSpec<CMFMessage, String> APPROVAL_CODE =
      BitmappedFieldSpec.of(38, field("approvalCode", Codecs.ascii(6)));
  public static final BitmappedFieldSpec<CMFMessage, String> ACTION_CODE =
      BitmappedFieldSpec.of(39, field("actionCode", Codecs.hex(4)));
  public static final BitmappedFieldSpec<CMFMessage, String> SERVICE_CODE =
      BitmappedFieldSpec.of(40, field("serviceCode", CMFCodecs.hex(3)));
  public static final BitmappedFieldSpec<CMFMessage, String> CARD_ACCEPTOR_TERMINAL_ID =
      BitmappedFieldSpec.of(41, field("cardAcceptorTerminalId", Codecs.ascii(16)));
  public static final BitmappedFieldSpec<CMFMessage, String> CARD_ACCEPTOR_ID_CODE =
      BitmappedFieldSpec.of(42, field("cardAcceptorIdCode", Codecs.ascii(Codecs.bcdInt(2))));
  public static final BitmappedFieldSpec<CMFMessage, CardAcceptorNameLocation>
      CARD_ACCEPTOR_NAME_LOCATION =
          BitmappedFieldSpec.of(
              43,
              field(
                  "cardAcceptorNameLocation",
                  Codecs.prefixed(Codecs.bcdInt(4), CardAcceptorNameLocation.CODEC)));
  public static final BitmappedFieldSpec<CMFMessage, byte[]> ADDITIONAL_RESPONSE_DATA =
      BitmappedFieldSpec.of(44, field("additionalResponseData", Codecs.binary(Codecs.bcdInt(4))));
  public static final BitmappedFieldSpec<CMFMessage, String> TRACK1_DATA =
      BitmappedFieldSpec.of(45, field("track1Data", Codecs.ascii(Codecs.bcdInt(2))));
  public static final BitmappedFieldSpec<CMFMessage, String> AMOUNTS_FEES =
      BitmappedFieldSpec.of(46, field("amountsFees", Codecs.ascii(Codecs.bcdInt(3))));
  public static final BitmappedFieldSpec<CMFMessage, String> ADDITIONAL_DATA_NATIONAL =
      BitmappedFieldSpec.of(47, field("additionalDataNational", Codecs.ascii(Codecs.bcdInt(3))));
  public static final BitmappedFieldSpec<CMFMessage, String> ADDITIONAL_DATA_PRIVATE =
      BitmappedFieldSpec.of(48, field("additionalDataPrivate", Codecs.ascii(Codecs.bcdInt(3))));
  public static final BitmappedFieldSpec<CMFMessage, VerificationData> VERIFICATION_DATA =
      BitmappedFieldSpec.of(
          49, field("verificationData", Codecs.prefixed(Codecs.bcdInt(4), VerificationData.CODEC)));
  public static final BitmappedFieldSpec<CMFMessage, byte[]> RESERVED_50 =
      BitmappedFieldSpec.of(50, field("reserved50", Codecs.binary(Codecs.bcdInt(4))));
  public static final BitmappedFieldSpec<CMFMessage, byte[]> RESERVED_51 =
      BitmappedFieldSpec.of(51, field("reserved51", Codecs.binary(Codecs.bcdInt(4))));
  public static final BitmappedFieldSpec<CMFMessage, byte[]> PIN_DATA =
      BitmappedFieldSpec.of(52, field("pinData", Codecs.binary(8)));
  public static final BitmappedFieldSpec<CMFMessage, byte[]> SECURITY_RELATED_CONTROL_INFORMATION =
      BitmappedFieldSpec.of(
          53, field("securityRelatedControlInformation", Codecs.binary(Codecs.bcdInt(2))));
  public static final BitmappedFieldSpec<CMFMessage, String> AMOUNTS_ADDITIONAL =
      BitmappedFieldSpec.of(54, field("amountsAdditional", Codecs.ascii(Codecs.bcdInt(3))));
  public static final BitmappedFieldSpec<CMFMessage, byte[]> IC_SYSTEM_RELATED_DATA =
      BitmappedFieldSpec.of(55, field("icSystemRelatedData", Codecs.binary(Codecs.bcdInt(4))));
  public static final BitmappedFieldSpec<CMFMessage, String> ORIGINAL_DATA_ELEMENTS =
      BitmappedFieldSpec.of(56, field("originalDataElements", CMFCodecs.hex(Codecs.bcdInt(2))));
  public static final BitmappedFieldSpec<CMFMessage, String> AUTHORIZATION_LIFE_CYCLE_CODE =
      BitmappedFieldSpec.of(57, field("authorizationLifeCycleCode", CMFCodecs.hex(3)));
  public static final BitmappedFieldSpec<CMFMessage, String> AUTHORIZING_AGENT_INSTITUTION_ID_CODE =
      BitmappedFieldSpec.of(
          58, field("authorizingAgentInstitutionIdCode", CMFCodecs.hex(Codecs.bcdInt(2))));
  public static final BitmappedFieldSpec<CMFMessage, String> TRANSPORT_DATA =
      BitmappedFieldSpec.of(59, field("transportData", Codecs.ascii(Codecs.bcdInt(3))));
  public static final BitmappedFieldSpec<CMFMessage, String> RESERVED_60 =
      BitmappedFieldSpec.of(60, field("reserved60", Codecs.ascii(Codecs.bcdInt(3))));
  public static final BitmappedFieldSpec<CMFMessage, String> RESERVED_61 =
      BitmappedFieldSpec.of(61, field("reserved61", Codecs.ascii(Codecs.bcdInt(3))));

  public static final Codec<CMFMessage> CODEC =
      BitmappedCodecBuilder.builder(CMFMessage::new)
          .field(MTI)
          .bitmap(BITMAP)
          .dataField(PAN)
          .dataField(PROCESSING_CODE)
          .dataField(TRANSACTION_AMOUNT)
          .dataField(RECONCILIATION_AMOUNT)
          .dataField(CARDHOLDER_BILLING_AMOUNT)
          .dataField(TRANSMISSION_DATE_TIME)
          .dataField(CARDHOLDER_BILLING_FEE_AMOUNT)
          .dataField(RECONCILIATION_CONVERSION_RATE)
          .dataField(CARDHOLDER_BILLING_CONVERSION_RATE)
          .dataField(STAN)
          .dataField(LOCAL_TRANSACTION_DATE_TIME)
          .dataField(EFFECTIVE_DATE)
          .dataField(EXPIRATION_DATE)
          .dataField(SETTLEMENT_DATE)
          .dataField(CONVERSION_DATE)
          .dataField(CAPTURE_DATE)
          .dataField(MESSAGE_ERROR_INDICATOR)
          .dataField(ACQUIRING_INSTITUTION_COUNTRY_CODE)
          .dataField(PAN_COUNTRY_CODE)
          .dataField(TRANSACTION_LIFE_CYCLE)
          .dataField(POS_DATA_CODE)
          .dataField(CARD_SEQUENCE_NUMBER)
          .dataField(FUNCTION_CODE)
          .dataField(MESSAGE_REASON_CODE)
          .dataField(MERCHANT_CATEGORY_CODE)
          .dataField(POS_CAPABILITY)
          .dataField(RECONCILIATION_DATE)
          .dataField(RECONCILIATION_INDICATOR)
          .dataField(AMOUNTS_ORIGINAL)
          .dataField(ACQUIRER_REFERENCE_NUMBER)
          .dataField(ACQUIRER_INSTITUTION_ID_CODE)
          .dataField(FORWARDING_INSTITUTION_ID_CODE)
          .dataField(ELECTRONIC_COMMERCE_DATA)
          .dataField(TRACK2_DATA)
          .dataField(TRACK3_DATA)
          .dataField(RETRIEVAL_REFERENCE_NUMBER)
          .dataField(APPROVAL_CODE)
          .dataField(ACTION_CODE)
          .dataField(SERVICE_CODE)
          .dataField(CARD_ACCEPTOR_TERMINAL_ID)
          .dataField(CARD_ACCEPTOR_ID_CODE)
          .dataField(CARD_ACCEPTOR_NAME_LOCATION)
          .dataField(ADDITIONAL_RESPONSE_DATA)
          .dataField(TRACK1_DATA)
          .dataField(AMOUNTS_FEES)
          .dataField(ADDITIONAL_DATA_NATIONAL)
          .dataField(ADDITIONAL_DATA_PRIVATE)
          .dataField(VERIFICATION_DATA)
          .dataField(RESERVED_50)
          .dataField(RESERVED_51)
          .dataField(PIN_DATA)
          .dataField(SECURITY_RELATED_CONTROL_INFORMATION)
          .dataField(AMOUNTS_ADDITIONAL)
          .dataField(IC_SYSTEM_RELATED_DATA)
          .dataField(ORIGINAL_DATA_ELEMENTS)
          .dataField(AUTHORIZATION_LIFE_CYCLE_CODE)
          .dataField(AUTHORIZING_AGENT_INSTITUTION_ID_CODE)
          .dataField(TRANSPORT_DATA)
          .dataField(RESERVED_60)
          .dataField(RESERVED_61)
          .build();

  public CMFMessage() {
    BITMAP.set(this, new MultiBlockBitmap(8));
  }

  @Override
  public MultiBlockBitmap getBitmap() {
    return BITMAP.get(this);
  }
}
