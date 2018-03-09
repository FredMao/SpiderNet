package com.spidernet.dashboard.dao;

import java.util.List;

import com.spidernet.dashboard.entity.PersonalTrainning;
import com.spidernet.dashboard.entity.PlanPersonalTrainning;

public interface PlanPersonalTrainningMapper
{
    int addPlanPersonalTrainning(List<PlanPersonalTrainning> planPersonalTrainningList);
    int checkPlanPersonalTrainningExists(PlanPersonalTrainning planPersonalTrainning);
    int updateEmpTrainingInfo(String ername, String trname);
    int deleteEmpTrainingInfo(String ername, String trname);
}
