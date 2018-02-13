package com.spidernet.dashboard.service;

import java.util.List;

import com.spidernet.dashboard.entity.EmpPageCondition;
import com.spidernet.dashboard.entity.TrainingInfo;


public interface TrainingInfoService
{

	List<TrainingInfo> queryAllEmpTrainingInfoList(EmpPageCondition empPageCondition);

	List<TrainingInfo> querySpecificTrainingPassedPersonList(EmpPageCondition empPageCondition);

	List<TrainingInfo> queryAllEmpPassedTrainingInfoList(EmpPageCondition empPageCondition);

	List<TrainingInfo> querySpecificTrainingAllPersonList(EmpPageCondition empPageCondition);

	int countPage(EmpPageCondition pageCondition);

	List<TrainingInfo> queryEmpPassedTrainingsDetailInfo(String erId);
	
	List<TrainingInfo> queryEmpPassedTrainingsDetailInfoByManyConditions(String erId, String trainingName);

	List<TrainingInfo> queryEmpUncompletedTrainingsDetailInfo(String erId);

	List<TrainingInfo> queryEmpUncompletedTrainingsDetailInfoByManyConditions(String erId, String trainingName);


       

}
