package com.excel.easyexcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.excel.Student;

public class StudentListener extends AnalysisEventListener<Student> {
    /*
    * 每读取一行数据会调用的内容
    *
    * */

    @Override
    public void invoke(Student student, AnalysisContext analysisContext) {
        System.out.println("student="+student);
    }
    /*
    * 读取完整个文档后会调用的内容
    *
    * */

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}