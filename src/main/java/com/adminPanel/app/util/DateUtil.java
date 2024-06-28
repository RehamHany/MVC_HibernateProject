package com.adminPanel.app.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil
{
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public static String dateToString(Date date)
    {
        return simpleDateFormat.format(date);
    }

    public static Date stringToDate(String stringDate) throws ParseException {
        return simpleDateFormat.parse(stringDate);
    }

    public static Date formatDate(Date date) throws ParseException {
        String dateStr =  simpleDateFormat.format(date);
        return stringToDate(dateStr);
    }
}
