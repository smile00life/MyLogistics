package com.logistics.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DepartmentInfo {

	private int department_id;
	private String department_name;
	private String city_name;
	private String department_code;
	private String department_address;
	private String district_name;
	private int manager_id;
	
	
	//ls_centerranger中的信息
	private int ranger_id;
	
	
	
	public int getRanger_id() {
		return ranger_id;
	}
	public void setRanger_id(int ranger_id) {
		this.ranger_id = ranger_id;
	}
	public String getDistrict_name() {
		return district_name;
	}
	public void setDistrict_name(String district_name) {
		this.district_name = district_name;
	}
	private String department_tel;
	private String department_upId;
	
	
	public String getDepartment_upId() {
		return department_upId;
	}
	public void setDepartment_upId(String department_upId) {
		this.department_upId = department_upId;
	}
	//设置时间显示格式
	public String getWrite_time() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm");
		return sdf.format(write_time);
		//return write_time;
	}
	public void setWrite_time(Date write_time) {
		this.write_time = write_time;
	}
	private String department_mobile;
	private String department_manager;
	private Date write_time;
	public String getDepartment_manager() {
		return department_manager;
	}
	public void setDepartment_manager(String department_manager) {
		this.department_manager = department_manager;
	}
	
	public int getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	public String getDepartment_code() {
		return department_code;
	}
	public void setDepartment_code(String department_code) {
		this.department_code = department_code;
	}
	public String getDepartment_address() {
		return department_address;
	}
	public void setDepartment_address(String department_address) {
		this.department_address = department_address;
	}
	
	public String getDepartment_tel() {
		return department_tel;
	}
	public void setDepartment_tel(String department_tel) {
		this.department_tel = department_tel;
	}
	public String getDepartment_mobile() {
		return department_mobile;
	}
	public void setDepartment_mobile(String department_mobile) {
		this.department_mobile = department_mobile;
	}
	public int getManager_id() {
		return manager_id;
	}
	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}
}
