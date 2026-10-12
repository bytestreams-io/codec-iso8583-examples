package io.bytestreams.codec.iso8583.examples.jpos.cmf;

import io.bytestreams.codec.core.Codec;
import io.bytestreams.codec.core.Codecs;
import io.bytestreams.codec.iso8583.BitmappedFieldSpec;

public class CMFV1Message extends CMFMessage {
  public static final BitmappedFieldSpec<CMFV1Message, byte[]> ELECTRONIC_COMMERCE_DATA =
      BitmappedFieldSpec.of(34, field("electronicCommerceData", Codecs.binary(Codecs.bcdInt(4))));
  public static final BitmappedFieldSpec<CMFV1Message, CardAcceptorNameLocation>
      CARD_ACCEPTOR_NAME_LOCATION =
          BitmappedFieldSpec.of(
              43,
              field(
                  "cardAcceptorNameLocation",
                  Codecs.prefixed(Codecs.bcdInt(4), CardAcceptorNameLocation.CODEC)));
  public static final BitmappedFieldSpec<CMFV1Message, byte[]> ADDITIONAL_RESPONSE_DATA =
      BitmappedFieldSpec.of(44, field("additionalResponseData", Codecs.binary(Codecs.bcdInt(4))));
  public static final BitmappedFieldSpec<CMFV1Message, VerificationData> VERIFICATION_DATA =
      BitmappedFieldSpec.of(
          49, field("verificationData", Codecs.prefixed(Codecs.bcdInt(4), VerificationData.CODEC)));
  public static final BitmappedFieldSpec<CMFV1Message, byte[]> RESERVED_51 =
      BitmappedFieldSpec.of(51, field("reserved51", Codecs.binary(Codecs.bcdInt(4))));
  public static final BitmappedFieldSpec<CMFV1Message, byte[]> IC_SYSTEM_RELATED_DATA =
      BitmappedFieldSpec.of(55, field("icSystemRelatedData", Codecs.binary(Codecs.bcdInt(4))));
  public static final BitmappedFieldSpec<CMFV1Message, byte[]> RESERVED_71 =
      BitmappedFieldSpec.of(71, field("reserved71", Codecs.binary(Codecs.bcdInt(4))));
  public static final BitmappedFieldSpec<CMFV1Message, byte[]> TRANSACTION_SPECIFIC_DATA =
      BitmappedFieldSpec.of(104, field("transactionSpecificData", Codecs.binary(Codecs.bcdInt(4))));
  public static final BitmappedFieldSpec<CMFV1Message, TppPrivateData> TPP_PRIVATE_DATA =
      BitmappedFieldSpec.of(
          113, field("tppPrivateData", Codecs.prefixed(Codecs.bcdInt(4), TppPrivateData.CODEC)));

  public static final Codec<CMFV1Message> CODEC =
      CMFMessage.codecBuilder(CMFV1Message::new)
          .dataField(ELECTRONIC_COMMERCE_DATA)
          .dataField(CARD_ACCEPTOR_NAME_LOCATION)
          .dataField(ADDITIONAL_RESPONSE_DATA)
          .dataField(VERIFICATION_DATA)
          .dataField(RESERVED_51)
          .dataField(IC_SYSTEM_RELATED_DATA)
          .dataField(RESERVED_71)
          .dataField(TRANSACTION_SPECIFIC_DATA)
          .dataField(TPP_PRIVATE_DATA)
          .build();
}
