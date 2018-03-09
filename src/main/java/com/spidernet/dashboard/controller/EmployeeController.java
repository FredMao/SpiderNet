package com.spidernet.dashboard.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spidernet.dashboard.entity.Employee;
import com.spidernet.dashboard.service.EmployeeService;

@Controller
@RequestMapping(value="/employee")
public class EmployeeController {
	@Resource
	private EmployeeService userService;
	
	private static Logger logger = LoggerFactory
            .getLogger(EmployeeController.class);

    @RequestMapping("/register")
    public String register(final HttpServletRequest request,
            final HttpServletResponse response)
    {
        return "employee/register";
    }
    
    @RequestMapping("/update")
    public String update(final HttpServletRequest request,
            final HttpServletResponse response)
    {
        String erNum = request.getParameter("erNum");
        request.setAttribute("erNum", erNum);
        return "employee/update";
    }
    
    @RequestMapping("/update2")
    public String update2(final HttpServletRequest request,
            final HttpServletResponse response)
    {
        return "employee/update2";
    }
    
    
    @RequestMapping("/scoreImport")
    public String scoreImport(final HttpServletRequest request,
            final HttpServletResponse response)
    {
        return "employee/scoreImport";
    }
    
    @RequestMapping("/capabilityMap")
    public String capabilityMap(final HttpServletRequest request,
            final HttpServletResponse response)
    {
        return "employee/capabilityMap";
    }
    
    @RequestMapping("/exam")
    public String exam(final HttpServletRequest request,
            final HttpServletResponse response)
    {
        return "employee/exam";
    }
    
    @RequestMapping("/training")
    public String training(final HttpServletRequest request,
            final HttpServletResponse response)
    {
        return "employee/training";
    }
    
    @RequestMapping("/batchAddCapability")
    public String batchAddCapability(final HttpServletRequest request,
            final HttpServletResponse response)
    {
        return "employee/batchAddCapability";
    }
    @RequestMapping("/assignTraining")
    public String assignTraining(final HttpServletRequest request,
            final HttpServletResponse response)
    {
        String trainingId = request.getParameter("trainingId");
        request.setAttribute("trainingId", trainingId);
        return "employee/assignTraining";
    }
    @RequestMapping("/assignPlanTraining")
    public String assignPlanTraining(final HttpServletRequest request,
            final HttpServletResponse response)
    {
        String trainingId = request.getParameter("trainingId");
        String planTrainingId = request.getParameter("planTrainingId");
        String pointId = request.getParameter("pointId");
        request.setAttribute("trainingId", trainingId);
        request.setAttribute("planTrainingId", planTrainingId);
        request.setAttribute("pointId", pointId);
        return "employee/assignPlanTraining";
    }
    
    @RequestMapping("/batchAddTraining")
    public String batchAddTraining(final HttpServletRequest request,
            final HttpServletResponse response)
    {
        return "employee/batchAddTraining";
    }
    
    @RequestMapping("/trainingPassedList")
    public String trainingPassedList(final HttpServletRequest request,
            final HttpServletResponse response)
    {
        return "employee/trainingPassedList";
    }

    @RequestMapping("/queryEmpInfo")
    @ResponseBody
    public Employee queryEmpInfo(final HttpServletRequest request,
            final HttpServletResponse response)
    {
        logger.info("debug------test----");
        
        HttpSession session = request.getSession();
        
        Employee employee = (Employee) session.getAttribute("employee");
        
        return employee;
    }
    
    @RequestMapping("/index")
    public String index(final HttpServletRequest request,
            final HttpServletResponse response)
    {
        return "index";
    }
    
    @RequestMapping("/checkErExists")
    @ResponseBody
    public String checkErExists(final HttpServletRequest request,
            final HttpServletResponse response,@RequestParam String er)
    {
        
        boolean result = userService.checkErExists(er);
        
        Map<String,Boolean> map = new HashMap<>();
        
        map.put("valid", result);
        
        ObjectMapper mapper = new ObjectMapper();
        
        String resultString = "";
        
        try
        {
            resultString = mapper.writeValueAsString(map);
        }
        catch (JsonProcessingException e)
        {
            logger.error("[EmployeeController.checkErExists] exception",e);
        }
        
        return resultString;
    }
    
    
    @RequestMapping("/checkHrExists")
    @ResponseBody
    public String checkHrExists(final HttpServletRequest request,
            final HttpServletResponse response,@RequestParam String hr)
    {
        
        boolean result = userService.checkHrExists(hr);
        
        Map<String,Boolean> map = new HashMap<>();
        
        map.put("valid", result);
        
        ObjectMapper mapper = new ObjectMapper();
        
        String resultString = "";
        
        try
        {
            resultString = mapper.writeValueAsString(map);
        }
        catch (JsonProcessingException e)
        {
            logger.error("[EmployeeController.checkHrExists] exception",e);
        }
        
        return resultString;
    }
    
    @RequestMapping("/findEmpByEr")
    @ResponseBody
    public Employee findEmpByEr(final HttpServletRequest request,
            final HttpServletResponse response)
    {
        String erNumber = request.getParameter("erNumber");
        
        Employee employee = userService.fetchByErNumber(erNumber);
        
        return employee;
    }



}
