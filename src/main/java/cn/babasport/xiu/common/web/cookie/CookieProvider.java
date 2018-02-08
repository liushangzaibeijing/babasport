package cn.babasport.xiu.common.web.cookie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * session的提供类
 * @author xieqixiu
 *
 */
public interface CookieProvider {
   
	//获取cookie中的信息
	public <T> T getCookieObj(HttpServletRequest request,Class<T> clazz);

	//响应cookie
	public <T> void  responseCookie(HttpServletResponse response,T t);

	
}
