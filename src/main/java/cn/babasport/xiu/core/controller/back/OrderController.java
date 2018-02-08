package cn.babasport.xiu.core.controller.back;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nibnait.common.page.Pagination;

import cn.babasport.xiu.core.bean.Buyer;
import cn.babasport.xiu.core.bean.Order;
import cn.babasport.xiu.core.bean.OrderDetail;
import cn.babasport.xiu.core.bean.query.OrderQuery;
import cn.babasport.xiu.core.service.BuyerService;
import cn.babasport.xiu.core.service.OrderDetailService;
import cn.babasport.xiu.core.service.OrderService;


/**
 * 后台订单管理
 * @author xieqixiu
 */
@Controller
@RequestMapping("/back")
public class OrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private BuyerService buyerService;
	@Autowired
	private OrderDetailService orderDetailService;
	
	//跳转到订单页面
	@RequestMapping("order/order_main.do")
	public String orderMain(Model model){
		return "frame/order_main";
	}
	
	//跳转到订单左侧页面
	@RequestMapping("order/order_left.do")
	public String orderLeft(Model model){
		return "frame/order_left";
	}
	
	
	//获取订单列表(所有人的订单信息)
	/**
	 * 根据订单支付状态和订单状态来查询订单，分页进行显示
	 * @param model
	 * @param isPaiy
	 * @param state
	 * @return
	 */
	@RequestMapping("order/orderList.do")
	public String orderlist(Model model,Integer isPaiy,Integer state,Integer pageNo){
		
		StringBuffer params = new StringBuffer();
		//使用分页查询订单
		//根据查询条件进行查询(订单状态，支付状态作为查询条件 每次只根据其中进行查询)
		OrderQuery orderQuery = new OrderQuery();
		
		if(isPaiy!=null){
			orderQuery.setIsPaiy(isPaiy);
		 	params.append("isPaiy=").append(isPaiy);
		}
		
		if(state!=null){
			orderQuery.setState(state);
			params.append("state=").append(state);
		}
		
		Pagination pagination = orderService.getOrderByPage(orderQuery,pageNo);
		
		
		pagination.pageView("/babasport/back/order/orderList.do", params.toString());;
		
		model.addAttribute("pagination", pagination);
		
		return "order/list";
	}
	
	/**
	 * 获取对应的订单和订单详情 收货人信息
	 * @param model
	 * @param orderId
	 * @return
	 */
	@RequestMapping("order/view.do")
	public String orderView(Model model,Integer orderId){
		//获取订单信息
		Order order = orderService.getOrderbyKey(orderId);
		
		//获取收货人信息
		Buyer buyer = buyerService.getBuyerByBuyerId(order.getBuyerId());
		
		//获取对应的订单详情
		List<OrderDetail> orderDetails = orderDetailService.getOrderDetailList(orderId);
		
		model.addAttribute("order", order);
		model.addAttribute("buyer", buyer);
		model.addAttribute("orderDetails", orderDetails);
		
		return "order/view";
	}
	
	
}
