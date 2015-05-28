package com.lite.jqgrid4j.test;

import java.util.List;
import java.util.Set;
import com.lite.jqgrid4j.JqGridHelper;
import com.lite.jqgrid4j.bean.FilterResult;
import com.lite.jqgrid4j.bean.ObjectGraph;
import com.lite.jqgrid4j.bean.ParameterStyle;
import com.lite.jqgrid4j.bean.Snippet;
import com.lite.jqgrid4j.config.FilterConfig;
import com.lite.jqgrid4j.util.ObjectGraphUtils;

public class Test {

	public static void main(String[] args) {
		Test test = new Test();
		test.complexSearchTest();
	}
	
	public void singleSearchTest(){
		long startTime = System.currentTimeMillis();
		FilterConfig filterConfig = new FilterConfig();
		filterConfig.setParameterStyle(ParameterStyle.VALUE);
		filterConfig.setShowRoot(false);
		filterConfig.setObjectAlias(false);
		FilterResult filterResult = JqGridHelper.singleSearchFilter("parent.parent.id", "le", "1", Admin.class, filterConfig);
		System.out.println( "转化时间：" + (System.currentTimeMillis() - startTime));
		this.printSnippet(filterResult);
	}
	
	public void complexSearchTest(){
		String filters = "{\"groupOp\":\"OR\",\"rules\":[{\"field\":\"primaryRole.name\",\"op\":\"in\",\"data\":\"1,3\"},{\"field\":\"loginCount\",\"op\":\"eq\",\"data\":\"232\"},{\"field\":\"parent.parent.parent.id\",\"op\":\"eq\",\"data\":\"32\"}],\"groups\":[{\"groupOp\":\"AND\",\"rules\":[{\"field\":\"parent.parent.id\",\"op\":\"eq\",\"data\":\"32\"},{\"field\":\"loginCount\",\"op\":\"eq\",\"data\":\"31\"}],\"groups\":[]}]}";
		long startTime = System.currentTimeMillis();
		FilterConfig filterConfig = new FilterConfig();
		//filterConfig.setRootName("admin11");
		//filterConfig.setParameterStyle(ParameterStyle.NON_NAMED);
		filterConfig.setShowRoot(false);
		filterConfig.setObjectAlias(false);
		FilterResult filterResult = JqGridHelper.complexSearchFilter(filters, Admin.class, filterConfig);
		System.out.println( "转化时间：" + (System.currentTimeMillis() - startTime));
		this.printSnippet(filterResult);
	}
	
	private void printSnippet(FilterResult filterResult){
		Snippet snippet = filterResult.getSnippet();
		System.out.println("转化的HQL：" + snippet.getScript());
		List<String> parameterNames = snippet.getParameterNames();
		List<Object> parameterValues = snippet.getParameterValues();
		for(int i = 0 ; i < parameterValues.size() ; i++){
			String parameterName = null;
			if(!parameterNames.isEmpty()){
				parameterName = parameterNames.get(i);
			}
			Object parameterValue = parameterValues.get(i);
			System.out.println("参数名：" + parameterName + "，参数值：" + parameterValue + "，参数值类型：" + parameterValue.getClass().getName());
		}
		ObjectGraph objectGraph = filterResult.getObjectGraph();
		System.out.println("根节点对象：名称--" + objectGraph.getName() +  "，重命名--" + objectGraph.getAlias() +   "，对象类型--" + objectGraph.getType().getSimpleName());
		this.printObjectGraph(objectGraph);
	} 
	
	public void objectGraphTest(){
		FilterConfig filterConfig = new FilterConfig();
		ObjectGraph rootObjectGraph = ObjectGraphUtils.rootObjectGraph(Admin.class, filterConfig);
		ObjectGraphUtils.objectGraph("parent.id", Admin.class, filterConfig, rootObjectGraph);
		System.out.println("名称--" + rootObjectGraph.getName() +  "，重命名--" + rootObjectGraph.getAlias() +  "，对象类型--" + rootObjectGraph.getType().getSimpleName());
		printObjectGraph(rootObjectGraph);
	}
	
	private void printObjectGraph(ObjectGraph objectGraph){
		Set<ObjectGraph> children = objectGraph.getChildren();
		for(ObjectGraph o : children){
			System.out.println("关联的对象：名称--" + o.getName() +  "，重命名--" + o.getAlias() + "，对象类型--" + o.getType().getSimpleName() + "，父对象名--" + o.getParent().getAlias());
			this.printObjectGraph(o);
		}
	} 

}
