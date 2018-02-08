package cn.babasport.xiu.core.controller.front;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import cn.babasport.xiu.common.constant.CommonsConstant;
import cn.babasport.xiu.common.web.cookie.CookieProvider;
import cn.babasport.xiu.common.web.session.SessionProvider;
import cn.babasport.xiu.core.bean.Buyer;
import cn.babasport.xiu.core.bean.Order;
import cn.babasport.xiu.core.bean.OrderDetail;
import cn.babasport.xiu.core.bean.Sku;
import cn.babasport.xiu.core.bean.shopping.BuyCart;
import cn.babasport.xiu.core.bean.shopping.BuyItem;
import cn.babasport.xiu.core.service.OrderDetailService;
import cn.babasport.xiu.core.service.OrderService;
import cn.babasport.xiu.core.service.ProductService;
import cn.babasport.xiu.core.service.SkuService;
import cn.babasport.xiu.core.service.TypeService;

/**
 * 前台订单控制器
 * @author xieqixiu
 */
@Controller
@RequestMapping("/front")
public class OrderFrontController {
	
	@Autowired
	private SkuService skuService;
	@Autowired
	private CookieProvider cookieProvider;
	@Autowired
	private TypeService typeService;
	@Autowired
	private ProductService productService;
	@Autowired
	private SessionProvider sessionProvider;
	
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderDetailService orderDetailService;
	
    /**
     * 跳转到订单确认页面
     * @param skudIds
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/buyer/productOrderUI.shtml")
    public String productOrderUI(String skuIds,HttpServletRequest request,HttpServletResponse response,Model model){
        //获取用户信息 从session中获取
    	Buyer buyer = (Buyer)sessionProvider.getAttribute(request, CommonsConstant.CURRENT_BUYER);
    	
    	//获取用户选中的购物车信息(并显示)
    	String[] skuIdsStr = skuIds.split(",");
    	//获取购物车对象
    	BuyCart buyCart = cookieProvider.getCookieObj(request, BuyCart.class);
    	
    	BuyCart buyCartChecked = new BuyCart();
    	//构造一个新的购物车对象，将用户选中的（要买的商品放入）
    	
		List<BuyItem> buyItems = buyCart.getBuyItems();
		
		for(String skuIdStr : skuIdsStr){
			int skuId = Integer.parseInt(skuIdStr);
			for(BuyItem buyItem :buyItems){
				int bSkuId = buyItem.getSku().getId();
				if(skuId==bSkuId){
					buyCartChecked.addBuyItem(buyItem);
				}
			}
		}
		
		//装载购物车
		loadBuyCart(buyCartChecked);
    	
    	
    	model.addAttribute("buyer", buyer);
    	model.addAttribute("buyCart",buyCartChecked);
        model.addAttribute("skuIds", skuIds);
        model.addAttribute("baseUrl", CommonsConstant.UPLOADFILE_BASE_URL);
    	
    	return "product/productOrder";
    }
    
    /**
     * 生成订单
     * @param skudIds
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/buyer/confirmOrder.shtml")
    public String confirmOrder(Order order,String skuIds,HttpServletRequest request,HttpServletResponse response,Model model){
    	
    	//获取用户选中的购物车信息(并显示)
    	String[] skuIdsStr = skuIds.split(",");
    	//获取购物车对象
    	BuyCart buyCart = cookieProvider.getCookieObj(request, BuyCart.class);
    	
    	BuyCart buyCartChecked = new BuyCart();
    	//构造一个新的购物车对象，将用户选中的（要买的商品放入）
    	
		List<BuyItem> buyItems = buyCart.getBuyItems();
		
		//在迭代集合的同时不能进行删除操作
		//对JAVA集合进行遍历删除时务必要用迭代器
		for(String skuIdStr : skuIdsStr){
			int skuId = Integer.parseInt(skuIdStr);
			Iterator<BuyItem> iterator = buyItems.iterator();
			  if(iterator.hasNext()){
				  BuyItem buyItem = iterator.next();
				  int bSkuId = buyItem.getSku().getId();
				  if(skuId==bSkuId){
					  buyCartChecked.addBuyItem(buyItem);
					  iterator.remove();
				  }
				  
			  }
		}
		
		//重新装载购物车
		cookieProvider.responseCookie(response, buyCart);
		
		
		//装载购物车
		loadBuyCart(buyCartChecked);
    	
    	//保存订单和订单项
    	order.setOid(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
    	order.setDeliverFee(buyCartChecked.getDeliveFee());
    	order.setPayableFee(buyCartChecked.getProductPrice());
    	order.setTotalPrice(buyCartChecked.getTotalPrice());
    	if(order.getPaymentWay()==0){
    		order.setIsPaiy(0);
    	}else{
    		order.setIsPaiy(1);
    	}
    	
    	order.setState(1);
    	order.setCreateDate(new Date());
    	
    	List<OrderDetail> orderItems = new ArrayList<>();
    	//设置订单项目
    	for(BuyItem buyItem:buyCartChecked.getBuyItems()){
    		 OrderDetail orderDetail = new OrderDetail();
    	     orderDetail.setProductNo(buyItem.getSku().getProduct().getNo());
    	     orderDetail.setProductName(buyItem.getSku().getProduct().getName());
    	     orderDetail.setColor(buyItem.getSku().getColor().getName());
    	     orderDetail.setSize(buyItem.getSku().getSize());
    	     orderDetail.setSkuPrice(buyItem.getSku().getMarketPrice());
    	     orderDetail.setAmount(buyItem.getAccount());
    	     orderItems.add(orderDetail);
    	}
    	
    	//这两个相关联的操作不在一个事务中，可能会出现不同步
    	orderService.addOrder(order);
    	
    	orderDetailService.addOrderDetails(orderItems,order.getId());
    	
    	return "redirect:showOrder.shtml?orderId="+order.getId();
    }

    /**
     * 生成订单
     * @param skudIds
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/buyer/showOrder.shtml")
    public String showOrder(Integer orderId,Model model){
    	
    	Order order = orderService.selectOrder(orderId);
    	
    	//5天到货
    	Date arrivalDate = new Date(new Date().getTime()+1000*60*60*24*5);
    	order.setArrivalDate(arrivalDate);
    	
    	model.addAttribute("order", order);
    	
    	return "product/confirmOrder";
    }

    
    /**
     * 装满购物车
     * @param buyCart 
     * @return
     */
	private BuyCart loadBuyCart(BuyCart buyCart) {
		for(BuyItem buyItem:buyCart.getBuyItems()){
			Sku sku = skuService.getSkuByKey(buyItem.getSku().getId());
			//获取商品类型
			String typeName = typeService.getTypeByKey(
					 productService.getProductByKey(sku.getProductId()).getTypeId()).getName();
			
			sku.setTypeName(typeName);
			buyItem.setSku(sku);
		}
		return buyCart;
	}
	
}
