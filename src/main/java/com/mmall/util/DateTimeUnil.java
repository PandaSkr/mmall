package com.mmall.util;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

public class DateTimeUnil {
    public static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static Date str2Date(String dateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(STANDARD_FORMAT);
        DateTime dateTime1 = dateTimeFormatter.parseDateTime(dateTime);
        return dateTime1.toDate();
    }

    public static String date2Str(Date date) {
        if (date == null)
            return StringUtils.EMPTY;
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(STANDARD_FORMAT);
    }
}
