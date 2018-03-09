$(function() {

    loadTrainningList();
    dateType();

});
function AssginTrainningplanMode(id){
	window.location.href=path+"/service/employee/assignTraining?trainingId="+id;
}
function AssignTrainingPlanMode(planId,trainingId,pointId){
	window.location.href=path+"/service/employee/assignPlanTraining?trainingId="+trainingId+"&planTrainingId="+planId+"&pointId="+pointId;
}
function loadTrainningList(pageState) {

    var trainningName = $("#trainningName").val();

    var pageState = pageState;

    $
        .ajax({
            url : path + "/service/trainning/trainningInfoList",
            dataType : "json",
            async : true,
            data : {
                "trainningName" : trainningName,
                "pageState" : pageState
            },
            cache : false,
            type : "post",
            success : function(result) {
                $("#trainningList tbody").remove();

                var tbody = $("<tbody>");
                tbody.appendTo($("#trainningList"));

                for (var i = 0; i < result.data.length; i++) {
                    var tr = $("<tr></tr>");
                    tr.appendTo(tbody);


                    var td1 = $("<td style='display:none'>" + result.data[i].trainningId + "</td>");

                    var td2 = $("<td>" + result.data[i].courseName
                        + "</td>");
                    var targetDate = result.data[i].time;
                    var splitTarget = targetDate.split(" ");
                    var td3 = $("<td>" + splitTarget[0] + "</td>");
                    var td4 = $("<td>" + result.data[i].location + "</td>");
                    //var td5 = $("<td>" + result.data[i].teacher + "</td>");
                    var td6 = $("<td>" + result.data[i].knowledgePoint + "</td>");
                    var td7 = $("<td>" + result.data[i].subTopic + "</td>");
                    var td8 = $("<td><a class='btn btn-primary btn-sm' href='javascript:void(0);' onclick=updateTrainningMode('"+result.data[i].trainningId+"','"+result.data[i].knowledgePoint+"')>Edit</a>"
                    		+ "&nbsp&nbsp&nbsp"
                            + "<a class='btn btn-primary btn-sm' href='javascript:void(0);' onclick=DeletedDetailTrainningplanMode('"
                            + result.data[i].trainningId
                            + "')>Deleted</a>"
                            + "&nbsp&nbsp&nbsp"
                            + "<a class='btn btn-primary btn-sm' href='javascript:void(0);' onclick=AssginTrainningplanMode('"
                            + result.data[i].trainningId
                            + "')>Assgin</a>"
                            + "</td>");
                    // var td6 = $("<td><a class='btn btn-info'
                    // href='javascript:void(0);'> <i class='glyphicon
                    // glyphicon-edit icon-white'></i> Edit</a></td>");
                    // var td7 = $("<td><a href='javascript:void(0);'
                    // onclick=getEr('"+result.data[i].er+"')>Edit</a></td>");
                    td1.appendTo(tr);
                    td2.appendTo(tr);
                    td3.appendTo(tr);
                    td4.appendTo(tr);
                   // td5.appendTo(tr);
                    td6.appendTo(tr);
                    td7.appendTo(tr);
                    td8.appendTo(tr);

                }
                $("#trainningList").append("</tbdoy>");
                // alert(window.location.href);
                var pageNum = parseInt(result.pageInfo.currentPage);
                pageNum = pageNum / 10 + 1;
                var totalPage = parseInt(result.pageInfo.pageCount);
                $("#pageCount").html(totalPage);
                $("#currentPage").html(pageNum);
                $("#nextPage").attr("onclick", "loadTrainningList('next')");
                $("#previousPage").attr("onclick",
                    "loadTrainningList('previous')");
                if (pageNum == totalPage) {
                    $("#nextPage").removeAttr("onclick");
                }
                if (pageNum == 1) {
                    $("#previousPage").removeAttr("onclick");
                }

                $("#trainningList tr").bind("click",function(){

                    var pid = $(this).find("td:first").text();

                    $(this).addClass('bgRed');
                    var CourceId = $(this).find("td:first").text();
                    $("#trainningList").find("tr").each(function(i, tar){
                        if (pid != $(tar).find("td:first").text()){
                            $(tar).removeClass('bgRed');
                        }
                    });
                    detailTrainningplan(CourceId)
                });
            }
        })
}

function dateType() {
    $('.form_datetime').datetimepicker({
        weekStart : 1,
        todayBtn : 1,
        autoclose : 1,
        todayHighlight : 1,
        startView : 2,
        forceParse : 0,
        language : 'zh-CN',
        format : 'yyyy-mm-dd',
        pickerPosition : 'bottom-left',
        showMeridian : 1
    });
}

function loadCCapability() {
    $
        .ajax({
            url : path + '/service/commonCapability/queryCCapability',
            dataType : "json",
            async : true,
            cache : false,
            type : "post",
            success : function(cCapabilityList) {
                $("#skillPoints").find("option").remove();
                $("#skillPoints").append(
                    "<option value=''>-- 请选择技能点 --</option>");
                for (var i = 0; i < cCapabilityList.length; i++) {
                    $("#skillPoints").append(
                        "<option value='"
                        + cCapabilityList[i].commCapabilityId
                        + "'>" + cCapabilityList[i].name
                        + "</option>");
                }
            }
        })
}

function loadProCapability() {
    $.ajax({
        url : path + '/service/proCapability/queryProCapability',
        dataType : "json",
        async : true,
        cache : false,
        type : "post",
        success : function(proCapabilityList) {
            $("#skillPoints").find("option").remove();
            $("#skillPoints").append("<option value=''>-- 请选择技能点 --</option>");
            for (var i = 0; i < proCapabilityList.length; i++) {
                $("#skillPoints").append(
                    "<option value='"
                    + proCapabilityList[i].proCapabilityId + "'>"
                    + proCapabilityList[i].name + "</option>");
            }
        }
    })
}

function addTrainning() {

    loadTreeView("");
    var result =globalTreeView;
    $('#treeview-Knowledge').treeview({
        data: result,
        showCheckbox: true,
        highlightSelected: false,
        emptyIcon: '',
        multiSelect: true,
        hierarchicalCheck:true,
        onNodeChecked: function (event,node) {
/*            var str = $("#knowledgePointList").val();
            if (str.length > 0) {
                $("#knowledgePointList").val(str + node.knowledgePoint + ',');
            } else {
                $("#knowledgePointList").val(node.knowledgePoint + ',');
            }*/
            function doCheckedNode(node) {
                var thisDiv = $('#treeview-Knowledge');
                var parentNode = thisDiv.treeview('getParent', node);
                if(parentNode && 0 <= parentNode.nodeId) {
                    console.log(parentNode);
                    thisDiv.treeview('checkNode', [ parentNode, { silent: true } ]);
                    doCheckedNode(parentNode);
                }
            }
            doCheckedNode(node);
        },
        onNodeUnchecked: function (event, node) {
            function doUnCheckedNode(node) {
                var thisDiv = $('#treeview-Knowledge');
                if(node && node.nodes && 0 < node.nodes.length) {
                    var childNodes = node.nodes;
                    for(var i = 0, len = childNodes.length; i < len; i++) {
                        // 取消选中
                        thisDiv.treeview('uncheckNode', [ childNodes[i].nodeId, { silent: true } ]);
                        // 递归
                        doUnCheckedNode(childNodes[i]);
                    }
                }
            }
            doUnCheckedNode(node);
        }

    }).treeview('collapseAll', {
        silent : false
    });
    var item = $('input[name="skillRadio"]:checked').val();

    if (item == 'option1') {
        loadCCapability();
    }

    if (item == 'option2') {
        loadProCapability();
    }

    $('#myModal').modal('show');
}

function saveTrainning() {
    var trainningName = $("#addtrainningName2").val();
    var trainningTime = $("#trainningTime").val();
    var location = $("#location").val();
    var teacher = $("#teacher").val();
    var trainningURL = $("#trainningURL").val();
    /*var knowledgePoint = $("#knowledgePointList").val();*/
    /*var childKnowledgePoints = $("#ChildKnowledgePoints").val();*/
    var knowledgePointList="";
    var test=$('#treeview-Knowledge').treeview('getChecked', 'knowledgePoint');
    for (var i = 0; i<test.length ;i++){
        knowledgePointList=test[i].knowledgePoint+","+knowledgePointList;
    }

    $.ajax({
        url : path + '/service/trainning/addTrainning',
        dataType : "json",
        async : true,
        data : {
            "trainningName" : trainningName,
            "trainningTime" : trainningTime,
            "location" : location,
            "teacher" : teacher,
            "trainningURL" : trainningURL,
            "knowledgePoint" : knowledgePointList/*,*/
            /*"childKnowledgePoints" : childKnowledgePoints*/
        },
        cache : false,
        type : "post",
        success : function(resultFlag) {
            if (resultFlag) {
                $("#successAlert").html('Successfully').show();
                setTimeout(function() {
                    $("#successAlert").hide();
                    $("#failureAlert").hide();
                    $("#addtrainningName2").val("");
                    $("#trainningTime").val("");
                    $("#location").val("");
                    $("#teacher").val("");
                    $("#trainningURL").val("");
                    $("#KnowledgePoint").val("");
                    $("#ChildKnowledgePoints").val("");
                    $("#knowledgePointList").val("");
                    $('#myModal').modal('hide');
                }, 2000);
            } else {
                $("#failureAlert").html('failed').show();
            }
        }
    })
    loadTrainningList();
}

$('#commonCapability').click(function() {
    loadCCapability();
})

$('#proCapability').click(function() {
    loadProCapability();
})

function loadTrainingMessage() {
    $
        .ajax({
            url : path + '/service/commonCapability/queryCCapability',
            dataType : "json",
            async : true,
            cache : false,
            type : "post",
            success : function(result) {
                $("#skillPoints").find("option").remove();
                $("#skillPoints").append(
                    "<option value=''>-- 请选择技能点 --</option>");
                for (var i = 0; i < cCapabilityList.length; i++) {
                    $("#skillPoints").append(
                        "<option value='"
                        + cCapabilityList[i].commCapabilityId
                        + "'>" + cCapabilityList[i].name
                        + "</option>");
                }
            }
        })
}

var globalKnowledgePointList;
var globalChildKnowledgePointList;
var globalTreeView;

function loadKnowledgePoint(trainingCourceId) {
    $.ajax({
        /*url : path + '/service/knowledge/getKnowledgePointByPid',*/
        url : path + '/service/trainning/getTrainingKnowledgeById',
        dataType : "json",
        async : false,
        data : {
            "trainingCourceId":trainingCourceId
        },
        cache : false,
        type : "post",
        success : function(knowledgePointList) {
            globalKnowledgePointList =knowledgePointList;
        }
    })
}

function loadChildKnowledgePoint(KnowledgePointByPid) {
    $.ajax({
        url : path + '/service/knowledge/getKnowledgePointByPid',
        dataType : "json",
        async : false,
        data : {
            "pid" : KnowledgePointByPid,
            "status" : "0",
        },
        cache : false,
        type : "post",
        success : function(childKnowledgePointList) {
            globalChildKnowledgePointList = childKnowledgePointList;
        },
        error : function() {
            alert("请求失败")
        }
    });
}

function loadTreeView(traningId) {
    $.ajax({
        type: "Post",
        url: path + '/service/knowledge/getKnowledgePointByJson',
        data : {
            "traningId" : traningId,
        },
        async : false,
        dataType: "json",
        success: function (result) {
            globalTreeView=result;
        },
        error: function () {
            alert("树形结构加载失败！")
        }
    });
}

function detailTrainningplan(CourceId) {

    var trainCourceId = CourceId;
    $("#parentTrainingName").val("");
    $("#parentTrainingName").val(trainCourceId);
    $.ajax({
            url : path + "/service/TrainingPlan/queryTrainPlan",
            dataType : "json",
            async : true,
            data : {
                    "trainCourceId" : trainCourceId
                   },
            cache : false,
            type : "post",
            success : function(result) {
                $("#trainningPlanDetail tbody").remove();
                var tbody = $("<tbody>");
                tbody.appendTo($("#trainningPlanDetail"));
                for (var i = 0; i < result.length; i++) {
                    var tr = $("<tr></tr>");
                    tr.appendTo(tbody);
                    var td1 = $("<td>" + result[i].parentTrainingName + "</td>");
                    var td2 = $("<td>" + result[i].childTrainName + "</td>");
                    var td3 = $("<td>" + result[i].trainTimeStart + "</td>");
                    var td4 = $("<td>" + result[i].trainTimeEnd + "</td>");
                    var td5 = $("<td>" + result[i].trainRoom + "</td>");
                    var td6 = $("<td>" + result[i].participants + "</td>");
                    var td7 = $("<td> <a class='btn btn-info btn-xs' href='javascript:void(0);' onclick=updateDetailTrainningplanMode('"
                        + result[i].allocationPlanId
                        + "','"
                        + result[i].parentId
                        + "','"
                        + result[i].childId
                        + "')>Edit</a>"
                        + "&nbsp&nbsp&nbsp"
                        + "<a class='btn btn-danger btn-xs' href='javascript:void(0);' onclick=DetailTrainningDetailplanMode('"
                        + result[i].allocationPlanId
                        + "','"
                        + trainCourceId
                        + "')>Deleted</a>"
                        + "&nbsp&nbsp&nbsp"
                        + "<a class='btn btn-danger btn-xs' href='javascript:void(0);' onclick=AssignTrainingPlanMode('"
                        + result[i].allocationPlanId
                        + "','"
                        + trainCourceId+"','"+  result[i].parentId
                        + "')>Assgin</a>"
                        + "</td>");
                    td1.appendTo(tr);
                    td2.appendTo(tr);
                    td3.appendTo(tr);
                    td4.appendTo(tr);
                    td5.appendTo(tr);
                    td6.appendTo(tr);
                    td7.appendTo(tr);
                }
                $("#trainningPlanDetail").append("</tbdoy>");
            }
        })
}

function addDetailTrainningplan() {

    $("#addKnowledgePoint").empty();
    $("#addChildKnowledgePoints").empty();
    $("#addDetailtrainningTime").empty();
    $("#addlocation").empty();
    $("#addParticipants").empty();

    var trainingCourceId = $("#parentTrainingName").val();

    loadKnowledgePoint(trainingCourceId);

    $("#addKnowledgePoint").find("option").remove();
    $("#addKnowledgePoint").append("<option value=''>-- Please Select --</option>");
    for (var i = 0; i < globalKnowledgePointList.length; i++) {
        $("#addKnowledgePoint").append(
            "<option value='" + globalKnowledgePointList[i].knowledgePointId + "'>"
                              + globalKnowledgePointList[i].pointTitle
            + "</option>");
    }
    globalKnowledgePointList=null;

    $('#addTrainingPlanDeatil').modal('show');
}

$("#addKnowledgePoint").change(
        function() {
            $("#addChildKnowledgePoints").empty();
            var KnowledgePointByPid = $(this).val();

            $("#addChildKnowledgePoints").find("option").remove();
            $("#addChildKnowledgePoints").append("<option value=''>-- Please Select --</option>");
            loadChildKnowledgePoint(KnowledgePointByPid);
            for (var i = 0; i < globalChildKnowledgePointList.length; i++) {
                $("#addChildKnowledgePoints").append("<option value='" + globalChildKnowledgePointList[i].knowledgePointId + "'>"
                                              + globalChildKnowledgePointList[i].pointTitle + "</option>");
            }
        });

function saveTrainningdetailPlan() {

    var trainingCourceId = $("#parentTrainingName").val();
    var parentTrainingName = $("#addKnowledgePoint").val();
    var childTrainingName = $("#addChildKnowledgePoints").val();
	var trainningTimeStart = $("#addDetailtrainningTimeStart").val();
	var trainningTimeEnd = $("#addDetailtrainningTimeEnd").val();
    var trainingRoom = $("#addlocation").val();
    var participantsNumber = $("#addParticipants").val();


    
    $.ajax({
        url : path + '/service/TrainingPlan/addTrainPlan',
        dataType : "json",
        async : true,
        data : {
            "trainingCourceId" : trainingCourceId,
            "parentTrainingName" : parentTrainingName,
            "childTrainingName" : childTrainingName,
			"trainningTimeStart" : trainningTimeStart,
			"trainningTimeEnd" : trainningTimeEnd,
            "trainingRoom" : trainingRoom,
            "participantsNumber" : participantsNumber
        },
        cache : false,
        type : "post",
        success : function(resultFlag) {
            if (resultFlag) {
                $("#addsuccessAlert").html('Successfully').show();
                setTimeout(function() {
                    $("#addsuccessAlert").hide();
                    $("#addfailureAlert").hide();
                    $("#trainingCourceId").val("");
                    $("#addKnowledgePoint").val("");
                    $("#addChildKnowledgePoints").val("");
					$("#addDetailtrainningTimeStart").val("");
					$("#addDetailtrainningTimeEnd").val("");
                    $("#addlocation").val("");
                    $("#addParticipants").val("");
                    $('#addTrainingPlanDeatil').modal('hide');
                }, 2000);
            } else {
                $("#addfailureAlert").html('failed').show();
            }
        }
    })

    detailTrainningplan(trainingCourceId);
}


function updateDetailTrainningplanMode(id, childId) {

    var trainingCourceId = $("#parentTrainingName").val();

    loadKnowledgePoint(trainingCourceId);

    loadChildKnowledgePoint(childId);

    queryTrainPlanByAllocationId(id);

    $('#updateTrainingPlanDeatil').modal('show');
}

function queryTrainPlanByAllocationId(id) {

    $.ajax({
        url : path + '/service/TrainingPlan/queryTrainPlanByAllocationId',
        dataType : "json",
        async : true,
        data : {
            "allocationPlanId" : id
        },
        cache : false,
        type : "post",
        success : function(result) {
            $("#updatenowledgePoint").find("option").remove();
            $("#updatenowledgePoint").append("<option value=''>-- Please Select --</option>");
            for (var i = 0; i < globalKnowledgePointList.length; i++) {
                $("#updatenowledgePoint").append(
                    "<option value='" + globalKnowledgePointList[i].knowledgePointId + "'" +
                    ">"
                    + globalKnowledgePointList[i].pointTitle
                    + "</option>");
                if(globalKnowledgePointList[i].knowledgePointId == result.parentTrainingName){
                    $($("#updatenowledgePoint").find('option')[i+1]).attr('selected',true)
                }
            }

            $("#updateChildKnowledge").find("option").remove();
            $("#updateChildKnowledge").append("<option value=''>-- Please Select --</option>");
            for (var i = 0; i < globalChildKnowledgePointList.length; i++) {
                $("#updateChildKnowledge").append(
                    "<option value='" + globalChildKnowledgePointList[i].knowledgePointId + "'" +
                    ">"
                    + globalChildKnowledgePointList[i].pointTitle
                    + "</option>");
                if(globalChildKnowledgePointList[i].knowledgePointId == result.childTrainName){
                    $($("#updateChildKnowledge").find('option')[i+1]).attr('selected',true)
                }
            }
            $("#updateDetailtrainningTime").val(result.trainTime);
            $("#updateDetailtrainningTimeStart").val(result.trainTimeStart);
            $("#updateDetailtrainningTimeEnd").val(result.trainTimeEnd);
            $("#updatelocation").val(result.trainRoom);
            $("#updateparticipants").val(result.participants);
            $("#updateAllocationPlanId").val(result.allocationPlanId)
        }
    })
}

function updateTrainningdetailPlan() {

    var trainingCourceId = $("#parentTrainingName").val();
    var updatenowledgePoint = $("#updatenowledgePoint").val();
    var updateChildKnowledge = $("#updateChildKnowledge").val();
	var updateDetailtrainningTimeStart = $("#updateDetailtrainningTimeStart").val();
	var updateDetailtrainningTimeEnd = $("#updateDetailtrainningTimeEnd").val();
    var updatelocation = $("#updatelocation").val();
    var updateparticipants = $("#updateparticipants").val();
    var updateAllocationPlanId = $("#updateAllocationPlanId").val();
    
    $.ajax({
        url : path + '/service/TrainingPlan/updateTrainPlan',
        dataType : "json",
        async : true,
        data : {
            "trainingCourceId" : trainingCourceId,
            "parentTrainingName" : updatenowledgePoint,
            "childTrainingName" : updateChildKnowledge,
			"trainningTimeStart" : updateDetailtrainningTimeStart,
			"trainningTimeEnd" : updateDetailtrainningTimeEnd,
            "trainingRoom" : updatelocation,
            "participantsNumber" : updateparticipants,
            "allocationPlanId" : updateAllocationPlanId
        },
        cache : false,
        type : "post",
        success : function(resultFlag) {
            if (resultFlag) {
                $("#updatesuccessAlert").html('修改成功').show();
                setTimeout(function() {
                    $("#updatesuccessAlert").hide();
                    $("#updatenowledgePoint").val("");
                    $("#updateChildKnowledge").val("");
					$("#updateDetailtrainningTimeStart").val("");
					$("#updateDetailtrainningTimeEnd").val("");
                    $("#updateparentTrainingName").val("");
                    $("#updateparticipants").val("");
                    $('#updateTrainingPlanDeatil').modal('hide');
                }, 2000);
            } else {
                $("#updatefailureAlert").html('修改失败').show();
            }
        }
    })

    detailTrainningplan(trainingCourceId);
}

function DetailTrainningDetailplanMode(traningId) {

    $("#traningDetailById").val(traningId);

    $('#delcfmModelByTraningDetail').modal('show');
}

function deletedTrainningdetailPlan() {

    var parentTrainingId = $('#parentTrainingName').val();
    var id = $('#traningDetailById').val();


    $.ajax({
        url : path + '/service/TrainingPlan/deleteTrainPlanByAllocationId',
        dataType : "json",
        async : true,
        data : {
            "allocationPlanId" : id
        },
        cache : false,
        type : "post",
        success : function(resultFlag) {
        }
    })

    detailTrainningplan(parentTrainingId);
}


function updateTrainningMode(id,knowledgePoint) {

    globalTreeView=null;
    loadTreeView(id);
/*    loadKnowledgePoint();

    loadChildKnowledgePoint(knowledgePoint);*/
    queryTrainById(id);

    $('#traningEditMode').modal('show');
}


function queryTrainById(id) {

    $.ajax({
        url : path + '/service/trainning/queryTrainingId',
        dataType : "json",
        async : true,
        data : {
                "traningId" : id
               },
        cache : false,
        type : "post",
        success : function(result) {
        	var datee=result.time.split(" ");
            $("#updatetrainningid2").val(result.trainningId);
            $("#updatetrainningName2").val(result.courseName);
            $("#updatetrainningTime").val(datee[0]);
            $("#udatelocation").val(result.location);
            $("#updateteacher").val(result.teacher);
            $("#updatetrainningURL").val(result.url);

            var result =globalTreeView;
            $('#update-treeview-Knowledge').treeview({
                data: result,
                showCheckbox: true,
                highlightSelected: false,
                emptyIcon: '',
                multiSelect: true,
                hierarchicalCheck:true,
                onNodeChecked: function (event,node) {
                    function doCheckedNode(node) {
                        var thisDiv = $('#update-treeview-Knowledge');
                        var parentNode = thisDiv.treeview('getParent', node);
                        if(parentNode && 0 <= parentNode.nodeId) {
                            console.log(parentNode);
                            thisDiv.treeview('checkNode', [ parentNode, { silent: true } ]);
                            doCheckedNode(parentNode);
                        }
                    }
                    doCheckedNode(node);
                },
                onNodeUnchecked: function (event, node) {
                    function doUnCheckedNode(node) {
                        var thisDiv = $('#update-treeview-Knowledge');
                        if(node && node.nodes && 0 < node.nodes.length) {
                            var childNodes = node.nodes;
                            for(var i = 0, len = childNodes.length; i < len; i++) {
                                // 取消选中
                                thisDiv.treeview('uncheckNode', [ childNodes[i].nodeId, { silent: true } ]);
                                // 递归
                                doUnCheckedNode(childNodes[i]);
                            }
                        }
                    }
                    doUnCheckedNode(node);
                }
            }).treeview('collapseAll', {
                silent : false
            });

        }
    })
    $("#updatetrainningid2").hide();
    $("#updateListfailureAlert").hide();
}

$("#KnowledgePoint").change(
    function() {
        $("#ChildKnowledgePoints").empty();
        var KnowledgePointByPid = $(this).val();

        $("#ChildKnowledgePoints").find("option").remove();
        $("#ChildKnowledgePoints").append("<option value=''>-- Please Select --</option>");
        loadChildKnowledgePoint(KnowledgePointByPid);
        for (var i = 0; i < globalChildKnowledgePointList.length; i++) {
            $("#ChildKnowledgePoints").append("<option value='" + globalChildKnowledgePointList[i].knowledgePointId + "'>"
                + globalChildKnowledgePointList[i].pointTitle + "</option>");
        }
    });

$(document).ready(function() {
    $('#addTrainPLanForm').bootstrapValidator({
		message: 'This value is not valid',

        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	addParticipants: {
				validators: {
                    notEmpty: {
                        message: 'please set participants number'
                    },
                    regexp: {
                    	regexp: /^[0-9]*$/,
                        message: 'only can set number'
                    },
                }
            }
        }
    }) .on('success.form.bv', function(e) {
        e.preventDefault();

        var $form     = $(e.target);
            validator = $form.data('bootstrapValidator');
        if(validator){
        	login(e.target);
        }

    }) ;
});

$(document).ready(function() {
    $('#updateTrainPLanForm').bootstrapValidator({
		message: 'This value is not valid',

        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	updateparticipants: {
				validators: {
                    notEmpty: {
                        message: 'please set participants number'
                    },
                    regexp: {
                    	regexp: /^[0-9]*$/,
                        message: 'only can set number'
                    },
                }
            }
        }
    }) .on('success.form.bv', function(e) {
        e.preventDefault();

        var $form     = $(e.target);
            validator = $form.data('bootstrapValidator');
        if(validator){
        	login(e.target);
        }

    }) ;
});


function updateTrainning() {

    var trainningId = $("#updatetrainningid2").val();
    var trainningName = $("#updatetrainningName2").val();
    var trainningTime = $("#updatetrainningTime").val();
    var location = $("#udatelocation").val();
    var teacher = $("#updateteacher").val();
    var trainningURL = $("#updatetrainningURL").val();
    /*var knowledgePoint = $('#update-treeview-Knowledge').treeview('getChecked');*/

    var knowledgePointList="";
    var test=$('#update-treeview-Knowledge').treeview('getChecked', 'knowledgePoint');

    for (var i=0;i<test.length;i++){
        knowledgePointList=test[i].knowledgePoint+','+knowledgePointList
    }

    $.ajax({
        url : path + '/service/trainning/updateTrainingById',
        dataType : "json",
        async : true,
        data : {
            "trainningId" : trainningId,
            "trainningName" : trainningName,
            "trainningTime" : trainningTime,
            "location" : location,
            "teacher" : teacher,
            "trainningURL" : trainningURL,
            "knowledgePoint" : knowledgePointList
        },
        cache : false,
        type : "post",
        success : function(resultFlag) {
            if (resultFlag) {
                $("#updateListsuccessAlert").html('Successfully').show();
                setTimeout(function() {
                    $("#updateListsuccessAlert").hide();
                    $("#updateListfailureAlert").hide();
                    $("#updatetrainningName2").val("");
                    $("#updatetrainningTime").val("");
                    $("#udatelocation").val("");
                    $("#updateteacher").val("");
                    $("#updatetrainningURL").val("");
                    $("#update-treeview-Knowledge").val();
                    $('#traningEditMode').modal('hide');
                }, 2000);
            } else {
                $("#updateListfailureAlert").html('failed').show();
            }
        }
    })
    loadTrainningList();
}

function DeletedDetailTrainningplanMode(traningId) {

    $("#traningById").val(traningId);

    $('#delcfmModelByTraning').modal('show');
}

function deletedTrainningdetail() {

//  var parentTrainingId = $('#parentTrainingName').val();

    var id = $('#traningById').val();
  $.ajax({
      url : path + '/service/trainning/deleteTrainingId',
      dataType : "json",
      async : true,
      data : {
          "traningId" : id
      },
      cache : false,
      type : "post",
      success : function(resultFlag) {
          $("#traningById").val("");
      }
  })

  /*loadTrainningList(pageState);*/
    loadTrainningList();
}
