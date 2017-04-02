package com.logistics.mapper;

import org.apache.ibatis.session.SqlSession;

import com.logistics.idal.ISystemLog4jMapper;
import com.logistics.model.SystemLog4jInfo;
import com.logistics.mybatisutil.MyBatisUtil;

//添加日志信息
public class SystemLog4jMapper {

	SqlSession session = MyBatisUtil.getsqlSession(true);
	ISystemLog4jMapper mapper = session.getMapper(ISystemLog4jMapper.class);
	public boolean getCreateSystemLog(SystemLog4jInfo log) {
		
		int rows = mapper.getCreateSystemLog(log);
		if(rows>0)
		{
			return true;
		}else {
			return false;
		}
		
	}
}
