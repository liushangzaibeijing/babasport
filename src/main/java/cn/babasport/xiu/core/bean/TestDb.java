package cn.babasport.xiu.core.bean;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class TestDb implements Serializable{

	@Override
	public String toString() {
		return "TestDb [id=" + id + ", name=" + name + ", birthday=" + birthday + ", age=" + age + "]";
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 7199286419024611557L;
	
	private String id;
	private String name;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birthday;
	private int age;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}
