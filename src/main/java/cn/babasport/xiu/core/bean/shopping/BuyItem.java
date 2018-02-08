package cn.babasport.xiu.core.bean.shopping;

import java.io.Serializable;

import cn.babasport.xiu.core.bean.Sku;

/**
 * 购物项
 * @author xieqixiu
 *
 */
public class BuyItem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1995818702607231580L;
	
	/**
	 * 最小销售单元
	 */
	private Sku sku;
	
	/**
	 * 购买的商品数量(默认为1)
	 */
	private int account = 1;

	public Sku getSku() {
		return sku;
	}

	public void setSku(Sku sku) {
		this.sku = sku;
	}

	public int getAccount() {
		return account;
	}

	public void setAccount(int account) {
		this.account = account;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + account;
		result = prime * result + ((sku == null) ? 0 : sku.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BuyItem other = (BuyItem) obj;
		if (sku == null) {
			if (other.sku != null)
				return false;
		}
		else if (!sku.getId().equals(other.sku.getId()))
			return false;
		return true;
	}
	
}
