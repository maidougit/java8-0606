package com.excel;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

import java.util.Date;

@Data
//@HeadRowHeight(20)//表头高度
//@ContentRowHeight(20)//内容的行高
public class Student {
    //和excel表中数据一一对应
//    @ExcelIgnore//该字段不参与读写
    private String id;

    //    @ExcelProperty("姓名")//列名
//    @ColumnWidth(20)//列宽
    private String name;
    //    @ExcelProperty("性别")
//    @ColumnWidth(20)
    private String gender;
    //    @ExcelProperty("出生日期")
//    @ColumnWidth(20)
//    @DateTimeFormat("yyyy-MM-dd")//指定日期格式
    private Date birthday;
}