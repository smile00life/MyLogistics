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
import com.logistics.mapper.AnnounceMapper;
import com.logistics.mapper.MenuMapper;
import com.logistics.mapper.OrderMapper;
import com.logistics.mapper.TransportMapper;
import com.logistics.model.AnnounceInfo;
import com.logistics.model.MenuInfo;
import com.logistics.model.TransportInfo;
import com.logistics.model.UserInfo;
import com.logistics.model.WorkerInfo;

@Controller
@RequestMapping("/transport")
public class TransportController extends BaseController{

	MenuMapper menuMapper = new MenuMapper();
	TransportMapper mapper = new TransportMapper();
	@RequestMapping("/index")
	public String TransIndex(HttpServletRequest request)
	{
		String view = initMenu(request, "transport/index");
			
		return view;
			
	}	
	// 车辆管理列表
	@RequestMapping("/manager")
	public String managerTransport(HttpServletRequest request, Model model,
			@RequestParam(required = false) String searchInfo,
			@RequestParam(required = false) Integer pageNum,
			@RequestParam(required = false) Integer pageSize) {
		// 初始化菜单信息
		String view = initMenu(request, "transport/manager");
		// 添加分页相关信息
		// 创建Map，来存放我们的数据，
		//我们这里四个参数分别为装载我们数据的model，条件查询内容，当前所在页码，每页显示多少行
		//创建Map，来存放我们的数据
		
		Map<String, Object> map = new HashMap<String, Object>();
		//这里的searchInfo是查询方法，在这里用不到，比如我们需要根据条件来查询我们的数据，这里就用不到了
		//其实这个searchInfo就是我们动态查询时的查询条件，这里无用
		map.put("searchInfo", searchInfo);
		
		//取出数据总数，
		Integer totalCount = mapper.getTransportCount();
		
		//初始化分页数据，map对应的model，pageNum对应当前页，pageSize为每页显示的数据，totalCount为数据总行数
		this.initPage(map, pageNum, pageSize, totalCount);
		System.out.println("当前页："+pageNum);
		System.out.println("页面显示条数："+pageSize);
		System.out.println("最大记录数："+totalCount);
		
		//list为我们需要显示的数据List,获取的返回值是我们常用的List<实体类>形式
		
		List list = mapper.getTransportList(map);
		//list为我们的需要显示的数据List
		//初始化结果
		this.initResult(model, list, map);

		return view;
	}

	public String initMenu(HttpServletRequest request, String pager) {
		// 获取session内的用户信息
		HttpSession session = request.getSession();
		UserInfo u = (UserInfo) session
				.getAttribute(MemberInterceptor.SESSION_MEMBER);
		// 登录管理用户名
		String logname = u.getUsername();
		int role_id = u.getRole_id();
		System.out.println(role_id);
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
		List<MenuInfo> department = menuMapper.getSecondMenu(role_id, 8);
		// 将取到的数据传递到前端页面，使用jstl调用
		request.setAttribute("menulist", menulist);
		request.setAttribute("siderlist", siderlist);
		request.setAttribute("department", department);
		// 登录的用户名放入这个对象中，传递给前端
		request.setAttribute("welcome", logname);
		request.setAttribute("manager_id", manager_id);
		request.setAttribute("manager_name", manager_name);

		return pager;
	}
	
	//增加车辆信息
	@RequestMapping("/create")
	public String getCreateTransport(HttpServletRequest request)
	{
		OrderMapper mapperOrderMapper = new OrderMapper();
		String view= initMenu(request, "transport/create");
		
		//获取司机，派送员
		//获取员工列表,派送员
		List<WorkerInfo> workersender=mapperOrderMapper.getWorkerInfo(5);
		request.setAttribute("workersender", workersender);
		
		return view;
	}
	@RequestMapping("/getCreateTransport")
	public String getCreateTransports(HttpServletRequest request,TransportInfo tf)
	{
		
		if(mapper.getCreateTransport(tf))
		{
			System.out.println("车辆类型为:"+tf.getTransport_type());
			JOptionPane.showMessageDialog(null, "添加成功！");
			return "redirect:/transport/manager";
		}else {
			return "redirect:/error/503";
		}
		
	}
	
	//删除车辆
	@RequestMapping("/getDelTransport/{transport_id}")
	public String getDelTransport(@PathVariable int transport_id)
	{
		String view="";
		if(mapper.getDelTransport(transport_id))
		{
			view="redirect:/transport/manager";
			JOptionPane.showMessageDialog(null, "删除成功！");
		}else {
			view="redirect:/error/503";
		}
		return view;
	}
	
	//编辑车辆
	//根据announce_id查找公告信息
	@RequestMapping("/update/{transport_id}")
	public String getTransportById(@PathVariable int transport_id,HttpServletRequest request)
	{
		OrderMapper mapperOrderMapper = new OrderMapper();
		String view= initMenu(request, "transport/update");
		TransportInfo transportInfo = mapper.getTransportById(transport_id);
		request.setAttribute("transportinfo",transportInfo);
		//获取司机，派送员
		//获取员工列表,派送员
		List<WorkerInfo> workersender=mapperOrderMapper.getWorkerInfo(5);
		request.setAttribute("workersender", workersender);
		
		return view;
	}
	//修改车辆
	@RequestMapping("/updateTransport")
	public String getUpdateTransport(HttpServletRequest request,TransportInfo tf)
	{
		if(mapper.getUpdateTransport(tf))
		{
			System.out.println("车辆信息修改成功！");
			JOptionPane.showMessageDialog(null, "车辆信息修改成功！");
			return "redirect:/transport/manager";
		}else {
			System.out.println("车辆信息修改失败！");
			return "redirect:/error/503";
		}	
	}
	//查看车辆
	@RequestMapping("/details/{transport_id}")
	public String getTransportInfo(@PathVariable int transport_id,HttpServletRequest request)
	{
		String view = initMenu(request, "transport/details");
		TransportInfo tf = mapper.getTransportById(transport_id);
		request.setAttribute("transportinfo", tf);
		System.out.println("车辆查询成功！");
		System.out.println("车辆名称为："+tf.getTransport_name());
		return view;
	}
}
