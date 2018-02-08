package cn.babasport.xiu.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 
 * @author xieqixiu
 * 后台菜单的跳转类，单纯的页面跳转，不涉及任何数据操作
 */
@Controller
@RequestMapping(value="/back")
public class BasicController {
	//输入babasport/back后跳转到后台页面
	@RequestMapping("index.do")
	public String backIndex(Model model){
		
		return "index";
	}
	
	
	/**
	 * 显示后台的顶部菜单
	 */
	@RequestMapping("/top.do")
	public String top(Model model){
		return "top";
	}
	
	/**
	 * 显示后台的下部菜单
	 */
	@RequestMapping("/main.do")
	public String main(Model model){
		return "main";
	}

	/**
	 * 显示后台下部的左侧菜单
	 */
	@RequestMapping("/left.do")
	public String left(Model model){
		return "left";
	}
	

	/**
	 * 显示后台下部的右侧菜单
	 */
	@RequestMapping("/right.do")
	public String right(Model model){
		return "right";
	}

	/**
	 *显示产品的主页面
	 */
	@RequestMapping("/product_main.do")
	public String product_main(Model model){
		return "frame/product_main";
	}
	
	/**
	 *显示产品的右侧页面
	 */
	@RequestMapping("/product_left.do")
	public String product_left(Model model){
		return "frame/product_left";
	}
	
	/**
	 *显示品牌的添加页面
	 */
	@RequestMapping("/brandAdd.do")
	public String brandAdd(Model model){
		return "brand/add";
	}
	
		
	
}
