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
<style type="text/css">
.bgRed{
    background-color: #b2dba1;
}
</style>
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
        <div class="box col-md-6">
            <div class="box-inner">
                <div class="box-header well" data-original-title="">
                    <h2>Knowledge Point</h2>
                </div>
                <div class="box-content">
                
                <div class="form-group">
                <form id="scoreForm" method="post" class="form-horizontal"
									style="width: 100%" enctype ="multipart/form-data">
				<div class="group">
							<label class="col-sm-2 control-label" style="width:80px;margin-top:5px;">Title</label>
							<div class="col-sm-3" style="margin-top:5px;">
								<input type="text" class="form-control" name="s_pointTitle" id="s_pointTitle" />
							</div>
						
							<label class="col-lg-2 control-label" style="margin-top:5px;">Status</label>
							<div class="col-lg-3" style="margin-top:5px;">
								<select href="#" class="form-control " name="s_status"
									data-bv-notempty data-bv-notempty-message="Select Status"
									id="s_status" data-bv-group=".group">
									<option value="0">Active</option>
									<option value="1">Inactive</option>
									<option value="-1">All</option>
								</select>
							</div>
						</div>
						<div class="group">				
						 <input type="button" value="&nbsp;Search&nbsp;"
										name="subscribe" id="sub_search" href="#"
										class="button btn btn-primary" data-dismiss="modal"
										onclick="loadKnowlegedPointList('')"
										style="background-color: #D5D5D5; border: 0 none; border-radius: 4px; color: #FFFFFF; cursor: pointer; display: inline-block; font-size: 15px; font-weight: bold; height: 32px; line-height: 32px; margin: 0 5px 10px 0; padding: 0; text-align: center; text-decoration: none; vertical-align: top; white-space: nowrap; width:80px; margin:auto ;margin-top:5px;">
				
				 <input type="button" value="&nbsp;Add&nbsp;"
					name="subscribe" id="sub_add" href="#"
					class="button btn btn-primary" data-dismiss="modal"
					onclick="addKnowledgePoint(0, this)"
					style="background-color: #D5D5D5; border: 0 none; border-radius: 4px; color: #FFFFFF; cursor: pointer; display: inline-block; font-size: 15px; font-weight: bold; height: 32px; line-height: 32px; margin: 0 5px 10px 0; padding: 0; text-align: center; text-decoration: none; vertical-align: top; white-space: nowrap; width: 80px; margin:auto ;margin-top:5px;">
					</div>
				</form>
				</div>
                <div>
                    <table id="knowledgePointList" class="table table-bordered">
                        <thead>
                        <tr>
                            <th>Title</th>
                            <th>Description</th>
							<th>Sort</th>
                            <th>Status</th>
                            <th>Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                    </div>
                    <div>
						<ul class="pagination pagination-centered">
							<li><a href="#" id="pFristPage" onclick="loadKnowlegedPointList('first')">Home Page</a></li>
							<li><a href="#" id="pPreviousPage" onclick="loadKnowlegedPointList('previous')">Previous Page</a></li>
							<li><a href="#" id="pNextPage" onclick="loadKnowlegedPointList('next')">Next Page</a></li>
							<li><a href="#" id="pLastPage" onclick="loadKnowlegedPointList('last')">Last Page</a></li>
						</ul>
						<br>
						Total<span id="pPageCount"></span>Pages at<span id="pCurrentPage"></span>Page
					</div>
					
                </div>
            </div>
        </div>
        <!--/span-->

        <div class="box col-md-6">
            <div class="box-inner">
                <div class="box-header well" data-original-title="">
                    <h2><span id="detail_title"></span>Detail</h2>
                </div>
                <div class="box-content">
                
                  
                <div class="form-group" style="height:30px;">
				    <div style="text-align:center;width:50%;float:left">
				    <input type="button" value="&nbsp;Add&nbsp;"
					name="subscribe" id="subitem_add" href="#"
					class="button btn btn-primary" data-dismiss="modal"
					onclick="addKnowledgePoint(2, this)"
					style="background-color: #D5D5D5; border: 0 none; border-radius: 4px; color: #FFFFFF; cursor: pointer; display: inline-block; font-size: 15px; font-weight: bold; height: 32px; line-height: 32px; margin: 0 5px 10px 0; padding: 0; text-align: center; text-decoration: none; vertical-align: top; white-space: nowrap; width: 80px; margin:auto ;">
				    </div>
				</div>
				
                    <table id="pointDetail" class="table table-striped table-bordered" parentid='0'>
                        <thead>
                        <tr>
                           <th>Title</th>
                            <th>Description</th>
                            <th>Status</th>
                            <th>Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <!--/span-->
    </div><!--/row-->
			</div>
			<!--/#content.col-md-0-->
		</div>
		<!--/fluid-row-->
		<hr>
		
		<div class="modal fade" id="addModel" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">


			<div class="modal-dialog">
				<div class="modal-content">
					<div class="box-header well" data-original-title="">
						<h2>
							<i class="glyphicon glyphicon-tasks"></i> Knowledge Point Information
						</h2>

						<div class="box-icon">
							<a href="#" class="btn btn-round btn-default  btn-minimize "><i
								class="glyphicon glyphicon-chevron-up"></i></a> <a
								class="btn btn-round btn-default" href="#" data-dismiss="modal">
								<i class="glyphicon glyphicon-remove"></i>
							</a>
						</div>
					</div>
					
					<div id="knowledgeBox" class="box-content">

						<div id="successAlert" class="alert alert-success" style="display: none;"></div>
						<div id="failureAlert" class="alert alert-warning" style="display: none;"></div>
						
						<form id="knowledgeBoxForm" method="post" class="form-horizontal">
						
						</br></br>
						<div class="form-group">
							<div class="group">
								<label class="col-sm-2 control-label">Title</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" name="knowledgePointId" id="knowledgePointId" style="display:none"/>
									<input type="text" class="form-control" name="pid" id="pid" style="display:none"/>
									<input type="text" class="form-control" name="pointTitle" id="pointTitle"/>
								</div>
							</div>
							<div class="group">
								<label class="col-sm-2 control-label">Description</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" name="description" id="description"/>
								</div>
							</div>
							<div class="group" style="padding-top:5px;">
								<label class="col-sm-2 control-label">Sort</label>
								<div class="col-sm-4" style="margin-top:5px;">
									<input type="text" class="form-control" name="Sort" value="0" id="Sort"/>
								</div>
							</div>
							
							<div class="group">
							<label class="col-sm-2 control-label">Status</label>
								<label class="radio-inline"> 
								<input type="radio"
									name="status" id="status" value="0"
									 checked="checked">
									Active
								</label> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<label class="radio-inline"> <input type="radio"
									name="status" id="status" value="1">
									Inactive
								</label>
						</div>
							
						</div>
						
						</br>
						
						</br></br></br>
						<div class="center">
							<a id="confirm_btn" class="btn btn-success" href="#" onClick="saveknowledgePoint()"> 
							<i class="glyphicon glyphicon-ok icon-white" ></i> Confirm</a>
							<!-- <input type="submit" value="确&nbsp;&nbsp;定" name="subscribe" id="sub_bt" 
							href="#" class="button btn btn-primary" data-dismiss="modal">  -->
							<a class="btn btn-info" href="#" data-dismiss="modal"> 
							<i class="glyphicon glyphicon-remove icon-white"></i> &nbsp;Cancel&nbsp;</a>
						</div>
						
						</form>
					</div>
				</div>
			</div>
			<!--/span-->
		</div>
		

		<c:import url="/service/manage/footer" />

	</div>
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

	<!-- default loading -->
	<script type="text/javascript" src="<%=path %>/js/spidernet/knowledgePoint.js"></script>
	<script type="text/javascript" src="<%=path %>/js/bootstrap-select.js"></script>
	<script type="text/javascript" src="<%=path %>/js/bootstrap-select.min.js"></script>		
</body>
</html>