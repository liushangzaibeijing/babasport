package cn.babasport.xiu.core.controller.front;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.opensymphony.oscache.util.StringUtil;

import cn.babasport.xiu.common.constant.CommonsConstant;
import cn.babasport.xiu.common.utils.ResponseUtils;
import cn.babasport.xiu.common.web.cookie.CookieProvider;
import cn.babasport.xiu.core.bean.Sku;
import cn.babasport.xiu.core.bean.shopping.BuyCart;
import cn.babasport.xiu.core.bean.shopping.BuyItem;
import cn.babasport.xiu.core.service.ProductService;
import cn.babasport.xiu.core.service.SkuService;
import cn.babasport.xiu.core.service.TypeService;

/**
 * 购物车控制器
 * @author xieqixiu
 */
@Controller
@RequestMapping("/front")
public class CartController {
	
	@Autowired
	private SkuService skuService;
	@Autowired
	private CookieProvider cookieProvider;
	@Autowired
	private TypeService typeService;
	@Autowired
	private ProductService productService;
	

	/**
	 * 加入购物车
	 * @param productId 最新浏览的商品id
	 * @param skuId 最小销售单元id
	 * @param stockInventory 库存
	 * @param request  
	 * @param model
	 * @return
	 */
    @RequestMapping(value = "/shopping/addShoppCart.shtml")
    public String addShoppCart(Integer productId,Integer skuId,Integer account,Integer buyLimit,Integer stockInventory,
    		                   HttpServletRequest request,HttpServletResponse response, Model model){
    	//从request中获取指定的cookie 
    	//如果有cookie 则将cookie反序列化成购物车，添加购物项（判断重复 判断是否查过购买限制，大于库存  数量默认为1） 
    	//因为cookie存储的大小限制 所以只存储关键字段 如 skuId，库存，购买限制， account,productId等关键字段
    	
    	BuyCart buyCart = null;
         
    	buyCart = cookieProvider.getCookieObj(request, BuyCart.class);
    	
    	//如果没有则构建新的购物车对象，购物项对象 （在购物车对象中添加购物项）创建cookie，将购物项对象序列化并添加到到cookie中
    	if(buyCart==null){
    		buyCart = new  BuyCart();
    	}
    	if(productId!=null){
    		buyCart.setProductId(productId);
    	}
    	BuyItem buyItem = null;
    	if(skuId!=null){
    		buyItem = new BuyItem();
    		Sku sku = new Sku();
    		if(account!=null){
    		   buyItem.setAccount(account);
    		}
    		if(buyLimit!=null){
    			sku.setSkuUpperLimit(buyLimit);
    		}
    		if(stockInventory!=null){
    			sku.setStockInventory(stockInventory);
    		}
    		sku.setId(skuId);
    		buyItem.setSku(sku);
    	}
    	buyCart.addBuyItem(buyItem);

    	//向客户端响应cookie
    	cookieProvider.responseCookie(response, buyCart);
    
    	return "redirect:showBuyItem.shtml";
    }
   
    /**
     * 显示购物项
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/shopping/showBuyItem.shtml")
    public String showBuyItem(HttpServletRequest request,HttpServletResponse response,
    		 Model model){
    	
    	BuyCart buyCart = null;
        
    	buyCart = cookieProvider.getCookieObj(request, BuyCart.class);
    	
    	//如果没有则构建新的购物车对象，购物项对象 （在购物车对象中添加购物项）创建cookie，将购物项对象序列化并添加到到cookie中
    	if(buyCart==null){
    		buyCart = new  BuyCart();
    	}
         
    	buyCart = loadBuyCart(buyCart);
        model.addAttribute("buyCart", buyCart);
     	model.addAttribute("baseUrl" , CommonsConstant.UPLOADFILE_BASE_URL);
     	
     	return "product/cart";
    }
    
    //后台ajax调用获取购物车信息
    /**
     * 显示购物项
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/shopping/showBuyItemAjax.shtml")
    public void showBuyItemAjax(HttpServletRequest request,HttpServletResponse response,
    		 Model model){
    	
    	BuyCart buyCart = null;
        
    	buyCart = cookieProvider.getCookieObj(request, BuyCart.class);
    	
    	//如果没有则构建新的购物车对象，购物项对象 （在购物车对象中添加购物项）创建cookie，将购物项对象序列化并添加到到cookie中
    	if(buyCart==null){
    		buyCart = new  BuyCart();
    	}
         
    	buyCart = loadBuyCart(buyCart);
     	
     	ObjectMapper mapper = new ObjectMapper();
     	String value = null;
		try {
			value = mapper.writeValueAsString(buyCart);
		} catch (IOException e) {
			e.printStackTrace();
		}
     	
     	ResponseUtils.renderJson(response, value);
    }
    
    
    /**
     * 删除商品项
     * @param skuId 
     * @return
     */
    @RequestMapping("/shopping/deleteBuyItem.shtml")
    public String deleteBuyItem(HttpServletRequest request,HttpServletResponse response,
    		 Integer skuId,Model model){
    	//获取cookie中的购物车
    	BuyCart  buyCart  = cookieProvider.getCookieObj(request, BuyCart.class);
    	//遍历购物车，删除指定skuId的购物项
    	for(BuyItem buyItem:buyCart.getBuyItems()){
    		if(buyItem.getSku().getId()==skuId){
    			buyCart.deleteBuyItem(buyItem);
    			break;
    		}
    	}
    	//响应cookie
    	cookieProvider.responseCookie(response, buyCart);
    	
    	//装满购物车
    	buyCart = loadBuyCart(buyCart);
    	
    	model.addAttribute("buyCart", buyCart);
      	model.addAttribute("baseUrl" , CommonsConstant.UPLOADFILE_BASE_URL);
    	
		return "product/cart";
    }
    
    
    
    /**
     * 删除商品项(异步操作)
     * @param skuId 
     * @return
     */
    @RequestMapping("/shopping/deleteBuyItemAjax.shtml")
    public void deleteBuyItemAjax(HttpServletRequest request,HttpServletResponse response,
    		 Integer skuId,Model model){
    	//获取cookie中的购物车
    	BuyCart  buyCart  = cookieProvider.getCookieObj(request, BuyCart.class);
    	//遍历购物车，删除指定skuId的购物项
    	for(BuyItem buyItem:buyCart.getBuyItems()){
    		if(buyItem.getSku().getId()==skuId){
    			buyCart.deleteBuyItem(buyItem);
    			break;
    		}
    	}
    	//响应cookie
    	cookieProvider.responseCookie(response, buyCart);
    	
    	//装满购物车
    	loadBuyCart(buyCart);
     	
     	ObjectMapper mapper = new ObjectMapper();
     	String value = null;
		try {
			value = mapper.writeValueAsString(buyCart);
		} catch (IOException e) {
			e.printStackTrace();
		}
     	
     	ResponseUtils.renderJson(response, value);
     	
    }
    
    //减少购物项
    @RequestMapping("/shopping/subBuyItem.shtml")
    public void subBuyItem(HttpServletRequest request,HttpServletResponse response,
    		@RequestBody Integer skuId,ModelMap model){
    	//获取cookie中的购物车
    	BuyCart  buyCart  = cookieProvider.getCookieObj(request, BuyCart.class);
    	//遍历购物车，删除指定skuId的购物项
    	
    	Sku sku = new Sku();
    	sku.setId(skuId);
        BuyItem buyItem = new BuyItem();
        buyItem.setSku(sku);
    	
    	buyCart.subBuyItem(buyItem);
    	//响应cookie
    	cookieProvider.responseCookie(response, buyCart);
    }
    
    //添加购物项
    @RequestMapping("/shopping/addBuyItem.shtml")
    public void addBuyItem(HttpServletRequest request,HttpServletResponse response,@RequestBody Integer skuId,ModelMap model){
    	//获取cookie中的购物车
    	BuyCart  buyCart  = cookieProvider.getCookieObj(request, BuyCart.class);
    	//遍历购物车，删除指定skuId的购物项
    	
    	Sku sku = new Sku();
    	sku.setId(skuId);
        BuyItem buyItem = new BuyItem();
        buyItem.setSku(sku);
    	
    	buyCart.addBuyItem(buyItem);
    	//响应cookie
    	cookieProvider.responseCookie(response, buyCart);
    }
    
    
    //清空购物车  //销毁cookie
    @RequestMapping("/shopping/clearCart.shtml")
    public String clearCart(HttpServletRequest request,HttpServletResponse response,@RequestBody Integer skuId,ModelMap model){
    	//获取cookie中的购物车
    	/*
    	BuyCart  buyCart  = cookieProvider.getCookieObj(request, BuyCart.class);
    	//遍历购物车，删除指定skuId的购物项
    	buyCart.clear();
    	//响应cookie
    	cookieProvider.responseCookie(response, buyCart);
    	*/
	  Cookie cookie = new Cookie(CommonsConstant.BUYERCART, null);
      cookie.setMaxAge(0);
      cookie.setPath("/");
      response.addCookie(cookie);

      return "redirect:/shopping/addShoppCart.shtml";
    }
    
    /**
     * 获取用户选中的购物项的信息
     * @param skudIds 用户选中的多个购物项id
     * @param request 
     * @param response
     */
    @RequestMapping("/shopping/showBalance.shtml")
    public void showBalance(@RequestBody String skudIds,HttpServletRequest request,HttpServletResponse response){
    	if(StringUtils.isBlank(skudIds)){
    		return ;
    	}
    	String[] skuIdsStr = skudIds.split(",");
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
		
		ObjectMapper mapper = new ObjectMapper();
     	String value = null;
		try {
			value = mapper.writeValueAsString(buyCartChecked);
		} catch (IOException e) {
			e.printStackTrace();
		}
     	
     	ResponseUtils.renderJson(response, value);
        		
    	
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
