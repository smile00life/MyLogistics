package com.logistics.idal;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;

import com.logistics.model.CityInfo;
import com.logistics.model.DepartmentInfo;
import com.logistics.model.UserInfo;

public interface IDepartmentMapper {

	//获取二级分拣中心总行数count(*)
	@Select("CALL getDepartmentCount()")
	@Options(statementType = StatementType.CALLABLE)
	public int getDepartmentCount();
	
	/*
	 * 参数：
	 * 第一个：当前页面数据的起始id
	 * 第二个：当前页面显示的数据内容
	 * 解释：假如数据有17条，每页显示5条，
	 * 第一页显示1-5
	 * 第二页显示6-10
	 * 第三页显示11-15
	 * 第四页显示16-17
	 * 如何取这里不管，这里只是分页查询
	 * 
	 * select * from sss LIMIT 1,5;
	 * 表示查找第2行开始到第6行之间的数据
	 * */
	//管理分拣中心列表
	//获取分拣中心列表信息
	@Select("CALL getDepartmentList(#{0},#{1})")
	@Options(statementType = StatementType.CALLABLE)
	public List<DepartmentInfo> getDepartmentList(int startIndex,int pageSize);
	
	
	
	//获取城市列表信息
	@Select("CALL getcitylist()")
	@Options(statementType = StatementType.CALLABLE)
	public List<CityInfo> getCityInfo();
		
	//获取二级城市列表信息
	@Select("CALL getAreaByCityId(#{select_id})")
	@Options(statementType = StatementType.CALLABLE)
	public List<CityInfo> getAreaInfo(int city_id);
	
	
	//获取未分配二级分拣中心管理权限的二级管理员列表
	@Select("CALL getSetCenterManagerList()")
	@Options(statementType = StatementType.CALLABLE)
	public List<UserInfo> getSetCenterManagerList();
	
	//获取二级管理员权限表对应的ID  ls_centerranger 表的ranger_id
	@Select("CALL getCenterRanger(#{manager_id})")
	@Options(statementType = StatementType.CALLABLE)
	public int getCenterRanger(int manager_id);
	

	
	//新建分拣中心
	@Insert("CALL getDepartmentCenter(#{department_upId},#{department_name},#{city_name},#{department_code},#{district_name},#{department_address},#{department_manager},#{manager_id},#{department_tel},#{department_mobile})")
	@Options(statementType = StatementType.CALLABLE)
	public int getDepartmentCenter(DepartmentInfo df);
	
	
	//修改二级分拣中心
	@Update("CALL getUpdateDepartment(#{department_upId},#{department_name},#{city_name},#{department_code},#{district_name},#{department_address},#{department_manager},#{manager_id},#{department_tel},#{department_mobile},#{department_id},#{ranger_id})")
	@Options(statementType = StatementType.CALLABLE)
	public int getUpdateDepartment(DepartmentInfo df);
	
	
	//根据ID获取二级分拣中心列表信息
	@Select("CALL getDepartmentById(#{department_id})")
	@Options(statementType = StatementType.CALLABLE)
	public DepartmentInfo getDepartmentById(int department_id);
	
	//删除二级分拣中心
	@Delete("CALL getDelDepartment(#{department_id})")
	@Options(statementType = StatementType.CALLABLE)
	public int getDelDepartment(int department_id);
	
}
