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
			    <label for="search_recid" class="col-sm-1 control-label">订单订单</label>
			    <div class="col-sm-3">
			    	<input type="text" class="form-control" id="search_recid" placeholder="请输入订单id">
			    </div>
			    <label for="search_orderNo" class="col-sm-1 control-label">订单编号</label>
			    <div class="col-sm-3">
			    	<input type="text" class="form-control" id="search_orderNo" placeholder="请输入订单编号">
			    </div>
			    <label for="search_orderNo" class="col-sm-1 control-label">订单编号</label>
			    <div class="col-sm-3">
			    	<input type="text" class="form-control" id="search_orderNo" placeholder="请输入订单编号">
			    </div>
			</div>
			<div class="form-group">
			    <label for="search_recid" class="col-sm-1 control-label">订单id</label>
			    <div class="col-sm-3">
			    	<input type="text" class="form-control" id="search_recid" placeholder="请输入订单id">
			    </div>
			    <label for="search_orderNo" class="col-sm-1 control-label">订单编号</label>
			    <div class="col-sm-3">
			    	<input type="text" class="form-control" id="search_orderNo" placeholder="请输入订单编号">
			    </div>
			    <label for="search_orderNo" class="col-sm-1 control-label">订单编号</label>
			    <div class="col-sm-3">
			    	<input type="text" class="form-control" id="search_orderNo" placeholder="请输入订单编号">
			    </div>
			</div>
			<div class="form-group">
			 	<div class="col-sm-offset-5 col-sm-7">
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