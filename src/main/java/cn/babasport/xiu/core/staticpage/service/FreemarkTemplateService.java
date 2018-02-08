package cn.babasport.xiu.core.staticpage.service;

import java.util.Map;

public interface FreemarkTemplateService{
	/**
	 * 为商品详情页生成静态模板语言
	 * @param rootMap  模板语言传入的数据
	 * @param productId 商品id
	 */
	public void createStaticPage(Map<String,Object> rootMap,Integer productId);
}