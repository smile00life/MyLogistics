package com.logistics.idal;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;

import com.logistics.model.CustomerInfo;
import com.logistics.model.UserInfo;

public interface ICustomerMapper {

	//获取客户表总行数
	@Select("CALL getCustomerCount()")
	@Options(statementType = StatementType.CALLABLE)
	public int getCustomerCount();
	
	//获取用户表分页信息，列表
	@Select("CALL getCustomerList(#{0},#{1})")
	@Options(statementType = StatementType.CALLABLE)
	public List<CustomerInfo> getCustomerList(int startIndex,int pageSize);
	
	
	//获取客户信息，列表
	@Select("CALL getCustomerInfo()")
	@Options(statementType = StatementType.CALLABLE)
	public List<CustomerInfo> getCustomerInfo();
	
	//根据Id查询客户信息
	@Select("CALL getCustomerById(#{Customer_id})")
	@Options(statementType= StatementType.CALLABLE)
	public CustomerInfo getCustomerById(int Customer_id);
	
	//检查插入的客户是否存在，新增,检查Customer_name是否已经存在
	@Select("CALL getCustomerInfoOk(#{Customer_name})")
	@Options(statementType = StatementType.CALLABLE)
	public int getCustomerInfoOk(String Customer_name);
	
	//增加客户
	@Insert("CALL getCreateCustomer(#{customer_name},#{customer_tel},#{customer_mobile},#{customer_address},#{customer_code},#{order_id})")
	@Options(statementType = StatementType.CALLABLE)
	public int getCreateCustomer(CustomerInfo cf);
	
	//修改客户信息
	@Update("CALL getUpdateCustomer(#{customer_name},#{customer_tel},#{customer_mobile},#{customer_address},#{customer_code},#{order_id},{customer_id})")
	@Options(statementType = StatementType.CALLABLE)
	public int getUpdateCustomer(CustomerInfo cf);
	
	//删除客户信息
	@Delete("CALL getDelCustomer(#{customer_id})")
	@Options(statementType = StatementType.CALLABLE)
	public int getDelCustomer(int customer_id);
	
	//修改密码,此处顺序有点怪异，需注意，，，，，
	@Update("CALL getUpdateUserPass(#{username},#{password},#{newpassword})")
	@Options(statementType = StatementType.CALLABLE)
	public int getUpdateUserPass(UserInfo uf);
}
