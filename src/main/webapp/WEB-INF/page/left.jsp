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
            	<input type="hidden" id="menuMapper"  value="${sessionScope.menuList}"/>
                <div class="nav-canvas">
                </div>
            </div>
        </div>
</body>
<script src="<%=path %>/bower_components/jquery/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/spidernet/leftPage.js"></script>
</html>


