package cn.babasport.xiu.core.staticpage.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import cn.babasport.xiu.core.service.impl.BaseServiceImpl;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreemarkTemplateServiceImpl extends BaseServiceImpl implements FreemarkTemplateService{
	
	private Configuration conf;
	
	public void setFreeMarkerConfigurer(FreeMarkerConfigurer freeMarkerConfigurer) {
		this.conf = freeMarkerConfigurer.getConfiguration();
	}


	/**
	 * 使用freemark模板语言为商品详情页生成静态模板语言
	 * @param rootMap  模板语言传入的数据
	 * @param productId 商品id
	 */
	public void createStaticPage(Map<String,Object> rootMap,Integer id){
		

        Writer out = null;
        try {
            Template template = conf.getTemplate("productDetail.html");

            //该路径为tomcat服务器的路径 而非项目的工作路径
            String path = getPath("/html/product/"+id+".html");
            System.out.println("生成的模板路径："+path);
            File f = new File(path);
            File parentFile = f.getParentFile();
            if (!parentFile.exists()){
                parentFile.mkdirs();
            }

            out = new OutputStreamWriter(new FileOutputStream(f), "UTF-8");
            template.process(rootMap,out);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out!=null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
	
	}

}
