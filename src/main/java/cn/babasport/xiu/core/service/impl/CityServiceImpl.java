package cn.babasport.xiu.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nibnait.common.page.Pagination;

import cn.babasport.xiu.core.bean.City;
import cn.babasport.xiu.core.bean.query.CityQuery;
import cn.babasport.xiu.core.dao.CityDao;
import cn.babasport.xiu.core.service.CityService;

/**
 * 市
 * @author lixu
 * @Date [2014-3-27 下午03:31:57]
 */
@Service
@Transactional
public class CityServiceImpl implements CityService {

	@Resource
	private CityDao cityDao;

	/**
	 * 插入数据库
	 * 
	 * @return
	 */
	public Integer addCity(City city) {
		return cityDao.addCity(city);
		
	}

	/**
	 * 根据主键查找
	 */
	@Transactional(readOnly = true)
	public City getCityByKey(Integer id) {
		return cityDao.getCityByKey(id);
	}
	
	@Transactional(readOnly = true)
	public List<City> getCitysByKeys(List<Integer> idList) {
		return cityDao.getCitysByKeys(idList);
	}

	/**
	 * 根据主键删除
	 * 
	 * @return
	 */
	public Integer deleteByKey(Integer id) {
		return cityDao.deleteByKey(id);
	}

	public Integer deleteByKeys(List<Integer> idList) {
		return cityDao.deleteByKeys(idList);
	}

	/**
	 * 根据主键更新
	 * 
	 * @return
	 */
	public Integer updateCityByKey(City city) {
		return cityDao.updateCityByKey(city);
	}
	
	@Transactional(readOnly = true)
	public Pagination getCityListWithPage(CityQuery cityQuery) {
		Pagination p = new Pagination(cityQuery.getPageNo(),cityQuery.getPageSize(),cityDao.getCityListCount(cityQuery));
		p.setList(cityDao.getCityListWithPage(cityQuery));
		return p;
	}
	
	@Transactional(readOnly = true)
	public List<City> getCityList(CityQuery cityQuery) {
		return cityDao.getCityList(cityQuery);
	}

	@Override
	public List<City> getCitys(String province) {
		return cityDao.getCitys(province);
	}
}
