package com.logistics.controller;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import org.jbarcode.util.ImageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.logistics.annotation.SystemControllerLog;
import com.logistics.interceptor.MemberInterceptor;
import com.logistics.mapper.CityMapper;
import com.logistics.mapper.CostMapper;
import com.logistics.mapper.MenuMapper;
import com.logistics.mapper.OrderMapper;
import com.logistics.mapper.SystemMapper;
import com.logistics.model.CityInfo;
import com.logistics.model.CostInfo;
import com.logistics.model.CustomerInfo;
import com.logistics.model.MenuInfo;
import com.logistics.model.OrderInfo;
import com.logistics.model.SystemInfo;
import com.logistics.model.UserInfo;
import com.logistics.model.WorkerInfo;
import com.logistics.util.BarcodeHelper;
import com.logistics.util.GetId;

@Controller
@RequestMapping("/order")

public class OrderController extends BaseController{
	MenuMapper menuMapper = new MenuMapper();
	OrderMapper mapper = new OrderMapper();
	
	@RequestMapping("/index")
	@SystemControllerLog(description = "订单管理首页")
	public String Index(HttpServletRequest request)
	{
		String view = initMenu(request, "order/index");
		return view;
	}
	@RequestMapping("/manager")
	public String managerOrder(HttpServletRequest request, Model model,
			@RequestParam(required = false) String searchInfo,
			@RequestParam(required = false) Integer pageNum,
			@RequestParam(required = false) Integer pageSize) {
		// 初始化菜单信息
		String view = initMenu(request, "order/manager");
		// 添加分页相关信息
		// 创建Map，来存放我们的数据，
		//我们这里四个参数分别为装载我们数据的model，条件查询内容，当前所在页码，每页显示多少行
		//创建Map，来存放我们的数据
		
		Map<String, Object> map = new HashMap<String, Object>();
		//这里的searchInfo是查询方法，在这里用不到，比如我们需要根据条件来查询我们的数据，这里就用不到了
		//其实这个searchInfo就是我们动态查询时的查询条件，这里无用
		map.put("searchInfo", searchInfo);
		
		//取出数据总数，
		Integer totalCount = mapper.getOrderCount();
		
		//初始化分页数据，map对应的model，pageNum对应当前页，pageSize为每页显示的数据，totalCount为数据总行数
		this.initPage(map, pageNum, pageSize, totalCount);
		System.out.println("当前页："+pageNum);
		System.out.println("页面显示条数："+pageSize);
		System.out.println("最大记录数："+totalCount);
		
		//list为我们需要显示的数据List,获取的返回值是我们常用的List<实体类>形式
		
		List list = mapper.getOrderList(map);
		//list为我们的需要显示的数据List
		//初始化结果
		this.initResult(model, list, map);

		return view;
	}
	//增加订单信息，页面跳转
	@RequestMapping("/create")
	
	public String getCreateOrder(HttpServletRequest request)
	{
		CityMapper cityMapper = new CityMapper();
		String view =initMenu(request, "order/create");
		//获取客户信息
		List<CustomerInfo> cusinfo = mapper.getCustomerInfo();
		request.setAttribute("cusinfo", cusinfo);
		
	    //获取城市列表
		List<CityInfo> cityInfos = cityMapper.getCityInfo();
		List<CityInfo> cityAreaInfos = cityMapper.getAreaCityInfo(1);
		request.setAttribute("citylist", cityInfos);
		request.setAttribute("arealist", cityAreaInfos);
		
		//获取员工列表,派送员,揽件员
		List<WorkerInfo> workersender=mapper.getWorkerInfo(5);
		List<WorkerInfo> workerreceiver=mapper.getWorkerInfo(4);
		request.setAttribute("workersender", workersender);
		request.setAttribute("workerreceiver", workerreceiver);
		
		//获取付款状态
		List<OrderInfo> orderpay = mapper.getOrderPay();
		request.setAttribute("orderpay", orderpay);
		
		//获取商品信息
		List<OrderInfo> goodsList = mapper.getGoodsLists();
		request.setAttribute("goodsList", goodsList);
		
		
		return view;
	}
	//增加订单信息，页面提交
	@RequestMapping("/createOrder")
	@SystemControllerLog(description = "创建订单")
	public String getCreateCosts(HttpServletRequest request,OrderInfo cf) throws IOException
	{
		String view = "";
		/*String order_code=cf.getOrder_code();
		//新增时,检查order_code是否已经存在
		if(mapper.getOrderInfoOk(order_code))
		{
			System.out.println("订单号已存在！");
			JOptionPane.showMessageDialog(null, "该订单号已经存在，请重新输入！");
			return "redirect:/order/create";
			
		}*/
/**************************************************获取订单号，************************************************************/		
		
		//生成标识码，用于订单号,由HGF+当前日期，组成
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String imgName = "HGF"+sdf.format(new Date()) ;
		System.out.println("订单号："+imgName);
		cf.setOrder_code(imgName);*/
		//随机产生一个10位数，包括数字和字母
		String sid = GetId.ID(10);
		//生成订单号order_id，以SS开头
		String order_id = "SS"+sid;
		cf.setOrder_code(order_id);
/**************************************************生成二维码，并且获取二维码路径，没有务器地址************************************************************/		
		
		//二维码生成,并将二维码地址加到cf中
		//定义二维码标示符号
		//String myCode="TR0220101X090001";
		//服务器存储文件夹,E:\MyEclipsePRO\.metadata\.me_tcat7\webapps\IlogisticsSystem\ upload\img
	    String mypath="upload\\img";
		//生成二维码，保存在本地,返回地址，此处会抛出异常，throws IOException
		String path= BarcodeHelper.createBarCode15(request, mypath, order_id);
		System.out.print("图片路径为："+path);
		cf.setImg_address(path);
/**************************************************获取文件下载路径，有服务器地址,此处没用到，此处用于比较，************************************************************/		
		//二维码下载地址,此处没用到，只是提示，用在查看订单处
		//定义文件夹名称
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String uploadSaveFolder = format.format(new Date());
		// 构造临时路径来存储上传的文件
		// 这个路径相对当前应用的目录  upload\img\2016-10-12\
		String uploadFile2=mypath+"\\"+uploadSaveFolder+"\\";
		//定义图片名称，与createBarCode15中的命名规则保持一致
		SimpleDateFormat imgname = new SimpleDateFormat("yyyyMMddhhmmss");
		String ImgName = imgname.format(new Date()) + "_" + order_id+"." + ImageUtil.JPEG;
		//增加服务器地址
		String uploadFile2Database =BarcodeHelper.setrootPaths(request, uploadFile2);
		//savefilepath 为最终图片本地地址，有服务器地址，与path的区别在于，有服务器地址
		String savefilepath=uploadFile2Database+ImgName;
		
		
		//判断订单是否增加成功！
		if(mapper.getCreateOrder(cf))
		{
			System.out.println("订单增加成功！");
			//JOptionPane.showMessageDialog(null, "订单增加成功！");
			view="redirect:/order/manager";
		}else {
			view = "redirect:/error/503";
		}
		return view;
	}
	//json请求数据，查询城市二级列表
	@ResponseBody
	@RequestMapping(value="/getAreaJsonDataByCityId",produces="application/json")
	private List<CityInfo> getCityList(int city_id) {
		// TODO Auto-generated method stub
		CityMapper cityMapper = new CityMapper();
		//转化成json数据
		List<CityInfo> cityinfo=cityMapper.getAreaCityInfo(city_id);
		System.out.println("Json数据获取成功！"+city_id);
		return cityinfo;
	}
	
	
	
	
	
	
	//修改订单信息，页面跳转
	@RequestMapping("/update/{order_id}")
	public String getUpdateOrder(HttpServletRequest request,@PathVariable int order_id)
	{
		CityMapper cityMapper = new CityMapper();
		String view =initMenu(request, "order/update");
		
		//查询订单信息
		OrderInfo orderInfo = mapper.getOrderById(order_id);
		System.out.println("order_code:"+orderInfo.getOrder_code());
		
		request.setAttribute("orderinfo", orderInfo);
		
		
		//获取客户信息
		List<CustomerInfo> cusinfo = mapper.getCustomerInfo();
		request.setAttribute("cusinfo", cusinfo);
		
	    //获取城市列表
		List<CityInfo> cityInfos = cityMapper.getCityInfo();
		List<CityInfo> cityAreaInfos = cityMapper.getAreaCityInfo(1);
		request.setAttribute("citylist", cityInfos);
		request.setAttribute("arealist", cityAreaInfos);
		
		//获取员工列表,派送员,揽件员
		List<WorkerInfo> workersender=mapper.getWorkerInfo(5);
		List<WorkerInfo> workerreceiver=mapper.getWorkerInfo(4);
		request.setAttribute("workersender", workersender);
		request.setAttribute("workerreceiver", workerreceiver);
		
		//获取付款状态
		List<OrderInfo> orderpay = mapper.getOrderPay();
		request.setAttribute("orderpay", orderpay);
		
		
		
		return view;
	}
	//修改订单信息，页面提交
	@RequestMapping("/updateOrder")
	@SystemControllerLog(description = "修改订单")
	public String getUpdateOrders(HttpServletRequest request,OrderInfo cf)
	{
		String view = "";
		if(mapper.getUpdateOrder(cf))
		{
			System.out.println("修改订单成功！");
			//JOptionPane.showMessageDialog(null, "修改订单成功！");
			view="redirect:/order/manager";
		}else {
			view="error/503";
		}
		return view;
	}
	//删除订单信息
	@RequestMapping("/delete/{order_id}")
	@SystemControllerLog(description = "删除订单")
	public String getDelOrder(@PathVariable int order_id)
	{
		if(mapper.getDelOrder(order_id))
		{
			System.out.println("订单删除成功！");
			JOptionPane.showMessageDialog(null, "订单删除成功！");
			return "redirect:/order/manager";
		}else {
			return "redirect:/error/503";
		}
	}
	//查看订单
	@RequestMapping("/details/{order_id}")
	@SystemControllerLog(description = "查看订单")
	public String getOrderInfo(HttpServletRequest request,@PathVariable int order_id)
	{
		SystemMapper systemMapper= new SystemMapper();
		CostMapper costMapper = new CostMapper();
		String view = initMenu(request, "order/details");
		
		OrderInfo orderInfo = mapper.getOrderById(order_id);
		request.setAttribute("orderinfo", orderInfo);
		System.out.println("图片地址"+orderInfo.getImg_address());
		//获取订单状态
		/*OrderInfo orderpay=mapper.getOrderPayOk(order_id);
		request.setAttribute("orderpay", orderpay);*/
		
		//获取公司信息,邮编、名称、地址、联系方式
		SystemInfo systemInfos = systemMapper.getSystemInfos();
		request.setAttribute("sysinfo", systemInfos);
		
		//获取商品信息
		String goods_name = orderInfo.getGoods_name();
		OrderInfo goods_info = mapper.getGoodsByName(goods_name); 
		request.setAttribute("goods_info", goods_info);
		
		//获取价格详情,根据 city_code 查询价格，这里的city_code 是 cost_info 中的属性,等价于code_info表中的city_name
		String city_code = orderInfo.getCity_name();
		CostInfo costInfo = costMapper.getCostByCode(city_code);
		request.setAttribute("costInfo", costInfo);
		//System.out.println("每公斤加价："+costInfo.getCost_per());
			
		return view;
	}
	
	/**********************************************小 曹 君 分割线！！！！***************************************************************/
	//商品管理
	@RequestMapping("/managergoods")
	@SystemControllerLog(description = "管理商品")
	public String managerGoods(HttpServletRequest request, Model model,
			@RequestParam(required = false) String searchInfo,
			@RequestParam(required = false) Integer pageNum,
			@RequestParam(required = false) Integer pageSize) {
		// 初始化菜单信息
		String view = initMenu(request, "order/managergoods");
		// 添加分页相关信息
		// 创建Map，来存放我们的数据，
		//我们这里四个参数分别为装载我们数据的model，条件查询内容，当前所在页码，每页显示多少行
		//创建Map，来存放我们的数据
		
		Map<String, Object> map = new HashMap<String, Object>();
		//这里的searchInfo是查询方法，在这里用不到，比如我们需要根据条件来查询我们的数据，这里就用不到了
		//其实这个searchInfo就是我们动态查询时的查询条件，这里无用
		map.put("searchInfo", searchInfo);
		
		//取出数据总数，
		Integer totalCount = mapper.getGoodsCount();
		
		//初始化分页数据，map对应的model，pageNum对应当前页，pageSize为每页显示的数据，totalCount为数据总行数
		this.initPage(map, pageNum, pageSize, totalCount);
		System.out.println("当前页："+pageNum);
		System.out.println("页面显示条数："+pageSize);
		System.out.println("最大记录数："+totalCount);
		
		//list为我们需要显示的数据List,获取的返回值是我们常用的List<实体类>形式
		
		List list = mapper.getGoodsList(map);
		//list为我们的需要显示的数据List
		//初始化结果
		this.initResult(model, list, map);

		return view;
	}
	
	//增加商品信息，页面跳转
		@RequestMapping("/creategoods")
		
		public String getCreateGoods(HttpServletRequest request)
		{
			
			String view =initMenu(request, "order/creategoods");
			return view;
		}
		//增加商品信息，页面提交
		@RequestMapping("/createGoods")
		@SystemControllerLog(description = "添加商品")
		public String getCreateGood(HttpServletRequest request,OrderInfo cf)
		{
			String view = "";
			if(mapper.getCreateGoods(cf))
			{
				System.out.println("商品增加成功！");
				JOptionPane.showMessageDialog(null, "商品增加成功！");
				view="redirect:/order/index";
			}else {
				view="error/503";
			}
			return view;
		}
		//修改商品信息，页面跳转
		@RequestMapping("/updateGoodId/{goods_id}")
		public String getUpdateGoods(HttpServletRequest request,@PathVariable int goods_id)
		{
			
			String view =initMenu(request, "order/updategoods");
			
			//查询商品信息
			OrderInfo goodsInfo = mapper.getGoodsById(goods_id);
			System.out.println("goods_name:"+goodsInfo.getGoods_name());
			
			request.setAttribute("goodsinfo", goodsInfo);
			
			
			
			return view;
		}
		//修改商品信息，页面提交
		@RequestMapping("/updateGoods")
		@SystemControllerLog(description = "修改商品")
		public String getUpdateGood(HttpServletRequest request,OrderInfo cf)
		{
			String view = "";
			if(mapper.getUpdateGoods(cf))
			{
				System.out.println("修改商品成功！");
				JOptionPane.showMessageDialog(null, "商品信息修改成功！");
				view="redirect:/order/managergoods";
			}else {
				System.out.println("修改商品失败！");
				view="error/503";
			}
			return view;
		}
		//删除商品信息
		@RequestMapping("/deleteGoods/{goods_id}")
		@SystemControllerLog(description = "删除商品")
		public String getDelGoods(@PathVariable int goods_id)
		{
			if(mapper.getDelGoods(goods_id))
			{
				System.out.println("商品删除成功！");
				JOptionPane.showMessageDialog(null, "商品删除成功！");
				return "redirect:/order/managergoods";
			}else {
				return "redirect:/error/503";
			}
		}
		//查看商品
		@RequestMapping("/detailsGoods/{goods_id}")
		@SystemControllerLog(description = "查看商品")
		public String getGoodsById(HttpServletRequest request,@PathVariable int goods_id)
		{
			
			String view = initMenu(request, "order/detailsgoods");
			
			OrderInfo goodsInfo = mapper.getGoodsById(goods_id);
			request.setAttribute("goodsinfo", goodsInfo);
			return view;
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
		//List<MenuInfo> menulist = menuMapper.getTopMenu(role_id);
		List<MenuInfo> siderlist = menuMapper.getSecondMenu(role_id, 0);
		List<MenuInfo> department = menuMapper.getSecondMenu(role_id, 7);
		// 将取到的数据传递到前端页面，使用jstl调用
		//request.setAttribute("menulist", menulist);
		request.setAttribute("siderlist", siderlist);
		request.setAttribute("department", department);
		// 登录的用户名放入这个对象中，传递给前端
		request.setAttribute("welcome", logname);
		request.setAttribute("manager_id", manager_id);
		request.setAttribute("manager_name", manager_name);

		return page;
		
	}
	

}
