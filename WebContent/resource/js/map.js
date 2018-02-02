function HashMap() {
	this.map = {};
}

HashMap.prototype = {
	put : function(key, value) {
		if(null!=key&&undefined!=key){
			this.map[key] = value;
		}
	},
	get : function(key) {
		if (this.map.hasOwnProperty(key)) {
			return this.map[key];
		}
		return null;
	},
	remove : function(key) {
		if (this.map.hasOwnProperty(key)) {
			return delete this.map[key];
		}
		return false;
	},
	removeAll : function() {
		this.map = {};
	},
	keySet : function() {
		var _keys = [];
		for ( var i in this.map) {
			_keys.push(i);
		}
		return _keys;
	},
	getJSON:function(){
		return JSON.stringify(this.map);
	},
	getSize:function(){
		var size=0;
		for ( var i in this.map) {
			size=size+1;
		}
		return size;
	}
};

HashMap.prototype.constructor = HashMap;