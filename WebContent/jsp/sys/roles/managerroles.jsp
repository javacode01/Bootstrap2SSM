<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/sys/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/jsp/sys/include/header.jsp"%>
<link rel="stylesheet" href="${basepath}resource/zTree/css/metroStyle/metroStyle.css">
</head>
<body>
	<div class="container-fluid">
		<form class="form-horizontal form-panel" role="form">
			<div class="form-group">
			    <label for="search_roleName" class="col-sm-1 control-label">角色名</label>
			    <div class="col-sm-3">
			    	<input type="text" class="form-control" id="search_roleName" placeholder="请输入角色名">
			    </div>
			    <label for="search_roleCode" class="col-sm-1 control-label">角色编号</label>
			    <div class="col-sm-3">
			    	<input type="text" class="form-control" id="search_roleCode" placeholder="请输入角色编号">
			    </div>
			    <div class="col-sm-4">
			    	<button type="button" class="btn btn-primary btn-sm" onclick="search()">查询</button>
			    </div>
			</div>
		</form>
		<div class="row table-panel">
			<div class="col-sm-12">
				<div id="toolbar">
					<button type="button" class="btn btn-success btn-sm" onclick="addRole()">增加</button>
					<button type="button" class="btn btn-primary btn-sm" onclick="editRole()">修改</button>
					<button type="button" class="btn btn-danger btn-sm" onclick="removeRoles()">删除</button>
					<button type="button" class="btn btn-info btn-sm" onclick="roleFunction()">权限分配</button>
				</div>
				<table id="rolesTable"></table>
			</div>
		</div>
	</div>
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="roleEdit" tabindex="-1" role="dialog" aria-hidden="true"></div>
	<%@ include file="/jsp/sys/include/footer.jsp"%>
	<script src="${basepath}resource/zTree/js/jquery.ztree.core.min.js"></script>
	<script src="${basepath}resource/zTree/js/jquery.ztree.excheck.min.js"></script>
	<script src="${basepath}jsp/sys/roles/managerroles.js"></script>
	<script type="text/javascript">
		$(function(){
			init();
		});
	</script>
</body>
</html>