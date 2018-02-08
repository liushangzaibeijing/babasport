package cn.babasport.xiu.core.bean;

import java.io.Serializable;

/**
 * 
 * @author lixu
 * @Date [2014-3-28 下午04:38:53]
 */
public class OrderDetail implements Serializable {
	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer orderId; //在插入订单时，返回订单id
	private String productNo; //sku.product.no
	private String productName; //sku.product.name
	private String color; 
	private String size; //sku.size
	private Double skuPrice; //sku.markprice
	private Integer amount;  //buyItem.account

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public Double getSkuPrice() {
		return skuPrice;
	}
	public void setSkuPrice(Double skuPrice) {
		this.skuPrice = skuPrice;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String toString() {
		return "Detail [id=" + id + ",orderId=" + orderId + ",productNo=" + productNo + ",productName=" + productName + ",color=" + color + ",size=" + size + ",skuPrice=" + skuPrice + ",amount=" + amount + "]";
	}
}
