/**
 * 系统弹出框、遮罩层等插件统一处理工具
 **/
var PluginUtil = (function(){
	var obj = {};
	/**
	 * 信息提示框
	 */
	obj.info = function(message){
		toastr.info(message);
	};
	/**
	 * 警告提示框
	 */
	obj.alert = function(message){
		bootbox.alert({ 
			  size: "small",
			  title: "<font color='#EFAD4F'>警告</font>",
			  message: message
			});
	};
	/**
	 * 确认对话框
	 */
	obj.confirm = function(message,callback){
		if(typeof callback !== "function"){
			console.log("请传入回调函数");
			return;
		}
		bootbox.confirm({
			title:"确认框",
		    message: message,
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
		    callback: function(result){
		    	if(result){
		    		callback();
		    	}
		    }
		});
	};
	/**
	 * 显示遮罩层
	 */
	obj.mask = function(elementId){
		if(elementId=='body'){
			$.busyLoadFull("show",{
				fontawesome: "fa fa-spinner fa-spin fa-3x fa-fw"
	    	});
		}else{
			$('#'+elementId).busyLoad("show",{
				fontawesome: "fa fa-spinner fa-spin fa-3x fa-fw"
			});
		}
	};
	/**
	 * 隐藏遮罩层
	 */
	obj.unmask = function(elementId){
		if(elementId=='body'){
			 $.busyLoadFull("hide");
		}else{
			$('#'+elementId).busyLoad("hide");
		}
	};
	return obj;
})();