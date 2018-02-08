package cn.babasport.xiu.core.interceptor;

import java.util.Enumeration;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.babasport.xiu.common.constant.CommonsConstant;
import cn.babasport.xiu.common.web.session.SessionProvider;
import cn.babasport.xiu.core.bean.Buyer;

/**
 * 登录操作的拦截器
 * @author xieqixiu
 *
 */
public class LoginInterceptor  implements HandlerInterceptor{
	
	@Autowired
	private SessionProvider sessionProvider;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("进入handler方法之前...");
		//对所有的进入前台的页面都进行拦截 
		//（判断用户是否登录(从session中获取用户信息) 根据登录标识来显示 isLogin）
		Buyer user = (Buyer) sessionProvider.getAttribute(request, CommonsConstant.CURRENT_BUYER);
		
		boolean isLogin = false;
		if(user!=null){
			isLogin = true;
		}
		
		request.setAttribute("isLogin",isLogin);
		
		//从reuest获取URL URI两者的区别
		//获取请求url
		//String urlab = request.getRequestURL().toString();  //获取完整请求路径 
		String url = request.getRequestURI();    //获取请求路径
		Enumeration<String> paramNames = request.getParameterNames();
		String resultUrl = request.getRequestURL().toString();
		String paramUrl = null;
		
		if(paramNames.hasMoreElements()){
			String key = paramNames.nextElement();
			String value = request.getParameter(key);
			paramUrl = key+"="+value+"&";
			//System.out.println("查询字符串:"+key+" : "+value);
		}
		if(paramUrl!=null){
			paramUrl = paramUrl.substring(0, paramUrl.lastIndexOf("&"));		
			resultUrl+="?"+paramUrl;
		}
		//对于/buyer/*的所有请求拦截后根据isLogin标识判断
		if(url.indexOf("/buyer/")!=-1){
			//如果用户没有登录
			if(!isLogin){
				//重定向到登录页面（并将当前要访问的url作为returnUrl(参数)）
				response.sendRedirect(request.getContextPath()+"/front/user/login.shtml?returnUrl="+resultUrl);
			    return false;
			}
		}
		//如果用户登录 (存放用户名)
		if(isLogin){
			request.setAttribute("username", user.getUsername());
		}
		
		//如果用户已经登陆则直接放行(return true)
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("进入handler执行之后，生成视图之前执行...");
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
        System.out.println("最后执行！！！一般用于释放资源！！");		
	}

}
