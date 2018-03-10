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
			<div class="col-sm-3 sys-border-right" style="height:100%;">
				<div class="sys-skin" style="position:absolute;left:0px;right:0px;z-index:600;height:30px;line-height: 30px;">
					<strong>&nbsp;组织机构树</strong>
				</div>
				<div class="row" style="height:100%;padding-top:30px;overflow-y:auto;">
					<div id="tree"></div>
				</div>
			</div>
			<!-- 右侧操作区域 -->
			<div class="col-sm-9" style="height:100%;">
				<div class="sys-skin" style="position:absolute;left:0px;right:0px;z-index:600;width:100%;height:30px;line-height: 30px;">
					<strong>&nbsp;当前机构详情</strong>
				</div>
				<div class="row" style="height:100%;padding-top:30px;overflow-y: auto;">
					<div class="panel panel-default">
					    <div class="panel-heading" style="text-align: right;">
					    	<div style="float:left;">当前机构信息</div>
					    	<button type="button" class="btn btn-primary btn-sm" onclick="edit()">编辑</button>
					    	<button type="button" class="btn btn-success btn-sm" onclick="add()">新增下级</button>
					    	<button type="button" class="btn btn-danger btn-sm" onclick="remove()">删除</button>
					    </div>
					    <div class="panel-body">
					    	<div class="row">
					    		<div class="col-sm-2" style="text-align: right;">机构编码：</div>
					    		<div class="col-sm-2" style="text-align: left;" id="show_organCode"></div>
					    		<div class="col-sm-2" style="text-align: right;">机构名称：</div>
					    		<div class="col-sm-2" style="text-align: left;" id="show_organName"></div>
					    		<div class="col-sm-2" style="text-align: right;">机构级别：</div>
					    		<div class="col-sm-2" style="text-align: left;" id="show_organLevel"></div>
					    	</div>
					    	<div class="row">
					    		<div class="col-sm-2" style="text-align: right;">机构图标：</div>
					    		<div class="col-sm-2" style="text-align: left;"><span id="show_iconUrl"></span></div>
					    		<div class="col-sm-2" style="text-align: right;">机构类型：</div>
					    		<div class="col-sm-2" style="text-align: left;" id="show_organType"></div>
					    		<div class="col-sm-2" style="text-align: right;">排序：</div>
					    		<div class="col-sm-2" style="text-align: left;" id="show_seq"></div>
					    	</div>
					    </div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							下级机构列表
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
	<div class="modal fade" id="organModal" tabindex="-1" role="dialog" aria-hidden="true">
	</div>
	<%@ include file="/jsp/sys/include/footer.jsp"%>
	<script src="${basepath}resource/bootstrap_treeview/bootstrap-treeview.js"></script>
	<script src="${basepath}jsp/sys/organ/managerorgan.js"></script>
	<script type="text/javascript">
		var ORGANLEVEL = <%=BspUtils.listDicItemJSONByDicType("ORGANLEVEL") %>;
		var ORGANTYPE = <%=BspUtils.listDicItemJSONByDicType("ORGANTYPE") %>;
		var ORGANCLASS = <%=BspUtils.listDicItemJSONByDicType("ORGANCLASS") %>;
		$(function(){
			init();
		});
	</script>
</body>
</html>