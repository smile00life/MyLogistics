package com.logistics.controller;


import java.util.HashMap;
import java.util.List;


import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.logistics.mapper.PageMapper;

@Controller
@RequestMapping("/page")
public class PageController extends BaseController{
	PageMapper cityMapper = new PageMapper();
	
	
	
	//分页测试
	//可以通过required=false或者true来要求@RequestParam配置的前端参数是否一定要传
	@RequestMapping("/list")
	public String pagelist(Model model,@RequestParam(required=false) String searchInfo,
			@RequestParam(required=false) Integer pageNum,
			@RequestParam(required=false) Integer pageSize)
	{
		//我们这里四个参数分别为装载我们数据的model，条件查询内容，当前所在页码，每页显示多少行
		//创建Map，来存放我们的数据
		
		Map<String, Object> map = new HashMap<String, Object>();
		//这里的searchInfo是查询方法，在这里用不到，比如我们需要根据条件来查询我们的数据，这里就用不到了
		//其实这个searchInfo就是我们动态查询时的查询条件，这里无用
		map.put("searchInfo", searchInfo);
		
		//取出数据总数，
		Integer totalCount = cityMapper.getCityTotal();
		
		//初始化分页数据，map对应的model，pageNum对应当前页，pageSize为每页显示的数据，totalCount为数据总行数
		this.initPage(map, pageNum, pageSize, totalCount);
		System.out.println("当前页："+pageNum);
		System.out.println("页面显示条数："+pageSize);
		System.out.println("最大记录数："+totalCount);
		
		//list为我们需要显示的数据List,获取的返回值是我们常用的List<实体类>形式
		
		List list = cityMapper.getCityMap(map);
		//list为我们的需要显示的数据List
		//初始化结果
		this.initResult(model, list, map);
		
		return "page/testListPager";
	}

}
