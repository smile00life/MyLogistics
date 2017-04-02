package com.logistics.test;

import java.util.List;
import org.junit.Test;
import com.logistics.mapper.MenuMapper;
import com.logistics.model.MenuInfo;

/*
 * 测试二级菜单的可用性
 * 
 * 
 * */
public class secondTest {

	@Test
	public void test()
	{
		
		MenuMapper userMapper = new MenuMapper();
		
		List<MenuInfo> menulist= userMapper.getSecondMenu(1, 1);
		for(MenuInfo s:menulist)
		{
			System.out.println(s.getMenuContent());
		}
		
	}
}
