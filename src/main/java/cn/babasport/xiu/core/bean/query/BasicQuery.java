package cn.babasport.xiu.core.bean.query;

import java.io.Serializable;

/**
 * 查询的通用对象 ，分页和查询指定的字段
 * @author xieqixiu
 *
 */
public class BasicQuery implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//定义每一页的页数，常量
	private static final int DEFAULT_PAGESIZE = 10;
	
	//每一页的个数
	private int pageSize = DEFAULT_PAGESIZE;
	//页码 即第几页
	private int pageNo;
	//开始页
	private int startRow;
	//查询字段 多个字段用","拼接
	private String fields;
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		//当每一页的显示个数改变时，startRow也会发生改变
		this.startRow = (pageNo-1)*this.pageSize;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
		this.startRow = (pageNo-1)*pageSize;
	}
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public String getFields() {
		return fields;
	}
	public void setFields(String fields) {
		this.fields = fields;
	}
}
