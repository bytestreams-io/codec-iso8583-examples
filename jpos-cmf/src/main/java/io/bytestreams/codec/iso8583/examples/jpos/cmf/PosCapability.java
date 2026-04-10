package io.bytestreams.codec.iso8583.examples.jpos.cmf;

import io.bytestreams.codec.core.Codec;
import io.bytestreams.codec.core.Codecs;
import io.bytestreams.codec.core.DataObject;
import io.bytestreams.codec.core.FieldSpec;

public class PosCapability extends DataObject {
  public static final FieldSpec<PosCapability, ReadingCapability> READING_CAPABILITY =
      field("readingCapability", ReadingCapability.CODEC);
  public static final FieldSpec<PosCapability, VerificationCapability> VERIFICATION_CAPABILITY =
      field("verificationCapability", VerificationCapability.CODEC);
  public static final FieldSpec<PosCapability, Integer> APPROVAL_CODE_LENGTH =
      field("approvalCodeLength", Codecs.asciiInt(1));
  public static final FieldSpec<PosCapability, Integer> CARDHOLDER_RECEIPT_DATA_LENGTH =
      field("cardholderReceiptDataLength", Codecs.asciiInt(3));
  public static final FieldSpec<PosCapability, Integer> CARD_ACCEPTOR_RECEIPT_DATA_LENGTH =
      field("cardAcceptorReceiptDataLength", Codecs.asciiInt(3));
  public static final FieldSpec<PosCapability, Integer> CARDHOLDER_DISPLAY_DATA_LENGTH =
      field("cardholderDisplayDataLength", Codecs.asciiInt(3));
  public static final FieldSpec<PosCapability, Integer> CARD_ACCEPTOR_DISPLAY_DATA_LENGTH =
      field("cardAcceptorDisplayDataLength", Codecs.asciiInt(3));
  public static final FieldSpec<PosCapability, Integer> ICC_SCRIPT_DATA_LENGTH =
      field("iccScriptDataLength", Codecs.asciiInt(3));
  public static final FieldSpec<PosCapability, String> TRACK3_REWRITE_CAPABILITY =
      field("track3RewriteCapability", Codecs.ascii(1));
  public static final FieldSpec<PosCapability, String> CARD_CAPTURE_CAPABILITY =
      field("cardCaptureCapability", Codecs.ascii(1));
  public static final FieldSpec<PosCapability, Integer> PIN_INPUT_LENGTH =
      field("pinInputLength", Codecs.uint8());

  public static final Codec<PosCapability> CODEC =
      Codecs.sequential(PosCapability::new)
          .field(READING_CAPABILITY)
          .field(VERIFICATION_CAPABILITY)
          .field(APPROVAL_CODE_LENGTH)
          .field(CARDHOLDER_RECEIPT_DATA_LENGTH)
          .field(CARD_ACCEPTOR_RECEIPT_DATA_LENGTH)
          .field(CARDHOLDER_DISPLAY_DATA_LENGTH)
          .field(CARD_ACCEPTOR_DISPLAY_DATA_LENGTH)
          .field(ICC_SCRIPT_DATA_LENGTH)
          .field(TRACK3_REWRITE_CAPABILITY)
          .field(CARD_CAPTURE_CAPABILITY)
          .field(PIN_INPUT_LENGTH)
          .build();

  public ReadingCapability getReadingCapability() {
    return READING_CAPABILITY.get(this);
  }

  public VerificationCapability getVerificationCapability() {
    return VERIFICATION_CAPABILITY.get(this);
  }
}
