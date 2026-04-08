package io.bytestreams.codec.iso8583.examples.jpos.cmf;

import io.bytestreams.codec.core.Codec;
import io.bytestreams.codec.core.Codecs;
import io.bytestreams.codec.core.DataObject;
import io.bytestreams.codec.core.FieldSpec;

public class MessageErrorIndicator extends DataObject {
  private static final FieldSpec<MessageErrorIndicator, String> ERROR_SEVERITY =
      field("errorSeverity", Codecs.ascii(1));
  private static final FieldSpec<MessageErrorIndicator, String> ERROR_CODE =
      field("errorCode", Codecs.ascii(4));
  private static final FieldSpec<MessageErrorIndicator, String> ERROR_DATA_ELEMENT =
      field("errorDataElement", Codecs.ascii(3));
  private static final FieldSpec<MessageErrorIndicator, String> ERROR_DATA_SUBELEMENT =
      field("errorDataSubelement", Codecs.ascii(3));
  private static final FieldSpec<MessageErrorIndicator, String> ERROR_DATA_ELEMENT_VALUE =
      field("errorDataElementValue", Codecs.ascii(3));
  static final Codec<MessageErrorIndicator> CODEC =
      Codecs.sequential(MessageErrorIndicator::new)
          .field(ERROR_SEVERITY)
          .field(ERROR_CODE)
          .field(ERROR_DATA_ELEMENT)
          .field(ERROR_DATA_SUBELEMENT)
          .field(ERROR_DATA_ELEMENT_VALUE)
          .build();

  public String getErrorSeverity() {
    return ERROR_SEVERITY.get(this);
  }

  public String getErrorCode() {
    return ERROR_CODE.get(this);
  }

  public String getErrorDataElement() {
    return ERROR_DATA_ELEMENT.get(this);
  }

  public String getErrorDataSubelement() {
    return ERROR_DATA_SUBELEMENT.get(this);
  }

  public String getErrorDataElementValue() {
    return ERROR_DATA_ELEMENT_VALUE.get(this);
  }
}
