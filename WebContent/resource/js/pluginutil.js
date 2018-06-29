/**
 * 系统弹出框、遮罩层等插件统一处理工具
 **/
var PluginUtil = (function(){
	var obj = {};
	/**
	 * 信息提示框
	 */
	obj.info = function(message){
		layer.msg(message);
	};
	/**
	 * 警告提示框
	 */
	obj.alert = function(message){
		bootbox.alert({ 
			  size: "small",
			  title: "<font color='#D9544F'>警告</font>",
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
		layer.load();
	};
	/**
	 * 隐藏遮罩层
	 */
	obj.unmask = function(elementId){
		layer.closeAll('loading');
	};
	return obj;
})();