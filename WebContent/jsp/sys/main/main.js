/**
 * 主页初始化
 */
function init(){
	//初始化用户头像
	initUserImage();
}

function initUserImage(){
//	$('#userImg1').attr("src",user_img);
	$('#userImg2').attr("src",user_img);
	$.ajax({
		url:basepath+'sys/attachment/getAttachmentPath/USER/'+userId+'/USER',
		type:'get',
		success:function(result){
			if(result.code=="success"){
				if(result.data.length>0){
					user_img = basepath+'sys/attachment/getImg/'+result.data[0].recid;
//					$('#userImg1').attr("src",user_img);
					$('#userImg2').attr("src",user_img);
				}
			}
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
