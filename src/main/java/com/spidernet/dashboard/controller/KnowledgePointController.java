package com.spidernet.dashboard.controller;

import java.sql.Timestamp;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.spidernet.dashboard.entity.*;
import com.spidernet.dashboard.service.TrainningService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spidernet.dashboard.service.KnowledgePointService;
import com.spidernet.util.Utils;

@Controller
@RequestMapping("/knowledge")
public class KnowledgePointController {

	  @Resource
	  KnowledgePointService knowledgePointService;

	  @Resource
	  TrainningService trainningService;
	 
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
		  int status = request.getParameter("status")==null?-1:Integer.parseInt(request.getParameter("status"));
		  KnowledgePoint point = new KnowledgePoint();
		  point.setPid(pid);
		  point.setStatus(status);
		  List<KnowledgePoint> list = knowledgePointService.queryKnowledgePointByPid(point);
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
	        knowledgePoint.setCreateDate(new Timestamp(new Date().getTime()));
	        knowledgePoint.setUpdateDate(new Timestamp(new Date().getTime()));
	        
	        if (operator == 1){
	        	result = knowledgePointService.updateKnowledgePoint(knowledgePoint)==1?true:false;
	        }else{
	        	 result = knowledgePointService.addKnowledgePoint(knowledgePoint)==1?true:false;
	        }
	        return result;
	    }

		@RequestMapping("/getKnowledgePointByJson")
		@ResponseBody
		public Object getKnowledgePointByJson(HttpServletRequest request,
											  HttpServletResponse response){

			String trainningId = request.getParameter("traningId");

	  	    List<NodeDTO> nodeDTOSList = new ArrayList<NodeDTO>();

			KnowledgePoint point = new KnowledgePoint();
			point.setPid("0");
			point.setStatus(0);
			List<KnowledgePoint> list = knowledgePointService.queryKnowledgePointByPid(point);

			String[] knowledgePointList =null;
			String[] childKnowledgePointList =null;

			Trainning training = trainningService.queryTrainingById(trainningId);
			if(!("".equals(trainningId)||training ==null)){
				String test= training.getKnowledgePoint();
				String test1= training.getSubTopic();
				if (test!=null &&!test.equals("")){
					knowledgePointList= test.substring(0,test.length()-1).split(",");
				}
				if (test1!=null &&!test1.equals("")){
					childKnowledgePointList= test1.substring(0,test1.length()-1).split(",");
				}
			}
			for (int i=0; i<list.size();i++) {

				NodeDTO nodeDTO = new NodeDTO();

				KnowledgePoint childPoint = new KnowledgePoint();
				childPoint.setPid(list.get(i).getKnowledgePointId());
				childPoint.setStatus(0);
				List<KnowledgePoint> childKnowledgePointlist = knowledgePointService.queryKnowledgePointByPid(childPoint);

				List<NodeDTO> childNodeDTOList= new ArrayList<NodeDTO>();

				for (int j= 0;j<childKnowledgePointlist.size();j++) {

					NodeDTO childNodeDTO = new NodeDTO();
					childNodeDTO.setKnowledgePoint(childKnowledgePointlist.get(j).getKnowledgePointId());
					childNodeDTO.setText(childKnowledgePointlist.get(j).getPointTitle());

					if(childKnowledgePointList !=null){

						for (String knowledgePoint:childKnowledgePointList){
							if(knowledgePoint.equals(childKnowledgePointlist.get(j).getKnowledgePointId())){
								Map map = new HashMap();
								map.put("checked", true);
								childNodeDTO.setState(map);
							}
						}
					}

					childNodeDTOList.add(childNodeDTO);
				}

				nodeDTO.setKnowledgePoint(list.get(i).getKnowledgePointId());
				nodeDTO.setText(list.get(i).getPointTitle());
				nodeDTO.setNodes(childNodeDTOList);
				if(knowledgePointList !=null){
					for (String knowledgePoint:knowledgePointList){
						if(knowledgePoint.equals(list.get(i).getKnowledgePointId())){
							Map<String, Object> map2 = new HashMap<String, Object>();
							map2.put("checked", true);
							nodeDTO.setState(map2);
						}
					}
				}
				nodeDTOSList.add(nodeDTO);
			}
			String result = JSON.toJSONString(nodeDTOSList,true);
			return result;
		}
}
