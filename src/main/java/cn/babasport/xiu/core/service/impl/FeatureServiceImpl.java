package cn.babasport.xiu.core.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.babasport.xiu.core.bean.Feature;
import cn.babasport.xiu.core.bean.Type;
import cn.babasport.xiu.core.bean.query.FeatureQuery;
import cn.babasport.xiu.core.bean.query.TypeQuery;
import cn.babasport.xiu.core.dao.FeatureDao;
import cn.babasport.xiu.core.dao.TypeDao;
import cn.babasport.xiu.core.service.FeatureService;
import cn.babasport.xiu.core.service.TypeService;

@Service("featureService")
@Transactional
public class FeatureServiceImpl implements FeatureService {
    @Autowired
    private FeatureDao featureDao;
	
    @Transactional(readOnly = true)
	public List<Feature> getFeatureList(FeatureQuery query) {
		return featureDao.getFeatureListNoPage(query);
	}
    
    
	
	
}