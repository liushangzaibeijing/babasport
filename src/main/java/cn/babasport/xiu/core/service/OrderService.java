package cn.babasport.xiu.core.service;


import com.nibnait.common.page.Paginable;
import com.nibnait.common.page.Pagination;

import cn.babasport.xiu.core.bean.Order;
import cn.babasport.xiu.core.bean.query.OrderQuery;
/**
 * 
 * @author lixu
 * @Date [2014-3-28 下午01:50:28]
 */
public interface OrderService {
	
	//添加商品和商品项
	public Integer addOrder(Order order);

	//获取订单
	public Order selectOrder(Integer orderId);

	/**
	 * 获取订单的列表，分页显示
	 * @param orderQuery
	 * @param pageNo
	 * @return
	 */
	public Pagination getOrderByPage(OrderQuery orderQuery,Integer pageNo);

	/**
	 * 获取订单信息
	 * @param orderId
	 */
	public Order getOrderbyKey(Integer orderId);
}
