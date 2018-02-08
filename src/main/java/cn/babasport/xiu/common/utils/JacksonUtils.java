package cn.babasport.xiu.common.utils;

import java.io.IOException;
import java.io.StringWriter;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/*
 * 对象和json之间进行的转换
 * @author xieqixiu
 *
 */
public class JacksonUtils {
	//Serialize and Deserialize,
	//将指定的对象转换为json串
	public static  <T> String serialize(T t){
		ObjectMapper mapper = new ObjectMapper();
		//序列化参数不包含为null的数据
		mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
	    StringWriter str = new StringWriter();
        try {
        	mapper.writeValue(str, t);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str.toString();
		
	}
	
	
	public static <T> T deserialize(String content, Class<T> clazz){
		ObjectMapper mapper = new ObjectMapper();
		  T t  = null;
		try {
			t = mapper.readValue(content, clazz);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return t;
		
	}

}
