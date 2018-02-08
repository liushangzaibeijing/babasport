package cn.babasport.xiu.core.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import cn.babasport.xiu.common.constant.CommonsConstant;
import cn.babasport.xiu.common.utils.GeneratorUtils;
import net.fckeditor.response.UploadResponse;


/**
 * 
 * @author xieqixiu
 * 文件上传的Controller
 */
@Controller
@RequestMapping("/upload")
public class UploadFileController {
	/**
	 * 使用jersey上传品牌图片(input file上传)
	 * @param imgs 图片文件
	 * @param response 响应信息
	 */
	@RequestMapping("uploadImgBrand.do")
	public void uploadImgBrand(@RequestParam(required=false) MultipartFile imgs,HttpServletResponse response){
		uploadfile(imgs, response,"uploadImg/brand");
	}
	
	/**
	 * 使用jersey上传商品图片(input file上传)
	 * @param imgs 图片文件
	 * @param response 响应信息
	 */
	@RequestMapping("uploadImgProduct.do")
	public void uploadImgProduct(@RequestParam(required=false) MultipartFile imgs,HttpServletResponse response){
		uploadfile(imgs, response,"uploadImg/product");
	}
	
	/**
	 * 使用jersey上传sku图片(input file上传)
	 * @param imgs 图片文件
	 * @param response 响应信息
	 */
	@RequestMapping("uploadImgSku.do")
	public void uploadImgSku(@RequestParam(required=false) MultipartFile imgs,HttpServletResponse response){
		uploadfile(imgs, response,"uploadImg/sku");
	}

	/**
	 * 使用jersey上传品牌图片(FCK文本编辑器来上传)
	 * @param imgs 图片文件
	 * @param response 响应信息
	 */
	@RequestMapping("uploadFckBrand.do")
    public void uploadFckBrand(HttpServletRequest request,HttpServletResponse response) {
        //该请求为上传请求对象，将其强制转换为
		MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;

		//获取所哦有上传的文件 map类型
        Map<String, MultipartFile> fileMap = req.getFileMap();

        for (Map.Entry<String, MultipartFile> entry : fileMap.entrySet()) {
            uploadfile(entry.getValue(), response, "uploadFck/brand");
        }

    }
	
	/**
	 * 使用jersey上传商品图片(FCK文本编辑器来上传)
	 * @param imgs 图片文件
	 * @param response 响应信息
	 */
	@RequestMapping("uploadFckProduct.do")
    public void uploadFckProduct(HttpServletRequest request,HttpServletResponse response) {
        //该请求为上传请求对象，将其强制转换为
		MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;

		//获取所哦有上传的文件 map类型
        Map<String, MultipartFile> fileMap = req.getFileMap();

        for (Map.Entry<String, MultipartFile> entry : fileMap.entrySet()) {
            uploadfile(entry.getValue(), response, "uploadFck/product");
        }

    }
	
	
	/**
	 * 使用jersey上传sku图片(FCK文本编辑器来上传)
	 * @param imgs 图片文件
	 * @param response 响应信息
	 */
	@RequestMapping("uploadFckSku.do")
    public void uploadFckSku(HttpServletRequest request,HttpServletResponse response) {
        //该请求为上传请求对象，将其强制转换为
		MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;

		//获取所哦有上传的文件 map类型
        Map<String, MultipartFile> fileMap = req.getFileMap();

        for (Map.Entry<String, MultipartFile> entry : fileMap.entrySet()) {
            uploadfile(entry.getValue(), response, "uploadFck/sku");
        }

    }
	
	
	 //使用jersey上传图片到另一台服务器
	private void uploadfile(MultipartFile imgs, HttpServletResponse response,String fileBasePath) {
			ClientConfig config = new DefaultClientConfig();
			config.getProperties().put(ClientConfig.PROPERTY_CONNECT_TIMEOUT, 10*1000);
			
			Client client = Client.create(config);
			
			String imgpreName = imgs.getOriginalFilename();
			String path = fileBasePath+"/"+GeneratorUtils.generatorImgName()+imgpreName.substring(imgpreName.lastIndexOf("."));
			
			String url = CommonsConstant.UPLOADFILE_BASE_URL+path;
			
			//客户端通过url来连接服务器
			WebResource webResource = client.resource(url);
			
		    try {
		    	//向服务器中上传图片
				webResource.put(String.class, imgs.getBytes());
			} catch (UniformInterfaceException e1) {
				e1.printStackTrace();
			} catch (ClientHandlerException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		    
		    if(fileBasePath.startsWith("uploadImg")){
		    	  JSONObject jo = new JSONObject();
		          jo.put("url", url);
		          jo.put("path",path);
		          try {
					response.getWriter().write(jo.toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
		    	
		    }else if(fileBasePath.startsWith("uploadFck")){
		    	 UploadResponse ok = UploadResponse.getOK(url);
				    try {
						response.getWriter().println(ok);;
					} catch (IOException e){
						e.printStackTrace();
					} 	
		    }
		   
		}
		
		
}
