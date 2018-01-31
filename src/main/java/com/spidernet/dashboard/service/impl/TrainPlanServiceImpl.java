package com.spidernet.dashboard.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.spidernet.dashboard.dao.TrainPlanMapper;
import com.spidernet.dashboard.entity.TrainPlan;
import com.spidernet.dashboard.entity.TrainPlanContainId;
import com.spidernet.dashboard.service.TrainPlanService;

@Service
public class TrainPlanServiceImpl implements TrainPlanService {

	@Resource
	private TrainPlanMapper trainPlanMapper;

	@Override
	public boolean addTrainPlan(TrainPlan TrainPlan) {
		if (trainPlanMapper.addTrainPlan(TrainPlan) > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updateTrainPlanByParentTrainName(TrainPlan TrainPlan) {

		if (trainPlanMapper.updateTrainPlanByParentTrainName(TrainPlan) > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteByAllocationPlanId(int allocationPlanId) {

		if (trainPlanMapper.deleteByAllocationPlanId(allocationPlanId) > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<TrainPlanContainId> queryTrainPlanByTrainCourseId(String TrainCourseId) {
		List<TrainPlan> trainPlanList = trainPlanMapper.queryTrainPlanByTrainCourseId(TrainCourseId);
		List<TrainPlanContainId> trainPlanList2 = new ArrayList<TrainPlanContainId>();
		
		for (TrainPlan trainPlanOb : trainPlanList) {
			TrainPlanContainId trainPlanContainId = new TrainPlanContainId();
			int allocationPlanId = trainPlanOb.getAllocationPlanId();
			String trainTimeStart = trainPlanOb.getTrainTimeStart();
			String trainTimeEnd = trainPlanOb.getTrainTimeEnd();
			String trainRoom = trainPlanOb.getTrainRoom();
			int participants = trainPlanOb.getParticipants();
			String active = trainPlanOb.getActive();
			String trainCourseId = trainPlanOb.getTrainCourseId();

			String parentTrainId = trainPlanOb.getParentTrainingName();
			String parentName = trainPlanMapper.queryParentName(parentTrainId);
			String childTrainId = trainPlanOb.getChildTrainName();
			String childName = trainPlanMapper.queryParentName(childTrainId);

			trainPlanContainId.setActive(active);
			trainPlanContainId.setAllocationPlanId(allocationPlanId);
			trainPlanContainId.setTrainTimeStart(trainTimeStart);
			trainPlanContainId.setTrainTimeEnd(trainTimeEnd);
			trainPlanContainId.setTrainRoom(trainRoom);
			trainPlanContainId.setParticipants(participants);
			trainPlanContainId.setTrainCourseId(trainCourseId);
			trainPlanContainId.setParentTrainingName(parentName);
			trainPlanContainId.setChildTrainName(childName);
			trainPlanContainId.setParentId(parentTrainId);
			trainPlanContainId.setChildId(childTrainId);
			trainPlanList2.add(trainPlanContainId);
		}
		return trainPlanList2;
	}

	@Override
	public TrainPlan queryTrainPlanByAllocationPlanId(String allocationPlanId) {
		TrainPlan trainPlan = trainPlanMapper.queryTrainPlanByAllocationPlanId(allocationPlanId);
		return trainPlan;
	}

}
