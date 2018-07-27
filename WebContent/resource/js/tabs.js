var tabs = (function(){
	var obj = new Object();
	/**
	 * 增加tab页
	 */
	obj.addTab = function(event){
		var data_id = $(event.target).attr("data-id");
		//校验tab是否存在
		if($('#tab_'+data_id).length>0){
			$('#tab_'+data_id).tab("show");
		}else{
			var data_name = $(event.target).attr("data-name");
			var data_url = $(event.target).attr("data-url");
			var tab_li = '<li data-id="'+data_id+'"><a id="tab_'+data_id+'" data-id="'+data_id+'" href="#'+data_id+'_div" data-toggle="tab">'+data_name+'&nbsp;<span onclick="tabs.removeTab()" class="fa fa-times" style="font-size:10px;"></span></a></li>';
			$("#tabsMenu").append(tab_li);
			var tab_div = '<div class="tab-pane fade" id="'+data_id+'_div" style="width:100%;height:100%;">'
		        	+'<iframe src="'+data_url+'" id="'+data_id+'_iframe" frameborder="0" scrolling="auto"'
		      		+'style="width:100%;height:100%;margin:0px;padding:0px;"></iframe>'
		      		+'</div>';
			$('#tabsContent').append(tab_div);
			$('#tab_'+data_id).tab("show");
//			$('#'+data_id+'_iframe').height($('#tabsContent').height());
		}
		obj.position($('#tabsMenu .active'));
	};
	/**
	 * 删除tab页
	 */
	obj.removeTab = function(){
		//获取当前选中tab页
		var cur_data_id = $('#tabsMenu .active').attr("data-id");
		var data_id = $(event.target).parent().attr("data-id");
		//删除tab列表标签
		$('#tab_'+data_id).remove();
		//删除tab内容区域
		$('#'+data_id+'_div').remove();
		if(cur_data_id==data_id){
			$('#tab_widgets').tab("show");//展示首页
		}
		obj.position($('#tabsMenu .active'));
	};
	/**
	 * 上一个标签
	 */
	obj.prev = function(){
		//获取当前选中标签
		var cur_tab = $('#tabsMenu .active');
		var prev_tab = cur_tab.prev();
		if(prev_tab.length>0){
			var prev_tab_data_id = prev_tab.attr("data-id");
			$('#tab_'+prev_tab_data_id).tab("show");
			obj.position(prev_tab);
		}
	};
	/**
	 * 下一个标签
	 */
	obj.next = function(){
		//获取当前选中标签
		var cur_tab = $('#tabsMenu .active');
		var next_tab = cur_tab.next();
		if(next_tab.length>0){
			var next_tab_data_id = next_tab.attr("data-id");
			$('#tab_'+next_tab_data_id).tab("show");
			obj.position(next_tab);
		}
	}
	obj.position = function(tab){
		//获取滚动条位置
		var cur_scroll = $('#tabsMenu').scrollTop();
//		console.log("当前滚动条位置："+cur_scroll);
//		console.log("当前tab相对坐标："+tab.position().top+":"+tab.position().left);
//		console.log("当前tab绝对坐标："+tab.offset().top+":"+tab.offset().left);
		$('#tabsMenu').animate({
            scrollTop: (cur_scroll+tab.position().top)
        }, 500);
	};
	obj.getIframeHeight = function(){
		var content_wrapper_height = $('#content_wrapper').height();
		console.log("content_wrapper高度="+content_wrapper_height);
		console.log("当前iframe高度="+(content_wrapper_height-44));
		return (content_wrapper_height-44);
	}
	return obj;
}());