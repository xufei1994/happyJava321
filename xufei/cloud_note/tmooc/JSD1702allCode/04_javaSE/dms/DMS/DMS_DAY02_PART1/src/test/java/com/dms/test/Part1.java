package com.dms.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.dms.bo.LogData;

/**
 * 该测试用来测试IOUtil类中的方法:
 * public static List<LogData> loadLogData(File file) throws Exception
 */
public class Part1 {
	/**
	 * 从给定的文件中读取每一条配对日志,并以若干LogData实例保存然后存入
	 * 一个集合中后返回。
	 * 注:LogData有一个构造方法，可以将log.txt文件中:
	 *    lidz,441232,7,1375334515,192.168.1.61
	 *    格式的字符串直接传入，然后就生成了LogData对象。
	 * @param file
	 * @return
	 * @throws Exception 
	 */
	public static List<LogData> loadLogData(File file) throws Exception{
		BufferedReader br = null;
		List<LogData> list = new ArrayList<LogData>();
		try {
		    br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
		    String message = null;
		    while((message = br.readLine())!=null){
		    	LogData data = new LogData(message);
		    	list.add(data);
		    }
		    
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally{
			if(br!=null){
				br.close();
			}
		}
		return list;
	}
	
	public static void main(String[] args) {
		try {
			File file = new File("src/test/resources/log.txt");
			List<LogData> list = loadLogData(file);
			/*
			 * 测试输出结果：
			 * 应与当前目录中log.txt文件内容一致:
			 * lidz,441232,7,1375334515,192.168.1.61
			 * ....
			 */
			for(LogData log : list){
				System.out.println(log);
			}
			System.out.println("读取完毕！");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
