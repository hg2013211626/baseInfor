package com.magnificent.lang;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeTypes {

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String ISO_8601_DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    public static final String ISO_8601_DATETIME_MILLISECOND_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    public static final String ISO_8601_DATETIME_MILLISECOND_FORMAT_2 = "yyyy-MM-dd'T'HH:mm:ss.SSS";

    public static final String ISO_8601_DATETIME_MILLISECOND_FORMAT_3 = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    public static final String DEFAULT_DATETIME_DASH_FORMAT = "yyyy_MM_dd_HH_mm_ss";

    public static Date toDate(Object input) {
        if (null == input) {
            return null;
        } else if (input instanceof Date) {
            return (Date) input;
        } else if (input instanceof String) {
            if (StringUtils.isBlank((String) input)) {
                return null;
            }
            try {
                return DateUtils.parseDate((String) input,
                        DEFAULT_DATE_FORMAT,
                        DEFAULT_DATETIME_FORMAT,
                        ISO_8601_DATETIME_FORMAT,
                        ISO_8601_DATETIME_MILLISECOND_FORMAT,
                        ISO_8601_DATETIME_MILLISECOND_FORMAT_2,
                        ISO_8601_DATETIME_MILLISECOND_FORMAT_3);
            } catch (ParseException e) {
                // todo
            }
        } else if (input instanceof Integer) {
            return new Date(((Integer) input).longValue());
        } else if (input instanceof Long) {
            return new Date((Long) input);
        }
        return null;
    }

    public static String toDateString(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static String toDateString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        return sdf.format(date);
    }

    public static String toDateTimeString(Object date) {
        if (date instanceof Date) {
            SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT);
            return sdf.format(date);
        } else if (date instanceof Long) {
            Timestamp ts = new Timestamp((Long) date);
            SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT);
            return sdf.format(new Date(ts.getTime()));
        } else {
            return date.toString();
        }
    }

    public static String toDateTimeDashString(Date date) {
        return toDateString(date, DEFAULT_DATETIME_DASH_FORMAT);
    }


}
