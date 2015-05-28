package com.lite.jqgrid4j.bean;

public abstract class FilterResult {
	
	private String filterString;
	
	private Snippet snippet = new Snippet();
	
	private ObjectGraph objectGraph = new ObjectGraph();
	
	public FilterResult() {

	}
	
	public String getFilterString() {
		return filterString;
	}

	public void setFilterString(String filterString) {
		this.filterString = filterString;
	}

	public Snippet getSnippet() {
		return snippet;
	}

	public void setSnippet(Snippet snippet) {
		this.snippet = snippet;
	}

	public ObjectGraph getObjectGraph() {
		return objectGraph;
	}

	public void setObjectGraph(ObjectGraph objectGraph) {
		this.objectGraph = objectGraph;
	}
	
}
