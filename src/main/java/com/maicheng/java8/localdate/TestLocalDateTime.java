package com.maicheng.java8.localdate;

import cn.hutool.core.lang.Console;
import org.junit.Test;

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
}