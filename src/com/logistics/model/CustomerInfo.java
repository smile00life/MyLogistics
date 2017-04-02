package com.logistics.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomerInfo {

	private int customer_id;
	private String customer_name;
	private String customer_tel;
	private String customer_mobile;
	private String customer_address;
	private String customer_code;
	private int order_id;
	private Date write_time;
	
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getCustomer_tel() {
		return customer_tel;
	}
	public void setCustomer_tel(String customer_tel) {
		this.customer_tel = customer_tel;
	}
	public String getCustomer_mobile() {
		return customer_mobile;
	}
	public void setCustomer_mobile(String customer_mobile) {
		this.customer_mobile = customer_mobile;
	}
	public String getCustomer_address() {
		return customer_address;
	}
	public void setCustomer_address(String customer_address) {
		this.customer_address = customer_address;
	}
	public String getCustomer_code() {
		return customer_code;
	}
	public void setCustomer_code(String customer_code) {
		this.customer_code = customer_code;
	}
	
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	//修改获取方法，改变时间显示格式
	public String getWrite_time() {
		SimpleDateFormat fore = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		
		return fore.format(write_time);
	}
	public void setWrite_time(Date write_time) {
		this.write_time = write_time;
	}
}
