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
<link
	href='<%=path%>/bower_components/bootstrap-val/bootstrapValidator.css'
	rel='stylesheet'>

<script type="text/javascript">
		var path = "<%=path%>";
</script>
<!-- jQuery -->
<script src="<%=path%>/bower_components/jquery/jquery.min.js"></script>

<link rel="shortcut icon" href="<%=path%>/img/favicon.ico">

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
									<i class="glyphicon glyphicon-edit"></i> Employee’s Information Modification
								</h2>
							</div>
							<div class="box-content">


								<form id="empForm" method="post" class="form-horizontal"
									style="width: 100%" action="target.php">
									
									
									<input type="hidden" name="privilegeState" id="privilegeState" value="${sessionScope.employee.hrNumber eq '123456'}" />
									
									<input type="hidden" name="buId" id="buId" value="${sessionScope.employee.buId}" />
									
									
									
									<div class="form-group">
										<div class="group">
											<label class="col-lg-2 control-label">Delivery Department</label>
											<div class="col-lg-3">
												<select href="#" class="form-control " name="bu"
													data-bv-notempty data-bv-notempty-message="Please select delivery department."
													id="bu" data-bv-group=".group"
													onchange="loadProject(this.options[this.options.selectedIndex].value);">		
													<option value="">-- Please Select --</option>
												</select>
											</div>
										</div>
										
										<div class="group">
											<label class="col-lg-3 control-label">Project</label>
											<div class="col-lg-3">
												<select href="#" class="form-control " name="project"
													data-bv-notempty data-bv-notempty-message="Please select project."
													id="project" data-bv-group=".group">
													<option value="">-- Please Select --</option>
												</select>
											</div>
										</div>
									</div>
									
									<div class="form-group">
										<div style="text-align: center;width:50%;float:left">
											<input type="button" value="Add Capability Map" name="subscribe"
												id="sub_upd" href="#" class="button btn btn-primary"
												data-dismiss="modal"
												style="background-color: #aaa; border: 0 none; border-radius: 4px; color: #FFFFFF; cursor: pointer; display: inline-block; font-size: 15px; font-weight: bold; height: 32px; line-height: 32px; margin: 0 5px 10px 0; padding: 0; text-align: center; text-decoration: none; vertical-align: top; white-space: nowrap; width: 100px; margin: auto;"
												onclick="ViewCapability();">
										</div>
										<div style="text-align:center;width=100%">
									    <input type="button" value="&nbsp;Search&nbsp;"
										name="subscribe" id="sub_search" href="#"
										class="button btn btn-primary" data-dismiss="modal"
										onclick="loadEmpList()"
										style="background-color: #D5D5D5; border: 0 none; border-radius: 4px; color: #FFFFFF; cursor: pointer; display: inline-block; font-size: 15px; font-weight: bold; height: 32px; line-height: 32px; margin: 0 5px 10px 0; padding: 0; text-align: center; text-decoration: none; vertical-align: top; white-space: nowrap; width: 100px; margin:auto ;">
									</div>
									</div>
									
									<div>
									<table id="EmployeeList"
										class="table table-striped table-bordered">
										<thead>
											<tr>
											    <th>Operation</th>
												<th>Er</th>
												<th>Hr</th>
												<th>Chinese Name</th>
												<th>English Name</th>
												<th>Delivery Department</th>
												<th>Project</th>
											</tr>
										</thead>
									</table>
									</div>
									<div>
										<ul class="pagination pagination-centered">
											<li><a href="#" id="fristPage" onclick="loadEmpList('frist')">Home Page</a></li>
											<li><a href="#" id="previousPage" onclick="loadEmpList('previous')">Previous Page</a></li>
											<li><a href="#" id="nextPage" onclick="loadEmpList('next')">Next Page</a></li>
											<li><a href="#" id="lastPage" onclick="loadEmpList('last')">Last Page</a></li>
										</ul>
										<br>
										Total<span id="pageCount"></span>Pages at<span id="currentPage"></span>Page
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
							<i class="glyphicon glyphicon-user"></i> Capability Map
						</h2>

						<div class="box-icon">
							<a href="#" class="btn btn-round btn-default  btn-minimize "><i
								class="glyphicon glyphicon-chevron-up"></i></a> <a
								class="btn btn-round btn-default" href="#" data-dismiss="modal">
								<i class="glyphicon glyphicon-remove"></i>
							</a>
							<!-- <a href="#"
								class="btn btn-close btn-round btn-default" data-dismiss="modal"><i
								class="glyphicon glyphicon-remove"></i></a> -->
						</div>
					</div>
					<div id="capabilityMapAll" class="box-content">
						<table id="capabilityMap"
							class="table table-striped table-bordered">
							<thead>
								<tr>
									<th style="width: 20%;">Domain</th>
									<th>Block</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
						<div class="center">
							<a class="btn btn-success" href="#" onClick="batchAddCapability()"> <i
								class="glyphicon glyphicon-ok icon-white" ></i> Confirm
							</a> <a class="btn btn-info" href="#" data-dismiss="modal"> <i
								class="glyphicon glyphicon-remove icon-white"></i> Cancel
							</a>
						</div>
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
	<script src="<%=path %>/js/charisma.js"></script>

	<!-- default loading -->
	<script type="text/javascript" src="<%=path %>/js/spidernet/batchAddCapability.js"></script>

</body>
</html>