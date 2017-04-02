package com.logistics.mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.logistics.idal.IUserMapper;
import com.logistics.model.UserInfo;
import com.logistics.mybatisutil.MyBatisUtil;

public class UserMapper {
 //调用我的sqlsession
	SqlSession session = MyBatisUtil.getsqlSession(true);
	//得到的UserMapperI 的接口实现类对象，UserMapperI 接口的实现类对象由sqlsession
	IUserMapper mapper = session.getMapper(IUserMapper.class);
	//IUserMapper mapper = (IUserMapper)session.getMapper();
	
	//实现方法
	//判断用户登录，返回登录成功的用户对象
	public UserInfo validateLogin(String username,String password)
	{
		//用户密码进行加密
		UserInfo u = mapper.validateLogin(username, password);
		return u;
	}
	
	//获取用户列表
	public List<UserInfo> getUserinfo()
	{
		List<UserInfo> userlist =mapper.getUserList(); 
		return userlist;
	}
	
	//存储图片
	public boolean logisticsimg(String imgnum)
	{
		SqlSession session = MyBatisUtil.getsqlSession(true);
		//得到的UserMapperI 的接口实现类对象，UserMapperI 接口的实现类对象由sqlsession
		IUserMapper mapper = session.getMapper(IUserMapper.class);
		
		int s = mapper.logisticsimg(imgnum);
		if(s>0)
		{
			System.out.println("图片存储成功");
			return true;
			
		}else {
			System.out.println("图片存储失败");
			return false;
		}
		
	}
}
