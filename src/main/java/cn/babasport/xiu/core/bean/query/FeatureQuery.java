package cn.babasport.xiu.core.bean.query;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品的查询类
 * 
 * @author xieqixiu
 *
 */
public class FeatureQuery extends BasicQuery {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String name;
	
	private boolean nameLike;

	private String value;
	
	private boolean valueLike;

	private Integer sort;

	private Integer isDel;

	public Integer getId() {
		return id;
	}

	public FeatureQuery setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public FeatureQuery setName(String name) {
		this.name = name;
		return this;
	}

	public boolean isNameLike() {
		return nameLike;
	}

	public FeatureQuery setNameLike(boolean nameLike) {
		this.nameLike = nameLike;
		return this;
	}

	public String getValue() {
		return value;
	}

	public FeatureQuery setValue(String value) {
		this.value = value;
		return this;
	}

	public boolean isValueLike() {
		return valueLike;
	}

	public FeatureQuery setValueLike(boolean valueLike) {
		this.valueLike = valueLike;
		return this;
	}

	public Integer getSort() {
		return sort;
	}

	public FeatureQuery setSort(Integer sort) {
		this.sort = sort;
		return this;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public FeatureQuery setIsDel(Integer isDel) {
		this.isDel = isDel;
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
		public FeatureQuery orderbyId(boolean isAsc) {
			orderFields.add(new OrderField("id", isAsc ? "ASC" : "DESC"));
			return this;
		}
		/**
		 * 设置排序按属性：name
		 * 
		 * @param isAsc
		 * 是否升序，否则为降序
		 */
		public FeatureQuery orderbyName(boolean isAsc) {
			orderFields.add(new OrderField("name", isAsc ? "ASC" : "DESC"));
			return this;
		}
		/**
		 * 设置排序按属性：value
		 * 
		 * @param isAsc
		 * 是否升序，否则为降序
		 */
		public FeatureQuery orderbyValue(boolean isAsc) {
			orderFields.add(new OrderField("value", isAsc ? "ASC" : "DESC"));
			return this;
		}
		/**
		 * 设置排序按属性：sort
		 * 
		 * @param isAsc
		 * 是否升序，否则为降序
		 */
		public FeatureQuery orderbySort(boolean isAsc) {
			orderFields.add(new OrderField("sort", isAsc ? "ASC" : "DESC"));
			return this;
		}
		/**
		 * 设置排序按属性：is_del
		 * 
		 * @param isAsc
		 * 是否升序，否则为降序
		 */
		public FeatureQuery orderbyIsDel(boolean isAsc) {
			orderFields.add(new OrderField("is_del", isAsc ? "ASC" : "DESC"));
			return this;
		}

}
