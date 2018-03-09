package com.spidernet.dashboard.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spidernet.dashboard.entity.EmpPageCondition;
import com.spidernet.dashboard.entity.Trainning;
import com.spidernet.dashboard.entity.TrainningPageCondition;

public interface TrainningMapper
{
    List<Trainning> fetchAllTrainning(@Param("capabilityId")String capabilityId, @Param("employeeId")String employeeId);
    int addTraining(Trainning trainning);
    List<Trainning> queryTrainingInfo(TrainningPageCondition trainningPageCondition);
    int countTrainingPage(TrainningPageCondition trainningPageCondition);
    List<Trainning> queryTrainingByName(String trainingName);
    List<Trainning> queryTrainingName();
    List<Trainning> queryTrainingPlanName(String pointId);
    Trainning queryTrainingById(String trainingId);
    List<String> queryPersonTrainingNames(EmpPageCondition empPageCondition);
    int updateTraining(Trainning trainning);
    int deleteTrainingById(String trainningId);
    String queryKnowedgePointEntity (String pointId);
}

