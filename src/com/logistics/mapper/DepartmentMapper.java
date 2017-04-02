package com.logistics.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.session.SqlSession;

import com.logistics.idal.IAnnounceMapper;
import com.logistics.idal.ICityMapper;
import com.logistics.idal.IDepartmentMapper;
import com.logistics.model.AnnounceInfo;
import com.logistics.model.CityInfo;
import com.logistics.model.DepartmentInfo;
import com.logistics.model.UserInfo;
import com.logistics.mybatisutil.MyBatisUtil;

public class DepartmentMapper {

	//获取公告列表总行数
		public int getDepartmentCount(){
			
			SqlSession session = MyBatisUtil.getsqlSession(true);
		    // 得到ICityMapper接口的实现类对象，ICityMapper接口的实现类对象由sqlSession.getMapper(ICityMapper.class)动态构建出来
		    IDepartmentMapper mapper = session.getMapper(IDepartmentMapper.class);
			int row= mapper.getDepartmentCount();
			session.close();
			return row;
		}
		
		//获取公告列表信息
		public List<DepartmentInfo> getDepartmentList(Map<String,Object> map){
			//String s=(String)map.get("startIndex");
			//String p=(String)map.get("pageSize");
			SqlSession session = MyBatisUtil.getsqlSession(true);
		    // 得到ICityMapper接口的实现类对象，ICityMapper接口的实现类对象由sqlSession.getMapper(ICityMapper.class)动态构建出来
		    IDepartmentMapper mapper = session.getMapper(IDepartmentMapper.class);
		    
			List<DepartmentInfo> annlist =mapper.getDepartmentList(Integer.parseInt(map.get("startIndex").toString()),Integer.parseInt(map.get("pageSize").toString()));

			session.close();
			return annlist;
		}
		
		//获取城市列表
		public List<CityInfo> getCityInfo()
		{
			SqlSession session = MyBatisUtil.getsqlSession(true);
		    // 得到ICityMapper接口的实现类对象，ICityMapper接口的实现类对象由sqlSession.getMapper(ICityMapper.class)动态构建出来
		    IDepartmentMapper mapper = session.getMapper(IDepartmentMapper.class);
			List<CityInfo> citylist = mapper.getCityInfo();
			if(citylist==null)
			{
				System.out.println("列表获取失败！");
				return null;
			}else {
				session.close();
				System.out.println("列表获取成功！");
				return citylist;
			}
			
		}
		
		//根据选择的城市，选择区县
		//查询二级菜单
		public List<CityInfo> getAreaCityInfo(int city_id)
		{
			SqlSession session = MyBatisUtil.getsqlSession(true);
		    // 得到ICityMapper接口的实现类对象，ICityMapper接口的实现类对象由sqlSession.getMapper(ICityMapper.class)动态构建出来
		    IDepartmentMapper mapper = session.getMapper(IDepartmentMapper.class);
			
			List<CityInfo> arealist = mapper.getAreaInfo(city_id);
			if(arealist==null)
			{
				System.out.println("二级列表获取失败！");
				return null;
			}else {
				session.close();
				System.out.println("二级列表获取成功！");
				return arealist;
			}
		}
		
		// 获取未分配二级分拣中心管理权限的二级管理员列表
		public List<UserInfo> getSetCenterManagerList() {
			SqlSession session = MyBatisUtil.getsqlSession(true);
		    // 得到ICityMapper接口的实现类对象，ICityMapper接口的实现类对象由sqlSession.getMapper(ICityMapper.class)动态构建出来
		    IDepartmentMapper mapper = session.getMapper(IDepartmentMapper.class);
	        return mapper.getSetCenterManagerList();
		}
		//获取ls_centerranger中的ranger_id
		public int getCenterRanger(int manager_id)
		{
			SqlSession session = MyBatisUtil.getsqlSession(true);
		    // 得到ICityMapper接口的实现类对象，ICityMapper接口的实现类对象由sqlSession.getMapper(ICityMapper.class)动态构建出来
		    IDepartmentMapper mapper = session.getMapper(IDepartmentMapper.class);
		    return mapper.getCenterRanger(manager_id);	
		}
		
		
		//新增分拣中心
		public boolean createDepartmentCenter(DepartmentInfo df)
		{
			SqlSession session = MyBatisUtil.getsqlSession(true);
			IDepartmentMapper mapper = session.getMapper(IDepartmentMapper.class);
			
			int rows = mapper.getDepartmentCenter(df);
			
			if(rows>0)
			{
				return true;
			}else {
				return false;
			}
			
		}
		
		//修改二级分拣中心信息
		public boolean getUpdateDepartment(DepartmentInfo df)
		{
			SqlSession session = MyBatisUtil.getsqlSession(true);
			IDepartmentMapper mapper = session.getMapper(IDepartmentMapper.class);
			
			int rows = mapper.getUpdateDepartment(df);
			if(rows>0)
			{ 
				session.close();
				return true;
			}else {
				return false;
			}
			
			
		}
		
		//查看二级分拣中心信息
		public DepartmentInfo getDepartmentById(int department_id)
		{
			SqlSession session = MyBatisUtil.getsqlSession(true);
			IDepartmentMapper mapper = session.getMapper(IDepartmentMapper.class);
			
			DepartmentInfo deinfo = mapper.getDepartmentById(department_id);
			
			if(deinfo == null)
			{
				return null;
			}else {
				session.close();
				return deinfo;
			}	
		}
		//删除二级分拣中心信息
		public boolean getDelDepartment(int department_id)
		{
			SqlSession session = MyBatisUtil.getsqlSession(true);
			IDepartmentMapper mapper = session.getMapper(IDepartmentMapper.class);
			int rows = mapper.getDelDepartment(department_id);
			if(rows>0)
			{
				session.close();
				//System.out.println("");
				return true;
			}
			else {
				return false;
			}	
		}
}
