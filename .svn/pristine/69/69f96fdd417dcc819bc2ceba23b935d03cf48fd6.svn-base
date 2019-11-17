/**
 * @Title: DateFormatUtil.java
 * @Package com.warmdoctor.ows.common.util
 * @author bruce
 * @date 2015年4月27日 上午10:31:01
 * @version V1.0
 */
package com.kfwy.hkp.common.utils;

import com.gniuu.framework.logging.Logger;
import com.gniuu.framework.logging.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * 时间格式化
 *
 * @author bruce
 * @ClassName: DateFormatUtil
 * @date 2015年4月27日 上午10:31:01
 */
public class DateFormatUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateFormatUtil.class);

    public static final String DateTimeFormatString = "yyyy-MM-dd HH:mm:ss";

    public static final String DateTimeFormatDay = "yyyy-MM-dd";

    public static final String DateTimeFormatDayChina = "yyyy年MM月dd日";

    public static final String DateTimeFormatMonth = "yyyy-MM";

    public static final String ADD_TIME_FORMAT_YEAR = "yyyy";

    public static final String ADD_TIME_FORMAT_DAY = "DD";

    public static final String[] WEEK_DAYS_NAME = {"周天", "周一", "周二", "周三", "周四", "周五", "周六"};

    public static final Integer[] WEEK_DAYS_CODE = {0, 1, 2, 3, 4, 5, 6};

    public static SimpleDateFormat getDateFormat() {
        return new SimpleDateFormat(DateTimeFormatString);
    }

    public static Date getCurrentDateTime() {
        return new Date();
    }

    public static Date getCurrentDate(String time, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(time);
    }

    public static String getCurrentDate(String format) {
        return getFormatDateTime(new Date(), format);
    }

    public static String getCurrentDate() {
        return getCurrentDate(DateTimeFormatString);
    }

    public static String getFormatDateTime(Date date, String format) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static long getCurrentTime() {
        return System.currentTimeMillis();
    }

    public static int getTimeCost(Date reqTime, Date respTime) {
        return Long.valueOf(respTime.getTime() - reqTime.getTime()).intValue();
    }

    public static String addTimeFormat(int time, String format) {
        return getFormatDateTime(addDate(time), format);
    }

    public static Date addDate(int time) {
        Date d = getCurrentDateTime();
        return addDate(d, time);
    }

    public static int getDays(Date date){
        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);//把日期设置为当月第一天
        a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 获取中文星期
     *
     * @param date
     * @return
     */
    public static String getWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        w = w < 0 ? 0 : w;
        return WEEK_DAYS_NAME[w];
    }

    /**
     * 获取周一的时间
     *
     * @param endTime
     * @param format
     * @return
     */
    public static String getWeekOne(String endTime, String format) {
        int mondayPlus = getMondayPlus(endTime);
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(5, mondayPlus);
        Date monday = currentDate.getTime();
        return getFormatDateTime(monday, format);
    }

    /**
     * 获取周末的时间
     *
     * @param startTime
     * @param format
     * @return
     */
    public static String getWeekLast(String startTime, String format) {
        int mondayPlus = getMondayPlus(startTime);
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(5, mondayPlus + 6);
        Date monday = currentDate.getTime();
        return getFormatDateTime(monday, format);
    }

    private static int getMondayPlus(String time) {
        Calendar cd = Calendar.getInstance();
        if (!StringUtils.isEmpty(time)) {
            try {
                cd.setTime(getDateFormat().parse(time));
            } catch (Exception e) {

            }
        }
        int dayOfWeek = cd.get(7) - 1;
        if (dayOfWeek == 1) {
            return 0;
        }
        return (1 - dayOfWeek);
    }

    public static Date addDate(Date date, int time) {
        return addDate(date, time, "mm");
    }

    public static Date addDate(Date date, int time, String type) {
        if (!StringUtils.isEmpty(type)) { //增加毫秒
            Calendar calender = Calendar.getInstance();
            calender.setTime(date);
            if ("YY".equalsIgnoreCase(type)) {
                calender.add(Calendar.YEAR, time);
            } else if ("MM".equals (type)) {
                calender.add(Calendar.MONTH, time);
            } else if ("DD".equalsIgnoreCase(type)) {
                calender.add(Calendar.DATE, time);
            } else if ("HH".equalsIgnoreCase(type)) {
                calender.add(Calendar.HOUR, time);
            } else if ("mm".equals (type)){  // "mm".equals(type)
                calender.add(Calendar.MINUTE, time);
            }else{
                calender.add (Calendar.SECOND, time);
            }
            return calender.getTime();
        } else {
            return null;
        }
    }

    public static int getDaySpace(Date first, Date last) throws ParseException {
        String start = getFormatDateTime(first, DateTimeFormatDay);
        String end = getFormatDateTime(last, DateTimeFormatDay);
        return getDaySpace(start, end);
    }

    public static int getDaySpace(String first, String last) throws ParseException {
//        if (StringUtils.isEmpty(first) || StringUtils.isEmpty(last)) {
//            return 0;
//        }
//        Calendar calst = Calendar.getInstance();
//        Calendar caled = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DateTimeFormatDay);
//        calst.setTime();
//        caled.setTime(sdf.parse(last));
//        //得到两个日期相差的天数
//        int days = ((int) ((caled.getTime().getTime() / 1000) - (calst.getTime().getTime() / 1000))) / 3600 / 24;
//        return days;
        return getSpace(DateFormatUtil.getFormatDateTime(sdf.parse(first), DateTimeFormatString), DateFormatUtil.getFormatDateTime(sdf.parse(last), DateTimeFormatString), "dd");
    }

    public static int getSpace(String first, String last, String type) throws ParseException {
        return getSpace(first, last, DateFormatUtil.DateTimeFormatString, type);
    }

    public static int getSpace(String first, String last, String format, String type) throws ParseException {
        if (StringUtils.isEmpty(first) || StringUtils.isEmpty(last)) {
            return 0;
        }
        Calendar calst = Calendar.getInstance();
        Calendar caled = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        calst.setTime(sdf.parse(first));
        caled.setTime(sdf.parse(last));
        int n = 0;
        while (!calst.after(caled)) {                     // 循环对比，直到相等，n 就是所要的结果
            //list.add(df.format(c1.getTime()));    // 这里可以把间隔的日期存到数组中 打印出来
            n++;
            if ("yy".equals(type)) {
                calst.add(Calendar.YEAR, 1);
            } else if ("MM".equals(type)) {
                calst.add(Calendar.MONTH, 1);
            } else if ("dd".equals(type)) {
                calst.add(Calendar.DATE, 1);
            } else if ("HH".equals(type)) {
                calst.add(Calendar.HOUR, 1);
            } else {  // "mm".equals(type)
                calst.add(Calendar.MINUTE, 1);
            }
        }
        return n - 1;
    }

    /**
     * 判断时间是否在今天有效
     *
     * @param first
     * @param last
     * @return
     */
    public static final boolean isRange(Date first, Date last) {
        Date date = new Date();
        return date.after(first) && date.before(last);
    }

    /**
     * 是否是今天
     *
     * @param date
     * @return
     */
    public static boolean isToday(final Date date) {
        return isTheDay(date, DateFormatUtil.getCurrentDateTime());
    }

    /**
     * 是否是指定日期
     *
     * @param date
     * @param day
     * @return
     */
    public static boolean isTheDay(final Date date, final Date day) {
        return date.getTime() >= DateFormatUtil.dayBegin(day).getTime()
                && date.getTime() <= DateFormatUtil.dayEnd(day).getTime();
    }

    /**
     * 获取指定时间的那天 00:00:00.000 的时间
     *
     * @param date
     * @return
     */
    public static Date dayBegin(final Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 获取指定时间的那天 23:59:59.999 的时间
     *
     * @param date
     * @return
     */
    public static Date dayEnd(final Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);
        return c.getTime();
    }

    /**
     * 获取本月第一天和最后一天
     *
     * @param date
     * @return
     */
    public static Date monthBegin(final Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        return c.getTime();
    }

    /**
     * 获取本月第一天和最后一天
     *
     * @param date
     * @return
     */
    public static Date monthEnd(final Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        return c.getTime();
    }

    /**
     * 本月第一天的yyyy-mm-01 00:00:00
     * @param time
     * @return
     */
    public static Date monthBeginDay(Date time) {
        return dayBegin(monthBegin(time));
    }

    /**
     * 本月的最后一天yyyy-mm-(28/29/30/31) 23:59:59
     * @param time
     * @return
     */
    public static Date monthEndDay(Date time) {
        return dayEnd(monthEnd(time));
    }

    /**
     * 字符串装换成指定的起始日期
     * 00:00:00.000
     *
     * @param date
     * @return
     */
    public static Date dayBeginFormat(final String date) {
        if (StringUtils.isNotEmpty(date)) {
            try {
                return getCurrentDate(date + " 00:00:00", DateTimeFormatString);
            } catch (Exception e) {
            }
        }
        return null;
    }

    /**
     * 字符串装换成指定的结束日期
     * 23:59:59.999
     *
     * @param date
     * @return
     */
    public static Date dayEndFormat(final String date) {
        if (StringUtils.isNotEmpty(date)) {
            try {
                return getCurrentDate(date + " 23:59:59", DateTimeFormatString);
            } catch (Exception e) {
            }
        }
        return null;
    }

    /**
     * 获得系统当前时间小时
     *
     * @return
     */
    public static final int getSystemHour() {
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 毫秒转化
     */
    public static String formatTime(long ms) {

        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;
        int dd = hh * 24;

        long day = ms / dd;
        long hour = (ms - day * dd) / hh;
        long minute = (ms - day * dd - hour * hh) / mi;
        long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        String strDay = day < 10 ? "0" + day : "" + day; //天
        String strHour = hour < 10 ? "0" + hour : "" + hour;//小时
        String strMinute = minute < 10 ? "0" + minute : "" + minute;//分钟
        String strSecond = second < 10 ? "0" + second : "" + second;//秒
        String strMilliSecond = milliSecond < 10 ? "0" + milliSecond : "" + milliSecond;//毫秒
        strMilliSecond = milliSecond < 100 ? "0" + strMilliSecond : "" + strMilliSecond;

        StringBuffer sb = new StringBuffer();
        if (day > 0) {
            sb.append(day + "天");
        }
        if (hour > 0) {
            sb.append(hour + "时");
        } else if (day > 0) {
            sb.append("00时");
        }
        if (minute > 0) {
            sb.append(minute + "分");
        } else if (day > 0 || hour > 0) {
            sb.append("00分");
        }
        if (second > 0) {
            sb.append(second + "秒");
        } else if (day > 0 || hour > 0 || minute > 0) {
            sb.append("00秒");
        }
        if (milliSecond > 0) {
            sb.append(milliSecond + "毫秒");
        } else if (day > 0 || hour > 0 || second > 0) {
            sb.append("000毫秒");
        }

        return sb.toString();
    }

    //Sun Jan 18 20:11:37 CST 1970
    public static String dateToString(Date time, String format){
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat(format);
        String ctime = formatter.format(time);
        return ctime;
    }

    public static String dateToString(Date time){
//        SimpleDateFormat formatter;
//        formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
//        String ctime = formatter.format(time);
        return dateToString(time, DateTimeFormatString);
    }

    /**
     * 字符串装换成指定的起始日期
     * 00:00:00.000
     *
     * @param date
     * @return
     */
    public static Date dayBeginFormat2(final String date)  {
        if(StringUtils.isNotEmpty(date)) {
            SimpleDateFormat format = new SimpleDateFormat(DateTimeFormatDay);
            Date d = null;
            try {
                d = format.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String date2 = format.format(d);
            if (StringUtils.isNotEmpty(date2)) {
                try {
                    return getCurrentDate(date2 + " 00:00:00", DateTimeFormatString);
                } catch (Exception e) {
                }
            }
        }
        return null;
    }

    /**
     * 字符串装换成指定的结束日期
     * 23:59:59.999
     *
     * @param date
     * @return
     */
    public static Date dayEndFormat2(final String date){
        if(StringUtils.isNotEmpty(date)) {
            SimpleDateFormat format = new SimpleDateFormat(DateTimeFormatDay);
            Date d = null;
            try {
                d = format.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String date2 = format.format(d);
            if (StringUtils.isNotEmpty(date2)) {
                try {
                    return getCurrentDate(date2 + " 23:59:59", DateTimeFormatString);
                } catch (Exception e) {
                }
            }
        }
            return null;
    }

    public static String getTime(Date time){
        if(null!=time){
            SimpleDateFormat formatter;
            formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String ctime = formatter.format(time);
            return ctime;
        }else{
            return null;
        }
    }

    /**
     * 时间计算
     * @param option    pre为时间减天数  next为时间加天数
     * @param _date     指定需要计算的时间
     * @param day       需要减或加的天数
     * @return
     */
    public static String checkOption(String option, String _date, int day) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cl = Calendar.getInstance();
        Date date = null;

        try {
            date = (Date) sdf.parse(_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cl.setTime(date);
        if ("pre".equals(option)) {
            // 时间减一天
            cl.add(Calendar.DAY_OF_MONTH, -day);

        } else if ("next".equals(option)) {
            // 时间加一天
            cl.add(Calendar.DAY_OF_YEAR, day);
        } else {
            // do nothing
        }
        date = cl.getTime();
        return sdf.format(date);
    }



    public static long getDaySub(String beginDateStr, String endDateStr)
     {
                 long day=0;
                 java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
                java.util.Date beginDate;
                java.util.Date endDate;
                try
               {
                       beginDate = format.parse(beginDateStr);
                        endDate= format.parse(endDateStr);
                        day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
                        //System.out.println("相隔的天数="+day);
                     } catch (ParseException e)
                {
                       // TODO 自动生成 catch 块
                         e.printStackTrace();
                     }
                 return day;
     }

     public static Date  dateTransition(String t){
         SimpleDateFormat sf = new SimpleDateFormat("EEE MMM dd yyyy hh:mm:ss z", Locale.ENGLISH);
         Date d=null;
         try {
             d = sf.parse(t);
         } catch (ParseException e) {
             e.printStackTrace();
         }
         return d;
     }

    /**
     * 添加天数
     * @param date
     * @param addDay
     * @return
     */
     public static Date dayAdd(Date date,Integer addDay){
         Calendar c = Calendar.getInstance();
         c.setTime(date);
         c.add(Calendar.DAY_OF_MONTH, 3);// 今天+3天
         return c.getTime();
     }

    /**
     * 天转换为时间
     * @param day
     * @return
     */
     public static Date dayTransitionDate(Long day){
         Calendar cal = Calendar.getInstance();
         cal.setTime(new Date());
         int Num = day.intValue();
         int fuNum = 0 - Num;
         //时
         cal.set(Calendar.HOUR_OF_DAY, 00);
         //分
         cal.set(Calendar.MINUTE, 00);
         //秒
         cal.set(Calendar.SECOND, 00);
         cal.add(Calendar.DATE, fuNum);
         // 从一个 Calendar 对象中获取 Date 对象
         Date followupDate = cal.getTime();
         return followupDate;
     }

    public static void main(String[] args) throws ParseException {
        //System.out.print(dateToString(new Date("Sun Jan 18 20:11:37 CST 1970"), DateTimeFormatString));
          //System.out.print(checkOption("pre",dateToString(new Date()),30));
//        System.out.print(dateToString(new Date(), "yyyy年MM月dd日"));
}


}
