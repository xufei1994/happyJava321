package com.dms.test;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;



/**
 * 测试用例2
 * 测试init方法。该方法是DMSClient中的
 * 方法。
 * @author Xiloer
 *
 */
public class Part2 {
	//属性定义
	
		//第一步:解析日志所需属性
		//unix系统日志文件
		private File logFile;
		//保存解析后日志的文件
		private File textLogFile;
		//书签文件
		private File lastPositionFile;
		//每次解析日志的条目数
		private int batch;
		
		//第二步:配对日志所需要属性
		//保存配对日志的文件
		private File logRecFile;
		//保存未配对日志的文件
		private File loginLogFile;
		
		//第三步:发送日志所需要属性
		//服务端地址
		private String serverHost;
		//服务端端口
		private int serverPort;
	/*
	 * 以上属性无需复制到DMSClient中，因为该类已经定义过
	 * 这些属性，这里只是用于测试这些属性的初始化
	 */
		
		
		
	/**
	 * 构造方法初始化第二步,根据配置项初始化属性
	 * Map中的key与属性同名(区别仅在于key中的字符串全小写)，根据
	 * 对应的key取出value来初始化相应的属性。
	 * @param config
	 * @throws Exception 
	 */
		
	private void init(Map<String,String> config) throws Exception{
		try {
			
			
		} catch (Exception e) {
			System.out.println("初始化属性失败!");
			e.printStackTrace();
			throw e;
		}
	}
	public static void main(String[] args) {
		try {
			Map<String,String> config = new HashMap<String,String>();
			config.put("logfile", "wtmpx");
			config.put("textlogfile", "log.txt");
			config.put("lastpositionfile", "last-position.txt");
			config.put("batch", "10");
			config.put("logrecfile", "logrec.txt");
			config.put("loginlogfile", "login.txt");
			config.put("serverhost", "localhost");
			config.put("serverport", "8088");
			Part2 p = new Part2();
			p.init(config);
			System.out.println("初始化完毕!");
		} catch (Exception e) {
			System.out.println("失败!");
		}
	}
	
}
