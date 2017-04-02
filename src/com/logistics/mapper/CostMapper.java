package com.logistics.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;






import com.logistics.idal.ICostMapper;
import com.logistics.model.CostInfo;
import com.logistics.mybatisutil.MyBatisUtil;

public class CostMapper {

	//查询成本信息行数
	public int getCostCount()
	{
		SqlSession session = MyBatisUtil.getsqlSession(true);
		ICostMapper mapper = session.getMapper(ICostMapper.class);
		int row= mapper.getCostCount();
		session.close();
		return row;
	}
	//获取成本列表信息
	public List<CostInfo> getCostList(Map<String,Object> map){
		
		SqlSession session = MyBatisUtil.getsqlSession(true);
	    // 得到ICityMapper接口的实现类对象，ICostMapper接口的实现类对象由sqlSession.getMapper(ICostMapper.class)动态构建出来
		ICostMapper mapper = session.getMapper(ICostMapper.class);
	    
		List<CostInfo> costlist =mapper.getCostList(Integer.parseInt(map.get("startIndex").toString()),Integer.parseInt(map.get("pageSize").toString()));

		session.close();
		return costlist;
	}
	//判断城市名称是否已存在，新增时，
	public boolean getCostInfoOk(String city_name)
	{
		SqlSession session = MyBatisUtil.getsqlSession(true);
	    // 得到ICityMapper接口的实现类对象，ICostMapper接口的实现类对象由sqlSession.getMapper(ICostMapper.class)动态构建出来
		ICostMapper mapper = session.getMapper(ICostMapper.class);
		System.out.println("city_name2:"+city_name);
		int rows= mapper.getCostInfoOk(city_name);
		if(rows>0)
		{
			//存在
			return true;
		}else {
			//不存在
			return false;
		}	
	}
	
	
	//根据ID查询信息
	public CostInfo getCostById(int cost_id)
	{
		SqlSession session = MyBatisUtil.getsqlSession(true);
		ICostMapper mapper = session.getMapper(ICostMapper.class);
		return mapper.getCostById(cost_id);
		
	}
	
	//根据city_code查询信息,用在订单中
	public CostInfo getCostByCode(String city_code)
	{
		SqlSession session = MyBatisUtil.getsqlSession(true);
		ICostMapper mapper = session.getMapper(ICostMapper.class);
		return mapper.getCostByCode(city_code);
		
	}
	//修改信息
	public boolean getUpdateCost(CostInfo cf)
	{
		SqlSession session = MyBatisUtil.getsqlSession(true);
		ICostMapper mapper = session.getMapper(ICostMapper.class);
		
		int rows = mapper.getUpdateCost(cf);
		if(rows>0)
		{
			return true;
		}else {
			return false;
		}
	}
	//增加信息
	public boolean getCreateCost(CostInfo cf)
	{
		SqlSession session = MyBatisUtil.getsqlSession(true);
		ICostMapper mapper = session.getMapper(ICostMapper.class);
		int rows = mapper.getCreateCost(cf);
		if(rows>0)
		{
			return true;
		}else {
			return false;
		}
	}
	//删除信息
	public boolean getDelCost(int cost_id)
	{
		SqlSession session = MyBatisUtil.getsqlSession(true);
		ICostMapper mapper = session.getMapper(ICostMapper.class);
		int rows = mapper.getDelCode(cost_id);
		if(rows>0)
		{
			return true;
		}else {
			return false;
		}
	}
	//搜索框，模糊查询
	public List<Object> getCostInfoName(String name) {
		// TODO Auto-generated method stub

		SqlSession session = MyBatisUtil.getsqlSession(true);
		ICostMapper mapper = session.getMapper(ICostMapper.class);
		return mapper.getCostInfoName(name);
	}
	
	//根据city_name查询信息，定位城市
	public CostInfo getCostInfoNameList(String city_name)
	{
		SqlSession session = MyBatisUtil.getsqlSession(true);
		ICostMapper mapper = session.getMapper(ICostMapper.class);
		return mapper.getCostInfoNameList(city_name);
	}
}
