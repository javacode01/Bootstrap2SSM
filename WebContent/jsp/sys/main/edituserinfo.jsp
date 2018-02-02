<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">&times;</button>
			<h4 class="modal-title">修改用户信息</h4>
		</div>
		<div class="modal-body">
			<div class="form-group">
		        <input id="file-1" type="file" multiple class="file">
		    </div>
			<form role="form" id="addEditForm">
				<div class="form-group" style="display:none;">
					<label for="userId">用户ID</label>
					<input type="text" class="form-control" id="userId" name="userId" value="${user.userId}">
				</div>
				<div class="form-group">
					<label for="userCode">用户编码</label>
					<input type="text" class="form-control" id="userCode" name="userCode" value="${user.userCode}" readonly>
				</div>
				<div class="form-group">
					<label for="userName">用户名称</label>
					<input type="text" class="form-control" id="userName" name="userName" value="${user.userName}" readonly>
				</div>
				<div class="form-group">
					<label for="organName">组织机构</label>
					<input type="text" class="form-control" id="organName" name="organName" value="${user.organName}" readonly>
				</div>
				<div class="form-group">
					<label for="nickname">昵称</label>
					<input type="text" class="form-control" id="nickname" name="nickname" value="${user.nickname}">
				</div>
			</form>
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
	$(function(){
		$("#file-1").fileinput({
			language: 'zh', //设置语言
			enctype:'multipart/form-data',
	        uploadUrl: basepath+'sys/attachment/upload', //上传的地址
	        uploadExtraData:{
	        	"${_csrf.parameterName}":"${_csrf.token}",//因为SpringSecurity框架，有csrf跨域提交防御，所需需要设置这个值
	        	"module":"USER",
	        	"type":"USER",
	        	"businessid":userId},//参数
	        allowedFileExtensions: ["jpg", "gif", "png"],//接收的文件后缀
	        showPreview: true,              //是否显示预览区域
	        showBrowse:true,				//是否显示浏览按钮
	        showRemove:false,				//是否显示移除按钮
	        showUpload:false,				//是否显示上传按钮
	        autoReplace:true,				//是否自动替换当前图片，设置为true时，再次选择文件， 会将当前的文件替换掉
	        browseClass:"btn btn-primary", //按钮样式 
	        showCaption: false,             //显示文字表述  
	        uploadAsync:false,              //false 同步上传，后台用数组接收，true 异步上传，每次上传一个file,会调用多次接口
	        removeFromPreviewOnError:true, //当选择的文件不符合规则时，例如不是指定后缀文件、大小超出配置等，选择的文件不会出现在预览框中，只会显示错误信息  
	        maxFileCount:1, //表示允许同时上传的最大文件个数
	        maxFileSize: 1024,           //单位为kb，如果为0表示不限制文件大小  
	        previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
	        dropZoneEnabled:false,			//是否显示拖拽区域
	        overwriteInitial: true,  
	        initialPreviewAsData: true,  
	        initialPreview: [user_img]//这里配置需要初始展示的图片连接数组，字符串类型的，通常是通过后台获取后然后组装成数组直接赋给initialPreview就行了 
	    }).on("fileuploaded", function (event, data, previewId, index) {
	        	initUserImage();
	    });
	});
	function addEditSubmit(){
		if(!$('#addEditForm').valid()){
			return false;
		}
		//提交
		$.ajax({
			url:basepath+'sys/users/editUsers',
			type:'post',
			data:$("#addEditForm").serialize(),
			success:function(result){
				if(result.code=='success'){
					bootbox.alert({ 
						  size: "small",
						  title: "提示框",
						  message: "保存成功（将退出系统，需要重新登录）",
						  callback: function () {
							  logout();
						}});
					$('#mainModal').modal('hide');
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
</script>