package com.logistics.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.logistics.interceptor.MemberInterceptor;

import com.logistics.mapper.CustomerMapper;
import com.logistics.mapper.MenuMapper;

import com.logistics.model.CustomerInfo;
import com.logistics.model.MenuInfo;
import com.logistics.model.UserInfo;

@Controller
@RequestMapping("/customer")
public class CustomerController extends BaseController{
	MenuMapper menuMapper = new MenuMapper();
	CustomerMapper mapper = new CustomerMapper();
	
	@RequestMapping("/index")
	public String Index(HttpServletRequest request)
	{
		String view = initMenu(request, "customer/index");
		return view;
	}
	@RequestMapping("/manager")
	public String managerCustomer(HttpServletRequest request, Model model,
			@RequestParam(required = false) String searchInfo,
			@RequestParam(required = false) Integer pageNum,
			@RequestParam(required = false) Integer pageSize) {
		// 初始化菜单信息
		String view = initMenu(request, "customer/manager");
		// 添加分页相关信息
		// 创建Map，来存放我们的数据，
		//我们这里四个参数分别为装载我们数据的model，条件查询内容，当前所在页码，每页显示多少行
		//创建Map，来存放我们的数据
		
		Map<String, Object> map = new HashMap<String, Object>();
		//这里的searchInfo是查询方法，在这里用不到，比如我们需要根据条件来查询我们的数据，这里就用不到了
		//其实这个searchInfo就是我们动态查询时的查询条件，这里无用
		map.put("searchInfo", searchInfo);
		
		//取出数据总数，
		Integer totalCount = mapper.getCustomerCount();
		
		//初始化分页数据，map对应的model，pageNum对应当前页，pageSize为每页显示的数据，totalCount为数据总行数
		this.initPage(map, pageNum, pageSize, totalCount);
		System.out.println("当前页："+pageNum);
		System.out.println("页面显示条数："+pageSize);
		System.out.println("最大记录数："+totalCount);
		
		//list为我们需要显示的数据List,获取的返回值是我们常用的List<实体类>形式
		
		List list = mapper.getCustomerList(map);
		//list为我们的需要显示的数据List
		//初始化结果
		this.initResult(model, list, map);

		return view;
	}
	//增加用户信息，页面跳转
	@RequestMapping("/create")
	public String getCreateCustomer(HttpServletRequest request)
	{
		String view =initMenu(request, "customer/create");
		return view;
	}
	//增加用户信息，页面提交
	@RequestMapping("/createCustomer")
	public String getCreateCustomer(HttpServletRequest request,CustomerInfo cf)
	{
		String view = "";
		String customer_name=cf.getCustomer_name();
		//新增时,检查customer_name是否已经存在
		if(mapper.getCustomerInfoOk(customer_name))
		{
			System.out.println("该用户已存在！");
			JOptionPane.showMessageDialog(null, "该用户已经存在，请重新输入！");
			return "redirect:/customer/create";
			
		}
		
		if(mapper.getCreateCustomer(cf))
		{
			System.out.println("客户增加成功！");
			JOptionPane.showMessageDialog(null, "客户增加成功！");
			view="redirect:/customer/index";
		}else {
			view="error/503";
		}
		return view;
	}
	
	
	
	//修改用户信息，页面跳转
	@RequestMapping("/update/{customer_id}")
	public String getUpdateCustomer(HttpServletRequest request,@PathVariable int customer_id)
	{
		String view =initMenu(request, "customer/update");
		
		//查询客户信息
		CustomerInfo cusInfo = mapper.getCustomerById(customer_id);
		System.out.println("city_name:"+cusInfo.getCustomer_name());
		
		request.setAttribute("cusinfo", cusInfo);
		return view;
	}
	//修改用户信息，页面提交
	@RequestMapping("/updateCustomer")
	public String getUpdateCustomer(HttpServletRequest request,CustomerInfo cf)
	{
		String view = "";
		if(mapper.getUpdateCustomer(cf))
		{
			System.out.println("成本修改成功！");
			view="redirect:/customer/manager";
		}else {
			view="error/503";
		}
		return view;
	}
	//删除客户信息
	@RequestMapping("/delete/{customer_id}")
	public String getDelCustomer(@PathVariable int customer_id)
	{
		if(mapper.getDelCustomer(customer_id))
		{
			System.out.println("客户信息删除成功！");
			return "redirect:/customer/manager";
		}else {
			return "redirect:/error/503";
		}
	}
	//查看客户信息
	@RequestMapping("/details/{customer_id}")
	public String getCustomerInfo(HttpServletRequest request,@PathVariable int customer_id)
	{
		String view = initMenu(request, "customer/details");
		
		CustomerInfo cusInfo = mapper.getCustomerById(customer_id);
		request.setAttribute("cusinfo", cusInfo);
		
		return view;
	}
	//修改密码，页面跳转
	@RequestMapping("/updatepass")
	public String getUpdateUserPass(HttpServletRequest request)
	{
		String view = initMenu(request, "customer/updatePass");
		return view;
	}
	//修改密码，页面提交
	@RequestMapping("/UpdateUserPass")
	public String getUpdateUserPassword(HttpServletRequest request,UserInfo uf)
	{
		if(mapper.getUpdateUserPass(uf))
		{
			System.out.println("密码修改成功！");
			JOptionPane.showMessageDialog(null, "密码修改成功！");
			return "redirect:/customer/index";
		}else {
			System.out.println("密码修改失败！");
			JOptionPane.showMessageDialog(null, "用户名或密码输入错误！");
			return "redirect:/error/503";
		}	
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
		List<MenuInfo> department = menuMapper.getSecondMenu(role_id, 3);
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
