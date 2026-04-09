package io.bytestreams.codec.iso8583.examples.jpos.cmf;

import io.bytestreams.codec.core.Codec;
import io.bytestreams.codec.iso8583.FieldCodecs;
import io.bytestreams.codec.iso8583.SingleBlockBitmap;

public class VerificationMethod {
  public static final int OFFLINE_BIOMETRICS = 1;
  public static final int OFFLINE_DIGITIZED_SIGNATURE_ANALYSIS = 2;
  public static final int OFFLINE_PIN_ENCRYPTED = 3;
  public static final int OFFLINE_PIN_IN_CLEAR = 4;
  public static final int ONLINE_PIN = 5;
  public static final int MANUAL_SIGNATURE = 6;
  public static final int NONE = 7;
  public static final int UNKNOWN = 8;
  public static final int PUBLIC_KEY_BASED_DIGITAL_SIGNATURE = 13;
  public static final int ACCOUNT_BASED_DIGITAL_SIGNATURE = 14;
  public static final int OFFLINE_BIOGRAPHICS = 15;
  public static final int OFFLINE_MANUAL_VERIFICATION = 16;

  static final Codec<VerificationMethod> CODEC =
      FieldCodecs.singleBlockBitmap(4).xmap(VerificationMethod::new, vm -> vm.bitmap);

  private final SingleBlockBitmap bitmap;

  private VerificationMethod(SingleBlockBitmap bitmap) {
    this.bitmap = bitmap;
  }

  public boolean has(int flag) {
    return bitmap.get(flag);
  }
}
