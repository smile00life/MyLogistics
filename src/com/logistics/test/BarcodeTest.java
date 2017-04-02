package com.logistics.test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.jbarcode.util.ImageUtil;
import org.junit.Test;

import com.logistics.util.BarcodeHelper;
import com.logistics.util.GetId;

public class BarcodeTest {

	@Test 
	public void test(HttpServletRequest request)throws IOException
	{
		//随机产生一个10位数，包括数字和字母
				String sid = GetId.ID(10);
				//生成订单号order_id，以SS开头
				String order_id = "SS"+sid;
				String dd_id = "SS010yXQOTSN";
		/**************************************************生成二维码，并且获取二维码路径，没有务器地址************************************************************/		
				
				//二维码生成,并将二维码地址加到cf中
				//定义二维码标示符号
				//String myCode="TR0220101X090001";
				//服务器存储文件夹,E:\MyEclipsePRO\.metadata\.me_tcat7\webapps\IlogisticsSystem\ upload\img
			    String mypath="upload\\img";
				//生成二维码，保存在本地,返回地址，此处会抛出异常，throws IOException
				String path= BarcodeHelper.createBarCode15(request, mypath, dd_id);
				System.out.print("图片路径为："+path);
				
		/**************************************************获取文件下载路径，有服务器地址,此处没用到，此处用于比较，************************************************************/		
				
	}
}
