package com.logistics.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;






import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;






import com.logistics.interceptor.MemberInterceptor;
import com.logistics.mapper.CostMapper;
import com.logistics.mapper.MenuMapper;
import com.logistics.model.CostInfo;
import com.logistics.model.MenuInfo;
import com.logistics.model.UserInfo;

@Controller
@RequestMapping("/cost")
public class CostController extends BaseController{
	MenuMapper menuMapper = new MenuMapper();
	CostMapper costMapper = new CostMapper();
	
	@RequestMapping("/index")
	public String Index(HttpServletRequest request)
	{
		String view = initMenu(request, "cost/index");
		return view;
	}
	@RequestMapping("/manager")
	public String managerAnnounce(HttpServletRequest request, Model model,
			@RequestParam(required = false) String searchInfo,
			@RequestParam(required = false) Integer pageNum,
			@RequestParam(required = false) Integer pageSize) {
		// 初始化菜单信息
		String view = initMenu(request, "cost/manager");
		// 添加分页相关信息
		// 创建Map，来存放我们的数据，
		//我们这里四个参数分别为装载我们数据的model，条件查询内容，当前所在页码，每页显示多少行
		//创建Map，来存放我们的数据
		
		Map<String, Object> map = new HashMap<String, Object>();
		//这里的searchInfo是查询方法，在这里用不到，比如我们需要根据条件来查询我们的数据，这里就用不到了
		//其实这个searchInfo就是我们动态查询时的查询条件，这里无用
		map.put("searchInfo", searchInfo);
		
		//取出数据总数，
		Integer totalCount = costMapper.getCostCount();
		
		//初始化分页数据，map对应的model，pageNum对应当前页，pageSize为每页显示的数据，totalCount为数据总行数
		this.initPage(map, pageNum, pageSize, totalCount);
		System.out.println("当前页："+pageNum);
		System.out.println("页面显示条数："+pageSize);
		System.out.println("最大记录数："+totalCount);
		
		//list为我们需要显示的数据List,获取的返回值是我们常用的List<实体类>形式
		
		List list = costMapper.getCostList(map);
		//list为我们的需要显示的数据List
		//初始化结果
		this.initResult(model, list, map);

		return view;
	}
	//增加成本信息，页面跳转
	@RequestMapping("/create")
	public String getCreateCost(HttpServletRequest request)
	{
		String view =initMenu(request, "cost/create");
		return view;
	}
	//增加成本信息，页面提交
	@RequestMapping("/createCost")
	public String getCreateCosts(HttpServletRequest request,CostInfo cf)
	{
		String view = "";
		String city_name=cf.getCity_name();
		//新增时,检查city_name是否已经存在
		if(costMapper.getCostInfoOk(city_name))
		{
			System.out.println("城市名已存在！");
			JOptionPane.showMessageDialog(null, "该城市已经存在，请重新输入！");
			return "redirect:/cost/create";
			
		}
		
		if(costMapper.getCreateCost(cf))
		{
			System.out.println("成本增加成功！");
			view="redirect:/cost/index";
		}else {
			view="error/503";
		}
		return view;
	}
	
	
	
	//修改成本信息，页面跳转
	@RequestMapping("/update/{cost_id}")
	public String getUpdateCost(HttpServletRequest request,@PathVariable int cost_id)
	{
		String view =initMenu(request, "cost/update");
		
		//查询成本信息
		CostInfo costInfo = costMapper.getCostById(cost_id);
		System.out.println("city_name:"+costInfo.getCity_name());
		
		request.setAttribute("costinfo", costInfo);
		return view;
	}
	//修改成本信息，页面提交
	@RequestMapping("/updateCost")
	public String getUpdateCosts(HttpServletRequest request,CostInfo cf)
	{
		String view = "";
		if(costMapper.getUpdateCost(cf))
		{
			System.out.println("成本修改成功！");
			view="redirect:/cost/manager";
		}else {
			view="error/503";
		}
		return view;
	}
	//删除成本信息
	@RequestMapping("/delete/{cost_id}")
	public String getDelCost(@PathVariable int cost_id)
	{
		if(costMapper.getDelCost(cost_id))
		{
			System.out.println("成本信息删除成功！");
			return "redirect:/cost/manager";
		}else {
			return "redirect:/error/503";
		}
	}
	//查看成本
	@RequestMapping("/details/{cost_id}")
	public String getCostInfo(HttpServletRequest request,@PathVariable int cost_id)
	{
		String view = initMenu(request, "cost/details");
		
		CostInfo costInfo = costMapper.getCostById(cost_id);
		request.setAttribute("costinfo", costInfo);
		
		return view;
	}
	//查看包裹柱状图
	@RequestMapping("/page")
	public String getPage(HttpServletRequest request)
	{
		String view = initMenu(request, "cost/page");
		return view;
	}
	//搜索框
	//根据关键字查询城市名称，用在输入框，输入关键字查询城市名字
	@ResponseBody
	 @RequestMapping(value="/getCostInfoName",produces="application/json")
	public StringBuilder getCostInfoName(@RequestParam String city_name)
	{
		System.out.println("输入框中数据："+city_name);
		List<Object> costInfo = costMapper.getCostInfoName(city_name);
		
		StringBuilder result = new StringBuilder();
		for(Object ss:costInfo)
		{
			System.out.println(ss);
			result.append("<div onclick=\"write_text(this)\" onmouseover=\"background_over(this)\" onmouseout=\"background_out(this)\" style=\"text-indent:2em;\">")
			.append(ss)
			.append("</div>");	
		}
		
		return result;
		
	}
	
	//搜索框
    //根据关键字查询城市名称，用在定位城市名，输入城市名，查询出当前的城市，并显示
	@ResponseBody
	 @RequestMapping(value="/getCostInfoNameList",produces="application/json")
	public StringBuilder getCostInfoNameList(@RequestParam String city_name)
	{
		System.out.println("查询的城市名为："+city_name);
		CostInfo costInfo = costMapper.getCostInfoNameList(city_name);
		
		StringBuilder result = new StringBuilder();
		
			System.out.println(costInfo);
			result.append("<tr class=\"oddRow\">")
			.append("<td style=\"width:20px;\"><input type=\"checkbox\" /></td>")
			.append("<td style=\"width:50px;text-align:center;\">")
			.append(costInfo.getCost_id())
			.append("</td>")
			.append("<td style=\"width:100px;text-align:center;\">"
			        +"<a href=\"cost/details/"+costInfo.getCost_id()+"\""
					+"title=\"+costInfo.getCost_id()+\">")
			.append(costInfo.getCity_name())
			.append("</a></td>")
			.append("<td style=\"width:130px;text-align:center;\">")
			.append(costInfo.getCost_price())
			.append("</td>")
			.append("<td style=\"width:130px;text-align:center;\">")
			.append(costInfo.getCost_per())
			.append("</td>")
			.append("<td style=\"width:130px;text-align:center;\">")
			.append(costInfo.getWrite_time())
			.append("</td>")
			.append("<td class=\"editItem\">"
			        +"<ul class=\"editlist\">"
					+ "<li class=\"iconEdit\"><a href=\"cost/update/"+costInfo.getCost_id()+"\">编辑</a></li>"
					+"<li class=\"iconDel\"><a onclick=\"return confirm('您确认要删除当前数据吗？')\" href=\"cost/delete/"+costInfo.getCost_id()+"\">删除</a></li>"
					+"</ul>"+"</td>")
			.append("</tr>");
		
		
		return result;
		
	}
	
	
	
	//实用json控制,查询城市名是否存在，用在添加城市价格时
    @ResponseBody
    @RequestMapping(value="/getJsonDataByCityName",produces="application/json")
	public String getJsonByName(HttpServletRequest request,HttpServletResponse response,@RequestParam String city_name)throws ServletException, IOException
	{
    	//response.setContentType("text/html;charset=UTF-8");
    	//request.setCharacterEncoding("UTF-8");//解决post乱码问题
    	//System.out.println(request.getCharacterEncoding());
    	
    	//city_name = new String(city_name.getBytes("ISO-8859-1"), "UTF-8");//解决中文乱码问题，完美！
		System.out.println("city_name:"+city_name);
		
		String date = "";
		if(costMapper.getCostInfoOk(city_name))
		{
			 System.out.println("已存在");
			date= "<font color='red' text-decoration:blink;>该城市已存在</font>";
		}
		else {
			System.out.println("可以使用");
			date="<font color='green' >可以使用</font>";	
			//request.setAttribute("true",false );
			 
		}
		return date;
	}
    
	
	
	//通用方法，获取导航栏菜单权限
	public String initMenu(HttpServletRequest request,String page)
	{
		HttpSession session = request.getSession();
		UserInfo u= (UserInfo)session.getAttribute(MemberInterceptor.SESSION_MEMBER);
		
		// 登录管理用户名
		String logname = u.getUsername();
		int role_id = u.getRole_id();
		
		//此处需要修改**********************************************8
		int manager_id = u.getRole_id();
		String manager_name= u.getUsername();
		// ModelAndView view;
		// 权限判断
		if (!menuMapper.validateMenuPath(role_id, "admin/index")) {
			// 无权访问
			// view =new ModelAndView("error/503");
			return "redirect:/error/503";
		}

		// 测试生成菜单，默认权限为1-总管理员
		List<MenuInfo> menulist = menuMapper.getTopMenu(role_id);
		List<MenuInfo> siderlist = menuMapper.getSecondMenu(role_id, 0);
		List<MenuInfo> department = menuMapper.getSecondMenu(role_id, 6);
		// 将取到的数据传递到前端页面，使用jstl调用
		request.setAttribute("menulist", menulist);
		request.setAttribute("siderlist", siderlist);
		request.setAttribute("department", department);
		// 登录的用户名放入这个对象中，传递给前端
		request.setAttribute("welcome", logname);
		request.setAttribute("manager_id", manager_id);
		request.setAttribute("manager_name", manager_name);

		return page;
		
	}
	

}
