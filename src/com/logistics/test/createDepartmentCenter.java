package com.logistics.test;

import org.junit.Test;

import com.logistics.mapper.DepartmentMapper;
import com.logistics.model.DepartmentInfo;

public class createDepartmentCenter {

	@Test
	public void test()
	{
		DepartmentMapper mapper = new DepartmentMapper();
		DepartmentInfo df = new DepartmentInfo();
		
		df.setCity_name("天津");
		df.setDepartment_address("津静路26号");
		df.setDepartment_code("HGHG52");
		df.setDepartment_manager("tom");
		df.setDepartment_mobile("12346");
		df.setDepartment_name("城建分拣中心");
		df.setDepartment_tel("25463");
		df.setDistrict_name("西青区");
		df.setManager_id(2);
		df.setDepartment_upId("120");
		
		if(mapper.createDepartmentCenter(df))
		{
			System.out.println("二级分拣中心创建成功！");
		}
	}
}
