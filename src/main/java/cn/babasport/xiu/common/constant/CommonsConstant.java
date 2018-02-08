package cn.babasport.xiu.common.constant;

/**
 * 定义一个日期转换类，将其配置在xml中作为全局的日期类型的转换器
 * @author xieqixiu
 *
 */

public class CommonsConstant {
	//文件上传的服务器根路径
	public final static String UPLOADFILE_BASE_URL = "http://127.0.0.1:8088/babasportImgService/";
    //部署到linux操作系统下的
	//public static String UPLOADFILE_BASE_URL = "http://192.168.47.131:8088/babasportImgService/";
    //session中存储的用户信息的key
	public final static String CURRENT_BUYER ="current_buyer";
	//cookie中存储的购物车信息
	public final static String  BUYERCART = "buyerCart";
	
}
