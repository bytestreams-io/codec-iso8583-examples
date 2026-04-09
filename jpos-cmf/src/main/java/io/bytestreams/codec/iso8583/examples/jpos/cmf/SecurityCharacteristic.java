package io.bytestreams.codec.iso8583.examples.jpos.cmf;

import io.bytestreams.codec.core.Codec;
import io.bytestreams.codec.iso8583.FieldCodecs;
import io.bytestreams.codec.iso8583.SingleBlockBitmap;

public class SecurityCharacteristic {
  public static final int PRIVATE_ALG_ENCRYPTION = 1;
  public static final int END_TO_END_ENCRYPTION = 2;
  public static final int CHANNEL_ENCRYPTION = 3;
  public static final int PASS_THROUGH_MACING = 4;
  public static final int CHANNEL_MACING = 5;
  public static final int OPEN_NETWORK = 6;
  public static final int PRIVATE_NETWORK = 7;
  public static final int UNKNOWN = 8;
  public static final int ACQUIRER_MANAGED_END_TO_END_ENCRYPTION = 9;
  public static final int MERCHANT_MANAGED_POINT_TO_POINT_ENCRYPTION = 10;
  public static final int MERCHANT_MANAGED_END_TO_END_ENCRYPTION = 11;
  public static final int CARDHOLDER_MANAGED_POINT_TO_POINT_ENCRYPTION = 12;
  public static final int CARDHOLDER_MANAGED_END_TO_END_ENCRYPTION = 13;
  public static final int STD_ALG_MACING = 14;
  public static final int PRIVATE_ALG_MACING = 15;
  public static final int PKI_ENCRYPTION = 16;
  public static final int ACQUIRER_MANAGED_POINT_TO_POINT_ENCRYPTION = 24;

  static final Codec<SecurityCharacteristic> CODEC =
      FieldCodecs.singleBlockBitmap(4).xmap(SecurityCharacteristic::new, sc -> sc.bitmap);

  private final SingleBlockBitmap bitmap;

  private SecurityCharacteristic(SingleBlockBitmap bitmap) {
    this.bitmap = bitmap;
  }

  public boolean has(int flag) {
    return bitmap.get(flag);
  }
}
