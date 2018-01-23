$(document).ready(function () {
	
	loadKnowlegedPointList('');
	
});

function loadKnowlegedPointList(pageState){
	
	var pointTitle = $("#s_pointTitle").val();
	var status = $("#s_status").val();
	
	$.ajax({
		url:path+'/service/knowledge/getKnowledgePointList',
		dataType:"json",
		data:{'pointTitle':pointTitle, 'status':status,'pageState':pageState},
		async:true,
		cache:false,
		type:"post",
		success:function(result){
			$("#knowledgePointList tbody").remove();
			var id = '';
			var title = '';
			var tbody = $("<tbody>");
			tbody.appendTo($("#knowledgePointList"));
			
			for (var i = 0; i < result.data.length; i++) {
				var tr = $("<tr></tr>");
				tr.appendTo(tbody);
				if (i == 0){
					id = result.data[i].knowledgePointId;
					title = result.data[i].pointTitle;
					tr.addClass('bgRed');
				}
				var td1 = $("<td style='display:none'>"
						+ result.data[i].knowledgePointId
						+ "</td>");

				var td2 = $("<td>"
						+ result.data[i].pointTitle
						+ "</td>");
				
				if(result.data[i].description == null){
					var td3 = $("<td></td>");
				}else{
					var td3 = $("<td>"
							+ result.data[i].description
							+ "</td>");
				}
				if(result.data[i].createUser == null){
					var td4 = $("<td></td>");
				}else{
					var td4 = $("<td>"
							+ result.data[i].updateDate
							+ "</td>");
				}
				
				var td5 = $("<td>"
						+ result.data[i].sort
						+ "</td>");
				var status = 'Active';
				var classname = 'label-success label label-default';
				if (result.data[i].status != 0){
					status = 'Inactive';
					classname = 'label-default label';
				}

				var td6=$("<td class='center'>" 
						+" <span class='"+classname+"'>"+status+"</span>"
						+"</td>");
				
				var td7 = $('<td><a class="btn btn-info" href="#" style="height: 25px; width: 55px;  padding-top: 5px; padding-left: 8px; font-size: 11px;" onclick="addKnowledgePoint(1, this)"><i class="glyphicon glyphicon-edit icon-white">Edit</i></a></td>');
				td1.appendTo(tr);
				td2.appendTo(tr);
				td3.appendTo(tr);
				td5.appendTo(tr);
				td6.appendTo(tr);
				td7.appendTo(tr);
			}
			
			$("#knowledgePointList").append("</tbdoy>");
			
			var pageNum = parseInt(result.pageInfo.currentPage);
			pageNum = pageNum / 10 + 1;
			var totalPage = parseInt(result.pageInfo.pageCount);
			$("#pPageCount").html(totalPage);
			$("#pCurrentPage").html(pageNum);
			$("#pNextPage").attr("onclick","loadKnowlegedPointList('next')");
			$("#pPreviousPage").attr("onclick","loadKnowlegedPointList('previous')");
			if(pageNum == totalPage){
				$("#pNextPage").removeAttr("onclick");
			}
			if(pageNum == 1){
				$("#pPreviousPage").removeAttr("onclick");
			}
			
			$("#detail_title").html(title+'&nbsp;>&nbsp;');
			queryItemDetailByPid(id);
			$('#pointDetail').attr('parentid', id);
			$("#knowledgePointList tr").bind("click",function(){
				
				var pid = $(this).find("td:first").text();
				
				$(this).addClass('bgRed');
				var curr = $(this);
				$("#knowledgePointList").find("tr").each(function(i, tar){
					if (pid != $(tar).find("td:first").text()){
						$(tar).removeClass('bgRed');
					}
				});
				queryItemDetailByPid(pid);
				$('#pointDetail').attr('parentid', pid);
			});
		}
	});
}


function queryItemDetailByPid(id){
	$.ajax({
		url:path+'/service/knowledge/getKnowledgePointByPid?pid='+id,
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		success:function(result){
			$("#pointDetail tbody").remove();
			
			var tbody = $("<tbody>");
			tbody.appendTo($("#pointDetail"));
			
			for (var i = 0; i < result.length; i++) {
				var tr = $("<tr></tr>");
				tr.appendTo(tbody);
				if (i == 0){
					id = result[i].knowledgePointId;
					title = result[i].pointTitle;
				}
				var td1 = $("<td style='display:none'>"
						+ result[i].knowledgePointId
						+ "</td>");

				var td2 = $("<td>"
						+ result[i].pointTitle
						+ "</td>");
				
				if(result[i].description == null){
					var td3 = $("<td></td>");
				}else{
					var td3 = $("<td>"
							+ result[i].description
							+ "</td>");
				}
				if(result[i].createUser == null){
					var td4 = $("<td></td>");
				}else{
					var td4 = $("<td>"
							+ result[i].updateDate
							+ "</td>");
				}
				
				var td5 = $("<td>"
						+ result[i].name
						+ "</td>");
				var status = 'Active';
				var classname = 'label-success label label-default';
				if (result[i].status != 0){
					status = 'Inactive';
					classname = 'label-default label';
				}

				var td6=$("<td class='center'>" 
						+" <span class='"+classname+"'>"+status+"</span>"
						+"</td>");
				
				var td7 = $('<td><a class="btn btn-info" href="#"  onclick="addKnowledgePoint(3, this)" style="height: 25px; width: 50px;  padding-top: 5px; padding-left: 8px; font-size: 11px;"><i class="glyphicon glyphicon-edit icon-white"></i>Edit</a></td>');
				td1.appendTo(tr);
				td2.appendTo(tr);
				td3.appendTo(tr);
				td6.appendTo(tr);
				td7.appendTo(tr);
				}
			$("#pointDetail").append("</tbdoy>");
		}
	});


}

function queryDetail(tar){
	var id = $(tar).parent().parent().find("td:first").text();
	var title = $(tar).parent().parent().find("td:eq(1)").text();
	$("#detail_title").html(title+'&nbsp;>&nbsp;');
	queryItemDetailByPid(id);
	$('#pointDetail').attr('parentid', id);
}

function addKnowledgePoint(type, tar){
	if (type == 0){
		//simple add knowledge point
		$('#knowledgePointId').val(0);
		$('#pid').val(0);
	}else if (type == 1){
		//simple edit knowledge point
		var id = $(tar).parent().parent().find("td:first").text();
		$('#knowledgePointId').val(id);
		$('#pid').val(0);
		loadFormData(id);
	}else if (type == 2){
		//simple add knowledge point detail
		var pid = $('#pointDetail').attr('parentid');
		$('#pid').val(pid);
		$('#knowledgePointId').val(0);
	}else if (type == 3){
		//simple edit knowledge point detail
		var id = $(tar).parent().parent().find("td:first").text();
		var pid = $('#pointDetail').attr('parentid');
		$('#pid').val(pid);
		$('#knowledgePointId').val(id);
		loadFormData(id);
	}
	$("#addModel").modal('show');
};

function loadFormData(id){
	$.ajax({
		url:path+'/service/knowledge/getKnowledgePoint',
		dataType:"json",
		async:true,
		data:{"knowledgePointId":id},
		cache:false,
		type:"post",
		success:function(result){
			$("#pointTitle").val(result.pointTitle);
			$("#description").val(result.description);
			$("input:radio[value='"+result.status+"']").attr('checked', true);
			$("#Sort").val(result.sort);
		}
	})
}

function saveknowledgePoint(){
	
	var knowledgePointId = $('#knowledgePointId').val();
	var pointTitle = $('#pointTitle').val();
	var description = $('#description').val();
	var pid = $('#pid').val();
	var status = $('input[name="status"]:checked').val();
	var sort = $("#Sort").val();
	$.ajax({
		url:path+'/service/knowledge/addOrUpdateKnowledgePoint',
		dataType:"json",
		async:true,
		data:{"knowledgePointId":knowledgePointId,"pointTitle":pointTitle,"description":description,"description":description,"pid":pid,"status":status,"sort":sort},
		cache:false,
		type:"post",
		success:function(resultFlag){
			if(resultFlag){
				$("#successAlert").html('Operator Success').show();
				setTimeout(function () {
					$("#successAlert").hide();
					$("#knowledgePointId").val("");
					$('#pointTitle').val("");
					$('#description').val("");
					$('#pid').val("");
					$('#status').val("");
					$('#addModel').modal('hide');

					if (pid == 0){
						loadKnowlegedPointList('first');
					}else{
						var id =  $('#pointDetail').attr('parentid');
						queryItemDetailByPid(id);
					}
					
					
					
			    }, 1000);
			}else{
				$("#failureAlert").html('Operator Fail').show();
			}
		}
	})
	
}