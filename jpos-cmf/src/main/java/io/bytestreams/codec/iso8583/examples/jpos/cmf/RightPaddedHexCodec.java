package io.bytestreams.codec.iso8583.examples.jpos.cmf;

import io.bytestreams.codec.core.Codec;
import io.bytestreams.codec.core.Codecs;
import io.bytestreams.codec.core.EncodeResult;
import io.bytestreams.codec.core.Inspectable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

class RightPaddedHexCodec implements Codec<String>, Inspectable<String> {
  private final Codec<String> delegate;
  private final int length;

  RightPaddedHexCodec(int length) {
    this.delegate = Codecs.hex(length + (length % 2));
    this.length = length;
  }

  @Override
  public String decode(InputStream input) throws IOException {
    String raw = delegate.decode(input);
    return raw.substring(0, length);
  }

  @Override
  public EncodeResult encode(String value, OutputStream output) throws IOException {
    return delegate.encode(length % 2 != 0 ? value + "0" : value, output);
  }

  @Override
  public Object inspect(String value) {
    return value;
  }
}
