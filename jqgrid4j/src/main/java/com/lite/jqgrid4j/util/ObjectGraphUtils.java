package com.lite.jqgrid4j.util;

import ognl.OgnlRuntime;
import com.lite.jqgrid4j.bean.ObjectGraph;
import com.lite.jqgrid4j.config.FilterConfig;

public class ObjectGraphUtils {
	
	public static ObjectGraph objectGraph(String field, Class<?> inClass, FilterConfig filterConfig, ObjectGraph objectGraph){
		String navigationOperator = filterConfig.getNavigationOperator();
		int index = field.indexOf(navigationOperator);
		if(index == -1){
			Class<?> fieldClazz = OgnlRuntime.getField(inClass, field).getType();
			String objectGraphName = objectGraph.getAlias() == null ? field : (objectGraph.getAlias() + navigationOperator + field);
			return new ObjectGraph(field, objectGraphName, fieldClazz, objectGraph);
		}
		else{
			String shortFiled =  field.substring(0, index);
			Class<?> fieldClazz = OgnlRuntime.getField(inClass, shortFiled).getType();
			String objectGraphName = objectGraph.getAlias() == null ? shortFiled : (objectGraph.getAlias() + navigationOperator + shortFiled);
			objectGraphName = filterConfig.isObjectAlias() ? objectGraphName.replaceAll("\\" + navigationOperator, filterConfig.getNavigationAlias()) : objectGraphName;
			ObjectGraph child = new ObjectGraph(shortFiled, objectGraphName, fieldClazz, objectGraph);
			objectGraph.getChildren().add(child);
			return ObjectGraphUtils.objectGraph(field.substring(index + 1), fieldClazz, filterConfig, child);
		}
	}
	
	public static ObjectGraph rootObjectGraph(Class<?> root, FilterConfig filterConfig){
		ObjectGraph objectGraph = new ObjectGraph();
		objectGraph.setType(root);
		String rootSimpleName = filterConfig.getRootName() != null ? filterConfig.getRootName() : root.getSimpleName();
		String objectGraphName = filterConfig.isShowRoot() ? new StringBuilder().append(Character.toLowerCase(rootSimpleName.charAt(0))).append(rootSimpleName.substring(1)).toString() : null;
		objectGraph.setName(objectGraphName);
		objectGraph.setAlias(objectGraphName);
		return objectGraph;
	}

}
