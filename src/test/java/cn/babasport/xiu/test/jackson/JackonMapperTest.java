package cn.babasport.xiu.test.jackson;

import org.junit.Test;

import cn.babasport.xiu.common.utils.JacksonUtils;
import cn.babasport.xiu.core.bean.Sku;
import cn.babasport.xiu.core.bean.shopping.BuyCart;
import cn.babasport.xiu.core.bean.shopping.BuyItem;

public class JackonMapperTest {
	
	@Test
	public void testReadValue(){
		
		Sku sku = new Sku();
		sku.setId(1);
		
		BuyItem buyItem = new BuyItem();
		buyItem.setAccount(1);
		buyItem.setSku(sku);
		
		
		BuyCart buyCart = new BuyCart();
		buyCart.setProductId(1);
		//buyCart.addBuyItem(buyItem);
		buyCart.getBuyItems().add(buyItem);
		
		String result = JacksonUtils.serialize(buyCart);
		
		System.out.println(result);
	}

}
