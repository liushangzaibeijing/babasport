package cn.babasport.xiu.core.bean.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 主要用于前台的显示
 * @author lixu
 * @Date [2014-3-28 下午05:58:00]
 */
public class SkuQuery extends BasicQuery {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * ==============================批量查询、更新、删除时的Where条件设置======================
	 * ============
	 **/
	private Integer id; //sku商品id
	public Integer getId() {
		return id;
	}
	public SkuQuery setId(Integer id) {
		this.id = id;
		return this;
	}
	private Integer productId;  //商品id
	public Integer getProductId() {
		return productId;
	}
	public SkuQuery setProductId(Integer productId) {
		this.productId = productId;
		return this;
	}
	private Integer colorId; //颜色
	public Integer getColorId() {
		return colorId;
	}
	public SkuQuery setColorId(Integer colorId) {
		this.colorId = colorId;
		return this;
	}
	private String size; //尺寸
	public String getSize() {
		return size;
	}
	public SkuQuery setSize(String size) {
		this.size = size;
		return this;
	}
	private boolean sizeLike;
	public SkuQuery setSizeLike(boolean isLike) {
		this.sizeLike = isLike;
		return this;
	}
	private Double deliveFee; //运费 默认为10元
	public Double getDeliveFee() {
		return deliveFee;
	}
	public SkuQuery setDeliveFee(Double deliveFee) {
		this.deliveFee = deliveFee;
		return this;
	}
	private Double skuPrice; //商品的出售价格
	public Double getSkuPrice() {
		return skuPrice;
	}
	public SkuQuery setSkuPrice(Double skuPrice) {
		this.skuPrice = skuPrice;
		return this;
	}
	private Integer stockInventory;  //库存
	public Integer getStockInventory() {
		return stockInventory;
	}
	public SkuQuery setStockInventory(Integer stockInventory) {
		this.stockInventory = stockInventory;
		return this;
	}
	private Integer skuUpperLimit;  //购买限制
	public Integer getSkuUpperLimit() {
		return skuUpperLimit;
	}
	public SkuQuery setSkuUpperLimit(Integer skuUpperLimit) {
		this.skuUpperLimit = skuUpperLimit;
		return this;
	}
	private String location; //仓库位置 显示和计算位置
	public String getLocation() {
		return location;
	}
	public SkuQuery setLocation(String location) {
		this.location = location;
		return this;
	}
	private boolean locationLike;
	public SkuQuery setLocationLike(boolean isLike) {
		this.locationLike = isLike;
		return this;
	}
	private String skuImg;   //商品图片url
	public String getSkuImg() {
		return skuImg;
	}
	public SkuQuery setSkuImg(String skuImg) {
		this.skuImg = skuImg;
		return this;
	}
	private boolean skuImgLike;
	public SkuQuery setSkuImgLike(boolean isLike) {
		this.skuImgLike = isLike;
		return this;
	}
	private Integer skuSort; //前台排序
	public Integer getSkuSort() {
		return skuSort;
	}
	public SkuQuery setSkuSort(Integer skuSort) {
		this.skuSort = skuSort;
		return this;
	}
	private String skuName;  //sku名称
	public String getSkuName() {
		return skuName;
	}
	public SkuQuery setSkuName(String skuName) {
		this.skuName = skuName;
		return this;
	}
	private boolean skuNameLike;
	public SkuQuery setSkuNameLike(boolean isLike) {
		this.skuNameLike = isLike;
		return this;
	}
	private Double marketPrice;  //原价
	public Double getMarketPrice() {
		return marketPrice;
	}
	public SkuQuery setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
		return this;
	}
	private Date createTime;  //上架时间
	public Date getCreateTime() {
		return createTime;
	}
	public SkuQuery setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	private Date updateTime;  //更新时间
	public Date getUpdateTime() {
		return updateTime;
	}
	public SkuQuery setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	private String createUserId;  //上架人id
	public String getCreateUserId() {
		return createUserId;
	}
	public SkuQuery setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
		return this;
	}
	private boolean createUserIdLike;
	public SkuQuery setCreateUserIdLike(boolean isLike) {
		this.createUserIdLike = isLike;
		return this;
	}
	private String updateUserId; //更新人的id
	public String getUpdateUserId() {
		return updateUserId;
	}
	public SkuQuery setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
		return this;
	}
	private boolean updateUserIdLike;
	public SkuQuery setUpdateUserIdLike(boolean isLike) {
		this.updateUserIdLike = isLike;
		return this;
	}
	private Integer lastStatus;  //状态  0历史，1最新
	public Integer getLastStatus() {
		return lastStatus;
	}
	public SkuQuery setLastStatus(Integer lastStatus) {
		this.lastStatus = lastStatus;
		return this;
	}
	private Integer skuType;  //类型，赠品还是普通商品
	public Integer getSkuType() {
		return skuType;
	}
	public SkuQuery setSkuType(Integer skuType) {
		this.skuType = skuType;
		return this;
	}
	private Integer sales;  //销量（同一种类商品，不同款式的售价）
	public Integer getSales() {
		return sales;
	}
	public SkuQuery setSales(Integer sales) {
		this.sales = sales;
		return this;
	}
	/**
	 * ==============================批量查询时的Order条件顺序设置==========================
	 * ========
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
		/**
	 * ==============================批量查询时的Order条件顺序设置==========================
	 * ========
	 **/
	/** 排序列表字段 **/
	private List<OrderField> orderFields = new ArrayList<OrderField>();
	/**
	 * 设置排序按属性：id
	 * 
	 * @param isAsc
	 *            是否升序，否则为降序
	 */
	public SkuQuery orderbyId(boolean isAsc) {
		orderFields.add(new OrderField("id", isAsc ? "ASC" : "DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：product_id
	 * 
	 * @param isAsc
	 *            是否升序，否则为降序
	 */
	public SkuQuery orderbyProductId(boolean isAsc) {
		orderFields.add(new OrderField("product_id", isAsc ? "ASC" : "DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：color_id
	 * 
	 * @param isAsc
	 *            是否升序，否则为降序
	 */
	public SkuQuery orderbyColorId(boolean isAsc) {
		orderFields.add(new OrderField("color_id", isAsc ? "ASC" : "DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：size
	 * 
	 * @param isAsc
	 *            是否升序，否则为降序
	 */
	public SkuQuery orderbySize(boolean isAsc) {
		orderFields.add(new OrderField("size", isAsc ? "ASC" : "DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：delive_fee
	 * 
	 * @param isAsc
	 *            是否升序，否则为降序
	 */
	public SkuQuery orderbyDeliveFee(boolean isAsc) {
		orderFields.add(new OrderField("delive_fee", isAsc ? "ASC" : "DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：sku_price
	 * 
	 * @param isAsc
	 *            是否升序，否则为降序
	 */
	public SkuQuery orderbySkuPrice(boolean isAsc) {
		orderFields.add(new OrderField("sku_price", isAsc ? "ASC" : "DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：stock_inventory
	 * 
	 * @param isAsc
	 *            是否升序，否则为降序
	 */
	public SkuQuery orderbyStockInventory(boolean isAsc) {
		orderFields.add(new OrderField("stock_inventory", isAsc ? "ASC" : "DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：sku_upper_limit
	 * 
	 * @param isAsc
	 *            是否升序，否则为降序
	 */
	public SkuQuery orderbySkuUpperLimit(boolean isAsc) {
		orderFields.add(new OrderField("sku_upper_limit", isAsc ? "ASC" : "DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：location
	 * 
	 * @param isAsc
	 *            是否升序，否则为降序
	 */
	public SkuQuery orderbyLocation(boolean isAsc) {
		orderFields.add(new OrderField("location", isAsc ? "ASC" : "DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：sku_img
	 * 
	 * @param isAsc
	 *            是否升序，否则为降序
	 */
	public SkuQuery orderbySkuImg(boolean isAsc) {
		orderFields.add(new OrderField("sku_img", isAsc ? "ASC" : "DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：sku_sort
	 * 
	 * @param isAsc
	 *            是否升序，否则为降序
	 */
	public SkuQuery orderbySkuSort(boolean isAsc) {
		orderFields.add(new OrderField("sku_sort", isAsc ? "ASC" : "DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：sku_name
	 * 
	 * @param isAsc
	 *            是否升序，否则为降序
	 */
	public SkuQuery orderbySkuName(boolean isAsc) {
		orderFields.add(new OrderField("sku_name", isAsc ? "ASC" : "DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：market_price
	 * 
	 * @param isAsc
	 *            是否升序，否则为降序
	 */
	public SkuQuery orderbyMarketPrice(boolean isAsc) {
		orderFields.add(new OrderField("market_price", isAsc ? "ASC" : "DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：create_time
	 * 
	 * @param isAsc
	 *            是否升序，否则为降序
	 */
	public SkuQuery orderbyCreateTime(boolean isAsc) {
		orderFields.add(new OrderField("create_time", isAsc ? "ASC" : "DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：update_time
	 * 
	 * @param isAsc
	 *            是否升序，否则为降序
	 */
	public SkuQuery orderbyUpdateTime(boolean isAsc) {
		orderFields.add(new OrderField("update_time", isAsc ? "ASC" : "DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：create_user_id
	 * 
	 * @param isAsc
	 *            是否升序，否则为降序
	 */
	public SkuQuery orderbyCreateUserId(boolean isAsc) {
		orderFields.add(new OrderField("create_user_id", isAsc ? "ASC" : "DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：update_user_id
	 * 
	 * @param isAsc
	 *            是否升序，否则为降序
	 */
	public SkuQuery orderbyUpdateUserId(boolean isAsc) {
		orderFields.add(new OrderField("update_user_id", isAsc ? "ASC" : "DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：last_status
	 * 
	 * @param isAsc
	 *            是否升序，否则为降序
	 */
	public SkuQuery orderbyLastStatus(boolean isAsc) {
		orderFields.add(new OrderField("last_status", isAsc ? "ASC" : "DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：sku_type
	 * 
	 * @param isAsc
	 *            是否升序，否则为降序
	 */
	public SkuQuery orderbySkuType(boolean isAsc) {
		orderFields.add(new OrderField("sku_type", isAsc ? "ASC" : "DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：sales
	 * 
	 * @param isAsc
	 *            是否升序，否则为降序
	 */
	public SkuQuery orderbySales(boolean isAsc) {
		orderFields.add(new OrderField("sales", isAsc ? "ASC" : "DESC"));
		return this;
	}
}
