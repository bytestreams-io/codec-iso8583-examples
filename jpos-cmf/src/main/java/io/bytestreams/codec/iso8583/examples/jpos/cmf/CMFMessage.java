package io.bytestreams.codec.iso8583.examples.jpos.cmf;

import io.bytestreams.codec.core.Codec;
import io.bytestreams.codec.core.Codecs;
import io.bytestreams.codec.core.DataObject;
import io.bytestreams.codec.core.FieldSpec;
import io.bytestreams.codec.iso8583.Bitmapped;
import io.bytestreams.codec.iso8583.BitmappedCodecBuilder;
import io.bytestreams.codec.iso8583.BitmappedFieldSpec;
import io.bytestreams.codec.iso8583.FieldCodecs;
import io.bytestreams.codec.iso8583.MultiBlockBitmap;

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
          .build();

  public CMFMessage() {
    BITMAP.set(this, new MultiBlockBitmap(8));
  }

  @Override
  public MultiBlockBitmap getBitmap() {
    return BITMAP.get(this);
  }
}
