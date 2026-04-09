package io.bytestreams.codec.iso8583.examples.jpos.cmf;

import io.bytestreams.codec.core.Codec;
import io.bytestreams.codec.iso8583.FieldCodecs;
import io.bytestreams.codec.iso8583.SingleBlockBitmap;

public class PosEnvironment {
  public static final int STORED_DETAILS = 1;
  public static final int RECURRING = 2;
  public static final int M_COMMERCE = 3;
  public static final int E_COMMERCE = 4;
  public static final int MOTO = 5;
  public static final int UNATTENDED = 6;
  public static final int ATTENDED = 7;
  public static final int UNKNOWN = 8;
  public static final int INSTALLMENT_TRANSACTION = 12;
  public static final int DEFERRED_TRANSACTION = 13;
  public static final int ATM_OFF_BANK = 14;
  public static final int ATM_ON_BANK = 15;
  public static final int CAT = 16;

  static final Codec<PosEnvironment> CODEC =
      FieldCodecs.singleBlockBitmap(4).xmap(PosEnvironment::new, pe -> pe.bitmap);

  private final SingleBlockBitmap bitmap;

  private PosEnvironment(SingleBlockBitmap bitmap) {
    this.bitmap = bitmap;
  }

  public boolean has(int flag) {
    return bitmap.get(flag);
  }
}
