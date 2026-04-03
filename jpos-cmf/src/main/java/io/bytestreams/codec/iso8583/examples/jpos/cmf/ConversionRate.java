package io.bytestreams.codec.iso8583.examples.jpos.cmf;

import io.bytestreams.codec.core.Codec;
import io.bytestreams.codec.core.Codecs;
import io.bytestreams.codec.core.EncodeResult;
import io.bytestreams.codec.core.Inspectable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

public class ConversionRate {
  public static final Codec<ConversionRate> CODEC = new ConversionRateCodec();

  private final int scale;
  private final int value;

  private ConversionRate(String decoded) {
    scale = Integer.parseInt(decoded.substring(0, 1));
    value = Integer.parseInt(decoded.substring(1));
  }

  public int getScale() {
    return scale;
  }

  public int getValue() {
    return value;
  }

  private String encode() {
    return String.format("%1d%07d", scale, value);
  }

  private static class ConversionRateCodec
      implements Codec<ConversionRate>, Inspectable<ConversionRate> {
    private static final Codec<ConversionRate> DELEGATE =
        Codecs.hex(8).xmap(ConversionRate::new, ConversionRate::encode);

    @Override
    public EncodeResult encode(ConversionRate value, OutputStream output) throws IOException {
      return DELEGATE.encode(value, output);
    }

    @Override
    public ConversionRate decode(InputStream input) throws IOException {
      return DELEGATE.decode(input);
    }

    @Override
    public Object inspect(ConversionRate value) {
      return Map.of("scale", value.scale, "value", value.value);
    }
  }
}
