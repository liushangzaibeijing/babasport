package cn.babasport.xiu.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.babasport.xiu.core.bean.PriceSection;
import cn.babasport.xiu.core.bean.Product;
import cn.babasport.xiu.core.bean.query.ProductQuery;

/**
 * 品牌查询的dao接口
 * @author xieqixiu
 *
 */
public interface ProductDao {
	/**
	 * 根据单个主键查询
	 * @param id 主键
	 * @return 品牌实体对象
	 */
	Product getProductBykey(Integer id);
	/**
	 * 根据多个主键查询
	 * @param ids 主键集合
	 * @return 品牌列表集合
	 */
	List<Product> getProductBykeys(List<Integer> ids);
	
	/**
	 * 查询所有不分页 
	 * @param query 查询条件对象
	 * @return 品牌列表集合
	 */
	List<Product> getProductListNoPage(ProductQuery query);
	
	/**
	 * 查询所有分页 
	 * @param query 查询条件对象
	 * @return 经过分页品牌列表集合
	 */
	List<Product> getProductListWithPage(ProductQuery query);
	
	/**
	 * 查询记录总数 
	 * @param query 查询条件对象
	 * @return 总记录数
	 */
	int getProductCounts(ProductQuery query);
	
	/**
	 * 添加品牌记录
	 * @param product 品牌对象
	 */
	Integer addProduct(Product product);
	
	/**
	 * 更新品牌记录
	 * @param product 品牌对象
	 */
	void updateProductByKey(Product product);
	
	/**
	 * 删除单个
	 * @param id 主键id
	 */
	void deleteProductOne(Integer id);
	
	/**
	 * 批量删除
	 * @param ids 主键集合
	 */
	void deleteProductMutil(List<Integer> ids);
	
	
	//--------------- 前台页面特有的查询持久层的方法 ----------------------//
	/**
	 * 查询热卖商品
	 * @return
	 */
	List<Product> getHotProductList(ProductQuery productQuery);
	
	List<Product> getProductByFilter(@Param("productQuery")ProductQuery productQuery,@Param("productIds") List<Integer> productIds);
	
	/**
	 * 获取前台筛选条件后的记录总数
	 * @param productQuery
	 * @param productIds
	 * @return
	 */
	int getProductByFilterCounts(@Param("productQuery")ProductQuery productQuery,@Param("productIds") List<Integer> productIds);
}