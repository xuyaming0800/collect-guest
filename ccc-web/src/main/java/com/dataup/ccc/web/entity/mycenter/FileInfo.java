/**
 * 
 */
package com.dataup.ccc.web.entity.mycenter;

/**
 * @author zhoujun
 *
 */
public class FileInfo {
	
	private String path;
	private String name;
	private Double size;
	private String id;
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Double getSize() {
		return size;
	}
	public void setSize(Double size) {
		this.size = size;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "FileInfo [path=" + path + ", name=" + name + ", size=" + size
				+ ", id=" + id + "]";
	}
}
