package com.spidernet.dashboard.service;

import java.util.List;

import com.spidernet.dashboard.entity.EmployeeInfo;
import com.spidernet.dashboard.entity.PageCondition;
import com.spidernet.dashboard.entity.TrainingInfo;

public interface EmployeeInfoService
{
    
    List<EmployeeInfo> queryEmpInfo(PageCondition pageCondition);
    
    List<EmployeeInfo> queryEmpPlanInfo(PageCondition pageCondition);
    
    int countPage(PageCondition pageCondition);

    int countPlanPage(PageCondition pageCondition);
    List<EmployeeInfo> queryEmpList(String trainingId);
    
    List<TrainingInfo> queryEmpTrainingInfo(String erId);
    
    int configRule(String er, String rule);
    String queryRuleByEr(String er);

}
