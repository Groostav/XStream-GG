package com.thoughtworks.xstream.converters.basic;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.SingleValueConverter;

import java.time.*;
import java.time.chrono.HijrahDate;
import java.time.chrono.JapaneseDate;
import java.time.chrono.MinguoDate;
import java.time.chrono.ThaiBuddhistDate;

/**
 * Created by Geoff Groos on 2015-04-29.
 */
public class JavaTime {

    public static void registerAll(XStream instance){
        instance.registerConverter(InstantConverter,            XStream.PRIORITY_NORMAL);
        instance.registerConverter(LocalTimeConverter,          XStream.PRIORITY_NORMAL);
        instance.registerConverter(LocalDateTimeConverter,      XStream.PRIORITY_NORMAL);
        instance.registerConverter(LocalDateConverter,          XStream.PRIORITY_NORMAL);
        instance.registerConverter(HijrahDateConverter,         XStream.PRIORITY_NORMAL);
        instance.registerConverter(JapaneseDateConverter,       XStream.PRIORITY_NORMAL);
        instance.registerConverter(MinguoDateConverter,         XStream.PRIORITY_NORMAL);
        instance.registerConverter(ThaiBuddhistDateConverter,   XStream.PRIORITY_NORMAL);
        instance.registerConverter(OffsetDateTimeConverter,     XStream.PRIORITY_NORMAL);
        instance.registerConverter(OffsetTimeConverter,         XStream.PRIORITY_NORMAL);
        instance.registerConverter(YearConverter,               XStream.PRIORITY_NORMAL);
        instance.registerConverter(YearMonthConverter,          XStream.PRIORITY_NORMAL);
        instance.registerConverter(ZonedDateTimeConverter,      XStream.PRIORITY_NORMAL);
        instance.registerConverter(DurationConverter,           XStream.PRIORITY_NORMAL);
        instance.registerConverter(PeriodConverter,             XStream.PRIORITY_NORMAL);
    }

    public static final SingleValueConverter InstantConverter           = new InstantConverter();
    public static final SingleValueConverter LocalTimeConverter         = new LocalTimeConverter();
    public static final SingleValueConverter LocalDateTimeConverter     = new LocalDateTimeConverter();
    public static final SingleValueConverter LocalDateConverter         = new LocalDateConverter();
    public static final SingleValueConverter HijrahDateConverter        = new HijrahDateConverter();
    public static final SingleValueConverter JapaneseDateConverter      = new JapaneseDateConverter();
    public static final SingleValueConverter MinguoDateConverter        = new MinguoDateConverter();
    public static final SingleValueConverter ThaiBuddhistDateConverter  = new ThaiBuddhistDateConverter();
    public static final SingleValueConverter OffsetDateTimeConverter    = new OffsetDateTimeConverter();
    public static final SingleValueConverter OffsetTimeConverter        = new OffsetTimeConverter();
    public static final SingleValueConverter YearConverter              = new YearConverter();
    public static final SingleValueConverter YearMonthConverter         = new YearMonthConverter();
    public static final SingleValueConverter ZonedDateTimeConverter     = new ZonedDateTimeConverter();
    public static final SingleValueConverter DurationConverter          = new DurationConverter();
    public static final SingleValueConverter PeriodConverter            = new PeriodConverter();

    public static class InstantConverter            extends JavaTimeConverter{ private InstantConverter(){          super(Instant.class);}          public Object fromString(String str) { return Instant.parse(str);                           }}
    public static class LocalTimeConverter          extends JavaTimeConverter{ private LocalTimeConverter(){        super(LocalTime.class);}        public Object fromString(String str) { return LocalTime.parse(str);                         }}
    public static class LocalDateTimeConverter      extends JavaTimeConverter{ private LocalDateTimeConverter(){    super(LocalDateTime.class);}    public Object fromString(String str) { return LocalDateTime.parse(str);                     }}
    public static class LocalDateConverter          extends JavaTimeConverter{ private LocalDateConverter(){        super(LocalDate.class);}        public Object fromString(String str) { return LocalDate.parse(str);                         }}

    public static class HijrahDateConverter         extends JavaTimeConverter{ private HijrahDateConverter(){       super(HijrahDate.class);}       public Object fromString(String str) { return HijrahDate.from(LocalDate.parse(str));        }}
    public static class JapaneseDateConverter       extends JavaTimeConverter{ private JapaneseDateConverter(){     super(JapaneseDate.class);}     public Object fromString(String str) { return JapaneseDate.from(LocalDate.parse(str));      }}
    public static class MinguoDateConverter         extends JavaTimeConverter{ private MinguoDateConverter(){       super(MinguoDate.class);}       public Object fromString(String str) { return MinguoDate.from(LocalDate.parse(str));        }}
    public static class ThaiBuddhistDateConverter   extends JavaTimeConverter{ private ThaiBuddhistDateConverter(){ super(ThaiBuddhistDate.class);} public Object fromString(String str) { return ThaiBuddhistDate.from(LocalDate.parse(str));  }}

    public static class OffsetDateTimeConverter     extends JavaTimeConverter{ private OffsetDateTimeConverter(){   super(OffsetDateTime.class);}   public Object fromString(String str) { return OffsetDateTime.parse(str);                    }}
    public static class OffsetTimeConverter         extends JavaTimeConverter{ private OffsetTimeConverter(){       super(OffsetTime.class);}       public Object fromString(String str) { return OffsetTime.parse(str);                        }}
    public static class YearConverter               extends JavaTimeConverter{ private YearConverter(){             super(Year.class);}             public Object fromString(String str) { return Year.parse(str);                              }}
    public static class YearMonthConverter          extends JavaTimeConverter{ private YearMonthConverter(){        super(YearMonth.class);}        public Object fromString(String str) { return YearMonth.parse(str);                         }}
    public static class ZonedDateTimeConverter      extends JavaTimeConverter{ private ZonedDateTimeConverter(){    super(ZonedDateTime.class);}    public Object fromString(String str) { return ZonedDateTime.parse(str);                     }}

    public static class DurationConverter           extends JavaTimeConverter{ private DurationConverter(){         super(Duration.class);}         public Object fromString(String str) { return Duration.parse(str);                          }}
    public static class PeriodConverter             extends JavaTimeConverter{ private PeriodConverter(){           super(Period.class);}           public Object fromString(String str) { return Period.parse(str);                            }}

    public static abstract class JavaTimeConverter extends AbstractSingleValueConverter{

        private final Class typeToConvert;

        protected JavaTimeConverter(Class typeToConvert){
            this.typeToConvert = typeToConvert;
        }

        @Override
        public boolean canConvert(Class type) {
            return type != null && type.equals(typeToConvert);
        }

        @Override
        public String toString(Object obj) {
            return obj.toString();
        }
    }
}
