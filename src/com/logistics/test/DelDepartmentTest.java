package com.logistics.test;

import org.junit.Test;

import com.logistics.mapper.DepartmentMapper;

public class DelDepartmentTest {

	@Test
	public void test()
	{
		DepartmentMapper mapper = new DepartmentMapper();
		if(mapper.getDelDepartment(8))
		{
			
			System.out.println("删除成功！");
		}else {
			System.out.println("删除失败！");
		}
		
	}
}
