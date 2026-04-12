package io.bytestreams.codec.iso8583.examples.jpos.cmf;

import io.bytestreams.codec.core.Codec;
import io.bytestreams.codec.core.Codecs;
import io.bytestreams.codec.core.DataObject;
import io.bytestreams.codec.core.FieldSpec;
import io.bytestreams.codec.iso8583.Bitmapped;
import io.bytestreams.codec.iso8583.BitmappedCodecBuilder;
import io.bytestreams.codec.iso8583.BitmappedFieldSpec;
import io.bytestreams.codec.iso8583.FieldCodecs;
import io.bytestreams.codec.iso8583.SingleBlockBitmap;

public class VerificationData extends DataObject implements Bitmapped {
  static final FieldSpec<VerificationData, SingleBlockBitmap> BITMAP =
      field("bitmap", FieldCodecs.singleBlockBitmap(8));
  public static final BitmappedFieldSpec<VerificationData, String> ADDITIONAL_IDENTIFICATION_TYPE =
      BitmappedFieldSpec.of(1, field("additionalIdentificationType", CMFCodecs.hex(1)));
  public static final BitmappedFieldSpec<VerificationData, String> CARD_VERIFICATION_DATA =
      BitmappedFieldSpec.of(2, field("cardVerificationData", Codecs.hex(4)));
  public static final BitmappedFieldSpec<VerificationData, String>
      CARDHOLDER_BILLING_ADDRESS_COMPRESSED =
          BitmappedFieldSpec.of(3, field("cardholderBillingAddressCompressed", Codecs.ascii(16)));
  public static final BitmappedFieldSpec<VerificationData, String> CARDHOLDER_BILLING_POSTAL_CODE =
      BitmappedFieldSpec.of(4, field("cardholderBillingPostalCode", Codecs.ascii(10)));
  public static final BitmappedFieldSpec<VerificationData, String>
      CARDHOLDER_BILLING_STREET_ADDRESS =
          BitmappedFieldSpec.of(5, field("cardholderBillingStreetAddress", Codecs.ascii(40)));
  public static final BitmappedFieldSpec<VerificationData, String>
      ADDRESS_VERIFICATION_RESULT_CODE =
          BitmappedFieldSpec.of(6, field("addressVerificationResultCode", Codecs.ascii(1)));

  static final Codec<VerificationData> CODEC =
      BitmappedCodecBuilder.builder(VerificationData::new)
          .bitmap(BITMAP)
          .dataField(ADDITIONAL_IDENTIFICATION_TYPE)
          .dataField(CARD_VERIFICATION_DATA)
          .dataField(CARDHOLDER_BILLING_ADDRESS_COMPRESSED)
          .dataField(CARDHOLDER_BILLING_POSTAL_CODE)
          .dataField(CARDHOLDER_BILLING_STREET_ADDRESS)
          .dataField(ADDRESS_VERIFICATION_RESULT_CODE)
          .build();

  public VerificationData() {
    BITMAP.set(this, new SingleBlockBitmap(8));
  }

  @Override
  public SingleBlockBitmap getBitmap() {
    return BITMAP.get(this);
  }

  public String getAdditionalIdentificationType() {
    return ADDITIONAL_IDENTIFICATION_TYPE.get(this);
  }

  public String getCardVerificationData() {
    return CARD_VERIFICATION_DATA.get(this);
  }

  public String getCardholderBillingAddressCompressed() {
    return CARDHOLDER_BILLING_ADDRESS_COMPRESSED.get(this);
  }

  public String getCardholderBillingPostalCode() {
    return CARDHOLDER_BILLING_POSTAL_CODE.get(this);
  }

  public String getCardholderBillingStreetAddress() {
    return CARDHOLDER_BILLING_STREET_ADDRESS.get(this);
  }

  public String getAddressVerificationResultCode() {
    return ADDRESS_VERIFICATION_RESULT_CODE.get(this);
  }
}
