package com.logistics.model;

public class MenuInfo {

	private int role_id;
	private String role_name;
	private String role_num;
	private int menu_id;
	private int upMenuId;
	private String menuContent;
	private String menuPath;
	private int menuOrder;
	private int isBlank;
	private int rangeId;
	public int getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}
	public int getUpMenuId() {
		return upMenuId;
	}
	public void setUpMenuId(int upMenuId) {
		this.upMenuId = upMenuId;
	}
	public String getMenuContent() {
		return menuContent;
	}
	public void setMenuContent(String menuContent) {
		this.menuContent = menuContent;
	}
	public String getMenuPath() {
		return menuPath;
	}
	public void setMenuPath(String menuPath) {
		this.menuPath = menuPath;
	}
	public int getMenuOrder() {
		return menuOrder;
	}
	public void setMenuOrder(int menuOrder) {
		this.menuOrder = menuOrder;
	}
	public int getIsBlank() {
		return isBlank;
	}
	public void setIsBlank(int isBlank) {
		this.isBlank = isBlank;
	}
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getRole_num() {
		return role_num;
	}
	public void setRole_num(String role_num) {
		this.role_num = role_num;
	}
	public void setRangeId(int rangeId)
	{
		this.rangeId= rangeId;
	}
	public int getRangeId()
	{
		return rangeId;
	}
}
