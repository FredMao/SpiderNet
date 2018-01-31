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
<script type="text/javascript">
var restJsonData;
$(document).ready(function(){
	 /* var data = [
		{"id":"-1","name":"Home Page","pId":"0","picUrl":"glyphicon glyphicon-home","MenuUrl":"#"},
		{"id":"1","name":"Employees Maintenance","pId":"0","picUrl":"glyphicon glyphicon-tasks","MenuUrl":"#"},
		{"id":"6","name":"Regist","pId":"1","picUrl":"glyphicon glyphicon-user","MenuUrl":"#"},
		{"id":"7","name":"Modify","pId":"1","picUrl":"glyphicon glyphicon-edit","MenuUrl":"#"},
		{"id":"8","name":"Capacity Maintenance","pId":"0","picUrl":"glyphicon glyphicon-picture","MenuUrl":"#"},
		{"id":"9","name":"Capacity Map","pId":"8","picUrl":"glyphicon glyphicon-picture","MenuUrl":"#"},
		{"id":"2","name":"Risk Factor","pId":"8","picUrl":"glyphicon glyphicon-pencil","MenuUrl":"#"},
		{"id":"3","name":"Risk Temp Add","pId":"2","picUrl":"glyphicon glyphicon-book","MenuUrl":"#"},
		{"id":"10","name":"Risk Temp Del","pId":"2","picUrl":"glyphicon glyphicon-hdd","MenuUrl":"#"},
		{"id":"11","name":"Team update","pId":"10","picUrl":"glyphicon glyphicon-share","MenuUrl":"#"},
		{"id":"4","name":"Menu Management","pId":"0","picUrl":"glyphicon glyphicon-plus-sign","MenuUrl":"#"}
	];
	restJsonData = eval(data);
	importCode(); */ 
	
	
	
	
	var menuValueSess = $("#menuMapper").val();
	menuValueSess = menuValueSess.replace(/\=/g, ":");
	menuValueSess = menuValueSess.replace(/\{/g, "{\"");
	menuValueSess = menuValueSess.replace(/\:/g, "\":\"");
	menuValueSess = menuValueSess.replace(/\}/g, "\"}");
	menuValueSess = menuValueSess.replace(/\,/g, "\",\"");
	menuValueSess = menuValueSess.replace(/}"," {/g, "},{");
	menuValueSess = menuValueSess.replace(/\" /g, "\"");
	
//	alert(menuValueSess);
	restJsonData = eval(menuValueSess);
//	alert(restJsonData.length+"  : lenght" );
	importCode();
	
});

function importCode(){
	var strs = "";
	var homeCodeId = -1;
	strs += "<div class=\"nav-sm nav nav-stacked\"></div>";
	strs += "<ul class=\"nav nav-pills nav-stacked main-menu\">";
	strs += "<li class=\"nav-header\">Functional Module</li>";
	forTree(homeCodeId);
	strs += str;
	strs += "</ul>";
//	alert(strs);
	$(".nav-canvas").append(strs);
};

var str = "";
var forTree = function(pIdCode) {
	for (var i = 0; i < restJsonData.length; i++) {
		if(restJsonData[i]["pId"] == pIdCode){
			var newVar = restJsonData[i]["MenuUrl"];
			if(restJsonData[i]["MenuUrl"]!="#"){
				newVar = path+"/service/"+restJsonData[i]["MenuUrl"];
			}
			
			if(findChindSize(restJsonData[i]["id"])==0){
				str += "<li><a class=\"ajax-link\" href=\""+newVar+"\"><i class=\""+restJsonData[i]["picUrl"]+"\"></i><span>&nbsp;&nbsp;&nbsp;&nbsp;"+restJsonData[i]["name"]+"</span></a></li>";
			}
			else if(restJsonData[i]["pId"] == -1){
				str += "<li><a class=\"ajax-link\" href=\""+newVar+"\"><i class=\""+restJsonData[i]["picUrl"]+"\"></i><span>&nbsp;&nbsp;&nbsp;&nbsp;"+restJsonData[i]["name"]+"</span></a></li>";
				forTree(restJsonData[i]["id"]);
			}
			else if(restJsonData[i]["pId"] != -1){
				str += "<li class=\"accordion\">"
               		+"<a href=\""+newVar+"\"><i class=\""+restJsonData[i]["picUrl"]+"\"></i><span>&nbsp;&nbsp;&nbsp;&nbsp;"+restJsonData[i]["name"]+"</span></a>"
               		+"<ul class=\"nav nav-pills nav-stacked\">";
               	forTree(restJsonData[i]["id"]);
				str += "</ul>"
	   				+"</li>";
			}
		}
		
	}
};

function findChindSize(nodeId){
	var childSize=0;
	for(var j=0;j<restJsonData.length;j++){
		if(restJsonData[j]["pId"]==nodeId){
			childSize++;
		}
	}
	return childSize;
};




</script>
</html>


