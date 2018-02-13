var empArray = new Array();

$(function(){
	loadEmpList();
	
	loadBu();
	
	loadProject();
	
	loadTrainingName();
	
});

function checkPrivilege(){
	
	var privilegeState = $("#privilegeState").val();
	
	var buId = $("#buId").val();
	
	if(privilegeState=='false'){
		//$("#bu option[value='']").removeAttr("selected");
		//$("#bu option[value='"+buId+"']").attr("select","selected");
		$("#bu").val(buId);
		$("#bu").attr("disabled","disabled");
	}
	
}


function loadTrainingName(){
	$.ajax({
		url:path+'/service/trainning/queryTrainingName',
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		success:function(trainingList){
			$("#TrainingName").append("<option>-- please select training Name --</option>");
			for(var i = 0;i<trainingList.length;i++){
				$("#TrainingName").append("<option>"+trainingList[i].courseName+"</option>");
			}
			$('#TrainingName').selectpicker({
		        'selectedText': 'cat'
		    });
		}
	})
}
$("#TrainingName").change(function(){
	var trainingName = $('#TrainingName').val();
	$.ajax({
		url:path+'/service/trainning/queryTrainingByName',
		dataType:"json",
		async:true,
		data:{"trainingName":trainingName},
		cache:false,
		type:"post",
		success:function(trainingList){
			$("#TrainingDate").find("option").remove(); 
			$("#TrainingDate").append("<option value=''>-- please select training date --</option>");
			for(var i = 0;i<trainingList.length;i++){
				$("#TrainingDate").append("<option >"+trainingList[i].time+"</option>");
			}
		}
	})
})


//function addEmployee(){
//	var er = "";
//	for(var i=0; i<10; i++){
//		er = $("#td"+i+"").text();
//		
//		if($("#checkbox"+i+"").is(':checked') && $.inArray(er, empArray) == -1){
//			empArray.push(er);
//		}
//		
//		if(!$("#checkbox"+i+"").is(':checked') && $.inArray(er, empArray) != -1){
//			empArray.splice(jQuery.inArray(er,empArray),1); 
//		}
//	}
//}


function addEmployee(){
	var option =document.all("checkbox");
	if (option!=null){
      var length=option.length;
	  for(var i=0; i<length; i++){   
		er = $("#td"+i+"").text();
		if(option[i].checked && $.inArray(er, empArray) == -1){
			empArray.push(er);
		}
		
		if(!option[i].checked && $.inArray(er, empArray) != -1){
			empArray.splice(jQuery.inArray(er,empArray),1); 
		}
	}
  }
}
	



//function showSelected(){
//	var er = "";
//	for(var i=0; i<10; i++){
//		er = $("#td"+i+"").text();
//		
//		if($.inArray(er, empArray) != -1){
//			
//			$("#checkbox"+i+"").attr("checked",'true');
//		}
//	}
//}

//function selectAll(obj){
//	if(obj.checked==true){
//	  for(var i=0; i<10; i++){
//			$("#checkbox"+i+"").attr("checked",'true');
//		}
//	  i=0;
//	}
//	if(obj.checked==false){
//		for(var i=0; i<10; i++){
//			$("#checkbox"+i+"").attr("checked",'false');
//			}
//		i=0;
//	}
//}
	

function checkBoxSelect(obj, element){
    var option=document.all(element);
	  if (option.length==undefined)
	  {
		  document.getElementById(element).checked=obj.checked;
	  }else{
       for (var x=0;x<option.length;x++ ){
           option[x].checked=obj.checked;
		  }
	  
	  }
 }

function batchAddTraining(){
	addEmployee();
	
	var trainingName = $('#TrainingName').val();
	
	$.ajax({
		url:path+'/service/trainning/batchAddTraining',
		data: {'empArray':empArray,'trainingName':trainingName},
		async:true,
		cache:false,
		traditional: true,
		type:"post",
		success:function(){
			 location.reload(); 
		}
	})
}


function loadEmpList(pageState){
	
	addEmployee();
	
	var buId = $("#bu").val();

	var projectId = $("#project").val();
	
	var trainingName = $("#TrainingName").val();

	var pageState = pageState;
	
	$.ajax({
		url:path+"/service/employeeInfo/employeeInfoList",
		dataType:"json",
		async:true,
		data:{"buId":buId,"projectId":projectId,"pageState":pageState,"trainingName":trainingName},
		cache:false,
		type:"post",
		success:function(result){
			$("#EmployeeList tbody").remove();
			
			var tbody = $("<tbody>");
			tbody.appendTo($("#EmployeeList"));
			
			for (var i = 0; i < result.data.length; i++) {
				var tr = $("<tr></tr>");
				tr.appendTo(tbody);

//				var td1 = $("<td><input type='checkbox' id='checkbox"+i+"'></td>");
				
				var td1=$("<td><input type='checkbox' id='checkbox'></td>");
				
				
				var td2 = $("<td id='td"+i+"'>"
						+ result.data[i].er
						+ "</td>");

				var td3 = $("<td>"
						+ result.data[i].hr
						+ "</td>");
				var td4 = $("<td>"
						+ result.data[i].name
						+ "</td>");
				var td5 = $("<td>"
						+ result.data[i].eName
						+ "</td>");
				var td6 = $("<td>"
						+ result.data[i].buName
						+ "</td>");
				var td7 = $("<td>"
						+ result.data[i].projectName
						+ "</td>");
				var td8 = $("<td>"
						+ result.trainingNames[i]
						+ "</td>");
//				var td9 = $('<td ><a class="btn btn-info" href="#" style="height: 25px; width: 55px;  padding-top: 5px; padding-left: 8px; font-size: 11px;" id = '+result.data[i].er+' onclick="ViewTrainings(this)"><i class="glyphicon glyphicon-edit icon-white">Edit</i></a></td>');
				var td9 = $('<td ><a class="btn btn-info" href="#" style="height: 25px; width: 55px;  padding-top: 5px; padding-left: 8px; font-size: 11px;" onclick="ViewTrainings(this)"><i class="glyphicon glyphicon-edit icon-white">Edit</i></a></td>');
				td1.appendTo(tr);
				td2.appendTo(tr);
				td3.appendTo(tr);
				td4.appendTo(tr);
				td5.appendTo(tr);
				td6.appendTo(tr);
				td7.appendTo(tr);
				td8.appendTo(tr);
				td9.appendTo(tr);
			}
			$("#EmployeeList").append("</tbdoy>");
			//alert(window.location.href);
			var pageNum = parseInt(result.pageInfo.currentPage);
			pageNum = pageNum / 10 + 1;
			var totalPage = parseInt(result.pageInfo.pageCount);
			$("#pageCount").html(totalPage);
			$("#currentPage").html(pageNum);
			$("#nextPage").attr("onclick","loadEmpList('next')");
			$("#previousPage").attr("onclick","loadEmpList('previous')");
			if(pageNum == totalPage){
				$("#nextPage").removeAttr("onclick");
			}
			if(pageNum == 1){
				$("#previousPage").removeAttr("onclick");
			}
			
//			showSelected();
		}
	})
}



function loadProject(buId,projectId){
	var buId = buId;
	$.ajax({
		url:path+'/service/project/queryAll',
		dataType:"json",
		async:true,
		data:{"buId":buId},
		cache:false,
		type:"post",
		success:function(listP){
			
			$("#project").find("option").remove(); 
			$("#project").append("<option value=''>-- please select project name --</option>");
			for(var i = 0;i<listP.length;i++){
				$("#project").append("<option value='"+listP[i].projectId+"'>"+listP[i].projectName+"</option>");
			}
			if(projectId!=null){
				$("#project").val(projectId);
			}
		}
	})
}

 
function loadBu(){
	
	$.ajax({
		url:path+'/service/bu/queryBu',
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		success:function(listB){
			$("#bu").append("<option value=''>-- please select delivery apartment --</option>");
			for(var i = 0;i<listB.length;i++){
				$("#bu").append("<option value='"+listB[i].buId+"'>"+listB[i].buName+"</option>");
			}
			checkPrivilege();
			
			loadEmpList();
		}
	})
}

function ViewTrainings(tar)
{

//var erId = tar.id;
  var erId = $(tar).parent().parent().find('td:eq(1)').text();
  var trName = $(tar).parent().parent().find('td:eq(7)').text();
var url = path+"/service/employeeInfo/viewTrainings";
    $.ajax({
        type: "post",
        url: url,
        data: {'erId':erId,'trName':trName},
        cache: false,
        async : false,
        dataType: "json",
        success: function (result)
        {
        	$("#editList tbody").remove();
			
			var tbody = $("<tbody>");
			tbody.appendTo($("#editList"));
			
			for (var i = 0; i < result.data.length; i++) {
				var tr = $("<tr></tr>");
				tr.appendTo(tbody);
				var td1 = $("<td id='td"+i+"'>"
						+ result.data[i].er
						+ "</td>");
				var td2 = $("<td>"
						+ result.data[i].eName
						+ "</td>");
				
				var td3 = $("<td>"
						+ result.data[i].buName
						+ "</td>");
				var td4 = $("<td>"
						+ result.data[i].trainingName
						+ "</td>");
				var td5 = $("<td>"
						+ result.data[i].knowledgePoint
						+ "</td>");
				var td6 = $("<td>"
						+ result.data[i].subKnowledgePoint
						+ "</td>");
				var td7 = $('<td ><a class="btn btn-info" href="#" style="height: 25px; width: 72px;  padding-top: 5px; padding-left: 8px; font-size: 11px;"  onclick="ActionsTrainings(this,1)"><i class="glyphicon glyphicon-ok-sign icon-white">Pass</i></a><td ><a class="btn btn-info" href="#" style="height: 25px; width: 72px;  padding-top: 5px; padding-left: 8px; font-size: 11px;"  onclick="ActionsTrainings(this,2)"><i class="glyphicon glyphicon-remove-sign icon-white">Delete</i></a></td>');
				td1.appendTo(tr);
				td2.appendTo(tr);
				td3.appendTo(tr);
				td4.appendTo(tr);
				td5.appendTo(tr);
				td6.appendTo(tr);
				td7.appendTo(tr);
			}
			$("#editList").append("</tbdoy>");	
        }
     });
    $("#editModel").modal('show');
}

function ActionsTrainings(tar,type)
{

	var ername = $(tar).parent().parent().find('td:eq(0)').text();
	var trname = $(tar).parent().parent().find('td:eq(3)').text();


$.ajax({
	url:path+'/service/trainning/trainingOperation',
	dataType:"json",
	async:true,
	data:{"ername":ername,"trname":trname, "type":type},
	cache:false,
	type:"post",
	success:function(resultFlag){
		if(resultFlag){
			$("#editModel").modal('hide');
			loadEmpList();
		}
	}
})

}
