package com.logistics.idal;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;

import com.logistics.model.SystemInfo;
import com.logistics.model.UserInfo;

public interface ISystemMapper {

	//查询所有公司信息,list列形式
	@Select("CALL getSystemInfo()")
	@Options(statementType = StatementType.CALLABLE)
	public List<SystemInfo> getSystemInfo();
	
	//查询所有公司信息，一行
	@Select("CALL getSystemInfo()")
	@Options(statementType = StatementType.CALLABLE)
	public SystemInfo getSystemInfos();
	
	//根据Id查看公司信息
	@Select("CALL getSystemById(#{0})")
	@Options(statementType = StatementType.CALLABLE)
	public SystemInfo getSystemById(int company_id);
	
	//修改公司信息
	@Update("CALL getUpdateSystem(#{company_name},#{company_code},#{company_license},#{company_address},#{company_tel},#{company_content},#{company_id})")
	@Options(statementType = StatementType.CALLABLE)
	public int getUpdateSystem(SystemInfo sf);
	
	//修改密码
	@Update("CALL getPassUser(#{newpassword},#{username})")
	@Options(statementType = StatementType.CALLABLE)
	public int getUserPass(UserInfo user);
	
	//判断密码是否正确
	@Select("CALL getPassOK(#{username},#{password})")
	@Options(statementType = StatementType.CALLABLE)
	public int getPassOk(UserInfo user);
	
}
