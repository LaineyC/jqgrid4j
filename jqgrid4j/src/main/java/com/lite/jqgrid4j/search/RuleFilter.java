package com.lite.jqgrid4j.search;

import ognl.OgnlRuntime;
import net.sf.json.JSONObject;
import com.lite.jqgrid4j.bean.FilterResult;
import com.lite.jqgrid4j.bean.ObjectGraph;
import com.lite.jqgrid4j.bean.Rule;
import com.lite.jqgrid4j.bean.Snippet;
import com.lite.jqgrid4j.config.FilterConfig;
import com.lite.jqgrid4j.dispatcher.DispatcherManager;
import com.lite.jqgrid4j.dispatcher.OperatorDispatcher;
import com.lite.jqgrid4j.dispatcher.TypeProxyDispatcher;
import com.lite.jqgrid4j.typeproxy.TypeProxy;
import com.lite.jqgrid4j.util.ObjectGraphUtils;

public class RuleFilter extends AbstractFilter{
	
	private Rule rule = new Rule();
	
	public RuleFilter(){

	}

	public RuleFilter(String filters, Class<?> root, FilterConfig filterConfig) {
		this.filter(filters, root, filterConfig);
	}
	
	public RuleFilter(String filters, Class<?> root) {
		this.filter(filters, root);
	}

	public FilterResult filter(String filters, Class<?> root, FilterConfig filterConfig) {
		JSONObject ruleJSONObject = JSONObject.fromObject(filters);
		String field = ruleJSONObject.getString(filterConfig.getField());
		String operator = ruleJSONObject.getString(filterConfig.getOperator());
		String data = ruleJSONObject.getString(filterConfig.getData());
		ObjectGraph ruleObjectGraph = ObjectGraphUtils.rootObjectGraph(root, filterConfig);
		this.rule.setObjectGraph(ruleObjectGraph);
		String navigationOperator = filterConfig.getNavigationOperator();
		int index = field.indexOf(navigationOperator);
		String objectGraphName = (ruleObjectGraph.getAlias() == null || !filterConfig.isShowRoot()) ? field : (ruleObjectGraph.getAlias() + navigationOperator + field);
		ObjectGraph objectGraph = index != -1 ? ObjectGraphUtils.objectGraph(field, root, filterConfig, ruleObjectGraph) : 
				new ObjectGraph(field, objectGraphName,  OgnlRuntime.getField(root, field).getType(), null);
		String type = objectGraph.getType().getSimpleName().toLowerCase();
		this.rule.setFilterString(filters);
		this.rule.setField(objectGraph.getAlias());
		this.rule.setOperator(operator);
		this.rule.setData(data);
		this.rule.setType(type);
		DispatcherManager dispatcherManager = DispatcherManager.getInstance();
		OperatorDispatcher operatorDispatcher = filterConfig.getOperatorDispatcher();
		TypeProxyDispatcher typeProxyDispatcher = filterConfig.getTypeProxyDispatcher();
		TypeProxy<?> typeProxy = dispatcherManager.typeProxyAction(this.rule, filterConfig, typeProxyDispatcher);
		Snippet snippet = dispatcherManager.operaterAction(this.rule, typeProxy, filterConfig, operatorDispatcher);
		this.rule.setSnippet(snippet);
		return this.rule;
	}

	public Rule getRule() {
		return rule;
	}

	public void setRule(Rule rule) {
		this.rule = rule;
	}
	
}
