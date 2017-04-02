package com.logistics.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SystemInfo {

	private int company_id;
	private String company_name;
	private String company_code;
	private String company_license;
	private String company_address;
	private String company_tel;
	private String company_content;
	private Date write_time;
	
	//修改获取方法，改变时间显示格式
	public String getWrite_time() {
		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		
		return time.format(write_time);
	}
	public void setWrite_time(Date write_time) {
		this.write_time = write_time;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getCompany_code() {
		return company_code;
	}
	public void setCompany_code(String company_code) {
		this.company_code = company_code;
	}
	public String getCompany_license() {
		return company_license;
	}
	public void setCompany_license(String company_license) {
		this.company_license = company_license;
	}
	public String getCompany_address() {
		return company_address;
	}
	public void setCompany_address(String company_address) {
		this.company_address = company_address;
	}
	public String getCompany_tel() {
		return company_tel;
	}
	public void setCompany_tel(String company_tel) {
		this.company_tel = company_tel;
	}
	public String getCompany_content() {
		return company_content;
	}
	public void setCompany_content(String company_content) {
		this.company_content = company_content;
	}
	
	
}
