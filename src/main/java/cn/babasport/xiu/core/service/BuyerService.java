package cn.babasport.xiu.core.service;

import java.util.List;

import com.nibnait.common.page.Pagination;

import cn.babasport.xiu.core.bean.Brand;
import cn.babasport.xiu.core.bean.Buyer;

/**
 * 用户service
 * @author xieqixiu
 *
 */
public interface BuyerService {
    /**
     * 根据用户登录名获取用户	
     * @param username 用户的登录名
     * @return 返回用户
     */
	public Buyer getBuyerByUsername(String username);
	
	/**
	 * 保存用户信息
	 */
	public void saveBuyer(Buyer buyer);
	
	
	/**
     * 根据用户登录名获取用户	
     * @param username 用户的登录名
     * @return 返回用户
     */
	public Buyer getBuyerByBuyerId(String username);
	
	

}
