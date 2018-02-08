package cn.babasport.xiu.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.babasport.xiu.core.bean.OrderDetail;
import cn.babasport.xiu.core.bean.query.OrderDetailQuery;
import cn.babasport.xiu.core.bean.query.OrderQuery;
import cn.babasport.xiu.core.dao.OrderDetailDao;
import cn.babasport.xiu.core.service.OrderDetailService;


/**
 * Created by nibnait on 2016/5/8.
 */
@Service
@Transactional
public class OrderDetailServiceImpl implements OrderDetailService{
	@Autowired
	private OrderDetailDao orderDetailDao;

	@Override
	public void addOrderDetail(OrderDetail orderDetail) {
        orderDetailDao.addDetail(orderDetail);
	}

	@Override
	public void addOrderDetails(List<OrderDetail> orderItems,Integer orderId) {
		for (OrderDetail orderDetail:orderItems) {
			orderDetail.setOrderId(orderId);
			orderDetailDao.addDetail(orderDetail);
		}
	}

	@Override
	@Transactional(readOnly=true)
	public List<OrderDetail> getOrderDetailList(Integer orderId) {
		if(orderId==null){
			return null;
		}
		OrderDetailQuery orderDetailQuery = new OrderDetailQuery();
		
		orderDetailQuery.setOrderId(orderId);
		
		return orderDetailDao.getDetailList(orderDetailQuery);
		
	}

 
}
