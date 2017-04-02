package com.logistics.mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.logistics.idal.ICityMapper;
import com.logistics.model.CityInfo;
import com.logistics.mybatisutil.MyBatisUtil;

public class CityMapper {

	
	
	//获取城市列表信息
	//查询一级菜单
	public List<CityInfo> getCityInfo()
	{
		SqlSession session = MyBatisUtil.getsqlSession(true);
		ICityMapper cityMapper = session.getMapper(ICityMapper.class);
		List<CityInfo> citylist = cityMapper.getCityInfo();
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
		ICityMapper cityMapper = session.getMapper(ICityMapper.class);
		
		List<CityInfo> arealist = cityMapper.getAreaInfo(city_id);
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
	
	
	
	
	
}
