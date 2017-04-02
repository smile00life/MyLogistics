package com.logistics.controller;


import java.util.HashMap;
import java.util.List;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.logistics.annotation.SystemControllerLog;
import com.logistics.interceptor.MemberInterceptor;
import com.logistics.mapper.AnnounceMapper;
import com.logistics.mapper.MenuMapper;
import com.logistics.model.AnnounceInfo;
import com.logistics.model.MenuInfo;
import com.logistics.model.UserInfo;


/*
 * 公告控制页面
 * 
 * */
@Controller
@RequestMapping("/announce")
public class AnnounceController extends BaseController{

	MenuMapper menuMapper = new MenuMapper();
	AnnounceMapper announceMapper = new AnnounceMapper();
	@RequestMapping("/index")
	@SystemControllerLog(description = "进入公告管理首页")
	public String CusIndex(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		//从seesion中获取数据
		UserInfo userInfo = (UserInfo)session.getAttribute(MemberInterceptor.SESSION_MEMBER);
		int role_id = userInfo.getRole_id();
		String username = userInfo.getUsername();
			//生成二级页面菜单，role_id可以从session中获得，第二个参数为upMenuId,这里设置为1
			List<MenuInfo> siderlist = menuMapper.getSecondMenu(role_id, 0);
			//生产管理目录权限,role_id可以从session中获得，第二个参数为upMenuId,这里设置为1
			List<MenuInfo> department = menuMapper.getSecondMenu(role_id, 1);
			
			
			
			
			
			//将读取到的数据传到前端页面，使用jstl调用
			request.setAttribute("siderlist", siderlist);
			request.setAttribute("department", department);
			//传递用户名
			request.setAttribute("welcome", username);
			
			if(siderlist==null&&department==null)
			{
				System.out.println("错误！AnnounceContoller");
				return null;
			}else {
				System.out.println("成功！AnnounceContoller");
				return "announce/index";
			}
	}	
			// 公告管理列表
			@RequestMapping("/manager")
			public String managerAnnounce(HttpServletRequest request, Model model,
					@RequestParam(required = false) String searchInfo,
					@RequestParam(required = false) Integer pageNum,
					@RequestParam(required = false) Integer pageSize) {
				// 初始化菜单信息
				String view = initMenu(request, "announce/manager");
				// 添加分页相关信息
				// 创建Map，来存放我们的数据，
				//我们这里四个参数分别为装载我们数据的model，条件查询内容，当前所在页码，每页显示多少行
				//创建Map，来存放我们的数据
				
				Map<String, Object> map = new HashMap<String, Object>();
				//这里的searchInfo是查询方法，在这里用不到，比如我们需要根据条件来查询我们的数据，这里就用不到了
				//其实这个searchInfo就是我们动态查询时的查询条件，这里无用
				map.put("searchInfo", searchInfo);
				
				//取出数据总数，
				Integer totalCount = announceMapper.getAnnounceCount();
				
				//初始化分页数据，map对应的model，pageNum对应当前页，pageSize为每页显示的数据，totalCount为数据总行数
				this.initPage(map, pageNum, pageSize, totalCount);
				System.out.println("当前页："+pageNum);
				System.out.println("页面显示条数："+pageSize);
				System.out.println("最大记录数："+totalCount);
				
				//list为我们需要显示的数据List,获取的返回值是我们常用的List<实体类>形式
				
				List list = announceMapper.getAnounceList(map);
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
				List<MenuInfo> department = menuMapper.getSecondMenu(role_id, 1);
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
			
			//增加公告信息
			@RequestMapping("/create")
			@SystemControllerLog(description = "新增公告")
			public String createAnnounce(HttpServletRequest request)
			{
				String view= initMenu(request, "announce/create");
				return view;
			}
			@RequestMapping("/createAnnounce")
			public String createAnnounces(HttpServletRequest request,AnnounceInfo af)
			{
				System.out.println(af.getAnnounce_Content()+"\n"+af.getAnnounce_Title()+"\n"+af.getManager_id());
				
				if(announceMapper.createAnnounce(af))
				{
					System.out.println("公告标题为:"+af.getAnnounce_Title());
					//JOptionPane.showMessageDialog(null, "添加成功！");
					return "redirect:/announce/manager";
				}else {
					return "redirect:/error/503";
				}
				
			}
			
			//删除公告
			@RequestMapping("/delAnnounce/{announce_id}")
			@SystemControllerLog(description = "删除公告")
			public String delAnnounce(@PathVariable int announce_id)
			{
				String view="";
				if(announceMapper.delAnnounce(announce_id))
				{
					view="redirect:/announce/manager";
					JOptionPane.showMessageDialog(null, "删除成功！");
				}else {
					view="redirect:/error/503";
				}
				return view;
			}
			
			//编辑公告
			//根据announce_id查找公告信息
			@RequestMapping("/update/{announce_Id}")
			
			public String getAnnounceById(@PathVariable int announce_Id,HttpServletRequest request)
			{
				String view= initMenu(request, "announce/update");
				AnnounceInfo announceInfo = announceMapper.getAnnounceById(announce_Id);
				request.setAttribute("announceInfo",announceInfo);
				return view;
			}
			//修改公告
			@RequestMapping("/updateAnnounce")
			@SystemControllerLog(description = "修改公告")
			public String updateAnnounce(HttpServletRequest request,AnnounceInfo af)
			{
				if(announceMapper.updateAnnounce(af))
				{
					System.out.println("修改成功！");
					//JOptionPane.showMessageDialog(null, "修改成功！");
					return "redirect:/announce/manager";
				}else {
					System.out.println("修改失败！");
					return "redirect:/error/503";
				}	
			}
			//查看公告
			@RequestMapping("/details/{announce_Id}")
			@SystemControllerLog(description = "查看公告")
			public String showAnnounce(@PathVariable int announce_Id,HttpServletRequest request)
			{
				String view = initMenu(request, "announce/details");
				AnnounceInfo af = announceMapper.getAnnounceById(announce_Id);
				request.setAttribute("announce", af);
				System.out.println("公告查询成功！");
				System.out.println("公告标题为："+af.getAnnounce_Title());
				return view;
			}
			//查看公告,列表list
			@RequestMapping("/detailAnnounce")
			public String detailsAnnounce(HttpServletRequest request)
			{
				String view = initMenu(request, "待续");
				List<AnnounceInfo> AnnounceInfo=announceMapper.detailsAnnounceInfo();
				request.setAttribute("AnnounceInfo", AnnounceInfo);
				return view;
			}
	}

