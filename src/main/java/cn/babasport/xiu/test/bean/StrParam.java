package cn.babasport.xiu.test.bean;

import java.util.List;

//将list集合放入一个包装对象上。
public  class StrParam{
	//list集合无法直接放置在controller层的方法上，这里将其放置在包装对象上，
	//再将包装对象放置到controller层的方法上。
	private List<String> strParams;

	public StrParam() {
		super();
	}
	
	public StrParam(List<String> strParams) {
		super();
		this.strParams = strParams;
	}



	public List<String> getStrParams() {
		return strParams;
	}

	public void setStrParams(List<String> strParams) {
		this.strParams = strParams;
	}
  }
  
  