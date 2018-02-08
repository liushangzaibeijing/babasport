package cn.babasport.xiu.common.web.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * session的提供类
 * @author xieqixiu
 *
 */
public interface SessionProvider {
   
	//设置session属性
	public void setAttribute(HttpServletRequest request,String name,Object value);

	//获取session属性
	public Object getAttribute(HttpServletRequest request,String name);

	//注销session 销毁session以及session对应的cookie
	public void invalidation(HttpServletRequest request,HttpServletResponse response);
	
	//获取jsonId
	public String getSessionId(HttpServletRequest request);
	
	
}
