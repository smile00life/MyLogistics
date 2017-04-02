package com.logistics.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderInfo {

	private int order_id;
	private String sender_user;
	private String receiver_user;
	private String goods_name;
	private String img_address;
	private String city_name;
	private String area_name;
	private String receiver_tel;
	private String receiver_address;
	private String receiver_employee;
	private String sender_employee;
	private Date create_time;
	private String order_code;
	private String order_pay;
	private String receiver_post;
	private String order_money;
	
	
	//付款状态
	private int orderpay_id;
	private String pay_status;
	private String pay_code;
	
	
	//商品信息
	private int goods_id;
	private String goods_content;
	private String goods_weight;
	private String goods_type;
	
	public String getOrder_money() {
		return order_money;
	}
	public void setOrder_money(String order_money) {
		this.order_money = order_money;
	}
	public int getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}
	public String getGoods_content() {
		return goods_content;
	}
	public void setGoods_content(String goods_content) {
		this.goods_content = goods_content;
	}
	public String getGoods_weight() {
		return goods_weight;
	}
	public void setGoods_weight(String goods_weight) {
		this.goods_weight = goods_weight;
	}
	public String getGoods_type() {
		return goods_type;
	}
	public void setGoods_type(String goods_type) {
		this.goods_type = goods_type;
	}
	public String getReceiver_post() {
		return receiver_post;
	}
	public void setReceiver_post(String receiver_post) {
		this.receiver_post = receiver_post;
	}
	public int getOrderpay_id() {
		return orderpay_id;
	}
	public void setOrderpay_id(int orderpay_id) {
		this.orderpay_id = orderpay_id;
	}
	public String getPay_status() {
		return pay_status;
	}
	public void setPay_status(String pay_status) {
		this.pay_status = pay_status;
	}
	public String getPay_code() {
		return pay_code;
	}
	public void setPay_code(String pay_code) {
		this.pay_code = pay_code;
	}
	public String getOrder_pay() {
		return order_pay;
	}
	public void setOrder_pay(String order_pay) {
		this.order_pay = order_pay;
	}
	public String getOrder_code() {
		return order_code;
	}
	public void setOrder_code(String order_code) {
		this.order_code = order_code;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public String getSender_user() {
		return sender_user;
	}
	public void setSender_user(String sender_user) {
		this.sender_user = sender_user;
	}
	public String getReceiver_user() {
		return receiver_user;
	}
	public void setReceiver_user(String receiver_user) {
		this.receiver_user = receiver_user;
	}
	
	public String getReceiver_employee() {
		return receiver_employee;
	}
	public void setReceiver_employee(String receiver_employee) {
		this.receiver_employee = receiver_employee;
	}
	public String getSender_employee() {
		return sender_employee;
	}
	public void setSender_employee(String sender_employee) {
		this.sender_employee = sender_employee;
	}
	//修改时间显示格式
	public String getCreate_time() {
		SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		return smf.format(create_time);
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public String getImg_address() {
		return img_address;
	}
	public void setImg_address(String img_address) {
		this.img_address = img_address;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	public String getArea_name() {
		return area_name;
	}
	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}
	
	public String getReceiver_tel() {
		return receiver_tel;
	}
	public void setReceiver_tel(String receiver_tel) {
		this.receiver_tel = receiver_tel;
	}
	public String getReceiver_address() {
		return receiver_address;
	}
	public void setReceiver_address(String receiver_address) {
		this.receiver_address = receiver_address;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
}
