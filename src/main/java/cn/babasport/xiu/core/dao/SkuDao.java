package cn.babasport.xiu.core.dao;

import java.util.List;

import cn.babasport.xiu.core.bean.PriceSection;
import cn.babasport.xiu.core.bean.Sku;
import cn.babasport.xiu.core.bean.query.SkuQuery;


public interface SkuDao {

	/**
	 * 添加
	 * @param sku
	 */
	public Integer addSku(Sku sku);

	/**
	 * 根据主键查找
	 * @param skuQuery
	 */
	public Sku getSkuByKey(Integer id);

	/**
	 * 根据主键批量查找
	 * @param skuQuery
	 */
	public List<Sku> getSkusByKeys(List<Integer> idList);

	/**
	 * 根据主键删除
	 * @param skuQuery
	 */
	public Integer deleteByKey(Integer id);

	/**
	 * 根据主键批量删除
	 * @param skuQuery
	 */
	public Integer deleteByKeys(List<Integer> idList);

	/**
	 * 根据主键更新
	 * @param skuQuery
	 */
	public Integer updateSkuByKey(Sku sku);

	/**
	 * 分页查询
	 * @param skuQuery
	 */
	public List<Sku> getSkuListWithPage(SkuQuery skuQuery);

	/**
	 * 集合查询
	 * @param skuQuery
	 */
	public List<Sku> getSkuList(SkuQuery skuQuery);
	
	/**
	 * 总条数
	 * @param skuQuery
	 */
	public int getSkuListCount(SkuQuery skuQuery);

    //查询库存大于>0
    public List<Sku> getStock(Integer id);

    //根据ProductId删除
    public void deleteByProductId(Integer id);

    //根据ProductIds删除
    public void deleteByProductIds(List<Integer> ids);
    
    //查重
    public Integer getSku(SkuQuery skuQuery);
    
    
	/**
	 * 商品本身没有价格字段，需要从sku中获取该商品的最大最小字段
	 * @param productId 商品id
	 * @return 商品价格区间对象
	 */
	PriceSection  getPriceSection(Integer productId);
	
	/**
	 * 根据商品价格区间查询
	 * @param priceSection
	 * @return
	 */
	List<Integer> getProductIdByPrices(PriceSection priceSection);

}
