package cn.babasport.xiu.core.dao;

import java.util.List;

import cn.babasport.xiu.core.bean.OrderDetail;
import cn.babasport.xiu.core.bean.query.OrderDetailQuery;

public interface OrderDetailDao {

	/**
	 * 添加
	 * @param detail
	 */
	public Integer addDetail(OrderDetail detail);

	/**
	 * 根据主键查找
	 * @param detailQuery
	 */
	public OrderDetail getDetailByKey(Integer id);

	/**
	 * 根据主键批量查找
	 * @param detailQuery
	 */
	public List<OrderDetail> getDetailsByKeys(List<Integer> idList);

	/**
	 * 根据主键删除
	 * @param detailQuery
	 */
	public Integer deleteByKey(Integer id);

	/**
	 * 根据主键批量删除
	 * @param detailQuery
	 */
	public Integer deleteByKeys(List<Integer> idList);

	/**
	 * 根据主键更新
	 * @param detailQuery
	 */
	public Integer updateDetailByKey(OrderDetail detail);

	/**
	 * 分页查询
	 * @param detailQuery
	 */
	public List<OrderDetail> getDetailListWithPage(OrderDetailQuery detailQuery);

	/**
	 * 集合查询
	 * @param detailQuery
	 */
	public List<OrderDetail> getDetailList(OrderDetailQuery detailQuery);
	
	/**
	 * 总条数
	 * @param detailQuery
	 */
	public int getDetailListCount(OrderDetailQuery detailQuery);
}
