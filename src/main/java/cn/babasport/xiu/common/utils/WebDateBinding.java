package cn.babasport.xiu.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

public class WebDateBinding implements WebBindingInitializer{

	public void initBinder(WebDataBinder binder, WebRequest request) {
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
		 dateFormat.setLenient(false);  
		 binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   //true:允许输入空值，false:不能为空值  
	}

}
