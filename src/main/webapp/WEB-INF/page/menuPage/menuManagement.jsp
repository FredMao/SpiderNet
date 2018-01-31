<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="utf-8">
<title>E-learning</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description"
	content="Charisma, a fully featured, responsive, HTML5, Bootstrap admin template.">
<meta name="author" content="Muhammad Usman">

<!-- The styles -->
<link href="<%=path%>/css/bootstrap-cerulean.min.css" rel="stylesheet">

<link href="<%=path%>/css/charisma-app.css" rel="stylesheet">
<link
	href='<%=path%>/bower_components/fullcalendar/dist/fullcalendar.css'
	rel='stylesheet'>
<link
	href='<%=path%>/bower_components/fullcalendar/dist/fullcalendar.print.css'
	rel='stylesheet' media='print'>
<link href='<%=path%>/bower_components/chosen/chosen.min.css'
	rel='stylesheet'>
<link href='<%=path%>/bower_components/colorbox/example3/colorbox.css'
	rel='stylesheet'>
<link
	href='<%=path%>/bower_components/responsive-tables/responsive-tables.css'
	rel='stylesheet'>
<link
	href='<%=path%>/bower_components/bootstrap-tour/build/css/bootstrap-tour.min.css'
	rel='stylesheet'>

<link href='<%=path%>/css/jquery.noty.css' rel='stylesheet'>
<link href='<%=path%>/css/noty_theme_default.css' rel='stylesheet'>
<link href='<%=path%>/css/elfinder.min.css' rel='stylesheet'>
<link href='<%=path%>/css/elfinder.theme.css' rel='stylesheet'>
<link href='<%=path%>/css/jquery.iphone.toggle.css' rel='stylesheet'>
<link href='<%=path%>/css/uploadify.css' rel='stylesheet'>
<link href='<%=path%>/css/animate.min.css' rel='stylesheet'>
<link href='<%=path%>/css/bootstrap-select.css' rel='stylesheet'>
<link href='<%=path%>/css/bootstrap-select.min.css' rel='stylesheet'>


<link href="<%=path%>/css/icon-picker.css" rel="stylesheet" />
 
<link
	href='<%=path%>/bower_components/bootstrap-val/bootstrapValidator.css'
	rel='stylesheet'>

<script type="text/javascript">
		var path = "<%=path%>";
</script>
<!-- jQuery -->
<script src="<%=path%>/bower_components/jquery/jquery.min.js"></script>

<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<!-- The fav icon -->
<link rel="shortcut icon" href="<%=path%>/img/favicon.ico">
<link rel="stylesheet" href="<%=path %>/bower_components/zTree/style/bootstrapStyle.css" type="text/css">

<script type="text/javascript" src="<%=path %>/js/spidernet/menuJs.js"></script>
</head>

<body>
	<!-- topbar starts -->
	<c:import url="/service/manage/top" />
	<!-- topbar ends -->
	<div class="ch-container">
		<div class="row">
			<!-- left menu starts -->
			<c:import url="/service/manage/left" />
			<!-- left menu ends -->
<div id="content" class="col-lg-10 col-sm-10">
            <!-- content starts -->
            

	<div class="row">
	    
	    <!--/span-->
	
	    <div class="box col-md-6">
	        <div class="box-inner">
	            <div class="box-header well" data-original-title="">
	                <h2><i class="glyphicon glyphicon-list"></i> Menu Show</h2>
	
	                <div class="box-icon">
	                    <a href="#" class="btn btn-setting btn-round btn-default"><i
	                            class="glyphicon glyphicon-cog"></i></a>
	                    <a href="#" class="btn btn-minimize btn-round btn-default"><i
	                            class="glyphicon glyphicon-chevron-up"></i></a>
	                    <a href="#" class="btn btn-close btn-round btn-default"><i
	                            class="glyphicon glyphicon-remove"></i></a>
	                </div>
	            </div>
	            <div class="box-content">
	                <ul id="treeDemo" class="ztree"></ul>
	            </div>
	        </div>
	    </div>
	    <!--/span-->
	
	    <div class="box col-md-6">
	        <div class="box-inner">
	            <div class="box-header well" data-original-title="">
	                <h2><i class="glyphicon glyphicon-list"></i> Menu Operation</h2>
	
	                <div class="box-icon">
	                    <a href="#" class="btn btn-setting btn-round btn-default"><i
	                            class="glyphicon glyphicon-cog"></i></a>
	                    <a href="#" class="btn btn-minimize btn-round btn-default"><i
	                            class="glyphicon glyphicon-chevron-up"></i></a>
	                    <a href="#" class="btn btn-close btn-round btn-default"><i
	                            class="glyphicon glyphicon-remove"></i></a>
	                </div>
	            </div>
	            <div class="box-content">
	                <form role="form">
	                <div class="form-group">
					    <input type="hidden" class="form-control" name="id" id="id" />
					    <input type="hidden" class="form-control" name="name"  id="b_name"/>
					    <input type="hidden" class="form-control" name="parentId" id="parentId" />
					    <input type="hidden" class="form-control" name="pName" id="b_pName" />
					    <input type="hidden" class="form-control" name="picUrl" id="picId" />
					    <input type="hidden" class="form-control" name="menuUrl" id="b_menuUrl" />
					    <input type="hidden" class="form-control" name="remark" id="b_remark" />
					    <input type="hidden" class="form-control" name="sort" id="sort" />
					</div>
					 <div class="box-content alerts" id="alertInfo">
		            </div>
                    <div class="form-group">
                        <label  for="exampleInputEmail1"><h4>Node Name</h4></label>
                        <input type="text" class="form-control" id="name"  placeholder="Please Input Information!" />
                    </div>
                    <div class="form-group">
                        <label  for="exampleInputPassword1"><h4>ParentNode Name</h4></label>
                        <input type="text" class="form-control" id="pName"  />
                    </div>
                    <div class="form-group">
                        <label  for="exampleInputPassword1"><h4>Picture Code</h4></label>
                        <input type="text" class="form-control" id="picUrl"  />
                    </div>
                    <div class="form-group">
                        <label  for="exampleInputPassword1"><h4>Node URL</h4></label>
                        <input type="text" class="form-control" id="menuUrl" />
                    </div>
                    <div class="form-group">
                        <label  for="exampleInputPassword1"><h4>Node Remark</h4></label>
                        <textarea class="form-control" id="remark" placeholder="Please Input some menu remark !" ></textarea>
                    </div>
                    
                    <!-- <div class="form-group">
                        <label for="exampleInputFile">File input</label>
                        <input type="file" id="exampleInputFile">

                        <p class="help-block">Example block-level help text here.</p>
                    </div>
                    <div class="checkbox">
                        <label>
                            <input type="checkbox"> Check me out
                        </label>
                    </div> -->
                    
                    <a class="btn btn-success" href="#" id="add">
		                <i class="glyphicon glyphicon-zoom-in icon-white"></i>
		                Add
		            </a>
		            <a class="btn btn-info" href="#" id = "update">
		                <i class="glyphicon glyphicon-edit icon-white" ></i>
		                Update
		            </a>
		            <a class="btn btn-danger" href="#" id ="delete">
		                <i class="glyphicon glyphicon-trash icon-white"></i>
		                Delete
		            </a>
                </form>
	            </div>
	        </div>
	    </div>
	    <!--/span-->
	</div><!--/row-->
    <!-- content ends -->
    <c:import url="/service/manage/footer" />
</div>
				</div>
				<!--/row-->
			</div>
			<!--/#content.col-md-0-->
		
		<!--/fluid-row-->
		<hr>

		

	
	<!--/.fluid-container-->

	<!-- external javascript -->

	<script
		src="<%=path%>/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	<script
		src="<%=path%>/bower_components/bootstrap-val/bootstrapValidator.js"></script>
	<!-- library for cookie management -->
	<script src="<%=path%>/js/jquery.cookie.js"></script>
	<!-- calender plugin -->
	<script src='<%=path%>/bower_components/moment/min/moment.min.js'></script>
	<script
		src='<%=path%>/bower_components/fullcalendar/dist/fullcalendar.min.js'></script>
	<!-- data table plugin -->
	<script src='<%=path%>/js/jquery.dataTables.min.js'></script>

	<!-- select or dropdown enhancer -->
	<script src="<%=path%>/bower_components/chosen/chosen.jquery.min.js"></script>
	<!-- plugin for gallery image view -->
	<script
		src="<%=path%>/bower_components/colorbox/jquery.colorbox-min.js"></script>
	<!-- notification plugin -->
	<script src="<%=path%>/js/jquery.noty.js"></script>
	<!-- library for making tables responsive -->
	<script
		src="<%=path%>/bower_components/responsive-tables/responsive-tables.js"></script>
	<!-- tour plugin -->
	<script
		src="<%=path%>/bower_components/bootstrap-tour/build/js/bootstrap-tour.min.js"></script>
	<!-- star rating plugin -->
	<script src="<%=path%>/js/jquery.raty.min.js"></script>
	<!-- for iOS style toggle switch -->
	<script src="<%=path%>/js/jquery.iphone.toggle.js"></script>
	<!-- autogrowing textarea plugin -->
	<script src="<%=path%>/js/jquery.autogrow-textarea.js"></script>
	<!-- multiple file upload plugin -->
	<script src="<%=path%>/js/jquery.uploadify-3.1.min.js"></script>
	<!-- history.js for cross-browser state change on ajax -->
	<script src="<%=path%>/js/jquery.history.js"></script>
	<!-- application script for Charisma demo -->
	<script src="<%=path%>/js/charisma.js"></script>
	
	<script src="<%=path%>/js/iconPicker.js"></script>

	<!-- default loading -->
	<script type="text/javascript" src="<%=path %>/js/spidernet/scoreImport.js"></script>
	<script type="text/javascript" src="<%=path %>/js/bootstrap-select.js"></script>
	<script type="text/javascript" src="<%=path %>/js/bootstrap-select.min.js"></script>		

	<script type="text/javascript" src="<%=path %>/bower_components/zTree/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="<%=path %>/bower_components/zTree/js/jquery.ztree.excheck-3.5.js"></script>
	<script type="text/javascript" src="<%=path %>/bower_components/zTree/js/jquery.ztree.exedit-3.5.js"></script>
</body>
</html>