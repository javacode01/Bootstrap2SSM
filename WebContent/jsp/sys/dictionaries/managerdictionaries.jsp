<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/sys/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/jsp/sys/include/header.jsp"%>
<!-- Theme style -->
<link rel="stylesheet" href="${basepath}resource/dist/css/AdminLTE.min.css">
<style>
.list-group-item:first-child {
    border-top-left-radius: 0px;
    border-top-right-radius: 0px;
}
.list-group-item:last-child {
    border-bottom-right-radius: 0px;
    border-bottom-left-radius: 0px;
}
.pull-left.pagination-detail{
	display:none;
}
.fixed-table-container {
    border-radius: 0px;
    -webkit-border-radius: 0px;
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
				<div class="row" style="height:100%;padding-top:30px;overflow-y:auto;">
					<div class="row" style="height:30px;position:absolute;">
						<div class="col-lg-12">
							<div class="input-group">
								<input type="text" class="form-control" id="dicSearch">
								<span class="input-group-btn">
									<button class="btn btn-default" type="button" onclick="searchDic()" style="border-radius: 0px;">
										<span class="glyphicon glyphicon-search"></span>
									</button>
								</span>
							</div>
						</div>
					</div>
					<div class="row" style="margin:0;padding-top:30px;">
						<div id="dicToolbar" class="btn-group">
							<button type="button" class="btn btn-success btn-xs" onclick="addDic()" style="border-radius: 0px;">增加</button>
							<button type="button" class="btn btn-primary btn-xs" onclick="editDic()">修改</button>
							<button type="button" class="btn btn-danger btn-xs" onclick="removeDic()">删除</button>
							<button type="button" class="btn btn-info btn-xs" onclick="refreshDictionaries()" style="border-radius: 0px;">刷新字典缓存</button>
						</div>
						<div class="col-lg-12" style="padding-left:0px;padding-right:0px;">
							<table id="dicTable" style="height:100%;"></table>
						</div>
					</div>
				</div>
			</div>
			<!-- 右侧区域 -->
			<div class="col-sm-9" style="height:100%;">
				<div class="sys-skin" style="position:absolute;left:0px;right:0px;z-index:600;width:100%;height:30px;line-height: 30px;">
					<strong>&nbsp;字典项管理</strong>
				</div>
				<div class="row" style="height:100%;padding-top:30px;overflow-y: auto;">
				<div class="panel panel-default cust-panel">
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
	<script src="${basepath}jsp/sys/dictionaries/managerdictionaries.js"></script>
	<script type="text/javascript">
		$(function(){
			init();
		});
	</script>
</body>
</html>