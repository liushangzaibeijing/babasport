package cn.babasport.xiu.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nibnait.common.page.Pagination;

import cn.babasport.xiu.core.bean.PriceSection;
import cn.babasport.xiu.core.bean.Product;
import cn.babasport.xiu.core.bean.Sku;
import cn.babasport.xiu.core.bean.query.ColorQuery;
import cn.babasport.xiu.core.bean.query.ProductQuery;
import cn.babasport.xiu.core.bean.query.SkuQuery;
import cn.babasport.xiu.core.dao.SkuDao;
import cn.babasport.xiu.core.service.ColorService;
import cn.babasport.xiu.core.service.ProductService;
import cn.babasport.xiu.core.service.SkuService;

/**
 * 最小销售单元事务层
 * @author lixu
 * @Date [2014-3-27 下午03:31:57]
 */
@Service
@Transactional
public class SkuServiceImpl implements SkuService {

	@Resource
	SkuDao skuDao;
	@Resource
	private ColorService colorService;
	@Resource
	private ProductService productService;

	/**
	 * 插入数据库
	 * 
	 * @return
	 */
	public Integer addSku(Sku sku) {
        return skuDao.addSku(sku);
	}

	/**
	 * 根据主键查找
	 */
	@Transactional(readOnly = true)
	public Sku getSkuByKey(Integer id) {

        Sku sku = skuDao.getSkuByKey(id);
        //通过商品ID
        Product product = productService.getProductByKey(sku.getProductId());

        sku.setProduct(product);
        //颜色加载
        sku.setColor(colorService.getColorByKey(sku.getColorId()));

        return sku;
	}
	
	@Transactional(readOnly = true)
	public List<Sku> getSkusByKeys(List<Integer> idList) {
		return skuDao.getSkusByKeys(idList);
	}

	/**
	 * 根据主键删除
	 * @return
	 */
	public Integer deleteByKey(Integer id) {
		return skuDao.deleteByKey(id);
	}

	public Integer deleteByKeys(List<Integer> idList) {
		return skuDao.deleteByKeys(idList);
	}

	/**
	 * 根据主键更新
	 * @return
	 */
	public Integer updateSkuByKey(Sku sku) {
		return skuDao.updateSkuByKey(sku);
	}
	
	@Transactional(readOnly = true)
	public Pagination getSkuListWithPage(SkuQuery skuQuery) {
		Pagination pagination = new Pagination(skuQuery.getPageNo(),skuQuery.getPageSize(),skuDao.getSkuListCount(skuQuery));
		List<Sku> skuList = skuDao.getSkuListWithPage(skuQuery);
	   
		ColorQuery colorQuery = new ColorQuery();
		colorQuery.setFields("name");
		ProductQuery productQuery = new ProductQuery();
		productQuery.setFields("no");
	    for (Sku sku:skuList){
	    	colorQuery.setId(sku.getColorId());
            sku.setColor(colorService.getColorList(colorQuery).get(0));
            productQuery.setId(sku.getProductId());
            sku.setProduct(productService.getProductListNoPage(productQuery).get(0));
	    }
		
	    pagination.setList(skuDao.getSkuListWithPage(skuQuery));
		return pagination;
	}
	
	@Transactional(readOnly = true)
	public List<Sku> getSkuList(SkuQuery skuQuery) {
        List<Sku> skuList = skuDao.getSkuList(skuQuery);
        for (Sku sku:skuList){
            sku.setColor(colorService.getColorByKey(sku.getColorId()));
        }
        return skuList;
	}
	
	@Transactional(readOnly = true)
	public Integer getSku(SkuQuery skuQuery) {
        return skuDao.getSku(skuQuery);
	}

    public List<Sku> getStock(Integer id) {
        List<Sku> skuList = skuDao.getStock(id);
        for (Sku sku:skuList){
            sku.setColor(colorService.getColorByKey(sku.getColorId()));
        }
        return skuList;
    }

    public void deleteByProductId(Integer id) {
        skuDao.deleteByProductId(id);
    }

    public void deleteByProductIds(List<Integer> ids) {
        skuDao.deleteByProductIds(ids);
    }

	@Override
	public PriceSection getPriceSection(Integer productId) {
		return skuDao.getPriceSection(productId);
	}

	@Override
	public List<Integer> getProductIdByPrices(PriceSection priceSection) {
		//如果只有最小价格 查询大于这个最小价格的所有商品id
		//如果只有最大价格，查询小于这个最大价格的所有商品id
		//如果两者都有，则查询两者之间的满足的商品id
		return skuDao.getProductIdByPrices(priceSection);
	}
}
