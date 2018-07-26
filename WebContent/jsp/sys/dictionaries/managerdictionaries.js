/**
 * 初始化的方法
 * 
 * @returns
 */
function init() {
	initDicTable();
	initTable("");
}

/**
 * 初始化字典类型表格
 * @returns
 */
function initDicTable(){
	$('#dicTable').bootstrapTable({
		url:basepath+"sys/dictionaries/listDictionariesByPage",
        striped: false,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        queryParams: function(params){
        	return getDicParam(params);
        },									//传递参数（*）
        pagination:true,//启用分页
        sidePagination:"server",//在服务端分页
        sortable: false,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        pageNumber:1,//首页页码
        pageSize:8,//页面数据条数
        clickToSelect: true,                //是否启用点击选中行
        uniqueId: "recid",                     //每一行的唯一标识，一般为主键列
        toolbar: "#dicToolbar",                //工具按钮用哪个容器
        showHeader:false,
        showColumns:false,
        showRefresh:false,
        showToggle:false,
        toolbarAlign:'right',
        onClickRow:function(row){
        	$('#table').bootstrapTable('destroy');
			initTable(row.dicType);
        },
        columns: [{
        	radio:true
        },{
            field: 'dicName',
            title: '字典名称'
        }]
	});
}

/**
 * 数据字典分页查询获取查询条件
 * @param params
 * @returns
 */
function getDicParam(params){
	var filter = new HashMap();
	var dicSearch = $("#dicSearch").val();
	if(dicSearch){
		filter.put("dicName@like","%"+dicSearch+"%");
	}
	$.extend(params,{filter:filter.getJSON()});
	return params;
}

/**
 * 数据字典查询
 * @returns
 */
function searchDic(){
	$('#dicTable').bootstrapTable('refresh');
}

/**
 * 初始化表格
 * @returns
 */
function initTable(dicType){
	$('#table').bootstrapTable({
		url:basepath+"sys/dictionariesitem/listDictionariesItemByPage",
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        queryParams: function(params){
        	return getParam(params,dicType);
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
            field: 'dicType',
            title: '字典类型',
            width:"20%"
        }, {
            field: 'itemCode',
            title: '字典项编码',
            width:"20%"
        }, {
            field: 'itemName',
            title: '字典项名称',
            width:"20%"
        }, {
            field: 'note',
            title: '备注',
            width:"30%"
        }]
	});
}

/**
 * 分页查询获取查询条件
 * @param params
 * @returns
 */
function getParam(params,dicType){
	if(null==dicType||""==dicType){
		return false;
	}
	var filter = new HashMap();
	filter.put("dicType@=",dicType);
	$.extend(params,{filter:filter.getJSON()});
	return params;
}

/**
 * 字典新增
 * @returns
 */
function addDic(){
	$("#dicEdit").load(basepath+"sys/dictionaries/toEditDic?handle=add",function(){
		$("#dicEdit").modal({backdrop: 'static', keyboard: false});
	});
}

/**
 * 字典修改
 * @returns
 */
function editDic(){
	var selected = $("#dicTable").bootstrapTable('getSelections');
	if(selected.length<1){
		PluginUtil.info("请先选择要编辑的字典");
		return false;
	}
	$("#dicEdit").load(basepath+"sys/dictionaries/toEditDic?handle=edit&recid="+selected[0].recid,function(){
		$("#dicEdit").modal({backdrop: 'static', keyboard: false});
	});
}

/**
 * 字典删除
 * @returns
 */
function removeDic(){
	var selected = $("#dicTable").bootstrapTable('getSelections');
	if(selected.length<1){
		PluginUtil.info("请先选择要删除的字典");
		return false;
	}
	PluginUtil.confirm("是否确认删除当前选中的字典？",function(){
		PluginUtil.mask("body");
		$.ajax({
 		   url:basepath+'sys/dictionaries/deleteDic',
 		   type:'post',
 		   data:{recid:selected[0].recid},
 		   success:function(result){
 			   PluginUtil.unmask("body");
 			   if(result.code=='success'){
 				   PluginUtil.info("删除成功");
 				   $('#dicTable').bootstrapTable('refresh');
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
 * 刷新字典缓存
 * @returns
 */
function refreshDictionaries(){
	PluginUtil.confirm("新增、修改、删除字典项后，才有必要刷新缓存，是否继续？",function(){
		PluginUtil.mask("body");
		//提交
 	   $.ajax({
 		   url:basepath+'sys/dictionaries/refreshDictionaries',
 		   type:'post',
 		   success:function(result){
 			   PluginUtil.unmask("body");
 			   if(result.code=='success'){
 				   	PluginUtil.info("刷新成功");
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
 * 字典项新增
 * @returns
 */
function addDicItem(){
	var selected = $("#dicTable").bootstrapTable('getSelections');
	if(selected.length<1){
		PluginUtil.info("请先选择左侧要增加字典项的字典");
		return false;
	}
	$("#dicItemEdit").load(basepath+"sys/dictionariesitem/toEditDicItem?handle=add",function(){
		$("#dicItemEdit").modal({backdrop: 'static', keyboard: false});
	});
}

/**
 * 字典项修改
 * @returns
 */
function editDicItem(){
	var selected = $("#table").bootstrapTable("getSelections");
	if(selected.length!=1){
		PluginUtil.info("请选择一条要编辑的字典项");
		return false;
	}
	$("#dicItemEdit").load(basepath+"sys/dictionariesitem/toEditDicItem?handle=edit&recid="+selected[0].recid,function(){
		$("#dicItemEdit").modal({backdrop: 'static', keyboard: false});
	});
}

/**
 * 字典项删除
 * @returns
 */
function removeDicItem(){
	var selected = $("#table").bootstrapTable("getSelections");
	if(selected.length<1){
		PluginUtil.info("请选择要删除的字典项");
		return false;
	}
	PluginUtil.confirm("是否确认删除当前选中的字典？",function(){
		var recids = new Array();
 	   $.each(selected,function(index,data){
 		   recids.push(data.recid);
 	   })
 	   PluginUtil.mask("body");
 	   //提交
 	   $.ajax({
 		   url:basepath+'sys/dictionariesitem/deleteDicItems',
 		   type:'post',
 		   data:{recids:recids.join(",")},
 		   success:function(result){
 			   PluginUtil.unmask("body");
 			   if(result.code=='success'){
 				   PluginUtil.info("删除成功");
 				   $('#table').bootstrapTable('refresh');
 			   }else{
 				   	PluginUtil.alert(result.data);
 			   }
 		  },
 		  error:function(error){
 			  PluginUtil.unmask("body");
 			  PluginUtil.alert(result.data);
 		  }
 	   });
	});
}