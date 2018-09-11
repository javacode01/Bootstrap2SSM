<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/sys/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/jsp/sys/include/header.jsp"%>
</head>
<body>
	<div class="container-fluid">
		<form class="form-horizontal form-panel" role="form">
						<div class="form-group">
				<label for="search_recid" class="col-sm-1 control-label">订单id</label>
			    <div class="col-sm-3">
			    	<input type="text" class="form-control" id="search_recid" placeholder="请输入订单id">
			    </div>
				<label for="search_orderNo" class="col-sm-1 control-label">订单编号</label>
			    <div class="col-sm-3">
			    	<input type="text" class="form-control" id="search_orderNo" placeholder="请输入订单编号">
			    </div>
				<label for="search_description" class="col-sm-1 control-label">订单简介</label>
			    <div class="col-sm-3">
			    	<input type="text" class="form-control" id="search_description" placeholder="请输入订单简介">
			    </div>
			</div>
			<div class="form-group">
				<label for="search_creater" class="col-sm-1 control-label">创建人</label>
			    <div class="col-sm-3">
			    	<input type="text" class="form-control" id="search_creater" placeholder="请输入创建人">
			    </div>
				<label for="search_createTime" class="col-sm-1 control-label">创建时间</label>
			    <div class="col-sm-3">
			    	<input type="text" class="form-control" id="search_createTime" placeholder="请输入创建时间">
			    </div>
				<label for="search_updater" class="col-sm-1 control-label">更新人</label>
			    <div class="col-sm-3">
			    	<input type="text" class="form-control" id="search_updater" placeholder="请输入更新人">
			    </div>
			</div>
			<div class="form-group">
				<label for="search_updateTime" class="col-sm-1 control-label">更新时间</label>
			    <div class="col-sm-3">
			    	<input type="text" class="form-control" id="search_updateTime" placeholder="请输入更新时间">
			    </div>
				<div class="col-sm-4">
			    	<button type="button" class="btn btn-primary btn-sm" onclick="search()">查询</button>
			    </div>
			</div>
		</form>
		<div class="row table-panel">
			<div class="col-sm-12">
				<div id="toolbar">
					<button type="button" class="btn btn-success btn-sm" onclick="add()">增加</button>
					<button type="button" class="btn btn-primary btn-sm" onclick="edit()">修改</button>
					<button type="button" class="btn btn-danger btn-sm" onclick="remove()">删除</button>
				</div>
				<table id="table"></table>
			</div>
		</div>
	</div>
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-hidden="true"></div>
	<%@ include file="/jsp/sys/include/footer.jsp"%>
	<script src="${basepath}jsp/demo/module/demoorder/managedemoorder.js"></script>
	<script type="text/javascript">
		$(function(){
			init();
		});
	</script>
</body>
</html>