package com.logistics.test;

import java.util.List;

import org.junit.Test;

import com.logistics.mapper.CostMapper;
import com.logistics.model.CostInfo;

/*
 * 搜索框查找城市
 * 
 * 
 * */
public class getCostInfoNameTest {

	@Test
	public void test()
	{
		CostMapper costMapper = new CostMapper();
		List<Object> list = costMapper.getCostInfoName("北");
		if(list.size()>0)
		{
			System.out.println("查找成功！");
			for(Object ss:list)
			{
				System.out.println(ss);
			}
		}else {
			System.out.println("没查找到！");
		}
		
		
	}
}
