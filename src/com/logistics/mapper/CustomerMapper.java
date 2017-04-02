package com.logistics.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.logistics.idal.ICustomerMapper;
import com.logistics.model.CustomerInfo;
import com.logistics.model.UserInfo;
import com.logistics.mybatisutil.MyBatisUtil;

public class CustomerMapper {

	//查询成本信息行数
	public int getCustomerCount()
	{
		SqlSession session = MyBatisUtil.getsqlSession(true);
		ICustomerMapper mapper = session.getMapper(ICustomerMapper.class);
		int row= mapper.getCustomerCount();
		session.close();
		return row;
	}
	//获取成本列表信息
	public List<CustomerInfo> getCustomerList(Map<String,Object> map){
		
		SqlSession session = MyBatisUtil.getsqlSession(true);
	    // 得到ICityMapper接口的实现类对象，ICostMapper接口的实现类对象由sqlSession.getMapper(ICostMapper.class)动态构建出来
		ICustomerMapper mapper = session.getMapper(ICustomerMapper.class);
	    
		List<CustomerInfo> costlist =mapper.getCustomerList(Integer.parseInt(map.get("startIndex").toString()),Integer.parseInt(map.get("pageSize").toString()));

		session.close();
		return costlist;
	}
	//判断城市名称是否已存在，新增时，
	public boolean getCustomerInfoOk(String Customer_name)
	{
		SqlSession session = MyBatisUtil.getsqlSession(true);
	    // 得到ICityMapper接口的实现类对象，ICostMapper接口的实现类对象由sqlSession.getMapper(ICostMapper.class)动态构建出来
		ICustomerMapper mapper = session.getMapper(ICustomerMapper.class);
		
		int rows= mapper.getCustomerInfoOk(Customer_name);
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
	public CustomerInfo getCustomerById(int Customer_id)
	{
		SqlSession session = MyBatisUtil.getsqlSession(true);
		ICustomerMapper mapper = session.getMapper(ICustomerMapper.class);
		return mapper.getCustomerById(Customer_id);
		
	}
	//修改信息
	public boolean getUpdateCustomer(CustomerInfo cf)
	{
		SqlSession session = MyBatisUtil.getsqlSession(true);
		ICustomerMapper mapper = session.getMapper(ICustomerMapper.class);
		
		int rows = mapper.getUpdateCustomer(cf);
		if(rows>0)
		{
			return true;
		}else {
			return false;
		}
	}
	//增加信息
	public boolean getCreateCustomer(CustomerInfo cf)
	{
		SqlSession session = MyBatisUtil.getsqlSession(true);
		ICustomerMapper mapper = session.getMapper(ICustomerMapper.class);
		int rows = mapper.getCreateCustomer(cf);
		if(rows>0)
		{
			return true;
		}else {
			return false;
		}
	}
	//删除信息
	public boolean getDelCustomer(int Customer_id)
	{
		SqlSession session = MyBatisUtil.getsqlSession(true);
		ICustomerMapper mapper = session.getMapper(ICustomerMapper.class);
		int rows = mapper.getDelCustomer(Customer_id);
		if(rows>0)
		{
			return true;
		}else {
			return false;
		}
	}
	
	//修改密码
	public boolean getUpdateUserPass(UserInfo uf)
	{
		System.out.println(uf.getUsername()+uf.getPassword()+uf.getNewpassword());
		SqlSession session = MyBatisUtil.getsqlSession(true);
		ICustomerMapper mapper = session.getMapper(ICustomerMapper.class);
		int rows = mapper.getUpdateUserPass(uf);
		if(rows>0)
		{
			return true;
		}else {
			return false;
		}
	}
}
