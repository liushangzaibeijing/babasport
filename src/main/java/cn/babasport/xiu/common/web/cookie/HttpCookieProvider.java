package cn.babasport.xiu.common.web.cookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.deser.Deserializers.Base;
import com.sun.jersey.core.util.Base64;

import cn.babasport.xiu.common.constant.CommonsConstant;
import cn.babasport.xiu.common.utils.JacksonUtils;

/**
 * session的提供类
 * @author xieqixiu
 *
 */
@Component
public class HttpCookieProvider implements CookieProvider{

	@Override
	public <T> T getCookieObj(HttpServletRequest request, Class<T> clazz) {
		T  t = null;
		//获取指定的cookie
    	Cookie[] cookies = request.getCookies();
    	if(cookies!=null&&cookies.length>0){
	    	for(Cookie cookie :cookies){
	    		if(CommonsConstant.BUYERCART.equals(cookie.getName())){
	    			String context = cookie.getValue();
	    			String result = new String(Base64.decode(context.getBytes()));
	    			t = JacksonUtils.deserialize(result, clazz);
	    			break;
	    		}
	    	}
    	}
		return t;
	}

	@Override
	public <T> void responseCookie(HttpServletResponse response, T t) {
		//设置cookie
    	//将购物车放置在cookie中
    	String result = JacksonUtils.serialize(t);
    	byte[] bytes = Base64.encode(result.getBytes());
    	Cookie cookie = new Cookie(CommonsConstant.BUYERCART, new String(bytes));
    	//后期使用加密解密算法  对cookie进行加密(后期安全考虑加上)
    	cookie.setPath("/");
    	cookie.setMaxAge(60*60*24*7);  //有效日期7天
    	response.addCookie(cookie);
    	
	}

	
	
}
