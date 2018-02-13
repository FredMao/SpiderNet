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

<link rel="shortcut icon" href="<%=path%>/img/favicon.ico">
<style type="text/css">
.form-horizontal .form-group {
    margin-left: 0px;
    margin-right: 0px;
}
.modal-content {
    width: 800px;
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
					<div class="box col-md-12">
						<div class="box-inner">
							<div class="box-header well" data-original-title="">
								<h2>
									<i class="glyphicon glyphicon-edit"></i> Training Passed Detail Information
								</h2>
							</div>
							<div class="box-content">


								<form id="empForm" method="post" class="form-horizontal"
									style="width: 100%" action="target.php">


									<div class="form-group">
									    <div class="group">
											<label class="col-lg-2 control-label">Delivery
												Department</label>
											<div class="col-lg-3">
												<select id="Bu" class="selectpicker"
													data-live-search="true">
												</select>
											</div>
										</div>
										
										<div class="group">
											<label class="col-lg-3 control-label">Training Name</label>
											<div class="col-lg-3">
												<select id="TrainingName" class="selectpicker"
													data-live-search="true">
												</select>
											</div>
											
										</div>
							
									</div>



									<div class="form-group">
									   <div style="text-align: center; width: 50%; float: left">
											<input type="button" value="&nbsp;Search&nbsp;"
												name="subscribe" id="sub_search" href="#"
												class="button btn btn-primary" data-dismiss="modal"
												onclick="loadTrainningPassedList()"
												style="background-color: #D5D5D5; border: 0 none; border-radius: 4px; color: #FFFFFF; cursor: pointer; display: inline-block; font-size: 15px; font-weight: bold; height: 32px; line-height: 32px; margin: 0 5px 10px 0; padding: 0; text-align: center; text-decoration: none; vertical-align: top; white-space: nowrap; width: 100px; margin: auto;">
									
									   </div>
                                    </div>
									

									<div>
										<table id="trainningPassedList"
											class="table table-striped table-bordered">
											<thead>
												<tr>
													<th>Er</th>
													<th>Hr</th>
													<th>Chinese Name</th>
													<th>English Name</th>
													<th>Delivery Department</th>
													<th>Project</th>
													<th>Passed Training Name</th>
													<th>Action</th>
												</tr>
											</thead>
										</table>
								</div>
									<div>
										<ul class="pagination pagination-centered">
											<li><a href="#" id="fristPage"
												onclick="loadTrainningPassedList('frist')">Home Page</a></li>
											<li><a href="#" id="previousPage"
												onclick="loadTrainningPassedList('previous')">Previous Page</a></li>
											<li><a href="#" id="nextPage"
												onclick="loadTrainningPassedList('next')">Next Page</a></li>
											<li><a href="#" id="lastPage"
												onclick="loadTrainningPassedList('last')">Last Page</a></li>
										</ul>
										<br> Total<span id="pageCount"></span>Pages at<span
											id="currentPage"></span>Page
									</div>

								</form>

							</div>
							<div class="modal fade" id="viewModel" tabindex="-1" role="dialog"
			                      aria-labelledby="myModalLabel" aria-hidden="true">


			                   <div class="modal-dialog">
				                 <div class="modal-content">
					                <div class="box-header well" data-original-title="">
						                <h2>
							              <i class="glyphicon glyphicon-user"></i>Employee Passed Training Detail Information
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
						</br></br>
						<div class="form-group">
							<div>
					 <table id="viewList" class="table table-bordered" data-show-refresh="true">
						<thead>
							<tr>
								<th>Er</th>
								<th>Chinese Name</th>
								<th>English Name</th>
								<th>Delivery Department</th>
								<th>Passed Training Name</th>
								<th>Training Knowledge Point</th>
								<th>Sub knowledge Point</th>
							</tr>
						</thead>
					</table>
				</div>
					</div>
				</div>
			</div>
			<!--/span-->
		</div>
			<!--/span-->
		</div>
						</div>
					</div>
				</div>
				<!--/row-->

			

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
	<!-- jquery session -->
	<script src="<%=path%>/js/spidernet/jquery.session.js"></script>

	<!-- default loading -->
	<script type="text/javascript"
		src="<%=path%>/js/spidernet/queryTrainingPassedList.js"></script>
	<script type="text/javascript" src="<%=path%>/js/bootstrap-select.js"></script>
	<script type="text/javascript"
		src="<%=path%>/js/bootstrap-select.min.js"></script>

</body>
</html>