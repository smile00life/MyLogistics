package com.logistics.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SystemLog4jInfo {

	private int log_id;
	private String log_message;
	private String log_user;
	private Date write_time;
	private String ip_addr;
	public String getIp_addr() {
		return ip_addr;
	}
	public void setIp_addr(String ip_addr) {
		this.ip_addr = ip_addr;
	}
	public int getLog_id() {
		return log_id;
	}
	public void setLog_id(int log_id) {
		this.log_id = log_id;
	}
	public String getLog_message() {
		return log_message;
	}
	public void setLog_message(String log_message) {
		this.log_message = log_message;
	}
	public String getLog_user() {
		return log_user;
	}
	public void setLog_user(String log_user) {
		this.log_user = log_user;
	}
	public String getWrite_time() {
		//定义时间显示格式
		SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd hh:mm");
		return format.format(write_time);
	}
	public void setWrite_time(Date write_time) {
		this.write_time = write_time;
	}
}
