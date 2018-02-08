package cn.babasport.xiu.core.dao;

import cn.babasport.xiu.core.bean.Buyer;
import cn.babasport.xiu.core.bean.query.BuyerQuery;

public interface BuyerDao {
   //增加用户
	/**
	 * 增加用户
	 * @param buyer 用户实体
	 * @return 用户名(实质上是返回主键的字段)
	 */
   public String addBuyer(Buyer buyer);	
   //查询用户
   /**
    * 查询用户 
    * @param buyerQuery
    * @return
    */
   public Buyer getBuyerByUserName(BuyerQuery buyerQuery);
   //更新用户(通过设置buyer的isDel的字段为0从而达到删除的目的)
   public void updateBuyerByUsername(Buyer buyer);
}
