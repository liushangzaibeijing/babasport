package cn.babasport.xiu.core.bean.vo;

import java.util.ArrayList;
import java.util.List;

import cn.babasport.xiu.core.bean.Product;
/**
 * springmvc无法直接传递非基本类型的包装对象，因为从request中获取的参数
 * 会通过set或者get方法赋给对象中的属性，但是list集合却没有对应的setList/getList的方法
 * 所以如果在controller层中放置list<?> 应用启动时会报Could not instantiate bean class [java.util.List]: Specified class is an interfac
 * @author xieqixiu
 *
 */

public class ProductsVo {
	
	private List<Product> products = new ArrayList<Product>();

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
