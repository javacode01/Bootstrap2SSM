<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/sys/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/jsp/sys/include/header.jsp"%>
</head>
<body>
	<div class="container" style="height:100%;">
		<div class="sys-skin" style="position:fixed;left:0px;right:0px;z-index:600;height:30px;line-height: 30px;">
			<strong>&nbsp;定时任务管理</strong>
		</div>
		<div class="row" style="height:100%;padding-top:30px;">
			<div class="row" style="padding-top:10px;">
				<div class="col-sm-1" style="line-height: 34px;padding-right:0px;">任务名称</div>
				<div class="col-sm-3"><input class="form-control" id="search_jobName"></div>
				<div class="col-sm-1" style="line-height: 34px;padding-right:0px;">任务组名</div>
				<div class="col-sm-3"><input class="form-control" id="search_jobGroupName"></div>
				<div class="col-sm-4"><button type="button" class="btn btn-primary btn-sm" onclick="search()">查询</button></div>
			</div>
			<div id="toolbar">
				<button type="button" class="btn btn-success btn-sm" onclick="addQuartzJob()">增加</button>
				<button type="button" class="btn btn-primary btn-sm" onclick="editQuartzJob()">修改</button>
				<button type="button" class="btn btn-danger btn-sm" onclick="deleteQuartzJob()">删除</button>
				<button type="button" class="btn btn-success btn-sm" onclick="startQuartzJob()">启动任务</button>
				<button type="button" class="btn btn-danger btn-sm" onclick="stopQuartzJob()">停止任务</button>
				<button type="button" class="btn btn-warning btn-sm" onclick="pauseQuartzJob()">暂停任务</button>
				<button type="button" class="btn btn-success btn-sm" onclick="resumeQuartzJob()">恢复任务</button>
				<button type="button" class="btn btn-info btn-sm" onclick="runQuartzJob()">立即执行(一次)</button>
			</div>
			<table id="quartzjobTable"></table>
		</div>
	</div>
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="quartzjobEdit" tabindex="-1" role="dialog" aria-hidden="true"></div>
	<%@ include file="/jsp/sys/include/footer.jsp"%>
	<script src="${basepath}jsp/sys/quartzjob/managerquartzjob.js"></script>
	<script type="text/javascript">
		//加载字典项
		var QUARTZJOBSTATUS = <%=BspUtils.listDicItemJSONByDicType("QUARTZJOBSTATUS") %>;
		$(function(){
			init();
		});
	</script>
</body>
</html>