package com.dms.bo;

public class LogData {
	
	/**
	 * 日志的长度(字节量)
	 */
	public static final int LOG_LENGTH = 372;
	
	/**
	 * user在一条日志中的起始位置
	 */
	public static final int USER_OFFSET = 0;
	
	/**
	 * user在一条日志中的长度(字节量)
	 */
	public static final int USER_LENGTH = 32;
	
	/**
	 * pid在一条日志中的起始位置
	 */
	public static final int PID_OFFSET = 68;
	
	/**
	 * type在一条日志中的起始位置
	 */
	public static final int TYPE_OFFSET = 72;
	
	/**
	 * time在一条日志中的起始位置
	 */
	public static final int TIME_OFFSET = 80;
	
	/**
	 * host在一条日志中的起始位置
	 */
	public static final int HOST_OFFSET = 114;
	
	/**
	 * host在一条日志中的长度(字节量)
	 */
	public static final int HOST_LENGTH = 258;
	
	/**
	 * 日志类型:登入日志
	 */
	public static final short TYPE_LOGIN = 7;
	
	/**
	 * 日志类型:登入日志
	 */
	public static final short TYPE_LOGOUT = 8;
	
	private String user;
	
	private int pid;
	
	private short type;
	
	private int time;
	
	private String host;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public short getType() {
		return type;
	}

	public void setType(short type) {
		this.type = type;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public LogData(String user, int pid, short type, int time, String host) {
		super();
		this.user = user;
		this.pid = pid;
		this.type = type;
		this.time = time;
		this.host = host;
	}

	
	public String toString() {
		return user+","+pid+","+type+","+time+","+host;
	}
	
	
}
