package com.lite.jqgrid4j.config;

import com.lite.jqgrid4j.dispatcher.DefaultTypeProxyDispatcher;
import com.lite.jqgrid4j.dispatcher.HQLOperatorDispatcher;
import com.lite.jqgrid4j.dispatcher.OperatorDispatcher;
import com.lite.jqgrid4j.dispatcher.TypeProxyDispatcher;

public class FilterConfig extends JqGridConfig{
	
	private	String groupOperator;

	private	String rules;

	private	String groups;

	private	String field;

	private	String operator;

	private	String data;
	
	private boolean objectAlias;
	
	private String rootName;
	
	private boolean showRoot;
	
	private OperatorDispatcher operatorDispatcher;
	
	private TypeProxyDispatcher typeProxyDispatcher;
	
	public FilterConfig(){
		this.init();
	}

	private void init(){
		this.groupOperator = "groupOp";
		this.rules = "rules";
		this.groups = "groups";
		this.field = "field";
		this.operator = "op";
		this.data = "data";
		this.objectAlias = true;
		this.showRoot = true;
		this.operatorDispatcher = new HQLOperatorDispatcher();
		this.typeProxyDispatcher = new DefaultTypeProxyDispatcher();
	}

	public String getGroupOperator() {
		return groupOperator;
	}

	public void setGroupOperator(String groupOperator) {
		this.groupOperator = groupOperator;
	}

	public String getRules() {
		return rules;
	}

	public void setRules(String rules) {
		this.rules = rules;
	}

	public String getGroups() {
		return groups;
	}

	public void setGroups(String groups) {
		this.groups = groups;
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

	public boolean isObjectAlias() {
		return objectAlias;
	}

	public void setObjectAlias(boolean objectAlias) {
		this.objectAlias = objectAlias;
	}

	public String getRootName() {
		return rootName;
	}

	public void setRootName(String rootName) {
		this.rootName = rootName;
	}

	public boolean isShowRoot() {
		return showRoot;
	}

	public void setShowRoot(boolean showRoot) {
		this.showRoot = showRoot;
	}

	public OperatorDispatcher getOperatorDispatcher() {
		return operatorDispatcher;
	}

	public void setOperatorDispatcher(OperatorDispatcher operatorDispatcher) {
		this.operatorDispatcher = operatorDispatcher;
	}

	public TypeProxyDispatcher getTypeProxyDispatcher() {
		return typeProxyDispatcher;
	}

	public void setTypeProxyDispatcher(TypeProxyDispatcher typeProxyDispatcher) {
		this.typeProxyDispatcher = typeProxyDispatcher;
	}
	
}
