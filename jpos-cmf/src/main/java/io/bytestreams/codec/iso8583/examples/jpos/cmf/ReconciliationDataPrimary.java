package io.bytestreams.codec.iso8583.examples.jpos.cmf;

import io.bytestreams.codec.core.Codec;
import io.bytestreams.codec.core.Codecs;
import io.bytestreams.codec.core.DataObject;
import io.bytestreams.codec.core.FieldSpec;

public class ReconciliationDataPrimary extends DataObject {
  private static final FieldSpec<ReconciliationDataPrimary, Long> CREDITS_AMOUNT =
      field("creditsAmount", Codecs.bcdLong(16));
  private static final FieldSpec<ReconciliationDataPrimary, Long> CREDITS_NUMBER =
      field("creditsNumber", Codecs.bcdLong(10));
  private static final FieldSpec<ReconciliationDataPrimary, Long> CREDITS_CHARGEBACK_AMOUNT =
      field("creditsChargebackAmount", Codecs.bcdLong(16));
  private static final FieldSpec<ReconciliationDataPrimary, Long> CREDITS_CHARGEBACK_NUMBER =
      field("creditsChargebackNumber", Codecs.bcdLong(10));
  private static final FieldSpec<ReconciliationDataPrimary, Long> CREDITS_REVERSAL_AMOUNT =
      field("creditsReversalAmount", Codecs.bcdLong(16));
  private static final FieldSpec<ReconciliationDataPrimary, Long> CREDITS_REVERSAL_NUMBER =
      field("creditsReversalNumber", Codecs.bcdLong(10));
  private static final FieldSpec<ReconciliationDataPrimary, Long> DEBITS_AMOUNT =
      field("debitsAmount", Codecs.bcdLong(16));
  private static final FieldSpec<ReconciliationDataPrimary, Long> DEBITS_NUMBER =
      field("debitsNumber", Codecs.bcdLong(10));
  private static final FieldSpec<ReconciliationDataPrimary, Long> DEBITS_CHARGEBACK_AMOUNT =
      field("debitsChargebackAmount", Codecs.bcdLong(16));
  private static final FieldSpec<ReconciliationDataPrimary, Long> DEBITS_CHARGEBACK_NUMBER =
      field("debitsChargebackNumber", Codecs.bcdLong(10));
  private static final FieldSpec<ReconciliationDataPrimary, Long> DEBITS_REVERSAL_AMOUNT =
      field("debitsReversalAmount", Codecs.bcdLong(16));
  private static final FieldSpec<ReconciliationDataPrimary, Long> DEBITS_REVERSAL_NUMBER =
      field("debitsReversalNumber", Codecs.bcdLong(10));

  public static final Codec<ReconciliationDataPrimary> CODEC =
      Codecs.sequential(ReconciliationDataPrimary::new)
          .field(CREDITS_AMOUNT)
          .field(CREDITS_NUMBER)
          .field(CREDITS_CHARGEBACK_AMOUNT)
          .field(CREDITS_CHARGEBACK_NUMBER)
          .field(CREDITS_REVERSAL_AMOUNT)
          .field(CREDITS_REVERSAL_NUMBER)
          .field(DEBITS_AMOUNT)
          .field(DEBITS_NUMBER)
          .field(DEBITS_CHARGEBACK_AMOUNT)
          .field(DEBITS_CHARGEBACK_NUMBER)
          .field(DEBITS_REVERSAL_AMOUNT)
          .field(DEBITS_REVERSAL_NUMBER)
          .build();

  public long getCreditsAmount() {
    return CREDITS_AMOUNT.get(this);
  }

  public long getCreditsNumber() {
    return CREDITS_NUMBER.get(this);
  }

  public long getCreditsChargebackAmount() {
    return CREDITS_CHARGEBACK_AMOUNT.get(this);
  }

  public long getCreditsChargebackNumber() {
    return CREDITS_CHARGEBACK_NUMBER.get(this);
  }

  public long getCreditsReversalAmount() {
    return CREDITS_REVERSAL_AMOUNT.get(this);
  }

  public long getCreditsReversalNumber() {
    return CREDITS_REVERSAL_NUMBER.get(this);
  }

  public long getDebitsAmount() {
    return DEBITS_AMOUNT.get(this);
  }

  public long getDebitsNumber() {
    return DEBITS_NUMBER.get(this);
  }

  public long getDebitsChargebackAmount() {
    return DEBITS_CHARGEBACK_AMOUNT.get(this);
  }

  public long getDebitsChargebackNumber() {
    return DEBITS_CHARGEBACK_NUMBER.get(this);
  }

  public long getDebitsReversalAmount() {
    return DEBITS_REVERSAL_AMOUNT.get(this);
  }

  public long getDebitsReversalNumber() {
    return DEBITS_REVERSAL_NUMBER.get(this);
  }
}
