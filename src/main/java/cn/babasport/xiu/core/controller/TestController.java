package cn.babasport.xiu.core.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.babasport.xiu.core.bean.TestDb;
import cn.babasport.xiu.core.service.TestService;


public class TestController {
	@Autowired
	private TestService testService;
	
	@RequestMapping("/front/demoTest.shtml")
	public String demoTest(String name,Date birthday,int age,Model model){
		TestDb test = new TestDb();
		test.setId((Math.random()*100)+"");
		test.setName(name);
		test.setBirthday(birthday);
		test.setAge(age);
		
		testService.saveTest(test);
		
		return "";
	}
	
	
	@RequestMapping("/back/demoTest2.do")
	public String demoTest2(String name,Date birthday,int age,Model model){
		TestDb test = new TestDb();
		test.setId((Math.random()*100)+"");
		test.setName(name);
		test.setBirthday(birthday);
		test.setAge(age);
		
		testService.saveTest(test);
		
		return "";
	}
	
	/* 在controller中加入该方法，仅仅作用于该controller中  
	@InitBinder  
	public void initBinder(WebDataBinder binder) {  
	   SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
	   dateFormat.setLenient(false);  
	   binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   //true:允许输入空值，false:不能为空值  
	}
	*/
}
