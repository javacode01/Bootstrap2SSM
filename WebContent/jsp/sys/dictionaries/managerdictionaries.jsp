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
			<!-- 左侧区域 -->
			<div class="col-sm-3 sys-border-right" style="height:100%;">
				<div class="sys-skin" style="position:absolute;left:0px;right:0px;z-index:600;height:30px;line-height: 30px;">
					<strong>&nbsp;字典管理</strong>
				</div>
				<div calss="row" style="position:absolute;top:30px;left:0px;right:0px;z-index:600;height:30px;line-height: 30px;text-align: right;padding-right:5px;">
					<div class="btn-group">
						<button type="button" class="btn btn-success btn-xs" onclick="addDic()">增加</button>
						<button type="button" class="btn btn-primary btn-xs" onclick="editDic()">修改</button>
						<button type="button" class="btn btn-danger btn-xs" onclick="removeDic()">删除</button>
					</div>
				</div>
				<div class="row" style="height:100%;padding-top:60px;overflow-y:auto;">
					<div id="tree"></div>
				</div>
			</div>
			<!-- 右侧区域 -->
			<div class="col-sm-9" style="height:100%;">
				<div class="sys-skin" style="position:absolute;left:0px;right:0px;z-index:600;width:100%;height:30px;line-height: 30px;">
					<strong>&nbsp;字典项管理</strong>
				</div>
				<div class="row" style="height:100%;padding-top:30px;overflow-y: auto;">
				<div class="panel panel-default">
					<div class="panel-body">
						<div id="toolbar">
					    	<button type="button" class="btn btn-success btn-sm" onclick="addDicItem()">增加</button>
							<button type="button" class="btn btn-primary btn-sm" onclick="editDicItem()">修改</button>
							<button type="button" class="btn btn-danger btn-sm" onclick="removeDicItem()">删除</button>
					    </div>
					    <table id="table"></table>
					</div>
				</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 字典编辑 -->
	<div class="modal fade" id="dicEdit" tabindex="-1" role="dialog" aria-hidden="true"></div>
	<!-- 字典项编辑 -->
	<div class="modal fade" id="dicItemEdit" tabindex="-1" role="dialog" aria-hidden="true"></div>
	<%@ include file="/jsp/sys/include/footer.jsp"%>
	<script src="${basepath}resource/bootstrap_treeview/bootstrap-treeview.js"></script>
	<script src="${basepath}jsp/sys/dictionaries/managerdictionaries.js"></script>
	<script type="text/javascript">
		$(function(){
			init();
		});
	</script>
</body>
</html>