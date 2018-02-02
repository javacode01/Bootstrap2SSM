<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- jQuery 3 -->
<script src="${basepath}resource/bower_components/jquery/dist/jquery.min.js"></script>
<!-- jQuery UI 1.11.4 -->
<script src="${basepath}resource/bower_components/jquery-ui/jquery-ui.min.js"></script>
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<script>
  $.widget.bridge('uibutton', $.ui.button);
</script>
<!-- Bootstrap 3.3.7 -->
<script src="${basepath}resource/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- Slimscroll -->
<script src="${basepath}resource/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- AdminLTE App -->
<script src="${basepath}resource/dist/js/adminlte.min.js"></script>
<!-- AdminLTE dashboard demo (This is only for demo purposes)
<script src="${basepath}resource/dist/js/pages/dashboard.js"></script> -->
<!-- AdminLTE for demo purposes -->
<script src="${basepath}resource/dist/js/demo.js"></script>
<script type="text/javascript" src="${basepath}resource/js/md5.js"></script>
<script src="${basepath}resource/js/bootbox.min.js"></script>
<script src="${basepath}resource/js/jquery.validate.min.js"></script>
<script src="${basepath}resource/js/messages_zh.min.js"></script>
<!-- bootstrap-fileinput图片上传 -->
<script src="${basepath}resource/bootstrap-fileinput/js/plugins/sortable.js" type="text/javascript"></script>
<script src="${basepath}resource/bootstrap-fileinput/js/fileinput.js" type="text/javascript"></script>
<script src="${basepath}resource/bootstrap-fileinput/js/locales/zh.js" type="text/javascript"></script>
<script src="${basepath}resource/bootstrap-fileinput/themes/explorer-fa/theme.js" type="text/javascript"></script>
<script src="${basepath}resource/bootstrap-fileinput/themes/fa/theme.js" type="text/javascript"></script>

<script>
	$(function(){  
	    var token = $("meta[name='_csrf']").attr("content");  
	    var header = $("meta[name='_csrf_header']").attr("content");  
	    $.ajaxSetup({
	    	beforeSend: function (xhr) {xhr.setRequestHeader(header, token);}
	    });   
	});
</script>