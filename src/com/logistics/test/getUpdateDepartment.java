package com.logistics.test;

import org.apache.ibatis.annotations.Mapper;
import org.junit.Test;

import com.logistics.mapper.DepartmentMapper;
import com.logistics.model.DepartmentInfo;

public class getUpdateDepartment {

	@Test
	public void test9()
	{
		DepartmentInfo df = new DepartmentInfo();
		DepartmentMapper mapper = new DepartmentMapper();
		
		df.setDepartment_id(3);
		
		df.setCity_name("1");
		df.setDepartment_address("西青");
		df.setDepartment_code("LOVE");
		
		df.setDepartment_manager("谢姐");
		df.setDepartment_mobile("123456");
		df.setDepartment_name("我的");
		df.setDepartment_tel("1512");
		df.setDepartment_upId("110");
		df.setDistrict_name("3");
		df.setManager_id(1);
		//df.setRanger_id(2);
		int manager_id = df.getManager_id();
        int ranger_id =mapper.getCenterRanger(manager_id);
		
		//将ranger_id加入到df对象中，传递ranger_id
		df.setRanger_id(ranger_id);
		
		
		if(mapper.getUpdateDepartment(df))
		{
			System.out.println("修改成功！");
		}
	}
}
