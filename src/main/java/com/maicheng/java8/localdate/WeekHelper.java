package com.maicheng.java8.localdate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * 描述:周计算
 *
 * FileName: WeekHelper.java
 * @Author: maido
 * @date:    2018/9/23
 * @since v1.8.0
 */
public class WeekHelper {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static long SevenDay = 604800;

    /**
     * @param startDate
     * @param endDate
     * @return
     */
    public static List<Week> getWeekSplit(Date startDate, Date endDate) {
        ArrayList<Week> WeekList = new ArrayList<>();
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(startDate);
        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate);

        Calendar FistSaturday = Calendar.getInstance();
        FistSaturday.set(startCal.get(Calendar.YEAR), Calendar.JANUARY, 1, 0, 0, 0);
        while (FistSaturday.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
            FistSaturday.add(Calendar.DAY_OF_YEAR, 1);
        }

        while (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
            startCal.add(Calendar.DAY_OF_YEAR, 1);
        }
        while (endCal.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY) {
            endCal.add(Calendar.DAY_OF_YEAR, -1);
        }
        System.out.println(sdf.format(startCal.getTime()) + "\t" + sdf.format(endCal.getTime()));
        int startYear = startCal.get(Calendar.YEAR);
        while (startCal.compareTo(endCal) < 0) {
            int endYear = startCal.get(Calendar.YEAR);
            if (startYear < endYear) {
                FistSaturday.set(endYear, Calendar.JANUARY, 1, 0, 0, 0);
                while (FistSaturday.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
                    FistSaturday.add(Calendar.DAY_OF_YEAR, 1);
                }
                startYear = endYear;
            }
            Week everyWeek = new Week();
            long WeekNum = (startCal.getTimeInMillis() / 1000 - FistSaturday.getTimeInMillis() / 1000) / SevenDay + 1;
            String WeekNumStr = String.valueOf(WeekNum);
            if (WeekNum < 10) {
                WeekNumStr = "0" + WeekNum;
            }
            everyWeek.setYear(startCal.get(Calendar.YEAR));
            everyWeek.setWeekBegin(sdf.format(startCal.getTime()));
            startCal.add(Calendar.DATE, 6);
            everyWeek.setWeekEnd(sdf.format(startCal.getTime()));
            everyWeek.setWeekNum(WeekNumStr);
            startCal.add(Calendar.DATE, 1);
            WeekList.add(everyWeek);
        }
        Iterator<Week> iter = WeekList.iterator();
        System.out.println("开始打印");
        while (iter.hasNext()) {
            Week everyweek = iter.next();
            System.out.println(everyweek.getYear() + "年第" + everyweek.getWeekNum() + "周\t" + "开始时间：" + everyweek.getWeekBegin() + "\t结束时间" + everyweek.getWeekEnd());
        }
        return WeekList;
    }

    public static void main(String[] args) {
        String start = "2018-09-09";
        String end = "2018-09-24";
        try {
            Date startDate = sdf.parse(start);
            Date endDate = sdf.parse(end);
            getWeekSplit(startDate, endDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}