package com.lite.jqgrid4j.search;

import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.lite.jqgrid4j.bean.FilterResult;
import com.lite.jqgrid4j.bean.Group;
import com.lite.jqgrid4j.bean.ObjectGraph;
import com.lite.jqgrid4j.bean.Rule;
import com.lite.jqgrid4j.bean.Snippet;
import com.lite.jqgrid4j.config.FilterConfig;
import com.lite.jqgrid4j.util.ObjectGraphUtils;

public class GroupFilter extends AbstractFilter{
	
	private static final String AND = "AND";
	
	private Group group = new Group();
	
	public GroupFilter(){

	}
	
	public GroupFilter(String filters, Class<?> root, FilterConfig filterConfig) {
		this.filter(filters, root, filterConfig);
	}
	
	public GroupFilter(String filters, Class<?> root) {
		this.filter(filters, root);
	}

	public FilterResult filter(String filters, Class<?> root, FilterConfig filterConfig) {
		Group group = this.group;
		ObjectGraph objectGraph = ObjectGraphUtils.rootObjectGraph(root, filterConfig);
		group.setObjectGraph(objectGraph);
		Snippet groupSnippet = group.getSnippet();
		StringBuffer snippetScript = groupSnippet.getScript();
		List<String> snippetParameterNames = groupSnippet.getParameterNames();
		List<Object> snippetParameterValues = groupSnippet.getParameterValues();
		List<Group> groups = group.getGroups();
		List<Rule> rules = group.getRules();
		group.setFilterString(filters);
		JSONObject filter = JSONObject.fromObject(filters);
		group.setGroupOperator(filter.getString(filterConfig.getGroupOperator()));
		String formatOperator = AND.equals(group.getGroupOperator().toUpperCase()) ? filterConfig.getGroupAndOperator(): filterConfig.getGroupOrOperator();
		JSONArray rulesJSONArray = null;
		int rulesSize = 0;
		if(filter.containsKey(filterConfig.getRules())){
			rulesJSONArray = filter.getJSONArray(filterConfig.getRules());
			rulesSize = rulesJSONArray.size();
		}
		String[] priorityOperators = filterConfig.getPriorityOperator().split(filterConfig.getDataSplit());
		snippetScript.append(priorityOperators[0]).append(filterConfig.getScriptSpaceMark());
		if(rulesSize > 0){
			for(int i = 0 ; i < rulesSize ; i++){
				RuleFilter ruleFilter = new RuleFilter(rulesJSONArray.getString(i), root, filterConfig);
				Snippet ruleFilterSnippet = ruleFilter.getRule().getSnippet();
				snippetScript.append(ruleFilterSnippet.getScript()).append(filterConfig.getScriptSpaceMark());
				if(i < rulesSize - 1){
					snippetScript.append(formatOperator).append(filterConfig.getScriptSpaceMark());
				}
				snippetParameterNames.addAll(ruleFilterSnippet.getParameterNames());
				snippetParameterValues.addAll(ruleFilterSnippet.getParameterValues());
				objectGraph.merge(ruleFilter.getRule().getObjectGraph());
				rules.add(ruleFilter.getRule());
			}
		}
		JSONArray groupsJSONArray = null;
		int groupsSize = 0;
		if(filter.containsKey(filterConfig.getGroups())){
			groupsJSONArray = filter.getJSONArray(filterConfig.getGroups());
			groupsSize = groupsJSONArray.size();
		}
		if(groupsSize > 0){
			boolean formatOperatorFirst = true;
			for(int i = 0 ; i < groupsSize ; i++){
				GroupFilter groupFilter = new GroupFilter(groupsJSONArray.getString(i), root, filterConfig);
				Snippet groupFilterSnippet = groupFilter.getGroup().getSnippet();
				if(groupFilter.getGroup().getGroups().size() > 0 || groupFilter.getGroup().getRules().size() > 0){
					if(formatOperatorFirst){
						formatOperatorFirst = false;
						if(rulesSize > 0){
							snippetScript.append(formatOperator).append(filterConfig.getScriptSpaceMark());
						}
					}
					else{
						snippetScript.append(formatOperator).append(filterConfig.getScriptSpaceMark());
					}
					snippetScript.append(groupFilterSnippet.getScript()).append(filterConfig.getScriptSpaceMark());
					groupSnippet.getParameterNames().addAll(groupFilterSnippet.getParameterNames());
					groupSnippet.getParameterValues().addAll(groupFilterSnippet.getParameterValues());
					objectGraph.merge(groupFilter.getGroup().getObjectGraph());
					groups.add(groupFilter.getGroup());
				}
			}
		}
		snippetScript.append(priorityOperators[1]);
		return group;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}
	
}
