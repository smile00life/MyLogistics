package com.logistics.test;

import org.junit.Test;


import com.logistics.mapper.CostMapper;


public class CityNameOkTest {

	@Test
	public void test()
	{
		CostMapper costMapper = new CostMapper();
		//CostInfo costInfo = new CostInfo();
		String city_name="北京";
		
		if(costMapper.getCostInfoOk(city_name))
		{
			System.out.println("已存在，请重新输入！");
		}else {
			System.out.println("可以使用！");
		}
	}
}
