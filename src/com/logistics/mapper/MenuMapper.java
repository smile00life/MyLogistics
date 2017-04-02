package com.logistics.mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.logistics.idal.IMenuMapper;
import com.logistics.model.MenuInfo;
import com.logistics.mybatisutil.MyBatisUtil;

public class MenuMapper {

	
	//后台首页菜单管理
	public List<MenuInfo> getTopMenu(int role_id)
	{
		SqlSession session = MyBatisUtil.getsqlSession(true);
		
		IMenuMapper mapper = session.getMapper(IMenuMapper.class);
		
		List<MenuInfo> menulist = mapper.getTopMenu(role_id);
		if(menulist==null)
		{
			//System.out.println("首页菜单查询失败！MenuMapper");
			return null;
		}else {
			System.out.println("首页菜单查询成功！MenuMapper");
			session.close();
			return menulist;
		}
		
	}
	
	//二级管理目录菜单管理
	//管理首页下
	public List<MenuInfo> getSecondMenu(int role_id,int upMenuId)
	{
        SqlSession session = MyBatisUtil.getsqlSession(true);
		
		IMenuMapper mapper = session.getMapper(IMenuMapper.class);
		
		List<MenuInfo> siderlist=mapper.getSecondMenu(role_id, upMenuId);
		
		if(siderlist==null)
		{
			//System.out.println("二级菜单查询失败！MenuMapper");
			return null;
		}else {
			//System.out.println("二级菜单查询成功！MenuMapper");
			session.close();
			return siderlist;
		}
	}
	
	
	//角色权限判定
	public boolean validateMenuPath(int role_id,String validateMenuPath)
	{
        SqlSession session = MyBatisUtil.getsqlSession(true);
		
		IMenuMapper mapper = session.getMapper(IMenuMapper.class);
		
		int row = mapper.validateMenu(role_id, validateMenuPath);
		if(row>0)
		{
			System.out.println();
			return true;
		}else {
			return false;
		}
		
	}
	
	
}
