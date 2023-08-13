package com.excel.poi;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.enums.WriteDirectionEnum;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.excel.FillData;
import com.excel.Student;
import com.excel.easyexcel.StudentListener;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ExceldemoApplicationTests {

    @Test
    public void contextLoads() {
    }
    @Test
    public void test01(){
//        获取工作簿对象
        ExcelReaderBuilder readWorkBook = EasyExcel.read("src/ExcelPattern/easyExcel.xlsx", Student.class, new StudentListener());
//        获取工作表对象
        ExcelReaderSheetBuilder sheet = readWorkBook.sheet();
//        读取表中的内容
        sheet.doRead();

    }
    @Test
    public void test02(){
        ExcelWriterBuilder writeWorkBook = EasyExcel.write("src/ExcelPattern/easyExcel.xlsx", Student.class);
        ExcelWriterSheetBuilder sheet = writeWorkBook.sheet();
        sheet.doWrite(initData());

    }

    //    数据生成
    private static List<Student> initData(){
        List<Student> students=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Student data=new Student();
            data.setName("测试"+i+2);
            data.setGender("男");
            data.setBirthday(new Date());
            students.add(data);
        }
        System.out.println(students);
        return students;
    }
    @Test
    public void test03(){
        String template="src/ExcelPattern/fill_data_template1.xlsx";
        /*
        * pathName 是写出路径
        * */
        ExcelWriterBuilder excelWriterBuilder = EasyExcel.write("Excel填充单组数据.xlsx", FillData.class).withTemplate(template);
        ExcelWriterSheetBuilder sheet = excelWriterBuilder.sheet();
        FillData fillData = new FillData();
        fillData.setName("abc");
        fillData.setAge(10);
        sheet.doFill(fillData);

    }

//    test04数据准备
        private static List<FillData> initFillData() {
            ArrayList<FillData> fillDatas = new ArrayList<FillData>();
            for (int i = 0; i < 10; i++) {
                FillData fillData = new FillData();
                fillData.setName("杭州黑马0" + i);
                fillData.setAge(10 + i);
                fillDatas.add(fillData);
            }
            return fillDatas;
        }
    @Test
    public void test04(){
        List<FillData> fillDatas=initFillData();

        String template="src/ExcelPattern/fill_data_template2.xlsx";
        /*
         * pathName 是写出路径
         * */
        ExcelWriterBuilder excelWriterBuilder = EasyExcel.write("Excel填充多组数据.xlsx", FillData.class).withTemplate(template);
        ExcelWriterSheetBuilder sheet = excelWriterBuilder.sheet();
        sheet.doFill(fillDatas);

    }
// 组合填充
    @Test
    public void test05(){
        List<FillData> fillDatas=initFillData();

        String template="src/ExcelPattern/fill_data_template3.xlsx";
        /*
         * pathName 是写出路径
         * */
        ExcelWriter workBook = EasyExcel.write("Excel填充组合数据.xlsx", FillData.class).withTemplate(template).build();
        WriteSheet sheet = EasyExcel.writerSheet().build();
//        换行-防止覆盖
        FillConfig fillConfig = FillConfig.builder().forceNewRow(true).build();

        HashMap<String, String> otherData = new HashMap<>();
        otherData.put("date", "2020-03-14");
        otherData.put("total", "100");

        workBook.fill(fillDatas,fillConfig,sheet);
        workBook.fill(otherData,sheet);

        workBook.finish();


    }

    @Test
    public void test06(){
        List<FillData> fillDatas=initFillData();

        String template="src/ExcelPattern/fill_data_template4.xlsx";
        /*
         * pathName 是写出路径
         * */
        ExcelWriter workBook = EasyExcel.write("Excel填充水平数据.xlsx", FillData.class).withTemplate(template).build();
        WriteSheet sheet = EasyExcel.writerSheet().build();
//        水平填充
        FillConfig fillConfig = FillConfig.builder().direction(WriteDirectionEnum.HORIZONTAL).build();
        workBook.fill(fillDatas,fillConfig,sheet);
        workBook.finish();
    }

    @Test
    public void test07(){


        String template="src/ExcelPattern/report_template.xlsx";
        /*
         * pathName 是写出路径
         * */
        ExcelWriter workBook = EasyExcel.write("Excel报表数据填充.xlsx", FillData.class).withTemplate(template).build();
        WriteSheet sheet = EasyExcel.writerSheet().build();
        // ****** 准备数据 *******
        // 日期
        HashMap<String, String> dateMap = new HashMap<String, String>();
        dateMap.put("date", "2020-03-16");

        // 总会员数
        HashMap<String, String> totalCountMap = new HashMap<String, String>();
        dateMap.put("totalCount", "1000");

        // 新增员数
        HashMap<String, String> increaseCountMap = new HashMap<String, String>();
        dateMap.put("increaseCount", "100");

        // 本周新增会员数
        HashMap<String, String> increaseCountWeekMap = new HashMap<String, String>();
        dateMap.put("increaseCountWeek", "50");

        // 本月新增会员数
        HashMap<String, String> increaseCountMonthMap = new HashMap<String, String>();
        dateMap.put("increaseCountMonth", "100");
        // 新增会员数据
        List<Student> students = initData();
        // **** 准备数据结束****

        // 写入统计数据
        workBook.fill(dateMap, sheet);
        workBook.fill(totalCountMap, sheet);
        workBook.fill(increaseCountMap, sheet);
        workBook.fill(increaseCountWeekMap, sheet);
        workBook.fill(increaseCountMonthMap, sheet);
        // 写入新增会员

        workBook.fill(students,sheet);
        workBook.finish();
    }





}
