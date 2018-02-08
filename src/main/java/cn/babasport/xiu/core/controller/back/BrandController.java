package cn.babasport.xiu.core.controller.back;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nibnait.common.page.Pagination;

import cn.babasport.xiu.common.constant.CommonsConstant;
import cn.babasport.xiu.core.bean.Brand;
import cn.babasport.xiu.core.bean.QueryBean;
import cn.babasport.xiu.core.service.BrandService;

@Controller
@RequestMapping("/back")
public class BrandController {

	@Autowired
	private BrandService brandService;

	/**
	 * 根据查询条件查询品牌信息
	 * @param name
	 * @param isDisplay
	 * @param pageNo
	 * @param model
	 * @return
	 */
	@RequestMapping("/brandList.do")
	public String brandMain(String name,Integer isDisplay,Integer pageNo,ModelMap model){
		//先判断name，isDisplay是否存在
		Brand brand = new Brand();
		StringBuilder params = new StringBuilder();
		if(name!=null&&StringUtils.isNotBlank(name)){
			brand.setName(name);
            params.append("name=").append(name);		
		}
		if(isDisplay!=null&&(isDisplay.intValue()==1||isDisplay.intValue()==0)){
			brand.setIsDisplay(isDisplay);
            params.append("&").append("isDisplay=").append(isDisplay);		
		}
		else{
            isDisplay = 1;
			brand.setIsDisplay(isDisplay); //默认按照降序进行排列
		}
		
		Pagination pagination = brandService.getBrandsPage(brand, pageNo);
		//设置分页的显示页面
		pagination.pageView("/babasport/back/brandList.do", params.toString());
   	    model.addAttribute("pagination", pagination);
		model.addAttribute("name",name);
		model.addAttribute("isDisplay",isDisplay);
		model.addAttribute("baseUrl",CommonsConstant.UPLOADFILE_BASE_URL);
		
		return "brand/list";
	}
	
	/**
	 * 添加品牌操作
	 * @param brand
	 * @return
	 */
	@RequestMapping("addBrand.do")
	public String addBrand(Brand brand){

		brandService.addBrand(brand);
		
		return "redirect:brandList.do";
	}
	
	/**
	 * 删除品牌操作 使用ajax异步操作 （不太适合）
	 * @param ids 一个或者多个id
	 * @return
	 */
	@RequestMapping("deleteBrandAjax.do")
	public String deleteBrand(@RequestBody QueryBean query,Model model){
	    brandService.deleteBrands(query.getIds(),query.getName(),query.getIsDisplay());
		model.addAttribute("name", query.getName());
		model.addAttribute("isDisplay",query.getIsDisplay());
		return "redirect:brandList.do";
	}
	
	/**
	 * 删除品牌操作 同步操作
	 * @param ids 一个或者多个id
	 * @return
	 */
	@RequestMapping("deleteBrand.do")
	public String deleteBrand(@RequestBody String ids,@RequestBody String name,
			@RequestBody Integer isDisplay,Model model){
	    brandService.deleteBrands(ids,name,isDisplay);
		model.addAttribute("name", name);
		model.addAttribute("isDisplay",isDisplay);
		return "redirect:brandList.do";
	}
	
	
	/**
	 *显示品牌的添加页面
	 */
	@RequestMapping("/brandEditUI.do")
	public String brandEidtUI(int id,String name,Integer isDisplay,Model model){
		Brand brand = brandService.selectOne(id);
		model.addAttribute("brand",brand);
		model.addAttribute("name",name);  //修改后重定向到列表页的名称显示
		model.addAttribute("isDisplay",isDisplay); //修改后重定向到列表页的显示
		model.addAttribute("baseUrl",CommonsConstant.UPLOADFILE_BASE_URL);
		return "brand/edit";
	}
	/**
	 *显示品牌的添加页面
	 */
	@RequestMapping("/brandEdit.do")
	public String brandEidt(Brand brand,@RequestParam(value="queryName") String name,@RequestParam(value="queryIsDisplay") int isDisplay,Model model){
		brandService.updateBrand(brand);
		model.addAttribute("name",name);  //修改后重定向到列表页的名称显示
		model.addAttribute("isDisplay",isDisplay); //修改后重定向到列表页的显示
		return "redirect:brandList.do";
	}
}
