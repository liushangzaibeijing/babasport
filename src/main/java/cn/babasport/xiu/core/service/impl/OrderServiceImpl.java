package cn.babasport.xiu.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ServletContextAware;

import com.nibnait.common.page.Paginable;
import com.nibnait.common.page.Pagination;

import cn.babasport.xiu.core.bean.Order;
import cn.babasport.xiu.core.bean.OrderDetail;
import cn.babasport.xiu.core.bean.query.OrderQuery;
import cn.babasport.xiu.core.dao.OrderDao;
import cn.babasport.xiu.core.dao.OrderDetailDao;
import cn.babasport.xiu.core.service.OrderService;

import java.util.List;


/**
 * 订单服务
 * @author xieqixiu
 *
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDao orderDao;

	@Override
	public Integer addOrder(Order order) {
		return orderDao.addOrder(order); 
	}

	@Override
	public Order selectOrder(Integer orderId) {
		return orderDao.getOrderByKey(orderId);
	}

	@Override
	public Pagination getOrderByPage(OrderQuery orderQuery,Integer pageNo) {
		//设置当前页码
		orderQuery.setPageNo(Pagination.cpn(pageNo));
		
		List<Order> orders = orderDao.getOrderListWithPage(orderQuery);
		
        Pagination page = new Pagination(orderQuery.getPageNo(), orderQuery.getPageSize(),
        		                         orderDao.getOrderListCount(orderQuery), orders);
		

        
        
		return page;
	}

	@Override
	public Order getOrderbyKey(Integer orderId) {
		return orderDao.getOrderByKey(orderId);
	}

}
