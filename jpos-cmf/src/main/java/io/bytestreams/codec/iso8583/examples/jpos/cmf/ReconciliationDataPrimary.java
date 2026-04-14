package io.bytestreams.codec.iso8583.examples.jpos.cmf;

import io.bytestreams.codec.core.Codec;
import io.bytestreams.codec.core.Codecs;
import io.bytestreams.codec.core.DataObject;
import io.bytestreams.codec.core.FieldSpec;

public class ReconciliationDataPrimary extends DataObject {
  private static final FieldSpec<ReconciliationDataPrimary, String> CREDITS_AMOUNT =
      field("creditsAmount", CMFCodecs.hex(16));
  private static final FieldSpec<ReconciliationDataPrimary, String> CREDITS_NUMBER =
      field("creditsNumber", CMFCodecs.hex(10));
  private static final FieldSpec<ReconciliationDataPrimary, String> CREDITS_CHARGEBACK_AMOUNT =
      field("creditsChargebackAmount", CMFCodecs.hex(16));
  private static final FieldSpec<ReconciliationDataPrimary, String> CREDITS_CHARGEBACK_NUMBER =
      field("creditsChargebackNumber", CMFCodecs.hex(10));
  private static final FieldSpec<ReconciliationDataPrimary, String> CREDITS_REVERSAL_AMOUNT =
      field("creditsReversalAmount", CMFCodecs.hex(16));
  private static final FieldSpec<ReconciliationDataPrimary, String> CREDITS_REVERSAL_NUMBER =
      field("creditsReversalNumber", CMFCodecs.hex(10));
  private static final FieldSpec<ReconciliationDataPrimary, String> DEBITS_AMOUNT =
      field("debitsAmount", CMFCodecs.hex(16));
  private static final FieldSpec<ReconciliationDataPrimary, String> DEBITS_NUMBER =
      field("debitsNumber", CMFCodecs.hex(10));
  private static final FieldSpec<ReconciliationDataPrimary, String> DEBITS_CHARGEBACK_AMOUNT =
      field("debitsChargebackAmount", CMFCodecs.hex(16));
  private static final FieldSpec<ReconciliationDataPrimary, String> DEBITS_CHARGEBACK_NUMBER =
      field("debitsChargebackNumber", CMFCodecs.hex(10));
  private static final FieldSpec<ReconciliationDataPrimary, String> DEBITS_REVERSAL_AMOUNT =
      field("debitsReversalAmount", CMFCodecs.hex(16));
  private static final FieldSpec<ReconciliationDataPrimary, String> DEBITS_REVERSAL_NUMBER =
      field("debitsReversalNumber", CMFCodecs.hex(10));

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

  public String getCreditsAmount() {
    return CREDITS_AMOUNT.get(this);
  }

  public String getCreditsNumber() {
    return CREDITS_NUMBER.get(this);
  }

  public String getCreditsChargebackAmount() {
    return CREDITS_CHARGEBACK_AMOUNT.get(this);
  }

  public String getCreditsChargebackNumber() {
    return CREDITS_CHARGEBACK_NUMBER.get(this);
  }

  public String getCreditsReversalAmount() {
    return CREDITS_REVERSAL_AMOUNT.get(this);
  }

  public String getCreditsReversalNumber() {
    return CREDITS_REVERSAL_NUMBER.get(this);
  }

  public String getDebitsAmount() {
    return DEBITS_AMOUNT.get(this);
  }

  public String getDebitsNumber() {
    return DEBITS_NUMBER.get(this);
  }

  public String getDebitsChargebackAmount() {
    return DEBITS_CHARGEBACK_AMOUNT.get(this);
  }

  public String getDebitsChargebackNumber() {
    return DEBITS_CHARGEBACK_NUMBER.get(this);
  }

  public String getDebitsReversalAmount() {
    return DEBITS_REVERSAL_AMOUNT.get(this);
  }

  public String getDebitsReversalNumber() {
    return DEBITS_REVERSAL_NUMBER.get(this);
  }
}
