<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- jQuery 3 -->
<script src="${basepath}resource/bower_components/jquery/dist/jquery.min.js"></script>
<!-- 页面加载进度 -->
<script src="${basepath}resource/bower_components/PACE/pace.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="${basepath}resource/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- Bootstrap Table -->
<script src="${basepath}resource/js/bootstrap-table.js"></script>
<!-- Bootstrap Table Locale -->
<script src="${basepath}resource/js/bootstrap-table-zh-CN.js"></script>
<script src="${basepath}resource/js/map.js"></script>
<script src="${basepath}resource/js/jquery.validate.min.js"></script>
<script src="${basepath}resource/js/messages_zh.min.js"></script>
<script src="${basepath}resource/js/bootbox.min.js"></script>
<script src="${basepath}resource/buzy-load/loading.min.js"></script>
<script src="${basepath}resource/toastr/toastr.js"></script>
<script src="${basepath}resource/js/pluginutil.js"></script>
<script src="${basepath}resource/js/sysutil.js"></script>

<script>
	var basepath = '${basepath}';
	$(function(){
		//设置进度条
		paceOptions = {
			ajax: false, // disabled
			document: true, // enabled
			eventLag: false, // disabled
			//elements: {
			  //	selectors: ['.my-page']
			//}
		};
		//设置提示框语言
		bootbox.setLocale("zh_CN");
		//设置提示框按钮默认值
		bootbox.addLocale("zh_CN", {
		    OK : '确定',
		    CANCEL : '取消',
		    CONFIRM : '确定'
		});
		//设置toastr默认值
		toastr.options = {  
	        closeButton: false,//是否显示关闭按钮（提示框右上角关闭按钮）
	        debug: false,//是否为调试
	        progressBar: false,//是否显示进度条（设置关闭的超时时间进度条）
	        positionClass: "toast-top-center",//消息框在页面显示的位置  
	        onclick: null,//点击消息框自定义事件 
	        showDuration: "300",//显示动作时间 
	        hideDuration: "1000",//隐藏动作时间
	        timeOut: "2000",//自动关闭超时时间
	        extendedTimeOut: "1000",  
	        showEasing: "swing",  
	        hideEasing: "linear",  
	        showMethod: "fadeIn",//显示的方式，和jquery相同 
	        hideMethod: "fadeOut"//隐藏的方式，和jquery相同
	    };
	    var token = $("meta[name='_csrf']").attr("content");  
	    var header = $("meta[name='_csrf_header']").attr("content");  
	    $.ajaxSetup({
	    	beforeSend: function (xhr) {xhr.setRequestHeader(header, token);},
	    	statusCode: {
	    		401:function(){
	    				bootbox.alert({ 
	    				  size: "small",
	    				  title: "错误",
	    				  message: "操作失败,未登录或登录超时"
	    				});
	    		  	},
	   		  	403:function(){
			   		  	bootbox.alert({ 
		  				  size: "small",
		  				  title: "错误",
		  				  message: "<font color='red'>操作失败,您无权限执行此操作</font>"
		  				});
	       		  	},  
	  		  	404:function(){
		  		  		bootbox.alert({ 
		  				  size: "small",
		  				  title: "错误",
		  				  message: "操作失败,请求未找到"
		  				});
	      		  	}, 
	    		408:function(){
	    				bootbox.alert({ 
		  				  size: "small",
		  				  title: "错误",
		  				  message: "操作失败,请求超时"
		  				});
	      		  	}, 
	      		500:function(){
	      				bootbox.alert({ 
		  				  size: "small",
		  				  title: "错误",
		  				  message: "操作失败,服务器系统内部错误"
		  				});
	        		}
	    	}
	    });
	});
</script>