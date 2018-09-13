package com.dms.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 测试用服务端
 * 为了测试DMSClient方法sendLogs方法使用
 * @author Xiloer
 *
 */
public class DMSServer {
	public static void main(String[] args) {
		
		PrintWriter pw = null;
		try {
			ServerSocket server = new ServerSocket(8088);
			System.out.println("等待客户端连接...");
			Socket socket = server.accept();
			System.out.println("客户端连接了,开始接收客户端发送的日志...");
			BufferedReader br = new BufferedReader(
				new InputStreamReader(
					socket.getInputStream(),"UTF-8"	
				)	
			);
			pw = new PrintWriter(
				new OutputStreamWriter(
					socket.getOutputStream(),"UTF-8"	
				),true	
			);
			String line = null;
			while((line = br.readLine())!=null){
				if("OVER".equals(line)){
					System.out.println("所有日志均已接收完毕!");
					break;
				}
				System.out.println("接收到配对日志:"+line);
			}
			System.out.println("响应客户端结果:OK");
			pw.println("OK");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("出错，响应客户端:ERROR");
			pw.println("ERROR");
		} 
		
	}
}
