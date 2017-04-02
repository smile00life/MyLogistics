package com.logistics.idal;



import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.logistics.model.MenuInfo;

public interface IMenuMapper {

	//设置后台首页管理
	//设置对应的权限检索出顶部导航标题
	//注解调用存储过程
	@Select("CALL getTopMenu(#{role_id})")
	//设置类型为CALL可用
	@Options(statementType = StatementType.CALLABLE)
	public List<MenuInfo> getTopMenu(int role_id);
	
	
	//设置二级管理页面
	@Select("CALL getSecondMenu(#{0},#{1})")
	@Options(statementType = StatementType.CALLABLE)
	public List<MenuInfo> getSecondMenu(int role_id,int upMenuId);
	
	
	/*目录权限验证
	 * 参数两个
	 * role_id角色
	 * validateMenuPath路径地址
	 * 返回count ，在实现里做true或false，判断或者写存储过程，返回true或false    mysql不支持boolean
	 * 所以选第一种
	 * 
	 * */
	@Select("CALL validateMenu(#{0},#{1})")
	@Options(statementType = StatementType.CALLABLE)
	public int validateMenu(int role_id,String validateMenuPath);
	
}
