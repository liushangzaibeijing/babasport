package cn.babasport.xiu.core.controller.back;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nibnait.common.page.Pagination;

import cn.babasport.xiu.common.constant.CommonsConstant;
import cn.babasport.xiu.core.bean.Brand;
import cn.babasport.xiu.core.bean.Color;
import cn.babasport.xiu.core.bean.Feature;
import cn.babasport.xiu.core.bean.Img;
import cn.babasport.xiu.core.bean.PriceSection;
import cn.babasport.xiu.core.bean.Product;
import cn.babasport.xiu.core.bean.QueryBean;
import cn.babasport.xiu.core.bean.Sku;
import cn.babasport.xiu.core.bean.Type;
import cn.babasport.xiu.core.bean.query.ColorQuery;
import cn.babasport.xiu.core.bean.query.FeatureQuery;
import cn.babasport.xiu.core.bean.query.ImgQuery;
import cn.babasport.xiu.core.bean.query.ProductQuery;
import cn.babasport.xiu.core.bean.query.SkuQuery;
import cn.babasport.xiu.core.bean.query.TypeQuery;
import cn.babasport.xiu.core.lucene.service.LunceneService;
import cn.babasport.xiu.core.service.BrandService;
import cn.babasport.xiu.core.service.ColorService;
import cn.babasport.xiu.core.service.FeatureService;
import cn.babasport.xiu.core.service.ImgService;
import cn.babasport.xiu.core.service.ProductService;
import cn.babasport.xiu.core.service.SkuService;
import cn.babasport.xiu.core.service.TypeService;
import cn.babasport.xiu.core.staticpage.service.FreemarkTemplateService;


@Controller
@RequestMapping("/back")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	@Autowired
	private BrandService brandService;
	@Autowired
	private TypeService typeService;
	@Autowired 
	private FeatureService featureService;
	@Autowired 
	private ColorService colorService;
	@Autowired 
	private SkuService skuService;
	@Autowired 
	private LunceneService lunceneService;
	@Autowired
	private ImgService imgService;
	//freemark模板服务类
	@Autowired
	private FreemarkTemplateService templateService;
	
	
	
	@RequestMapping("/productList.do")
    public String productList(String name,Integer brandId,Integer isShow,Integer pageNo,Model model){
		ProductQuery  query = new ProductQuery();
		
		StringBuffer params = new StringBuffer();
		
		//判断字段是否为null
		//判断name字段
	    if(StringUtils.isNotBlank(name)){
	       	query.setName(name).setNameLike(true);
	       	params.append("name=").append(name);
	    }
	    //判断brandId
	    if(brandId!=null){
	    	query.setBrandId(brandId);
	    	params.append("&brandId=").append(brandId);
	    }
	    //判断isShow
	    if(isShow!=null){
	    	query.setIsShow(isShow);
	    	params.append("&isShow=").append(isShow);
	    }
	    
		Pagination pagination = productService.getProductListWithPage(query, pageNo);
		
		//设置分页
		pagination.pageView("/babasport/back/productList.do", params.toString());
		model.addAttribute("pagination",pagination);

		//查询所有的品牌
		List<Brand> brands = brandService.getBrands();
		
		
		//将查询结果返回到页面
		model.addAttribute("name",name);
		model.addAttribute("brandId",brandId);
		model.addAttribute("isShow",isShow);
		model.addAttribute("brands",brands);
		model.addAttribute("baseUrl",CommonsConstant.UPLOADFILE_BASE_URL);
		
		return "product/list";
	}
		
	/**
	 * 跳转到添加页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/addUI.do")
	public String addUI(Model model){
		
		TypeQuery typeQuery = new TypeQuery();
		
		typeQuery.setFields("id,name");
		
		//查询商品类型
		List<Type> types = typeService.getTypeList(typeQuery);
		
		//查询所有的品牌
		List<Brand> brands = brandService.getBrands();

		//查询所有的类型
		FeatureQuery featureQuery = new FeatureQuery();
		featureQuery.setFields("id,name");
		List<Feature> features = featureService.getFeatureList(featureQuery);
		
		//查询颜色
		ColorQuery colorQuery = new ColorQuery();
		colorQuery.setFields("id,name");
		List<Color> colors = colorService.getColorList(colorQuery);
		
		model.addAttribute("types", types);
		model.addAttribute("brands", brands);
		model.addAttribute("features", features);
		model.addAttribute("colors", colors);
		
		return "product/add";
	}

	@RequestMapping("/addProduct.do")
	public String addProduct(Product product,Img img){
		//添加商品
		product.setImg(img);
		
        productService.addProduct(product);
        //添加商品索引
        product.setImgUrl(img.getUrl());
        
        PriceSection priceSection = skuService.getPriceSection(product.getId());
        
        product.setMaxPrice(priceSection.getMaxPrice());
        product.setMinPrice(priceSection.getMinPrice());
        
        lunceneService.indexProduct(product);
        
        return "redirect:/back/productList.do";
	}
	
	//错误无法映射
	@Deprecated
	@RequestMapping("/deleteProduct2.do")
	public String deleteProduct(@RequestBody String ids,@RequestBody String name,@RequestBody Integer brandId,@RequestBody Integer isShow,Model model){
		//将ids字符串转换为list
		productService.deleteProduts(ids);
		
		model.addAttribute("name", name);
		model.addAttribute("brandId", brandId);
		model.addAttribute("isShow", isShow);
		
		//return "redirect:/back/productList.do";
		return "redirect:/back/productList.do";
	}
	@RequestMapping("/deleteProduct.do")
	public String deleteProductext(@RequestBody QueryBean queryBean,Model model){
		//将ids字符串转换为list
		productService.deleteProduts(queryBean.getIds());
		
		//删除商品索引
		String[] ids = queryBean.getIds().split(",");
		List<Integer> productIds = new ArrayList<Integer>();
		for(String id:ids){
			productIds.add(Integer.parseInt(id));
		}
		lunceneService.deleteProductsIndex(productIds);;
		
		model.addAttribute("name", queryBean.getName());
		model.addAttribute("brandId", queryBean.getBrandId());
		model.addAttribute("isShow", queryBean.getIsShow());
		
		return "redirect:/back/productList.do";
	}
	
	//上架
	@RequestMapping("/putOnsale")
	public String putOnsale(@RequestBody QueryBean queryBean,HttpServletRequest request,Model model){
		productService.updateProductsPutOn(queryBean.getIds());

		List<String> ids = Arrays.asList(queryBean.getIds().split(","));

		// 查询商品，sku colorlist salesproducts baseUrl
        for(String id:ids){
        	createProductStaticPage(request, id);
        }
		

		model.addAttribute("name", queryBean.getName());
		model.addAttribute("brandId", queryBean.getBrandId());
		model.addAttribute("isShow", queryBean.getIsShow());

		return "redirect:/back/productList.do";

	}

	/**
	 * 生成商品详情页的静态模板
	 * @param request
	 * @param id 商品id
	 */
	private void createProductStaticPage(HttpServletRequest request, String id) {
		Integer productId = Integer.parseInt(id);
		Product product = productService.getProductByKey(productId);
		// 获取图片
		Img img = imgService.getImgList(new ImgQuery().setProductId(productId)).get(0);

		product.setImg(img);

		// 获得sku
		// 根据商品id获取获取sku
		SkuQuery skuQuery = new SkuQuery();
		skuQuery.setProductId(productId);
		List<Sku> skus = skuService.getSkuList(skuQuery);

		List<Color> colors = new ArrayList<Color>();
		// 考虑颜色去重复
		for (Sku sku : skus) {
			Color color = colorService.getColorByKey(sku.getColorId());
			if (!colors.contains(color)) {
				colors.add(color);
				// color中的url
				color.setImgUrl(sku.getSkuImg());
			}
		}

		// 销量排行榜
		List<Product> salesProducts = productService.getSalesProducts();

		Map<String,Object> rootMap = new HashMap<String,Object>();
		rootMap.put("salesProducts", salesProducts);  //设置销量排行榜
		rootMap.put("product", product);  //设置商品
		rootMap.put("skus", skus);        //设置sku
		rootMap.put("colorList", colors); //设置颜色列表
		
		rootMap.put("baseUrl", CommonsConstant.UPLOADFILE_BASE_URL);  //设置图片的基路径
		rootMap.put("contextPath", request.getContextPath());  //设置项目路径
		
		templateService.createStaticPage(rootMap, productId);
	}
	
	//下架
	@RequestMapping("/putOffsale")
	public String putOffsale(@RequestBody QueryBean queryBean, Model model){
        productService.updateProductsPutOff(queryBean.getIds());
		
		model.addAttribute("name", queryBean.getName());
		model.addAttribute("brandId", queryBean.getBrandId());
		model.addAttribute("isShow", queryBean.getIsShow());
		
		return "redirect:/back/productList.do";
		
	}
	
	
}
