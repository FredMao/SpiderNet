<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
	<%@ page import="java.text.*"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	SimpleDateFormat sdf = new SimpleDateFormat("YYYY");
	String dateStr = sdf.format(new Date());
%>
<!DOCTYPE html>
<html lang="en">
<head>
</head>
<body>
   <footer class="row">
        <p class="col-md-9 col-sm-9 col-xs-12 copyright">©2017-<%=dateStr %> Chinasoft HSBC Capacity Center</p>

        <p class="col-md-3 col-sm-3 col-xs-12 powered-by">Powered by : E-learning </p>
    </footer>
</body>
</html>


