package com.maicheng.java8.localdate;

import cn.hutool.core.lang.Console;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Date;
import java.util.Locale;

/**
 * 描述:TODO
 *
 * FileName: DateAndLocalDateUtil.java
 * @Author: maido
 * @date:    2018/9/23
 * @since v1.8.0
 */
public class DateAndLocalDateUtil {

    /**
     * localDate转Date
     */
    public Date localDate2Date(LocalDate localDate){
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
        Instant instant1 = zonedDateTime.toInstant();
        Date from = Date.from(instant1);
        return  from;
    }

    /**
     * Date 转 localDate
     */
    public LocalDate date2LocalDate(Date date){
        Instant instant = date.toInstant();
        ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
        LocalDate localDate = zdt.toLocalDate();
        return localDate;
    }

    //获取月第一天
    public Date getStartDayOfMonth(String date) {
        LocalDate now = LocalDate.parse(date);
        return this.getStartDayOfMonth(now);
    }

    public Date getStartDayOfMonth(LocalDate date) {
        LocalDate now = date.with(TemporalAdjusters.firstDayOfMonth());
        return this.localDate2Date(now);
    }
    //获取月最后一天
    public Date getEndDayOfMonth(String date) {
        LocalDate localDate = LocalDate.parse(date);
        return this.getEndDayOfMonth(localDate);
    }

    public Date getEndDayOfMonth(LocalDate date) {
        LocalDate now = date.with(TemporalAdjusters.lastDayOfMonth());

        Date.from(now.atStartOfDay(ZoneId.systemDefault()).plusDays(1L).minusNanos(1L).toInstant());
        return this.localDate2Date(now);
    }

    //获取周第一天
    public Date getStartDayOfWeek(String date) {
        LocalDate now = LocalDate.parse(date);
        return this.getStartDayOfWeek(now);
    }

    public Date getStartDayOfWeek(TemporalAccessor date) {
        TemporalField fieldISO = WeekFields.of(Locale.CHINA).dayOfWeek();
        LocalDate localDate = LocalDate.from(date);
        localDate=localDate.with(fieldISO, 1);
        return this.localDate2Date(localDate);
    }
    //获取周最后一天
    public Date getEndDayOfWeek(String date) {
        LocalDate localDate = LocalDate.parse(date);
        return this.getEndDayOfWeek(localDate);
    }

    public Date getEndDayOfWeek(TemporalAccessor date) {
        TemporalField fieldISO = WeekFields.of(Locale.CHINA).dayOfWeek();
        LocalDate localDate = LocalDate.from(date);
        localDate=localDate.with(fieldISO, 7);
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).plusDays(1L).minusNanos(1L).toInstant());
    }
    //一天的开始
    public Date getStartOfDay(String date) {
        LocalDate localDate = LocalDate.parse(date);
        return this.getStartOfDay(localDate);
    }

    public Date getStartOfDay(TemporalAccessor date) {
        LocalDate localDate = LocalDate.from(date);
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
    //一天的结束
    public Date getEndOfDay(String date){
        LocalDate localDate = LocalDate.parse(date);
        return this.getEndOfDay(localDate);
    }
    public Date getEndOfDay(TemporalAccessor date) {
        LocalDate localDate = LocalDate.from(date);
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).plusDays(1L).minusNanos(1L).toInstant());
    }

    public static void main(String[] args) {
        DateAndLocalDateUtil date = new DateAndLocalDateUtil();
        Console.log("结果--->" + date.getEndDayOfMonth("2018-09-23"));
    }
}