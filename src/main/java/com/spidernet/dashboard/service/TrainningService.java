package com.spidernet.dashboard.service;

import java.util.List;

import com.spidernet.dashboard.entity.EmpPageCondition;
import com.spidernet.dashboard.entity.Trainning;
import com.spidernet.dashboard.entity.TrainningPageCondition;

public interface TrainningService
{
    List<Trainning> fetchAllTrainning(String capabilityId, String employeeId);
    boolean addTraining(Trainning trainning);
    List<Trainning> queryTrainingInfo(TrainningPageCondition trainningPageCondition);
    int countTrainingPage(TrainningPageCondition trainningPageCondition);
    List<Trainning> queryTrainingByName(String trainingName);
    List<Trainning> queryTrainingName();
    List<Trainning> queryTrainingPlanName(String knowledgePoint);
    Trainning queryTrainingById(String trainingId);
    List<String> queryEmpAllTrainingNames(EmpPageCondition empPageCondition);
    boolean updateTraining(Trainning trainning);
    boolean deleteTrainingById(String trainningId);
}
