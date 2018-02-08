package cn.babasport.xiu.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.babasport.xiu.core.bean.TestDb;

public interface TestDao {
   
	public void addTest(TestDb test);
	
	/**
	 * mybatis的@Param(value="值")注解能传递多个参数在xml中直接使用#{值}
	 * 在对应的mapper映射文件中使用
	 *  
	 */
	public List<TestDb> queryTestByQuery(
			@Param(value = "name") String userName,
			@Param("age") Integer age);
	
	/**
	 * mybatis的批量删除时候用 
	 *
     */
	public void deleteTests(@Param(value = "list") List<Integer> ids,
			@Param(value = "name") String userName,@Param("age") Integer age);


	/**
	 * 查询出指定范围内测试数据
	 * @param ids 数组
	 * @return
	 */
    public List<TestDb> foreachTestDb(int[] ids);
    
    /**
	 * 查询出指定范围内测试数据
	 * @param ids lis集合
	 * @return
	 */
    public List<TestDb> foreachTestDb(List<Integer> ids);
}
