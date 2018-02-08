package cn.babasport.xiu.core.dao;

import java.util.List;


import cn.babasport.xiu.core.bean.Feature;
import cn.babasport.xiu.core.bean.query.FeatureQuery;

/**
 * 材质查询的dao接口
 * @author xieqixiu
 *
 */
public interface FeatureDao {
	/**
	 * 根据单个主键查询
	 * @param id 主键pin
	 * @return 材质实体对象
	 */
	Feature getFeatureBykey(Integer id);
	/**
	 * 根据多个主键查询
	 * @param ids 主键集合
	 * @return 材质列表集合
	 */
	List<Feature> getFeatureBykeys(List<Integer> ids);
	
	/**
	 * 查询所有不分页 
	 * @param query 查询条件对象
	 * @return 材质列表集合
	 */
	List<Feature> getFeatureListNoPage(FeatureQuery query);
	
	/**
	 * 查询所有分页 
	 * @param query 查询条件对象
	 * @return 经过分页材质列表集合
	 */
	List<Feature> getFeatureListWithPage(FeatureQuery query);
	
	/**
	 * 查询记录总数 
	 * @param query 查询条件对象
	 * @return 总记录数
	 */
	int getFeatureCounts(FeatureQuery query);
	
	/**
	 * 添加材质记录
	 * @param Feature 材质对象
	 */
	void addFeature(Feature Feature);
	
	/**
	 * 更新材质记录
	 * @param Feature 材质对象
	 */
	void updateFeatureByKey(Feature Feature);
	
	/**
	 * 删除单个
	 * @param id 主键id
	 */
	void deleteFeatureOne(Integer id);
	
	/**
	 * 批量删除
	 * @param ids 主键集合
	 */
	void deleteFeatureMutil(List<Integer> ids);
	
}