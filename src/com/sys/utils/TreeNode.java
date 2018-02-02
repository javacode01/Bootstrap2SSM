package com.sys.utils;

import java.util.List;
import java.util.Map;

/**
 * bootstrap tree-view 树对应的节点
 * @author Administrator
 *
 */
public class TreeNode {
	
	private String text;//必须，列表树节点上的文本
	private String icon;//列表树节点上的图标
	private Boolean selectable;//默认为true，指定列表树的节点是否可选择。设置为false将使节点展开，并且不能被选择。
	//state.checked	    Boolean，默认值false	指示一个节点是否处于checked状态，用一个checkbox图标表示。
	//state.disabled	Boolean，默认值false	指示一个节点是否处于disabled状态。（不是selectable，expandable或checkable）
	//state.expanded	Boolean，默认值false	指示一个节点是否处于展开状态。
	//state.selected	Boolean，默认值false	指示一个节点是否可以被选择。
	private Map<String,Object> state;//一个节点的初始状态
//	private String color;//节点的前景色
//	private String backColor;//节点的背景色
//	private String[] tags;//通过结合全局showTags选项来在列表树节点的右边添加额外的信息。
	private List<TreeNode> nodes;
	private String id;
	private Object data;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Boolean getSelectable() {
		return selectable;
	}
	public void setSelectable(Boolean selectable) {
		this.selectable = selectable;
	}
	public Map<String, Object> getState() {
		return state;
	}
	public void setState(Map<String, Object> state) {
		this.state = state;
	}
	public List<TreeNode> getNodes() {
		return nodes;
	}
	public void setNodes(List<TreeNode> nodes) {
		this.nodes = nodes;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
