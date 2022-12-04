package laptrinhandroid.fpoly.dnnhm3.XuLiNgay;

import android.util.Log;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.sql.Date;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FormatDay {
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-d");

    public static Date getCurrentDateSql() {
        java.util.Date date = new java.util.Date(System.currentTimeMillis());
        return new Date(date.getTime());
    }

    public static java.util.Date getCurrentDateUtil() {
        java.util.Date date = new java.util.Date(System.currentTimeMillis());
        return date;
    }

    public static long getBatDauLam() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new java.util.Date(System.currentTimeMillis()));
        calendar.set(Calendar.HOUR_OF_DAY,7);
        calendar.set(Calendar.MINUTE, 30);

        return calendar.getTime().getTime();
    }

    public static long getKetThucHLam() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new java.util.Date(System.currentTimeMillis()));
        calendar.set(Calendar.MINUTE, 30);
        calendar.set(Calendar.HOUR_OF_DAY,17);
        return calendar.getTime().getTime();
    }

    public static Calendar convertTimeToCalender(Time time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        return calendar;
    }

    public boolean check(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek == Calendar.SUNDAY;
    }

    public static CalendarDay calendarDay() {
        Calendar cal = Calendar.getInstance();
        return CalendarDay.from(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH));
    }

    public static CalendarDay calendarDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return CalendarDay.from(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH));
    }

    public static CalendarDay calendarDay(int year, int month, int day) {
        return CalendarDay.from(year, month, day);
    }

    public static Date convertToDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day);
        Log.d("ssss", "convertToDate: " + new Date(cal.getTime().getTime()));
        return new Date(cal.getTime().getTime());
    }
}
