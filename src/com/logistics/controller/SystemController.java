package com.logistics.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.logistics.interceptor.MemberInterceptor;
import com.logistics.mapper.MenuMapper;
import com.logistics.mapper.SystemMapper;
import com.logistics.model.MenuInfo;
import com.logistics.model.SystemInfo;
import com.logistics.model.UserInfo;


@Controller
@RequestMapping("/system")
public class SystemController {

	MenuMapper menuMapper = new MenuMapper();
	SystemMapper mapper = new SystemMapper();
	@RequestMapping("/index")
	public String index(HttpServletRequest request)
	{
		String view = initMenu(request, "system/index");
		return view;
	}
	//查询信息
	@RequestMapping("/manager")
	public String getSysInfo(HttpServletRequest request)
	{
		String view = initMenu(request, "system/manager");
	    List<SystemInfo> sf = mapper.getSystemInfo();
		request.setAttribute("systeminfo", sf);
		for(SystemInfo ss:sf)
		{
			System.out.println("公司名称："+ss.getCompany_name()+"识别码："+ss.getCompany_code());
		}
		return view;
	}
	//根据id查看公司信息
	@RequestMapping("/details/{company_id}")
	public String getSystemById(HttpServletRequest request,@PathVariable int company_id)
	{
		String view = initMenu(request, "system/details");
		
		SystemInfo sf = mapper.getSystemById(company_id);
		request.setAttribute("systeminfo", sf);
		return view;
	}
	@RequestMapping("/update/{company_id}")
	public String getSystemInfo(HttpServletRequest request,@PathVariable int company_id)
	{
		String view = initMenu(request, "system/update");
		
		SystemInfo sf = mapper.getSystemById(company_id);
		request.setAttribute("systeminfo", sf);
		return view;
	}
	//修改公司信息
	@RequestMapping("/updateSystem")
	public String getUpdateSystem(HttpServletRequest request,SystemInfo sf)
	{
        String view = initMenu(request, "system/manager");
		
        //修改后跳转到manager页面，需要查询信息，
        List<SystemInfo> sff = mapper.getSystemInfo();
		request.setAttribute("systeminfo", sff);
		
	    if(mapper.getUpdateSystem(sf))
	    {
	    	return view;
	    }else {
	    	return null;
		}
		
	}
	//修改密码
	
	@RequestMapping("/updatePass")
	public String updatePass(HttpServletRequest request)
	{
		String view = initMenu(request, "system/updatePass");
		
		
		return view;
	}
	
	@RequestMapping("/updatePassword")
	public String Updatepassword(HttpServletRequest request,UserInfo user)
	{
		String view = initMenu(request, "system/manager");
		//查询公司信息，页面传递到manager时需要
		List<SystemInfo> sff = mapper.getSystemInfo();
		request.setAttribute("systeminfo", sff);
		
		//验证密码
		if(mapper.getPassOk(user))
		{
			System.out.println("密码输入正确！");
		}else {
			System.out.println("密码输入错误！");
			JOptionPane.showMessageDialog(null, "密码输入错误，请重新输入！");
			view = "system/updatePass";
			return view;
		}
		
		if(mapper.updatePass(user))
		{
			System.out.println("密码修改成功！");
			JOptionPane.showMessageDialog(null, "密码修改成功！");
			view = "system/index";
		}else {
			JOptionPane.showMessageDialog(null, "密码修改失败，请重试！");
			System.out.println("密码修改失败！");
			view="system/updatePass";
		}
		return view;
	}
	
	
	
	//获取管理权限，传递菜单参数
	public String initMenu(HttpServletRequest request, String pager) {
		// 获取session内的用户信息
		HttpSession session = request.getSession();
		UserInfo u = (UserInfo) session
				.getAttribute(MemberInterceptor.SESSION_MEMBER);
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
		List<MenuInfo> department = menuMapper.getSecondMenu(role_id, 4);
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
}
