package com.spidernet.dashboard.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spidernet.dashboard.entity.Employee;
import com.spidernet.dashboard.entity.KnowledgePoint;
import com.spidernet.dashboard.entity.KnowledgePointCondition;
import com.spidernet.dashboard.service.KnowledgePointService;
import com.spidernet.util.Utils;

@Controller
@RequestMapping("/knowledge")
public class KnowledgePointController {

	  @Resource
	  KnowledgePointService knowledgePointService;
	 
	  @RequestMapping("/knowledgePoint")
	  public String scoreImport(final HttpServletRequest request,
            final HttpServletResponse response)
	  {
		  return "knowledge/knowledgePoint";
	  }
	  
	  @RequestMapping("/getKnowledgePointByPid")
	  @ResponseBody
	  public Object getKnowledgePointByPid(final HttpServletRequest request,
			  final HttpServletResponse response){
		  String pid = request.getParameter("pid")==null?"0":request.getParameter("pid");
		  List<KnowledgePoint> list = knowledgePointService.queryKnowledgePointByPid(pid);
		  return list;
	  }
	  
	  @RequestMapping("/getKnowledgePointList")
	  @ResponseBody
	  public Object queryKowledgePointList(final HttpServletRequest request,
	            final HttpServletResponse response){
		  
		  String pid = request.getParameter("pid")==null?"0":request.getParameter("pid");
		  String pointTitle = request.getParameter("pointTitle")==null?"":request.getParameter("pointTitle");
		  int status = request.getParameter("status")==null?-1:Integer.parseInt(request.getParameter("status"));
		  
		  String pageState = request.getParameter("pageState");
	      String currentPage = null;
	      int countPage = 0;
	      
		  KnowledgePointCondition pageCondition = new KnowledgePointCondition();
		   if("".equals(pageState) || pageState == null){
			   currentPage = "0";
			   pageCondition.setPid(pid);
			   pageCondition.setPointTitle(pointTitle);
			   pageCondition.setStatus(status);
	           pageCondition.setCurrentPage(currentPage);
	           countPage = knowledgePointService.countPage(pageCondition);
	           if (countPage % 10 == 0){
	        	   countPage = countPage/10;
	           }else{
	        	   countPage = countPage/10 + 1;
	           }
	           pageCondition.setPageCount(countPage+"");
	           request.getSession().setAttribute("pageCondition", pageCondition);
		   }else if("first".equals(pageState)){
			   currentPage = "0";
	           pageCondition = (KnowledgePointCondition) request.getSession().getAttribute("pageCondition");
	           pageCondition.setCurrentPage(currentPage);
	           request.getSession().setAttribute("pageCondition", pageCondition);
		   }else if("next".equals(pageState)){
			   pageCondition = (KnowledgePointCondition) request.getSession().getAttribute("pageCondition");
	           currentPage = Integer.parseInt(pageCondition.getCurrentPage()) + 10 +"";
	           pageCondition.setCurrentPage(currentPage);
	           request.getSession().setAttribute("pageCondition", pageCondition);
		   }else if("previous".equals(pageState)){
			   pageCondition = (KnowledgePointCondition) request.getSession().getAttribute("pageCondition");
	           currentPage = Integer.parseInt(pageCondition.getCurrentPage()) - 10 +"";
	           pageCondition.setCurrentPage(currentPage);
	           request.getSession().setAttribute("pageCondition", pageCondition);
		   }else if("last".equals(pageState)){
			   pageCondition = (KnowledgePointCondition) request.getSession().getAttribute("pageCondition");
	           currentPage = (Integer.parseInt(pageCondition.getPageCount()) - 1) * 10 +"";
	           pageCondition.setCurrentPage(currentPage);
	           request.getSession().setAttribute("pageCondition", pageCondition);
		   }
		  
		   List<KnowledgePoint> listE = knowledgePointService.queryKnowledgePoints((KnowledgePointCondition) request.getSession().getAttribute("pageCondition"));
	       Map<String,Object> result = new HashMap<String,Object>();
	       result.put("data", listE);
	       result.put("pageInfo", request.getSession().getAttribute("pageCondition"));
		  return result;
	  }
	  
	  @RequestMapping("/getKnowledgePoint")
	  @ResponseBody
	  public Object getKnowledgePointItemList(final HttpServletRequest request,
	            final HttpServletResponse response){
		  String knowledgePointId = request.getParameter("knowledgePointId");
		  KnowledgePoint knowledgePoint = new KnowledgePoint();
		  knowledgePoint = knowledgePointService.queryKnowledgePointById(knowledgePointId);
		  return knowledgePoint;
	  }
	  
	  	@RequestMapping("/addOrUpdateKnowledgePoint")
	    @ResponseBody
	    public Boolean addKnowledgePoint(final HttpServletRequest request,
	            final HttpServletResponse response)
	    {
	  	    boolean result = false;
	  	    int operator = 0;
	  		KnowledgePoint knowledgePoint = new KnowledgePoint();
	  	    String knowledgePointId = request.getParameter("knowledgePointId");
	  	    if (null != knowledgePointId && !"".equals(knowledgePointId) &&!"0".equals(knowledgePointId)){
	  	    	 knowledgePoint = knowledgePointService.queryKnowledgePointById(knowledgePointId);
	  	    	 operator = 1;
	  	    }else{
	  	    	knowledgePointId = Utils.getUUID();
	  	    }
	  	    
	        String pointTitle = request.getParameter("pointTitle");
	        String pid = request.getParameter("pid")==null?"0":request.getParameter("pid");
	        String icon = request.getParameter("icon");
	        String description = request.getParameter("description");
	        String status = request.getParameter("status");
	        HttpSession session = request.getSession();
	        Employee employee = (Employee) session.getAttribute("employee");
	        int sort = request.getParameter("sort")==null?0:Integer.parseInt(request.getParameter("sort"));
	        
	        knowledgePoint.setKnowledgePointId(knowledgePointId);
	        knowledgePoint.setPointTitle(pointTitle);
	        knowledgePoint.setPid(pid);
	        knowledgePoint.setIcon(icon);
	        knowledgePoint.setCreateUser(employee.getEmployeeId());
	        knowledgePoint.setSort(sort);
	        knowledgePoint.setDescription(description);
	        
	        if (null != status && !"".equals(status)){
	        	  knowledgePoint.setStatus(Integer.parseInt(status));
	        }
	        knowledgePoint.setCreateDate(new Date());
	        knowledgePoint.setUpdateDate(new Date());
	        
	        if (operator == 1){
	        	result = knowledgePointService.updateKnowledgePoint(knowledgePoint)==1?true:false;
	        }else{
	        	 result = knowledgePointService.addKnowledgePoint(knowledgePoint)==1?true:false;
	        }
	        return result;
	    }
}
