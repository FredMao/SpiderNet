package com.spidernet.dashboard.dao;

import java.util.List;

import com.spidernet.dashboard.entity.EmpPageCondition;
import com.spidernet.dashboard.entity.EmployeeInfo;
import com.spidernet.dashboard.entity.PageCondition;
import com.spidernet.dashboard.entity.TrainingInfo;

public interface TrainingInfoMapper
{

	List<TrainingInfo> queryAllEmpTrainingInfoList(EmpPageCondition empPageCondition);

	List<TrainingInfo> querySpecificTrainingPassedPersonList(EmpPageCondition empPageCondition);

	List<TrainingInfo> queryAllEmpPassedTrainingInfoList(EmpPageCondition empPageCondition);

	List<TrainingInfo> querySpecificTrainingAllPersonList(EmpPageCondition empPageCondition);

	int countPage(EmpPageCondition pageCondition);

	List<TrainingInfo> queryEmpPassedTrainingsDetailInfo(String erId);

	List<TrainingInfo> queryEmpUncompletedTrainingsDetailInfo(String erId);

	List<TrainingInfo> queryEmpPassedTrainingsDetailInfoByManyConditions(String erId, String trainingName);

	List<TrainingInfo> queryEmpUncompletedTrainingsDetailInfoByManyConditions(String erId, String trainingName);

}
