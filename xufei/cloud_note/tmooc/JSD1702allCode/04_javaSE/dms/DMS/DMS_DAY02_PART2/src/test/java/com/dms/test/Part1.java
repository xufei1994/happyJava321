package com.dms.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import com.dms.IOUtil;

/**
 * 该测试用例是用于测试DMSClient的
 * 第三步发送日志的方法
 * 当前测试用例中的属性都可以在DMSClient中找到
 * 
 * 根据需求写好方法后，首先运行当前目录下的DMSServer类
 * 然后运行当前测试用例，查看DMSServer控制台输出的结果。
 * 结果应为:
 * 等待客户端连接...
 * 客户端连接了,开始接收客户端发送的日志...
 * 接收到配对日志:huangr,12348,7,1377537895,192.168.1.38|huangr,12348,8,1377541495,192.168.1.38
 * 接收到配对日志:guojing,12341,7,1377660105,192.168.1.34|guojing,12341,8,1377666173,192.168.1.34
 * 所有日志均已接收完毕!
 * 响应客户端结果:OK
 * 
 * @author Xiloer
 *
 */
public class Part1 {
	//属性定义	
	//第二步:配对日志所需要属性
	//保存配对日志的文件
	private File logRecFile = new File("src/test/resources/logrec.txt");
	
	//第三步:发送日志所需要属性
	//服务端地址
	private String serverHost = "localhost";
	//服务端端口
	private int serverPort = 8088;
	
	/**
	 * 第三步:发送日志
	 * @return true:发送成功
	 *         false:发送失败
	 */
	private boolean sendLogs(){
			/*
			 * 实现要求:
			 * 将logRecFile文件中的所有配对日志读取出来(使用IOUtil的
			 * loadLogRec方法)，然后连接上服务端并将每一个LogRec的
			 * toString方法返回的字符串按行发送过去，最后再发送一个字符
			 * 串"OVER"，以向服务端表示所有日志均已发送完毕，若服务端全
			 * 部接收，会发送一个字符串"OK"表示所有日志全部接收完毕，
			 * 这时就可以将该文件(logRecFile)删除，方法返回true表示
			 * 发送工作完毕了。
			 * 
			 * 实现步骤:
			 * 1:必要的判断,logRecFile文件必须存在
			 * 2:将所有配对日志读取出来并存入一个集合
			 *   等待发送(IOUtil.loadLogRec方法)
			 * 3:通过实例化Socket来连接服务端
			 * 4:通过Socket获取输出流并包装为PrintWRiter
			 *   由于网络间传输需要固定字符集，这里统一为UTF-8
			 * 5:顺序将所有配对日志按行发送给服务端
			 * 6:单独发送一个字符串"OVER"表示所有日志
			 *   均已发送完毕
			 * 7:通过Socket获取输入流并包装为BuferedReader
			 * 8:读取服务端发送回来的响应字符串
			 * 9:若响应的字符串为"OK",表示服务端正常
			 *   接收了所有日志,这时就可以将logRecFile
			 *   文件删除并返回true表示发送完毕。
			 * 若服务端回复其他响应字符串或方法执行过程中出错，
			 * 则方法返回false
			 * 最终应当在finally中将Socket关闭      
			 */	
		if(!logRecFile.exists()){
			return false;
		}
		PrintWriter pw= null;
		BufferedReader br = null;
		Socket socket = null;
		try {
			List<String> list= IOUtil.loadLogRec(logRecFile);
			socket = new Socket(serverHost, serverPort);
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"),true);
			for(String message:list){
				pw.println(message);
			}
			pw.println("OVER");
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
			if("OK".equals(br.readLine())){
				logRecFile.delete();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(socket!=null){
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		
		return false;
	}
	public static void main(String[] args) {
		try {
			Part1 p = new Part1();
			p.sendLogs();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
