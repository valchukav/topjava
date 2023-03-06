package ru.javawebinar.topjava.web.converter;

import org.springframework.format.Formatter;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;

/**
 * @author Alexei Valchuk, 06.03.2023, email: a.valchukav@gmail.com
 */

public class DateTimeFormatters {

    public static class LocalDateFormatter implements Formatter<LocalDate> {

        @Override
        public LocalDate parse(@Nullable String text, @Nullable Locale locale) {
            return parseLocalDate(text);
        }

        @Override
        public String print(@Nullable LocalDate lt, @Nullable Locale locale) {
            return lt.format(DateTimeFormatter.ISO_LOCAL_DATE);
        }
    }

    public static class LocalTimeFormatter implements Formatter<LocalTime> {

        @Override
        public LocalTime parse(@Nullable String text, @Nullable Locale locale) {
            return parseLocalTime(text);
        }

        @Override
        public String print(@Nullable LocalTime lt, @Nullable Locale locale) {
            return lt.format(DateTimeFormatter.ISO_LOCAL_TIME);
        }
    }
}
