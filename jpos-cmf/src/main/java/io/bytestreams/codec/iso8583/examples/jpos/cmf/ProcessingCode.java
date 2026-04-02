package io.bytestreams.codec.iso8583.examples.jpos.cmf;

import io.bytestreams.codec.core.Codec;
import io.bytestreams.codec.core.Codecs;
import io.bytestreams.codec.core.DataObject;
import io.bytestreams.codec.core.FieldSpec;

public class ProcessingCode extends DataObject {
  private static final FieldSpec<ProcessingCode, String> TRANSACTION_TYPE =
      field("transactionType", Codecs.ascii(2));
  private static final FieldSpec<ProcessingCode, String> FROM_ACCOUNT =
      field("fromAccount", Codecs.ascii(2));
  private static final FieldSpec<ProcessingCode, String> TO_ACCOUNT =
      field("toAccount", Codecs.ascii(2));
  public static final Codec<ProcessingCode> CODEC =
      Codecs.sequential(ProcessingCode::new)
          .field(TRANSACTION_TYPE)
          .field(FROM_ACCOUNT)
          .field(TO_ACCOUNT)
          .build();

  public String getTransactionType() {
    return TRANSACTION_TYPE.get(this);
  }

  public String getFromAccount() {
    return FROM_ACCOUNT.get(this);
  }

  public String getToAccount() {
    return TO_ACCOUNT.get(this);
  }
}
