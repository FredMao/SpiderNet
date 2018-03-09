package com.spidernet.dashboard.service;

import java.util.List;

import com.spidernet.dashboard.entity.PersonalTrainning;
import com.spidernet.dashboard.entity.PlanPersonalTrainning;

public interface PersonalTrainningService
{
    Boolean addPersonalTrainning(List<PersonalTrainning> personalTrainning);
    Boolean checkPersonalTrainningExists(PersonalTrainning personalTrainning);
    Boolean addPlanPersonalTrainning(List<PlanPersonalTrainning> planPersonalTrainning);
    Boolean checkPlanPersonalTrainningExists(PlanPersonalTrainning planPersonalTrainning);
    int deleteEmpTrainingInfo(String ername, String trname);

	int updateEmpTrainingInfo(String ername, String trname);
}
