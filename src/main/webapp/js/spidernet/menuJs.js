var setting = {
    view: {
        /*addHoverDom: addHoverDom,
        removeHoverDom: removeHoverDom,
        selectedMulti: false*/
    },
    /*check: {
        enable: true
    },*/
    data: {
        simpleData: {
            enable: true
        }
    },
   /*edit: {
        enable: true
    },*/
    callback: {
		onClick: zTreeOnClick
	}
};

var addNumber = 0;

$(document).ready(function(){
	$("#picUrl").iconPicker();
	$.ajax({
		url : path+"/service/menuInfo/showMenu",
		type : "post",
		async : true,
		cache : false,
		dataType : "json",
		timeout : 20000,
		success : function(jsonData) {
			$.fn.zTree.init($("#treeDemo"), setting, jsonData);
			
			$(function(){
				var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
				treeObj.expandAll(true);
				});
		}
	});
	
	
	function cleanScreen(){
		$("#pName").val($("#name").val());
		$("#name").val("");
		$("#menuUrl").val("");
		$("#remark").val("");
		$("#picUrl").val("");
		$("#pId").val($("#id").val());
		$('#pName').attr("disabled","disabled");
		
	};
	function addMenuInfo(){
		var id = $("#id").val();
		var name = $("#name").val();
		var url = $("#menuUrl").val();
		var remark = $("#remark").val();
		var picUrl = $("#picUrl").val();
		var valid = 'Y';
		$.ajax({
			url:path+"/service/menuInfo/addMenu",
			dataType:"json",
			async:true,
			data: {'id':id,'name':name,'url':url,'remark':remark,'picUrl':picUrl},
			cache:false,
			type:"post",
			success:function(resultFlag){
				if (resultFlag)
	    		{
					cleanStyle();
					var successAlert ="<div class=\"alert alert-success\">"
						+"<button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>"
						+"<strong> Success !</strong> Add success please check on the left tree menu ."
						+"</div>";
					$("#alertInfo").append(successAlert);
					setTimeout(function(){  
						window.location.href = path+"/service/menuInfo/menuManagement.html";
                     },3000);
	    		}
	        	else
	    		{
	        		var errorAlert ="<div class=\"alert alert-danger\">"
		 		           +"<button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>"
		 		           +"<strong> Warning! </strong> Operation failure please from the new operation !"
		 		           +"</div>";
		        	$("#alertInfo").append(errorAlert);
	    		}
			}
		})
	};
	
	
	$("#add").click(function(){
		var valuePre = preCheck('add');
		if(valuePre){
			if(addNumber==0){
				cleanScreen();
				addNumber++;
			}else{
				addMenuInfo();
				addNumber--;
			}
		}
		
		
	});
	
	

	$("#update").click(function(){
		var valuePre = preCheck('upd');
		if(valuePre){
			var id = $("#id").val();
			var name = $("#name").val();
			var url = $("#menuUrl").val();
			var sort = $("#sort").val();
			var parentId = $("#parentId").val();
			var remark = $("#remark").val();
			var picUrl = $("#picUrl").val();
			var valid = 'Y';
	
			$.ajax({
				url:path+"/service/menuInfo/updateMenu.shtml",
				dataType:"json",
				async:true,
				data: {'id':id,'name':name,'url':url,'sort':sort,'parentId':parentId,'remark':remark,'picUrl':picUrl},
				cache:false,
				type:"post",
				success:function(resultFlag){
					if (resultFlag)
		    		{
						var successAlert ="<div class=\"alert alert-success\">"
							+"<button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>"
							+"<strong> Success !</strong> Update success please check on the left tree menu ."
							+"</div>";
						$("#alertInfo").append(successAlert);
		        		setTimeout(function(){  
		        			window.location.href = path+"/service/menuInfo/menuManagement.html";
                         },3000);
		    		}
		        	else
		    		{
		        		cleanStyle();
		        		var errorAlert ="<div class=\"alert alert-danger\">"
			 		           +"<button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>"
			 		           +"<strong> Warning! </strong> Operation failure please from the new operation !"
			 		           +"</div>";
			        	$("#alertInfo").append(errorAlert);
		    		}
				}
			});
		}
	});
	
	$("#delete").click(function(){
		var valuePre = preCheck('del');
		if(valuePre){
			var id = $("#id").val();
			$.ajax({
				url:path+"/service/menuInfo/deleteMenu",
				dataType:"json",
				async:true,
				data:{"id":id},
				cache:false,
				type:"post",
				success:function(resultFlag){
					if (resultFlag)
		    		{
						cleanStyle();
		        		var successAlert ="<div class=\"alert alert-success\">"
			                   +"<button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>"
			                    +"<strong> Success !</strong> Delete success please check on the left tree menu ."
			                    +"</div>";
		        		$("#alertInfo").append(successAlert);
		        		setTimeout(function(){  
		        			window.location.href = path+"/service/menuInfo/menuManagement.html";
                         },3000);
		    		}
		        	else
		    		{
		        		var errorAlert ="<div class=\"alert alert-danger\">"
		 		           +"<button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>"
		 		           +"<strong> Warning! </strong> Operation failure please from the new operation !"
		 		           +"</div>";
		        		$("#alertInfo").append(errorAlert);
		    		}
				}
			});
		}
	});
	
	function preCheck(type){
		var preCheckValue = true;
		var alertInfo = "";
		var id = $("#id").val();
		if(id==''){
			preCheckValue = false;
			alertInfo = " Please click on the tree menu on the left first and try it again! "
		}
		else if(type == 'del' && $("#name").val() == ''){
			preCheckValue = false;
			alertInfo = " Please click on the tree menu on the left first and try it again! "
		}
		else if(type!='del'){
			var name = $("#name").val();
			if(name == ''){
				$("#name").parent().addClass("has-error");
				$("#name").attr('placeholder','Input cannot be null !');
				alertInfo = " Please input the correct information ."
					preCheckValue = false;
			}
			var url = $("#menuUrl").val();
			if(url == ''){
				$("#menuUrl").parent().addClass("has-error");
				$("#menuUrl").attr('placeholder','Input cannot be null !');
				alertInfo = " Please input the correct information ."
					preCheckValue = false;
			}
			var picUrl = $("#picUrl").val();
			if(picUrl == ''){
				$("#picUrl").parent().addClass("has-error");
				$("#picUrl").attr('placeholder','Input cannot be null !');
				alertInfo = " Please input the correct information ."
					preCheckValue = false;
			}
		}
		
		if(!preCheckValue){
			$("#alertInfo").empty();
			var errorAlert ="<div class=\"alert alert-danger\">"
		           +"<button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>"
		           +"<strong> Warning! </strong> "
		           + alertInfo
		           +"</div>";
			$("#alertInfo").append(errorAlert);
		}
		return preCheckValue;
	};
	
	
	$(".form-control").blur(function(){
		if($(this).val()!=''){
			$(this).parent().removeClass("has-error");
			$(this).parent().addClass("has-success");
		}
	});
	
	
});

function cleanStyle(){
	addNumber = 0;
	$("#alertInfo").empty();
	$('#pName').attr("disabled",true);
	$("#name").parent().removeClass("has-error");
	$("#menuUrl").parent().removeClass("has-error");
	$("#picUrl").parent().removeClass("has-error");
}

function zTreeOnClick(event, treeId, treeNode) {
	$.ajax({
		url:path+"/service/menuInfo/getMenuById",
		dataType:"json",
		async:true,
		data:{"id":treeNode.id},
		cache:false,
		type:"post",
		success:function(menu){
			$("#id").val(menu.id);
			$("#name").val(menu.name);
			$("#menuUrl").val(menu.url);
			$("#sort").val(menu.sort);
			$("#parentId").val(menu.parentId);
			$("#remark").val(menu.remark);
			$("#picUrl").val(menu.picUrl);
			$("#pName").val(menu.parentName);
			cleanStyle();
		}
	});
};
