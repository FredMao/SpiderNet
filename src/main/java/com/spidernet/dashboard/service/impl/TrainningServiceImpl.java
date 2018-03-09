package com.spidernet.dashboard.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.spidernet.dashboard.dao.TrainningMapper;
import com.spidernet.dashboard.entity.EmpPageCondition;
import com.spidernet.dashboard.entity.TrainPlan;
import com.spidernet.dashboard.entity.Trainning;
import com.spidernet.dashboard.entity.TrainningPageCondition;
import com.spidernet.dashboard.service.TrainningService;

@Service
public class TrainningServiceImpl implements TrainningService {

	@Resource
	private TrainningMapper trainningMapper;

	@Override
	public List<Trainning> fetchAllTrainning(String capabilityId, String employeeId) {
		// TODO Auto-generated method stub
		List<Trainning> trainningList = new ArrayList<Trainning>();

		trainningList = trainningMapper.fetchAllTrainning(capabilityId, employeeId);
		return trainningList;
	}

	@Override
	public boolean addTraining(Trainning trainning) {
		if (trainningMapper.addTraining(trainning) > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Trainning> queryTrainingInfo(TrainningPageCondition trainningPageCondition) {
		List<Trainning> trainningList = trainningMapper.queryTrainingInfo(trainningPageCondition);
		List<Trainning> trainningList2 = new ArrayList<Trainning>();
		for (Trainning trainOb : trainningList) {
			Trainning trainning = new Trainning();
			String ssk[] = null;
			String sss[] = null;
			String trainningId = trainOb.getTrainningId();
			String courseName = trainOb.getCourseName();
			String time = trainOb.getTime();
			String location = trainOb.getLocation();
			String teacher = trainOb.getTeacher();
			String url = trainOb.getUrl();
			String status = trainOb.getStatus();
			String knowledgePoint = trainOb.getKnowledgePoint();
			String SubTopic = trainOb.getSubTopic();

			String knowledgePointEntity = "";
			String SubTopicEntity = "";

			if (knowledgePoint == null || knowledgePoint.isEmpty()) {
				knowledgePointEntity = "";
			} else if (knowledgePoint.contains(",")) {

				ssk = knowledgePoint.split(",");
				for (int i = 0; i < ssk.length; i++) {
					if (i == 0) {
						knowledgePointEntity = knowledgePointEntity + ""
								+ trainningMapper.queryKnowedgePointEntity(ssk[i]);
					} else {
						knowledgePointEntity = knowledgePointEntity + ";"
								+ trainningMapper.queryKnowedgePointEntity(ssk[i]);
					}
				}
			} else {
				knowledgePointEntity = trainningMapper.queryKnowedgePointEntity(knowledgePoint);
			}

			if (SubTopic == null || SubTopic.isEmpty()) {
				SubTopicEntity = "";
			} else if (SubTopic.contains(",")) {
				sss = SubTopic.split(",");
				for (int i = 0; i < sss.length; i++) {
					if (i == 0) {
						SubTopicEntity = SubTopicEntity + "" + trainningMapper.queryKnowedgePointEntity(sss[i]);
					} else {
						SubTopicEntity = SubTopicEntity + ";" + trainningMapper.queryKnowedgePointEntity(sss[i]);
					}
				}
			} else {
				SubTopicEntity = trainningMapper.queryKnowedgePointEntity(SubTopic);
			}

			trainning.setTrainningId(trainningId);
			trainning.setCourseName(courseName);
			trainning.setTime(time);
			trainning.setLocation(location);
			trainning.setTeacher(teacher);
			trainning.setUrl(url);
			trainning.setStatus(status);
			trainning.setStatus(status);
			trainning.setKnowledgePoint(knowledgePointEntity);
			trainning.setSubTopic(SubTopicEntity);
			trainningList2.add(trainning);

		}
		return trainningList2;
	}

	@Override
	public int countTrainingPage(TrainningPageCondition trainningPageCondition) {
		return trainningMapper.countTrainingPage(trainningPageCondition) / 10 + 1;
	}

	@Override
	public List<Trainning> queryTrainingByName(String trainingName) {
		List<Trainning> trainingList = trainningMapper.queryTrainingByName(trainingName);
		return trainingList;
	}

	@Override
	public List<Trainning> queryTrainingName() {
		List<Trainning> trainingList = trainningMapper.queryTrainingName();

		return trainingList;
	}
	@Override
    public List<Trainning> queryTrainingPlanName(String knowlegePoint) {
        List<Trainning> trainingList = trainningMapper.queryTrainingPlanName(knowlegePoint);

        return trainingList;
    }

	@Override
	public Trainning queryTrainingById(String trainingId) {
		Trainning trainning = trainningMapper.queryTrainingById(trainingId);
		return trainning;
	}

	public List<String> queryEmpAllTrainingNames(EmpPageCondition empPageCondition) {
		// TODO Auto-generated method stub
		List<String> trainingName = trainningMapper.queryPersonTrainingNames(empPageCondition);
		return trainingName;
	}

	@Override
	public boolean updateTraining(Trainning trainning) {
		if (trainningMapper.updateTraining(trainning) > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteTrainingById(String trainningId) {

		if (trainningMapper.deleteTrainingById(trainningId) > 0) {
			return true;
		}
		return false;
	}

}
