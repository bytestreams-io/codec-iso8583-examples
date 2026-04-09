package io.bytestreams.codec.iso8583.examples.jpos.cmf;

import io.bytestreams.codec.core.Codec;
import io.bytestreams.codec.core.Codecs;
import io.bytestreams.codec.core.DataObject;
import io.bytestreams.codec.core.FieldSpec;

public class PosDataCode extends DataObject {
  public static final FieldSpec<PosDataCode, ReadingMethod> READING_METHOD =
      field("readingMethod", ReadingMethod.CODEC);
  public static final FieldSpec<PosDataCode, VerificationMethod> VERIFICATION_METHOD =
      field("verificationMethod", VerificationMethod.CODEC);
  public static final FieldSpec<PosDataCode, PosEnvironment> POS_ENVIRONMENT =
      field("posEnvironment", PosEnvironment.CODEC);
  public static final FieldSpec<PosDataCode, SecurityCharacteristic> SECURITY_CHARACTERISTIC =
      field("securityCharacteristic", SecurityCharacteristic.CODEC);
  public static final Codec<PosDataCode> CODEC =
      Codecs.sequential(PosDataCode::new)
          .field(READING_METHOD)
          .field(VERIFICATION_METHOD)
          .field(POS_ENVIRONMENT)
          .field(SECURITY_CHARACTERISTIC)
          .build();

  public ReadingMethod getReadingMethod() {
    return READING_METHOD.get(this);
  }

  public VerificationMethod getVerificationMethod() {
    return VERIFICATION_METHOD.get(this);
  }

  public PosEnvironment getPosEnvironment() {
    return POS_ENVIRONMENT.get(this);
  }

  public SecurityCharacteristic getSecurityCharacteristic() {
    return SECURITY_CHARACTERISTIC.get(this);
  }
}
