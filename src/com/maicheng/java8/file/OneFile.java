package com.maicheng.java8.file;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Copyright (C), 2017-2018, maicheng
 * FileName: OneFile.java
 * Author:   maido
 * Date:     2018/7/8 11:37
 * Description: 第一个文件操作注释  五个核心类  FIle InputStream OutputStream  Reader  Writer
 */
public class OneFile {
    public static void main(String[] args) throws IOException {
        // 1. 如果目录不能访问， 2 文件重名或者文件名称重名是不能创建文件的
        File file = new File("E://demo.txt");
        if (file.exists()) {

            System.out.println("文件删除成功：" + file.delete());
        } else {
            System.out.println("文件创建成功：" + file.createNewFile());
        }

        // 使用 File.separator  代替
    }

    @Test
    public void test2() {
        File file = new File("E:" + File.separator + "demo" + File.separator + "demo.txt");
        if(file.getParentFile().exists()) {
            file.getParentFile().mkdir();
        }
    }
}