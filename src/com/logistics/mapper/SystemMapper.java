package com.logistics.mapper;

import java.util.List;

import javax.swing.JOptionPane;

import org.apache.ibatis.session.SqlSession;

import com.logistics.idal.ISystemMapper;
import com.logistics.model.SystemInfo;
import com.logistics.model.UserInfo;
import com.logistics.mybatisutil.MyBatisUtil;

public class SystemMapper {

	
	//查询所有公司信息,list列
	public List<SystemInfo> getSystemInfo()
	{
		//提交事物
		SqlSession session = MyBatisUtil.getsqlSession(true);
		ISystemMapper mapper = session.getMapper(ISystemMapper.class);
		
		List<SystemInfo> sf = mapper.getSystemInfo();
		if(sf.size()==0)
		{
			System.out.println("公司信查找改失败！");
			return null;
			
		}else {
			session.close();
			System.out.println("公司信查找改成功！");
			return sf;
			
		}
	}
	//查询所有公司信息,单个
	public SystemInfo getSystemInfos()
	{
		//提交事物
		SqlSession session = MyBatisUtil.getsqlSession(true);
		ISystemMapper mapper = session.getMapper(ISystemMapper.class);
		
		SystemInfo sf = mapper.getSystemInfos();
		
		session.close();
		System.out.println("公司信查找改成功！");
		return sf;
			
		
	}
	
	
	
	//根据id查找公司信息
	public SystemInfo getSystemById(int company_id)
	{
		
		SqlSession session = MyBatisUtil.getsqlSession(true);
		ISystemMapper mapper = session.getMapper(ISystemMapper.class);
		
		SystemInfo sf = mapper.getSystemById(company_id);
		if(sf==null)
		{
			System.out.println("根据ID公司信查找改失败！");
			return null;
			
		}else {
			session.close();
			System.out.println("根据ID公司信查找改成功！");
			return sf;
			
		}
	}
	
	//修改公司信息
	public boolean getUpdateSystem(SystemInfo sf)
	{
		boolean flg = false;
		SqlSession session = MyBatisUtil.getsqlSession(true);
		ISystemMapper mapper = session.getMapper(ISystemMapper.class);
		
		int rows = mapper.getUpdateSystem(sf);
		if(rows>0)
		{
			flg=true;
			System.out.println("公司信息修改成功！");
		}else {
			flg=false;
		}
		return flg;
	}
	
	
	//修改密码
	public boolean updatePass(UserInfo user)
	{
		SqlSession session = MyBatisUtil.getsqlSession(true);
		ISystemMapper mapper = session.getMapper(ISystemMapper.class);
		
			int rows = mapper.getUserPass(user);
			if(rows>0)
			{
				System.out.println("密码修改成功！sysmapper");
				session.close();
				return true;
			}else {
				System.out.println("密码修改失败！sysmapper");
				return false;
			}
	}
	//判断密码是否正确
	public boolean getPassOk(UserInfo user)
	{
		SqlSession session = MyBatisUtil.getsqlSession(true);
		ISystemMapper mapper = session.getMapper(ISystemMapper.class);
		
		int rows = mapper.getPassOk(user);
		if(rows>0)
		{
			System.out.println("密码验证成功！sysmapper");
			session.close();
			return true;
		}
		else {
			System.out.println("密码验证失败！sysmapper");
			return false;
		}
	}
}
