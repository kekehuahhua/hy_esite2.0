package com.huiyee.esite.model;


public class GK {
	private String type;//���������� S:String  A:textarea I:img L:link V:vedio
	private String mapping;//������:��ʾ����
	private String desc;//����  150*150
	private String value;

	public GK(String type,String mapping,String desc,String value) {
		super();
		this.type=type;
		this.mapping=mapping;
		this.desc=desc;
		this.value=value;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getMapping() {
		return mapping;
	}
	public void setMapping(String mapping) {
		this.mapping = mapping;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	

}
