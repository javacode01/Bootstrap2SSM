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
			<#list columns as column>
				<#if column_index%3==0>
			<div class="form-group">
				</#if>
				<label for="search_${column.propertyName}" class="col-sm-1 control-label">${column.columnComment}</label>
			    <div class="col-sm-3">
			    	<input type="text" class="form-control" id="search_${column.propertyName}" placeholder="请输入${column.columnComment}">
			    </div>
				<#if (column_index+1)%3==0>
			</div>
				</#if>
			</#list>
			<#if columns?size%3!=0>
				<div class="col-sm-4">
			    	<button type="button" class="btn btn-primary btn-sm" onclick="search()">查询</button>
			    </div>
			</div>
			<#else>
			<div class="form-group">
			 	<div class="col-sm-offset-5 col-sm-7">
			    	<button type="button" class="btn btn-primary btn-sm" onclick="search()">查询</button>
			    </div>
			</div>
			</#if>
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
	<script src="${r"${basepath}"}${targetDir}manage${className?lower_case}.js"></script>
	<script type="text/javascript">
		$(function(){
			init();
		});
	</script>
</body>
</html>