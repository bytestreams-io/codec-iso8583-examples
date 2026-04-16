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

public class TppPrivateData extends DataObject implements Bitmapped {
  static final FieldSpec<TppPrivateData, MultiBlockBitmap> BITMAP =
      field("bitmap", FieldCodecs.multiBlockBitmap(8));
  public static final BitmappedFieldSpec<TppPrivateData, String> VERSION =
      BitmappedFieldSpec.of(2, field("version", Codecs.ascii(Codecs.bcdInt(2))));
  public static final BitmappedFieldSpec<TppPrivateData, String> FIRST_NAME =
      BitmappedFieldSpec.of(3, field("firstName", Codecs.ascii(Codecs.bcdInt(2))));
  public static final BitmappedFieldSpec<TppPrivateData, String> MIDDLE_NAME =
      BitmappedFieldSpec.of(4, field("middleName", Codecs.ascii(Codecs.bcdInt(2))));
  public static final BitmappedFieldSpec<TppPrivateData, String> LAST_NAME =
      BitmappedFieldSpec.of(5, field("lastName", Codecs.ascii(Codecs.bcdInt(2))));
  public static final BitmappedFieldSpec<TppPrivateData, String> SECONDARY_LAST_NAME =
      BitmappedFieldSpec.of(6, field("secondaryLastName", Codecs.ascii(Codecs.bcdInt(2))));
  public static final BitmappedFieldSpec<TppPrivateData, String> EMAIL =
      BitmappedFieldSpec.of(7, field("email", Codecs.ascii(Codecs.bcdInt(2))));
  public static final BitmappedFieldSpec<TppPrivateData, byte[]> STATUS_FLAGS =
      BitmappedFieldSpec.of(8, field("statusFlags", Codecs.binary(1)));
  public static final BitmappedFieldSpec<TppPrivateData, String> HONORIFIC =
      BitmappedFieldSpec.of(9, field("honorific", Codecs.ascii(Codecs.bcdInt(2))));
  public static final BitmappedFieldSpec<TppPrivateData, String> GENDER =
      BitmappedFieldSpec.of(10, field("gender", Codecs.ascii(1)));
  public static final BitmappedFieldSpec<TppPrivateData, String> ADDRESS_1 =
      BitmappedFieldSpec.of(11, field("address1", Codecs.ascii(Codecs.bcdInt(2))));
  public static final BitmappedFieldSpec<TppPrivateData, String> ADDRESS_2 =
      BitmappedFieldSpec.of(12, field("address2", Codecs.ascii(Codecs.bcdInt(2))));
  public static final BitmappedFieldSpec<TppPrivateData, String> CITY =
      BitmappedFieldSpec.of(13, field("city", Codecs.ascii(Codecs.bcdInt(2))));
  public static final BitmappedFieldSpec<TppPrivateData, String> STATE =
      BitmappedFieldSpec.of(14, field("state", Codecs.ascii(2)));
  public static final BitmappedFieldSpec<TppPrivateData, String> ZIP_CODE =
      BitmappedFieldSpec.of(15, field("zipCode", Codecs.ascii(Codecs.bcdInt(2))));
  public static final BitmappedFieldSpec<TppPrivateData, String> COUNTRY_CODE =
      BitmappedFieldSpec.of(16, field("countryCode", Codecs.ascii(2)));
  public static final BitmappedFieldSpec<TppPrivateData, String> PHONE =
      BitmappedFieldSpec.of(17, field("phone", Codecs.ascii(Codecs.bcdInt(2))));
  public static final BitmappedFieldSpec<TppPrivateData, String> NOTES =
      BitmappedFieldSpec.of(18, field("notes", Codecs.ascii(Codecs.bcdInt(2))));
  public static final BitmappedFieldSpec<TppPrivateData, String> DATES =
      BitmappedFieldSpec.of(19, field("dates", CMFCodecs.hex(Codecs.bcdInt(2))));
  public static final BitmappedFieldSpec<TppPrivateData, String> CUSTOMER_ALTERNATE_IDENTIFIER =
      BitmappedFieldSpec.of(
          20, field("customerAlternateIdentifier", Codecs.ascii(Codecs.bcdInt(2))));
  public static final BitmappedFieldSpec<TppPrivateData, String> CARD_PRODUCT_ID =
      BitmappedFieldSpec.of(21, field("cardProductId", CMFCodecs.hex(Codecs.bcdInt(2))));
  public static final BitmappedFieldSpec<TppPrivateData, String> UUIDS =
      BitmappedFieldSpec.of(22, field("uuids", Codecs.ascii(Codecs.bcdInt(3))));
  public static final BitmappedFieldSpec<TppPrivateData, String> SSN =
      BitmappedFieldSpec.of(23, field("ssn", Codecs.ascii(11)));
  public static final BitmappedFieldSpec<TppPrivateData, String> INSTALLMENTS_INFORMATION =
      BitmappedFieldSpec.of(24, field("installmentsInformation", CMFCodecs.hex(4)));
  public static final BitmappedFieldSpec<TppPrivateData, String> NETWORK_NAME =
      BitmappedFieldSpec.of(25, field("networkName", Codecs.ascii(Codecs.bcdInt(2))));
  public static final BitmappedFieldSpec<TppPrivateData, String> DEVICE_ID =
      BitmappedFieldSpec.of(26, field("deviceId", CMFCodecs.hex(Codecs.bcdInt(2))));
  public static final BitmappedFieldSpec<TppPrivateData, String> GEOLOCATION =
      BitmappedFieldSpec.of(27, field("geolocation", Codecs.ascii(Codecs.bcdInt(2))));
  public static final BitmappedFieldSpec<TppPrivateData, String> CARDHOLDER_IDENTIFICATION_DATA =
      BitmappedFieldSpec.of(
          28, field("cardholderIdentificationData", Codecs.ascii(Codecs.bcdInt(2))));
  public static final BitmappedFieldSpec<TppPrivateData, String> INVOICE =
      BitmappedFieldSpec.of(29, field("invoice", Codecs.ascii(Codecs.bcdInt(2))));
  public static final BitmappedFieldSpec<TppPrivateData, String> TOTAL_RECORDS_FOR_PRESENTMENT =
      BitmappedFieldSpec.of(30, field("totalRecordsForPresentment", CMFCodecs.hex(2)));
  public static final BitmappedFieldSpec<TppPrivateData, String> PRESENTMENT_SEQUENCE_NUMBER =
      BitmappedFieldSpec.of(31, field("presentmentSequenceNumber", CMFCodecs.hex(2)));
  public static final BitmappedFieldSpec<TppPrivateData, String> INTERCHANGE_FEE =
      BitmappedFieldSpec.of(32, field("interchangeFee", Codecs.ascii(Codecs.bcdInt(2))));
  public static final BitmappedFieldSpec<TppPrivateData, String> REMOTE_ISO_RESULT_CODE =
      BitmappedFieldSpec.of(39, field("remoteIsoResultCode", Codecs.ascii(Codecs.bcdInt(2))));
  public static final BitmappedFieldSpec<TppPrivateData, byte[]> NEW_PIN_BLOCK =
      BitmappedFieldSpec.of(52, field("newPinBlock", Codecs.binary(8)));
  public static final BitmappedFieldSpec<TppPrivateData, byte[]> KSN_FOR_NEW_PIN_BLOCK =
      BitmappedFieldSpec.of(53, field("ksnForNewPinBlock", Codecs.binary(Codecs.bcdInt(2))));
  public static final BitmappedFieldSpec<TppPrivateData, String> ADDITIONAL_DATA =
      BitmappedFieldSpec.of(63, field("additionalData", Codecs.ascii(Codecs.bcdInt(4))));
  public static final BitmappedFieldSpec<TppPrivateData, String> CARD_TOKEN =
      BitmappedFieldSpec.of(69, field("cardToken", Codecs.ascii(40)));
  public static final BitmappedFieldSpec<TppPrivateData, String> TRANSACTION_ID =
      BitmappedFieldSpec.of(70, field("transactionId", CMFCodecs.hex(19)));
  public static final BitmappedFieldSpec<TppPrivateData, String> TRANSACTION_GROUP_ID =
      BitmappedFieldSpec.of(71, field("transactionGroupId", CMFCodecs.hex(19)));

  static final Codec<TppPrivateData> CODEC =
      BitmappedCodecBuilder.builder(TppPrivateData::new)
          .bitmap(BITMAP)
          .dataField(VERSION)
          .dataField(FIRST_NAME)
          .dataField(MIDDLE_NAME)
          .dataField(LAST_NAME)
          .dataField(SECONDARY_LAST_NAME)
          .dataField(EMAIL)
          .dataField(STATUS_FLAGS)
          .dataField(HONORIFIC)
          .dataField(GENDER)
          .dataField(ADDRESS_1)
          .dataField(ADDRESS_2)
          .dataField(CITY)
          .dataField(STATE)
          .dataField(ZIP_CODE)
          .dataField(COUNTRY_CODE)
          .dataField(PHONE)
          .dataField(NOTES)
          .dataField(DATES)
          .dataField(CUSTOMER_ALTERNATE_IDENTIFIER)
          .dataField(CARD_PRODUCT_ID)
          .dataField(UUIDS)
          .dataField(SSN)
          .dataField(INSTALLMENTS_INFORMATION)
          .dataField(NETWORK_NAME)
          .dataField(DEVICE_ID)
          .dataField(GEOLOCATION)
          .dataField(CARDHOLDER_IDENTIFICATION_DATA)
          .dataField(INVOICE)
          .dataField(TOTAL_RECORDS_FOR_PRESENTMENT)
          .dataField(PRESENTMENT_SEQUENCE_NUMBER)
          .dataField(INTERCHANGE_FEE)
          .dataField(REMOTE_ISO_RESULT_CODE)
          .dataField(NEW_PIN_BLOCK)
          .dataField(KSN_FOR_NEW_PIN_BLOCK)
          .dataField(ADDITIONAL_DATA)
          .dataField(CARD_TOKEN)
          .dataField(TRANSACTION_ID)
          .dataField(TRANSACTION_GROUP_ID)
          .build();

  public TppPrivateData() {
    BITMAP.set(this, new MultiBlockBitmap(8));
  }

  @Override
  public MultiBlockBitmap getBitmap() {
    return BITMAP.get(this);
  }

  public String getVersion() {
    return VERSION.get(this);
  }

  public String getFirstName() {
    return FIRST_NAME.get(this);
  }

  public String getMiddleName() {
    return MIDDLE_NAME.get(this);
  }

  public String getLastName() {
    return LAST_NAME.get(this);
  }

  public String getSecondaryLastName() {
    return SECONDARY_LAST_NAME.get(this);
  }

  public String getEmail() {
    return EMAIL.get(this);
  }

  public byte[] getStatusFlags() {
    return STATUS_FLAGS.get(this);
  }

  public String getHonorific() {
    return HONORIFIC.get(this);
  }

  public String getGender() {
    return GENDER.get(this);
  }

  public String getAddress1() {
    return ADDRESS_1.get(this);
  }

  public String getAddress2() {
    return ADDRESS_2.get(this);
  }

  public String getCity() {
    return CITY.get(this);
  }

  public String getState() {
    return STATE.get(this);
  }

  public String getZipCode() {
    return ZIP_CODE.get(this);
  }

  public String getCountryCode() {
    return COUNTRY_CODE.get(this);
  }

  public String getPhone() {
    return PHONE.get(this);
  }

  public String getNotes() {
    return NOTES.get(this);
  }

  public String getDates() {
    return DATES.get(this);
  }

  public String getCustomerAlternateIdentifier() {
    return CUSTOMER_ALTERNATE_IDENTIFIER.get(this);
  }

  public String getCardProductId() {
    return CARD_PRODUCT_ID.get(this);
  }

  public String getUuids() {
    return UUIDS.get(this);
  }

  public String getSsn() {
    return SSN.get(this);
  }

  public String getInstallmentsInformation() {
    return INSTALLMENTS_INFORMATION.get(this);
  }

  public String getNetworkName() {
    return NETWORK_NAME.get(this);
  }

  public String getDeviceId() {
    return DEVICE_ID.get(this);
  }

  public String getGeolocation() {
    return GEOLOCATION.get(this);
  }

  public String getCardholderIdentificationData() {
    return CARDHOLDER_IDENTIFICATION_DATA.get(this);
  }

  public String getInvoice() {
    return INVOICE.get(this);
  }

  public String getTotalRecordsForPresentment() {
    return TOTAL_RECORDS_FOR_PRESENTMENT.get(this);
  }

  public String getPresentmentSequenceNumber() {
    return PRESENTMENT_SEQUENCE_NUMBER.get(this);
  }

  public String getInterchangeFee() {
    return INTERCHANGE_FEE.get(this);
  }

  public String getRemoteIsoResultCode() {
    return REMOTE_ISO_RESULT_CODE.get(this);
  }

  public byte[] getNewPinBlock() {
    return NEW_PIN_BLOCK.get(this);
  }

  public byte[] getKsnForNewPinBlock() {
    return KSN_FOR_NEW_PIN_BLOCK.get(this);
  }

  public String getAdditionalData() {
    return ADDITIONAL_DATA.get(this);
  }

  public String getCardToken() {
    return CARD_TOKEN.get(this);
  }

  public String getTransactionId() {
    return TRANSACTION_ID.get(this);
  }

  public String getTransactionGroupId() {
    return TRANSACTION_GROUP_ID.get(this);
  }
}
