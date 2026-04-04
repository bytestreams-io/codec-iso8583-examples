package io.bytestreams.codec.iso8583.examples.jpos.cmf;

import io.bytestreams.codec.core.Codec;
import io.bytestreams.codec.core.Codecs;
import io.bytestreams.codec.core.DataObject;
import io.bytestreams.codec.core.FieldSpec;
import java.time.LocalTime;
import java.time.MonthDay;

public class TransmissionDateTime extends DataObject {
  public static final FieldSpec<TransmissionDateTime, LocalTime> LOCAL_TIME =
      field("hourMinuteSecond", CMFCodecs.HHMMSS);
  public static final FieldSpec<TransmissionDateTime, MonthDay> MONTH_DAY =
      field("monthDay", CMFCodecs.MMDD);
  public static final Codec<TransmissionDateTime> CODEC =
      Codecs.sequential(TransmissionDateTime::new).field(MONTH_DAY).field(LOCAL_TIME).build();
}
