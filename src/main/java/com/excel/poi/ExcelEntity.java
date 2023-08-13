package com.excel.poi;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * excel基础类
 */
@Data
public class ExcelEntity {

    /** 标题 */
    private String title;
    /** 排序 */
    private String sort;
    /** 属性名称 */
    private String fieldName;
    /** 参数类型 */
    private Class classType;

    private static String getStr = "get";

    private static String setStr = "set";

    public ExcelEntity(OutputExcel excel, String fieldName){
        this.fieldName = getStr + StringUtils.upperCase(fieldName);
        this.title = StringUtils.isBlank(excel.title())?"":excel.title();
        this.sort = excel.sort()+"";
    }

    public ExcelEntity(ImportExcel excel, String fieldName, Class classType){
        this.fieldName = setStr + StringUtils.upperCase(fieldName);
        this.classType = classType;
        this.sort = excel.value()+"";
    }
}