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
</head>

<body>
        <!-- left menu starts -->
        <div class="col-sm-2 col-lg-2">
            <div class="sidebar-nav">
                <div class="nav-canvas">
                    <div class="nav-sm nav nav-stacked">
                    </div>
                    <ul class="nav nav-pills nav-stacked main-menu">
                        <li class="nav-header">Functional Module</li>
                        <li><a class="ajax-link" href="<%=path %>/service/employee/index.html"><i class="glyphicon glyphicon-home"></i><span>&nbsp;&nbsp;&nbsp;&nbsp;Home Page</span></a>
                        </li>
                        <c:if test="${sessionScope.employee.empTypeId eq 'a6b8fd9eb5e547da907c7a004810d022'}">
                        <li class="accordion">
                            <a href="#"><i class="glyphicon glyphicon-tasks"></i><span>&nbsp;&nbsp;&nbsp;&nbsp;Employees Maintenance</span></a>
                            <ul class="nav nav-pills nav-stacked">
                                <li><a class="ajax-link" href="<%=path %>/service/employee/register.html"><i class="glyphicon glyphicon-user"></i><span>&nbsp;&nbsp;&nbsp;&nbsp;Regist</span></a></li>
                                <li><a class="ajax-link" href="<%=path %>/service/employee/update2.html"><i class="glyphicon glyphicon-edit"></i><span>&nbsp;&nbsp;&nbsp;&nbsp;Modify</span></a></li>
                                
                            </ul>
                        </li>      
                        <li><a class="ajax-link" href="<%=path %>/service/employee/scoreImport.html"><i class="glyphicon glyphicon-download-alt"></i><span>&nbsp;&nbsp;&nbsp;&nbsp;Score Import</span></a>
                        </li>
                        <li><a class="ajax-link" href="<%=path %>/service/knowledge/knowledgePoint.html"><i class="glyphicon glyphicon-download-alt"></i><span>&nbsp;&nbsp;&nbsp;&nbsp;Knowledge Point</span></a>
                        </li>
                        
                        <li class="accordion">
                            <a href="#"><i class="glyphicon glyphicon-th"></i><span>&nbsp;&nbsp;&nbsp;&nbsp;Capacity Maintenance</span></a>
                            <ul class="nav nav-pills nav-stacked">
                            <c:if test="${sessionScope.employee.hrNumber eq '123456'}">
                                <li><a class="ajax-link" href="<%=path %>/service/employee/capabilityMap.html"><i class="glyphicon glyphicon-picture"></i><span>&nbsp;&nbsp;&nbsp;&nbsp;Capacity Map</span></a></li>
                                <li><a class="ajax-link" href="<%=path %>/service/employee/exam.html"><i class="glyphicon glyphicon-pencil"></i><span>&nbsp;&nbsp;&nbsp;&nbsp;Examination</span></a></li>
                                <li><a class="ajax-link" href="<%=path %>/service/employee/training.html"><i class="glyphicon glyphicon-book"></i><span>&nbsp;&nbsp;&nbsp;&nbsp;Trainning</span></a></li>
                            </c:if>
                                <li><a class="ajax-link" href="<%=path %>/service/employee/batchAddCapability.html"><i class="glyphicon glyphicon-hdd"></i><span>&nbsp;&nbsp;&nbsp;&nbsp;Batch Add Capacity</span></a></li>
                                <li><a class="ajax-link" href="<%=path %>/service/employee/batchAddTraining.html"><i class="glyphicon glyphicon-share"></i><span>&nbsp;&nbsp;&nbsp;&nbsp;Batch Add Trainning</span></a></li>
                            </ul>
                        </li>
                        </li>
                        </c:if>
                        
                    </ul>
                </div>
            </div>
        </div>
</body>
</html>


