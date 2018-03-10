<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/sys/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/jsp/sys/include/header.jsp"%>
<link rel="stylesheet" href="${basepath}resource/bootstrap_treeview/bootstrap-treeview.css">
<style>
.list-group-item:first-child {
    border-top-left-radius: 0px;
    border-top-right-radius: 0px;
}
.list-group-item:last-child {
    border-bottom-right-radius: 0px;
    border-bottom-left-radius: 0px;
}
</style>
</head>
<body>
	<div class="container" style="height:100%;">
		<div class="sys-skin" style="position:fixed;left:0px;right:0px;z-index:600;height:30px;line-height: 30px;">
			<strong>&nbsp;角色管理</strong>
		</div>
		<div class="row" style="height:100%;padding-top:30px;">
			<div class="row" style="padding-top:10px;">
				<div class="col-sm-1" style="line-height: 34px;padding-right:0px;">角色名</div>
				<div class="col-sm-3"><input class="form-control" id="search_roleName"></div>
				<div class="col-sm-1" style="line-height: 34px;padding-right:0px;">角色编号</div>
				<div class="col-sm-3"><input class="form-control" id="search_roleCode"></div>
				<div class="col-sm-4"><button type="button" class="btn btn-primary btn-sm" onclick="search()">查询</button></div>
			</div>
			<div id="toolbar">
				<button type="button" class="btn btn-success btn-sm" onclick="addRole()">增加</button>
				<button type="button" class="btn btn-primary btn-sm" onclick="editRole()">修改</button>
				<button type="button" class="btn btn-danger btn-sm" onclick="removeRoles()">删除</button>
				<button type="button" class="btn btn-info btn-sm" onclick="roleFunction()">权限分配</button>
			</div>
			<table id="rolesTable"></table>
		</div>
	</div>
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="roleEdit" tabindex="-1" role="dialog" aria-hidden="true"></div>
	<%@ include file="/jsp/sys/include/footer.jsp"%>
	<script src="${basepath}resource/bootstrap_treeview/bootstrap-treeview.js"></script>
	<script src="${basepath}jsp/sys/roles/managerroles.js"></script>
	<script type="text/javascript">
		$(function(){
			init();
		});
	</script>
</body>
</html>