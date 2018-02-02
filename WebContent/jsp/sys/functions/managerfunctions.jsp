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
	<div class="container-fluid" style="height:100%;">
		<div class="row" style="height:100%;">
			<!-- 左侧的树状结构区域 -->
			<div class="col-sm-3" style="height:100%;border-right:2px solid #555;">
				<div calss="row sys-skin" style="position:absolute;left:0px;right:0px;z-index:600;height:30px;line-height: 30px;background-color:#555;color:#FFF;">
					<strong>&nbsp;功能树结构</strong>
				</div>
				<div class="row" style="height:100%;padding-top:30px;overflow-y:auto;">
					<div id="tree"></div>
				</div>
			</div>
			<!-- 右侧操作区域 -->
			<div class="col-sm-9" style="height:100%;">
				<div calss="row sys-skin" style="position:absolute;left:0px;right:0px;z-index:600;width:100%;height:30px;line-height: 30px;background-color:#555;color:#FFF;">
					<strong>&nbsp;当前功能详情</strong>
				</div>
				<div class="row" style="height:100%;padding-top:30px;overflow-y: auto;">
					<div class="panel panel-default">
					    <div class="panel-heading" style="text-align: right;">
					    	<div style="float:left;">当前功能信息</div>
					    	<button type="button" class="btn btn-primary btn-sm" onclick="edit()">编辑</button>
					    	<button type="button" class="btn btn-success btn-sm" onclick="add()">新增下级</button>
					    	<button type="button" class="btn btn-danger btn-sm" onclick="remove()">删除</button>
					    </div>
					    <div class="panel-body">
					    	<div class="row">
					    		<div class="col-sm-2" style="text-align: right;">功能编号：</div>
					    		<div class="col-sm-2" style="text-align: left;" id="show_functionCode"></div>
					    		<div class="col-sm-2" style="text-align: right;">功能名称：</div>
					    		<div class="col-sm-2" style="text-align: left;" id="show_functionName"></div>
					    		<div class="col-sm-2" style="text-align: right;">功能级别：</div>
					    		<div class="col-sm-2" style="text-align: left;" id="show_functionLevel"></div>
					    	</div>
					    	<div class="row">
					    		<div class="col-sm-2" style="text-align: right;">图标：</div>
					    		<div class="col-sm-2" style="text-align: left;"><span id="show_functionIcon"></span></div>
					    		<div class="col-sm-2" style="text-align: right;">资源地址：</div>
					    		<div class="col-sm-2" style="text-align: left;" id="show_functionUrl"></div>
					    		<div class="col-sm-2" style="text-align: right;">排序：</div>
					    		<div class="col-sm-2" style="text-align: left;" id="show_seq"></div>
					    	</div>
					    </div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							下级功能列表
						</div>
						<div class="panel-body">
							<table id="table"></table>
						</div>
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