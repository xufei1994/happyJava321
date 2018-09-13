package com.dms.test;

import java.io.File;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试用例2
 * 测试init方法。该方法是DMSServer中的
 * 方法。
 * @author Xiloer
 *
 */
public class Part2 {
	//用来接收客户端连接的服务端的ServerSocket
	private ServerSocket server;
	//用来管理处理客户端请求的线程的线程池
	private ExecutorService threadPool;
	//保存所有客户端发送过来配对日志的文件
	private File serverLogFile;
	/*
	 * 以上属性无需复制到DMSServer中，因为该类已经定义过
	 * 这些属性，这里只是用于测试这些属性的初始化
	 */
		
		
	/**
	 * 构造方法初始化第二步,根据配置项初始化属性
	 * Map中的key与属性同名(区别仅在于key中的字符串全小写)，根据
	 * 对应的key去除value来初始化相应的属性。
	 * @param config
	 * @throws Exception 
	 */
	private void init(Map<String,String> config) throws Exception{
		/*
		 * 用配置文件中的<logrecfile>初始化属性：serverLogFile
		 * 用配置文件中的<threadsum>初始化属性：threadPool，这里创建固定大小线程池。该值作为线程池线程数量
		 * 用配置文件中的<serverport>初始化属性：server,这里这个值为ServerSocket的服务端口
		 */
		try {
			serverLogFile = new File(config.get("logrecfile"));
			threadPool = Executors.newFixedThreadPool(Integer.parseInt(config.get("threadsum")));
			server = new ServerSocket(Integer.parseInt(config.get("serverport")));
			
			
		} catch (Exception e) {
			System.out.println("初始化属性失败!");
			e.printStackTrace();
			throw e;
		}
	}
	public static void main(String[] args) {
		try {
			Map<String,String> config = new HashMap<String,String>();
			config.put("logrecfile", "server-logs.txt");
			config.put("threadsum", "30");
			config.put("serverport", "8088");
			Part2 p = new Part2();
			p.init(config);
			System.out.println("初始化完毕!");
		} catch (Exception e) {
			System.out.println("失败!");
		}
	}
	
}
