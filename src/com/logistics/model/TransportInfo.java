package com.logistics.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TransportInfo {

	private int transport_id;   //编号
	private String transport_name;  //车辆名称
	private String transport_type;   //车辆类型
	private String plate_number;   //车牌号
	private String transport_driver;  //司机
	private String transport_info;  //车辆详细信息
	private Date write_time;
	public String getWrite_time() {
		SimpleDateFormat smp = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		
		return smp.format(write_time);
	}
	public void setWrite_time(Date write_time) {
		this.write_time = write_time;
	}
	public String getTransport_info() {
		return transport_info;
	}
	public void setTransport_info(String transport_info) {
		this.transport_info = transport_info;
	}
	public int getTransport_id() {
		return transport_id;
	}
	public void setTransport_id(int transport_id) {
		this.transport_id = transport_id;
	}
	public String getTransport_name() {
		return transport_name;
	}
	public void setTransport_name(String transport_name) {
		this.transport_name = transport_name;
	}
	public String getTransport_type() {
		return transport_type;
	}
	public void setTransport_type(String transport_type) {
		this.transport_type = transport_type;
	}
	public String getPlate_number() {
		return plate_number;
	}
	public void setPlate_number(String plate_number) {
		this.plate_number = plate_number;
	}
	public String getTransport_driver() {
		return transport_driver;
	}
	public void setTransport_driver(String transport_driver) {
		this.transport_driver = transport_driver;
	}
	
}
