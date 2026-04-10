package io.bytestreams.codec.iso8583.examples.jpos.cmf;

import io.bytestreams.codec.core.Codec;
import io.bytestreams.codec.core.Codecs;
import io.bytestreams.codec.core.DataObject;
import io.bytestreams.codec.core.FieldSpec;

public class AmountsOriginal extends DataObject {
  public static final FieldSpec<AmountsOriginal, CurrencyAmount> REPLACEMENT_AMOUNT =
      field("replacementAmount", CurrencyAmount.codec(16));
  public static final FieldSpec<AmountsOriginal, CurrencyAmount> REPLACEMENT_RECONCILIATION_AMOUNT =
      field("replacementReconciliationAmount", CurrencyAmount.codec(16));

  public static final Codec<AmountsOriginal> CODEC =
      Codecs.sequential(AmountsOriginal::new)
          .field(REPLACEMENT_AMOUNT)
          .field(REPLACEMENT_RECONCILIATION_AMOUNT)
          .build();

  public CurrencyAmount getReplacementAmount() {
    return REPLACEMENT_AMOUNT.get(this);
  }

  public CurrencyAmount getReplacementReconciliationAmount() {
    return REPLACEMENT_RECONCILIATION_AMOUNT.get(this);
  }
}
