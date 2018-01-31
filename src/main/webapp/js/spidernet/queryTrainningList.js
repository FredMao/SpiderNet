$(function() {

    loadTrainningList();
    dateType();

});

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
                    var td3 = $("<td>" + result.data[i].time + "</td>");
                    var td4 = $("<td>" + result.data[i].location + "</td>");
                    var td5 = $("<td>" + result.data[i].teacher + "</td>");
                    var td6 = $("<td><a class='btn btn-primary btn-sm' href='javascript:void(0);' onclick=updateTrainningMode('"+result.data[i].trainningId+"','"+result.data[i].knowledgePoint+"')>Edit</a></td>");
                    // var td6 = $("<td><a class='btn btn-info'
                    // href='javascript:void(0);'> <i class='glyphicon
                    // glyphicon-edit icon-white'></i> Edit</a></td>");
                    // var td7 = $("<td><a href='javascript:void(0);'
                    // onclick=getEr('"+result.data[i].er+"')>Edit</a></td>");
                    td1.appendTo(tr);
                    td2.appendTo(tr);
                    td3.appendTo(tr);
                    td4.appendTo(tr);
                    td5.appendTo(tr);
                    td6.appendTo(tr);

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
        format : 'yyyy-mm-dd hh:ii:ss',
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

    loadKnowledgePoint();

    $("#KnowledgePoint").find("option").remove();
    $("#KnowledgePoint").append("<option value=''>-- Please Select --</option>");
    for (var i = 0; i < globalKnowledgePointList.length; i++) {
        $("#KnowledgePoint").append(
            "<option value='" + globalKnowledgePointList[i].knowledgePointId + "'" +
            ">"
            + globalKnowledgePointList[i].pointTitle
            + "</option>");
    }
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
    var knowledgePoint = $("#KnowledgePoint").val();
    var childKnowledgePoints = $("#ChildKnowledgePoints").val();

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
            "knowledgePoint" : knowledgePoint,
            "childKnowledgePoints" : childKnowledgePoints
        },
        cache : false,
        type : "post",
        success : function(resultFlag) {
            if (resultFlag) {
                $("#successAlert").html('添加成功').show();
                setTimeout(function() {
                    $("#successAlert").hide();
                    $("#addtrainningName2").val("");
                    $("#trainningTime").val("");
                    $("#location").val("");
                    $("#teacher").val("");
                    $("#trainningURL").val("");
                    $("#KnowledgePoint").val();
                    $("#ChildKnowledgePoints").val();
                    $('#myModal').modal('hide');
                }, 2000);
            } else {
                $("#failureAlert").html('添加失败').show();
            }
        }
    })
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

function loadKnowledgePoint() {
    $.ajax({
        url : path + '/service/knowledge/getKnowledgePointByPid',
        dataType : "json",
        async : false,
        data : {
            "pid" : "0",
            "status" : "0",
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
                        + "<a class='btn btn-danger btn-xs' href='javascript:void(0);' onclick=deletedTrainningdetailPlan('"
                        + result[i].allocationPlanId
                        + "','"
                        + result[i].TrainCourseId
                        + "')>Deleted</a>"
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

    loadKnowledgePoint();

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
                $("#addsuccessAlert").html('添加成功').show();
                setTimeout(function() {
                    $("#addsuccessAlert").hide();
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
                $("#addfailureAlert").html('添加失败').show();
            }
        }
    })

    detailTrainningplan(trainingCourceId);
}


function updateDetailTrainningplanMode(id, childId) {

    loadKnowledgePoint();
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
            $("#updatelocation").val(result.trainRoom);
            $("#updateparticipants").val(result.participants);
            $("#updateAllocationPlanId").val(result.allocationPlanId)
        }
    })
}


//有可能没有用
/*function loadupdateKnowledgePoint() {
    $.ajax({
        url : path + '/service/knowledge/getKnowledgePointByPid',
        dataType : "json",
        async : true,
        data : {
            "pid" : "0",
            "status" : "0",
        },
        cache : false,
        type : "post",
        success : function(knowledgePointList) {
            $("#updatenowledgePoint").find("option").remove();
            $("#updatenowledgePoint").append(
                "<option value=''>-- Please Select --</option>");
            for (var i = 0; i < knowledgePointList.length; i++) {
                $("#updatenowledgePoint").append(
                    "<option value='"
                    + knowledgePointList[i].knowledgePointId + "'>"
                    + knowledgePointList[i].pointTitle
                    + "</option>");
                $("#updateKnowledgePoint").append(
                    "<option value='"
                    + knowledgePointList[i].knowledgePointId + "'>"
                    + knowledgePointList[i].pointTitle
                    + "</option>");
            }
        }
    })
}*/

/*$("#updatenowledgePoint").change(function() {

    $("#updateChildKnowledge").empty();
    var KnowledgePointByPid = $(this).val();

    loadupdateChildKnowledgePoint(KnowledgePointByPid);

});*/


//有可能没用
/*function loadupdateChildKnowledgePoint(KnowledgePointByPid) {

    $.ajax({
        url : path + '/service/knowledge/getKnowledgePointByPid',
        dataType : "json",
        async : true,
        data : {
            "pid" : KnowledgePointByPid,
            "status" : "0",
        },
        cache : false,
        type : "post",
        success : function(knowledgePointList) {
            $("#updateChildKnowledge").find("option").remove();
            $("#updateChildKnowledge").append(
                "<option value=''>-- Please Select --</option>");
            for (var i = 0; i < knowledgePointList.length; i++) {
                $("#updateChildKnowledge").append(
                    "<option value='"
                    + knowledgePointList[i].knowledgePointId + "'>"
                    + knowledgePointList[i].pointTitle
                    + "</option>");
                $("#updateChildKnowledgePoints").append(
                    "<option value='"
                    + knowledgePointList[i].knowledgePointId + "'>"
                    + knowledgePointList[i].pointTitle
                    + "</option>");
            }
        },
        error : function() {
            alert("请求失败")
        }
    });
}*/

function updateTrainningdetailPlan() {

    var trainingCourceId = $("#parentTrainingName").val();
    var updatenowledgePoint = $("#updatenowledgePoint").val();
    var updateChildKnowledge = $("#updateChildKnowledge").val();
	var updateDetailtrainningTimeStart = $("#updateDetailtrainningTimeStart").val();
	var updateDetailtrainningTimeEnd = $("#updateDetailtrainningTimeEnd").val();
    var updatelocation = $("#updatelocation").val();
    var updateparticipants = $("#updateparticipants").val();
    var updateAllocationPlanId = $("#updateAllocationPlanId").val();

    validatesForm();
    
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

function deletedTrainningdetailPlan(id) {

    var parentTrainingId = $('#parentTrainingName').val();

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

    loadKnowledgePoint();

    loadChildKnowledgePoint(knowledgePoint);
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
            $("#updatetrainningName2").val(result.courseName);
            $("#updatetrainningTime").val(result.time);
            $("#udatelocation").val(result.location);
            $("#updateteacher").val(result.teacher);
            $("#updatetrainningURL").val(result.url);

            $("#updateKnowledgePoint").find("option").remove();
            $("#updateKnowledgePoint").append("<option value=''>-- Please Select --</option>");
            for (var i = 0; i < globalKnowledgePointList.length; i++) {
                $("#updateKnowledgePoint").append(
                    "<option value='" + globalKnowledgePointList[i].knowledgePointId + "'" +
                    ">"
                    + globalKnowledgePointList[i].pointTitle
                    + "</option>");
                if(globalKnowledgePointList[i].knowledgePointId == result.knowledgePoint){
                    $($("#updateKnowledgePoint").find('option')[i+1]).attr('selected',true)
                }
            }

            $("#updateChildKnowledgePoints").find("option").remove();
            $("#updateChildKnowledgePoints").append("<option value=''>-- Please Select --</option>");
            for (var i = 0; i < globalChildKnowledgePointList.length; i++) {
                $("#updateChildKnowledgePoints").append(
                    "<option value='" + globalChildKnowledgePointList[i].knowledgePointId + "'" +
                    ">"
                    + globalChildKnowledgePointList[i].pointTitle
                    + "</option>");
                if(globalChildKnowledgePointList[i].knowledgePointId == result.subTopic){
                    $($("#updateChildKnowledgePoints").find('option')[i+1]).attr('selected',true)
                }
            }
        }
    })
}

/*
$("#KnowledgePoint").change(
        function() {
            $("#ChildKnowledgePoints").empty();
            var KnowledgePointByPid = $(this).val();
            $.ajax({
                    url : path
                    + '/service/knowledge/getKnowledgePointByPid',
                    dataType : "json",
                    async : true,
                    data : {
                        "pid" : KnowledgePointByPid,
                        "status" : "0",
                    },
                    cache : false,
                    type : "post",
                    success : function(knowledgePointList) {

                        $("#ChildKnowledgePoints").find("option").remove();
                        $("#ChildKnowledgePoints").append(
                                "<option value=''>-- Please Select --</option>");
                        for (var i = 0; i < knowledgePointList.length; i++) {
                            $("#ChildKnowledgePoints").append(
                                    "<option value='" + knowledgePointList[i].knowledgePointId + "'>"
                                    + knowledgePointList[i].pointTitle
                                    + "</option>");
                        }
                    },
                    error : function() {
                        alert("请求失败")
                    }
                });
        });*/

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
