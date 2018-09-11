<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/sys/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/jsp/sys/include/header.jsp"%>
<link rel="stylesheet" href="${basepath}resource/zTree/css/metroStyle/metroStyle.css">
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
					<ul id="tree" class="ztree"></ul>
				</div>
			</div>
			<!-- 右侧操作区域 -->
			<div class="col-sm-9" style="height:100%;overflow-y:auto;">
				<div class="row detail-panel">
					<div class="col-sm-12">
						<div class="row">
							<div class="col-sm-2">机构编码：</div>
				    		<div class="col-sm-2" id="show_organCode">&nbsp;</div>
				    		<div class="col-sm-2">机构名称：</div>
				    		<div class="col-sm-6" id="show_organName">&nbsp;</div>
						</div>
						<div class="row" style="margin-top:10px;">
				    		<div class="col-sm-2">机构级别：</div>
				    		<div class="col-sm-2" id="show_organLevel">&nbsp;</div>
				    		<div class="col-sm-2">机构图标：</div>
				    		<div class="col-sm-2"><span id="show_iconUrl">&nbsp;</span></div>
				    		<div class="col-sm-2">排序：</div>
				    		<div class="col-sm-2" id="show_seq">&nbsp;</div>
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
	<div class="modal fade" id="organModal" tabindex="-1" role="dialog" aria-hidden="true">
	</div>
	<%@ include file="/jsp/sys/include/footer.jsp"%>
	<script src="${basepath}resource/zTree/js/jquery.ztree.core.min.js"></script>
	<script src="${basepath}resource/zTree/js/jquery.ztree.exedit.min.js"></script>
	<script src="${basepath}jsp/sys/organ/managerorgan.js"></script>
	<script type="text/javascript">
		var ORGANLEVEL = <%=BspUtils.listOrganDesign() %>;
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
				onClick:showDetail,//点击节点事件
				beforeEditName:edit,//编辑节点
				beforeRemove:remove//删除节点
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
	    		enable: true,
	    		showRemoveBtn: function(treeId, treeNode){
	    			if('root'==treeNode.id){
	    				return false;
	    			}else{
	    				return true;
	    			}
	    		},
	    		removeTitle: "删除",
	    		showRenameBtn: function(treeId, treeNode){
	    			if('root'==treeNode.id){
	    				return false;
	    			}else{
	    				return true;
	    			}
	    		},
	    		renameTitle: "编辑"
	    	},
			view:{
				showLine: false,
				nameIsHTML:true,
				showIcon: false,
				showTitle:false,
				addHoverDom: addHoverDom,		// 用于当鼠标移动到节点上时，显示用户自定义控件。务必与 setting.view.removeHoverDom 同时使用
				removeHoverDom: removeHoverDom	// 用于当鼠标移出节点时，隐藏用户自定义控件。务必与 addHoverDom 同时使用
			}
			
	   	};
		 function addHoverDom(treeId, treeNode) {
			 if(nextLevel(treeNode.data.organLevel,ORGANLEVEL).length<1){
					return false;
			 }else{
				//给节点添加"新增"按钮
				 var sObj = $("#" + treeNode.tId + "_span");
				 if (treeNode.editNameFlag || $("#addBtn_"+treeNode.id).length>0) return;
				 var addStr = "<span class='button add' id='addBtn_" + treeNode.id
				 + "' title='新增' onfocus='this.blur();'></span>";
				 sObj.after(addStr);
				 var btn = $("#addBtn_"+treeNode.id);
				 if (btn) btn.bind("click", function(){
					 add(treeId, treeNode);
				 });
			 }
		 };
		  
		 // 用于当鼠标移出节点时，隐藏用户自定义控件
		 function removeHoverDom(treeId, treeNode) {
			 $("#addBtn_"+treeNode.id).unbind().remove();
		 };
	   	// zTree 的数据属性，深入使用请参考 API 文档（zTreeNode 节点数据详解）
	   	var zNodes = [{id:"root",name:"&nbsp;<span class='fa fa-sitemap' style='font: normal normal normal 14px/1 FontAwesome;'></span>&nbsp;组织结构树",isParent:true,data:{organCode:"root",organName:"根节点",organLevel:"root",iconUrl:"",seq:"1"}}];
		$(function(){
			init();
		});
	</script>
</body>
</html>