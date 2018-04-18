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
		url:basepath+"${urlPrefix}/${className?lower_case}/get${className}ByPage",
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
        }, 
        <#list columns as column>
        {
        	field:'${column.propertyName}',
        	title:'${column.columnComment}'
        },
		</#list>
		{
			title:'操作'
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
	<#list columns as column>
    var search${column.propertyName} = $("#search_${column.propertyName}").val();
	if(search${column.propertyName}){
		filter.put("${column.propertyName}@=",search${column.propertyName});
	}
	</#list>
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
	$("#editModal").load(basepath+"${urlPrefix}/${className?lower_case}/toEdit${className}?handle=add",function(){
		$("#editModal").modal({backdrop: 'static', keyboard: false});
	});
}

/**
 * 修改
 * @returns
 */
function edit(){
	var selected = $("#table").bootstrapTable("getSelections");
	if(selected.length!=1){
		bootbox.alert({ 
			  size: "small",
			  title: "提示框",
			  message: "请选择一个要编辑的记录"
			});
		return false;
	}
	$("#editModal").load(basepath+"${urlPrefix}/${className?lower_case}/toEdit${className}?handle=edit&recid="+selected[0].recid,function(){
		$("#editModal").modal({backdrop: 'static', keyboard: false});
	});
}

/**
 * 删除
 * @returns
 */
function remove(){
	var selected = $("#table").bootstrapTable("getSelections");
	if(selected.length<1){
		bootbox.alert({ 
			  size: "small",
			  title: "提示框",
			  message: "请先选择要删除的记录"
			});
		return false;
	}
	bootbox.confirm({
		title:"确认框",
	    message: "是否确认删除当前选中的记录？",
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
	    		   url:basepath+'${urlPrefix}/${className?lower_case}/delete${className}',
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