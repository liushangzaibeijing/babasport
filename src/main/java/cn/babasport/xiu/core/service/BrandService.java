package cn.babasport.xiu.core.service;

import java.util.List;

import com.nibnait.common.page.Pagination;

import cn.babasport.xiu.core.bean.Brand;

public interface BrandService {
	
	/**
	 * 通过查询条件查询到商品信息并分页显示
	 * @param Brand 封装查询条件的brand
	 * @param pageNo 页号
	 * @return
	 */
	public Pagination getBrandsPage(Brand Brand,Integer pageNo);
	
	/**
	 * 通过查询条件查询到商品信息
	 * @param Brand 封装查询条件的brand
	 * @param pageNo 页号
	 * @return
	 */
	public List<Brand> getBrands();

	/**
	 * 添加品牌
	 * @param brand 品牌对象
	 */
	public void addBrand(Brand brand);

    /**
     * 将拼接的ids 分割id数据来删除
     * @param ids
     */
	public void deleteBrands(String ids,String name,int isDisplay);

	/**
	 * 根据id查询品牌对象
	 * @param id
	 * @return
	 */
	public Brand selectOne(int id);

	/**
	 * 品牌的更新操作，非空字段不进行更新
	 * @param brand
	 */
	public void updateBrand(Brand brand);

}
