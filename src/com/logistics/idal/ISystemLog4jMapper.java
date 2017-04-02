package com.logistics.idal;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.mapping.StatementType;

import com.logistics.model.SystemLog4jInfo;

public interface ISystemLog4jMapper {

	//添加日志信息
	@Insert("CALL getCreateSystemLog4j(#{log_message},#{log_user},#{ip_addr})")
	@Options(statementType = StatementType.CALLABLE)
	public int getCreateSystemLog(SystemLog4jInfo log);
}
