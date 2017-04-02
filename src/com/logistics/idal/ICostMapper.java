package com.logistics.idal;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;






import com.logistics.model.CostInfo;

/*
 * 成本，管理员查询，修改，增加
 * 
 * 
 * */
public interface ICostMapper {

	//新建成本信息
	@Insert("CALL getCreateCost(#{city_name},#{cost_price},#{cost_per})")
	@Options(statementType = StatementType.CALLABLE)
	public int getCreateCost(CostInfo cf);
	
	//修改成本信息,city_name不能修改
	@Update("CALL getUpdateCost(#{cost_price},#{cost_per},#{cost_id})")
	@Options(statementType = StatementType.CALLABLE)
	public int getUpdateCost(CostInfo cf);
	
	//删除成本信息
	@Delete("CALL getDelCost(#{cost_id})")
	@Options(statementType = StatementType.CALLABLE)
	public int getDelCode(int cost_id);
	
	
	
	//根据Id查询信息
	@Select("CALL getCostById(#{cost_id})")
	@Options(statementType= StatementType.CALLABLE)
	public CostInfo getCostById(int cost_id);
	
	//根据city_code查询信息，用在订单中
	@Select("CALL getCostByCode(#{city_code})")
	@Options(statementType= StatementType.CALLABLE)
	public CostInfo getCostByCode(String city_code);
	
	//查询成本信息表行数
	@Select("CALL getCostCount()")
	@Options(statementType = StatementType.CALLABLE)
	public int getCostCount();
	
	//获取成本列表信息
	@Select("CALL getCostList(#{0},#{1})")
	@Options(statementType = StatementType.CALLABLE)
	public List<CostInfo> getCostList(int startIndex,int pageSize);
	
	//检查插入的城市是否存在，新增,检查city_name是否已经存在
	@Select("CALL getCostInfoOk(#{city_name})")
	@Options(statementType = StatementType.CALLABLE)
	public int getCostInfoOk(String city_name);

	//根据关键字查询城市名
	@Select("CALL getCostInfoName(#{city_name})")
	@Options(statementType = StatementType.CALLABLE)
	public List<Object> getCostInfoName(String city_name);
	
	//根据城市名字，查询其信息
	@Select("CALL getCostInfoNameList(#{city_name})")
	@Options(statementType = StatementType.CALLABLE)
	public CostInfo getCostInfoNameList(String city_name);
}
