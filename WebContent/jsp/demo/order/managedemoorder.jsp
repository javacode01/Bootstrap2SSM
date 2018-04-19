<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/sys/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/jsp/sys/include/header.jsp"%>
</head>
<body>
	<div class="container">
		<div class="sys-skin sys-manage-title">
			<strong>&nbsp;订单管理</strong>
		</div>
		<div class="row sys-manage-body">
			<div class="row sys-manage-body-search">
				<div class="col-sm-1" style="line-height: 34px;padding-right:0px;">角色编号</div>
				<div class="col-sm-3"><input class="form-control" id="search_orderno"></div>
				<div class="col-sm-4"><button type="button" class="btn btn-primary btn-sm" onclick="search()">查询</button></div>
			</div>
			<div id="toolbar">
				<button type="button" class="btn btn-success btn-sm" onclick="add()">增加</button>
				<button type="button" class="btn btn-primary btn-sm" onclick="edit()">修改</button>
				<button type="button" class="btn btn-danger btn-sm" onclick="remove()">删除</button>
			</div>
			<table id="table"></table>
		</div>
	</div>
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-hidden="true"></div>
	<%@ include file="/jsp/sys/include/footer.jsp"%>
	<script src="${basepath}jsp/demo/order/managedemoorder.js"></script>
	<script type="text/javascript">
		$(function(){
			init();
		});
	</script>
</body>
</html>