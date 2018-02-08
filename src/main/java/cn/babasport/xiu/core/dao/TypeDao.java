package cn.babasport.xiu.core.dao;

import java.util.List;


import cn.babasport.xiu.core.bean.Type;
import cn.babasport.xiu.core.bean.query.TypeQuery;

/**
 * 类型查询的dao接口
 * @author xieqixiu
 *
 */
public interface TypeDao {
	/**
	 * 根据单个主键查询
	 * @param id 主键
	 * @return 类型实体对象
	 */
	Type getTypeBykey(Integer id);
	/**
	 * 根据多个主键查询
	 * @param ids 主键集合
	 * @return 类型列表集合
	 */
	List<Type> getTypeBykeys(List<Integer> ids);
	
	/**
	 * 查询所有不分页 
	 * @param query 查询条件对象
	 * @return 类型列表集合
	 */
	List<Type> getTypeListNoPage(TypeQuery query);
	
	List<Type> getTypeList(TypeQuery query);
	/**
	 * 查询所有分页 
	 * @param query 查询条件对象
	 * @return 经过分页类型列表集合
	 */
	List<Type> getTypeListWithPage(TypeQuery query);
	
	/**
	 * 查询记录总数 
	 * @param query 查询条件对象
	 * @return 总记录数
	 */
	int getTypeCounts(TypeQuery query);
	
	/**
	 * 添加类型记录
	 * @param Type 类型对象
	 */
	void addType(Type Type);
	
	/**
	 * 更新类型记录
	 * @param Type 类型对象
	 */
	void updateTypeByKey(Type Type);
	
	/**
	 * 删除单个
	 * @param id 主键id
	 */
	void deleteTypeOne(Integer id);
	
	/**
	 * 批量删除
	 * @param ids 主键集合
	 */
	void deleteTypeMutil(List<Integer> ids);
	
}