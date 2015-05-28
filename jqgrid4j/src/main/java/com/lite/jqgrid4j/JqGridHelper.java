package com.lite.jqgrid4j;

import java.util.HashMap;
import java.util.Map;
import net.sf.json.JSONObject;
import com.lite.jqgrid4j.bean.Group;
import com.lite.jqgrid4j.bean.Rule;
import com.lite.jqgrid4j.config.FilterConfig;
import com.lite.jqgrid4j.search.GroupFilter;
import com.lite.jqgrid4j.search.RuleFilter;

public class JqGridHelper {
	
	public static Group complexSearchFilter(String filters, Class<?> root, FilterConfig filterConfig){
		return new GroupFilter(filters, root, filterConfig).getGroup();
	}
	
	public static Group complexSearchFilter(String filters, Class<?> root){
		return JqGridHelper.complexSearchFilter(filters, root, new FilterConfig());
	}
	
	public static Rule singleSearchFilter(String field, String operator, String data, Class<?> root, FilterConfig filterConfig){
		Map<String, String> rule = new HashMap<String, String>();
		rule.put(filterConfig.getField(), field);
		rule.put(filterConfig.getOperator(), operator);
		rule.put(filterConfig.getData(), data);
		return new RuleFilter(JSONObject.fromObject(rule).toString(), root, filterConfig).getRule();
	}
	
	public static Rule singleSearchFilter(String field, String operator, String data, Class<?> root){
		return JqGridHelper.singleSearchFilter(field, operator, data, root, new FilterConfig());
	}
	
}
