package com.logistics.annotation;

import java.lang.reflect.Method;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;    
import org.springframework.web.context.request.ServletRequestAttributes; 




import com.logistics.mapper.SystemLog4jMapper;
import com.logistics.model.SystemLog4jInfo;
import com.logistics.model.UserInfo;
import com.logistics.interceptor.*;


/**
 * 切点类
 * 
 * @author eaglet
 * @since 2016年11月22日
 * 注意：mvc配置文件中，要将包com.logistics.annotation扫描，否则aop不能生效
 */
@Aspect
@Component
public class SystemLogAspect {
	
	
	SystemLog4jMapper sysLogMapper=new SystemLog4jMapper();

	// 定义Controller层切点
	@Pointcut("@annotation(com.logistics.annotation.SystemControllerLog)")
	public void controllerAspect() {
		//切点不需要写任何方法内容
	}

	//Service层切点  ,此处未用到
    /*@Pointcut("@annotation(com.annotation.SystemServiceLog)")    
     public  void serviceAspect() {    
    } */
	/**
	 * 前置通知 用于拦截Controller层记录用户的操作
	 * 
	 * @param joinPoint
	 *            切点
	 */
	@Before("controllerAspect()")
	public void doBefore(JoinPoint joinPoint) {
         //System.out.println("程序执行之前进行的操作");
	}

	/**
	 * 执行完成后通知
	 * 
	 * @param joinPoint
	 *            切点
	 */
	@AfterReturning("controllerAspect()")
	public void doAfterReturning(JoinPoint joinPoint) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		
		UserInfo u=(UserInfo)session.getAttribute(MemberInterceptor.SESSION_MEMBER);
		String user="";
		//请求客户端ip地址
		String ip = request.getRemoteAddr();    
		if(u!=null){
			user=u.getUsername();
		}
		
		try {
			
			String logDescript=getControllerMethodDescription(joinPoint);
			
			//System.out.println("程序执行成功之后执行的操作");
			System.out.println("日志信息");
			System.out.println("操作人："+user);;
			System.out.println("执行的操作为："+logDescript);
			System.out.println("请求IP:" + ip);
			
			//添加日志信息,保存入数据库
			SystemLog4jInfo si=new SystemLog4jInfo();
			si.setLog_user(user);
			si.setLog_message(logDescript);
			si.setIp_addr(ip);
			sysLogMapper.getCreateSystemLog(si);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
    
    /**  
     * 获取注解中对方法的描述信息 用于Controller层注解  
     *  
     * @param joinPoint 切点  
     * @return 方法描述  
     * @throws Exception  
     */    
     public  static String getControllerMethodDescription(JoinPoint joinPoint)  throws Exception {    
        String targetName = joinPoint.getTarget().getClass().getName();   
        //System.out.println(targetName);
        String methodName = joinPoint.getSignature().getName();    
        Object[] arguments = joinPoint.getArgs();    
        Class targetClass = Class.forName(targetName);    
        Method[] methods = targetClass.getMethods();    
        String description = "";    
         for (Method method : methods) {    
             if (method.getName().equals(methodName)) {    
                Class[] clazzs = method.getParameterTypes();    
                 if (clazzs.length == arguments.length) {    
                    description = method.getAnnotation(SystemControllerLog.class).description();    
                     break;    
                }    
            }    
        }    
         return description;    
    }  
}
