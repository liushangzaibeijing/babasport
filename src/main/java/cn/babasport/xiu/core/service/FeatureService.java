package cn.babasport.xiu.core.service;

import java.util.List;

import cn.babasport.xiu.core.bean.Feature;
import cn.babasport.xiu.core.bean.query.FeatureQuery;

public interface FeatureService {
	
	public List<Feature> getFeatureList(FeatureQuery query);

}
