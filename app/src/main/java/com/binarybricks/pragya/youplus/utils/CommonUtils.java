package com.binarybricks.pragya.youplus.utils;

import android.content.Context;

import com.binarybricks.pragya.youplus.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * All util methods are defined here
 * Created by PRAGYA on 8/2/2016.
 */
public class CommonUtils {

    public static final String MM_DD_YYYY = "MM/dd/yyyy";
    public static final String HH_MM_A = "hh:mm a";

    //helper method to display previous date as yesterday
    public static String formatToYesterdayOrToday(Context context,String date) throws ParseException {
        Date dateTime = new SimpleDateFormat(MM_DD_YYYY).parse(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateTime);
        Calendar today = Calendar.getInstance();
        Calendar yesterday = Calendar.getInstance();
        yesterday.add(Calendar.DATE, -1);
        DateFormat timeFormatter = new SimpleDateFormat(HH_MM_A);

        if (calendar.get(Calendar.YEAR) == today.get(Calendar.YEAR) && calendar.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR)) {
            return timeFormatter.format(dateTime);
        } else if (calendar.get(Calendar.YEAR) == yesterday.get(Calendar.YEAR) && calendar.get(Calendar.DAY_OF_YEAR) == yesterday.get(Calendar.DAY_OF_YEAR)) {
            return context.getString(R.string.yesterday);
        } else {
            return date;
        }
    }
}
