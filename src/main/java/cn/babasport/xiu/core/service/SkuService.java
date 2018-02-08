package cn.babasport.xiu.core.service;

import java.util.List;

import com.nibnait.common.page.Pagination;

import cn.babasport.xiu.core.bean.PriceSection;
import cn.babasport.xiu.core.bean.Sku;
import cn.babasport.xiu.core.bean.query.SkuQuery;

/**
 * 
 * @author lixu
 * @Date [2014-3-28 下午01:50:28]
 */
public interface SkuService {
	/**
	 * 基本插入
	 * 
	 * @return
	 */
	public Integer addSku(Sku sku);

	/**
	 * 根据主键查询
	 */
	public Sku getSkuByKey(Integer id);

	/**
	 * 根据主键批量查询
	 */
	public List<Sku> getSkusByKeys(List<Integer> idList);

	/**
	 * 根据主键删除
	 * 
	 * @return
	 */
	public Integer deleteByKey(Integer id);

	/**
	 * 根据主键批量删除
	 * 
	 * @return
	 */
	public Integer deleteByKeys(List<Integer> idList);

	/**
	 * 根据主键更新
	 * 
	 * @return
	 */
	public Integer updateSkuByKey(Sku sku);

	/**
	 * 根据条件查询分页查询
	 * 
	 * @param skuQuery
	 *            查询条件
	 * @return
	 */
	public Pagination getSkuListWithPage(SkuQuery skuQuery);

	/**
	 * 根据条件查询
	 * 
	 * @param skuQuery
	 *            查询条件
	 * @return
	 */
	public List<Sku> getSkuList(SkuQuery skuQuery);


    //查询库存大于>0
    public List<Sku> getStock(Integer id);

    //根据ProductId删除
    public void deleteByProductId(Integer id);

    //根据ProductIds删除
    public void deleteByProductIds(List<Integer> ids);
    
    //查重
    public Integer getSku(SkuQuery skuQuery);
	
    /**
     * 查询商品价格区间
     * @param productId
     * @return
     */
    public PriceSection getPriceSection(Integer productId);

    /**
     * 根据价格区间查询对应的商品
     * @param priceSection 价格区间
     * @return 所有符合要求的品牌的id
     */
	public List<Integer> getProductIdByPrices(PriceSection priceSection);
}
