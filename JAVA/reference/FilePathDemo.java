package com.cn.kd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FilePathDemo {
    public static void main(String[] args) throws IOException {

        File srcFolder = new File("F:/College/JAVA/20.6.17");

        getAllJavaFilePaths(srcFolder);
    }

    private static void getAllJavaFilePaths(File srcFolder) throws IOException {

        File[] fileArray = srcFolder.listFiles();

        for (File file : fileArray) {

            if (file.isDirectory()) {

                getAllJavaFilePaths(file);

            } else {

                if (file.getName().endsWith(".java")) {

                    FileInputStream fis = new FileInputStream(file);
                    InputStreamReader isr = new InputStreamReader(fis, "GBK");
                    BufferedReader br = new BufferedReader(isr);
                    String str = null;

                    StringBuffer sb = new StringBuffer();

                    while ((str = br.readLine()) != null) {

                        str += "\n";
                        sb.append(str);
                    }
                    String str2 = sb.toString();

                    FileOutputStream fos = new FileOutputStream(file.getAbsolutePath(), false);
                    OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
                    osw.write(str2);
                    osw.flush();
                    osw.close();
                    fos.close();
                    br.close();
                    isr.close();
                    fis.close();
                }
            }
        }
    }
}
