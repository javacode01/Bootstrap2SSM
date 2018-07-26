package com.sys.utils;

import java.util.List;

/**
 * zTree树对应节点
 * @author Administrator
 *
 */
public class ZTreeNode {
	
	private String id;
	private String pid;
	private boolean checked;//节点的 checkBox / radio 的 勾选状态
	private List<ZTreeNode> children;//节点的子节点数据集合
	private boolean chkDisabled;//设置节点的 checkbox / radio 是否禁用
	private String icon;//节点自定义图标的 URL 路径
	private String isParent;//记录 treeNode 节点是否为父节点
	private String name;//节点名称
	private String open;//记录 treeNode 节点的 展开 / 折叠 状态。
	private Object data;//自定义数据
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public List<ZTreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<ZTreeNode> children) {
		this.children = children;
	}
	public boolean isChkDisabled() {
		return chkDisabled;
	}
	public void setChkDisabled(boolean chkDisabled) {
		this.chkDisabled = chkDisabled;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getIsParent() {
		return isParent;
	}
	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}
	public String getName() {
		return "<span class=\""+icon+"\"></span>"+name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
