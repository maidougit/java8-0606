package com.maicheng.java8.localdate;

/**
 * 描述:周
 *
 * FileName: Week.java
 * @Author: maido
 * @date:    2018/9/23
 * @since v1.8.0
 */
public class Week {
    String weekNum;
    int year;
    String weekBegin;
    String weekEnd;

    public String getWeekNum() {
        return weekNum;
    }

    public void setWeekNum(String weekNum) {
        this.weekNum = weekNum;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getWeekBegin() {
        return weekBegin;
    }

    public void setWeekBegin(String weekBegin) {
        this.weekBegin = weekBegin;
    }

    public String getWeekEnd() {
        return weekEnd;
    }

    public void setWeekEnd(String weekEnd) {
        this.weekEnd = weekEnd;
    }
}