package cn.babasport.xiu.common.utils;

import java.io.IOException;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.babasport.xiu.core.bean.IpAddressSina;
import cn.babasport.xiu.core.bean.IpAddressTaobao;

/**
 * 通过调用第三方的接口定位用户所在的地理位置
 * 
 * @author xieqixiu
 *
 */
public class IpAddressUtils {

	private static String sinaurl = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json";
	private static String taobaourl = "http://ip.taobao.com/service/getIpInfo.php";

	/**
	 * 根据新浪接口获取用户的地理位置(默认获取本地ip的地理位置)
	 * @return 包含有用户位置信息的对象
	 */
	public static IpAddressSina getIpInfoBySina() {
		IpAddressSina address = null;
		if (StringUtils.isNotBlank(sinaurl)) {
			String remoteIpInfo = "";
             
			remoteIpInfo = HttpClientUtil.doGet(sinaurl);
			//使用jackson来自动映射
			ObjectMapper mapper = new ObjectMapper();
			try {
				address = mapper.readValue(remoteIpInfo, IpAddressSina.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return address;
	}

	
	/**
	 * 根据新浪接口获取用户的地理位置(获取指定ip的地理位置)
	 * @return 包含有用户位置信息的对象
	 */
	public static IpAddressSina getIpInfoBySina(String ip) {
		IpAddressSina address = null;
		if(StringUtils.isBlank(ip)){
			return null;
		}
		sinaurl +="&ip="+ip;
		if (StringUtils.isNotBlank(sinaurl)) {
			String remoteIpInfo = "";
             
			remoteIpInfo = HttpClientUtil.doGet(sinaurl);
			//使用jackson来自动映射
			ObjectMapper mapper = new ObjectMapper();
			try {
				address = mapper.readValue(remoteIpInfo, IpAddressSina.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return address;
	}
	
	
	/**
	 * 根据淘宝接口获取用户的地理位置(获取指定ip的地理位置)
	 * @return 包含有用户位置信息的对象
	 */
	public static IpAddressTaobao getIpInfoByTaobao(String ip) {
		IpAddressTaobao address = null;
		if(StringUtils.isBlank(ip)){
			throw new RuntimeException("ip 参数不能为null");
		}
		taobaourl +="?ip="+ip;
		System.out.println(taobaourl);
		if (StringUtils.isNotBlank(taobaourl)) {
			String remoteIpInfo = "";
             
			remoteIpInfo = HttpClientUtil.doGet(taobaourl);
			//使用jackson来自动映射
			ObjectMapper mapper = new ObjectMapper();
			try {
				address = mapper.readValue(remoteIpInfo, IpAddressTaobao.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return address;
	}
	
}
