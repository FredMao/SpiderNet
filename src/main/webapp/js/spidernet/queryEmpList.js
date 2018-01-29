

$(function(){

	loadEmpList();
	
	loadBu();
	
	loadProject();
	
	loadTrainingName();
	
	loadExamName();
	
	$("#examBox").hide();
	
	$("#sub_export").attr("disabled","disabled");
	
	multipleSelect();
	
	$("#rule_confirm_btn").click(function(){
		var rule = $("#ruleList").val();
		var er = $("#er_conf").val();
		if (rule == '' || rule == null){
			$("#failureAlert").html('Please select rule.').show();
		}
		
		$.ajax({
			url:path+'/service/employeeInfo/configRule',
			dataType:"json",
			data:{"er":er,"rule":rule.toString()},
			async:true,
			cache:false,
			type:"post",
			success:function(resultFlag){
				
				if(resultFlag){
					$("#successAlert").html('Operator Success').show();
					setTimeout(function () {
						$("#successAlert").hide();
						$('#ruleConfigModel').modal('hide');
						$('#rule_confirm_btn').attr("disabled", false);
						loadEmpList();
				    }, 1000);
				}else{
					$("#failureAlert").html('Operator Fail').show();
				}
				
			}
		})
		
	});
});


function multipleSelect(){
	$.ajax({
		url:path+'/service/rule/finalAll',
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		success:function(listB){
			buMultiselect = listB;
			for(var i = 0;i<listB.length;i++){
				$("#ruleList").append("<option value='"+listB[i].id+"'>"+listB[i].name+"</option>");
			}
			
			$('.selectpicker').selectpicker({
		        'selectedText': 'cat'
		    });
		}
	})
}

function setRulesByEr(e){
	$("#ruleList").selectpicker('val', ''.split(','));
	$.ajax({
		url:path+'/service/employeeInfo/getRule',
		data:{"er":e},
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		success:function(result){
			var data = result.rule;
			//var dd = ruleid;
			$("#ruleList").selectpicker('val', data.split(','));
		}
	})
}


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

$('#trainingRadio').click(function(){
	$("#examBox").hide();
	$("#trainingBox").show();
	$("#TrainingDate").val('');
	//resetSelect();
	//$("#TrainingName+div :button").text("--请选择培训名称--");
	//$("#sub_export").removeAttr("disabled");
	$("#sub_export").attr("disabled","disabled");
})

$('#examRadio').click(function(){
	$("#trainingBox").hide();
	$("#examBox").show();
	$("#examDate").val('');
	//resetSelect();
	//$("#examName+div :button").text("--请选择培训名称--");sub_export
	$("#sub_export").attr("disabled","disabled");
})


function resetSelect(){
	var liArray = $("li").filter(".selected");

	for(var i=0; i<liArray.length; i++){
		if(liArray.eq(i).text().indexOf("Please Select") == -1){
			liArray.eq(i).removeClass("selected")
		}
	}
}


function exportExcel(){
	var url = path+'/service/employeeInfo/exportExcel';
	$("#exceltHref").attr("href",url);
	//$("#exceltHref").click();
	document.getElementById("exceltHref").click();
}



function loadExamName(){
	$.ajax({
		url:path+'/service/exam/queryExamName',
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		success:function(examList){
			$("#examName").append("<option>-- Please Select --</option>");
			for(var i = 0;i<examList.length;i++){
				$("#examName").append("<option>"+examList[i].name+"</option>");
			}
			$('#examName').selectpicker({
		        'selectedText': 'cat'
		    });
		}
	})
}
$("#examName").change(function(){
	var examName = $('#examName').val();
	$.ajax({
		url:path+'/service/exam/queryExamByName',
		dataType:"json",
		async:true,
		data:{"examName":examName},
		cache:false,
		type:"post",
		success:function(examList){
			$("#examDate").find("option").remove(); 
			$("#examDate").append("<option value=''>-- Please Select --</option>");
			for(var i = 0;i<examList.length;i++){
				$("#examDate").append("<option value='"+examList[i].examId+"'>"+examList[i].startTime+"</option>");
			}
		}
	})
})




function loadTrainingName(){
	$.ajax({
		url:path+'/service/trainning/queryTrainingName',
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		success:function(trainingList){
			$("#TrainingName").append("<option>-- Please Select --</option>");
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
			$("#TrainingDate").append("<option value=''>-- Please Select --</option>");
			for(var i = 0;i<trainingList.length;i++){
				$("#TrainingDate").append("<option value='"+trainingList[i].trainningId+"'>"+trainingList[i].time+"</option>");
			}
		}
	})
})


$("#TrainingDate").change(function(){
	
	$("#sub_export").attr("disabled","disabled");
	
})


function loadEmpList(pageState){
	
	var buId = $("#bu").val();

	var projectId = $("#project").val();

	var er = $("#er").val();
	
	var trainingId = $('#TrainingDate').val();
	
	var examId = $('#examDate').val();

	var pageState = pageState;
	
	$.ajax({
		url:path+"/service/employeeInfo/employeeInfoList",
		dataType:"json",
		async:true,
		data:{"buId":buId,"projectId":projectId,"er":er,"pageState":pageState,"trainingId":trainingId,"examId":examId},
		cache:false,
		type:"post",
		success:function(result){
			$("#EmployeeList tbody").remove();
			
			var tbody = $("<tbody>");
			tbody.appendTo($("#EmployeeList"));
			
			for (var i = 0; i < result.data.length; i++) {
				var tr = $("<tr></tr>");
				tr.appendTo(tbody);

				var td1 = $("<td id='tx1'>"
						+ result.data[i].er
						+ "</td>");

				var td2 = $("<td>"
						+ result.data[i].hr
						+ "</td>");
				var td3 = $("<td>"
						+ result.data[i].name
						+ "</td>");
				var td4 = $("<td>"
						+ result.data[i].eName
						+ "</td>");
				var td5 = $("<td>"
						+ result.data[i].buName
						+ "</td>");
				var td6 = $("<td>"
						+ result.data[i].projectName
						+ "</td>");
				var td7 = $("<td><a class='btn btn-info' href='javascript:void(0);'  onclick=getEr('"+result.data[i].er+"')> <i class='glyphicon glyphicon-edit icon-white'></i> 编辑</a></td>");
				//var td7 = $("<td><a href='javascript:void(0);'  onclick=getEr('"+result.data[i].er+"')>Edit</a></td>");
				
				var td8 = $("<td> <a class='btn btn-info' href='javascript:void(0);'onclick=configRule('"+result.data[i].er+"') ><i class='glyphicon glyphicon-info-sign'></i> 权限配置</a></td>");
				td1.appendTo(tr);
				td2.appendTo(tr);
				td3.appendTo(tr);
				td4.appendTo(tr);
				td5.appendTo(tr);
				td6.appendTo(tr);
				td7.appendTo(tr);
				td8.appendTo(tr);
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
			
			if(result.data.length>0 && trainingId != null && trainingId != ""){
				$("#sub_export").removeAttr("disabled");
			}
		}
	})
}

function getEr(er){
	$("#editForm").attr("action",path+"/service/employee/update.html");
	$("#erNum").val(er);
	$("#editForm").submit();
}

function configRule(er){
	setRulesByEr(er);
	$("#er_conf").val(er);
	$("#ruleConfigModel").modal('show');
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
			$("#project").append("<option value=''>-- Please Select --</option>");
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
			$("#bu").append("<option value=''>-- Please Select --</option>");
			for(var i = 0;i<listB.length;i++){
				$("#bu").append("<option value='"+listB[i].buId+"'>"+listB[i].buName+"</option>");
			}
			checkPrivilege();
			
			loadEmpList();
		}
	})
}