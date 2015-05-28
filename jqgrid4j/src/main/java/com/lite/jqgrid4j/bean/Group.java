package com.lite.jqgrid4j.bean;

import java.util.ArrayList;
import java.util.List;

public class Group extends FilterResult{
	
	private String groupOperator;
	
	private List<Group> groups = new ArrayList<Group>();
	
	private List<Rule> rules = new ArrayList<Rule>();
	
	public Group() {

	}
	
	public String getGroupOperator() {
		return groupOperator;
	}

	public void setGroupOperator(String groupOperator) {
		this.groupOperator = groupOperator;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public List<Rule> getRules() {
		return rules;
	}

	public void setRules(List<Rule> rules) {
		this.rules = rules;
	}

}
