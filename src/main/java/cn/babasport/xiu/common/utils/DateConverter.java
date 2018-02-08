package cn.babasport.xiu.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * 定义一个日期转换类，将其配置在xml中作为全局的日期类型的转换器
 * @author xieqixiu
 *
 */

public class DateConverter  implements Converter<String, Date> {

	public Date convert(String source) {
	 try {
            Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(source);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
	}

}
