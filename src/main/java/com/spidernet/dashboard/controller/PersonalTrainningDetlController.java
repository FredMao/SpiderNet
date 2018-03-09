package com.spidernet.dashboard.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spidernet.dashboard.entity.CCapability;
import com.spidernet.dashboard.entity.CapabilityMap;
import com.spidernet.dashboard.entity.Employee;
import com.spidernet.dashboard.entity.PersonalMap;
import com.spidernet.dashboard.entity.PersonalTrainning;
import com.spidernet.dashboard.entity.PlanPersonalTrainning;
import com.spidernet.dashboard.entity.ProCapability;
import com.spidernet.dashboard.service.CCapabilityService;
import com.spidernet.dashboard.service.CapabilityTrainingService;
import com.spidernet.dashboard.service.EmployeeService;
import com.spidernet.dashboard.service.PersonalMapService;
import com.spidernet.dashboard.service.PersonalTrainningService;
import com.spidernet.dashboard.service.ProCapabilityService;
import com.spidernet.dashboard.service.TrainningService;
import com.spidernet.util.Constants;
import com.spidernet.util.XmlUtil;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/trainning")
public class PersonalTrainningDetlController
{
    @Resource
    private PersonalTrainningService personalTrainningService;

    @Resource
    private CapabilityTrainingService capabilityTrainingService;

    @Resource
    private CCapabilityService ccapabilityService;

    @Resource
    private ProCapabilityService proCapabilityService;

    @Resource
    private PersonalMapService personalMapService;
    
    @Resource
    private EmployeeService employeeService;
    
    @Resource
    TrainningService trainningService;

    private static Logger logger = LoggerFactory
            .getLogger(PersonalTrainningDetlController.class);

    @RequestMapping("/addPersonalTrainningDetl")
    @ResponseBody
    public Boolean addPersonalTrainningDetl(final HttpServletRequest request,
            final HttpServletResponse response)
    {

        logger.debug("Add the personal trainning detail begin");
        ProCapability proCapability = null;
        CCapability commonCapability = null;
        PersonalMap personalMap = null;
        Boolean addResultFlag = false;
        String capabilityId = null;

        String employeeId = ((Employee) request.getSession()
                .getAttribute("employee")).getEmployeeId();
        String selectedTrainning = request
                .getParameter("selectedTrainningArray");

        JSONArray selectedTrainningArray = JSONArray
                .fromObject(selectedTrainning);

        List<PersonalTrainning> personalTrainningList = new ArrayList<PersonalTrainning>();

        PersonalTrainning personalTrainning = null;
        personalMap = personalMapService.fetchByEmpId(employeeId);
        String personalDetail = personalMap.getDetail();
        CapabilityMap capabilityMap = (CapabilityMap) XmlUtil
                .convertXmlStrToObject(CapabilityMap.class,
                        personalMap.getDetail());

        for (int i = 0; i < selectedTrainningArray.size(); i++)
        {
            personalTrainning = new PersonalTrainning();

            personalTrainning.setEmployeeId(employeeId);
            personalTrainning.setTrainningId(
                    new JSONObject(selectedTrainningArray.get(i).toString())
                            .get("trainningId").toString());
            personalTrainning.setStatus(Constants.TRAINNING_STATUS_REGISTED);

            if (personalTrainningService
                    .checkPersonalTrainningExists(personalTrainning))
            {
                return false;
            }
            personalTrainningList.add(personalTrainning);
        }

        if (personalTrainningList.size() > 0)
        {
            capabilityId = capabilityTrainingService
                    .fetchCapabilityIdByTrainningId(
                            personalTrainningList.get(0).getTrainningId());
        }

        commonCapability = ccapabilityService
                .fetchCommonCapabilty(capabilityId);

        proCapability = proCapabilityService
                .fetchProCapabilityByCapabilityId(capabilityId);

        for (int i = 0; i < capabilityMap.getCapabilityMap().size(); i++)
        {

            if (proCapability != null)
            {
                for (int j = 0; j < capabilityMap.getCapabilityMap().get(i)
                        .getProCapabilityL().size(); j++)
                {
                    if (proCapability.getProCapabilityId()
                            .equals(capabilityMap.getCapabilityMap().get(i)
                                    .getProCapabilityL().get(j)
                                    .getProCapabilityId()))
                    {
                        capabilityMap.getCapabilityMap().get(i)
                                .getProCapabilityL().get(j).setTrainingStatus(
                                        Constants.TRAINNING_STATUS_REGISTED);
                    }
                }
            }
            else if (commonCapability != null)
            {
                for (int j = 0; j < capabilityMap.getCapabilityMap().get(i)
                        .getcCapabilityL().size(); j++)
                {
                    if (commonCapability.getCommCapabilityId()
                            .equals(capabilityMap.getCapabilityMap().get(i)
                                    .getcCapabilityL().get(j)
                                    .getCommCapabilityId()))
                    {
                        capabilityMap.getCapabilityMap().get(i)
                                .getcCapabilityL().get(j).setTrainingStatus(
                                        Constants.TRAINNING_STATUS_REGISTED);
                    }
                }

            }
        }

        personalDetail = XmlUtil.convertToXml(capabilityMap);

        personalMap.setDetail(personalDetail);
        personalMapService.updatePersonalMap(personalMap);

        addResultFlag = personalTrainningService
                .addPersonalTrainning(personalTrainningList);

        return addResultFlag;

    }
    
    
    @RequestMapping("/batchAddTraining")
    @ResponseBody
    public Boolean batchAddTraining(final HttpServletRequest request,
            final HttpServletResponse response,final String[] empArray)
    {
        List<PersonalTrainning> personalTrainningList = new ArrayList<PersonalTrainning>();

        PersonalTrainning personalTrainning = null;
        
        String employeeId = "";
        
        String trainingName = request.getParameter("trainingName");
        
        boolean addResultFlag;
        
        String trainingId = trainningService.queryTrainingByName(trainingName).get(0).getTrainningId();
        if(empArray!=null) {
        
          for(int i = 0; i < empArray.length; i++){
            
            employeeId = employeeService.fetchByErNumber(empArray[i]).getEmployeeId();
            
            personalTrainning = new PersonalTrainning();

            personalTrainning.setEmployeeId(employeeId);
            personalTrainning.setTrainningId(trainingId);
            personalTrainning.setStatus(Constants.TRAINNING_STATUS_REGISTED);
            
            if (personalTrainningService.checkPersonalTrainningExists(personalTrainning))
            {
                continue;
            }
            
            personalTrainningList.add(personalTrainning);
        }
        
         addResultFlag = personalTrainningService.addPersonalTrainning(personalTrainningList);
        }
        else {
        	addResultFlag =false;	
        } 
        
        return addResultFlag;
    }
    @RequestMapping("/batchAddPlanTraining")
    @ResponseBody
    public Boolean batchAddPlanTraining(final HttpServletRequest request,
            final HttpServletResponse response,final String[] empArray)
    {
        List<PlanPersonalTrainning> planPersonalTrainningList = new ArrayList<PlanPersonalTrainning>();

        PlanPersonalTrainning planPersonalTrainning = null;
        
        String employeeId = "";
        
        String trainingId = request.getParameter("trainingId");
        String planId = request.getParameter("planId");
        
        boolean addResultFlag;
        
        if(empArray!=null) {
        
          for(int i = 0; i < empArray.length; i++){
            
            employeeId = employeeService.fetchByErNumber(empArray[i]).getEmployeeId();
            
            planPersonalTrainning = new PlanPersonalTrainning();

            planPersonalTrainning.setEmployeeId(employeeId);
            planPersonalTrainning.setTrainningId(trainingId);
            planPersonalTrainning.setStatus(Constants.TRAINNING_STATUS_REGISTED);
            planPersonalTrainning.setPlanId(planId);
            
            if (personalTrainningService.checkPlanPersonalTrainningExists(planPersonalTrainning))
            {
                continue;
            }
            
            planPersonalTrainningList.add(planPersonalTrainning);
        }
        
         addResultFlag = personalTrainningService.addPlanPersonalTrainning(planPersonalTrainningList);
        }
        else {
            addResultFlag =false;   
        } 
        
        return addResultFlag;
    }
    
    @RequestMapping("/trainingOperation")
    @ResponseBody
    public Object trainingOperation(final HttpServletRequest request,
            final HttpServletResponse response)
    {

		String ername = request.getParameter("ername");
		String trname = request.getParameter("trname");
		String type=request.getParameter("type");
	
		Map<String, Integer> result = new HashMap<String, Integer>();
		if("1".equals(type)) {
			int result1=personalTrainningService
					.updateEmpTrainingInfo(ername, trname);
					
					
		Integer resultFlag = Integer.valueOf(result1);
				result.put("result", resultFlag);
		}else {
		
			Integer resultFlag = Integer.valueOf(personalTrainningService
			.deleteEmpTrainingInfo(ername, trname));
			result.put("result", resultFlag);
			
		}
		
		return result;
    }
   

}
