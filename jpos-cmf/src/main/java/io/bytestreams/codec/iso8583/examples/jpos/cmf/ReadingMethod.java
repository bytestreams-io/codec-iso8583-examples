package io.bytestreams.codec.iso8583.examples.jpos.cmf;

import io.bytestreams.codec.core.Codec;
import io.bytestreams.codec.iso8583.FieldCodecs;
import io.bytestreams.codec.iso8583.SingleBlockBitmap;

public class ReadingMethod {
  public static final int DATA_ON_FILE = 2;
  public static final int ICC = 3;
  public static final int MAGNETIC_STRIPE = 4;
  public static final int BARCODE = 5;
  public static final int PHYSICAL = 6;
  public static final int CONTACTLESS = 7;
  public static final int UNKNOWN = 8;
  public static final int FALLBACK = 11;
  public static final int MAGNETIC_STRIPE_FAILED = 12;
  public static final int ICC_FAILED = 13;
  public static final int TRACK2_PRESENT = 28;
  public static final int TRACK1_PRESENT = 29;

  static final Codec<ReadingMethod> CODEC =
      FieldCodecs.singleBlockBitmap(4).xmap(ReadingMethod::new, rm -> rm.bitmap);

  private final SingleBlockBitmap bitmap;

  private ReadingMethod(SingleBlockBitmap bitmap) {
    this.bitmap = bitmap;
  }

  public boolean has(int flag) {
    return bitmap.get(flag);
  }
}
