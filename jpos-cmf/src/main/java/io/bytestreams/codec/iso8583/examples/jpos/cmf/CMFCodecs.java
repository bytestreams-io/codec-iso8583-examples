package io.bytestreams.codec.iso8583.examples.jpos.cmf;

import io.bytestreams.codec.core.Codec;
import io.bytestreams.codec.core.Codecs;
import io.bytestreams.codec.core.util.Converter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQuery;

final class CMFCodecs {

  static final Codec<LocalTime> HHMMSS = Codecs.hex(6).xmap(temporal("HHmmss", LocalTime::from));
  static final Codec<MonthDay> MMDD = Codecs.hex(4).xmap(temporal("MMdd", MonthDay::from));
  static final Codec<YearMonth> YYMM = Codecs.hex(4).xmap(temporal("yyMM", YearMonth::from));
  static final Codec<LocalDate> YYMMDD = Codecs.hex(6).xmap(temporal("yyMMdd", LocalDate::from));
  static final Codec<LocalDate> YYYYMMDD =
      Codecs.hex(8).xmap(temporal("yyyyMMdd", LocalDate::from));
  static final Codec<LocalDateTime> YYYYMMDDHHMMSS =
      Codecs.hex(14).xmap(temporal("yyyyMMddHHmmss", LocalDateTime::from));

  private CMFCodecs() {}

  static Codec<String> hex(int n) {
    return new RightPaddedHexCodec(n);
  }

  static Codec<String> hex(Codec<Integer> lengthCodec) {
    return Codecs.prefixed(lengthCodec, String::length, CMFCodecs::hex);
  }

  static <T extends TemporalAccessor> Converter<String, T> temporal(
      String format, TemporalQuery<T> query) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
    return new Converter<>() {
      @Override
      public T to(String value) {
        return formatter.parse(value, query);
      }

      @Override
      public String from(T value) {
        return formatter.format(value);
      }
    };
  }
}
