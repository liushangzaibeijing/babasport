package cn.babasport.xiu.core.bean.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 商品的查询类
 * 
 * @author xieqixiu
 *
 */
public class ProductQuery extends BasicQuery {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//商品id
	private Integer id;

	//商品编号
	private String no;
	
	//商品编号模糊查询
	private boolean noLike;
	
	//商品名称
	private String name;
	
	//商品名称模糊查询
	private boolean nameLike;

	//商品重量
	private Double weight;

	//是否为新品
	private Integer isNew;

	//是否为热卖商品
	private Integer isHot;

	//是否推荐
	private Integer isCommend;

	//商品添加时间
	private Date createTime;

	//商品创建人id
	private String createUserId;
	
	//商品创建人模糊查询
	private boolean createUserIdLike;

	//审核时间
	private Date checkTime;

	//审核人的id
	private String checkUserId;
	
	//审核人模糊查询
    private boolean checkUserIdLike;
    
    private String description;
    
    private boolean descriptionLike;
    
    private String packageList;
    
    private boolean packageListLike;

	//是否下架
	private Integer isShow;

	//是否删除
	private Integer isDel;

	//商品类型
	private Integer typeId;

	//品牌id
	private Integer brandId;

	//检索的关键字
	private String keywords;
	
	//关键字模糊查询
	private boolean keywordsLike;

	private Integer sales;

	//商品材质 一个商品可能由多种材料组成，多个材质以逗号分隔
	private String feature;
	
	//商品材质模糊查询
	private boolean featureLike;

	//商品颜色  一个商品可能由多种颜色组成，多个颜色以逗号分隔
	private String color;
	
	//颜色的模糊查询
	private boolean colorLike;

	//一个商品可能由多个尺寸，多个尺寸以逗号分隔
	private String size;  //商品尺寸 S,M,L
	

	public Integer getId() {
		return id;
	}

	public ProductQuery setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getNo() {
		return no;
	}

	public ProductQuery setNo(String no) {
		this.no = no;
		return this;
	}

	public boolean isNoLike() {
		return noLike;
	}

	public ProductQuery setNoLike(boolean noLike) {
		this.noLike = noLike;
		return this;
	}

	public String getName() {
		return name;
	}

	public ProductQuery setName(String name) {
		this.name = name;
		return this;
	}

	public boolean isNameLike() {
		return nameLike;
	}

	public ProductQuery setNameLike(boolean nameLike) {
		this.nameLike = nameLike;
		return this;
	}

	public Double getWeight() {
		return weight;
	}

	public ProductQuery setWeight(Double weight) {
		this.weight = weight;
		return this;
	}

	public Integer getIsNew() {
		return isNew;
	}

	public ProductQuery setIsNew(Integer isNew) {
		this.isNew = isNew;
		return this;
	}

	public Integer getIsHot() {
		return isHot;
	}

	public ProductQuery setIsHot(Integer isHot) {
		this.isHot = isHot;
		return this;
	}

	public Integer getIsCommend() {
		return isCommend;
	}

	public ProductQuery setIsCommend(Integer isCommend) {
		this.isCommend = isCommend;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public ProductQuery setDescription(String description) {
		this.description = description;
		return this;
	}

	public boolean isDescriptionLike() {
		return descriptionLike;
	}
	

	public ProductQuery setDescriptionLike(boolean descriptionLike) {
		this.descriptionLike = descriptionLike;
		return this;
	}

	
	public String getPackageList() {
		return packageList;
	}

	public ProductQuery setPackageList(String packageList) {
		this.packageList = packageList;
		return this;
	}

	public boolean isPackageListLike() {
		return packageListLike;
	}

	public ProductQuery setPackageListLike(boolean packageListLike) {
		this.packageListLike = packageListLike;
		return this;
	}

	public List<OrderField> getOrderFields() {
		return orderFields;
	}

	public ProductQuery setOrderFields(List<OrderField> orderFields) {
		this.orderFields = orderFields;
		return this;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public ProductQuery setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public ProductQuery setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
		return this;
	}

	public boolean isCreateUserIdLike() {
		return createUserIdLike;
	}

	public ProductQuery setCreateUserIdLike(boolean createUserIdLike) {
		this.createUserIdLike = createUserIdLike;
		return this;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public ProductQuery setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
		return this;
	}

	public String getCheckUserId() {
		return checkUserId;
	}

	public ProductQuery setCheckUserId(String checkUserId) {
		this.checkUserId = checkUserId;
		return this;
	}

	public boolean isCheckUserIdLike() {
		return checkUserIdLike;
	}

	public ProductQuery setCheckUserIdLike(boolean checkUserIdLike) {
		this.checkUserIdLike = checkUserIdLike;
		return this;
	}

	public Integer getIsShow() {
		return isShow;
	}

	public ProductQuery setIsShow(Integer isShow) {
		this.isShow = isShow;
		return this;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public ProductQuery setIsDel(Integer isDel) {
		this.isDel = isDel;
		return this;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public ProductQuery setTypeId(Integer typeId) {
		this.typeId = typeId;
		return this;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public ProductQuery setBrandId(Integer brandId) {
		this.brandId = brandId;
		return this;
	}

	public String getKeywords() {
		return keywords;
	}

	public ProductQuery setKeywords(String keywords) {
		this.keywords = keywords;
		return this;
	}

	public boolean isKeywordsLike() {
		return keywordsLike;
	}

	public ProductQuery setKeywordsLike(boolean keywordsLike) {
		this.keywordsLike = keywordsLike;
		return this;
	}

	public Integer getSales() {
		return sales;
	}

	public ProductQuery setSales(Integer sales) {
		this.sales = sales;
		return this;
	}

	public String getFeature() {
		return feature;
	}

	public ProductQuery setFeature(String feature) {
		this.feature = feature;
		return this;
	}

	public boolean isFeatureLike() {
		return featureLike;
	}

	public ProductQuery setFeatureLike(boolean featureLike) {
		this.featureLike = featureLike;
		return this;
	}

	public String getColor() {
		return color;
	}

	public ProductQuery setColor(String color) {
		this.color = color;
		return this;
	}

	public boolean isColorLike() {
		return colorLike;
	}

	public ProductQuery setColorLike(boolean colorLike) {
		this.colorLike = colorLike;
		return this;
	}

	public String getSize() {
		return size;
	}

	public ProductQuery setSize(String size) {
		this.size = size;
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

	//按照属性设置排序字段
	/**
	 * 设置排序按属性：id
	 * 
	 * @param isAsc
	 * 是为升序，否则为降序
	 */
	public ProductQuery orderbyId(boolean isAsc) {
		orderFields.add(new OrderField("id", isAsc ? "ASC" : "DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：no
	 * 
	 * @param isAsc
	 * 是为升序，否则为降序
	 */
	public ProductQuery orderbyNo(boolean isAsc) {
		orderFields.add(new OrderField("no", isAsc ? "ASC" : "DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：name
	 * 
	 * @param isAsc
	 * 是为升序，否则为降序
	 */
	public ProductQuery orderbyName(boolean isAsc) {
		orderFields.add(new OrderField("name", isAsc ? "ASC" : "DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：weight
	 * 
	 * @param isAsc
	 * 是为升序，否则为降序
	 */
	public ProductQuery orderbyWeight(boolean isAsc) {
		orderFields.add(new OrderField("weight", isAsc ? "ASC" : "DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：is_new
	 * 
	 * @param isAsc
	 * 是为升序，否则为降序
	 */
	public ProductQuery orderbyIsNew(boolean isAsc) {
		orderFields.add(new OrderField("is_new", isAsc ? "ASC" : "DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：is_hot
	 * 
	 * @param isAsc
	 * 是为升序，否则为降序
	 */
	public ProductQuery orderbyIsHot(boolean isAsc) {
		orderFields.add(new OrderField("is_hot", isAsc ? "ASC" : "DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：is_commend
	 * 
	 * @param isAsc
	 * 是为升序，否则为降序
	 */
	public ProductQuery orderbyIsCommend(boolean isAsc) {
		orderFields.add(new OrderField("is_commend", isAsc ? "ASC" : "DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：create_time
	 * 
	 * @param isAsc
	 * 是为升序，否则为降序
	 */
	public ProductQuery orderbyCreateTime(boolean isAsc) {
		orderFields.add(new OrderField("create_time", isAsc ? "ASC" : "DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：create_user_id
	 * 
	 * @param isAsc
	 * 是为升序，否则为降序
	 */
	public ProductQuery orderbyCreateUserId(boolean isAsc) {
		orderFields.add(new OrderField("create_user_id", isAsc ? "ASC" : "DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：check_time
	 * 
	 * @param isAsc
	 * 是为升序，否则为降序
	 */
	public ProductQuery orderbyCheckTime(boolean isAsc) {
		orderFields.add(new OrderField("check_time", isAsc ? "ASC" : "DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：check_user_id
	 * 
	 * @param isAsc
	 * 是为升序，否则为降序
	 */
	public ProductQuery orderbyCheckUserId(boolean isAsc) {
		orderFields.add(new OrderField("check_user_id", isAsc ? "ASC" : "DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：is_show
	 * 
	 * @param isAsc
	 * 是为升序，否则为降序
	 */
	public ProductQuery orderbyIsShow(boolean isAsc) {
		orderFields.add(new OrderField("is_show", isAsc ? "ASC" : "DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：is_del
	 * 
	 * @param isAsc
	 * 是为升序，否则为降序
	 */
	public ProductQuery orderbyIsDel(boolean isAsc) {
		orderFields.add(new OrderField("is_del", isAsc ? "ASC" : "DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：type_id
	 * 
	 * @param isAsc
	 * 是为升序，否则为降序
	 */
	public ProductQuery orderbyTypeId(boolean isAsc) {
		orderFields.add(new OrderField("type_id", isAsc ? "ASC" : "DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：brand_id
	 * 
	 * @param isAsc
	 * 是为升序，否则为降序
	 */
	public ProductQuery orderbyBrandId(boolean isAsc) {
		orderFields.add(new OrderField("brand_id", isAsc ? "ASC" : "DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：keywords
	 * 
	 * @param isAsc
	 * 是为升序，否则为降序
	 */
	public ProductQuery orderbyKeywords(boolean isAsc) {
		orderFields.add(new OrderField("keywords", isAsc ? "ASC" : "DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：sales
	 * 
	 * @param isAsc
	 * 是为升序，否则为降序
	 */
	public ProductQuery orderbySales(boolean isAsc) {
		orderFields.add(new OrderField("sales", isAsc ? "ASC" : "DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：description
	 * 
	 * @param isAsc
	 * 是为升序，否则为降序
	 */
	public ProductQuery orderbyDescription(boolean isAsc) {
		orderFields.add(new OrderField("description", isAsc ? "ASC" : "DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：package_list
	 * 
	 * @param isAsc
	 * 是为升序，否则为降序
	 */
	public ProductQuery orderbyPackageList(boolean isAsc) {
		orderFields.add(new OrderField("package_list", isAsc ? "ASC" : "DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：feature
	 * 
	 * @param isAsc
	 * 是为升序，否则为降序
	 */
	public ProductQuery orderbyFeature(boolean isAsc) {
		orderFields.add(new OrderField("feature", isAsc ? "ASC" : "DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：color
	 * 
	 * @param isAsc
	 * 是为升序，否则为降序
	 */
	public ProductQuery orderbyColor(boolean isAsc) {
		orderFields.add(new OrderField("color", isAsc ? "ASC" : "DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：size
	 * 
	 * @param isAsc
	 * 是为升序，否则为降序
	 */
	public ProductQuery orderbySize(boolean isAsc) {
		orderFields.add(new OrderField("size", isAsc ? "ASC" : "DESC"));
		return this;
	}
	
	
}
