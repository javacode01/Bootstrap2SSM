<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- jQuery 3 -->
<script src="${basepath}resource/bower_components/jquery/dist/jquery.min.js"></script>
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
<script src="${basepath}resource/js/sysutil.js"></script>

<script>
	var basepath = '${basepath}';
	$(function(){  
		//设置提示框语言
		bootbox.setLocale("zh_CN");
		//设置提示框按钮默认值
		bootbox.addLocale("zh_CN", {
		    OK : '确定',
		    CANCEL : '取消',
		    CONFIRM : '确定'
		});
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