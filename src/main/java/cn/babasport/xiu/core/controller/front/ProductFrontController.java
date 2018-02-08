package cn.babasport.xiu.core.controller.front;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nibnait.common.page.Pagination;

import cn.babasport.xiu.common.constant.CommonsConstant;
import cn.babasport.xiu.common.web.cookie.CookieProvider;
import cn.babasport.xiu.core.bean.Brand;
import cn.babasport.xiu.core.bean.Color;
import cn.babasport.xiu.core.bean.Feature;
import cn.babasport.xiu.core.bean.Img;
import cn.babasport.xiu.core.bean.Product;
import cn.babasport.xiu.core.bean.Sku;
import cn.babasport.xiu.core.bean.Type;
import cn.babasport.xiu.core.bean.query.ColorQuery;
import cn.babasport.xiu.core.bean.query.FeatureQuery;
import cn.babasport.xiu.core.bean.query.ImgQuery;
import cn.babasport.xiu.core.bean.query.SkuQuery;
import cn.babasport.xiu.core.bean.query.TypeQuery;
import cn.babasport.xiu.core.bean.shopping.BuyCart;
import cn.babasport.xiu.core.bean.shopping.BuyItem;
import cn.babasport.xiu.core.bean.vo.ProductsVo;
import cn.babasport.xiu.core.lucene.service.LunceneService;
import cn.babasport.xiu.core.service.BrandService;
import cn.babasport.xiu.core.service.ColorService;
import cn.babasport.xiu.core.service.FeatureService;
import cn.babasport.xiu.core.service.ImgService;
import cn.babasport.xiu.core.service.ProductService;
import cn.babasport.xiu.core.service.SkuService;
import cn.babasport.xiu.core.service.TypeService;

@Controller
@RequestMapping("/front")
public class ProductFrontController {
	@Autowired
	private ProductService productService; 
	//品牌
	@Autowired
	private BrandService brandService;
	//图片
	@Autowired
	private ImgService imgService;
	//sku
	@Autowired
	private SkuService skuService;
	//类型
	@Autowired
	private TypeService typeService;
	//颜色
	@Autowired
	private ColorService colorService;
	//材质
	@Autowired
	private FeatureService featureService;
	//全文检索
	@Autowired
	private LunceneService lunceneService;
	@Autowired
	private CookieProvider cookieProvider;
	

	@RequestMapping("productList.shtml")
	public String productList(
			Integer brandId,String brandName,                    //商品
			Integer minPrice,Integer maxPrice,                   //价格
			Integer typeId,String typeName,                      //类型
			Integer featureId,String featureName,                //材质
			String filPeople,                                    //适用人群
			Integer pageNo,                                      //页号
			boolean iscreateTimeDesc,
			boolean isSalesDesc,
            @ModelAttribute("products") ProductsVo products,              //list集合的包装类
			HttpServletRequest request,
            Model model){
		
		
	   //显示用户所在的位置(有时新浪的接口无法调用)
	   //<!-- 收藏本网站！${location.province}/${location.city}<a href="#" title="更换">[更换]</a>   -->
	   //IpAddressSina location = IpAddressUtils.getIpInfoBySina();
	   //model.addAttribute("location", location);
		
	   //查询热卖商品
	   List<Product> hotProducts = productService.getHotProducts();
	   model.addAttribute("baseUrl",CommonsConstant.UPLOADFILE_BASE_URL);
	   model.addAttribute("hotProducts",hotProducts);
	
	   //销量排行榜
	   List<Product> salesProducts = productService.getSalesProducts();
	   model.addAttribute("salesProducts", salesProducts);
	   
	   //查询条件列表的显示和隐藏
	   //商品列表
	   if(brandId==null){
		   //查询品牌
		   List<Brand> brands = brandService.getBrands();
		   model.addAttribute("brands", brands);
	   }else{
		   model.addAttribute("brandId",brandId);
		   model.addAttribute("brandName",brandName);
	   }
	   //类型列表
	   if(typeId==null){
		   //查询类型
		   TypeQuery typeQuery = new TypeQuery();
		   typeQuery.setFields("id,name");
		   List<Type> types = typeService.getTypeList(typeQuery);
		   model.addAttribute("types", types);
	   }else{
		   model.addAttribute("typeId",typeId);
		   model.addAttribute("typeName",typeName);
	   }
	   
	   //价格列表
	   //价格 从sku中查询对应的商品根据价格区间
	   if(minPrice==null||maxPrice==null){
		   //查询根据sku查处最大值，最小值（第一种方式 京东没有提供价格的条件显示）
		   //类似于适用人群(第二种)
		   model.addAttribute("showPrice", true);
		   
	   }else{
		   model.addAttribute("showPrice", false);
		   model.addAttribute("minPrice", minPrice);
		   model.addAttribute("maxPrice", maxPrice);
	   }
	   //材质列表
	   if(featureId==null){
		   //查询材质(通过材质查询商品，需要解析字符串)
		   FeatureQuery featureQuery = new FeatureQuery();
		   featureQuery.setFields("id,name");
		   List<Feature> features = featureService.getFeatureList(featureQuery);
		   model.addAttribute("features", features);
	   }else{
		   model.addAttribute("featureId",featureId);
		   model.addAttribute("featureName",featureName);
	   }
	   //适用人群(数据库暂时没有考虑到) 单独使用布尔类型来判断是否显示
	   if(StringUtils.isBlank(filPeople)){
		   model.addAttribute("showFilePeople", true);
	   }else{
		   model.addAttribute("showFilePeople", false);
		   model.addAttribute("filPeople", filPeople);
	   }
	   
	 //获取商品信息
 	
 	 if(products!=null&&products.getProducts().size()>0){ //如果索引数据 则model中放置索引出来的数据
 		  //将索引出来的商品添加到该方法中
		 Pagination pagination  = new Pagination();
		 pagination.setList(products.getProducts());
		 model.addAttribute("pagination", pagination);
 	  }else{
 		 //根据查询条件查询
		  Pagination pagination = productService.getProductsByFilter(brandId,brandName,                    //商品
					minPrice,maxPrice,                   
					typeId,typeName,                      
					featureId,featureName,                
					filPeople,pageNo,isSalesDesc,iscreateTimeDesc);
		  
		  model.addAttribute("pagination",pagination);
 	  }
 	 
 	 //获取购物车信息
 	BuyCart buyCart = null;
    
	buyCart = cookieProvider.getCookieObj(request, BuyCart.class);
	
	//如果没有则构建新的购物车对象，购物项对象 （在购物车对象中添加购物项）创建cookie，将购物项对象序列化并添加到到cookie中
	if(buyCart==null){
		buyCart = new  BuyCart();
	}
     
	buyCart = loadBuyCart(buyCart);
    model.addAttribute("buyCart", buyCart);
	 	   
	return "product/product";
	}
	
	
	//使用luncene 进行检索 搜索框 
	@RequestMapping("productSearch.shtml")
	@ResponseBody
	public List<String> productSearch(@RequestBody() String keyword,Model model){
		//为搜索框的关键字创建索引(第一次使用需要穿件索引)
		//String[] strs = {"瑜伽","瑜伽服","瑜伽垫","瑜伽辅助","舞蹈鞋服","瑜伽铺巾"};
	    //lunceneService.indexText(Arrays.asList(strs));
	    
	    List<String> results = lunceneService.searchText(keyword);
 		
		return results;
		
	}
	
	//使用luncene 进行检索 搜索框
	@RequestMapping("getproduct.shtml")
	public String getproduct(String keyword,RedirectAttributes model){
		//先创建商品索引
		//productService.indexProduct();
		
		//springmvc重定向时传递非基本类型的集合对象的问题
        List<Product> list = lunceneService.search(keyword);
        for(Product product:list){
        	Img img = new Img();
            img.setUrl(product.getImgUrl());
        	product.setImg(img);
        }
        ProductsVo products = new ProductsVo();
        products.setProducts(list);
        
        model.addFlashAttribute("products", products);
        
		//return "redirect:productList.shtml?products="+products;
        return "redirect:productList.shtml";
	}
	
	//进入商品详情页
	@RequestMapping("/{productId}/productDetail.shtml")
	public String productDetail(@PathVariable("productId") Integer productId,Model model){
	//@RequestMapping("productDetail.shtml")
	//public String productDetail(Integer productId,Model model){
		//获得商品
		Product product = productService.getProductByKey(productId);
		//获取图片
		Img img = imgService.getImgList(new ImgQuery().setProductId(productId)).get(0);
       
		product.setImg(img);
		
		//获得sku
		//根据商品id获取获取sku
		SkuQuery skuQuery = new SkuQuery();
		skuQuery.setProductId(productId);
		List<Sku> skus = skuService.getSkuList(skuQuery);
		
		List<Color> colors = new ArrayList<Color>();
		//考虑颜色去重复
		for(Sku sku:skus){
			Color color = colorService.getColorByKey(sku.getColorId());
			if(!colors.contains(color)){
				colors.add(color);
				//color中的url
				color.setImgUrl(sku.getSkuImg());
			}
		}
		
		  //销量排行榜
		List<Product> salesProducts = productService.getSalesProducts();
		
		model.addAttribute("salesProducts", salesProducts);
		model.addAttribute("product",product);
		model.addAttribute("skus", skus);
		model.addAttribute("colorList",colors);
		model.addAttribute("baseUrl",CommonsConstant.UPLOADFILE_BASE_URL);
		
		return "product/productDetail";
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
