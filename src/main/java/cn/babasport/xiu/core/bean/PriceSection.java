package cn.babasport.xiu.core.bean;

import java.io.Serializable;

/**
 * 商品的价格区间对象（不太正规）
 * @author xieqixiu
 *
 */
public class PriceSection implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7411901774097420946L;
    
	//商品的最大价格
    private double maxPrice;
    //商品的最小价格
    private double minPrice;
	public double getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(double maxPrice) {
		this.maxPrice = maxPrice;
	}
	public double getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(double minPrice) {
		this.minPrice = minPrice;
	} 
    
}
