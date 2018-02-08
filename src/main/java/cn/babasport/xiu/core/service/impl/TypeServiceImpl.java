package cn.babasport.xiu.core.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.babasport.xiu.core.bean.Type;
import cn.babasport.xiu.core.bean.query.TypeQuery;
import cn.babasport.xiu.core.dao.TypeDao;
import cn.babasport.xiu.core.service.TypeService;

@Service("typeService")
@Transactional
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeDao typeDao;
	
    @Transactional(readOnly=true)
	public List<Type> getTypeList(TypeQuery query) {
		return typeDao.getTypeListNoPage(query);
    	//return typeDao.getTypeList(query);
	}

	@Override
	public Type getTypeByKey(Integer typeId) {
		return typeDao.getTypeBykey(typeId);
	}
    
	
	
}