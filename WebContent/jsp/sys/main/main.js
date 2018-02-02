/**
 * 主页初始化
 */
function init(){
	//初始化菜单
	$.ajax({
		type:'POST',
		url:basepath+'sys/functions/getUserMenus',
		dataType:'json',
		success:function(data){
			recursionAddModules(data.moduleList);
			addFunctions(data.functionList);
			addLinks(data.handleList);
			//设置iframe高度
//			$('#tabsContent').height(tabs.getIframeHeight());
//			$('#widgets_iframe').height($('#tabsContent').height());
		}
	});
	//初始化用户头像
	initUserImage();
}

function initUserImage(){
	$('#userImg1').attr("src",user_img);
	$('#userImg2').attr("src",user_img);
	$.ajax({
		url:basepath+'sys/attachment/getAttachmentPath/USER/'+userId+'/USER',
		type:'get',
		success:function(result){
			if(result.code=="success"){
				if(result.data.length>0){
					user_img = basepath+'sys/attachment/getImg/'+result.data[0].recid;
					$('#userImg1').attr("src",user_img);
					$('#userImg2').attr("src",user_img);
				}
			}
		}
	});
}
/**
 * 添加模块代码
 * @param moduleList
 */
function recursionAddModules(moduleList){
	var list = new Array();
	$.each(moduleList,function(index,module){
		var menu = '';
		if(module.parentCode=='root'){
			menu = '<li class="treeview"><a href="javascript:void(0);"><i class="'+module.functionIcon+'"></i> <span>'+module.functionName+'</span> <span class="pull-right-container">'
              +'<i class="fa fa-angle-left pull-right"></i></span></a>'
              +'<ul class="treeview-menu" id="'+module.functionCode+'"></ul></li>';
			$('#menu_tree').append(menu);
		}else if($('#'+module.parentCode).length>0){
			menu = '<li class="treeview"><a href="javascript:void(0);"><i class="'+module.functionIcon+'"></i> <span>'+module.functionName+'</span> <span class="pull-right-container">'
	            +'<i class="fa fa-angle-left pull-right"></i></span></a>'
	            +'<ul class="treeview-menu" id="'+module.functionCode+'"></ul></li>';
			$('#'+module.parentCode).append(menu);
		}else{
			list.push(module);
		}
	});
	if(list.length>0){
		recursionAddModules(list);
	}
}
/**
 * 添加功能代码
 * @param functionList
 */
function addFunctions(functionList){
	$.each(functionList,function(index,data){
		if($('#'+data.parentCode).length>0){
			var menu = '<li><a href="javascript:void(0);" id="'+data.functionCode+'" data-id="'+data.functionCode+'" data-name="'+data.functionName+'"><i class="'+data.functionIcon+'"></i>'+data.functionName+'</a></li>';
			$('#'+data.parentCode).append(menu);
		}
	});
}
/**
 * 添加链接代码
 * @param handleList
 */
function addLinks(handleList){
	$.each(handleList,function(index,data){
		if(data.defaultAction=='1'&&$('#'+data.parentCode).length>0){
			$('#'+data.parentCode).attr('data-url',basepath+data.functionUrl);
			$('#'+data.parentCode).click(tabs.addTab);
		}
	});
}
/**
 * 退出
 */
function logout(){
	$.ajax({
		url:basepath+'signout',
		async:false,
		type:'POST',
		success:function(data){
			window.location.href=basepath;
		},
		error:function(error){
			window.location.href=basepath;
		}
	});
}

/**
 * 修改密码
 * @returns
 */
function changePassword(){
	$("#mainModal").load(basepath+"sys/users/toChangePassword",function(){
		$("#mainModal").modal({backdrop: 'static', keyboard: false});
	});
}

/**
 * 修改用户信息
 * @returns
 */
function editUser(){
	$("#mainModal").load(basepath+"sys/users/toEditUserInfo",function(){
		$("#mainModal").modal({backdrop: 'static', keyboard: false});
	});
}
