package com.library.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static String getStringFromDate(String format, Date date) {
        return new SimpleDateFormat(format).format(date);
    }

    public static Date getDateFromString(String format, String date) throws ParseException {
        return new SimpleDateFormat(format).parse(date);
    }
}
