package com.dms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.Collection;
import java.util.List;

/**
 * 该类是一个工具类，负责客户端的IO操作
 * @author Administrator
 *
 */
public class IOUtil {
	
	/**
	 * 将指定的long值以字符串的形式写入到
	 * 给定文件的第一行
	 * @param l
	 * @param file
	 * @throws Exception 
	 */
	public static void saveLong(
					long lon,File file) throws Exception{
		FileOutputStream fis = new FileOutputStream(file);
		OutputStreamWriter osw = new OutputStreamWriter(fis,"utf-8");
		PrintWriter pw = new PrintWriter(osw,true);
		pw.println(lon);
		pw.close();
		
	}
	
	/**
	 * 将集合中每个元素的toString方法返回的字符串
	 * 以行为单位写入到指定文件中。
	 * @param c
	 * @param file
	 * @throws Exception 
	 */
	public static void saveCollection(
								Collection c,File file) throws Exception{
		FileOutputStream fos = new FileOutputStream(file);
		OutputStreamWriter osw = new OutputStreamWriter(fos,"UTF-8");
		PrintWriter pw = new PrintWriter(osw,true);
		if(c instanceof List){
			for(int i = 0;i<c.size();i++){
				pw.println(((List) c).get(i));
			}
		}
		pw.close();
	}
	
	/**
	 * 从给定的RandomAccessFile当前位置开始连续
	 * 读取length个字节，并按照ISO8859-1编码转换为字符串后返回
	 * @param raf
	 * @param length
	 * @return
	 * @throws Exception 
	 */
	public static String readString(
					RandomAccessFile raf,int length) throws Exception{
		byte[] bytes = new byte[length];
		raf.read(bytes);
		
		String str = new String(bytes,"ISO8859-1");
		return str;		
	}
	
	/**
	 * 从给定文件中读取第一行字符串，然后将其
	 * 转换为一个long值后返回
	 * @param file
	 * @return
	 * @throws Exception 
	 */
	public static long readLong(File file) throws Exception{
		   FileInputStream fis = new FileInputStream(file);
		   InputStreamReader isr = new InputStreamReader(fis,"UTF-8");
		   BufferedReader br = new BufferedReader(isr);
		   String str = null;
		   if((str=br.readLine())!=null){
			   long lon = Long.parseLong(str);
			   br.close();
			   return lon;
		   }else{
			   return -1;
		   }
	}
}







