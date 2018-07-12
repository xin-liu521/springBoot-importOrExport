package com.velvol.salary.util;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * Created by Administrator on 2018/4/16.
 */
public class UnZipFiles {

    public static final int DEFAULT_BUFSIZE = 1024 * 16;

    public  static List<String> unZip(File files) throws IOException{
        String descDir = "E:/unZipFiles/";
        File pathFile = new File(descDir);
        if(!pathFile.exists())
        {
            pathFile.mkdirs();
        }
        List<String> pathList = new ArrayList<>();
        String outPath = "";
        //解决zip文件中有中文目录或者中文文件
        ZipFile zip = new ZipFile(files, Charset.forName("GBK"));
        for(Enumeration entries = zip.entries(); entries.hasMoreElements();)
        {
            ZipEntry entry = (ZipEntry)entries.nextElement();
            String zipEntryName = entry.getName();
            InputStream in = zip.getInputStream(entry);
            outPath = descDir+zipEntryName;

            //判断路径是否存在,不存在则创建文件路径
            File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
            if(!file.exists())
            {
                file.mkdirs();
            }
            //判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
            if(new File(outPath).isDirectory())
            {
                continue;
            }
            //输出文件路径信息
            pathList.add(outPath);
            System.out.println(outPath);
            File pathfi = new File(outPath);
            //in.close();
            write(in, pathfi);
        }
//        if (pathList.size()>0) {
//            files.delete();
//        }
        System.out.println("******************解压完毕********************");
        return pathList;
    }

    public static void main(String[] args) throws IOException {
        /**
         * 解压文件
         */
        File zipFile = new File("E:/资料.zip");
        String path = "d:/zipfile/";
        unZip(zipFile);
    }


    /**
     * 将输入流中的数据写到指定文件
     *
     * @param inputStream
     * @param destFile
     */
    public static void write(InputStream inputStream, File destFile) throws IOException
    {
        BufferedInputStream bufIs = null;
        BufferedOutputStream bufOs = null;
        try {
            bufIs = new BufferedInputStream(inputStream);
            bufOs = new BufferedOutputStream(new FileOutputStream(destFile));
            byte[] buf = new byte[DEFAULT_BUFSIZE];
            int len = 0;
            while ((len = bufIs.read(buf, 0, buf.length)) > 0) {
                bufOs.write(buf, 0, len);
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            close(bufOs, bufIs);
        }
    }

    /**
     * 安全关闭多个流
     *
     * @param streams
     */
    public static void close(Closeable... streams)
    {
        try {
            for (Closeable s : streams) {
                if (s != null)
                    s.close();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace(System.err);
        }
    }
}
