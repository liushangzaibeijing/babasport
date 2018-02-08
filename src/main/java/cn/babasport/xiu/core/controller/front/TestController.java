package cn.babasport.xiu.core.controller.front;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.utils.URLEncodedUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.babasport.xiu.test.bean.StrParam;
/**
 * 测试重定向参数
 * @author xieqixiu
 *
 */
@Controller
@RequestMapping("/test")
public class TestController {

  /**
   * 使用Model来存放参数，在重定向时该参数也会被带过去
   * @param model
   * @return
   */
  @RequestMapping("redirectParamsByModel.shtml")
  public String redirectParamsByModel(Model model){
	  
	  List<String> strParams = new ArrayList<String>();  
	  strParams.add("测试一");
	  strParams.add("测试二");
	  strParams.add("测试三");
	  
	  StrParam strParam = new StrParam();
	  strParam.setStrParams(strParams);
	  
	  model.addAttribute("strParam", strParam);
	  return "redirect:/test/test1.shtml?strParams="+strParam;
  }
  
  //重定的方法，同时上面的Model中存放的参数也被传递过来了
  @RequestMapping("test1.shtml")
  public String test1(Model model,StrParam strParam){
	  int aa = 4;
	  aa +=3;
	  System.out.println("通过model传递的参数list集合长度： "+strParam.getStrParams().size());
	  return "aaa";
  }
  
  
  @RequestMapping("redirectParamsByUrl.shtml")
  public String redirectParamsByUrl(Model model){
	  String strParams = "测试一aaa";
	  String urlParams = null;
	  try {
		  urlParams = URLEncoder.encode(strParams, "UTF-8");
	  } catch (UnsupportedEncodingException e) {
		 e.printStackTrace();
	  }
  	
	  return "redirect:/test/test2.shtml?strParams="+urlParams;
  }
  
  @RequestMapping("test2.shtml")
  public String test2(Model model,String strParams){
	  System.out.println("通过重定向url传递的参数： "+strParams);
	  return "aaa";
  }
  
  /**
   * 使用RedirectAttributes springmvc的一个用于重定向传递参数
   * @param model
   * @return
   */
  @RequestMapping("redirectParamsByedirectAtr.shtml")
  public String redirectParamsByedirectAtr(RedirectAttributes model){
	  String strParams = "测试一";
	  //使用addFlashAttribute来设置重定向时需要传递的参数。
	  model.addFlashAttribute("strParams", strParams);
	  return "redirect:/test/test3.shtml";
  }
  
  //要重定向的方法，使用@ModelAttribute注解将参数拿到
  @RequestMapping("test3.shtml")
  public String test3(@ModelAttribute("strParams") String strParams){
	  System.out.println("通过重定向url传递的参数： "+strParams);
	  return "aaa";
  }
  
}
