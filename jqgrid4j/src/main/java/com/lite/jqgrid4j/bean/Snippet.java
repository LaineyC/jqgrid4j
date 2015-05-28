package com.lite.jqgrid4j.bean;

import java.util.ArrayList;
import java.util.List;

public class Snippet {
	
	private StringBuffer script;
	
	private List<String> parameterNames;
	
	private List<Object> parameterValues;
	
	public Snippet(){
		this.script = new StringBuffer();
		this.parameterNames = new ArrayList<String>();
		this.parameterValues = new ArrayList<Object>();
	}

	public StringBuffer getScript() {
		return script;
	}
	
	public void append(Snippet snippet) {
		this.script.append(snippet.getScript());
		this.parameterNames.addAll(snippet.getParameterNames());
		this.parameterValues.addAll(snippet.getParameterValues());
	}

	public void setScript(StringBuffer script) {
		this.script = script;
	}

	public List<String> getParameterNames() {
		return parameterNames;
	}

	public void setParameterNames(List<String> parameterNames) {
		this.parameterNames = parameterNames;
	}

	public List<Object> getParameterValues() {
		return parameterValues;
	}

	public void setParameterValues(List<Object> parameterValues) {
		this.parameterValues = parameterValues;
	}
	
}
