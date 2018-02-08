package cn.babasport.xiu.test.dao;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import cn.babasport.xiu.common.utils.LunceneUtils;
import cn.babasport.xiu.core.bean.Product;
import cn.babasport.xiu.core.lucene.service.LunceneService;
import cn.babasport.xiu.core.service.ProductService;
import cn.babasport.xiu.test.commons.SpringJunit;




public class LuceneDaoTest extends SpringJunit {
	

	@Resource
	private LunceneService lunceneService;
	@Resource
	private ProductService productService;
	//添加索引
	@Test
	public void index(){
		
	  List<Product> products = new ArrayList<Product>();	
	  for(int i=0;i<10;i++){
		 Product product = new Product();
		 product.setId(1);
		 product.setName("浩沙");
		 product.setDescription("瑜伽服女套装灯笼裤舞蹈运动服");
		 product.setImgUrl("192.168.47.131/babasportImg/"+20171229+".jpg");
		 product.setMaxPrice(452.6);
		 product.setMinPrice(152.6);
		 products.add(product);
	  }
		
		 lunceneService.indexProducts(products);
		 
		 System.out.println("索引创建成功");
	}
	
	//搜索索引
	@Test
	public void serarch(){
		List<Product> products = lunceneService.search("瑜伽");
		
		for(Product product:products){
			System.out.println(product.getName()+"  "+product.getDescription());
		}
	}
	
	//添加商品索引
	@Test
	public void indexProduct(){
		productService.indexProduct();
	}
	
	
	//商品索引
	@Test
	public void searchProduct(){
		List<Product> products = lunceneService.search("瑜伽辅助");
		for(Product product:products){
			System.out.println(product.getName()+"  "+product.getDescription());
		}
//      List<Product> products = lunceneService.search("套装");
//		
//		for(Product product:products){
//			System.out.println(product.getName()+"  "+product.getDescription());
//		}
	}
	
	//对搜索框的关键字进行索引
	@Test
	public void indexSearchKeyword(){
		String[] strs = {"瑜伽","瑜伽服","瑜伽垫","瑜伽辅助","舞蹈鞋服","瑜伽铺巾"};
		lunceneService.indexText(Arrays.asList(strs));
		System.out.println("创建索引成功");
	}
	
	//对关键字进行搜索
	@Test
	public void searchKeyword(){
		List<String> results = lunceneService.searchText("瑜伽服");
		
		for(String result:results){
		    System.out.println("查找结果："+result);
		}
		
	}
	
	
	
	
	//查看商品的索引对象
	@Test
	public void showAnalyzerResult(){
		 
		List<String> result = LunceneUtils.showPaoingAnalyzer("瑜伽服女生");
	     
		for(String str :result){
			System.out.println(str);
		}
	
	}

}
