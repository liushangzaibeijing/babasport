package cn.babasport.xiu.core.service;


import java.util.List;

import com.nibnait.common.page.Pagination;

import cn.babasport.xiu.core.bean.PriceSection;
import cn.babasport.xiu.core.bean.Product;
import cn.babasport.xiu.core.bean.query.ProductQuery;

public interface ProductService {
	
	/**
	 * 获取商品列表(分页)
	 * @param query
	 * @return
	 */
	public Pagination getProductListWithPage(ProductQuery query,Integer pageNo);
	
	/**
	 * 获取商品列表(不分页)
	 * @param query
	 * @return
	 */
	public List<Product> getProductListNoPage(ProductQuery query);
		
	/**
	 * 添加商品
	 * @param product
	 */
	public void addProduct(Product product);

	/**
	 * 通过主键查找商品
	 * @param productId
	 * @return
	 */
	public Product getProductByKey(Integer productId);

	/**
	 * 删除商品
	 * @param ids 根据主键删除商品
	 */
	public void deleteProduts(String ids);

	/**
	 * 更新商品的状态为上架
	 * @param ids
	 */
	public void updateProductsPutOn(String ids);
	
	/**
	 * 更新商品的状态为下架
	 * @param ids
	 */
	public void updateProductsPutOff(String ids);

	/**
	 * 查询热卖商品
	 */
	public List<Product> getHotProducts();
    
	/**
	 * 销量排行
	 */
	public List<Product>  getSalesProducts();

	/**
	 * 根据前台的筛选条件查询对应的商品列表
	 * @param priceSection 价格查询，（从属于商品查询的附属条件）
	 */
	public Pagination getProductsByFilter(Integer brandId,String brandName,                    //商品
			Integer minPrice,Integer maxPrice,                   //价格
			Integer typeId,String typeName,                      //类型
			Integer featureId,String featureName,                //材质
			String filPeople,                                    //适用人群
			Integer pageNo,boolean isSalesDesc,boolean iscreateTimeDesc);
	
	
	public void indexProduct();

}
