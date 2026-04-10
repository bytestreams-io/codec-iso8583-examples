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
          .build();

  public CMFMessage() {
    BITMAP.set(this, new MultiBlockBitmap(8));
  }

  @Override
  public MultiBlockBitmap getBitmap() {
    return BITMAP.get(this);
  }
}
