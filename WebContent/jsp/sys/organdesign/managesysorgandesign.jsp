<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/sys/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/jsp/sys/include/header.jsp"%>
<link rel="stylesheet" href="${basepath}resource/bower_components/select2/dist/css/select2.min.css">
</head>
<body>
	<div class="container">
		<div class="sys-skin sys-manage-title">
			<strong>&nbsp;设置组织结构的规则</strong>
		</div>
		<div class="row sys-manage-body">
			<div class="row sys-manage-body-search">
					<div class="col-sm-2 sys-search-label">组织结构级别</div>
					<div class="col-sm-3 sys-search-input"><input class="form-control" id="search_organLevel"></div>
					<div class="col-sm-2 sys-search-label">组织级别名称</div>
					<div class="col-sm-3 sys-search-input"><input class="form-control" id="search_organLevelName"></div>
				<div class="col-sm-1 sys-search-input"><button type="button" class="btn btn-primary btn-sm" onclick="search()">查询</button></div>
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