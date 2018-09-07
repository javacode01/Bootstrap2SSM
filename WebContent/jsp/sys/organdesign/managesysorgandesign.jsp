<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/sys/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/jsp/sys/include/header.jsp"%>
<link rel="stylesheet" href="${basepath}resource/bower_components/select2/dist/css/select2.min.css">
</head>
<body>
	<div class="container-fluid">
		<div class="row search-panel">
			<div class="col-sm-1 search-label">组织级别</div>
			<div class="col-sm-3 search-input"><input class="form-control" id="search_organLevel"></div>
			<div class="col-sm-1 search-label">级别名称</div>
			<div class="col-sm-3 search-input"><input class="form-control" id="search_organLevelName"></div>
			<div class="col-sm-4 search-input"><button type="button" class="btn btn-primary btn-sm" onclick="search()">查询</button></div>
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
	<script src="${basepath}resource/bower_components/select2/dist/js/select2.min.js"></script>
	<script src="${basepath}jsp/sys/organdesign/managesysorgandesign.js"></script>
	<script type="text/javascript">
		var ISORNOT = <%=BspUtils.listDicItemJSONByDicType("ISORNOT") %>;
		$(function(){
			init();
		});
	</script>
</body>
</html>