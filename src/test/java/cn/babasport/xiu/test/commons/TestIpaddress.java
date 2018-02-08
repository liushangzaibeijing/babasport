package cn.babasport.xiu.test.commons;


import org.junit.Test;


import cn.babasport.xiu.common.utils.IpAddressUtils;
import cn.babasport.xiu.core.bean.IpAddressSina;
import cn.babasport.xiu.core.bean.IpAddressTaobao;

/**
 * 根据ip地址定位地理位置
 * 
 * @author xieqixiu
 *
 */
public class TestIpaddress {

	//新浪接口本机ip
	@Test
	public void testSinal(){
		IpAddressSina address = IpAddressUtils.getIpInfoBySina();
		
		System.out.println(address.getProvince());
	}
	
	
	//新浪接口制定ip
	@Test 
	public void testSinalByIp(){
		//http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=218.192.3.42
		IpAddressSina address = IpAddressUtils.getIpInfoBySina("218.192.3.42");
		
		System.out.println(address.getProvince());
	}
	
	//淘宝接口指定ip(测试不通过)
	@Test 
	public void testTaobapoByIp(){
		//http://ip.taobao.com/service/getIpInfo.php?ip=218.192.3.42
	    //http://ip.taobao.com/service/getIpInfo.php?ip=218.192.3.42
		IpAddressTaobao address = IpAddressUtils.getIpInfoByTaobao("218.192.3.42");
		
		System.out.println(address.getData().getCity());
	}
		

}
