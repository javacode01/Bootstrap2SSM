<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/sys/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/jsp/sys/include/header.jsp"%>
</head>
<body>
	<div class="container-fluid">
		<div class="row search-panel">
							<div class="col-sm-1 sys-search-label">订单id</div>
				<div class="col-sm-3 sys-search-input"><input class="form-control" id="search_recid"></div>
				<div class="col-sm-1 sys-search-label">订单编号</div>
				<div class="col-sm-3 sys-search-input"><input class="form-control" id="search_orderNo"></div>
				<div class="col-sm-1 sys-search-label">订单简介</div>
				<div class="col-sm-3 sys-search-input"><input class="form-control" id="search_description"></div>
				<div class="col-sm-1 sys-search-label">创建人</div>
				<div class="col-sm-3 sys-search-input"><input class="form-control" id="search_creater"></div>
				<div class="col-sm-1 sys-search-label">创建时间</div>
				<div class="col-sm-3 sys-search-input"><input class="form-control" id="search_createTime"></div>
				<div class="col-sm-1 sys-search-label">更新人</div>
				<div class="col-sm-3 sys-search-input"><input class="form-control" id="search_updater"></div>
				<div class="col-sm-1 sys-search-label">更新时间</div>
				<div class="col-sm-3 sys-search-input"><input class="form-control" id="search_updateTime"></div>
			<div class="col-sm-1 search-input"><button type="button" class="btn btn-primary btn-sm" onclick="search()">查询</button></div>
		</div>
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