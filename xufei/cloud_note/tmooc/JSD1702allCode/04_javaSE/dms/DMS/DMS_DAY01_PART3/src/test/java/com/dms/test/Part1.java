package com.dms.test;

import java.io.File;

import com.dms.IOUtil;

/**
 * 该测试用例是用来测试DMSClient中的方法
 * hasLogs()。
 * @author Xiloer
 *
 */
public class Part1 {
	private File logFile = new File("wtmpx");
	private File lastPositionFile;
	/**
	 * 第一步解析日志中的一个环节，
	 * 根据书签文件记录的位置判断是否还有
	 * 日志可以解析，若有，则将上次最后的位置
	 * 返回，若没有则返回-1。
	 * @return
	 */
	private long hasLogs(){
		try {
			/*
			 * 实现步骤:
			 * 1:若lastPositionFile不存在，则说明
			 *   从来没有解析过，那么从头开始解析即可，返回0。
			 *   若存在，则读取里面记录的数字，该数字在文件中
			 *   的第一行，是一个字符串，读取出来需要转换为long
			 *   值(使用IOUtil的readLong方法)。
			 * 2:判断上次读取的位置与文件的长度，若文件的长度
			 *   减去上次读取的位置大于等于一条日志的长度，则
			 *   说明还有日志可以读取，那么直接将上次的位置返回，
			 *   从这里读取即可，若不足则返回-1.  
			 */
			if(lastPositionFile.exists()==false){
				return 0;
			}else{
				long lastlocation = IOUtil.readLong(lastPositionFile);
				//System.out.println(lastlocation);
				if((logFile.length()-lastlocation)>=372){
					return lastlocation;
				}else{
					return -1;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	/**
	 * 运行程序后应当顺序输出:
	 * pos:0
	 * pos:3720
	 * pos:-1
	 * @param args
	 */
	public static void main(String[] args) {
		Part1 p = new Part1();
		p.lastPositionFile = new File("src/test/resources/last-position.txt");
		long pos = p.hasLogs();
		System.out.println("pos:"+pos);
		
		p.lastPositionFile = new File("src/test/resources/last-position1.txt");
		pos = p.hasLogs();
		System.out.println("pos:"+pos);
		
		p.lastPositionFile = new File("src/test/resources/last-position2.txt");
		pos = p.hasLogs();
		System.out.println("pos:"+pos);
	}
}
