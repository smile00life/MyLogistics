package com.logistics.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CostInfo {

	private int cost_id;
	private String city_name;
	private String cost_price;
	private String cost_per;
	private Date write_time;
	private String city_code;
	
	
	public String getCity_code() {
		return city_code;
	}
	public void setCity_code(String city_code) {
		this.city_code = city_code;
	}
	//改变当前时间显示格式
	public String getWrite_time() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm");
		return sdf.format(write_time);
		//return write_time;
	}
	public void setWrite_time(Date write_time) {
		this.write_time = write_time;
	}
	public int getCost_id() {
		return cost_id;
	}
	public void setCost_id(int cost_id) {
		this.cost_id = cost_id;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	public String getCost_price() {
		return cost_price;
	}
	public void setCost_price(String cost_price) {
		this.cost_price = cost_price;
	}
	public String getCost_per() {
		return cost_per;
	}
	public void setCost_per(String cost_per) {
		this.cost_per = cost_per;
	}
	
}
