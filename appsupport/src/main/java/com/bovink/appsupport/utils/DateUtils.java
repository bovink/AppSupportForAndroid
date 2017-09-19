package com.bovink.appsupport.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author Retina975
 * @since 2017/07/24
 */

public class DateUtils {

    private DateUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static String getToday() {
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss", Locale.getDefault());
        return sdf.format(today);
    }

    /**
     * 将Date按照规则转换成字符串
     *
     * @param date
     * @return
     */
    public static String formatChatTime(Date date) {
        Calendar today = Calendar.getInstance();
        today.setTime(new Date());
        int todayYear = today.get(Calendar.YEAR);
        int todayMonth = today.get(Calendar.MONTH);
        int todayDay = today.get(Calendar.DAY_OF_MONTH);

        Calendar chatDate = Calendar.getInstance();
        chatDate.setTime(date);
        int chatYear = chatDate.get(Calendar.YEAR);
        int chatMonth = chatDate.get(Calendar.MONTH);
        int chatDay = chatDate.get(Calendar.DAY_OF_MONTH);
        int chatHour = chatDate.get(Calendar.HOUR_OF_DAY);

        String time = "";
        if (chatHour >= 0 && chatHour < 12) {
            time = "早上";
        } else if (chatHour >= 12 && chatHour < 18) {
            time = "下午";
        } else if (chatHour >= 18 && chatHour < 24) {
            time = "晚上";
        }


        String formatString;
        SimpleDateFormat sdf;
        // 不是同一年
        if (todayYear != chatYear) {

            sdf = new SimpleDateFormat("yyyy年MM月dd日 " + time + "HH:mm", Locale.getDefault());
            formatString = sdf.format(date);
            return formatString;
        }

        // 同一天
        if (todayMonth == chatMonth && todayDay == chatDay) {

            sdf = new SimpleDateFormat(time + "HH:mm", Locale.getDefault());
            formatString = sdf.format(date);
            return formatString;
        } else {// 同一年但不是同一天

            sdf = new SimpleDateFormat("MM月dd日 " + time + "HH:mm", Locale.getDefault());
            formatString = sdf.format(date);
            return formatString;
        }
    }

    /**
     * 联系时间
     *
     * @param date
     * @return
     */
    public static String formatContactTime(Date date) {
        Calendar today = Calendar.getInstance();
        today.setTime(new Date());
        int todayYear = today.get(Calendar.YEAR);
        int todayMonth = today.get(Calendar.MONTH);
        int todayDay = today.get(Calendar.DAY_OF_MONTH);

        Calendar chatDate = Calendar.getInstance();
        chatDate.setTime(date);
        int chatYear = chatDate.get(Calendar.YEAR);
        int chatMonth = chatDate.get(Calendar.MONTH);
        int chatDay = chatDate.get(Calendar.DAY_OF_MONTH);
        int chatHour = chatDate.get(Calendar.HOUR_OF_DAY);

        String time = "";
        if (chatHour >= 0 && chatHour < 12) {
            time = "早上";
        } else if (chatHour >= 12 && chatHour < 18) {
            time = "下午";
        } else if (chatHour >= 18 && chatHour < 24) {
            time = "晚上";
        }


        String formatString;
        SimpleDateFormat sdf;
        // 不是同一年
        if (todayYear != chatYear) {

            sdf = new SimpleDateFormat("yyyy年MM月dd日", Locale.getDefault());
            formatString = sdf.format(date);
            return formatString;
        }

        // 同一天
        if (todayMonth == chatMonth && todayDay == chatDay) {

            sdf = new SimpleDateFormat(time + "HH:mm", Locale.getDefault());
            formatString = sdf.format(date);
            return formatString;
        } else {// 同一年但不是同一天

            // 如果聊天的日期比今天晚一天
            if (todayMonth == chatMonth && todayDay - chatDay == 1) {
                return "昨天";
            }

            sdf = new SimpleDateFormat("MM月dd日", Locale.getDefault());
            formatString = sdf.format(date);
            return formatString;
        }
    }

    /**
     * @param date        当前决定是否要显示的时间
     * @param compareDate 上一个已经显示的对话的时间
     * @return
     */
    public static boolean showTime(Date date, Date compareDate) {
        Calendar calendar = Calendar.getInstance();

        // 今天的时间
        Date today = new Date();
        calendar.setTime(today);
        StringBuilder todaySb = new StringBuilder()
                .append(calendar.get(Calendar.YEAR))
                .append(calendar.get(Calendar.MONTH))
                .append(calendar.get(Calendar.DAY_OF_MONTH));

        // 当前对话的时间
        calendar.setTime(date);
        StringBuilder dateSb = new StringBuilder()
                .append(calendar.get(Calendar.YEAR))
                .append(calendar.get(Calendar.MONTH))
                .append(calendar.get(Calendar.DAY_OF_MONTH));

        // 已经显示对话的时间
        calendar.setTime(compareDate);
        StringBuilder compareDateSb = new StringBuilder()
                .append(calendar.get(Calendar.YEAR))
                .append(calendar.get(Calendar.MONTH))
                .append(calendar.get(Calendar.DAY_OF_MONTH));

        // 当前对话的时间是今天
        if (dateSb.toString().equals(todaySb.toString())) {

            // 之前显示的对话也是今天
            if (dateSb.toString().equals(compareDateSb.toString())) {

                // 如果当前的时间超过1分钟，则显示
                long oneMinute = 60000;
                return date.getTime() - compareDate.getTime() > oneMinute;
            }

            // 之前显示的对话不是今天，则按规则显示当前的对话
            return true;

        } else {

            // 如果是同一天
            if (dateSb.toString().equals(compareDateSb.toString())) {

                // 如果当前的时间超过1分钟，则显示
                long oneMinute = 60000;
                return date.getTime() - compareDate.getTime() > oneMinute;
            } else {

                return true;
            }
        }
    }

    /**
     * 将字符串按SimpleDateFormat的规则转换成Date对象
     *
     * @param date
     * @param sdf
     * @return
     */
    public static Date toDate(String date, SimpleDateFormat sdf) {
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
