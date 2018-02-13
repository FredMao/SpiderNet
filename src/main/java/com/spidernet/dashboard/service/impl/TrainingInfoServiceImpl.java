package com.spidernet.dashboard.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.spidernet.dashboard.dao.EmployeeMapper;
import com.spidernet.dashboard.dao.PersonalTrainningMapper;
import com.spidernet.dashboard.dao.TrainingInfoMapper;
import com.spidernet.dashboard.entity.EmpPageCondition;
import com.spidernet.dashboard.entity.PersonalTrainning;
import com.spidernet.dashboard.entity.TrainingInfo;
import com.spidernet.dashboard.service.PersonalTrainningService;
import com.spidernet.dashboard.service.TrainingInfoService;

@Service
public class TrainingInfoServiceImpl implements TrainingInfoService
{

	@Resource
    private TrainingInfoMapper trainingInfoMapper;
	
	@Override
	public List<TrainingInfo> queryAllEmpTrainingInfoList(EmpPageCondition empPageCondition) {
		// TODO Auto-generated method stub
		List<TrainingInfo> listE=trainingInfoMapper.queryAllEmpTrainingInfoList(empPageCondition);
		return listE;
	}

	@Override
	public List<TrainingInfo> querySpecificTrainingPassedPersonList(EmpPageCondition empPageCondition) {
		// TODO Auto-generated method stub
		List<TrainingInfo> listE=trainingInfoMapper.querySpecificTrainingPassedPersonList(empPageCondition);
		return listE;
	}
	
	@Override
	public List<TrainingInfo> queryAllEmpPassedTrainingInfoList(EmpPageCondition empPageCondition){
		List<TrainingInfo> listE=trainingInfoMapper.queryAllEmpPassedTrainingInfoList(empPageCondition);
		return listE;
		
	}

	@Override
	public List<TrainingInfo> querySpecificTrainingAllPersonList(EmpPageCondition empPageCondition) {
		// TODO Auto-generated method stub
		List<TrainingInfo> listE=trainingInfoMapper.querySpecificTrainingAllPersonList(empPageCondition);
		return listE;
	}

	@Override
	public int countPage(EmpPageCondition pageCondition) {
		// TODO Auto-generated method stub
		 return trainingInfoMapper.countPage(pageCondition)/10 + 1;
	}

	@Override
	public List<TrainingInfo> queryEmpPassedTrainingsDetailInfo(String erId) {
		// TODO Auto-generated method stub
		List<TrainingInfo> listE=trainingInfoMapper.queryEmpPassedTrainingsDetailInfo(erId);
		return listE;
	}

	@Override
	public List<TrainingInfo> queryEmpUncompletedTrainingsDetailInfo(String erId) {
		// TODO Auto-generated method stub
		List<TrainingInfo> listE=trainingInfoMapper.queryEmpUncompletedTrainingsDetailInfo(erId);
		return listE;
	}

	@Override
	public List<TrainingInfo> queryEmpPassedTrainingsDetailInfoByManyConditions(String erId, String trainingName) {
		// TODO Auto-generated method stub
		List<TrainingInfo> listE=trainingInfoMapper.queryEmpPassedTrainingsDetailInfoByManyConditions(erId,trainingName);
		return listE;
	}

	@Override
	public List<TrainingInfo> queryEmpUncompletedTrainingsDetailInfoByManyConditions(String erId, String trainingName) {
		// TODO Auto-generated method stub
		List<TrainingInfo> listE=trainingInfoMapper.queryEmpUncompletedTrainingsDetailInfoByManyConditions(erId,trainingName);
		return listE;
	}
    
}
