package com.snail.zk.node;

import java.util.ArrayList;
import java.util.List;

public class ZKNode {
	private ZKNode parent;
	private List<ZKNode> children;
	private String path;
	private byte[] data;
	
	public ZKNode(ZKNode parent){
		this(parent, null, null);
	}
	public ZKNode(ZKNode parent, byte[] data){
		this(parent, null, data);
	}
	public ZKNode(ZKNode parent, String path,byte[] data){
		this.parent = parent;
		this.path = path;
		this.data = data;
	}
	
	public List<ZKNode> addChildren(ZKNode child){
		if(child == null){
			return children;
		}
		if(children == null){
			children = new ArrayList<ZKNode>();
		}
		children.add(child);
		return children;
	}
	public ZKNode getParent() {
		return parent;
	}
	public void setParent(ZKNode parent) {
		this.parent = parent;
	}

	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	
}
