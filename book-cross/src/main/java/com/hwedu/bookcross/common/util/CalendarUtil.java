package com.hwedu.bookcross.common.util;

import java.util.Calendar;

/**
 * Created by lizhiqiang on 2016/4/11.
 */
public final class CalendarUtil {

    public static final Calendar calendar = Calendar.getInstance();

    private CalendarUtil() {

    }

    /**
     * 取当前是周几，周日到周六=1到7
     *
     * @return
     */
    public static Integer dayOfWeek() {
        return calendar.get(Calendar.DAY_OF_WEEK);
    }
}
