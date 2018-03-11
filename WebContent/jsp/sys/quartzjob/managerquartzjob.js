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
		bootbox.alert({ 
			  size: "small",
			  title: "提示框",
			  message: "请选择一个要修改定时任务"
			});
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
		bootbox.alert({ 
			  size: "small",
			  title: "提示框",
			  message: "请先选择要删除的定时任务"
			});
		return false;
	}
	bootbox.confirm({
		title:"确认框",
	    message: "是否确认删除当前选中的定时任务？",
	    buttons: {
	        confirm: {
	            label: '确定',
	            className: 'btn-danger'
	        },
	        cancel: {
	            label: '取消',
	            className: 'btn-success'
	        }
	    },
	    callback: function (result) {
	       if(result){
	    	   var recids = new Array();
	    	   $.each(selected,function(index,data){
	    		   recids.push(data.recid);
	    	   });
	    	   //提交
	    	   $.ajax({
	    		   url:basepath+'sys/quartzjob/deleteQuartzJob',
	    		   type:'post',
	    		   data:{recids:recids.join(",")},
	    		   success:function(result){
	    			   if(result.code=='success'){
	    				   bootbox.alert({ 
	    						  size: "small",
	    						  title: "提示框",
	    						  message: "删除成功"
	    						});
	    				   search();
	    			   }else{
	    				   bootbox.alert({ 
	    						  size: "small",
	    						  title: "警告框",
	    						  message: result.data
	    						});
	    			   }
	    		  },
	    		  error:function(error){
	    			  bootbox.alert({ 
						  size: "small",
						  title: "警告框",
						  message: error
						});
	    		  }
	    	   });
	       }
	    }
	});
}

/**
 * 启动定时任务
 * @returns
 */
function startQuartzJob(){
	var selected = $("#quartzjobTable").bootstrapTable("getSelections");
	if(selected.length<1){
		bootbox.alert({ 
			  size: "small",
			  title: "提示框",
			  message: "请先选择要启动的定时任务"
			});
		return false;
	}
	bootbox.confirm({
		title:"确认框",
	    message: "是否确认启动当前选中的定时任务？",
	    buttons: {
	        confirm: {
	            label: '确定',
	            className: 'btn-danger'
	        },
	        cancel: {
	            label: '取消',
	            className: 'btn-success'
	        }
	    },
	    callback: function (result) {
	       if(result){
	    	   var recids = new Array();
	    	   $.each(selected,function(index,data){
	    		   recids.push(data.recid);
	    	   });
	    	   //提交
	    	   $.ajax({
	    		   url:basepath+'sys/quartzjob/startQuartzJob',
	    		   type:'post',
	    		   data:{recids:recids.join(",")},
	    		   success:function(result){
	    			   if(result.code=='success'){
	    				   bootbox.alert({ 
	    						  size: "small",
	    						  title: "提示框",
	    						  message: "启动成功"
	    						});
	    				   search();
	    			   }else{
	    				   bootbox.alert({ 
	    						  size: "small",
	    						  title: "警告框",
	    						  message: result.data
	    						});
	    			   }
	    		  },
	    		  error:function(error){
	    			  bootbox.alert({ 
						  size: "small",
						  title: "警告框",
						  message: error
						});
	    		  }
	    	   });
	       }
	    }
	});
}

/**
 * 停止定时任务
 * @returns
 */
function stopQuartzJob(){
	var selected = $("#quartzjobTable").bootstrapTable("getSelections");
	if(selected.length<1){
		bootbox.alert({ 
			  size: "small",
			  title: "提示框",
			  message: "请先选择要停止的定时任务"
			});
		return false;
	}
	bootbox.confirm({
		title:"确认框",
	    message: "是否确认停止当前选中的定时任务？",
	    buttons: {
	        confirm: {
	            label: '确定',
	            className: 'btn-danger'
	        },
	        cancel: {
	            label: '取消',
	            className: 'btn-success'
	        }
	    },
	    callback: function (result) {
	       if(result){
	    	   var recids = new Array();
	    	   $.each(selected,function(index,data){
	    		   recids.push(data.recid);
	    	   });
	    	   //提交
	    	   $.ajax({
	    		   url:basepath+'sys/quartzjob/stopQuartzJob',
	    		   type:'post',
	    		   data:{recids:recids.join(",")},
	    		   success:function(result){
	    			   if(result.code=='success'){
	    				   bootbox.alert({ 
	    						  size: "small",
	    						  title: "提示框",
	    						  message: "停止成功"
	    						});
	    				   search();
	    			   }else{
	    				   bootbox.alert({ 
	    						  size: "small",
	    						  title: "警告框",
	    						  message: result.data
	    						});
	    			   }
	    		  },
	    		  error:function(error){
	    			  bootbox.alert({ 
						  size: "small",
						  title: "警告框",
						  message: error
						});
	    		  }
	    	   });
	       }
	    }
	});
}

/**
 * 暂停定时任务
 * @returns
 */
function pauseQuartzJob(){
	var selected = $("#quartzjobTable").bootstrapTable("getSelections");
	if(selected.length<1){
		bootbox.alert({ 
			  size: "small",
			  title: "提示框",
			  message: "请先选择要暂停的定时任务"
			});
		return false;
	}
	bootbox.confirm({
		title:"确认框",
	    message: "是否确认暂停当前选中的定时任务？",
	    buttons: {
	        confirm: {
	            label: '确定',
	            className: 'btn-danger'
	        },
	        cancel: {
	            label: '取消',
	            className: 'btn-success'
	        }
	    },
	    callback: function (result) {
	       if(result){
	    	   var recids = new Array();
	    	   $.each(selected,function(index,data){
	    		   recids.push(data.recid);
	    	   });
	    	   //提交
	    	   $.ajax({
	    		   url:basepath+'sys/quartzjob/pauseQuartzJob',
	    		   type:'post',
	    		   data:{recids:recids.join(",")},
	    		   success:function(result){
	    			   if(result.code=='success'){
	    				   bootbox.alert({ 
	    						  size: "small",
	    						  title: "提示框",
	    						  message: "暂停成功"
	    						});
	    				   search();
	    			   }else{
	    				   bootbox.alert({ 
	    						  size: "small",
	    						  title: "警告框",
	    						  message: result.data
	    						});
	    			   }
	    		  },
	    		  error:function(error){
	    			  bootbox.alert({ 
						  size: "small",
						  title: "警告框",
						  message: error
						});
	    		  }
	    	   });
	       }
	    }
	});
}

/**
 * 恢复定时任务
 * @returns
 */
function resumeQuartzJob(){
	var selected = $("#quartzjobTable").bootstrapTable("getSelections");
	if(selected.length<1){
		bootbox.alert({ 
			  size: "small",
			  title: "提示框",
			  message: "请先选择要恢复的定时任务"
			});
		return false;
	}
	bootbox.confirm({
		title:"确认框",
	    message: "是否确认恢复当前选中的定时任务？",
	    buttons: {
	        confirm: {
	            label: '确定',
	            className: 'btn-danger'
	        },
	        cancel: {
	            label: '取消',
	            className: 'btn-success'
	        }
	    },
	    callback: function (result) {
	       if(result){
	    	   var recids = new Array();
	    	   $.each(selected,function(index,data){
	    		   recids.push(data.recid);
	    	   });
	    	   //提交
	    	   $.ajax({
	    		   url:basepath+'sys/quartzjob/resumeQuartzJob',
	    		   type:'post',
	    		   data:{recids:recids.join(",")},
	    		   success:function(result){
	    			   if(result.code=='success'){
	    				   bootbox.alert({ 
	    						  size: "small",
	    						  title: "提示框",
	    						  message: "恢复成功"
	    						});
	    				   search();
	    			   }else{
	    				   bootbox.alert({ 
	    						  size: "small",
	    						  title: "警告框",
	    						  message: result.data
	    						});
	    			   }
	    		  },
	    		  error:function(error){
	    			  bootbox.alert({ 
						  size: "small",
						  title: "警告框",
						  message: error
						});
	    		  }
	    	   });
	       }
	    }
	});
}

/**
 * 立即执行（一次）
 * @returns
 */
function runQuartzJob(){
	var selected = $("#quartzjobTable").bootstrapTable("getSelections");
	if(selected.length<1){
		bootbox.alert({ 
			  size: "small",
			  title: "提示框",
			  message: "请先选择要执行的定时任务"
			});
		return false;
	}
	bootbox.confirm({
		title:"确认框",
	    message: "是否确认执行当前选中的定时任务？",
	    buttons: {
	        confirm: {
	            label: '确定',
	            className: 'btn-danger'
	        },
	        cancel: {
	            label: '取消',
	            className: 'btn-success'
	        }
	    },
	    callback: function (result) {
	       if(result){
	    	   var recids = new Array();
	    	   $.each(selected,function(index,data){
	    		   recids.push(data.recid);
	    	   });
	    	   //提交
	    	   $.ajax({
	    		   url:basepath+'sys/quartzjob/runQuartzJob',
	    		   type:'post',
	    		   data:{recids:recids.join(",")},
	    		   success:function(result){
	    			   if(result.code=='success'){
	    				   bootbox.alert({ 
	    						  size: "small",
	    						  title: "提示框",
	    						  message: "执行成功"
	    						});
	    				   search();
	    			   }else{
	    				   bootbox.alert({ 
	    						  size: "small",
	    						  title: "警告框",
	    						  message: result.data
	    						});
	    			   }
	    		  },
	    		  error:function(error){
	    			  bootbox.alert({ 
						  size: "small",
						  title: "警告框",
						  message: error
						});
	    		  }
	    	   });
	       }
	    }
	});
}