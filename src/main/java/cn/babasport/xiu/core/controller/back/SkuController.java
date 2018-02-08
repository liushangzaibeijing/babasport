package cn.babasport.xiu.core.controller.back;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nibnait.common.page.Pagination;

import cn.babasport.xiu.common.constant.CommonsConstant;
import cn.babasport.xiu.core.bean.Color;
import cn.babasport.xiu.core.bean.Product;
import cn.babasport.xiu.core.bean.Sku;
import cn.babasport.xiu.core.bean.query.ColorQuery;
import cn.babasport.xiu.core.bean.query.ProductQuery;
import cn.babasport.xiu.core.bean.query.SkuQuery;
import cn.babasport.xiu.core.service.ColorService;
import cn.babasport.xiu.core.service.ProductService;
import cn.babasport.xiu.core.service.SkuService;


@Controller
@RequestMapping("/back")
public class SkuController {
	
	@Autowired
	private SkuService skuService;
	@Autowired
	private ProductService productService;
	@Autowired
	private ColorService colorService;
    
	@RequestMapping(value="skuList.do")
	public String skuList(Integer productId,Integer pageNo,Model model){
	    //创建查询条件
		if(productId==null){
			throw new RuntimeException("the productId can not null");
		}
		
		StringBuffer params = new StringBuffer();
		params.append("&productId=").append(productId);
		
		if(pageNo==null){
			pageNo =Pagination.cpn(pageNo);
		}
		
		SkuQuery skuQuery = new SkuQuery();
		skuQuery.setFields("id,product_id,color_id,size,market_price,sku_price,stock_inventory,sku_upper_limit,delive_fee,sku_type");
		skuQuery.setProductId(productId);
		skuQuery.setPageNo(pageNo);
		skuQuery.setPageSize(10);
		
		Pagination pagination = skuService.getSkuListWithPage(skuQuery);
		
		pagination.pageView("/babasport/back/skuList.do", params.toString());
	 	
		model.addAttribute("pagination", pagination);
		
	    return "sku/list";
	}
	
	
	//跳转到sku的更新页面
	@RequestMapping(value="updateSkuUI.do")
	public String updateSkuUI(Integer skuId,Model model){
	    //创建查询条件
		if(skuId==null){
			throw new RuntimeException("the skuId can not null");
		}
		//查询商品，颜色
		ProductQuery productQuery = new ProductQuery();
		productQuery.setFields("id,name");
		List<Product> products = productService.getProductListNoPage(productQuery);
		
		ColorQuery colorQuery = new ColorQuery();
		colorQuery.setFields("id,name");
		List<Color> colors = colorService.getColorList(colorQuery);
		
		Sku sku = skuService.getSkuByKey(skuId);
		
		model.addAttribute("products",products);
		model.addAttribute("colors",colors);
		model.addAttribute("sku",sku);
		model.addAttribute("baseUrl",CommonsConstant.UPLOADFILE_BASE_URL);
		
		return "sku/edit";
	}
	
	//sku的更新操作
	@RequestMapping(value="updateSku.do")
	public String updateSkuUI(Sku sku,Model model,HttpServletResponse response){
	    //判断sku是否为null
		if(sku==null){
			throw new RuntimeException("the sku can not null");
		}
		//在判断用户更新后的sku是否与其他sku冲突
		//根据商品id,颜色id,尺寸查询是否更新的sku与其他的sku有冲突
		SkuQuery skuQuery = new SkuQuery();
				
		skuQuery.setProductId(sku.getProductId());
		skuQuery.setColorId(sku.getColorId());
		skuQuery.setId(sku.getId());
		skuQuery.setSize(sku.getSize());
		Integer id = skuService.getSku(skuQuery);
		//用户更新了没有（怎么判断用户更新了没有）
		if(id!=null){
			//前台提示该sku已经存在 使用java代码向前台输出js脚本
			response.setContentType("text/html;charset=UTF-8");
	        response.setCharacterEncoding("UTF-8");
	        response.setHeader("Cache-Control", "no-cache");
			PrintWriter out = null;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
			out.println("<script language='javascript'>");
			out.println("alert('该sku已经存在')");
			out.println("history.go(-1)");
			out.println("</script>");
			out.flush();
			out.close();
			return null;
		}
        		
		
		//设置更新时间和更新人的id(从session中取出用户的登录id后期做)
		sku.setUpdateTime(new Date());
		skuService.updateSkuByKey(sku);
		model.addAttribute("productId", sku.getProductId());
		
		return "redirect:skuList.do";
	}
	
	//跳转到sku的添加页面
	@RequestMapping(value="addSkuUI.do")
	public String addSkuUI(Model model){
		//查询商品，颜色
		ProductQuery productQuery = new ProductQuery();
		productQuery.setFields("id,name");
		List<Product> products = productService.getProductListNoPage(productQuery);
		
		ColorQuery colorQuery = new ColorQuery();
		colorQuery.setFields("id,name");
		List<Color> colors = colorService.getColorList(colorQuery);
		
		model.addAttribute("products",products);
		model.addAttribute("colors",colors);
		
		return "sku/add";
	}
	
	//sku的添加操作
	@RequestMapping(value="addSku.do")
	public String addSku(Sku sku,Model model,HttpServletResponse response){
	   //创建查询条件
	   if(sku==null){
		  throw new RuntimeException("the sku can not null");
	   }
		//在判断用户更新后的sku是否与其他sku冲突
		//根据商品id,颜色id,尺寸查询是否更新的sku与其他的sku有冲突
		SkuQuery skuQuery = new SkuQuery();
				
		skuQuery.setProductId(sku.getProductId());
		skuQuery.setColorId(sku.getColorId());
		//skuQuery.setId(sku.getId());
		skuQuery.setSize(sku.getSize());
		Integer id = skuService.getSku(skuQuery);
		//用户更新了没有（怎么判断用户更新了没有）
		if(id!=null){
			//前台提示该sku已经存在 使用java代码向前台输出js脚本
			response.setContentType("text/html;charset=UTF-8");
	        response.setCharacterEncoding("UTF-8");
	        response.setHeader("Cache-Control", "no-cache");
			PrintWriter out = null;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
			out.println("<script language='javascript'>");
			out.println("alert('该sku已经存在')");
			//out.println("history.go(-1)");
			out.println("window.location.href=${pageContext.request.contextPath}/back/skuList.do");
			out.println("</script>");
			out.flush();
			out.close();
			return null;
		}
       	
	   skuService.addSku(sku);
	   model.addAttribute("productId", sku.getProductId());
	   return "redirect:skuList.do";
	}
	
	
	@RequestMapping(value="deleteSku.do")
	public String deleteSku(Integer skuId,Integer productId,Model model){
	   if(skuId==null){
		   throw new RuntimeException("the skuId can not null");
	   }
	   
	    skuService.deleteByKey(skuId);
	    model.addAttribute("productId", productId);
	    
		return "redirect:skuList.do";
	}

}
