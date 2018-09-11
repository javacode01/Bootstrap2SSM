<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/sys/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/jsp/sys/include/header.jsp"%>
<link rel="stylesheet" href="${basepath}resource/bootstrap_treeview/bootstrap-treeview.css">
<style>
html,body {
	height:100%;
}
</style>
</head>
<body>
	<div class="container-fluid" style="height:100%;">
		<div class="row" style="height:100%;">
			<!-- 左侧的树状结构区域 -->
			<div class="col-sm-3" style="height:100%;padding:10px 15px 10px 25px;">
				<div class="row base-panel" style="height:100%;overflow-y:auto;">
					<div id="tree"></div>
				</div>
			</div>
			<!-- 右侧操作区域 -->
			<div class="col-sm-9" style="height:100%;overflow-y:auto;">
				<div class="row detail-panel">
					<div class="col-sm-12">
						<div class="row" style="border-bottom:1px solid #eeeeee;">
							<div class="col-sm-6">
								<h5>当前功能信息</h5>
							</div>
							<div class="col-sm-6" style="text-align: right;">
								<button type="button" class="btn btn-primary btn-sm" onclick="edit()">编辑</button>
						    	<button type="button" class="btn btn-success btn-sm" onclick="add()">新增下级</button>
						    	<button type="button" class="btn btn-danger btn-sm" onclick="remove()">删除</button>
							</div>
						</div>
						<div class="row" style="margin-top:10px;">
							<div class="col-sm-2">功能编号：</div>
				    		<div class="col-sm-2" id="show_functionCode"></div>
				    		<div class="col-sm-2">功能名称：</div>
				    		<div class="col-sm-2" id="show_functionName"></div>
				    		<div class="col-sm-2">图标：</div>
				    		<div class="col-sm-2" style="text-align: left;"><span id="show_functionIcon"></span></div>
						</div>
						<div class="row" style="margin-top:10px;">
				    		<div class="col-sm-2">功能级别：</div>
				    		<div class="col-sm-2" id="show_functionLevel"></div>
				    		<div class="col-sm-2">资源地址：</div>
				    		<div class="col-sm-6" id="show_functionUrl"></div>
						</div>
					</div>
		    	</div>
				<div class="row table-panel">
					<div class="col-sm-12">
						<table id="table"></table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	</div>
	<%@ include file="/jsp/sys/include/footer.jsp"%>
	<script src="${basepath}resource/bootstrap_treeview/bootstrap-treeview.js"></script>
	<script src="${basepath}jsp/sys/functions/managerfunctions.js"></script>
	<script type="text/javascript">
		var FLEVEL = <%=BspUtils.listDicItemJSONByDicType("FLEVEL") %>;
		var FTYPE = <%=BspUtils.listDicItemJSONByDicType("FTYPE") %>;
		var ISORNOT = <%=BspUtils.listDicItemJSONByDicType("ISORNOT") %>;
		$(function(){
			init();
		});
	</script>
</body>
</html>