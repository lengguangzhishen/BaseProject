package com.wumai.baselibrary.util;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MINUTE;

public final class TimeUtil {

    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static final String YMD_2 = "yyyy-MM-dd";

    public static final String YMD_4_CN = "yyyy年MM月dd日";

    public static final String YMD_H_M = "yyyy-MM-dd HH:mm";

    public static String format(String pattern, long time) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(new Date(time));
    }

    public static long parseTime(String pattern, String time) {

        return parseTime(new SimpleDateFormat(pattern), time);
    }

    public static long parseTime(SimpleDateFormat format, String time) {
        try {
            return format.parse(time)
                    .getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return System.currentTimeMillis();
        }
    }

    /**
     * 获取当前时间hours:min 形式的操作;
     *
     * @param millisTime
     * @return
     */
    public static String getHoursMin(long millisTime) {

        try {
            Date date = new Date(millisTime);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            String hour = calendar.get(HOUR_OF_DAY) + "";
            String min = calendar.get(MINUTE) + "";
            if (hour.length() == 1) {
                hour = "0" + hour;
            }
            if (min.length() == 1) {
                min = "0" + min;
            }
            return hour + ":" + min;
        } catch (Exception e) {
            e.printStackTrace();
            return "00:00";
        }
    }

    /**
     * 获取当前时间hours:min:ss 形式的操作;
     *
     * @param millisTime
     * @return
     */
    @SuppressLint("DefaultLocale")
    public static String getHoursMinSec(long millisTime) {
        try {
            return String.format("%tT", millisTime);
        } catch (Exception e) {
            e.printStackTrace();
            return "00:00:00";
        }
    }

}
