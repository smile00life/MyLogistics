package com.logistics.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.logistics.interceptor.MemberInterceptor;
import com.logistics.mapper.MenuMapper;
import com.logistics.model.MenuInfo;
import com.logistics.model.UserInfo;

@Controller
@RequestMapping("/contract")
public class ContractController {

	MenuMapper menuMapper = new MenuMapper();
	@RequestMapping("/index")
	public String index(HttpServletRequest request)
	{
		String view = initMenu(request, "contract/index");
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
		List<MenuInfo> department = menuMapper.getSecondMenu(role_id, 9);
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
