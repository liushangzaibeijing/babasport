package cn.babasport.xiu.core.bean;

/**
 * 淘宝接口返回的对应的地址信息
 * @author xieqixiu
 *
 */
public class IpAddressTaobao {
	    private int code;
	    private IpAddressDetail data;
	    public void setCode(int code) {
	         this.code = code;
	     }
	     public int getCode() {
	         return code;
	     }
		public IpAddressDetail getData() {
			return data;
		}
		public void setData(IpAddressDetail data) {
			this.data = data;
		}
}
