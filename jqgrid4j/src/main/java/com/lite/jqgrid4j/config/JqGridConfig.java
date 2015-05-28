package com.lite.jqgrid4j.config;

import com.lite.jqgrid4j.bean.ParameterStyle;

public class JqGridConfig {
	
	private String priorityOperator;
	
	private String navigationOperator;
	
	private String navigationAlias;
	
	private String scriptSpaceMark;
	
	private String groupAndOperator;
	
	private String groupOrOperator;
	
	private String dataSplit;
	
	private ParameterStyle parameterStyle;
	
	public JqGridConfig(){
		this.init();
	}
	
	private void init( ){
		this.priorityOperator = "(,)";
		this.navigationOperator = ".";
		this.navigationAlias = "_";
		this.scriptSpaceMark = " ";
		this.groupAndOperator = "AND";
		this.groupOrOperator = "OR";
		this.dataSplit = ",";
		this.parameterStyle = ParameterStyle.NAMED;
	}

	public String getPriorityOperator() {
		return priorityOperator;
	}

	public void setPriorityOperator(String priorityOperator) {
		this.priorityOperator = priorityOperator;
	}

	public String getNavigationOperator() {
		return navigationOperator;
	}

	public void setNavigationOperator(String navigationOperator) {
		this.navigationOperator = navigationOperator;
	}

	public String getNavigationAlias() {
		return navigationAlias;
	}

	public void setNavigationAlias(String navigationAlias) {
		this.navigationAlias = navigationAlias;
	}

	public String getScriptSpaceMark() {
		return scriptSpaceMark;
	}

	public void setScriptSpaceMark(String scriptSpaceMark) {
		this.scriptSpaceMark = scriptSpaceMark;
	}

	public String getGroupAndOperator() {
		return groupAndOperator;
	}

	public void setGroupAndOperator(String groupAndOperator) {
		this.groupAndOperator = groupAndOperator;
	}

	public String getGroupOrOperator() {
		return groupOrOperator;
	}

	public void setGroupOrOperator(String groupOrOperator) {
		this.groupOrOperator = groupOrOperator;
	}

	public String getDataSplit() {
		return dataSplit;
	}

	public void setDataSplit(String dataSplit) {
		this.dataSplit = dataSplit;
	}

	public ParameterStyle getParameterStyle() {
		return parameterStyle;
	}

	public void setParameterStyle(ParameterStyle parameterStyle) {
		this.parameterStyle = parameterStyle;
	}

}
