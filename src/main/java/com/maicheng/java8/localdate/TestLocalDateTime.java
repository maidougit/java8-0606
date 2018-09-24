package com.maicheng.java8.localdate;

import cn.hutool.core.lang.Console;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Copyright (C), 2017-2018, maicheng
 * FileName: TestLocalDateTime.java
 * Author:   maido
 * Date:     2018/7/24 7:10
 * Description:
 */
public class TestLocalDateTime {

    @Test
    public void test1() {
        LocalDateTime localDateTime = LocalDateTime.now();

        System.out.println("当前时间：" + localDateTime);

        LocalDateTime localDateTime1 = LocalDateTime.of(2106, 2, 11, 12,12,12);
        System.out.println(localDateTime1);

        LocalDateTime localDateTime2 = localDateTime1.plusYears(2);

        System.out.println(localDateTime2);
    }

    // Clock 提供对现在时间和日期的访问。Clocks 能感知时区，可能被用来替代System.currentTimeMillis() 来获取现在毫秒数
    //这样一个在时间线上即时的点也被类Instant所表示。Instant可以被用来创建java.util.Date 对象。
    @Test
    public void test2() {
        Clock clock = Clock.systemDefaultZone();
        long millis = clock.millis();
        Console.log("结果：{}", millis);

        Instant instant = clock.instant();
        Date legacyDate = Date.from(instant);

        Console.log("当前时间：{}", legacyDate);
    }

    //Timezones由一个ZoneId代表。他们可以被静态工厂方法很容易地访问。 Timezones 定义偏移量，这对于instants 和本地日期、时间之间的转换非常重要
    //LocalTime 代表一个不带时区的时间，例如：10pm 或者 17:30:15。如下示例为如上定义的时区创建两个本地时间 。那么，我们就可以比较两个时间，
    // 并且计算出两个时间间的时间差（小时或者分钟为单位
    @Test
    public void testLocalTime(){
        ZoneId zone1 = ZoneId.of("Europe/Berlin");
        ZoneId zone2 = ZoneId.of("Brazil/East");
        Console.log("zone1:{}", zone1.getRules());
        Console.log("zone2:{}",zone2.getRules());

        LocalTime now1 = LocalTime.now(zone1);
        LocalTime now2 = LocalTime.now(zone2);

        Console.log("比较结果：{}", now1.isBefore(now2));

        LocalTime late = LocalTime.of(23, 59, 59);

        Console.log("创建时间：{}", late);

        DateTimeFormatter germanFormatter =
                DateTimeFormatter
                        .ofLocalizedTime(FormatStyle.SHORT)
                        .withLocale(Locale.GERMAN);

        LocalTime leetTime = LocalTime.parse("13:37", germanFormatter);
        Console.log("结果", leetTime);   // 13:37
    }

    @Test
    public void  testLocalDate() {
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
        LocalDate yesterday = tomorrow.minusDays(2);

        Console.log("时间：{}", yesterday);
        LocalDate independenceDay = LocalDate.of(2014, Month.JULY, 4);
        DayOfWeek dayOfWeek = independenceDay.getDayOfWeek();
        Console.log(dayOfWeek);

        DateTimeFormatter germanFormatter = DateTimeFormatter
                        .ofLocalizedDate(FormatStyle.MEDIUM)
                        .withLocale(Locale.GERMAN);

        LocalDate xmas = LocalDate.parse("24.12.2014", germanFormatter);

        Console.log("时间：{}", xmas);   // 2014-12-24
    }

    @Test
    public void testDateTimeFormatter() {
        DateTimeFormatter formatter = DateTimeFormatter
                        .ofPattern("MM dd, yyyy - HH:mm");

        LocalDateTime parsed = LocalDateTime.parse("07 03, 2014 - 07:13", formatter);
        String string = formatter.format(parsed);

        Console.log("结果：{}", string);     // Nov 03, 2014 - 07:13
    }

    @Test
    public void testJsSj() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();

        c.setTime(new Date());
        c.add(Calendar.DATE, - 7);
        Date d = c.getTime();
        String day = format.format(d);
        System.out.println("过去七天："+day);
    }


    @Test
    public void getWeekStartTime() throws Exception{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "yyyyMMdd", Locale. getDefault());
        Calendar cal = Calendar.getInstance();
        int day_of_week = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0 ) {
            day_of_week = 7 ;
        }
        cal.add(Calendar.DATE , -day_of_week + 1 );
        System.out.println("---->" + simpleDateFormat.format(cal.getTime()) + "000000000");
    }

    @Test
    public void getWeekFirst() {

        LocalDate inputDate = LocalDate.parse("2018-09-12");
        TemporalAdjuster FIRST_OF_WEEK = TemporalAdjusters.ofDateAdjuster(
                localDate -> localDate.minusDays(localDate.getDayOfWeek().getValue()-DayOfWeek.MONDAY.getValue()));
        System.out.println(inputDate.with(FIRST_OF_WEEK));
        TemporalAdjuster LAST_OF_WEEK = TemporalAdjusters.ofDateAdjuster(
                localDate -> localDate.plusDays(DayOfWeek.SUNDAY.getValue() - localDate.getDayOfWeek().getValue()));
        System.out.println(inputDate.with(LAST_OF_WEEK));
    }

    @Test
    public void getMonthFirst() {

        LocalDate inputDate = LocalDate.parse("2018-09-12");
        TemporalAdjuster FIRST_OF_WEEK = TemporalAdjusters.ofDateAdjuster(
                localDate -> localDate.minusMonths(localDate.getDayOfMonth()-DayOfWeek.MONDAY.getValue()));
        System.out.println(inputDate.with(FIRST_OF_WEEK));
        TemporalAdjuster LAST_OF_WEEK = TemporalAdjusters.ofDateAdjuster(
                localDate -> localDate.plusMonths(DayOfWeek.SUNDAY.getValue() - localDate.getDayOfWeek().getValue()));
        System.out.println(inputDate.with(LAST_OF_WEEK));
    }

    @Test
    public void getWeekByDay() {
        LocalDate today = LocalDate.parse("2018-09-12");
        //本月的第一天
        LocalDate firstday = LocalDate.of(today.getYear(),today.getMonth().minus(1),1);
        //本月的最后一天
        LocalDate lastDay =today.minusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("本月的第一天"+firstday);
        System.out.println("本月的最后一天"+lastDay);
    }

    @Test
    public void getWeek() {
        String today = "2013-01-14";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try{
           date = format.parse(today);
        } catch (ParseException e){
           e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        System.out.println(calendar.get(Calendar.WEEK_OF_YEAR));
    }

    public void getDay() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2016); // 2016年
        cal.set(Calendar.WEEK_OF_YEAR, 10); // 设置为2016年的第10周
        cal.set(Calendar.DAY_OF_WEEK, 2); // 1表示周日，2表示周一，7表示周六
        Date date = cal.getTime();
    }

 /*   public void getEndDay() {

        LocalDate now = LocalDate.now();
        LocalDate lastDayOfPreviousMonth = now.minusMonths(1).dayOfMonth().withMaximumValue();
    }*/

}