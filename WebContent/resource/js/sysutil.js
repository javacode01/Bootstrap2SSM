var SysUtil = (function(){
	var obj = new Object();
	/**
	 * 字典项值转换
	 */
	obj.formatDicItem = function(value,items){
		if(!value){
			return "";
		}
		if(!items){
			return value;
		}
		for(var i=0;i<items.length;i++){
			if(value==items[i].itemCode){
				return items[i].itemName;
			}
		}
		
	};
	obj.initSelect = function(id,items,defaultValue){
		if($("#"+id).length<1){
			return false;
		}
		if(!items){
			return false;
		}
		$("#"+id).append("<option value=''></option>")
		for(var i=0;i<items.length;i++){
			if(items[i].itemCode==defaultValue){
				$("#"+id).append("<option value='"+items[i].itemCode+"' selected='selected'>"+items[i].itemName+"</option>");
			}else{
				$("#"+id).append("<option value='"+items[i].itemCode+"'>"+items[i].itemName+"</option>");
			}
		}
	}
	return obj;
})();