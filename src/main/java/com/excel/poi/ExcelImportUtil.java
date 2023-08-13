//package com.excel.poi;
//
//import cn.hutool.core.date.DateUtil;
//import cn.hutool.core.lang.Console;
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFDataFormat;
//import org.apache.poi.hssf.usermodel.HSSFDateUtil;
//import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.poifs.filesystem.POIFSFileSystem;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellValue;
//import org.apache.poi.ss.usermodel.FormulaEvaluator;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.*;
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//import java.math.BigDecimal;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
///**
// * excel导入工具类
// */
//public class ExcelImportUtil {
//
//    private static Logger logger = LoggerFactory.getLogger(ExcelImportUtil.class);
//
//    private final static String xls = "xls";
//    private final static String xlsx = "xlsx";
//
//    private static FormulaEvaluator evaluator;
//
//    /**
//     * excel数据集合
//     */
//    private List list = null;
//
//    public ExcelImportUtil(MultipartFile file, int rowIndex, Class c) {
//        try {
//            POIFSFileSystem fs = new POIFSFileSystem(file.getInputStream());    //写入流
//            HSSFWorkbook workbook = new HSSFWorkbook(fs);   //创建workbook
//            HSSFSheet sheet = workbook.getSheetAt(0);   //获取sheet页
//            int rowNum = sheet.getPhysicalNumberOfRows();   //获取总行数
//            HSSFRow row = null;
//            HSSFCell cell = null;
//            List<ExcelEntity> entitys = getExcelEntitys(c); //获取类注解信息
//            this.list = new ArrayList();
//            for (int i = rowIndex; i < rowNum; i++) {
//                row = sheet.getRow(i);  //获取行
//                Object objCla = c.newInstance();    //实例化对象
//                for (ExcelEntity entity : entitys) {
//                    cell = row.getCell(Integer.valueOf(entity.getSort()));  //根据注释下标获得对应的单元格
//                    Method method = c.getMethod(entity.getFieldName(), entity.getClassType()); //获取属性的set方法
//                    Console.log(cell.getCellTypeEnum());
//                    //获取属性set方法的返回值
//                    Object invoke = null;
//                    if ("STRING".equals(cell.getCellTypeEnum().toString())) {
//                         invoke = method.invoke(objCla, cell.getStringCellValue());
//                    } else if ("NUMERIC".equals(cell.getCellTypeEnum().toString())) {
//                         invoke = method.invoke(objCla, cell.getNumericCellValue() + "");
//                    } else if ("BOOLEAN".equals(cell.getCellTypeEnum().toString())) {
//                         invoke = method.invoke(objCla, cell.getBooleanCellValue() + "");
//                    } else if ("DATE".equals(cell.getCellTypeEnum().toString())) {
//                         invoke = method.invoke(objCla, DateUtil.format(cell.getDateCellValue(), "yyyy-MM-dd HH:mm:ss"));
//                    }
//                }
//                this.list.add(objCla);   //将excel获取的数据添加到集合当中。
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public ExcelImportUtil(MultipartFile file, Class c) {
//
//        try {
//            Workbook workbook = getWorkBook(file);
//            List<ExcelEntity> entitys = getExcelEntitys(c); //获取类注解信息
//            this.list = new ArrayList();
//            Cell cell = null;
//            if (workbook != null) {
//                Sheet sheet = workbook.getSheetAt(0);
//                int firstRowNum = sheet.getFirstRowNum();
//                int lastRowNum = sheet.getLastRowNum();
//                for (int rowNum = firstRowNum + 1; rowNum <= lastRowNum; rowNum++) {
//                    Row row = sheet.getRow(rowNum);
//                    Object objCla = c.newInstance();    //实例化对象
//                    for (ExcelEntity entity : entitys) {
//                        cell = row.getCell(Integer.valueOf(entity.getSort()));  //根据注释下标获得对应的单元格
//                        Method method = c.getMethod(entity.getFieldName(), entity.getClassType()); //获取属性的set方法
//                        //System.out.println(cell.getCellTypeEnum());
//                        //获取属性set方法的返回值
//
//                        Object invoke = method.invoke(objCla, getCellVal(cell));
//                    }
//
//                    this.list.add(objCla);
//                }
//            }
//        } catch (Exception e) {
//            logger.error("异常信息:{}", e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 获取类注解信息
//     */
//    private List<ExcelEntity> getExcelEntitys(Class c) {
//        List<ExcelEntity> list = new ArrayList<>();
//        Field[] fields = c.getDeclaredFields();
//        for (Field field : fields) {
//            if (field.isAnnotationPresent(ImportExcel.class)) {
//                ImportExcel importExcel = field.getAnnotation(ImportExcel.class);
//                list.add(new ExcelEntity(importExcel, field.getName(), field.getType()));
//            }
//        }
//        return list;
//    }
//
//    public List getList() {
//        return list;
//    }
//
//    public void setList(List list) {
//        this.list = list;
//    }
//
//    public static List<String[]> readExcel(MultipartFile file) throws IOException {
//        //检查文件
//        checkFile(file);
//        //获得Workbook工作薄对象
//        Workbook workbook = getWorkBook(file);
//        //创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回
//        List<String[]> list = new ArrayList<String[]>();
//        if (workbook != null) {
//            for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
//                //获得当前sheet工作表
//                Sheet sheet = workbook.getSheetAt(sheetNum);
//                if (sheet == null) {
//                    continue;
//                }
//                //获得当前sheet的开始行
//                int firstRowNum = sheet.getFirstRowNum();
//                //获得当前sheet的结束行
//                int lastRowNum = sheet.getLastRowNum();
//                //循环除了第一行的所有行
//                for (int rowNum = firstRowNum + 1; rowNum <= lastRowNum; rowNum++) {
//                    //获得当前行
//                    Row row = sheet.getRow(rowNum);
//                    if (row == null) {
//                        continue;
//                    }
//                    //获得当前行的开始列
//                    int firstCellNum = row.getFirstCellNum();
//                    //获得当前行的列数
//                    int lastCellNum = row.getPhysicalNumberOfCells();
//                    String[] cells = new String[row.getPhysicalNumberOfCells()];
//                    //循环当前行
//                    for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
//                        Cell cell = row.getCell(cellNum);
//                        cells[cellNum] = getCellValue(cell);
//                    }
//                    list.add(cells);
//                }
//            }
//            workbook.close();
//        }
//        return list;
//    }
//
//    public static void checkFile(MultipartFile file) throws IOException {
//        //判断文件是否存在
//        if (null == file) {
//            logger.error("文件不存在！");
//            throw new FileNotFoundException("文件不存在！");
//        }
//        //获得文件名
//        String fileName = file.getOriginalFilename();
//        //判断文件是否是excel文件
//        if (!fileName.endsWith(xls) && !fileName.endsWith(xlsx)) {
//            logger.error(fileName + "不是excel文件");
//            throw new IOException(fileName + "不是excel文件");
//        }
//    }
//
//    public static Workbook getWorkBook(MultipartFile file) {
//        //获得文件名
//        String fileName = file.getOriginalFilename();
//        //创建Workbook工作薄对象，表示整个excel
//        Workbook workbook = null;
//        FormulaEvaluator formulaEvaluator = null;
//
//        try (InputStream is = file.getInputStream()) {
//            //获取excel文件的io流
//            //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
//            if (fileName.endsWith(xls)) {
//                //2003
//                workbook = new HSSFWorkbook(is);
//                formulaEvaluator = new HSSFFormulaEvaluator((HSSFWorkbook) workbook);
//            } else if (fileName.endsWith(xlsx)) {
//                //2007
//                workbook = new XSSFWorkbook(is);
//                formulaEvaluator = new XSSFFormulaEvaluator((XSSFWorkbook) workbook);
//            }
//        } catch (IOException e) {
//            logger.info(e.getMessage());
//        }
//
//        return workbook;
//    }
//
//    public static Workbook getWorkBook(File file) {
//        //获得文件名
//        String fileName = file.getName();
//        //创建Workbook工作薄对象，表示整个excel
//        Workbook workbook = null;
//        FormulaEvaluator formulaEvaluator = null;
//
//        try (InputStream is = new FileInputStream(file);) {
//            //获取excel文件的io流
//
//            //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
//            if (fileName.endsWith(xls)) {
//                //2003
//                workbook = new HSSFWorkbook(is);
//                formulaEvaluator = new HSSFFormulaEvaluator((HSSFWorkbook) workbook);
//            } else if (fileName.endsWith(xlsx)) {
//                //2007
//                workbook = new XSSFWorkbook(is);
//                formulaEvaluator = new XSSFFormulaEvaluator((XSSFWorkbook) workbook);
//            }
//        } catch (IOException e) {
//            logger.info(e.getMessage());
//        }
//
//        return workbook;
//    }
//
//
//    public static String getCellValue(Cell cell) {
//        String cellValue = "";
//        if (cell == null) {
//            return cellValue;
//        }
//        //把数字当成String来读，避免出现1读成1.0的情况
//        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
//            cell.setCellType(Cell.CELL_TYPE_STRING);
//        }
//        //判断数据的类型
//        switch (cell.getCellType()) {
//            case Cell.CELL_TYPE_NUMERIC: //数字
//                if (HSSFDateUtil.isCellDateFormatted(cell)) {
//                    Date date = cell.getDateCellValue();
//                    DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
//                    cellValue = formater.format(date);
//                } else {
//                    cellValue = String.valueOf(cell.getNumericCellValue());
//                }
//                break;
//            case Cell.CELL_TYPE_STRING: //字符串
//                cellValue = String.valueOf(cell.getStringCellValue());
//                break;
//            case Cell.CELL_TYPE_BOOLEAN: //Boolean
//                cellValue = String.valueOf(cell.getBooleanCellValue());
//                break;
//            case Cell.CELL_TYPE_FORMULA: //公式
//                cellValue = String.valueOf(cell.getCellFormula());
//                break;
//            case Cell.CELL_TYPE_BLANK: //空值
//                cellValue = "";
//                break;
//            case Cell.CELL_TYPE_ERROR: //故障
//                cellValue = "非法字符";
//                break;
//            default:
//                cellValue = "未知类型";
//                break;
//        }
//        return cellValue;
//    }
//
//    public static String getCellVal(Cell cell) {
//
//        String cellValue = "";
//        if (cell == null) {
//            return cellValue;
//        }
//
//        //判断数据的类型
//        switch (cell.getCellTypeEnum().toString()) {
//            case "NUMERIC": //数字
//                if (HSSFDateUtil.isCellDateFormatted(cell)) {
//                    SimpleDateFormat sdf = null;
//                    // 处理时分秒问题
//                    if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
//                        sdf = new SimpleDateFormat("HH:mm");
//                    } else {// 日期
//                        sdf = new SimpleDateFormat("yyyy-MM-dd");
//                    }
//                    Date date = cell.getDateCellValue();
//
//                    cellValue = sdf.format(date);
//                } else {
//                    //  判断是否为科学计数法（包含E、e、+等符号）
//                    if (("" + cell.getNumericCellValue()).indexOf("E") != -1 || ("" + cell.getNumericCellValue()).indexOf("e") != -1 || ("" + cell.getNumericCellValue()).indexOf("+") != -1) {
//                        BigDecimal bd = new BigDecimal("" + cell.getNumericCellValue());
//                        cellValue = bd.toPlainString();
//                    } else {
//                        cellValue = String.valueOf(cell.getNumericCellValue());
//                    }
//
//                    String[] str = cellValue.split("\\.");
//                    if (str.length > 1) {
//                        if (str[1].equals("0")) {
//                            cellValue = str[0];
//                        }
//                    }
//                }
//                break;
//            case "STRING": //字符串
//                cellValue = String.valueOf(cell.getStringCellValue());
//                break;
//            case "BOOLEAN": //Boolean
//                cellValue = String.valueOf(cell.getBooleanCellValue());
//                break;
//            case "FORMULA": //公式
//                try {
//                    /*
//                     * 此处判断使用公式生成的字符串有问题，因为HSSFDateUtil.isCellDateFormatted(cell)判断过程中cell
//                     * .getNumericCellValue();方法会抛出java.lang.NumberFormatException异常
//                     */
//                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
//                        SimpleDateFormat sdf = null;
//                        // 处理时分秒问题
//                        if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
//                            sdf = new SimpleDateFormat("HH:mm");
//                        } else {// 日期
//                            sdf = new SimpleDateFormat("yyyy-MM-dd");
//                        }
//                        Date date = cell.getDateCellValue();
//                        cellValue = sdf.format(date);
//                    } else {
//                        BigDecimal b = new BigDecimal(cell.getNumericCellValue());
//                        cellValue = String.valueOf(b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue());
//                    }
//                } catch (IllegalStateException e) {
//                    cellValue = String.valueOf(cell.getRichStringCellValue());
//                }
//                //BigDecimal b = new BigDecimal(cell.getNumericCellValue());
//                // cellValue = String.valueOf(b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue());
//                break;
//            case "BLANK": //空值
//                cellValue = "";
//                break;
//            case "ERROR": //故障
//                cellValue = "非法字符";
//                break;
//            default:
//                cellValue = "未知类型";
//                break;
//        }
//
//        return cellValue;
//    }
//
//    private static String getCellValue(CellValue cell) {
//        String cellValue = null;
//        switch (cell.getCellType()) {
//            case Cell.CELL_TYPE_STRING:
//                System.out.print("String :");
//                cellValue = cell.getStringValue();
//                break;
//
//            case Cell.CELL_TYPE_NUMERIC:
//                System.out.print("NUMERIC:");
//                cellValue = String.valueOf(cell.getNumberValue());
//                break;
//            case Cell.CELL_TYPE_FORMULA:
//                System.out.print("FORMULA:");
//                break;
//            default:
//                break;
//        }
//
//        return cellValue;
//    }
//
//    /**
//     * 处理公式
//     *
//     * @param cell             : 表格
//     * @param formulaEvaluator : 公式
//     * @return : 返回结果
//     */
//    public static String getCellValueFormula(Cell cell, FormulaEvaluator formulaEvaluator) {
//        if (cell == null || formulaEvaluator == null) {
//            return null;
//        }
//
//        if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
//
//            return String.valueOf(formulaEvaluator.evaluate(cell).getNumberValue());
//        }
//
//        return getCellValue(cell);
//    }
//
//    /**
//     * 获取公式
//     *
//     * @param file : 文件
//     * @return : 结果
//     */
//    public static FormulaEvaluator getFormulaEvaluator(MultipartFile file) {
//        //获得文件名
//        String fileName = file.getOriginalFilename();
//        //创建Workbook工作薄对象，表示整个excel
//        Workbook workbook = null;
//
//        FormulaEvaluator formulaEvaluator = null;
//        try (InputStream is = file.getInputStream()) {
//            //获取excel文件的io流
//            //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
//            if (fileName.endsWith(xls)) {
//                //2003
//                workbook = new HSSFWorkbook(is);
//                formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
//                // formulaEvaluator = new HSSFFormulaEvaluator((HSSFWorkbook) workbook);
//            } else if (fileName.endsWith(xlsx)) {
//                //2007
//                workbook = new XSSFWorkbook(is);
//                // formulaEvaluator = new XSSFFormulaEvaluator((XSSFWorkbook) workbook);
//                formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
//            }
//        } catch (IOException e) {
//            logger.info(e.getMessage());
//        }
//
//        return formulaEvaluator;
//    }
//
//    /**
//     * 公式计算
//     *
//     * @param cell
//     * @param formulaEvaluator
//     * @return
//     */
//    public static String getCellValOrFormulaEvaluator(Cell cell, FormulaEvaluator formulaEvaluator) {
//
//        String cellValue = "";
//        if (cell == null) {
//            return cellValue;
//        }
//
//        //判断数据的类型
//        switch (cell.getCellTypeEnum().toString()) {
//            case "NUMERIC": //数字
//                if (HSSFDateUtil.isCellDateFormatted(cell)) {
//                    SimpleDateFormat sdf = null;
//                    // 处理时分秒问题
//                    if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
//                        sdf = new SimpleDateFormat("HH:mm");
//                    } else {// 日期
//                        sdf = new SimpleDateFormat("yyyy-MM-dd");
//                    }
//                    Date date = cell.getDateCellValue();
//
//                    cellValue = sdf.format(date);
//                } else {
//                    //  判断是否为科学计数法（包含E、e、+等符号）
//                    if (("" + cell.getNumericCellValue()).indexOf("E") != -1 || ("" + cell.getNumericCellValue()).indexOf("e") != -1 || ("" + cell.getNumericCellValue()).indexOf("+") != -1) {
//                        BigDecimal bd = new BigDecimal("" + cell.getNumericCellValue());
//                        cellValue = bd.toPlainString();
//                    } else {
//                        cellValue = String.valueOf(cell.getNumericCellValue());
//                    }
//
//                    String[] str = cellValue.split("\\.");
//                    if (str.length > 1) {
//                        if (str[1].equals("0")) {
//                            cellValue = str[0];
//                        }
//                    }
//                }
//                break;
//            case "STRING": //字符串
//                cellValue = String.valueOf(cell.getStringCellValue());
//                break;
//            case "BOOLEAN": //Boolean
//                cellValue = String.valueOf(cell.getBooleanCellValue());
//                break;
//            case "FORMULA": //公式
//                BigDecimal b = new BigDecimal(cell.getNumericCellValue());
//                cellValue = String.valueOf(b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue());
//                //cellValue = getCellValue(formulaEvaluator.evaluate(cell));
//                break;
//            case "BLANK": //空值
//                cellValue = "";
//                break;
//            case "ERROR": //故障
//                cellValue = "非法字符";
//                break;
//            default:
//                cellValue = "未知类型";
//                break;
//        }
//
//        return cellValue;
//    }
//
//
//    public ExcelImportUtil(MultipartFile file, Class c, boolean flag) {
//
//        try {
//            Workbook workbook = getWorkBook(file);
//            List<ExcelEntity> entitys = getExcelEntitys(c); //获取类注解信息
//            this.list = new ArrayList();
//            Cell cell = null;
//            if (workbook != null) {
//                Sheet sheet = workbook.getSheetAt(0);
//                // 跳过第一行
//                int firstRowNum = flag ? sheet.getFirstRowNum() + 1 : sheet.getFirstRowNum();
//                int lastRowNum = sheet.getLastRowNum();
//                for (int rowNum = firstRowNum + 1; rowNum <= lastRowNum; rowNum++) {
//                    Row row = sheet.getRow(rowNum);
//                    Object objCla = c.newInstance();    //实例化对象
//                    for (ExcelEntity entity : entitys) {
//                        cell = row.getCell(Integer.valueOf(entity.getSort()));  //根据注释下标获得对应的单元格
//                        Method method = c.getMethod(entity.getFieldName(), entity.getClassType()); //获取属性的set方法
//                        //System.out.println(cell.getCellTypeEnum());
//                        //获取属性set方法的返回值
//
//                        Object invoke = method.invoke(objCla, getCellVal(cell));
//                    }
//
//                    this.list.add(objCla);
//                }
//            }
//        } catch (Exception e) {
//            logger.error("异常信息:{}", e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    public ExcelImportUtil(File file, Class c, boolean flag) {
//        try {
//            Workbook workbook = getWorkBook(file);
//            List<ExcelEntity> entitys = getExcelEntitys(c); //获取类注解信息
//            this.list = new ArrayList();
//            Cell cell = null;
//            if (workbook != null) {
//                Sheet sheet = workbook.getSheetAt(0);
//                // 跳过第一行
//                int firstRowNum = flag ? sheet.getFirstRowNum() + 1 : sheet.getFirstRowNum();
//                int lastRowNum = sheet.getLastRowNum();
//                for (int rowNum = firstRowNum + 1; rowNum <= lastRowNum; rowNum++) {
//                    Row row = sheet.getRow(rowNum);
//                    Object objCla = c.newInstance();    //实例化对象
//                    for (ExcelEntity entity : entitys) {
//                        cell = row.getCell(Integer.valueOf(entity.getSort()));  //根据注释下标获得对应的单元格
//                        Method method = c.getMethod(entity.getFieldName(), entity.getClassType()); //获取属性的set方法
//                        //System.out.println(cell.getCellTypeEnum());
//                        //获取属性set方法的返回值
//
//                        Object invoke = method.invoke(objCla, ExcelImportUtil.getCellVal(cell));
//                        // Object invoke = method.invoke(objCla, getCellVal(cell));
//                    }
//
//                    this.list.add(objCla);
//                }
//            }
//        } catch (Exception e) {
//            logger.error("异常信息:{}", e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    public static List<String[]> readExcelBySheet(MultipartFile file, Integer sheetNum) throws IOException {
//        //检查文件
//        checkFile(file);
//        //获得Workbook工作薄对象
//        Workbook workbook = getWorkBook(file);
//        //创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回
//        List<String[]> list = new ArrayList<String[]>();
//        if (workbook != null) {
//            //获得当前sheet工作表
//            Sheet sheet = workbook.getSheetAt(sheetNum);
//            if (sheet == null) {
//                return null;
//            }
//            //获得当前sheet的开始行
//            int firstRowNum = sheet.getFirstRowNum();
//            //获得当前sheet的结束行
//            int lastRowNum = sheet.getLastRowNum();
//            //循环除了第一行的所有行
//            for (int rowNum = firstRowNum + 1; rowNum <= lastRowNum; rowNum++) {
//                //获得当前行
//                Row row = sheet.getRow(rowNum);
//                if (row == null) {
//                    continue;
//                }
//                //获得当前行的开始列
//                int firstCellNum = row.getFirstCellNum();
//                //获得当前行的列数
//                int lastCellNum = row.getPhysicalNumberOfCells();
//                String[] cells = new String[row.getPhysicalNumberOfCells()];
//                //循环当前行
//                for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
//                    Cell cell = row.getCell(cellNum);
//                    cells[cellNum] = getCellValue(cell);
//                }
//                list.add(cells);
//            }
//            workbook.close();
//        }
//        return list;
//    }
//}
