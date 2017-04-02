package com.logistics.idal;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;





import com.logistics.model.OrderInfo;
import com.logistics.model.WorkerInfo;

/*
 * 订单，管理员查询，修改，增加
 * 
 * 
 * */
public interface IOrderMapper {

	//新建订单信息
	@Insert("CALL getCreateOrder(#{sender_user},#{receiver_user},#{receiver_tel},#{city_name},#{area_name},#{receiver_address},#{goods_name},#{img_address},#{receiver_employee},#{sender_employee},#{receiver_post},#{order_pay},#{order_code},#{order_money})")
	@Options(statementType = StatementType.CALLABLE)
	public int getCreateOrder(OrderInfo cf);
	
	//修改订单信息,
	@Update("CALL getUpdateOrder(#{sender_user},#{receiver_user},#{receiver_tel},#{city_name},#{area_name},#{receiver_address},#{goods_name},#{receiver_employee},#{sender_employee},#{receiver_post},#{order_pay},#{order_money},#{order_id})")
	@Options(statementType = StatementType.CALLABLE)
	public int getUpdateOrder(OrderInfo cf);
	
	//删除订单信息
	@Delete("CALL getDelOrder(#{order_id})")
	@Options(statementType = StatementType.CALLABLE)
	public int getDelOrder(int order_id);
	
	//查询付款状态,已付款或未付款,返回所有信息
	@Select("CALL getOrderPay()")
	@Options(statementType= StatementType.CALLABLE)
	public List<OrderInfo> getOrderPay();
	
	//查询付款状态,已付款或未付款,返回订单状态,pay_status
	@Select("CALL getOrderPayOk(#{order_id})")
	@Options(statementType= StatementType.CALLABLE)
	public OrderInfo getOrderPayOk(int order_id);
	
	
	//根据Id查询订单信息
	@Select("CALL getOrderById(#{order_id})")
	@Options(statementType= StatementType.CALLABLE)
	public OrderInfo getOrderById(int order_id);
	
	//查询员工信息Sender 派送员,  receiver揽件员
	@Select("CALL getWorkerInfo(#{role_id})")
	@Options(statementType= StatementType.CALLABLE)
	public List<WorkerInfo> getWorkerInfo(int role_id);
	
	

	//查询订单信息表行数
	@Select("CALL getOrderCount()")
	@Options(statementType = StatementType.CALLABLE)
	public int getOrderCount();
	
	//获取订单列表信息
	@Select("CALL getOrderList(#{0},#{1})")
	@Options(statementType = StatementType.CALLABLE)
	public List<OrderInfo> getOrderList(int startIndex,int pageSize);
	
	//检查插入的订单编号是否已存在，新增,检查oder_code是否已经存在
	@Select("CALL getOrderInfoOk(#{order_code})")
	@Options(statementType = StatementType.CALLABLE)
	public int getOrderInfoOk(String order_code);
	/**********************************************小 曹 君 分割线！！！！***************************************************************/
	//商品信息管理
	//查询商品信息表行数
	@Select("CALL getGoodsCount()")
	@Options(statementType = StatementType.CALLABLE)
	public int getGoodsCount();
	
	//获取商品列表信息，分页
	@Select("CALL getGoodsList(#{0},#{1})")
	@Options(statementType = StatementType.CALLABLE)
	public List<OrderInfo> getGoodsList(int startIndex,int pageSize);
	
	//获取商品列表信息，订单
	@Select("CALL getGoodsLists()")
	@Options(statementType = StatementType.CALLABLE)
	public List<OrderInfo> getGoodsLists();
	
	//根据Id查询商品信息
	@Select("CALL getGoodsById(#{goods_id})")
	@Options(statementType= StatementType.CALLABLE)
	public OrderInfo getGoodsById(int goods_id);
	
	//新建商品信息
	@Insert("CALL getCreateGoods(#{goods_name},#{goods_content},#{goods_weight},#{goods_type})")
	@Options(statementType = StatementType.CALLABLE)
	public int getCreateGoods(OrderInfo cf);
	
	//修改商品信息,
	@Update("CALL getUpdateGoods(#{goods_name},#{goods_content},#{goods_weight},#{goods_type},#{goods_id})")
	@Options(statementType = StatementType.CALLABLE)
	public int getUpdateGoods(OrderInfo cf);
	
	//删除商品信息
	@Delete("CALL getDelGoods(#{goods_id})")
	@Options(statementType = StatementType.CALLABLE)
	public int getDelGoods(int goods_id);
	
	//根据商品名称查询商品重量
	@Select("CALL getGoodsByName(#{goods_name})")
	@Options(statementType= StatementType.CALLABLE)
	public OrderInfo getGoodsByName(String goods_name);
	
}
