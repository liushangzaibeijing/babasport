package cn.babasport.xiu.test.dao;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import cn.babasport.xiu.core.bean.TestDb;
import cn.babasport.xiu.core.dao.TestDao;
import cn.babasport.xiu.test.commons.SpringJunit;




public class TestDaoTest extends SpringJunit {
	

	@Resource
	private TestDao testDao;
	
	@Test
	public void queryByCondition(){
		String name = "谢奇秀";
		int age = 32;
		
		List<TestDb> list = testDao.queryTestByQuery(name, age);
		
		System.out.println("集合大小："+list.size());
	}
	
	//批量删除
	@Test
	public void deletePatch(){
	    List<Integer> ids = new ArrayList<Integer>();
	    ids.add(8);
	    ids.add(9);
	    ids.add(10);
	    ids.add(11);
	    String name = "张三";
	    int age = 22;
		testDao.deleteTests(ids,name,age);
		
	}

	//遍历数组
	@Test
	public void foreachArrayTest(){
		int[] array = {3,4,5};
		

		List<TestDb> list = testDao.foreachTestDb(array);
		
		for(TestDb test:list){
			System.out.println(test);
		}
		
	}
	
	//遍历集合
	@Test
	public void foreachListTest(){
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(3);
		ids.add(4);
		ids.add(5);
		
		List<TestDb> list = testDao.foreachTestDb(ids);
	    
		for(TestDb test:list){
			System.out.println(test);
		}
	}
	
	
	public static void main(String[] args) {
		String str = "\u4E0A\u4E00\u9875";
		System.out.println(str);
	}
	
}
