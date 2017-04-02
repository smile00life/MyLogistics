package com.logistics.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.web.bind.annotation.ResponseBody;

import com.logistics.mapper.CityMapper;
import com.logistics.model.CityInfo;

@Controller
@RequestMapping("/city")
public class CityController {
	
	CityMapper cityMapper = new CityMapper();

	@RequestMapping("/index")
	public String getciyInfo(HttpServletRequest request)
	{ 
		List<CityInfo> cityInfos = cityMapper.getCityInfo();
		List<CityInfo> cityAreaInfos = cityMapper.getAreaCityInfo(1);
		//List<CityInfo> cityinfo2 = cityMapper.getAreaCityInfo(2);
		
		
		request.setAttribute("citylist", cityInfos);
		request.setAttribute("arealist", cityAreaInfos);
		//request.setAttribute("arealist3", cityinfo2);
		
		
		return "city/index";
	}
	
	//使用json控制
  /*@ResponseBody
   * 该注解用于将Controller的方法返回的对象，
   * 通过适当的HttpMessageConverter转换为指定格式后，这里json格式
   * 写入到Response对象的body数据区
   */	
	@ResponseBody
	@RequestMapping(value="/getAreaJsonDataByCityId",produces="application/json")
	private List<CityInfo> getCityList(int city_id) {
		// TODO Auto-generated method stub
		
		//转化成json数据
		List<CityInfo> cityinfo=cityMapper.getAreaCityInfo(city_id);
		System.out.println("Json数据获取成功！"+city_id);
		return cityinfo;
	}
	
}
