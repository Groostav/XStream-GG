package com.thoughtworks.xstream.converters.basic;

import com.thoughtworks.xstream.XStream;

import java.io.Serializable;
import java.time.*;
import java.time.chrono.HijrahDate;
import java.time.chrono.JapaneseDate;
import java.time.chrono.MinguoDate;
import java.time.chrono.ThaiBuddhistDate;
import java.util.function.Function;

/**
 * Created by Geoff Groos on 2015-04-29.
 */
public class JavaTime {

    public static void registerAll(XStream instance){
        instance.registerConverter(InstantConverter, XStream.PRIORITY_NORMAL);
        instance.registerConverter(LocalTimeConverter, XStream.PRIORITY_NORMAL);
        instance.registerConverter(LocalDateTimeConverter, XStream.PRIORITY_NORMAL);
        instance.registerConverter(LocalDateConverter, XStream.PRIORITY_NORMAL);

        instance.registerConverter(HijrahDateConverter, XStream.PRIORITY_NORMAL);
        instance.registerConverter(JapaneseDateConverter, XStream.PRIORITY_NORMAL);
        instance.registerConverter(MinguoDateConverter, XStream.PRIORITY_NORMAL);
        instance.registerConverter(ThaiBuddhistDateConverter, XStream.PRIORITY_NORMAL);

        instance.registerConverter(OffsetDateTimeConverter, XStream.PRIORITY_NORMAL);
        instance.registerConverter(OffsetTimeConverter, XStream.PRIORITY_NORMAL);
        instance.registerConverter(YearConverter, XStream.PRIORITY_NORMAL);
        instance.registerConverter(YearMonthConverter, XStream.PRIORITY_NORMAL);
        instance.registerConverter(ZonedDateTimeConverter, XStream.PRIORITY_NORMAL);

        instance.registerConverter(DurationConverter, XStream.PRIORITY_NORMAL);
        instance.registerConverter(PeriodConverter, XStream.PRIORITY_NORMAL);
    }

    //typical types
    public static final Converter<Instant>          InstantConverter =          new Converter<>(Instant.class,          Instant::parse);
    public static final Converter<LocalTime>        LocalTimeConverter =        new Converter<>(LocalTime.class,        LocalTime::parse);
    public static final Converter<LocalDateTime>    LocalDateTimeConverter =    new Converter<>(LocalDateTime.class,    LocalDateTime::parse);
    public static final Converter<LocalDate>        LocalDateConverter =        new Converter<>(LocalDate.class,        LocalDate::parse);

    //location specific types
    public static final Converter<HijrahDate>       HijrahDateConverter =       new Converter<>(HijrahDate.class,       str -> HijrahDate.from(LocalDate.parse(str)));
    public static final Converter<JapaneseDate>     JapaneseDateConverter =     new Converter<>(JapaneseDate.class,     str -> JapaneseDate.from(LocalDate.parse(str)));
    public static final Converter<MinguoDate>       MinguoDateConverter =       new Converter<>(MinguoDate.class,       str -> MinguoDate.from(LocalDate.parse(str)));
    public static final Converter<ThaiBuddhistDate> ThaiBuddhistDateConverter = new Converter<>(ThaiBuddhistDate.class, str -> ThaiBuddhistDate.from(LocalDate.parse(str)));

    //misc
    public static final Converter<OffsetDateTime>   OffsetDateTimeConverter =   new Converter<>(OffsetDateTime.class,   OffsetDateTime::parse);
    public static final Converter<OffsetTime>       OffsetTimeConverter =       new Converter<>(OffsetTime.class,       OffsetTime::parse);
    public static final Converter<Year>             YearConverter =             new Converter<>(Year.class,             Year::parse);
    public static final Converter<YearMonth>        YearMonthConverter =        new Converter<>(YearMonth.class,        YearMonth::parse);
    public static final Converter<ZonedDateTime>    ZonedDateTimeConverter =    new Converter<>(ZonedDateTime.class,    ZonedDateTime::parse);

    //temporal-amount
    public static final Converter<Duration>         DurationConverter =         new Converter<>(Duration.class,         Duration::parse);
    public static final Converter<Period>           PeriodConverter   =         new Converter<>(Period.class,           Period::parse);

    public static class Converter<TTemporal> extends AbstractSingleValueConverter{

        private final Class<TTemporal> typeToConvert ;
        //since the java.time package only exists in java 8, I get to use lambdas! yay!
        private final Function<CharSequence, TTemporal> converter;

        protected Converter(Class<TTemporal> typeToConvert, ParserFunction<TTemporal> converter){
            this.typeToConvert = typeToConvert;
            this.converter = converter;
        }

        @Override
        public boolean canConvert(Class type) {
            return type != null && type.equals(typeToConvert);
        }

        @Override
        public Object fromString(String str) {
            return converter.apply(str);
        }

        @Override
        public String toString(Object obj) {
            return obj.toString();
        }
    }

    public static interface ParserFunction<TTemporal>
            extends Function<CharSequence, TTemporal>, Serializable {}
}
