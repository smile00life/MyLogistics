package com.logistics.test;

import org.junit.Test;

import com.logistics.mapper.MenuMapper;
/*
 * 测试用户是否有访问权限
 * 
 * 
 * */
public class validatePathTest {

	@Test
	public void test()
	{
		MenuMapper mapper = new MenuMapper();
		if(mapper.validateMenuPath(1, "admin/index"))
		{
			System.out.print("访问成功！");
		}else {
			System.out.print("权限不足，访问失败！");
		}
		
	}
}
