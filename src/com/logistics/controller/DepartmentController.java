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
import org.springframework.web.bind.annotation.ResponseBody;

import com.logistics.interceptor.MemberInterceptor;
import com.logistics.mapper.DepartmentMapper;
import com.logistics.mapper.MenuMapper;
import com.logistics.model.CityInfo;
import com.logistics.model.DepartmentInfo;
import com.logistics.model.MenuInfo;
import com.logistics.model.UserInfo;


@Controller
@RequestMapping("/department")
public class DepartmentController extends BaseController{

	MenuMapper menuMapper = new MenuMapper();
	DepartmentMapper mapper = new DepartmentMapper();
	
	@RequestMapping("/index")
	public String Index(HttpServletRequest request)
	{
		String view = initMenu(request, "department/index");
		return view;
	}
	@RequestMapping("/managertwo")
	public String managertwo(HttpServletRequest request, Model model,
			@RequestParam(required = false) String searchInfo,
			@RequestParam(required = false) Integer pageNum,
			@RequestParam(required = false) Integer pageSize)
	{
		String view = initMenu(request, "department/manager");
		
		// 添加分页相关信息
		// 创建Map，来存放我们的数据，
		//我们这里四个参数分别为装载我们数据的model，条件查询内容，当前所在页码，每页显示多少行
		//创建Map，来存放我们的数据
		
		Map<String, Object> map = new HashMap<String, Object>();
		//这里的searchInfo是查询方法，在这里用不到，比如我们需要根据条件来查询我们的数据，这里就用不到了
		//其实这个searchInfo就是我们动态查询时的查询条件，这里无用
		map.put("searchInfo", searchInfo);
		
		//取出数据总数，
		Integer totalCount = mapper.getDepartmentCount();
		
		//初始化分页数据，map对应的model，pageNum对应当前页，pageSize为每页显示的数据，totalCount为数据总行数
		this.initPage(map, pageNum, pageSize, totalCount);
		System.out.println("当前页："+pageNum);
		System.out.println("页面显示条数："+pageSize);
		System.out.println("最大记录数："+totalCount);
		
		//list为我们需要显示的数据List,获取的返回值是我们常用的List<实体类>形式
		
		List list = mapper.getDepartmentList(map);
		//list为我们的需要显示的数据List
		//初始化结果
		this.initResult(model, list, map);
		return view;
	}
	//创建分拣中心
	@RequestMapping("/createtwo")
	public String createDepartment(HttpServletRequest request)
	{
		String view = initMenu(request, "department/create");
		
		//获取城市列表
		List<CityInfo> cityInfos = mapper.getCityInfo();
		List<CityInfo> cityAreaInfos = mapper.getAreaCityInfo(1);
		//List<CityInfo> cityinfo2 = cityMapper.getAreaCityInfo(2);
		
		//获取管理员列表
		List<UserInfo> userlist=mapper.getSetCenterManagerList();
		System.out.println(userlist);
		request.setAttribute("citylist", cityInfos);
		request.setAttribute("arealist", cityAreaInfos);
		request.setAttribute("userlist", userlist);
		
		return view;
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
			List<CityInfo> cityinfo=mapper.getAreaCityInfo(city_id);
			System.out.println("Json数据获取成功！"+city_id);
			return cityinfo;
		}
	
	
	
	//创建二级分拣中心
	@RequestMapping("/createDepartwo")
	public String createtwo(HttpServletRequest request,DepartmentInfo df)
	{
		String view = initMenu(request, "department/index");
		System.out.println("区县Id为："+df.getDistrict_name());
		if(mapper.createDepartmentCenter(df))
		{
			JOptionPane.showMessageDialog(null, "二级分拣中心创建成功！");
			System.out.println("二级分拣中心创建成功！");
			
		}else {
			view="redirect:/error/503";
		}
		return view;
	}
	//  修改二级分拣中心,页面跳转，信息获取
	@RequestMapping("/update/{department_id}")
	public String updateDepartmentById(HttpServletRequest request,@PathVariable int department_id)
	{
		String view = initMenu(request, "department/update");
		
		//根据ID获取二级分拣中心信息
		DepartmentInfo dfinfo = mapper.getDepartmentById(department_id);
		
		//获取ranger_id，可用于修改表ls_centerranger中的 manager_id和department_id
		int manager_id = dfinfo.getManager_id();
		int ranger_id =mapper.getCenterRanger(manager_id);
		
		//将ranger_id加入到df对象中，传递ranger_id
		dfinfo.setRanger_id(ranger_id);
		request.setAttribute("departmentInfo",dfinfo );
		
		System.out.println("getRanger_id:"+dfinfo.getRanger_id());
		System.out.println("getDepartment_id:"+dfinfo.getDepartment_id());
		
		
		//获取城市列表
		List<CityInfo> cityInfos = mapper.getCityInfo();
		List<CityInfo> cityAreaInfos = mapper.getAreaCityInfo(1);
		//List<CityInfo> cityinfo2 = cityMapper.getAreaCityInfo(2);
		
		//获取管理员列表
		List<UserInfo> userlist=mapper.getSetCenterManagerList();
		
		System.out.println(userlist);
		request.setAttribute("citylist", cityInfos);
		request.setAttribute("arealist", cityAreaInfos);
		request.setAttribute("userlist", userlist);
		
		return view;
	}
	//修改二级分拣中心信息
	@RequestMapping("/updateDepartment")
	public String updateDepartment(HttpServletRequest request,DepartmentInfo df)
	{
		
		
		if(mapper.getUpdateDepartment(df))
		{
			System.out.println("二级分拣中心信息修改成功！");
			return "redirect:/department/managertwo";
		}else {
			System.out.println("二级分拣中心信息修改失败！");
			return "error/503";
		}	
	}
	//根据ID查看二级分拣中心信息
	@RequestMapping("/details/{department_id}")
	public String Details(@PathVariable int department_id,HttpServletRequest request)
	{
		String view = initMenu(request, "department/details");
		DepartmentInfo dfinfo =mapper.getDepartmentById(department_id);
		request.setAttribute("departmentInfo", dfinfo);
		if(dfinfo==null)
		{
			System.out.println("二级分拣中心信息查询失败！");
			return "redirect:/error/503";
		}else {
			System.out.println("getDepartment_name:"+dfinfo.getDepartment_name());
			System.out.println("二级分拣中心信息查询成功！");
			
		}
		
		return view;
	}
	//删除二级分拣中心
	@RequestMapping("/delete/{department_id}")
	public String getDelDepartment(@PathVariable int department_id)
	{
		if(mapper.getDelDepartment(department_id))
		{
			System.out.println("删除成功！");
			return "redirect:/department/managertwo";
		}else {
			System.out.println("删除失败！");
			return "redirect:/error/503";
		}
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
		List<MenuInfo> department = menuMapper.getSecondMenu(role_id, 5);
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
