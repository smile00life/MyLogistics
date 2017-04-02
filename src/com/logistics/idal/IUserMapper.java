package com.logistics.idal;



import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.logistics.model.UserInfo;

public interface IUserMapper {

	//判断用户登录，返回登录成功的用户对象
	//注解调用存储过程
	//@Select("select * from logisticsLogin where username=#{username} and password=#{passowrd}")
	@Select("CALL getlogin(#{0},#{1})")
	//设置类型call为可用
	@Options(statementType = StatementType.CALLABLE)
	public UserInfo validateLogin(String username,String password);
	
	//获取用户列表
	//@Select("select * from logisticsLogin")
	@Select("CALL getuserlist()")
	@Options(statementType = StatementType.CALLABLE)
	public List<UserInfo> getUserList();
	
	
	
	//增加数据库存储
	@Insert("insert into logisticsImg(imgnum) values(#{imgnum})")
	public int logisticsimg(String imgnum);
	
}
