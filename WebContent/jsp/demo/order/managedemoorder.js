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
	$('#table').bootstrapTable({
		url:basepath+"demo/demoorder/listDemoOrderByPage",
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
            field: 'orderNo',
            title: '订单编号'
        }, {
            field: 'description',
            title: '订单简介'
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
	var searchOrderno = $("#search_orderno").val();
	if(searchOrderno){
		filter.put("orderNo@LIKE","%"+searchOrderno+"%");
	}
	$.extend(params,{filter:filter.getJSON()});
	return params;
}

/**
 * 查询
 * @returns
 */
function search(){
	$('#table').bootstrapTable('destroy');
	initTable();
}

/**
 * 新增
 * @returns
 */
function add(){
	$("#editModal").load(basepath+"demo/demoorder/toEditDemoOrder?handle=add",function(){
		$("#editModal").modal({backdrop: 'static', keyboard: false});
	});
}

/**
 * 修改角色
 * @returns
 */
function edit(){
	var selected = $("#table").bootstrapTable("getSelections");
	if(selected.length!=1){
		bootbox.alert({ 
			  size: "small",
			  title: "提示框",
			  message: "请选择一个要编辑的角色"
			});
		return false;
	}
	$("#editModal").load(basepath+"demo/demoorder/toEditDemoOrder?handle=edit&recid="+selected[0].recid,function(){
		$("#editModal").modal({backdrop: 'static', keyboard: false});
	});
}

/**
 * 字典删除
 * @returns
 */
function remove(){
	var selected = $("#table").bootstrapTable("getSelections");
	if(selected.length<1){
		bootbox.alert({ 
			  size: "small",
			  title: "提示框",
			  message: "请先选择要删除的订单"
			});
		return false;
	}
	bootbox.confirm({
		title:"确认框",
	    message: "是否确认删除当前选中的订单？",
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
	    	   })
	    	   //提交
	    	   $.ajax({
	    		   url:basepath+'demo/demoorder/deleteDemoOrder',
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