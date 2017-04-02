package com.logistics.test;

import org.junit.Test;

import com.logistics.mapper.SystemMapper;
import com.logistics.model.UserInfo;

public class updatePassTest {

	@Test
	public void updatePass()
	{
		UserInfo userInfo = new UserInfo();
		SystemMapper mapper = new SystemMapper();
		userInfo.setNewpassword("123");
		userInfo.setPassword("456");
		userInfo.setUsername("tom");
		//验证密码
		if(mapper.getPassOk(userInfo))
		{
			//修改密码
			mapper.updatePass(userInfo);
		}
		
		
	}
}
