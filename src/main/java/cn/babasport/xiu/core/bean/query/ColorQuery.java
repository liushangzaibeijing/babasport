package cn.babasport.xiu.core.bean.query;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品的查询类
 * 
 * @author xieqixiu
 *
 */
public class ColorQuery extends BasicQuery {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id; // 颜色的id
	private String name; // 颜色的名字
	private Integer parentId; // 父类的id 色系 即 红色为一个色系，大红，桃红，粉红为具体的颜色
	private String imgUrl; // 颜色对应的图片路径

	public Integer getId() {
		return id;
	}

	public ColorQuery setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public ColorQuery setName(String name) {
		this.name = name;
		return this;
	}

	public Integer getParentId() {
		return parentId;
	}

	public ColorQuery setParentId(Integer parentId) {
		this.parentId = parentId;
		return this;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public ColorQuery setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
		return this;
	}

	/*** 非持久化对象 ***/

	private boolean nameLike;

	public ColorQuery setNameLike(boolean isLike) {
		this.nameLike = isLike;
		return this;
	}

	private boolean imgUrlLike;

	public ColorQuery setImgUrlLike(boolean isLike) {
		this.imgUrlLike = isLike;
		return this;
	}

	/**
	 * 内部排序类，属性一个为排序字段，一个为'asc'或'desc' 设置排序
	 **/
	public class OrderField {
		public OrderField(String fieldName, String order) {
			super();
			this.fieldName = fieldName;
			this.order = order;
		}

		private String fieldName;
		private String order;

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

	/** 排序列表字段 **/
	private List<OrderField> orderFields = new ArrayList<OrderField>();

	/**
	 * 设置排序按属性：id
	 * 
	 * @param isAsc
	 * 是否升序，否则为降序
	 */
	public ColorQuery orderbyId(boolean isAsc) {
		orderFields.add(new OrderField("id", isAsc ? "ASC" : "DESC"));
		return this;
	}

	/**
	 * 设置排序按属性：name
	 * 
	 * @param isAsc
	 * 是否升序，否则为降序
	 */
	public ColorQuery orderbyName(boolean isAsc) {
		orderFields.add(new OrderField("name", isAsc ? "ASC" : "DESC"));
		return this;
	}

	/**
	 * 设置排序按属性：parent_id
	 * 
	 * @param isAsc
	 * 是否升序，否则为降序
	 */
	public ColorQuery orderbyParentId(boolean isAsc) {
		orderFields.add(new OrderField("parent_id", isAsc ? "ASC" : "DESC"));
		return this;
	}

	/**
	 * 设置排序按属性：img_url
	 * 
	 * @param isAsc
	 * 是否升序，否则为降序
	 */
	public ColorQuery orderbyImgUrl(boolean isAsc) {
		orderFields.add(new OrderField("img_url", isAsc ? "ASC" : "DESC"));
		return this;
	}

}
