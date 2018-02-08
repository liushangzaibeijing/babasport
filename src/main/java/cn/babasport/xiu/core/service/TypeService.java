package cn.babasport.xiu.core.service;


import java.util.List;

import com.nibnait.common.page.Pagination;

import cn.babasport.xiu.core.bean.Type;
import cn.babasport.xiu.core.bean.query.ProductQuery;
import cn.babasport.xiu.core.bean.query.TypeQuery;

public interface TypeService {
	
	public List<Type> getTypeList(TypeQuery query);
    /**
     * 根据typeId获取指定的Type对象
     * @param typeId
     */
	public Type getTypeByKey(Integer typeId);
	

}
