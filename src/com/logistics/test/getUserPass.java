package com.logistics.test;

import org.junit.Test;

import com.logistics.mapper.CustomerMapper;
import com.logistics.model.UserInfo;

public class getUserPass {

	/*
	 * 修改用户密码
	 * 
	 * 
	 * 
	 * */
	@Test
	public void test()
	{
		CustomerMapper mapper = new CustomerMapper();
		UserInfo user = new UserInfo();
		
		user.setUsername("tom");
		user.setPassword("123");
		user.setNewpassword("456");
		if(mapper.getUpdateUserPass(user))
		{
			System.out.println("密码修改成功！");
		}else {
			System.out.println("密码修改失败！");
		}
	}
}
