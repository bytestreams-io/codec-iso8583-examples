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

public class CardAcceptorNameLocation extends DataObject implements Bitmapped {
  static final FieldSpec<CardAcceptorNameLocation, SingleBlockBitmap> BITMAP =
      field("bitmap", FieldCodecs.singleBlockBitmap(8));
  public static final BitmappedFieldSpec<CardAcceptorNameLocation, String> NAME =
      BitmappedFieldSpec.of(2, field("name", Codecs.ascii(Codecs.bcdInt(2))));
  public static final BitmappedFieldSpec<CardAcceptorNameLocation, String> STREET_ADDRESS =
      BitmappedFieldSpec.of(3, field("streetAddress", Codecs.ascii(Codecs.bcdInt(2))));
  public static final BitmappedFieldSpec<CardAcceptorNameLocation, String> CITY =
      BitmappedFieldSpec.of(4, field("city", Codecs.ascii(Codecs.bcdInt(2))));
  public static final BitmappedFieldSpec<CardAcceptorNameLocation, String> STATE =
      BitmappedFieldSpec.of(5, field("state", Codecs.ascii(3)));
  public static final BitmappedFieldSpec<CardAcceptorNameLocation, String> POSTAL_CODE =
      BitmappedFieldSpec.of(6, field("postalCode", Codecs.ascii(10)));
  public static final BitmappedFieldSpec<CardAcceptorNameLocation, String> COUNTRY_CODE =
      BitmappedFieldSpec.of(7, field("countryCode", Codecs.ascii(3)));
  public static final BitmappedFieldSpec<CardAcceptorNameLocation, String> PHONE =
      BitmappedFieldSpec.of(8, field("phone", Codecs.ascii(16)));
  public static final BitmappedFieldSpec<CardAcceptorNameLocation, String> CUSTOMER_SERVICE_PHONE =
      BitmappedFieldSpec.of(9, field("customerServicePhone", Codecs.ascii(16)));
  public static final BitmappedFieldSpec<CardAcceptorNameLocation, String> ADDITIONAL_CONTACT =
      BitmappedFieldSpec.of(10, field("additionalContact", Codecs.ascii(Codecs.bcdInt(2))));
  public static final BitmappedFieldSpec<CardAcceptorNameLocation, String> URL =
      BitmappedFieldSpec.of(11, field("url", Codecs.ascii(Codecs.bcdInt(3))));
  public static final BitmappedFieldSpec<CardAcceptorNameLocation, String> EMAIL =
      BitmappedFieldSpec.of(12, field("email", Codecs.ascii(Codecs.bcdInt(2))));

  static final Codec<CardAcceptorNameLocation> CODEC =
      BitmappedCodecBuilder.builder(CardAcceptorNameLocation::new)
          .bitmap(BITMAP)
          .dataField(NAME)
          .dataField(STREET_ADDRESS)
          .dataField(CITY)
          .dataField(STATE)
          .dataField(POSTAL_CODE)
          .dataField(COUNTRY_CODE)
          .dataField(PHONE)
          .dataField(CUSTOMER_SERVICE_PHONE)
          .dataField(ADDITIONAL_CONTACT)
          .dataField(URL)
          .dataField(EMAIL)
          .build();

  public CardAcceptorNameLocation() {
    BITMAP.set(this, new SingleBlockBitmap(8));
  }

  @Override
  public SingleBlockBitmap getBitmap() {
    return BITMAP.get(this);
  }

  public String getName() {
    return NAME.get(this);
  }

  public String getStreetAddress() {
    return STREET_ADDRESS.get(this);
  }

  public String getCity() {
    return CITY.get(this);
  }

  public String getState() {
    return STATE.get(this);
  }

  public String getPostalCode() {
    return POSTAL_CODE.get(this);
  }

  public String getCountryCode() {
    return COUNTRY_CODE.get(this);
  }
}
