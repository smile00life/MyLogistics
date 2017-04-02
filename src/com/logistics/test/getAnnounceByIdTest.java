package com.logistics.test;

import org.junit.Test;

import com.logistics.mapper.AnnounceMapper;
import com.logistics.model.AnnounceInfo;

public class getAnnounceByIdTest {

//	@Test
//	public void Test()
//	{
//		AnnounceMapper mapper = new AnnounceMapper();
//		
//		AnnounceInfo af =mapper.getAnnounceById(10);
//		System.out.println("查询成功！");
//		System.out.println(af.getAnnounce_Title());
//		
//	}
	@Test
	public void Test2()
	{
		AnnounceInfo af = new AnnounceInfo();
		AnnounceMapper mapper = new AnnounceMapper();
		af.setAnnounce_Id(10);
		af.setAnnounce_Content("sss");
		af.setAnnounce_Title("aaa");
		af.setManager_id(2);
		if(mapper.updateAnnounce(af))
		{
			System.out.println("修改成功！");
		}else {
			System.out.println("修改失败！");
		}
	}
}
