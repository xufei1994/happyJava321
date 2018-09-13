package com.dms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

//import com.dms.test.Part1.ClientHandler;
//import com.dms.test.Part1.SaveLogHandler;

//import com.dms.test.Part1.ClientHandler;
//import com.dms.test.Part1.SaveLogHandler;

/**
 * DMS服务端，用来接收每个客户端发送过来的
 * 配对日志并保存在本地文件中
 * @author Administrator
 *
 */
public class DMSServer {
	//属性定义
	//用来接收客户端连接的服务端的ServerSocket
	private ServerSocket server;
	//用来管理处理客户端请求的线程的线程池
	private ExecutorService threadPool;
	//保存所有客户端发送过来配对日志的文件
	private File serverLogFile;
	//消息队列
	private BlockingQueue<String> messageQueue = new LinkedBlockingQueue<String>();
	
	public DMSServer() throws Exception{
		try {
			System.out.println("服务端正在初始化...");
			//1 解析配置文件server-config.xml
			Map<String,String> config = loadConfig();
			
			//2 根据配置文件内容初始化属性
			init(config);
			System.out.println("服务端初始化完毕...");
		} catch (Exception e) {
			System.out.println("初始化服务端失败!");
			throw e;
		}
	}
	
	/**
	 * 构造方法初始化第一步，解析配置文件
	 * @return 返回的Map中保存的是配置文件中的
	 *         每一条内容，其中key:标签的名字，
	 *         value为标签中间的文本
	 * @throws Exception 
	 */
	private Map<String,String> loadConfig() throws Exception{
		try {
			SAXReader reader = new SAXReader();
			Document doc
				= reader.read(new File("server-config.xml"));
			Element root = doc.getRootElement();
			
			Map<String,String> config
				= new HashMap<String,String>();
			/*
			 * 获取<config>标签中的所有子标签
			 * 并将每一个子标签的名字作为key,中间的
			 * 文本作为value存入Map集合
			 */
			List<Element> list = root.elements();
			for(Element e : list){
				String key = e.getName();
				String value = e.getTextTrim();
				config.put(key, value);
			}
			return config;
		} catch (Exception e) {
			System.out.println("解析配置文件异常!");
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * 构造方法初始化第二步,根据配置项初始化属性
	 * @param config
	 * @throws Exception 
	 */
	private void init(Map<String,String> config) throws Exception{
		/*
		 * 用配置文件中的<logrecfile>初始化属性：serverLogFile
		 * 用配置文件中的<threadsum>初始化属性：threadPool，这里创建固定大小线程池。该值作为线程池线程数量
		 * 用配置文件中的<serverport>初始化属性：server,这里这个值为ServerSocket的服务端口
		 */
		this.server = new ServerSocket(
			Integer.parseInt(config.get("serverport"))	
		);
		
		this.serverLogFile = new File(
			config.get("logrecfile")	
		);
		this.threadPool = Executors.newFixedThreadPool(
			Integer.parseInt(config.get("threadsum"))	
		);
	}	
	/**
	 * 服务端开始工作的方法
	 * @throws Exception
	 */
	public void start() throws Exception{
		/*
		 * 实现要求:
		 *  首先单独启动一个线程，用来运行SaveLogHandler
		 *  这个任务，目的是保存所有配对日志
		 *  然后开始循环监听服务端端口，一旦一个客户端连接了，
		 *  就实例化一个ClientHander,然后将该任务交给线程池
		 *  使其分配线程来处理与该客户端的交互。
		 *  
		 */
		try {
			System.out.println("服务端开始工作...");
			Thread t1 = new Thread(new SaveLogHandler());
			t1.start();
			while(true){
				threadPool.execute(new ClientHandler(server.accept()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public static void main(String[] args) {
		try {
			DMSServer server = new DMSServer();
			server.start();
		} catch (Exception e) {
			System.out.println("启动服务端失败!");
		}
	}
	/**
	 * 该线程负责从消息队列中取出每一条配对日志，
	 * 并存入到serverLogFile文件
	 * @author Administrator
	 *
	 */
	private class SaveLogHandler implements Runnable{
		public void run(){
			PrintWriter pw = null;
			try {
				pw = new PrintWriter(
					new FileOutputStream(
						serverLogFile,true	
					)	
				);
				while(true){
					if(messageQueue.size()>0){
						pw.println(messageQueue.poll());
					}else{
						pw.flush();
						Thread.sleep(500);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				if(pw != null){
					pw.close();
				}
			}
		}
	}
	
	/**
	 * 处理一个指定客户端请求
	 * @author Administrator
	 *
	 */
	private class ClientHandler implements Runnable{
		private Socket socket;
		public ClientHandler(Socket socket){
			this.socket = socket;
		}
		public void run(){
			/*
			 * 思路:
			 * 首先接收客户端发送过来的所有配对日志，
			 * 直到读取到"OVER"为止，然后将这些配对
			 * 日志保存到本地的文件中，并回复客户端
			 * "OK"
			 * 执行步骤:
			 * 1:通过Socket创建输出流，用来给客户端
			 *   发送响应
			 * 2:通过Socket创建输入流，读取客户端发送
			 *   过来的日志
			 * 3:循环读取客户端发送过来的每一行字符串，并
			 *   先判断是否为字符串"OVER",若不是，则是
			 *   一条配对日志，那么保存到本地文件，若是，
			 *   则停止读取。
			 * 4:成功读取所有日志后回复客户端"OK"      
			 */
			PrintWriter pw = null;
			try {
				//1
				pw = new PrintWriter(
					new OutputStreamWriter(
						socket.getOutputStream(),"UTF-8"	
					)	
				);
				//2
				BufferedReader br = new BufferedReader(
					new InputStreamReader(
						socket.getInputStream(),"UTF-8"	
					)	
				);
				
				//3
				String message = null;
				while((message = br.readLine())!=null){
					if("OVER".equals(message)){
						break;
					}
					//将该日志写入文件保存
					messageQueue.offer(message);
				}
				
				//4
				pw.println("OK");
				pw.flush();
				
				
			} catch (Exception e) {
				e.printStackTrace();
				pw.println("ERROR");
				pw.flush();
			} finally{
				try {
					//与客户端断开连接释放资源
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}





