package com.spidernet.dashboard.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.spidernet.dashboard.dao.PersonalTrainningMapper;
import com.spidernet.dashboard.dao.PlanPersonalTrainningMapper;
import com.spidernet.dashboard.entity.PersonalTrainning;
import com.spidernet.dashboard.entity.PlanPersonalTrainning;
import com.spidernet.dashboard.service.PersonalTrainningService;

@Service
public class PersonalTrainningServiceImpl implements PersonalTrainningService
{
    @Resource
    private PersonalTrainningMapper personalTrainningMapper;
    
    @Resource
    private PlanPersonalTrainningMapper planPersonalTrainningMapper;

    @Override
    public Boolean addPersonalTrainning(List<PersonalTrainning> personalTrainningList)
    {
        if (personalTrainningMapper.addPersonalTrainning(personalTrainningList) > 0)
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    @Override
    public Boolean checkPersonalTrainningExists(
            PersonalTrainning personalTrainning)
    {
        if(personalTrainningMapper.checkPersonalTrainningExists(personalTrainning) > 0){
            return true;
        }else{
            return false;
        }
    }
    
    @Override
    public Boolean addPlanPersonalTrainning(List<PlanPersonalTrainning> personalTrainningList)
    {
        if (planPersonalTrainningMapper.addPlanPersonalTrainning(personalTrainningList) > 0)
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    @Override
    public Boolean checkPlanPersonalTrainningExists(
            PlanPersonalTrainning personalTrainning)
    {
        if(planPersonalTrainningMapper.checkPlanPersonalTrainningExists(personalTrainning) > 0){
            return true;
        }else{
            return false;
        }
    }
    
    public int updateEmpTrainingInfo(String ername, String trname) 
    {
    	int result=personalTrainningMapper.updateEmpTrainingInfo(ername, trname);
        return result ;
    }

	public int deleteEmpTrainingInfo(String ername, String trname) 
	{

		int result= personalTrainningMapper.deleteEmpTrainingInfo(ername, trname);
        return result;
	}

}
