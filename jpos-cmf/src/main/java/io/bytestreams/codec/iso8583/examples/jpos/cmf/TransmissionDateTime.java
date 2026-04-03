package io.bytestreams.codec.iso8583.examples.jpos.cmf;

import io.bytestreams.codec.core.Codec;
import io.bytestreams.codec.core.Codecs;
import io.bytestreams.codec.core.DataObject;
import io.bytestreams.codec.core.FieldSpec;
import io.bytestreams.codec.core.util.Converter;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;

public class TransmissionDateTime extends DataObject {
  public static final FieldSpec<TransmissionDateTime, MonthDay> MONTH_DAY =
      field("monthDay", Codecs.hex(4).xmap(MonthDayConverter.INSTANCE));
  public static final FieldSpec<TransmissionDateTime, LocalTime> LOCAL_TIME =
      field("hourMinuteSecond", Codecs.hex(6).xmap(LocalTimeConverter.INSTANCE));
  public static final Codec<TransmissionDateTime> CODEC =
      Codecs.sequential(TransmissionDateTime::new).field(MONTH_DAY).field(LOCAL_TIME).build();

  private static class MonthDayConverter implements Converter<String, MonthDay> {
    static final MonthDayConverter INSTANCE = new MonthDayConverter();
    private static final DateTimeFormatter MMDD = DateTimeFormatter.ofPattern("MMdd");

    @Override
    public MonthDay to(String value) {
      return MonthDay.parse(value, MMDD);
    }

    @Override
    public String from(MonthDay value) {
      return value.format(MMDD);
    }
  }

  private static class LocalTimeConverter implements Converter<String, LocalTime> {
    static final LocalTimeConverter INSTANCE = new LocalTimeConverter();
    private static final DateTimeFormatter HHMMSS = DateTimeFormatter.ofPattern("HHmmss");

    @Override
    public LocalTime to(String value) {
      return LocalTime.parse(value, HHMMSS);
    }

    @Override
    public String from(LocalTime value) {
      return value.format(HHMMSS);
    }
  }
}
