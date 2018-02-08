package cn.babasport.xiu.core.bean;

import java.util.Objects;


public class Color {
    private Integer id;

    private String name;

    private Integer parentId;

    private String imgUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }


	 @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        Color color = (Color) o;
	        return Objects.equals(getId(), color.getId());
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(getId());
	    }
    
}    
