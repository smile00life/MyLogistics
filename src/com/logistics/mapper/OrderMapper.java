package com.logistics.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;








import com.logistics.idal.ICustomerMapper;
import com.logistics.idal.IOrderMapper;
import com.logistics.model.CustomerInfo;
import com.logistics.model.OrderInfo;
import com.logistics.model.WorkerInfo;
import com.logistics.mybatisutil.MyBatisUtil;

public class OrderMapper {

	SqlSession session = MyBatisUtil.getsqlSession(true);
	IOrderMapper mapper = session.getMapper(IOrderMapper.class);
	//查询订单信息总行数
	public int getOrderCount()
	{
		SqlSession session = MyBatisUtil.getsqlSession(true);
		IOrderMapper mapper = session.getMapper(IOrderMapper.class);
		int row= mapper.getOrderCount();
		
		return row;
	}
	//获取订单列表信息
	public List<OrderInfo> getOrderList(Map<String,Object> map){
		
		SqlSession session = MyBatisUtil.getsqlSession(true);
	    // 得到IOrderMapper接口的实现类对象，IOrderMapper接口的实现类对象由sqlSession.getMapper(IOrderMapper.class)动态构建出来
		IOrderMapper mapper = session.getMapper(IOrderMapper.class);
	    
		List<OrderInfo> costlist =mapper.getOrderList(Integer.parseInt(map.get("startIndex").toString()),Integer.parseInt(map.get("pageSize").toString()));

		/*session.close();*/
		return costlist;
	}
	//判断订单编号是否已存在，新增时，
	public boolean getOrderInfoOk(String order_code)
	{
		SqlSession session = MyBatisUtil.getsqlSession(true);
	    // 得到IOrderMapper接口的实现类对象，IOrderMapper接口的实现类对象由sqlSession.getMapper(IOrderMapper.class)动态构建出来
		IOrderMapper mapper = session.getMapper(IOrderMapper.class);
		
		int rows= mapper.getOrderInfoOk(order_code);
		if(rows>0)
		{
			//存在
			return true;
		}else {
			//不存在
			return false;
		}	
	}
	
	
	//根据ID查询订单信息
	public OrderInfo getOrderById(int order_id)
	{
		SqlSession session = MyBatisUtil.getsqlSession(true);
		IOrderMapper mapper = session.getMapper(IOrderMapper.class);
		return mapper.getOrderById(order_id);
		
	}
	//修改订单信息
	public boolean getUpdateOrder(OrderInfo cf)
	{
		SqlSession session = MyBatisUtil.getsqlSession(true);
		IOrderMapper mapper = session.getMapper(IOrderMapper.class);
		
		int rows = mapper.getUpdateOrder(cf);
		if(rows>0)
		{
			return true;
		}else {
			return false;
		}
	}
	//增加订单信息
	public boolean getCreateOrder(OrderInfo cf)
	{
		SqlSession session = MyBatisUtil.getsqlSession(true);
		IOrderMapper mapper = session.getMapper(IOrderMapper.class);
		int rows = mapper.getCreateOrder(cf);
		if(rows>0)
		{
			return true;
		}else {
			return false;
		}
	}
	//删除订单信息
	public boolean getDelOrder(int roder_id)
	{
		SqlSession session = MyBatisUtil.getsqlSession(true);
		IOrderMapper mapper = session.getMapper(IOrderMapper.class);
		int rows = mapper.getDelOrder(roder_id);
		if(rows>0)
		{
			return true;
		}else {
			return false;
		}
	}
	
	//获取客户信息
	public List<CustomerInfo> getCustomerInfo()
	{
		SqlSession session = MyBatisUtil.getsqlSession(true);
		ICustomerMapper mapper = session.getMapper(ICustomerMapper.class);
		List<CustomerInfo> cusinfo = mapper.getCustomerInfo();
		session.close();
		return cusinfo;
		
	}
	
	//查询员工信息 Sender 派送员, receiver 揽件员
	public List<WorkerInfo> getWorkerInfo(int role_id)
	{
		SqlSession session = MyBatisUtil.getsqlSession(true);
		IOrderMapper mapper = session.getMapper(IOrderMapper.class);
		List<WorkerInfo> workerinfo = mapper.getWorkerInfo(role_id);
		return workerinfo;
	}
	
	
	//查询付款状态,返回所有信息 list
	public List<OrderInfo> getOrderPay()
	{
		SqlSession session = MyBatisUtil.getsqlSession(true);
		IOrderMapper mapper = session.getMapper(IOrderMapper.class);
		List<OrderInfo> orderpay = mapper.getOrderPay();
		return orderpay;
	}
	
	//查询付款状态,返回 pay_status
	public OrderInfo getOrderPayOk(int order_id)
	{
		SqlSession session = MyBatisUtil.getsqlSession(true);
		IOrderMapper mapper = session.getMapper(IOrderMapper.class);
		OrderInfo orderpay = mapper.getOrderPayOk(order_id);
		return orderpay;
	}
	/**********************************************小 曹 君 分割线！！！！***************************************************************/
	
	//商品管理
	
	//查询商品信息总行数
		public int getGoodsCount()
		{
			SqlSession session = MyBatisUtil.getsqlSession(true);
			IOrderMapper mapper = session.getMapper(IOrderMapper.class);
			int row= mapper.getGoodsCount();
			session.close();
			return row;
		}
		//获取商品列表信息,分页
		public List<OrderInfo> getGoodsList(Map<String,Object> map){
			
			SqlSession session = MyBatisUtil.getsqlSession(true);
		    // 得到IOrderMapper接口的实现类对象，IOrderMapper接口的实现类对象由sqlSession.getMapper(IOrderMapper.class)动态构建出来
			IOrderMapper mapper = session.getMapper(IOrderMapper.class);
		    
			List<OrderInfo> goodslist =mapper.getGoodsList(Integer.parseInt(map.get("startIndex").toString()),Integer.parseInt(map.get("pageSize").toString()));

			session.close();
			return goodslist;
		}
		//获取商品列表信息，订单
		public List<OrderInfo> getGoodsLists(){
			
			SqlSession session = MyBatisUtil.getsqlSession(true);
		    // 得到IOrderMapper接口的实现类对象，IOrderMapper接口的实现类对象由sqlSession.getMapper(IOrderMapper.class)动态构建出来
			IOrderMapper mapper = session.getMapper(IOrderMapper.class);
		    
			List<OrderInfo> goodslist =mapper.getGoodsLists();

			session.close();
			return goodslist;
		}
		//根据ID查询商品信息
		public OrderInfo getGoodsById(int goods_id)
		{
			SqlSession session = MyBatisUtil.getsqlSession(true);
			IOrderMapper mapper = session.getMapper(IOrderMapper.class);
			return mapper.getGoodsById(goods_id);
			
		}
		//根据Name查询商品信息
		public OrderInfo getGoodsByName(String goods_name)
		{
			SqlSession session = MyBatisUtil.getsqlSession(true);
			IOrderMapper mapper = session.getMapper(IOrderMapper.class);
			return mapper.getGoodsByName(goods_name);
			
		}
		//修改商品信息
		public boolean getUpdateGoods(OrderInfo cf)
		{
			SqlSession session = MyBatisUtil.getsqlSession(true);
			IOrderMapper mapper = session.getMapper(IOrderMapper.class);
			
			int rows = mapper.getUpdateGoods(cf);
			if(rows>0)
			{
				return true;
			}else {
				return false;
			}
		}
		//增加商品信息
		public boolean getCreateGoods(OrderInfo cf)
		{
			SqlSession session = MyBatisUtil.getsqlSession(true);
			IOrderMapper mapper = session.getMapper(IOrderMapper.class);
			int rows = mapper.getCreateGoods(cf);
			if(rows>0)
			{
				return true;
			}else {
				return false;
			}
		}
		//删除商品信息
		public boolean getDelGoods(int goods_id)
		{
			SqlSession session = MyBatisUtil.getsqlSession(true);
			IOrderMapper mapper = session.getMapper(IOrderMapper.class);
			int rows = mapper.getDelGoods(goods_id);
			if(rows>0)
			{
				return true;
			}else {
				return false;
			}
		}
	
	
	
	
	
	
	
}
