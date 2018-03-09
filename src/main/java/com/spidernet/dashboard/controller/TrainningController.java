package com.spidernet.dashboard.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spidernet.dashboard.entity.*;
import com.spidernet.dashboard.service.KnowledgePointService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spidernet.dashboard.service.CapabilityTrainingService;
import com.spidernet.dashboard.service.TrainningService;
import com.spidernet.util.Utils;

@Controller
@RequestMapping("/trainning")
public class TrainningController {
	@Resource
	TrainningService trainningService;

	@Resource
	KnowledgePointService knowledgePointService;

	@Resource
	CapabilityTrainingService capabilityTrainingService;

	private static Logger logger = LoggerFactory.getLogger(TrainningController.class);

	@RequestMapping("/personalTrainningList")
	@ResponseBody
	public Object trainningList(final HttpServletRequest request, final HttpServletResponse response) {
		String capabilityId = request.getParameter("capabilityId");
		String employeeId = ((Employee) request.getSession().getAttribute("employee")).getEmployeeId();

		List<Trainning> trainningList = trainningService.fetchAllTrainning(capabilityId, employeeId);
		/*
		 * Trainning trainning1 = new Trainning(); Trainning trainning2 = new
		 * Trainning();
		 * 
		 * List<Trainning> trainningList = new ArrayList<Trainning>();
		 * 
		 * trainningList.add(trainning1); trainningList.add(trainning2);
		 */
		return trainningList;
	}

	@RequestMapping("/trainningInfoList")
	@ResponseBody
	public Object trainningInfoList(final HttpServletRequest request, final HttpServletResponse response) {
		String trainningName = request.getParameter("trainningName");

		String pageState = request.getParameter("pageState");

		String currentPage = null;

		int countPage = 0;

		TrainningPageCondition trainningPageCondition = new TrainningPageCondition();

		if ("".equals(pageState) || pageState == null) {
			currentPage = "0";
			trainningPageCondition.setTrainningName(trainningName);
			trainningPageCondition.setCurrentPage(currentPage);
			countPage = trainningService.countTrainingPage(trainningPageCondition);
			trainningPageCondition.setPageCount(countPage + "");
			request.getSession().setAttribute("trainningPageCondition", trainningPageCondition);
		} else if ("frist".equals(pageState)) {
			currentPage = "0";
			trainningPageCondition = (TrainningPageCondition) request.getSession()
					.getAttribute("trainningPageCondition");
			trainningPageCondition.setCurrentPage(currentPage);
			request.getSession().setAttribute("trainningPageCondition", trainningPageCondition);
		} else if ("next".equals(pageState)) {
			trainningPageCondition = (TrainningPageCondition) request.getSession()
					.getAttribute("trainningPageCondition");
			currentPage = Integer.parseInt(trainningPageCondition.getCurrentPage()) + 10 + "";
			trainningPageCondition.setCurrentPage(currentPage);
			request.getSession().setAttribute("trainningPageCondition", trainningPageCondition);
		} else if ("previous".equals(pageState)) {
			trainningPageCondition = (TrainningPageCondition) request.getSession()
					.getAttribute("trainningPageCondition");
			currentPage = Integer.parseInt(trainningPageCondition.getCurrentPage()) - 10 + "";
			trainningPageCondition.setCurrentPage(currentPage);
			request.getSession().setAttribute("trainningPageCondition", trainningPageCondition);
		} else if ("last".equals(pageState)) {
			trainningPageCondition = (TrainningPageCondition) request.getSession()
					.getAttribute("trainningPageCondition");
			currentPage = (Integer.parseInt(trainningPageCondition.getPageCount()) - 1) * 10 + "";
			trainningPageCondition.setCurrentPage(currentPage);
			request.getSession().setAttribute("trainningPageCondition", trainningPageCondition);
		}

		List<Trainning> trainningInfoList = trainningService.queryTrainingInfo(trainningPageCondition);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", trainningInfoList);
		result.put("pageInfo", request.getSession().getAttribute("trainningPageCondition"));
		return result;
	}

	@RequestMapping("/addTrainning")
	@ResponseBody
	public Boolean addTrainning(final HttpServletRequest request, final HttpServletResponse response) {
		String trainningId = Utils.getUUID();
		String trainningName = request.getParameter("trainningName");
		String trainningTime = request.getParameter("trainningTime");
		String location = request.getParameter("location");
		String teacher = request.getParameter("teacher");
		String trainningURL = request.getParameter("trainningURL");
		String status = "0";
		String knowledgePoint = request.getParameter("knowledgePoint");
		/*String childKnowledgePoints = request.getParameter("childKnowledgePoints");*/
		String knowledgePointList="";
		String childKnowledgePointList="";
		if(knowledgePoint.length()>10){
		    String[] list =knowledgePoint.substring(0,knowledgePoint.length()-1).split(",");
	        for (int i = 0 ;i<list.length ; i++){
	            KnowledgePoint kp = knowledgePointService.queryKnowledgePointById(list[i]);
	            if (kp.getPid().equals("0"))
	            {
	                knowledgePointList=knowledgePointList+list[i]+",";
	            }else{
	                childKnowledgePointList=childKnowledgePointList+list[i]+",";
	            }
	        }
		}
		

		Trainning trainning = new Trainning();

		trainning.setTrainningId(trainningId);
		trainning.setCourseName(trainningName);
		trainning.setLocation(location);
		trainning.setTime(trainningTime);
		trainning.setTeacher(teacher);
		trainning.setUrl(trainningURL);
		trainning.setStatus(status);
		trainning.setKnowledgePoint(knowledgePointList);
		trainning.setSubTopic(childKnowledgePointList);
		trainning.setCreateTime(new Timestamp(System.currentTimeMillis()));
		boolean resultFlag = trainningService.addTraining(trainning);

		return (resultFlag);
	}

	@RequestMapping("/queryTrainingByName")
	@ResponseBody
	public Object queryTrainingByName(final HttpServletRequest request, final HttpServletResponse response) {
		String trainingName = request.getParameter("trainingName");

		List<Trainning> trainingList = trainningService.queryTrainingByName(trainingName);

		return trainingList;
	}

	@RequestMapping("/queryTrainingName")
	@ResponseBody
	public Object queryTrainingName(final HttpServletRequest request, final HttpServletResponse response) {
		List<Trainning> trainingList = trainningService.queryTrainingName();

		return trainingList;
	}
	@RequestMapping("/queryTrainingPlanName")
    @ResponseBody
    public Object queryTrainingPlanName(final HttpServletRequest request, final HttpServletResponse response) {
	    String pointId = request.getParameter("pointId");
        List<Trainning> trainingList = trainningService.queryTrainingPlanName(pointId);

        return trainingList;
    }

	@RequestMapping("/queryTrainingId")
	@ResponseBody
	public Object queryTrainingById(final HttpServletRequest request, final HttpServletResponse response) {
		String trainningId = request.getParameter("traningId");

		Trainning training = trainningService.queryTrainingById(trainningId);

		return training;
	}

	@RequestMapping("/updateTrainingById")
	@ResponseBody
	public Object updateTrainingById(final HttpServletRequest request, final HttpServletResponse response) {
		String trainningId = request.getParameter("trainningId");
		String trainningName = request.getParameter("trainningName");
		String trainningTime = request.getParameter("trainningTime");
		String location = request.getParameter("location");
		String teacher = request.getParameter("teacher");
		String trainningURL = request.getParameter("trainningURL");
		String status = "0";
		String knowledgePoint = request.getParameter("knowledgePoint");

		String knowledgePointList="";
		String childKnowledgePointList="";
		if(knowledgePoint.length()>10){
		    String[] list =knowledgePoint.substring(0,knowledgePoint.length()-1).split(",");
	        for (int i = 0 ;i<list.length ; i++){
	            KnowledgePoint kp = knowledgePointService.queryKnowledgePointById(list[i]);
	            if (kp.getPid().equals("0"))
	            {
	                knowledgePointList=knowledgePointList+list[i]+",";
	            }else{
	                childKnowledgePointList=childKnowledgePointList+list[i]+",";
	            }
	        }
		}
		
		Trainning trainning = new Trainning();

		trainning.setTrainningId(trainningId);
		trainning.setCourseName(trainningName);
		trainning.setLocation(location);
		trainning.setTime(trainningTime);
		trainning.setTeacher(teacher);
		trainning.setUrl(trainningURL);
		trainning.setStatus(status);
		trainning.setKnowledgePoint(knowledgePointList);
		trainning.setSubTopic(childKnowledgePointList);

		boolean resultFlag = trainningService.updateTraining(trainning);

		return (resultFlag);
	}

	@RequestMapping("/deleteTrainingId")
	@ResponseBody
	public boolean deleteTrainingById(final HttpServletRequest request, final HttpServletResponse response) {
		String trainningId = request.getParameter("traningId");
		if (trainningService.deleteTrainingById(trainningId)) {
			return true;
		}
		return false;
	}

	@RequestMapping("/getTrainingKnowledgeById")
	@ResponseBody
	public Object getTrainingKnowledgeById(final HttpServletRequest request, final HttpServletResponse response) {
		String trainingCourceId = request.getParameter("trainingCourceId");
		Trainning training = trainningService.queryTrainingById(trainingCourceId);
		String[] knowledgePointId =training.getKnowledgePoint().substring(0,training.getKnowledgePoint().length()-1).split(",");
		List<KnowledgePoint> list = new ArrayList<KnowledgePoint>();
		for (String id :knowledgePointId) {
			KnowledgePoint knowledgePoint = knowledgePointService.queryKnowledgePointById(id);
			list.add(knowledgePoint);
		}
		return list;
	}
}
