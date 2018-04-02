<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">&times;</button>
			<h4 class="modal-title">权限分配</h4>
		</div>
		<div class="modal-body" id="rolefuction" style="overflow: auto;">
			<div id="tree"></div>
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-primary" id="addEditButton" onclick="addEditSubmit()">保存</button>
			<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		</div>
	</div>
	<!-- /.modal-content -->
</div>
<!-- /.modal -->
<script type="text/javascript">
	var roleCode = "${roleCode}";
	$("#rolefuction").height($(window).height()-150);
	if(roleCode==""){
		bootbox.alert({ 
			  size: "small",
			  title: "提示框",
			  message: "参数错误"
			});
		$('#roleEdit').modal('hide');
	}
	$(function(){
		//初始化界面
		initTree();
		//展开功能树
		$('#tree').treeview('expandAll', {});
		//初始化选中节点
		initChecked();
	});
	//提交
	function addEditSubmit(){
		var checkeds = $('#tree').treeview('getChecked',[]);
		var functionCodes = new Array();
		$.each(checkeds,function(index,node){
			functionCodes.push(node.data.functionCode);
		});
		//提交
		$.ajax({
			url:basepath+'sys/roles/saveRoleFunction',
			type:'post',
			data:{roleCode:roleCode,functionCodes:functionCodes.join(",")},
			success:function(result){
				if(result.code=='success'){
					bootbox.alert({ 
						  size: "small",
						  title: "提示框",
						  message: "保存成功"
						});
					$('#roleEdit').modal('hide');
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
					})
			}
		});
	}
	/**
	 * 初始化树结构
	 * @returns
	 */
	function initTree(){
		$('#tree').treeview({
			data : getTree(),
			color:'#555',//设置列表树所有节点的前景色。
			levels:0,//Default: 2 设置继承树默认展开的级别。
			showBorder:false,//是否在节点上显示边框。
			showCheckbox:true,
			onNodeExpanded:function(event,data){
				var param = data.id
				if(data.nodes.length<=0){
					$.ajax({
						type:"post",
						async:false,
						url:basepath+"sys/functions/getFunctionNodes",
						dataType:'json',
						data: {functionCode:param},
						success:function(result){
							if(undefined!=result&&null!=result){
								for(var i=0;i<result.length;i++){
									var temp = result[i];
									$("#tree").treeview("addNode",[data.nodeId,{node:temp,silent:true}]);
								}
							}
						}
					});
				}
				$('#tree').treeview('expandAll', {});
			},
			onNodeChecked:function(event,data){
				//子节点全选
				if('undefined'!=typeof(data.nodes)&&null!=data.nodes&&data.nodes.length>0){
					$.each(data.nodes,function(index,node){
						$('#tree').treeview('checkNode',[node.nodeId]);
					});
				}
				//父节点递归选中
				if("undefined"!=typeof(data)&&null!=data){
					treenodecheck(data);
				}
			},
			onNodeUnchecked:function(event,data){
				//子节点取消选中
				if('undefined'!=typeof(data.nodes)&&null!=data.nodes&&data.nodes.length>0){
					$.each(data.nodes,function(index,node){
						$('#tree').treeview('uncheckNode',[node.nodeId]);
					});
				}
				//父节点递归取消选中
				if("undefined"!=typeof(data)&&null!=data){
					treenodeuncheck(data);
				}
			}
		});
	}
	function treenodecheck(data){
		var parentdata = $('#tree').treeview('getParent',[data.nodeId]);
		if("undefined"!=typeof(parentdata)&&null!=parentdata&&"undefined"!=typeof(parentdata.state)){
			$('#tree').treeview('checkNode',[parentdata.nodeId,{ silent: true }]);
			treenodecheck(parentdata);
		}
	}
	
	function treenodeuncheck(data){
		//获取同级节点
		var datas = $('#tree').treeview('getSiblings',[data.nodeId]);
		var f = true;
		if("undefined"!=typeof(datas)&&null!=datas){
			$.each(datas,function(index,node){
				if(node.state.checked){
					f = false;
				}
			});
		}
		if(f&&"undefined"!=typeof(data)&&null!=data){
			//父节点设为未选中
			var parentdata = $('#tree').treeview('getParent',[data.nodeId]);
			if("undefined"!=typeof(parentdata)&&null!=parentdata&&"undefined"!=typeof(parentdata.state)){
				$('#tree').treeview('uncheckNode',[parentdata.nodeId,{ silent: true }]);
				treenodeuncheck(parentdata);
			}
		}
	}
	/**
	 * 初始化选中节点
	 * @returns
	 */
	function initChecked(){
		$.ajax({
			url:basepath+'sys/roles/listRoleFunction',
			type:'post',
			data:{roleCode:roleCode},
			success:function(result){
				if(result.code=='success'){
					var list = result.data;
					//获取所有节点
					var nodes = $('#tree').treeview('getUnchecked',[]);
					$.each(nodes,function(index,node){
						$.each(list,function(index1,temp){
							if(node.data.functionCode==temp.functionCode){
								$('#tree').treeview('checkNode',[node.nodeId,{silent: true}]);
								return false;
							}
						});
					});
				}
			}
		});
	}
	/**
	 * 初始化功能树
	 * @returns
	 */
	function getTree(){
		//根节点
		var root = [{
			  id:"root",
			  text: "功能",
			  icon: "glyphicon glyphicon-stop",
			  selectedIcon: "glyphicon glyphicon-stop",
			  state: {
			    expanded: false
			  },
			  nodes:[],
			  data:{
				  functionCode:"root",
				  functionName:"功能",
				  functionLevel:"1",
				  functionIcon:"glyphicon glyphicon-stop",
				  functionUrl:"",
				  seq:""
			  }}];
		return root;
	}
</script>