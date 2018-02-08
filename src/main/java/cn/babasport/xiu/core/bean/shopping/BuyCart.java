package cn.babasport.xiu.core.bean.shopping;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 购物车
 * @author xieqixiu
 *
 */
public class BuyCart implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6463334448662950428L;
	
	/**
	 * 购物项
	 */
	List<BuyItem> buyItems = new ArrayList<BuyItem>();
	/**
	 * 继续购物时转向最新商品详情页商品id 
	 */
	private Integer productId;
	
	
	public List<BuyItem> getBuyItems() {
		return buyItems;
	}
	public void setBuyItems(List<BuyItem> buyItems) {
		this.buyItems = buyItems;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	//添加购物项（1、判断重复（重写BuyerItem的equals和hashCode方法 ）  2、判断是否超过购买限制，3、判断是否大于库存（大于库存 添加刚好满足的最大数量） 4、每次添加 数量默认为1）
	public void addBuyItem(BuyItem buyItem) {
		boolean falg = false;
		for(BuyItem bItem:buyItems){
			if(buyItem.equals(bItem)){
				int result = bItem.getAccount()+buyItem.getAccount(); //重复则数量增加
				//判断是否超过购买限制
				int buyLimit = bItem.getSku().getSkuUpperLimit();
				if(result > buyLimit){
					//设置购买限制
					bItem.setAccount(buyLimit);
				}else{
					bItem.setAccount(result);
				}
				//判断是否查过了库存
				int stockInventory = bItem.getSku().getStockInventory();
				if(result > stockInventory){
					bItem.setAccount(stockInventory);
				}
				falg = true;
				break;
		    }
		}
		if(!falg){
			this.buyItems.add(buyItem);
		}
		
	}
	
	//获取商品总数
	@JsonIgnore
	public Integer getProductAmount(){
		Integer amount = 0;
		for(BuyItem buyItem:buyItems){
			amount += buyItem.getAccount();
		}
		return amount;
	}

	
	//获得商品金额
	@JsonIgnore
	//不能进行序列化，因为sku中只有id，其他的均为null,所以会报null指针错误
	public Double getProductPrice(){
		Double productPrice = 0.0;
		for(BuyItem buyItem:buyItems){
			productPrice += buyItem.getSku().getSkuPrice()*buyItem.getAccount();
		}
		return productPrice;
	}
	
	
	
	//获取运费（69元免运费）
	@JsonIgnore
	public Double getDeliveFee(){
		Double deliveFee = 0.00;
		
		if(getProductPrice()>=69){
			return 0.00;
		}
		
		
		for(BuyItem buyItem:buyItems){
			deliveFee += buyItem.getSku().getDeliveFee(); //同一件商品 运费不进行叠加
		}
		return deliveFee;
		
	}
	
	
	//获取应付总金额
	@JsonIgnore
	public Double getTotalPrice(){
		return  getProductPrice() + getDeliveFee();
	}
	
	
	//减少商品数量
	public void subBuyItem(BuyItem buyItem){
		for(BuyItem bItem:buyItems){
			if(buyItem.equals(bItem)){
				int result = bItem.getAccount()-1; //重复则数量增加
				//判断是否超过购买限制
				if(result > 0){
					//减少
					bItem.setAccount(result);
				}else {
                    deleteBuyItem(buyItem);
                }
				break;
		    }
		}
		
	}
	
	//删除商品
	public void deleteBuyItem(BuyItem buyItem){
		buyItems.remove(buyItem);
	}
}
