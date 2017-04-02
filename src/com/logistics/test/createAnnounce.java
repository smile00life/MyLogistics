package com.logistics.test;


import org.junit.Test;


import com.logistics.mapper.AnnounceMapper;
import com.logistics.model.AnnounceInfo;

public class createAnnounce {

	@Test
	public void test()
	{
        AnnounceMapper am=new AnnounceMapper();
		
		AnnounceInfo ai=new AnnounceInfo();
		ai.setManager_id(1);
		ai.setAnnounce_Title("test");
		ai.setAnnounce_Content("试试");
		
		if(am.createAnnounce(ai)){
			System.out.println("成功");
		}
		else
		{
			System.out.println("失败");
		}
	}
}
