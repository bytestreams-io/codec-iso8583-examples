package io.bytestreams.codec.iso8583.examples.jpos.cmf;

import io.bytestreams.codec.core.Codec;
import io.bytestreams.codec.core.Codecs;
import io.bytestreams.codec.core.EncodeResult;
import io.bytestreams.codec.core.Inspectable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class PanCodec implements Codec<String>, Inspectable<String> {
  private static final Codec<String> DELEGATE = CMFCodecs.hexr(Codecs.bcdInt(2));

  @Override
  public Object inspect(String value) {
    return value.substring(0, 6) + "******" + value.substring(value.length() - 4);
  }

  @Override
  public EncodeResult encode(String value, OutputStream output) throws IOException {
    return DELEGATE.encode(value, output);
  }

  @Override
  public String decode(InputStream input) throws IOException {
    return DELEGATE.decode(input);
  }
}
