package com.lite.jqgrid4j.bean;

public class Rule extends FilterResult{
	
	private String field;
	
	private String operator;
	
	private String data;
	
	private String type;
	
	public Rule() {

	}
	
	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
