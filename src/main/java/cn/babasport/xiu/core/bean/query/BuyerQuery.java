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
public class BuyerQuery extends BasicQuery {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    /**
     * 用户名
     */
	private String username;
	/**
	 * 用户名模糊查询
	 */
	private boolean usernameLike;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 密码模糊查询
	 */
	private boolean passwordLike;
	/**
	 * 性别
	 */
	private String gender;
	/**
	 * 性别模糊查询
	 */
	private boolean genderLike;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 *邮箱模糊查询
	 */
	private boolean emailLike;
	/**
	 * 真实姓名
	 */
	private String realName;
	/**
	 * 真实姓名模糊查询
	 */
	private boolean realNameLike;
	
	/**
	 * 注册时间
	 */
	private Date registerTime;
	/**
	 * 所在省份编码
	 */
	private String province;
	/**
	 * 省份模糊查询
	 */
	private boolean provinceLike;
	/**
	 * 所在城市编码
	 */
	private String city;
	/**
	 * 城市模糊查询
	 */
	private boolean cityLike;
	/**
	 * 所在区域编码
	 */
	private String town;
	/**
	 * 区域模糊查询
	 */
	private boolean townLike;
	/**
	 * 用户地址
	 */
	private String addr;
	/**
	 * 地址模糊查询
	 */
	private boolean addrLike;
	/**
	/**
	 * 用户账号是否注销
	 */
	private Integer isDel;

	public String getUsername() {
		return username;
	}
	public BuyerQuery setUsername(String username) {
		this.username = username;
		return this;
	}
	public boolean isUsernameLike() {
		return usernameLike;
	}
	public BuyerQuery setUsernameLike(boolean usernameLike) {
		this.usernameLike = usernameLike;
		return this;
	}
	public String getPassword() {
		return password;
	}
	public BuyerQuery setPassword(String password) {
		this.password = password;
		return this;
	}
	public boolean isPasswordLike() {
		return passwordLike;
	}
	public BuyerQuery setPasswordLike(boolean passwordLike) {
		this.passwordLike = passwordLike;
		return this;
	}
	public String getGender() {
		return gender;
	}
	public BuyerQuery setGender(String gender) {
		this.gender = gender;
		return this;
	}
	public boolean isGenderLike() {
		return genderLike;
	}
	public BuyerQuery setGenderLike(boolean genderLike) {
		this.genderLike = genderLike;
		return this;
	}
	public String getEmail() {
		return email;
	}
	public BuyerQuery setEmail(String email) {
		this.email = email;
		return this;
	}
	public boolean isEmailLike() {
		return emailLike;
	}
	public BuyerQuery setEmailLike(boolean emailLike) {
		this.emailLike = emailLike;
		return this;
	}
	public String getRealName() {
		return realName;
	}
	public BuyerQuery setRealName(String realName) {
		this.realName = realName;
		return this;
	}
	public boolean isRealNameLike() {
		return realNameLike;
	}
	public BuyerQuery setRealNameLike(boolean realNameLike) {
		this.realNameLike = realNameLike;
		return this;
	}
	public Date getRegisterTime() {
		return registerTime;
		
	}
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	public String getProvince() {
		return province;
	}
	public BuyerQuery setProvince(String province) {
		this.province = province;
		return this;
	}
	public boolean isProvinceLike() {
		return provinceLike;
	}
	public BuyerQuery setProvinceLike(boolean provinceLike) {
		this.provinceLike = provinceLike;
		return this;
	}
	public String getCity() {
		return city;
	}
	public BuyerQuery setCity(String city) {
		this.city = city;
		return this;
	}
	public boolean isCityLike() {
		return cityLike;
	}
	public BuyerQuery setCityLike(boolean cityLike) {
		this.cityLike = cityLike;
		return this;
	}
	public String getTown() {
		return town;
	}
	public BuyerQuery setTown(String town) {
		this.town = town;
		return this;
	}
	public boolean isTownLike() {
		return townLike;
	}
	public BuyerQuery setTownLike(boolean townLike) {
		this.townLike = townLike;
		return this;
	}
	public String getAddr() {
		return addr;
	}
	public BuyerQuery setAddr(String addr) {
		this.addr = addr;
		return this;
	}
	public boolean isAddrLike() {
		return addrLike;
	}
	public BuyerQuery setAddrLike(boolean addrLike) {
		this.addrLike = addrLike;
		return this;
	}
	public Integer getIsDel() {
		return isDel;
	}
	public BuyerQuery setIsDel(Integer isDel) {
		this.isDel = isDel;
		return this;
	}

	//排序字段
	private List<OrderField> orderFields = new ArrayList<OrderField>();
	
	//添加排序字段
	public List<OrderField> getOrderFields() {
		return orderFields;
	}
	public BuyerQuery setOrderFields(List<OrderField> orderFields) {
		this.orderFields = orderFields;
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
	

	//按照属性设置排序字段
	/**
	 * 设置排序按属性：username
	 * 
	 * @param isAsc
	 * 是为升序，否则为降序
	 */
	public BuyerQuery orderbyUserName(boolean isAsc) {
		orderFields.add(new OrderField("username", isAsc ? "ASC" : "DESC"));
		return this;
	}
	
	/**
	 * 设置排序按属性：password
	 * 
	 * @param isAsc
	 * 是为升序，否则为降序
	 */
	public BuyerQuery orderbyPassword(boolean isAsc) {
		orderFields.add(new OrderField("password", isAsc ? "ASC" : "DESC"));
		return this;
	}
	
	/**
	 * 设置排序按属性：password
	 * 
	 * @param isAsc
	 * 是为升序，否则为降序
	 */
	public BuyerQuery orderbyGender(boolean isAsc) {
		orderFields.add(new OrderField("gender", isAsc ? "ASC" : "DESC"));
		return this;
	}
	
	/**
	 * 设置排序按属性：email
	 * 
	 * @param isAsc
	 * 是为升序，否则为降序
	 */
	public BuyerQuery orderbyEmail(boolean isAsc) {
		orderFields.add(new OrderField("email", isAsc ? "ASC" : "DESC"));
		return this;
	}
	
	/**
	 * 设置排序按属性：realName
	 * 
	 * @param isAsc
	 * 是为升序，否则为降序
	 */
	public BuyerQuery orderbyRealName(boolean isAsc) {
		orderFields.add(new OrderField("real_name", isAsc ? "ASC" : "DESC"));
		return this;
	}
	
	/**
	 * 设置排序按属性：registerTime
	 * 
	 * @param isAsc
	 * 是为升序，否则为降序
	 */
	public BuyerQuery orderbyRegisterTime(boolean isAsc) {
		orderFields.add(new OrderField("register_time", isAsc ? "ASC" : "DESC"));
		return this;
	}
	
	/**
	 * 设置排序按属性：province
	 * 
	 * @param isAsc
	 * 是为升序，否则为降序
	 */
	public BuyerQuery orderbyProvince(boolean isAsc) {
		orderFields.add(new OrderField("province", isAsc ? "ASC" : "DESC"));
		return this;
	}
	
	/**
	 * 设置排序按属性：city
	 * 
	 * @param isAsc
	 * 是为升序，否则为降序
	 */
	public BuyerQuery orderbyCity(boolean isAsc) {
		orderFields.add(new OrderField("city", isAsc ? "ASC" : "DESC"));
		return this;
	}
	
	/**
	 * 设置排序按属性：town
	 * 
	 * @param isAsc
	 * 是为升序，否则为降序
	 */
	public BuyerQuery orderbyTown(boolean isAsc) {
		orderFields.add(new OrderField("town", isAsc ? "ASC" : "DESC"));
		return this;
	}
	
	/**
	 * 设置排序按属性：town
	 * 
	 * @param isAsc
	 * 是为升序，否则为降序
	 */
	public BuyerQuery orderbyAddr(boolean isAsc) {
		orderFields.add(new OrderField("addr", isAsc ? "ASC" : "DESC"));
		return this;
	}
	
	/**
	 * 设置排序按属性：isDel
	 * 
	 * @param isAsc
	 * 是为升序，否则为降序
	 */
	public BuyerQuery orderbyIsDel(boolean isAsc) {
		orderFields.add(new OrderField("is_del", isAsc ? "ASC" : "DESC"));
		return this;
	}
	
}
