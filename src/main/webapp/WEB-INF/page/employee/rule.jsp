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
<link href='<%=path%>/css/bootstrap-datetimepicker.css' rel='stylesheet'>
<link href='<%=path%>/css/bootstrap-datetimepicker.min.css' rel='stylesheet'>
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

<script type="text/javascript" src="<%=path %>/bower_components/zTree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="<%=path %>/bower_components/zTree/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="<%=path %>/bower_components/zTree/js/jquery.ztree.exedit-3.5.js"></script>


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
					<div class="box col-md-12">
						<div class="box-inner">
							<div class="box-header well" data-original-title="">
								<h2>
									<i class="glyphicon glyphicon-book"></i> Rule
								</h2>
							</div>
							<div class="box-content">

								<form id="searchForm" method="post" class="form-horizontal"
									style="width: 100%">
									
									<div class="form-group">
									<div class="group">
										<label class="col-sm-4 control-label">Name</label>
										<div class="col-sm-4">
											<input type="text" class="form-control" name="ruleName" id="ruleName" />
										</div>
									</div>
								    </div>
								    
								    <div class="form-group">
									    <div style="text-align:center;width:50%;float:left">
									    <input type="button" value="&nbsp;Search&nbsp;"
										name="subscribe" id="sub_search" href="#"
										class="button btn btn-primary" data-dismiss="modal"
										onclick="loadRuleList()"
										style="background-color: #D5D5D5; border: 0 none; border-radius: 4px; color: #FFFFFF; cursor: pointer; display: inline-block; font-size: 15px; font-weight: bold; height: 32px; line-height: 32px; margin: 0 5px 10px 0; padding: 0; text-align: center; text-decoration: none; vertical-align: top; white-space: nowrap; width: 100px; margin:auto ;">
									    </div>
									    
									    <div style="text-align:center;width:50%;float:right">
									    <input type="button" value="&nbsp;Add&nbsp;"
										name="subscribe" id="sub_add" href="#"
										class="button btn btn-primary" data-dismiss="modal"
										onclick="addRule()"
										style="background-color: #D5D5D5; border: 0 none; border-radius: 4px; color: #FFFFFF; cursor: pointer; display: inline-block; font-size: 15px; font-weight: bold; height: 32px; line-height: 32px; margin: 0 5px 10px 0; padding: 0; text-align: center; text-decoration: none; vertical-align: top; white-space: nowrap; width: 100px; margin:auto ;">
									    </div>
									</div>

									<div>
										<table id="ruleList"
											class="table table-striped table-bordered">
											<thead>
												<tr>
													<th>Name</th>
													<th>Remark</th>
													<th>Operation</th>
												</tr>
											</thead>
										</table>
									</div>
									<div>
										<ul class="pagination pagination-centered">
											<li><a href="#" id="fristPage"
												onclick="loadRuleList('frist')">Home Page</a></li>
											<li><a href="#" id="previousPage"
												onclick="loadRuleList('previous')">Previous Page</a></li>
											<li><a href="#" id="nextPage"
												onclick="loadRuleList('next')">Next Page</a></li>
											<li><a href="#" id="lastPage"
												onclick="loadRuleList('last')">Last Page</a></li>
										</ul>
										<br> Total<span id="pageCount"></span>Pages at<span
											id="currentPage"></span>Page
									</div>
								</form>
								
								
							</div>
						</div>
					</div>
				</div>
				<!--/row-->
			</div>
			<!--/#content.col-md-0-->
		</div>
		<!--/fluid-row-->
		<hr>
		
		
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">

			<div class="modal-dialog">
				<div class="modal-content">
					<div class="box-header well" data-original-title="">
						<h2>
							<i class="glyphicon glyphicon-user"></i> Rule Information
						</h2>

						<div class="box-icon">
							<a href="#" class="btn btn-round btn-default  btn-minimize "><i
								class="glyphicon glyphicon-chevron-up"></i></a> <a
								class="btn btn-round btn-default" href="#" data-dismiss="modal" onClick="cancelRule()">
								<i class="glyphicon glyphicon-remove"></i>
							</a>
						</div>
					</div>
					
					
					<div class="box-content">
					<form id="ruleForm" class="form-horizontal" method="post">						
						<div id="successAlert" class="alert alert-success" style="display: none;"></div>
						<div id="failureAlert" class="alert alert-warning" style="display: none;"></div>
						<input type="hidden" name="id" id="id" />
						
						<div class="form-group">
							<div class="group">
								<label class="col-sm-2 control-label">Rule Name</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" name="name" id="name"/>
								</div>
							</div>
							<div class="group">
								<label class="col-sm-2 control-label">Role Sort</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" name="sort" id="sort"/>
								</div>
							</div>
						</div>
						
						</br>
												
						<div class="form-group">
							<div class="group">
								<label class="col-sm-2 control-label">Remark</label>
								<div class="col-sm-10">
									<textarea class="form-control" name="remark" id="remark" rows="5" ></textarea>									
								</div>
							</div>
						</div>
						
						</br>
						
						<div class="center">
							<button type="submit" id="submitBtn" class="btn btn-success">Confirm</button>
							<a class="btn btn-info" href="#" data-dismiss="modal" onClick="cancelRule()"> 
							<i class="glyphicon glyphicon-remove icon-white"></i> Cancel</a>
						</div>
					</form>
					</div>
				</div>
			</div>
			<!--/span-->
		</div>	
		
		
		<div class="modal fade" id="menuModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">			
			<div class="modal-dialog">
			<div class="modal-content">	            
	            <div class="box-header well" data-original-title="">
						<h2><i class="glyphicon glyphicon-user"></i> Menu Show</h2>

						<div class="box-icon">
							<a href="#" class="btn btn-round btn-default  btn-minimize ">
							    <i class="glyphicon glyphicon-chevron-up"></i></a> 
							<a href="#" class="btn btn-round btn-default"  data-dismiss="modal" onClick="cancelRuleMenu()">
								<i class="glyphicon glyphicon-remove"></i></a>
						</div>
					</div>
					
	            <div class="box-content">
	            <form id="ruleMenuForm" class="form-horizontal" method="post">
	                <input type="hidden" name="ruleId" id="ruleId" />
	                <input type="hidden" name="menuIds" id="menuIds" />
	                <ul id="treeDemo" class="ztree"></ul>
	                <div class="center">
						<button type="submit" id="ruleMenuBtn" class="btn btn-success">Confirm</button>
						<a class="btn btn-info" href="#" data-dismiss="modal" onClick="cancelRuleMenu()"> 
						<i class="glyphicon glyphicon-remove icon-white"></i> Cancel</a>
					</div>
				</form>
	            </div>
	        </div>
	        </div>
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
	<script type="text/javascript"
		src="<%=path %>/js/spidernet/queryRuleList.js"></script>
	<script type="text/javascript" src="<%=path %>/js/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript" src="<%=path %>/js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript" src="<%=path %>/js/bootstrap-datetimepicker.zh-CN.js"></script>

</body>
</html>