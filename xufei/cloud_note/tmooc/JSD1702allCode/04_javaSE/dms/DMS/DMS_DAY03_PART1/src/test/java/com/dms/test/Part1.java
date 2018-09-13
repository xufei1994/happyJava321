package com.dms.test;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 测试用例1
 * 测试loadConfig方法。该方法是DMSServer中的
 * 方法。
 * @author Xiloer
 *
 */
public class Part1 {
	/**
	 * 构造方法初始化第一步，解析配置文件
	 * 读取并解析项目根目录中的配置文件server-config.xml，将
	 * 根标签<config>中的所有子标签解析出来，并将每一个子
	 * 标签的名字作为key,标签中间的文本作为value,存入到
	 * Map后返回。
	 * @return 返回的Map中保存的是配置文件中的
	 *         每一条内容，其中key:标签的名字，
	 *         value为标签中间的文本
	 * @throws Exception 
	 */
	private Map<String,String> loadConfig() throws Exception{
		try {
			Map<String,String> config
				= new HashMap<String,String>();
			SAXReader reader = new SAXReader();
			Document d = reader.read(new FileInputStream("server-config.xml"));
			Element root = d.getRootElement();
			List<Element> elelist = root.elements();
			
			for(Element e:elelist){
				e = root.element(e.getName());
				String key = e.getName();		
				String value = e.getText();
				config.put(key,value);
			}
			return config;
		} catch (Exception e) {
			System.out.println("解析配置文件异常!");
			e.printStackTrace();
			throw e;
		}
	}
	
	public static void main(String[] args) {
		try {
			Part1 p = new Part1();
			Map<String,String> config = p.loadConfig();
			/*
			 * 输出后的效果应当为:
			 * {threadsum=30, serverport=8088, logrecfile=server-logs.txt}
			 */
			System.out.println(config);
		} catch (Exception e) {
			System.out.println("失败!");
		}
	}
}
