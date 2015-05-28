package com.lite.jqgrid4j.bean;

import java.util.HashSet;
import java.util.Set;

public class ObjectGraph {
	
	private String name;
	
	private String alias;
	
	private Class<?> type;
	
	private ObjectGraph parent;
	
	private Set<ObjectGraph> children = new HashSet<ObjectGraph>();
	
	public ObjectGraph(){
		
	}

	public ObjectGraph(String name, String alias, Class<?> type, ObjectGraph parent) {
		super();
		this.name = name;
		this.alias = alias;
		this.type = type;
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Class<?> getType() {
		return type;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}

	public ObjectGraph getParent() {
		return parent;
	}

	public void setParent(ObjectGraph parent) {
		this.parent = parent;
	}

	public Set<ObjectGraph> getChildren() {
		return children;
	}

	public void setChildren(Set<ObjectGraph> children) {
		this.children = children;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alias == null) ? 0 : alias.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ObjectGraph other = (ObjectGraph) obj;
		if (alias == null) {
			if (other.alias != null)
				return false;
		} else if (!alias.equals(other.alias))
			return false;
		return true;
	}

	public void merge(ObjectGraph other){
		if((this.alias == null && other.getAlias() == null) || 
				(this.alias != null && this.alias.equals(other.getAlias()))){
			for(ObjectGraph otherchild : other.getChildren()){
				if(!this.children.contains(otherchild)){
					this.children.add(otherchild);
				}
				else{
					for(ObjectGraph child : this.children){
						child.merge(otherchild);
					}
				}
			}
		}
	}

}
