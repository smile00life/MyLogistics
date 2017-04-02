package com.logistics.test;

import java.util.List;






import org.junit.Test;

import com.logistics.mapper.MenuMapper;
import com.logistics.model.MenuInfo;

public class logisticsTest {

	@Test
	public void test()
	{
		MenuMapper mmMapper = new MenuMapper();
		List<MenuInfo> miInfos =mmMapper.getTopMenu(1);
		
		for(MenuInfo m:miInfos)
		{
			System.out.println(m.getMenuContent());
		}
	}
}
