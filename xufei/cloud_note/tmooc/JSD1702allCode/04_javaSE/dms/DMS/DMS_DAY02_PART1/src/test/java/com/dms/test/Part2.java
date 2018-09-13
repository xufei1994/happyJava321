package com.dms.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
/**
 * 该测试用来测试IOUtil类中的方法:
 * public static List<String> loadLogRec(File file) throws Exception
 */
public class Part2 {
	/**
	 * 从给定的文件中读取每一行字符串(配对日志)
	 * 并存入一个集合后返回
	 * @param file
	 * @return
	 * @throws Exception 
	 */
	public static List<String> loadLogRec(File file) throws Exception{
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
			List<String> list = new ArrayList<String>();
			String message = null;
			while((message = br.readLine())!=null){
				list.add(message);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally{
			if(br!=null){
				br.close();
			}
		}
		
	}
	
	public static void main(String[] args) {
		try {
			File file = new File("src/test/resources/logrec.txt");
			List<String> list = loadLogRec(file);	
			/*
			 * 测试输出结果：
			 * 应与当前目录中logrec.txt文件内容一致:
			 * huangr,12348,7,1377537895,192.168.1.38|huangr,12348,8,1377541495,192.168.1.38
			 * guojing,12341,7,1377660105,192.168.1.34|guojing,12341,8,1377666173,192.168.1.34
			 */
			for(String log : list){
				System.out.println(log);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
