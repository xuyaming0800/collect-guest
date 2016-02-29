package com.dataup.ccc.core.util.cache;

import java.io.Serializable;
import java.util.List;

public class Node  implements Serializable {

 
	/** 
	 * <p>Title: </p> 
	 * <p>Description: </p> 
	 */ 
	private static final long serialVersionUID = 3766117273840197382L;
	private String id;
	private String parent;
	private List<String> child;
	private Integer level;
	private Integer agentType;
	
	private Object value;
	

	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public List<String> getChild() {
		return child;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getAgentType() {
		return agentType;
	}
	public void setAgentType(Integer agentType) {
		this.agentType = agentType;
	}
	public void setChild(List<String> child) {
		this.child = child;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
