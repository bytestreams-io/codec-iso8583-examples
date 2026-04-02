package io.bytestreams.codec.iso8583.examples.jpos.cmf;

import io.bytestreams.codec.core.Codec;
import io.bytestreams.codec.core.Codecs;
import io.bytestreams.codec.core.util.Converter;

final class CMFCodecs {

  private CMFCodecs() {}

  static Codec<String> hexr(Codec<Integer> lengthCodec) {
    return Codecs.prefixed(
        lengthCodec, String::length, n -> Codecs.hex(n + (n % 2)).xmap(rightZeroPadded(n)));
  }

  private static Converter<String, String> rightZeroPadded(int n) {
    return new Converter<>() {
      @Override
      public String to(String s) {
        return s.substring(0, n);
      }

      @Override
      public String from(String s) {
        return n % 2 != 0 ? s + "0" : s;
      }
    };
  }
}
