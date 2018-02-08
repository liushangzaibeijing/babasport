package cn.babasport.xiu.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.babasport.xiu.core.bean.Brand;

public interface BrandDao {
   /**
    * 查询制定条件下的品牌
    * @param brand 封装条件的brand对象(查询条件和分页条件)
    * @return 查询到的brand记录的集合
    */
   public List<Brand> getBrandsWithPage(Brand brand);
   
   /**
    * 查询所有的品牌
    * @param brand 封装条件的brand对象(查询条件和分页条件)
    * @return 查询到的brand记录的集合
    */
   public List<Brand> getBrands();
   
   /**
    * 
    * 查询所有的记录数
    * @param brand 封装条件的brand对象
    * @return 记录数
    */
   public int getTotalCounts(Brand brand);
   
   /**
    * 添加新的品牌
    * @param brand
    */
   public void saveBrand(Brand brand);
   
   /**
    * 批量删除品牌
    * @param ids 要删除的品牌id的集合
    * @param name  品牌的名字
    * @param isDisplay 品牌的大小
    */
   public void deleteBrandbyCondition(@Param("list") List<Integer> ids,@Param("name") String name,@Param("isDisplay") Integer isDisplay);

   /**
    * 根据对象的主键来查询品牌实体类对象
    * @param id 主键
    * @return 品牌实体类对象
    */
   public Brand getBrandOne(int id);
   /**
    * 更新品牌操作
    * @param brand
    */
   public void updateBrand(Brand brand);
}
