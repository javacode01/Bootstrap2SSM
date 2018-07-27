<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/sys/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
  	<%@ include file="/jsp/sys/include/main_header.jsp"%>
  	<link rel="stylesheet" href="${basepath}resource/css/tabs.css">
  	<style>
		.error{
			color:red;
		}
		.spinner {
			text-align: center;
			padding-top:20%;
		}
		.spinner > div {
			width: 30px;
			height: 30px;
		  	background-color: #67CF22;
			
		  	border-radius: 100%;
		  	display: inline-block;
		  	-webkit-animation: bouncedelay 1.4s infinite ease-in-out;
		  	animation: bouncedelay 1.4s infinite ease-in-out;
		  	/* Prevent first frame from flickering when animation starts */
		  	-webkit-animation-fill-mode: both;
		  	animation-fill-mode: both;
		}
		 
		.spinner .bounce1 {
		  	-webkit-animation-delay: -0.32s;
		  	animation-delay: -0.32s;
		}
		 
		.spinner .bounce2 {
			-webkit-animation-delay: -0.16s;
		  	animation-delay: -0.16s;
		}
		 
		@-webkit-keyframes bouncedelay {
		  	0%, 80%, 100% { -webkit-transform: scale(0.0) }
		  	40% { -webkit-transform: scale(1.0) }
		}
		 
		@keyframes bouncedelay {
		  	0%, 80%, 100% {
		    	transform: scale(0.0);
		    	-webkit-transform: scale(0.0);
		  	} 
		  	40% {
		    	transform: scale(1.0);
		    	-webkit-transform: scale(1.0);
		  	}
		}
	</style>
</head>
<body class="hold-transition skin-blue sidebar-mini fixed">
<!-- 页面内容开始 -->
<div class="wrapper">
  <!-- 顶部区域开始 -->
  <header class="main-header">
    <!-- logo区域 -->
    <a href="javascript:void(0);" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><%=BspUtils.getSysShortName() %></span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><%=BspUtils.getSysName() %></span>
    </a>
    <!-- logo区域结束 -->
    <!-- 右边顶部开始 -->
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
      <!-- 菜单按钮开始Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
        <span class="sr-only">切换菜单</span>
      </a>
	  <!-- 菜单按钮结束-->
	  <!-- 功能区开始 -->
      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          <!-- Messages: style can be found in dropdown.less-->
          <li class="dropdown messages-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <i class="fa fa-envelope-o"></i>
              <span class="label label-success">4</span>
            </a>
            <ul class="dropdown-menu">
              <li class="header">你有4条信息</li>
              <li>
                <!-- inner menu: contains the actual data -->
                <ul class="menu">
                  <li><!-- start message -->
                    <a href="#">
                      <div class="pull-left">
                        <img src="${basepath}resource/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                      </div>
                      <h4>
                        Support Team
                        <small><i class="fa fa-clock-o"></i> 5 mins</small>
                      </h4>
                      <p>Why not buy a new awesome theme?</p>
                    </a>
                  </li>
                  <!-- end message -->
                  <li>
                    <a href="#">
                      <div class="pull-left">
                        <img src="${basepath}resource/dist/img/user3-128x128.jpg" class="img-circle" alt="User Image">
                      </div>
                      <h4>
                        AdminLTE Design Team
                        <small><i class="fa fa-clock-o"></i> 2 hours</small>
                      </h4>
                      <p>Why not buy a new awesome theme?</p>
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <div class="pull-left">
                        <img src="${basepath}resource/dist/img/user4-128x128.jpg" class="img-circle" alt="User Image">
                      </div>
                      <h4>
                        Developers
                        <small><i class="fa fa-clock-o"></i> Today</small>
                      </h4>
                      <p>Why not buy a new awesome theme?</p>
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <div class="pull-left">
                        <img src="${basepath}resource/dist/img/user3-128x128.jpg" class="img-circle" alt="User Image">
                      </div>
                      <h4>
                        Sales Department
                        <small><i class="fa fa-clock-o"></i> Yesterday</small>
                      </h4>
                      <p>Why not buy a new awesome theme?</p>
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <div class="pull-left">
                        <img src="${basepath}resource/dist/img/user4-128x128.jpg" class="img-circle" alt="User Image">
                      </div>
                      <h4>
                        Reviewers
                        <small><i class="fa fa-clock-o"></i> 2 days</small>
                      </h4>
                      <p>Why not buy a new awesome theme?</p>
                    </a>
                  </li>
                </ul>
              </li>
              <li class="footer"><a href="#">See All Messages</a></li>
            </ul>
          </li>
          <!-- Notifications: style can be found in dropdown.less -->
          <li class="dropdown notifications-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <i class="fa fa-bell-o"></i>
              <span class="label label-warning">10</span>
            </a>
            <ul class="dropdown-menu">
              <li class="header">You have 10 notifications</li>
              <li>
                <!-- inner menu: contains the actual data -->
                <ul class="menu">
                  <li>
                    <a href="#">
                      <i class="fa fa-users text-aqua"></i> 5 new members joined today
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <i class="fa fa-warning text-yellow"></i> Very long description here that may not fit into the
                      page and may cause design problems
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <i class="fa fa-users text-red"></i> 5 new members joined
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <i class="fa fa-shopping-cart text-green"></i> 25 sales made
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <i class="fa fa-user text-red"></i> You changed your username
                    </a>
                  </li>
                </ul>
              </li>
              <li class="footer"><a href="#">View all</a></li>
            </ul>
          </li>
          <!-- Tasks: style can be found in dropdown.less -->
          <li class="dropdown tasks-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <i class="fa fa-flag-o"></i>
              <span class="label label-danger">9</span>
            </a>
            <ul class="dropdown-menu">
              <li class="header">You have 9 tasks</li>
              <li>
                <!-- inner menu: contains the actual data -->
                <ul class="menu">
                  <li><!-- Task item -->
                    <a href="#">
                      <h3>
                        Design some buttons
                        <small class="pull-right">20%</small>
                      </h3>
                      <div class="progress xs">
                        <div class="progress-bar progress-bar-aqua" style="width: 20%" role="progressbar"
                             aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                          <span class="sr-only">20% Complete</span>
                        </div>
                      </div>
                    </a>
                  </li>
                  <!-- end task item -->
                  <li><!-- Task item -->
                    <a href="#">
                      <h3>
                        Create a nice theme
                        <small class="pull-right">40%</small>
                      </h3>
                      <div class="progress xs">
                        <div class="progress-bar progress-bar-green" style="width: 40%" role="progressbar"
                             aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                          <span class="sr-only">40% Complete</span>
                        </div>
                      </div>
                    </a>
                  </li>
                  <!-- end task item -->
                  <li><!-- Task item -->
                    <a href="#">
                      <h3>
                        Some task I need to do
                        <small class="pull-right">60%</small>
                      </h3>
                      <div class="progress xs">
                        <div class="progress-bar progress-bar-red" style="width: 60%" role="progressbar"
                             aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                          <span class="sr-only">60% Complete</span>
                        </div>
                      </div>
                    </a>
                  </li>
                  <!-- end task item -->
                  <li><!-- Task item -->
                    <a href="#">
                      <h3>
                        Make beautiful transitions
                        <small class="pull-right">80%</small>
                      </h3>
                      <div class="progress xs">
                        <div class="progress-bar progress-bar-yellow" style="width: 80%" role="progressbar"
                             aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                          <span class="sr-only">80% Complete</span>
                        </div>
                      </div>
                    </a>
                  </li>
                  <!-- end task item -->
                </ul>
              </li>
              <li class="footer">
                <a href="#">View all tasks</a>
              </li>
            </ul>
          </li>
          <!-- User Account: style can be found in dropdown.less -->
          <li class="dropdown user user-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <img id="userImg2" src="javascript:void(0)" class="user-image">
              <span class="hidden-xs"><%=BspUtils.getLoginUser().getNickname()%></span>
            </a>
            <ul class="dropdown-menu" style="left:0px;right:0px;z-index:400;">
            	<li  role="presentation">
		            <a role="menuitem" tabindex="-1" href="javascript:void(0);" onclick="changePassword()">修改密码</a>
		        </li>
		        <li  role="presentation">
		            <a role="menuitem" tabindex="-1" href="javascript:void(0);" onclick="editUser()">修改用户信息</a>
		        </li>
		        <li  role="presentation">
		            <a role="menuitem" tabindex="-1" href="javascript:void(0);" onclick="logout()">退出</a>
		        </li>
            </ul>
          </li>
          <!-- 系统设置开始 -->
          <!-- Control Sidebar Toggle Button -->
          <li>
            <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
          </li>
          <!-- 系统设置结束 -->
        </ul>
      </div>
      <!-- 功能区结束 -->
    </nav>
    <!-- 右边顶部结束 -->
  </header>
  <!-- 顶部区域结束 -->
  <!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- Sidebar user panel -->
      <!-- 
      <div class="user-panel">
        <div class="pull-left image">
          <img id="userImg1" src="javascript:void(0)" class="img-circle">
        </div>
        <div class="pull-left info">
          <p><%=BspUtils.getLoginUser().getNickname()%></p>
          <a href="javascript:void(0);"><%=BspUtils.getLoginOrgan()==null?"未知":BspUtils.getLoginOrgan().getOrganName()%></a>
        </div>
      </div>
       -->
      <!-- sidebar menu: : style can be found in sidebar.less -->
      <!-- 菜单加载使用自定义标签 -->
      <systag:menu id="menu_tree" path="${basepath}"/>
    </section>
    <!-- /.sidebar -->
  </aside>
  <!-- Content Wrapper. Contains page content -->
  <div id="content_wrapper" class="content-wrapper" style="position:relative;background-color:#FFFFFF;">
  	<div class="sys-border-bottom" style="position:relative;margin-top:-1px;padding:0px;overflow:auto;">
  		<div style="position:absolute;left:0px;z-index:600;width:40px;height:42px;"><button onclick="tabs.prev()" type="button" class="btn" style="width:100%;height:100%;border-radius: 0px;"><span class="glyphicon glyphicon-backward"></span></button></div>
  		<div style="position:absolute;right:0px;z-index:600;width:40px;height:42px;"><button onclick="tabs.next()" type="button" class="btn" style="width:100%;height:100%;border-radius: 0px;"><span class="glyphicon glyphicon-forward"></span></button></div>
  		<ul id="tabsMenu" class="nav nav-tabs pre-scrollable" style="position:relative;z-index:600; height:42px;background-color:#EEEEEE;margin:0px 40px;">
		    <li data-id="widgets" class="active"><a id="tab_widgets" data-id="widgets" href="#widgets_div" data-toggle="tab">首页</a></li>
		</ul>
  	</div>
  	<!-- 页面动画加载 -->
  	<div class="spinner" style="position:absolute;top:93px;bottom:0px;z-index:0;width:100%;">
  		<div class="bounce1"></div>
		<div class="bounce2"></div>
		<div class="bounce3"></div>
	</div>
  	<div id="tabsContent" class="tab-content" style="position:absolute;top:93px;bottom:0px;z-index:900;width:100%;">
	    <div class="tab-pane fade in active" id="widgets_div" style="width:100%;height:100%;">
	        <iframe src="${basepath}/toWidgets" id="widgets_iframe" frameborder="0" scrolling="auto"
      		style="width:100%;height:100%;margin:0px;padding:0px;"></iframe>
	    </div>
	</div>
  </div>
  <!-- /.content-wrapper -->
  <footer class="main-footer" style="font-size:10px;background-color:#eee;padding:2px 15px;text-align: center;">
    <div class="pull-right hidden-xs">
      <b>版本</b> 1.0
    </div>
    <strong>Copyright &copy; <a href="#">Bootstrap2SSM</a>。</strong> 版权所有。
  </footer>

  <!-- Control Sidebar -->
  <aside class="control-sidebar control-sidebar-dark">
    <!-- Create the tabs -->
    <ul id="theme_setting" class="nav nav-tabs nav-justified control-sidebar-tabs">
    </ul>
    <!-- Tab panes -->
    <div id="theme_setting_content" class="tab-content">
    </div>
  </aside>
  <!-- /.control-sidebar -->
  <!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
  <div class="control-sidebar-bg"></div>
</div>
<!-- 模态框（Modal） -->
<div class="modal fade" id="mainModal" tabindex="-1" role="dialog" aria-hidden="true">
</div>
<!-- 页面内容结束 -->
<!-- javascript开始 -->
<%@ include file="/jsp/sys/include/main_footer.jsp"%>
<script src="${basepath}resource/js/tabs.js"></script>
<script src="${basepath}jsp/sys/main/main.js"></script>
<script type="text/javascript">
var basepath = '${basepath}';
var userId = '<%=BspUtils.getLoginUser().getUserId()%>';
var user_img = basepath+'resource/img/user-200x200.jpg';
$(document).ready(function() {
	init();
});
</script>
<!-- javascript结束 -->
</body>
</html>
