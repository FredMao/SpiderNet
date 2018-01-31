var restJsonData;
$(document).ready(function(){
	var menuValueSess = $("#menuMapper").val();
	menuValueSess = menuValueSess.replace(/\=/g, ":");
	menuValueSess = menuValueSess.replace(/\{/g, "{\"");
	menuValueSess = menuValueSess.replace(/\:/g, "\":\"");
	menuValueSess = menuValueSess.replace(/\}/g, "\"}");
	menuValueSess = menuValueSess.replace(/\,/g, "\",\"");
	menuValueSess = menuValueSess.replace(/}"," {/g, "},{");
	menuValueSess = menuValueSess.replace(/\" /g, "\"");
	
	restJsonData = eval(menuValueSess);
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