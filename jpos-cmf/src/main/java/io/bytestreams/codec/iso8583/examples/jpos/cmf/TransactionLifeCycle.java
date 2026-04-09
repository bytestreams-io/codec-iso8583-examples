package io.bytestreams.codec.iso8583.examples.jpos.cmf;

import io.bytestreams.codec.core.Codec;
import io.bytestreams.codec.core.Codecs;
import io.bytestreams.codec.core.DataObject;
import io.bytestreams.codec.core.FieldSpec;

public class TransactionLifeCycle extends DataObject {
  public static final FieldSpec<TransactionLifeCycle, String> SUPPORT_INDICATOR =
      field("supportIndicator", Codecs.ascii(1));
  public static final FieldSpec<TransactionLifeCycle, String> TRACE_IDENTIFIER =
      field("traceIdentifier", Codecs.ascii(15));
  public static final FieldSpec<TransactionLifeCycle, String> SEQUENCE_NUMBER =
      field("sequenceNumber", Codecs.hex(2));
  public static final FieldSpec<TransactionLifeCycle, String> AUTHENTICATION_TOKEN =
      field("authenticationToken", Codecs.hex(4));
  public static final Codec<TransactionLifeCycle> CODEC =
      Codecs.sequential(TransactionLifeCycle::new)
          .field(SUPPORT_INDICATOR)
          .field(TRACE_IDENTIFIER)
          .field(SEQUENCE_NUMBER)
          .field(AUTHENTICATION_TOKEN)
          .build();

  public String getSupportIndicator() {
    return SUPPORT_INDICATOR.get(this);
  }

  public String getTraceIdentifier() {
    return TRACE_IDENTIFIER.get(this);
  }

  public String getSequenceNumber() {
    return SEQUENCE_NUMBER.get(this);
  }

  public String getAuthenticationToken() {
    return AUTHENTICATION_TOKEN.get(this);
  }
}
