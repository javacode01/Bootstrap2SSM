/**
 * 初始化的方法
 * 
 * @returns
 */
function init() {
	initTree();
	initTable("");
}

/**
 * 初始化结构树
 */
function initTree(){
	zTreeObj = $.fn.zTree.init($("#tree"), setting, zNodes);
}

/**
 * 初始化表格
 * @returns
 */
function initTable(organCode){
	$('#table').bootstrapTable({
		url:basepath+"sys/organ/listOrgans",
//        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        queryParams: function(params){
        	return getParam(params,organCode);
        },									//传递参数（*）
        clickToSelect: true,                //是否启用点击选中行
        uniqueId: "organId",                     //每一行的唯一标识，一般为主键列
        columns: [{
            field: 'iconUrl',
            title: '图标',
            formatter:toShowIcon
        }, {
            field: 'organCode',
            title: '机构编码'
        }, {
            field: 'organName',
            title: '机构名称'
        }, {
            field: 'organLevel',
            title: '机构级别',
            formatter:function(value,row,index){
            	return SysUtil.formatDicItem(value,ORGANLEVEL);
            }
        }]
	});
}

function getParam(params,organCode){
	if(""==organCode){
		return false;
	}
	var filter = new HashMap();
	filter.put("parentCode@=",organCode);
	$.extend(params,{filter:filter.getJSON()});
	return params;
}

function toShowIcon(value,row,index){
	return '<span class="'+value+'"></span>';
}

/**
 * 编辑功能
 * @returns
 */
function edit(treeId, treeNode){
	if('root'==treeNode.data.organCode){
		PluginUtil.info("根节点不能编辑");
		return false;
	}
	zTreeObj.selectNode(treeNode);
	$("#organModal").load(basepath+"sys/organ/toEditOrgan",{
		organCode:treeNode.data.organCode,
		handle:'edit'
	},function(){
		$("#organModal").modal({backdrop: 'static', keyboard: false});
	});
	return false;
}

/**
 * 返回允许创建的下级列表
 * @param level //当前级别
 * @param items //级别关系列表
 * @returns
 */
function nextLevel(level,items){
	if("root"==level){
		return items;
	}
	//获取当前级别信息
	var currLevel = null;
	for(var i=0;i<items.length;i++){
		if(items[i].itemCode==level){
			currLevel = items[i];
			break;
		}
	}
	//获取下一级别列表
	var nextLevel = new Array();
	if(undefined!=currLevel.nextLevel&&""!=currLevel.nextLevel){
		var nextCode = currLevel.nextLevel.split(",");
		for(var i=0;i<nextCode.length;i++){
			for(var j=0;j<items.length;j++){
				if(nextCode[i]==items[j].itemCode){
					nextLevel.push(items[j]);
					break;
				}
			}
		}
	}
	return nextLevel;
}

/**
 * 新增下级功能
 * @returns
 */
function add(treeId, treeNode){
	if(nextLevel(treeNode.data.organLevel,ORGANLEVEL).length<1){
		PluginUtil.info("不允许创建下级");
		return false;
	}
	zTreeObj.selectNode(treeNode);
	$("#organModal").load(basepath+"sys/organ/toEditOrgan",{
		organCode:treeNode.data.organCode,
		handle:'add'
	},function(){
		$("#organModal").modal({backdrop: 'static', keyboard: false});
	});
}

/**
 * 删除功能及其子功能
 * @returns
 */
function remove(treeId, treeNode){
	if('root'==treeNode.data.organCode){
		PluginUtil.info("根节点不能编辑");
		return false;
	}
	zTreeObj.selectNode(treeNode);
	PluginUtil.confirm("是否确认删除当前选中的机构及其下级机构？",function(){
		PluginUtil.mask("body");
		//提交
 	   $.ajax({
 		   url:basepath+'sys/organ/deleteOrgan',
 		   type:'post',
 		   data:{organId:treeNode.data.organId},
 		   success:function(result){
 			   PluginUtil.unmask("body");
 			   if(result.code=='success'){
 				   PluginUtil.info("删除成功");
 				   var selected = zTreeObj.getSelectedNodes();
 				   zTreeObj.removeNode(selected[0]);
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
	return false;
}

/**
 * 显示节点详情
 * @returns
 */
function showDetail(event, treeId, treeNode){
	$('#table').bootstrapTable('destroy');
	initTable(treeNode.id);
	if("root"!=treeNode.id){
		$.ajax({
			   url:basepath+'sys/organ/getOrganDetail/'+treeNode.id,
			   type:'post',
			   success:function(result){
				   if(result.code=='success'){
					   $('#show_organCode').html(result.data.organCode);
					   $('#show_organName').html(result.data.organName);
					   $('#show_organLevel').html(SysUtil.formatDicItem(result.data.organLevel,ORGANLEVEL));
					   $('#show_iconUrl').removeClass();
					   $('#show_iconUrl').addClass(result.data.iconUrl);
					   $('#show_seq').html(result.data.seq);
				   }
			  },
			  error:function(error){
				  PluginUtil.alert(error);
			  }
		});
	}else{
		$('#show_organCode').html(treeNode.data.organCode);
		$('#show_organName').html(treeNode.data.organName);
		$('#show_organLevel').html(SysUtil.formatDicItem(treeNode.data.organLevel,ORGANLEVEL));
		$('#show_iconUrl').removeClass();
		$('#show_iconUrl').addClass(treeNode.data.iconUrl);
		$('#show_seq').html(treeNode.data.seq);
	}
}