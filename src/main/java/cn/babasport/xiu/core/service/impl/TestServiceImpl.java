package cn.babasport.xiu.core.service.impl;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.babasport.xiu.core.bean.TestDb;
import cn.babasport.xiu.core.dao.TestDao;
import cn.babasport.xiu.core.service.TestService;

@Service("testService")
public class TestServiceImpl implements TestService {
	
	@Autowired
	private TestDao testDao;
	
	public void saveTest(TestDb test) {
         testDao.addTest(test);		
	}

}
