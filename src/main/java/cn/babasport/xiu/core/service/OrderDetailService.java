package cn.babasport.xiu.core.service;

import java.util.List;

import cn.babasport.xiu.core.bean.OrderDetail;

/**
 * Created by nibnait on 2016/5/8.
 */
public interface OrderDetailService {
	
	public void addOrderDetail(OrderDetail orderDetail);

	public void addOrderDetails(List<OrderDetail> orderItems,Integer orderId);

	public List<OrderDetail> getOrderDetailList(Integer orderId);
 
}
