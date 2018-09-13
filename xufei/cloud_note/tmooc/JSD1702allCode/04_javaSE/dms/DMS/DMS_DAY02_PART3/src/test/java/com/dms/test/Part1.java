package com.dms.test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.dms.IOUtil;
import com.dms.bo.LogData;
import com.dms.bo.LogRec;

/**
 * 该测试用例是用于测试DMSClient的
 * 第二步配对日志的方法
 * 当前测试用例中的属性都可以在DMSClient中找到
 * @author Xiloer
 *
 */
public class Part1 {
	//属性定义
	//保存解析后日志的文件
	private File textLogFile = new File("src/test/java/com/dms/test/log.txt");
	//第二步:配对日志所需要属性
	//保存配对日志的文件
	private File logRecFile = new File("src/test/java/com/dms/test/logrec.txt");;
	//保存未配对日志的文件
	private File loginLogFile = new File("src/test/java/com/dms/test/login.txt");;
	
	/**
	 * 第二步:配对日志
	 * @return true:配对成功
	 *         false:配对失败
	 */
	private boolean matchLogs(){
		try {
			/*
			 * 实现要求:
			 * 将第一步解析的新日志(textLogFile中记录)，与上次为配对成功
			 * 的登入日志(loginLogFile中记录)全部读取出来，然后再按照user,
			 * pid相同，type一个是7，一个是8进行配对。
			 * 注:只要能找到类型为8的，一定可以找到一个
			 *    能与之配对的登入日志。
			 * 将配对成功的日志存入logRecFile表示的文件中
			 * 将未配对的日志存入loginLogFile表的文件中   
			 * 成功配对后，需要将第一步生成的textLogFile文件
			 * 删除，以表示第二步成功。   
			 * 
			 * 实现步骤:
			 * 1:必要的判断
			 *   1.1:logRecFile是否存在，存在则不再
			 *       进行新的配对工作，避免覆盖。
			 *   1.2:textLogFile文件必须存在。    
			 * 2:读取textLogFile将日志读取出来，并
			 *   存入到集合中。(若干LogData实例)
			 *   注:使用IOUtil.loadLogData方法。
			 * 3:若loginLogFile文件若存在，则说明
			 *   有上次未配对成功的日志，也将其读取
			 *   出来存入步骤2的集合等待一起配对
			 *   注:使用IOUtil.loadLogData方法。
			 * 4:配对工作
			 *   4.1:创建一个集合，用于保存所有配对日志
			 *   4.2:创建两个Map分别保存登入日志与登出日志
			 *   4.3:遍历所有待配对的日志，按照登入与登出
			 *       分别存入两个Map中，
			 *       其中key:user,pid
			 *           value:LogData实例
			 *   4.4:遍历登出Map,并根据每条登出日志的key
			 *       去登入Map中找到对应的登入日志，并
			 *       以一个LogRec实例保存该配对日志，然后
			 *       存入配对日志的集合中。并将该配对日志
			 *       中的登入日志从登入Map中删除。这样一来
			 *       登入Map中应当只剩下没有配对的了。
			 *  5:将配对日志写入到logRecFile中
			 *    注:使用IOUtil.saveCollection方法
			 *  6:将所有未配对日志写入到loginLogFile中
			 *    注:使用IOUtil.saveCollection方法
			 *  7:将textLogFile文件删除
			 *  8:返回true,表示配对完毕
			 * 
			 */
			
				if(logRecFile.exists()){
					return true;
				}
				if(!textLogFile.exists()){
					return false;
				}
				List<LogData> logDataList= IOUtil.loadLogData(textLogFile);
				List<String> matchedDataList = new ArrayList<String>();
				if(loginLogFile.exists()){
					List<LogData> addedlist = IOUtil.loadLogData(loginLogFile);
					logDataList.addAll(addedlist);
				}
				Map<String,LogData> loginMap= new HashMap<String, LogData>();
				Map<String,LogData> logoutMap = new HashMap<String, LogData>();
				for(LogData data:logDataList){
					if(data.getType()==8){
						logoutMap.put(data.getUser()+data.getPid(), data);
					}
					if(data.getType()==7){
						loginMap.put(data.getUser()+data.getPid(), data);
					}
				}
				Set<String> InkeySet = loginMap.keySet();
				Set<String> OutkeySet = logoutMap.keySet();
				for(String out:OutkeySet){
					for(String in:InkeySet){
						if(out.equals(in)){
							matchedDataList.add(loginMap.get(in).toString()+"|"+logoutMap.get(out).toString());
							logDataList.remove(loginMap.get(in));
							logDataList.remove(logoutMap.get(out));
							break;
						}
					}
				}
				IOUtil.saveCollection(matchedDataList, logRecFile);
				IOUtil.saveCollection(logDataList, loginLogFile);
				textLogFile.delete();
			 
		} catch (Exception e) {
			System.out.println("配对日志失败!");
			e.printStackTrace();
		}
		return false;
	}
	public static void main(String[] args) {
		try {
			Part1 p = new Part1();
			p.matchLogs();
			System.out.println("执行完毕");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
