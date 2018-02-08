package cn.babasport.xiu.core.controller.front;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.babasport.xiu.common.constant.CommonsConstant;
import cn.babasport.xiu.common.spring.utils.Md5Pwd;
import cn.babasport.xiu.common.web.session.SessionProvider;
import cn.babasport.xiu.core.bean.Buyer;
import cn.babasport.xiu.core.bean.City;
import cn.babasport.xiu.core.bean.Province;
import cn.babasport.xiu.core.bean.Town;
import cn.babasport.xiu.core.bean.query.CityQuery;
import cn.babasport.xiu.core.bean.query.ProvinceQuery;
import cn.babasport.xiu.core.bean.query.TownQuery;
import cn.babasport.xiu.core.service.BuyerService;
import cn.babasport.xiu.core.service.CityService;
import cn.babasport.xiu.core.service.ProvinceService;
import cn.babasport.xiu.core.service.TownService;

/**
 * 用户相关的控制层
 * @author xieqixiu
 *
 */
@Controller
@RequestMapping("/front")
public class UserFrontController {
	@Autowired
	private SessionProvider sessionProvider;
	
	@Autowired
	private BuyerService buyerService;
	
	@Autowired
	private ProvinceService provinceService;
	
	@Autowired
	private CityService cityService;
	
	@Autowired
	private TownService townService;
	
	@Autowired
	private Md5Pwd md5pwd;
	
    
	@RequestMapping("/user/login.shtml")
	public String loginUI(String returnUrl,Model model){
		
	    model.addAttribute("returnUrl", returnUrl);
		
		return "buyer/login";
	}
	
	/**
	 * 校验并登录
	 * @param buyer 用户信息
	 * @param captcha 验证码
	 * @param resultUrl 登录成功后返回的url
	 * @param model 
	 * @return 校验成功跳转到resultUrl指定的页面（不存在跳转到用户个人中心） ，不成工返回登录首页
	 */
	@RequestMapping("/user/index.shtml")
	public String login(Buyer buyer, String captcha, String returnUrl,HttpServletRequest request,Model model){
		Buyer buyerq = null;
		//后台校验
		if(buyer==null){
			model.addAttribute("error","用户不能为null");
			model.addAttribute("buyer", buyer);   //数据回显
			model.addAttribute("returnUrl", returnUrl); //数据回显
			return "buyer/login";
		}
		//判断验证码
		if(StringUtils.isBlank(captcha)){
			model.addAttribute("error","没有填写验证码");
			model.addAttribute("buyer", buyer);   //数据回显
			model.addAttribute("returnUrl", returnUrl); //数据回显
			return "buyer/login";
		}
		String aa = (String) sessionProvider.getAttribute(request, "captcha");
		if(!sessionProvider.getAttribute(request, "captcha").equals(captcha)){
			model.addAttribute("error","验证码不正确！");
			model.addAttribute("buyer", buyer);   //数据回显
			model.addAttribute("returnUrl", returnUrl); //数据回显
			return "buyer/login";
		}
		
		//判断用户名
		if(StringUtils.isBlank(buyer.getUsername())){
			model.addAttribute("error","请填写用户名");
			model.addAttribute("buyer", buyer);   //数据回显
			model.addAttribute("returnUrl", returnUrl); //数据回显
			return "buyer/login";
		}else{
			//使用用户名查询用户
			buyerq =  buyerService.getBuyerByUsername(buyer.getUsername());
			if(buyerq == null){
				model.addAttribute("error", "用户名不存在");
				model.addAttribute("buyer", buyer);   //数据回显
				model.addAttribute("returnUrl", returnUrl); //数据回显
				return "buyer/login";
			}
			//判断密码
			if(StringUtils.isBlank(buyer.getPassword())){
				model.addAttribute("error","请填写密码");
				return "buyer/login";
			}
			//使用md5加密后进行密码的比较
			String md5password  = md5pwd.encode(buyer.getPassword());
			if(!md5password.equals(buyerq.getPassword())){
				model.addAttribute("error", "密码不正确");
				return "buyer/login";
			}
			
			//存放用户信息到session
			sessionProvider.setAttribute(request, CommonsConstant.CURRENT_BUYER, buyerq);
		}
		//设置登录成功的标记(拦截器实现)
		//returnUrl为null的情况很常见，如果为null 则直接跳转到个人中心
	    if(StringUtils.isBlank(returnUrl)){
	    	return "buyer/profile.shtml";  //跳转到个人中心
	    }
	    //如果用户是从静态商品页进行登录则需要更改其url
	    //http://localhost:8080/babasport/html/product/1.html?isLogin=false&returnUrl=
		if(returnUrl.indexOf("/html/")!=-1){ //说明用户是从静态商品页中进行登录的
			//重新拼接url  http://localhost:8080/babasport/html/product/1.html
			returnUrl = returnUrl.substring(0, returnUrl.indexOf("?")+1);
			returnUrl+="isLogin=true&username="+buyerq.getUsername();
		}
	    //返回到原来的点击登陆的登录页面
		return "redirect:"+returnUrl;
	}
	
	/**
	 * 用户登出（用户登出如果实在需要登陆才能访问的页面，则通过拦截器进行拦截跳转到登录页面）
	 * @param returnUrl 用户登出时需要返回到的url地址
	 * @param model
	 * @return
	 */
	@RequestMapping("user/logout.shtml")
	public String loginout(String returnUrl,Model model,
			 HttpServletRequest request,HttpServletResponse response){
		//销毁session
        sessionProvider.invalidation(request, response);
    	if(returnUrl.indexOf("/html/")!=-1){ //说明用户是从静态商品页中进行登录的
			//重新拼接url  http://localhost:8080/babasport/html/product/1.html
			returnUrl = returnUrl.substring(0, returnUrl.indexOf("?")+1);
			returnUrl+="isLogin=false&username=";
		}
		//返回到原来的点击登陆的登录页面
		return "redirect:"+returnUrl;
	}
	
	
	//用户跳转中心
	@RequestMapping("buyer/profile.shtml")
	public String profile(Model model,
			 HttpServletRequest request,HttpServletResponse response){
		//从session中获取用户信息
		//如果为null,跳转到登录页面(拦截器实现了 此处必须要进行判断)
		Buyer buyer = (Buyer) sessionProvider.getAttribute(request, CommonsConstant.CURRENT_BUYER);
		
		if(buyer==null){
			model.addAttribute("error", "用户未登录或者session失效,请重新登陆");
			return "buyer/login";
		}
		
		model.addAttribute("buyer", buyer);
		
		//获取全部的省份地址（省市县三级联动 的入口）
        List<Province> provinces =  provinceService.getProvinceList(null);
        
        List<City> citys = cityService.getCityList(new CityQuery().setProvince(buyer.getProvince()));

        List<Town> towns = townService.getTownList(new TownQuery().setCity(buyer.getCity()));
        
        model.addAttribute("provinces", provinces);
        model.addAttribute("citys",citys);
        model.addAttribute("towns",towns);
		
		return "buyer/profile";
		
	}
	
	
	/**
	 * 获取省份对应的城市
	 * @param provinceCode 获取省份code
	 */
	@RequestMapping("buyer/citys.shtml")
	public @ResponseBody List<City> citys(@RequestBody String code){
	    List<City> citys = cityService.getCitys(code);
	    return citys;
		
	}
	
	/**
	 * 获取对应的县/区
	 * @param code 获取cityCode
	 */
	@RequestMapping("buyer/towns.shtml")
	public @ResponseBody List<Town> towns(@RequestBody String code){
		TownQuery townQuery = new TownQuery();
		townQuery.setCity(code);
	    
		List<Town> towns = townService.getTownList(townQuery);
		
	    return towns;
	}
	
	/**
	 * 保存用户的更改信息
	 * 更新数据库中的用户信息
	 * 重新设置session中的当前用户
	 */
	@RequestMapping("buyer/saveProfile.shtml")
	public String saveProfile(Buyer buyer,HttpServletRequest request,HttpServletResponse response,
			 Model model){
		
		buyerService.saveBuyer(buyer);
		
		Buyer buyerq = buyerService.getBuyerByUsername(buyer.getUsername());
		
		sessionProvider.invalidation(request, response);
		sessionProvider.setAttribute(request, CommonsConstant.CURRENT_BUYER, buyerq);
		
	    return "redirect:profile.shtml";
	}
	
	
}
