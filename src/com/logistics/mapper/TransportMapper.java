package com.logistics.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;


import com.logistics.idal.ITransportMapper;

import com.logistics.model.TransportInfo;
import com.logistics.mybatisutil.MyBatisUtil;

public class TransportMapper {

	//获取车辆列表总行数
		public int getTransportCount(){
			
			SqlSession session = MyBatisUtil.getsqlSession(true);
		    // 得到ICityMapper接口的实现类对象，ICityMapper接口的实现类对象由sqlSession.getMapper(ICityMapper.class)动态构建出来
		    ITransportMapper mapper = session.getMapper(ITransportMapper.class);
			int row= mapper.getTransportCount();
			session.close();
			return row;
		}
		
		//获取车辆列表信息
		public List<TransportInfo> getTransportList(Map<String,Object> map){
			//String s=(String)map.get("startIndex");
			//String p=(String)map.get("pageSize");
			SqlSession session = MyBatisUtil.getsqlSession(true);
		    // 得到ICityMapper接口的实现类对象，ICityMapper接口的实现类对象由sqlSession.getMapper(ICityMapper.class)动态构建出来
		    ITransportMapper mapper = session.getMapper(ITransportMapper.class);
		    
			List<TransportInfo> annlist =mapper.getTransportList(Integer.parseInt(map.get("startIndex").toString()),Integer.parseInt(map.get("pageSize").toString()));

			session.close();
			return annlist;
		}
		
		//增加车辆
		public boolean getCreateTransport(TransportInfo tf)
		{
			SqlSession session = MyBatisUtil.getsqlSession(true);
		    // 得到ICityMapper接口的实现类对象，ICityMapper接口的实现类对象由sqlSession.getMapper(ICityMapper.class)动态构建出来
		    ITransportMapper mapper = session.getMapper(ITransportMapper.class);
			int count = mapper.getCreateTransport(tf);
			System.out.println(count);
			if(count>0)
			{
				session.close();
				System.out.println("车辆增加成功！");
//				JOptionPane.showMessageDialog(null, "更新成功!");
				return true;
			}else {
				return false;
			}
		}
		
		//编辑车辆
		//修改车辆
		public boolean getUpdateTransport(TransportInfo tf)
		{
			SqlSession session = MyBatisUtil.getsqlSession(true);
			ITransportMapper mapper = session.getMapper(ITransportMapper.class);
			
			int rows = mapper.getUpdateTransport(tf); 
			if(rows>0)
			{
				System.out.println("车辆成功");
				session.close();
				return true;
			}
			else {
				return false;
			}	
		}
		
		//删除车辆
		public boolean getDelTransport(int transport_id)
		{
			SqlSession session = MyBatisUtil.getsqlSession(true);
			ITransportMapper mapper = session.getMapper(ITransportMapper.class);
			int rows = mapper.getDelTransport(transport_id);
			if(rows>0)
			{
				System.out.println("删除成功！");
				return true;
			}else {
				return false;
			}
		}
		
		//根据ID查询车辆
		public TransportInfo getTransportById(int id)
		{
			SqlSession session = MyBatisUtil.getsqlSession(true);
			ITransportMapper mapper = session.getMapper(ITransportMapper.class);
			
			TransportInfo listaf = mapper.getTransportById(id);
			System.out.println("车辆名称为："+listaf.getTransport_name());
			session.close();
			return listaf;
		}
}
