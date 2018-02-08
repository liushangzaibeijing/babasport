package cn.babasport.xiu.core.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nibnait.common.page.Pagination;

import cn.babasport.xiu.core.bean.Brand;
import cn.babasport.xiu.core.dao.BrandDao;
import cn.babasport.xiu.core.service.BrandService;

@Service("brandService")
@Transactional
public class BrandServiceImpl implements BrandService {
	
	@Autowired
	private BrandDao brandDao;

	@Transactional(readOnly=true)
	public Pagination getBrandsPage(Brand brand,Integer pageNo) {
		//brand中的分页条件
		brand.setPageNo(Pagination.cpn(pageNo));
		brand.setPageSize(5);
		List<Brand> list = brandDao.getBrandsWithPage(brand);
		
		//创建分页对象
		Pagination page = new Pagination(brand.getPageNo(), brand.getPageSize(), brandDao.getTotalCounts(brand));
		
		page.setList(list);
		
		return page;
	}

	public void addBrand(Brand brand) {
		brandDao.saveBrand(brand);
	}

	public void deleteBrands(String ids,String name,int isDisplay) {
		String[] idarray = ids.split(",");
		List<Integer> list = new ArrayList<Integer>();
		
		for(int i=0;i<idarray.length;i++){
			list.add(Integer.parseInt(idarray[i]));
		}
		brandDao.deleteBrandbyCondition(list, name, isDisplay);
		
	}
	
	@Transactional(readOnly=true)
	public Brand selectOne(int id) {
		Brand brand = brandDao.getBrandOne(id);
		return brand;
	}

	public void updateBrand(Brand brand) {
		brandDao.updateBrand(brand);
		
	}

	public List<Brand> getBrands() {
		return brandDao.getBrands();
	}

	

}
