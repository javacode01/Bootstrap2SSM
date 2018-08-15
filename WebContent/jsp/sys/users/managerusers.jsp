<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/sys/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/jsp/sys/include/header.jsp"%>
<link rel="stylesheet" href="${basepath}resource/zTree/css/metroStyle/metroStyle.css">
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
.panel-default>.panel-heading {
    border:0px;
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
					<ul id="tree" class="ztree"></ul>
				</div>
			</div>
			<!-- 右侧操作区域 -->
			<div class="col-sm-9" style="height:100%;">
				<div class="sys-skin" style="position:absolute;left:0px;right:0px;z-index:600;width:100%;height:30px;line-height: 30px;">
					<strong>&nbsp;当前机构信息</strong>
				</div>
				<div class="row" style="height:100%;padding-top:30px;overflow-y: auto;">
					<div class="panel panel-default cust-panel">
					    <div class="panel-body">
					    	<div class="row" style="margin-bottom:10px;">
					    		<div class="col-sm-2" style="text-align: right;">机构编码：</div>
					    		<div class="col-sm-2" style="text-align: left;" id="show_organCode"></div>
					    		<div class="col-sm-2" style="text-align: right;">机构名称：</div>
					    		<div class="col-sm-6" style="text-align: left;" id="show_organName"></div>
					    	</div>
					    	<div class="row">
					    		<div class="col-sm-2" style="text-align: right;">机构图标：</div>
					    		<div class="col-sm-2" style="text-align: left;"><span id="show_iconUrl"></span></div>
					    		<div class="col-sm-2" style="text-align: right;">机构级别：</div>
					    		<div class="col-sm-2" style="text-align: left;" id="show_organLevel"></div>
					    		<div class="col-sm-2" style="text-align: right;">排序：</div>
					    		<div class="col-sm-2" style="text-align: left;" id="show_seq"></div>
					    	</div>
					    </div>
					</div>
					<div class="panel panel-default cust-panel">
						<div class="panel-heading cust-panel-heading">
							机构用户列表
						</div>
						<div class="panel-body">
							<div id="toolbar">
								<button type="button" class="btn btn-primary btn-sm" onclick="editUser()">编辑</button>
						    	<button type="button" class="btn btn-success btn-sm" onclick="addUser()">新增</button>
						    	<button type="button" class="btn btn-danger btn-sm" onclick="removeUser()">删除</button>
						    	<button type="button" class="btn btn-info btn-sm" onclick="userRole()">分配角色</button>
							</div>
							<table id="table"></table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="userModal" tabindex="-1" role="dialog" aria-hidden="true">
	</div>
	<%@ include file="/jsp/sys/include/footer.jsp"%>
	<script src="${basepath}resource/zTree/js/jquery.ztree.core.min.js"></script>
	<script src="${basepath}jsp/sys/users/managerusers.js"></script>
	<script type="text/javascript">
		//加载字典项
		var ORGANLEVEL = <%=BspUtils.listOrganDesign() %>;
		var USERSSTATUS = <%=BspUtils.listDicItemJSONByDicType("USERSSTATUS") %>;
		var USERSSYSTEM = <%=BspUtils.listDicItemJSONByDicType("USERSSYSTEM") %>;
		
		var zTreeObj;
	   	// zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
	   	var setting = {
			async: {
				enable: true,
				autoParam: ["id"],
				url: basepath+"sys/organ/getOrganNodes?"+$("meta[name='_csrf_header']").attr("content")+"="+$("meta[name='_csrf']").attr("content"),
				type: "post",
				dataType:'json'
			},
			callback:{
				onClick:showDetail//点击节点事件
			},
			data:{
	            simpleData:{
	                enable: true,
	                idKey:'id',
	                pIdKey:'pid',
	                rootPId: 0
	            }
	        },
	        edit: {
	    		enable: false
	    	},
			view:{
				showLine: false,
				nameIsHTML:true,
				showIcon: false,
				showTitle:false
			}
	   	};
	   	// zTree 的数据属性，深入使用请参考 API 文档（zTreeNode 节点数据详解）
	   	var zNodes = [{id:"root",name:"&nbsp;<span class='fa fa-sitemap' style='font: normal normal normal 14px/1 FontAwesome;'></span>&nbsp;组织结构树",isParent:true,data:{organCode:"root",organName:"根节点",organLevel:"root",iconUrl:"",seq:"1"}}];
		$(function(){
			init();
		});
	</script>
</body>
</html>