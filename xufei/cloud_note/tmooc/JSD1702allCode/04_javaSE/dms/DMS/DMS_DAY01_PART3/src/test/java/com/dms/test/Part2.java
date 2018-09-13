package com.dms.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import com.dms.IOUtil;

/**
 * 该测试用例是用来测试DMSClient中的方法:
 * parseLogs()。
 * 
 * @author Xiloer
 *
 */
public class Part2 {
	//第一步:解析日志所需属性
	//unix系统日志文件
	private File logFile = new File("wtmpx");
	//保存解析后日志的文件
	private File textLogFile = new File("log.txt");
	//书签文件
	private File lastPositionFile = new File("last-position.txt");
	//每次解析日志的条目数
	private int batch = 10;
	/**
	 * 该方法为了配合parseLogs中的一个判断，无需修改该方法	
	 * @return
	 */
	private long hasLogs(){
		return 0;
	}	
	/**
	 * 第一步:解析日志
	 * @return true:解析成功
	 *         false:解析失败
	 */
	private boolean parseLogs(){
		/*
		 * 实现要求:
		 * 从logFile表示的文件中循环读取batch条日志，
		 * 然后将每条日志中的5个信息解析出来并以一个LogData实例
		 * 表示，最终将这些LogData对象的toString返回的字符串按
		 * 行写入到textLogFile表示的文件中
		 * 
		 * 实现步骤：
		 * 1:必要的判断工作
		 *   1.1:为了避免解析的日志还没有被使用，而
		 *       第一步又重复执行导致之前日志被覆盖
		 *       的问题，这里需要判断，若保存解析后
		 *       的日志文件(textLogFile)存在，则第
		 *       一步不再执行。该日志文件会在第二步
		 *       配对完毕后删除。所以textLogFile文
		 *       件若存在，则直接返回true,无需重复解析。
		 *   1.2:logFile文件必须存在(wtmpx文件)
		 *   1.3:是否还有日志可以解析
		 *       先根据hasLog将上次最后读取的位置读取出来，
		 *       然后判断该位置的值，若返回值>=0即说明有日
		 *       志可以解析
		 * 2:创建RandomAccessFile来读取logFile
		 * 3:将指针移动到上次最后读取的位置，准备
		 *   开始新的解析工作
		 * 4:解析工作
		 *   4.1:创建一个List集合，用于保存解析后
		 *       的每一条日志(LogData实例)
		 *   4.2:循环batch次，解析每条日志中的
		 *       5项内容(user,pid,type,time,host)
		 *       并用一个LogData实例保存，然后将
		 *       该LogData实例存入集合
		 *       注:读取字符串使用IOUtil的readString方法
		 * 5:将集合中的所有的日志以行为单位保存到
		 *   textLogFile中 
		 *   注:使用IOUtil的saveCollection方法
		 * 6:保存书签信息   
		 *   注:使用IOUtil的saveLong方法      
		 * 7:返回true,表示工作完毕
		 * 
		 */
		RandomAccessFile raf = null;
		try {
			if(!logFile.exists()){
				return false;
			}
			if(textLogFile.exists()){
				return true;
			}
			
			long lastlocation = hasLogs();
			
			if(lastlocation<0){
				return false;
			}
			List<LogData> list= new ArrayList<LogData>();
			for(int i = 0;i<batch;i++){
				if((logFile.length()-lastlocation)<LogData.LOG_LENGTH){
					break;
				}
				raf = new RandomAccessFile(logFile, "r");
				raf.seek(lastlocation+LogData.USER_OFFSET);
				String user = IOUtil.readString(raf, LogData.USER_LENGTH).trim();
				raf.seek(lastlocation+LogData.PID_OFFSET);
				int pid = raf.readInt();
				raf.seek(lastlocation+LogData.TYPE_OFFSET);
				short type = raf.readShort();
				raf.seek(lastlocation+LogData.TIME_OFFSET);
				int time = raf.readInt();
				raf.seek(lastlocation+LogData.HOST_OFFSET);
				String host = IOUtil.readString(raf, LogData.HOST_LENGTH).trim();
				LogData logdata = new LogData(user, pid, type, time, host);
				list.add(logdata);
				lastlocation = raf.getFilePointer();
			}
			IOUtil.saveCollection(list, textLogFile);
			IOUtil.saveLong(lastlocation, lastPositionFile);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(raf!=null){
				try {
					raf.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
			
		
		return false;
	}
	/**
	 * 当全部完成后，运行DMSClient类，项目中应当
	 * 会出现两个文件:
	 * 1:last-position.txt，内容为:3720
	 * 2:log.txt,内容为10条日志信息
	 * @param args
	 */
	public static void main(String[] args) {
		Part2 p = new Part2();
		if(p.parseLogs()){
			System.out.println("解析完毕!");
		}else{
			System.out.println("解析失败!");
		}
		
	}
}
