/**
 * 初始化的方法
 * 
 * @returns
 */
function init() {
	initTable();
}

/**
 * 初始化表格
 * @returns
 */
function initTable(){
	$('#quartzjobTable').bootstrapTable({
		url:basepath+"sys/quartzjob/listQuartzJobByPage",
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        queryParams: function(params){
        	return getParam(params);
        },									//传递参数（*）
        pagination:true,//启用分页
        sidePagination:"server",//在服务端分页
        sortable: false,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        pageNumber:1,//首页页码
        pageSize:10,//页面数据条数
        pageList:[10,25,50,100],//页面条数选择
        clickToSelect: true,                //是否启用点击选中行
        uniqueId: "recid",                     //每一行的唯一标识，一般为主键列
        toolbar: "#toolbar",                //工具按钮用哪个容器
        showColumns:true,
        showRefresh:true,
        showToggle:true,
        columns: [{
        	checkbox:true
        }, {
            field: 'jobName',
            title: '任务名称',
            width:"15%"
        }, {
            field: 'jobGroupName',
            title: '任务组',
            width:"15%"
        }, {
            field: 'jobClass',
            title: '任务执行类',
            width:"20%"
        }, {
            field: 'cronExpression',
            title: '触发时间',
            width:"10%"
        }, {
            field: 'status',
            title: '状态',
            width:"10%",
            formatter:function(value,row,index){
            	return SysUtil.formatDicItem(value,QUARTZJOBSTATUS);
            }
        }, {
            field: 'remark',
            title: '备注',
            width:"25%"
        }]
	});
}

/**
 * 获取查询条件
 * @param params
 * @returns
 */
function getParam(params){
	var filter = new HashMap();
	var searchJobName = $("#search_jobName").val();
	if(searchJobName){
		filter.put("jobName@LIKE","%"+searchJobName+"%");
	}
	var searchJobGroupName = $("#search_jobGroupName").val();
	if(searchJobGroupName){
		filter.put("jobGroupName@LIKE","%"+searchJobGroupName+"%");
	}
	$.extend(params,{filter:filter.getJSON()});
	return params;
}

/**
 * 查询
 * @returns
 */
function search(){
	$('#quartzjobTable').bootstrapTable('destroy');
	initTable();
}

/**
 * 新增定时任务
 * @returns
 */
function addQuartzJob(){
	$("#quartzjobEdit").load(basepath+"sys/quartzjob/toEditQuartzJob?handle=add",function(){
		$("#quartzjobEdit").modal({backdrop: 'static', keyboard: false});
	});
}

/**
 * 修改定时任务
 * @returns
 */
function editQuartzJob(){
	var selected = $("#quartzjobTable").bootstrapTable("getSelections");
	if(selected.length!=1){
		PluginUtil.info("请选择一个要修改定时任务");
		return false;
	}
	$("#quartzjobEdit").load(basepath+"sys/quartzjob/toEditQuartzJob?handle=edit&recid="+selected[0].recid,function(){
		$("#quartzjobEdit").modal({backdrop: 'static', keyboard: false});
	});
}

/**
 * 删除定时任务
 * @returns
 */
function deleteQuartzJob(){
	var selected = $("#quartzjobTable").bootstrapTable("getSelections");
	if(selected.length<1){
		PluginUtil.info("请先选择要删除的定时任务");
		return false;
	}
	PluginUtil.confirm("是否确认删除当前选中的定时任务？",function(){
		var recids = new Array();
 	   $.each(selected,function(index,data){
 		   recids.push(data.recid);
 	   });
 	   PluginUtil.mask("body");
 	   //提交
 	   $.ajax({
 		   url:basepath+'sys/quartzjob/deleteQuartzJob',
 		   type:'post',
 		   data:{recids:recids.join(",")},
 		   success:function(result){
 			   PluginUtil.unmask("body");
 			   if(result.code=='success'){
 				   PluginUtil.info("删除成功");
 				   search();
 			   }else{
 				   PluginUtil.info(result.data);
 			   }
 		  },
 		  error:function(error){
 			  PluginUtil.unmask("body");
 			  PluginUtil.info(error);
 		  }
 	   });
	});
}

/**
 * 启动定时任务
 * @returns
 */
function startQuartzJob(){
	var selected = $("#quartzjobTable").bootstrapTable("getSelections");
	if(selected.length<1){
		PluginUtil.info("请先选择要启动的定时任务");
		return false;
	}
	PluginUtil.confirm("是否确认启动当前选中的定时任务？",function(){
		 var recids = new Array();
  	   $.each(selected,function(index,data){
  		   recids.push(data.recid);
  	   });
  	   PluginUtil.mask("body");
  	   //提交
  	   $.ajax({
  		   url:basepath+'sys/quartzjob/startQuartzJob',
  		   type:'post',
  		   data:{recids:recids.join(",")},
  		   success:function(result){
  			   PluginUtil.unmask("body");
  			   if(result.code=='success'){
  				   PluginUtil.info("启动成功");
  				   search();
  			   }else{
  				   PluginUtil.alert(result.data);
  			   }
  		  },
  		  error:function(error){
  			  PluginUtil.unmask("body");
  			  PluginUtil.alert(error);
  		  }
  	   });
	});
}

/**
 * 停止定时任务
 * @returns
 */
function stopQuartzJob(){
	var selected = $("#quartzjobTable").bootstrapTable("getSelections");
	if(selected.length<1){
		PluginUtil.info("请先选择要停止的定时任务");
		return false;
	}
	PluginUtil.confirm("是否确认停止当前选中的定时任务？",function(){
		var recids = new Array();
 	   	$.each(selected,function(index,data){
 	   		recids.push(data.recid);
 	   	});
 	   	PluginUtil.mask("body");
 	   	//提交
 	   	$.ajax({
 	   		url:basepath+'sys/quartzjob/stopQuartzJob',
 	   		type:'post',
 	   		data:{recids:recids.join(",")},
 	   		success:function(result){
 	   			PluginUtil.unmask("body");
 	   			if(result.code=='success'){
 	   				PluginUtil.info("停止成功");
 	   				search();
 	   			}else{
 	   				PluginUtil.alert(result.data);
 	   			}
 	   		},
 	   		error:function(error){
 	   			PluginUtil.unmask("body");
 	   			PluginUtil.alert(error);
 	   		}
 	   	});
	});
}

/**
 * 暂停定时任务
 * @returns
 */
function pauseQuartzJob(){
	var selected = $("#quartzjobTable").bootstrapTable("getSelections");
	if(selected.length<1){
		PluginUtil.info("请先选择要暂停的定时任务");
		return false;
	}
	PluginUtil.confirm("是否确认暂停当前选中的定时任务？",function(){
		var recids = new Array();
 	   $.each(selected,function(index,data){
 		   recids.push(data.recid);
 	   });
 	   PluginUtil.mask("body");
 	   //提交
 	   $.ajax({
 		   url:basepath+'sys/quartzjob/pauseQuartzJob',
 		   type:'post',
 		   data:{recids:recids.join(",")},
 		   success:function(result){
 			   PluginUtil.unmask("body");
 			   if(result.code=='success'){
 				   PluginUtil.info("暂停成功");
 				   search();
 			   }else{
 				   PluginUtil.alert(result.data);
 			   }
 		  },
 		  error:function(error){
 			  PluginUtil.unmask("body");
 			  PluginUtil.alert(error);
 		  }
 	   });
	});
}

/**
 * 恢复定时任务
 * @returns
 */
function resumeQuartzJob(){
	var selected = $("#quartzjobTable").bootstrapTable("getSelections");
	if(selected.length<1){
		PluginUtil.info("请先选择要恢复的定时任务");
		return false;
	}
	PluginUtil.confirm("是否确认恢复当前选中的定时任务？",function(){
		 var recids = new Array();
  	   $.each(selected,function(index,data){
  		   recids.push(data.recid);
  	   });
  	   PluginUtil.mask("body");
  	   //提交
  	   $.ajax({
  		   url:basepath+'sys/quartzjob/resumeQuartzJob',
  		   type:'post',
  		   data:{recids:recids.join(",")},
  		   success:function(result){
  			   PluginUtil.unmask("body");
  			   if(result.code=='success'){
  				   PluginUtil.info("恢复成功");
  				   search();
  			   }else{
  				   PluginUtil.alert(result.data);
  			   }
  		  },
  		  error:function(error){
  			  PluginUtil.unmask("body");
  			  PluginUtil.alert(error);
  		  }
  	   });
	});
}

/**
 * 立即执行（一次）
 * @returns
 */
function runQuartzJob(){
	var selected = $("#quartzjobTable").bootstrapTable("getSelections");
	if(selected.length<1){
		PluginUtil.info("请先选择要执行的定时任务");
		return false;
	}
	PluginUtil.confirm("是否确认执行当前选中的定时任务？",function(){
		var recids = new Array();
 	   $.each(selected,function(index,data){
 		   recids.push(data.recid);
 	   });
 	   PluginUtil.mask("body");
 	   //提交
 	   $.ajax({
 		   url:basepath+'sys/quartzjob/runQuartzJob',
 		   type:'post',
 		   data:{recids:recids.join(",")},
 		   success:function(result){
 			   PluginUtil.unmask("body");
 			   if(result.code=='success'){
 				   PluginUtil.info("执行成功");
 				   search();
 			   }else{
 				   PluginUtil.alert(result.data);
 			   }
 		  },
 		  error:function(error){
 			  PluginUtil.unmask("body");
 			  PluginUtil.alert(error);
 		  }
 	   });
	});
}