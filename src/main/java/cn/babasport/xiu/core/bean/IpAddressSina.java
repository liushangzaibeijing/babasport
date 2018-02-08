package cn.babasport.xiu.core.bean;

/**
 * 用户的ip所在的地理位置在前台用户定位时显示
 * 新浪
 * @author xieqixiu
 *
 */
public class IpAddressSina {
	private int ret;
	private int start;
	private int end;
	private String country;
	private String province;
	private String city;
	private String district;
	private String isp;
	private String type;
	private String desc;

	public void setRet(int ret) {
		this.ret = ret;
	}

	public int getRet() {
		return ret;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getStart() {
		return start;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getEnd() {
		return end;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountry() {
		return country;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getProvince() {
		return province;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCity() {
		return city;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getDistrict() {
		return district;
	}

	public void setIsp(String isp) {
		this.isp = isp;
	}

	public String getIsp() {
		return isp;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

}