package cn.babasport.xiu.core.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.babasport.xiu.core.bean.Buyer;
import cn.babasport.xiu.core.bean.City;
import cn.babasport.xiu.core.bean.Province;
import cn.babasport.xiu.core.bean.Town;
import cn.babasport.xiu.core.bean.query.BuyerQuery;
import cn.babasport.xiu.core.bean.query.CityQuery;
import cn.babasport.xiu.core.bean.query.ProvinceQuery;
import cn.babasport.xiu.core.bean.query.TownQuery;
import cn.babasport.xiu.core.dao.BuyerDao;
import cn.babasport.xiu.core.dao.CityDao;
import cn.babasport.xiu.core.dao.ProvinceDao;
import cn.babasport.xiu.core.dao.TownDao;
import cn.babasport.xiu.core.service.BuyerService;

/**
 * 用户服务的实现类
 */
@Service("buyerService")
@Transactional
public class BuyerServiceImpl implements BuyerService {
	
	@Autowired
	private BuyerDao buyerDao;
	@Autowired
	private ProvinceDao provinceDao;
	@Autowired
	private CityDao cityDao;
	@Autowired
	private TownDao townDao;

	@Override
	@Transactional(readOnly=true)
	public Buyer getBuyerByUsername(String username) {
		BuyerQuery buyerQuery = new BuyerQuery();
		buyerQuery.setUsername(username);
		
		Buyer buyer = buyerDao.getBuyerByUserName(buyerQuery);
		//设置省市县
	
		/*
		Province province = provinceDao.getProvinceList(new ProvinceQuery().setCode(buyer.getProvince())).get(0);
		CityQuery cityQuery = new CityQuery().setCode(buyer.getCity());
		List<City> citys = cityDao.getCityList(cityQuery);
		City city = cityDao.getCityList(new CityQuery().setCode(buyer.getCity())).get(0);
		
		Town town = townDao.getTownList(new TownQuery().setCode(buyer.getTown())).get(0);
		
		buyer.setProvince(province.getName());
		buyer.setCity(city.getName());
		buyer.setTown(town.getName());
		*/
		
		return buyer;
	}

	@Override
	public void saveBuyer(Buyer buyer) {
		buyerDao.updateBuyerByUsername(buyer);
	}

	@Override
	public Buyer getBuyerByBuyerId(String username) {
		BuyerQuery buyerQuery = new BuyerQuery();
		buyerQuery.setUsername(username);
		
		Buyer buyer = buyerDao.getBuyerByUserName(buyerQuery);
		//设置省市县
	
		
		Province province = provinceDao.getProvinceList(new ProvinceQuery().setCode(buyer.getProvince())).get(0);
		CityQuery cityQuery = new CityQuery().setCode(buyer.getCity());
		List<City> citys = cityDao.getCityList(cityQuery);
		City city = cityDao.getCityList(new CityQuery().setCode(buyer.getCity())).get(0);
		
		Town town = townDao.getTownList(new TownQuery().setCode(buyer.getTown())).get(0);
		
		buyer.setProvince(province.getName());
		buyer.setCity(city.getName());
		buyer.setTown(town.getName());
		
		
		return buyer;
	}

   
}
