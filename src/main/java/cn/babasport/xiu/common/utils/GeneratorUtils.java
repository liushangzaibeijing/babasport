package cn.babasport.xiu.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 通用的生成名字的类
 * @author xieqixiu
 *
 */
public class GeneratorUtils{
	
	/**
	 * 根据日期来生成保存图片的名称
	 * @return
	 */
	public static String generatorImgName(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmssSSS");
		
		String path = dateFormat.format(new Date());
		Random random = new Random();
		for(int i=0;i<3;i++){
			path+=random.nextInt(10);
		}
		return path;
	}

}
