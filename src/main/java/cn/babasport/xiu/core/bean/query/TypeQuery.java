package cn.babasport.xiu.core.bean.query;

import java.util.ArrayList;
import java.util.List;


/**
 * 商品的查询类
 * 
 * @author xieqixiu
 *
 */
public class TypeQuery extends BasicQuery {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
    private String name;
    
    private boolean nameLike;

    private Integer parentId;

    private String note;
    
    private boolean noteLike;

    private Integer isDisplay;

	public Integer getId() {
		return id;
	}

	public TypeQuery setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public TypeQuery setName(String name) {
		this.name = name;
		return this;
	}

	public boolean isNameLike() {
		return nameLike;
	}

	public TypeQuery setNameLike(boolean nameLike) {
		this.nameLike = nameLike;
		return this;
	}

	public Integer getParentId() {
		return parentId;
	}

	public TypeQuery setParentId(Integer parentId) {
		this.parentId = parentId;
		return this;
	}

	public String getNote() {
		return note;
	}

	public TypeQuery setNote(String note) {
		this.note = note;
		return this;
	}

	public boolean isNoteLike() {
		return noteLike;
	}

	public TypeQuery setNoteLike(boolean noteLike) {
		this.noteLike = noteLike;
		return this;
	}

	public Integer getIsDisplay() {
		return isDisplay;
	}

	public TypeQuery setIsDisplay(Integer isDisplay) {
		this.isDisplay = isDisplay;
		return this;
	}
	
	// 排序查询
	public class OrderField{
		private String fieldName;
		private String order;
		public OrderField(String fieldName, String order) {
			super();
			this.fieldName = fieldName;
			this.order = order;
		}
		public String getFieldName() {
			return fieldName;
		}
		public OrderField setFieldName(String fieldName) {
			this.fieldName = fieldName;
			return this;
		}
		public String getOrder() {
			return order;
		}
		public OrderField setOrder(String order) {
			this.order = order;
			return this;
		}
	}
	
	private List<OrderField> orderFields = new ArrayList<OrderField>();
	
	/**
	 * 设置排序按属性：id
	 * 
	 * @param isAsc
	 * 是否升序，否则为降序
	 */
	public TypeQuery orderbyId(boolean isAsc) {
		orderFields.add(new OrderField("id", isAsc ? "ASC" : "DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：name
	 * 
	 * @param isAsc
	 *是否升序，否则为降序
	 */
	public TypeQuery orderbyName(boolean isAsc) {
		orderFields.add(new OrderField("name", isAsc ? "ASC" : "DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：parent_id
	 * 
	 * @param isAsc
	 *是否升序，否则为降序
	 */
	public TypeQuery orderbyParentId(boolean isAsc) {
		orderFields.add(new OrderField("parent_id", isAsc ? "ASC" : "DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：note
	 * 
	 * @param isAsc
	 *是否升序，否则为降序
	 */
	public TypeQuery orderbyNote(boolean isAsc) {
		orderFields.add(new OrderField("note", isAsc ? "ASC" : "DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：is_display
	 * 
	 * @param isAsc
	 *是否升序，否则为降序
	 */
	public TypeQuery orderbyIsDisplay(boolean isAsc) {
		orderFields.add(new OrderField("is_display", isAsc ? "ASC" : "DESC"));
		return this;
	}
    
}
