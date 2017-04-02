package com.logistics.idal;
/*
 * 
 * 级联菜单，获取一级菜单，二级菜单，两者相互关联
 * 
 * 城市--区县
 * 
 * 天津-西青区
 * 
 * */
import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.logistics.model.CityInfo;

public interface ICityMapper {

	//获取城市列表信息
	@Select("CALL getcitylist()")
	@Options(statementType = StatementType.CALLABLE)
	public List<CityInfo> getCityInfo();
	
	//获取二级城市列表信息
	@Select("CALL getAreaByCityId(#{select_id})")
	@Options(statementType = StatementType.CALLABLE)
	public List<CityInfo> getAreaInfo(int city_id);
}
