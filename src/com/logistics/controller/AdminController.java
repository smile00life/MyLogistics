package com.logistics.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;




import com.logistics.annotation.SystemControllerLog;
import com.logistics.interceptor.MemberInterceptor;
import com.logistics.mapper.MenuMapper;
import com.logistics.mapper.UserMapper;
import com.logistics.model.MenuInfo;
import com.logistics.model.UserInfo;
import com.logistics.util.BarcodeHelper;

@Controller
@RequestMapping("/admin")   //显示action默认在admin下，下面的action定义，就可以不用写了
public class AdminController {
    //后台管理首页
	
	UserMapper userMapper = new UserMapper();
	MenuMapper menuMapper = new MenuMapper();
	
	
	
	//后台首页管理
//	@RequestMapping("/index")
//	public ModelAndView index(){
//		
//		
//		
//		ModelAndView view = new ModelAndView("admin/index");
//		//生成测试菜单，默认权限为1-总管理权限role_id=1
//		List<MenuInfo> menulist = menuMapper.getTopMenu(1);
//		//生成二级页面菜单，
//		List<MenuInfo> siderlist = menuMapper.getSecondMenu(1, 1);
//		
//		//将读取到的数据传到前端页面，使用jstl调用
//		view.addObject("menulist",menulist);
//		view.addObject("siderlist", siderlist);
//		
//		if(menulist==null)
//		{
//			System.out.println("错误！AdminContoller");
//			return null;
//		}else {
//			System.out.println("成功！AdminContoller");
//			return view;
//		}
//		
//	}
	@RequestMapping("/index")
	//注解Aop做日志功能
	//@SystemControllerLog(description = "进入后台管理首页")
	public String index(HttpServletRequest request){
		
		HttpSession session = request.getSession();
		UserInfo userInfo = (UserInfo)session.getAttribute(MemberInterceptor.SESSION_MEMBER);
		//获取session中保存的用户名
		String logname = userInfo.getUsername();
		//获取role_id权限号
		int role_id=userInfo.getRole_id();
		
		//角色权限判断，比如权限为1，怎判断在数据库中是否有对应的路径
		if(!menuMapper.validateMenuPath(role_id, "admin/index"))
		{
			//无权访问，重定向跳转
			System.out.println("无访问权限！");
			return "redirect:admin/error/503";
		}
		
		//生成测试菜单，默认权限为1-总管理权限role_id=1
		//List<MenuInfo> menulist = menuMapper.getTopMenu(role_id);
		
		List<MenuInfo> siderlist = menuMapper.getSecondMenu(role_id, 0);
		//生产管理目录权限,role_id可以从session中获得，
		List<MenuInfo> department = menuMapper.getSecondMenu(role_id, 0);
		//将读取到的数据传到前端页面，使用jstl调用
		request.setAttribute("siderlist",siderlist);
		//request.setAttribute("menulist",menulist);
		request.setAttribute("welcome",logname);
		request.setAttribute("department",department);
		
		if(siderlist==null&&department==null)
		{
			//System.out.println("错误！AdminContoller");
			return null;
		}else {
			//System.out.println("成功！AdminContoller");
			return "admin/index";
		}
		
	}
	
	//二维码添加管理
	@RequestMapping("/testcode")
	@SystemControllerLog(description = "添加二维码")
	public String testcode(HttpServletRequest request) throws IOException{
		//ModelAndView view=new ModelAndView("admin/codetest");
		//条形码
		String myCode="TR0220101X090001";
		//服务器存储文件夹
		String mypath="upload\\code";
		
		
		
		//生成路径
		String path=BarcodeHelper.createBarCode15(request, mypath, myCode);
		
		List<String> list = new ArrayList<String>();
		System.out.print("图片路径为："+path);
		
		//将图片地址加到数据库中
		list.add(path);
		
		
		for(String s :list)
		{
			if(userMapper.logisticsimg(s))
			{
				System.out.print("添加成功！");
			}else{
				System.out.print("添加失败！");
			}
			
		}
		
		//打印输出
		System.out.println(path);
		
		request.setAttribute("ment", "增加成功");
		request.setAttribute("path", path);
		
		return "admin/codetest";
	}
	

}
