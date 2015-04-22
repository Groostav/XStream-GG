package com.thoughtworks.xstream.converters.basic;

import java.time.*;
import java.time.chrono.HijrahDate;
import java.time.chrono.JapaneseDate;
import java.time.chrono.MinguoDate;
import java.time.chrono.ThaiBuddhistDate;
import java.util.function.Function;

public class JavaTime {

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

        protected Converter(Class<TTemporal> typeToConvert, Function<CharSequence, TTemporal> converter){
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
    }
}
