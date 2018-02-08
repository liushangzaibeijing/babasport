package cn.babasport.xiu.core.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nibnait.common.page.Pagination;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import cn.babasport.xiu.common.constant.CommonsConstant;
import cn.babasport.xiu.common.utils.FormatDateUtils;
import cn.babasport.xiu.core.bean.Img;
import cn.babasport.xiu.core.bean.PriceSection;
import cn.babasport.xiu.core.bean.Product;
import cn.babasport.xiu.core.bean.Sku;
import cn.babasport.xiu.core.bean.query.ImgQuery;
import cn.babasport.xiu.core.bean.query.ProductQuery;
import cn.babasport.xiu.core.bean.query.ProductQuery.OrderField;
import cn.babasport.xiu.core.dao.ProductDao;
import cn.babasport.xiu.core.lucene.service.LunceneService;
import cn.babasport.xiu.core.service.ImgService;
import cn.babasport.xiu.core.service.ProductService;
import cn.babasport.xiu.core.service.SkuService;

@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {
    
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ImgService imgService;
	@Autowired
	private SkuService skuService;
	@Autowired
	private LunceneService lunceneService;
	
	public Pagination getProductListWithPage(ProductQuery query,Integer pageNo) {
		query.setPageNo(Pagination.cpn(pageNo));
		
		List<Product> list = productDao.getProductListWithPage(query);
		
		//将每一个图片添加到指定的list中
		for(Product product:list){
			Img img = imgService.getImgList(new ImgQuery().setProductId(product.getId())).get(0);
			product.setImg(img);
		}
		
		Pagination pagination = new Pagination(query.getPageNo(), query.getPageSize(),
				                productDao.getProductCounts(query));
		
		pagination.setList(list);
		
		
		return pagination;
	}
	
	@Override
	public List<Product> getProductListNoPage(ProductQuery query) {
		return productDao.getProductListNoPage(query);
	}
	

	
	public void addProduct(Product product) {
		product.setNo(FormatDateUtils.dateToString3(new Date()));
        product.setCreateTime(new Date());
        //**保存商品**************begin*************/
        //需useGeneratedKeys自动生成商品id，返回的i是影响到的行数。
        Integer i = productDao.addProduct(product);
        System.out.println("影响的行数："+i);

        /**保存图片**************begin*************/
        product.getImg().setProductId(product.getId());
        product.getImg().setIsDef(1);
       
        imgService.addImg(product.getImg());
        
	    //具体到每一个颜色每一个尺寸均为一个销售单元
	    /**保存sku**************begin*************/
        Sku sku = new Sku();
        sku.setProductId(product.getId());
        sku.setDeliveFee(10.00);//运费
        sku.setSkuPrice(0.00);//售价
        sku.setMarketPrice(0.00);//市场价
        sku.setStockInventory(0);//库存
        sku.setSkuUpperLimit(0);//购买限制
        sku.setCreateTime(new Date());//添加时间
        sku.setLastStatus(1);//是 最新
        sku.setSkuType(1);//是普通商品（不是赠品）
        for (String color : product.getColor().split(",")) {
            sku.setColorId(Integer.parseInt(color));
            for (String size : product.getSize().split(",")) {
                sku.setSize(size);
                skuService.addSku(sku);//**********保存*********************
            }
        }
        
    }

    /**
     * 根据主键查找商品
     */
	public Product getProductByKey(Integer productId) {
		return productDao.getProductBykey(productId);
	}

    /**
     * 删除商品根据主键id
     */
	public void deleteProduts(String ids) {
		String[] idarray = ids.split(",");
		List<Integer> list = new ArrayList<Integer>();
		
		ImgQuery imgQuery = new ImgQuery();
		imgQuery.setFields("id,url");
		for(int i=0;i<idarray.length;i++){
			list.add(Integer.parseInt(idarray[i]));
			imgQuery.setProductId(Integer.parseInt(idarray[i]));
			List<Img> imgs = imgService.getImgList(imgQuery);
			//先删除图片
		    deleteImgResource(imgs);
			
		}
		//根据商品id查询对应的商品（图片路径传递到jersey服务上删除对应的图片，图片id删除数据库中的记录）
		
		//删除图片的对应的数据库记录
		imgService.deleteByProductIds(list);
		
		//再删除sku(直接根据商品id删除sku)
		skuService.deleteByProductIds(list);
		
		productDao.deleteProductMutil(list);
	}
	
	//使用jersey的客户端远程服务删除图片资源
	private void  deleteImgResource(List<Img> imgs){
		 Client client = Client.create();
		 
		 for(Img img :imgs){
			  WebResource webResource = client.resource("http://127.0.0.1:8088/babasportImgService/img/");
			  webResource.path("deleteImg").queryParam("imgfilePath",img.getUrl()).delete();  
		 }
	}


	//商品上架
	public void updateProductsPutOn(String ids) {
        Product product = new Product();
        product.setIsShow(1);
        
        for(String id : ids.split(",")){
        	product.setId(Integer.parseInt(id));
        	productDao.updateProductByKey(product);
        }
	}
	
	//商品下架
	public void updateProductsPutOff(String ids) {
	     Product product = new Product();
	     product.setIsShow(0);
	       
         for(String id : ids.split(",")){
	     	product.setId(Integer.parseInt(id));
	     	productDao.updateProductByKey(product);
	     }
	}

	@Override
	public List<Product> getHotProducts() {
		
	   //设置查询条件  上架，热卖
	   ProductQuery productQuery = new ProductQuery();
	   productQuery.setIsShow(1);
	   productQuery.setIsHot(1);
	   productQuery.setPageNo(1);
	   productQuery.setPageSize(4);
		
       List<Product> products = productDao.getHotProductList(productQuery);
       
       for(Product product:products){
    	   PriceSection priceSection = skuService.getPriceSection(product.getId());
           product.setMaxPrice(priceSection.getMaxPrice());
           product.setMinPrice(priceSection.getMinPrice());
       }
       return products;
	}

	@Override
	public List<Product> getSalesProducts() {
		   //销量排行榜
		   ProductQuery productQuery = new ProductQuery();
		  
		   productQuery.setFields("id,name,description,keywords");
		   productQuery.setIsShow(1);
		   productQuery.setIsHot(1);
		   productQuery.setPageNo(1);
		   productQuery.setPageSize(6);
	       productQuery.orderbySales(false);
		   List<Product> salesProducts = this.getProductListNoPage(productQuery);
	       
		   ImgQuery imgQuery = new ImgQuery();
		   imgQuery.setFields("url");
		   int i = 1;
		   for(Product product:salesProducts){
			   product.setNum(i++);;
			   //添加图片
			   imgQuery.setProductId(product.getId());
			   product.setImg(imgService.getImgList(imgQuery).get(0));
			   //添加价格区间
			   PriceSection priceSection = skuService.getPriceSection(product.getId());
	           product.setMaxPrice(priceSection.getMaxPrice());
	           product.setMinPrice(priceSection.getMinPrice());
		   }
		   
		   return salesProducts;
		   
	}

	@Override
	public Pagination getProductsByFilter(Integer brandId, String brandName, Integer minPrice, Integer maxPrice,
			Integer typeId, String typeName, Integer featureId, String featureName, String filPeople, Integer pageNo,
			boolean isSalesDesc,boolean iscreateTimeDesc) {
		
		   StringBuffer params = new StringBuffer();
		   
		   //创建查询条件
	 	   ProductQuery productQuery = new ProductQuery();
	 	   
	 	   if(brandId!=null){
	 		   productQuery.setBrandId(brandId);
	 		   params.append("&brandId=").append(brandId);
	 		   params.append("&brandName=").append(brandName);
	 	   }
	 	   
	 	  if(typeId!=null){
	 		   productQuery.setTypeId(typeId);
	 		   params.append("&typeId=").append(typeId);
	 		   params.append("&typeName=").append(typeName);
	 	   }
	 	  
	 	  if(typeId!=null){
	 		   productQuery.setFeature(String.valueOf(featureId));
	 		   productQuery.setFeatureLike(true);
	 		   params.append("&featureId=").append(featureId);
	 		   params.append("&featureName=").append(featureName);
	 	   }
	 	   
	 	   PriceSection priceSection = null;
           if(maxPrice!=null&&minPrice!=null){
        	   priceSection = new PriceSection();
        	   priceSection.setMinPrice(minPrice);
        	   params.append("&minPrice=").append(minPrice);
        	   priceSection.setMaxPrice(maxPrice);
        	   params.append("&maxPrice=").append(maxPrice);
        	   
           }
           
           //设置适用人群
           if(filPeople!=null){
        	   params.append("&filPeople=").append(filPeople);
           }
		   //设置排序
           if(iscreateTimeDesc){
        	   productQuery.orderbyCreateTime(false);
           }
           if(isSalesDesc){
        	   productQuery.orderbySales(false);
           }
           
		   //设置分页条件
		   productQuery.setPageNo(Pagination.cpn(pageNo));
		   productQuery.setPageSize(12); 
		
		  List<Integer> productIds = skuService.getProductIdByPrices(priceSection);
		  // SELECT id,NAME,description, p.keywords FROM bbs_product WHERE id IN (276,278) AND  
		  List<Product> products = productDao.getProductByFilter(productQuery,productIds);
		  
		  ImgQuery imgQuery = new ImgQuery();
		  imgQuery.setFields("url");
		  //获取图片
		  for(Product product:products){
			  imgQuery.setProductId(product.getId());
			  product.setImg(imgService.getImgList(imgQuery).get(0));
		      
			  //添加价格区间
		      PriceSection priceRegion = skuService.getPriceSection(product.getId());
              product.setMaxPrice(priceRegion.getMaxPrice());
              product.setMinPrice(priceRegion.getMinPrice());
		  }
		  
		  int counts = productDao.getProductByFilterCounts(productQuery, productIds);
		  Pagination pagination = new Pagination(productQuery.getPageNo(), productQuery.getPageSize(),counts );
		  pagination.setList(products);
		  
		  pagination.pageView("/front/productList.shtml", params.toString());
		  
		  return pagination;
	}
	//查询所有商品，并为所有已经上架的商品添加索引
		public void indexProduct(){
			ProductQuery productQuery = new ProductQuery();
			productQuery.setFields("id,name,description,keywords");
			productQuery.setIsShow(1);
			
			List<Product> products = getProductListNoPage(productQuery);
			
			//从ImgService中获取imgUrl
			for(Product product:products){
				ImgQuery imgQuery = new ImgQuery();
				imgQuery.setProductId(product.getId());
				String imgUrl = imgService.getImgList(imgQuery).get(0).getUrl();
				product.setImgUrl(imgUrl);
				
				//从sku中获取最大值MaxPrice和最小值MinPrice
				PriceSection priceSection= skuService.getPriceSection(product.getId());
				product.setMaxPrice(priceSection.getMaxPrice());
				product.setMinPrice(priceSection.getMinPrice());
				
			}
			
			lunceneService.indexProducts(products);
		}
}