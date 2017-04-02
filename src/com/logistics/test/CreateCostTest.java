package com.logistics.test;

import org.junit.Test;

import com.logistics.mapper.CostMapper;
import com.logistics.model.CostInfo;
/*@Test
 * 添加成本
 * 
 * */
public class CreateCostTest {

	@Test
	public void testcost()
	{
		CostInfo costInfo = new CostInfo();
		CostMapper costMapper = new CostMapper();
		
		costInfo.setCost_price("25");//起送价
		costInfo.setCost_per("10");//超过1公斤加价
		costInfo.setCity_name("江苏");//城市名称
		costInfo.setCity_code("150");//城市编号
		if(costMapper.getCostInfoOk(costInfo.getCity_name()))
		{
			System.out.println("该城市已存在，请重新输入！");
			return;
		}
		if(costMapper.getCreateCost(costInfo))
		{
			System.out.println("成本信息添加成功！");
		}else {
			System.out.println("成本信息添加失败！");
		}
		
		
	}
}
