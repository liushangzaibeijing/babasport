package cn.babasport.xiu.common.web.session;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * session的提供类
 * @author xieqixiu
 *
 */
public class HttpSessionProvider implements SessionProvider {

	//设置session属性
	@Override
	public void setAttribute(HttpServletRequest request, String name, Object value) {
        HttpSession session = request.getSession();  //从request中获取session，如果session存在则直接返回，否则创建一个新的session返回
        session.setAttribute(name,value);
	}

	//获取session属性
	@Override
	public Object getAttribute(HttpServletRequest request, String name) {
        HttpSession session = request.getSession(false); //获取已经存在的session,如果不存在不进行创建
		if(session!=null){
			return session.getAttribute(name);
		}
        
		return null;
	}

	//注销session 销毁session以及session对应的cookie
	@Override
	public void invalidation(HttpServletRequest request,HttpServletResponse response) {
		 HttpSession session = request.getSession(false);
	     if (session!=null) {
	          session.invalidate();
	          // 根据JSESSIONID 清除对应的Cookie
              
	          //获取JSESSIONID
	          String sessionId = session.getId();
	          //清除cookie思路：构造一个新的同名的cookie，设置其时间为0
	          Cookie cookie = new Cookie("JSESSIONID",sessionId);
	          cookie.setPath("/");
	          cookie.setMaxAge(0);
	          response.addCookie(cookie);
	     }
	}

	
	//获取jsonId
	@Override
	public String getSessionId(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
	     if (session!=null) {
	         //获取JSESSIONID
	         return session.getId();
	     }
		return null;
	}
   

	
	
	
	
}
