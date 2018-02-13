package com.spidernet.dashboard.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spidernet.dashboard.entity.CCapability;
import com.spidernet.dashboard.entity.CapabilityB;
import com.spidernet.dashboard.entity.CapabilityMap;
import com.spidernet.dashboard.entity.EmpPageCondition;
import com.spidernet.dashboard.entity.EmployeeInfo;
import com.spidernet.dashboard.entity.ProCapability;
import com.spidernet.dashboard.entity.TrainingInfo;
import com.spidernet.dashboard.entity.TrainingInfoPageCondition;
import com.spidernet.dashboard.entity.Trainning;
import com.spidernet.dashboard.service.EmployeeInfoService;
import com.spidernet.dashboard.service.ExamService;
import com.spidernet.dashboard.service.TrainingInfoService;
import com.spidernet.dashboard.service.TrainningService;
import com.spidernet.util.Constants;
import com.spidernet.util.Utils;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

@Controller
@RequestMapping("/employeeInfo")
public class EmployeeInfoController {

	@Resource
	private EmployeeInfoService employeeInfoService;
	
	@Resource
	private TrainingInfoService trainingInfoService;

	@Resource
	private ExamService examService;

	@Resource
	private TrainningService trainingService;

	@Resource
	TrainningService trainningService;

	private static Logger logger = LoggerFactory.getLogger(EmployeeInfoController.class);

	
	@RequestMapping("/employeeInfoList")
	@ResponseBody
	public Object employeeInfoList(final HttpServletRequest request, final HttpServletResponse response) {
		String er = request.getParameter("er");

		String buId = request.getParameter("buId");

		String projectId = request.getParameter("projectId");

		String examId = request.getParameter("examId");

		String pageState = request.getParameter("pageState");

		String trainingName = request.getParameter("trainingName");

		String currentPage = null;

		int countPage = 0;

		EmpPageCondition pageCondition = new EmpPageCondition();

		String trainingId = "";
		if (!"".equals(trainingName) && !trainingName.contains("--")) {
			trainingId = trainningService.queryTrainingByName(trainingName).get(0).getTrainningId();
		}
		
		pageCondition.setBuId(buId);
		pageCondition.setProjectId(projectId);
		pageCondition.setExamId(examId);
		pageCondition.setTrainingId(trainingId);
		pageCondition.setEr(er);

		if ("".equals(pageState) || pageState == null) {
			currentPage = "0";
			pageCondition.setCurrentPage(currentPage);
			countPage = employeeInfoService.countPage(pageCondition);
			pageCondition.setPageCount(countPage + "");
			request.getSession().setAttribute("pageCondition", pageCondition);
		} else if ("frist".equals(pageState)) {
			currentPage = "0";
			pageCondition = (EmpPageCondition) request.getSession().getAttribute("pageCondition");
			pageCondition.setCurrentPage(currentPage);
			request.getSession().setAttribute("pageCondition", pageCondition);
		} else if ("next".equals(pageState)) {
			pageCondition = (EmpPageCondition) request.getSession().getAttribute("pageCondition");
			currentPage = Integer.parseInt(pageCondition.getCurrentPage()) + 10 + "";
			pageCondition.setCurrentPage(currentPage);
			request.getSession().setAttribute("pageCondition", pageCondition);
		} else if ("previous".equals(pageState)) {
			pageCondition = (EmpPageCondition) request.getSession().getAttribute("pageCondition");
			currentPage = Integer.parseInt(pageCondition.getCurrentPage()) - 10 + "";
			pageCondition.setCurrentPage(currentPage);
			request.getSession().setAttribute("pageCondition", pageCondition);
		} else if ("last".equals(pageState)) {
			pageCondition = (EmpPageCondition) request.getSession().getAttribute("pageCondition");
			currentPage = (Integer.parseInt(pageCondition.getPageCount()) - 1) * 10 + "";
			pageCondition.setCurrentPage(currentPage);
			request.getSession().setAttribute("pageCondition", pageCondition);
		}

		List<EmployeeInfo> listE = employeeInfoService
				.queryEmpInfo((EmpPageCondition) request.getSession().getAttribute("pageCondition"));
		Map<String, Object> result = new HashMap<String, Object>();
		List<String> trainingNames = new ArrayList<String>();

		if (!"".equals(trainingName) && !trainingName.contains("--")) {
			for (int i = 0; i < listE.size(); i++) {
				trainingNames.add(trainingName);
			}
			result.put("data", listE);
			result.put("trainingNames", trainingNames);
			result.put("pageInfo", request.getSession().getAttribute("pageCondition"));
		} else {
			trainingNames = trainingService.queryEmpAllTrainingNames((EmpPageCondition) request.getSession().getAttribute("pageCondition"));
			result.put("data", listE);
			result.put("trainingNames", trainingNames);
			result.put("pageInfo", request.getSession().getAttribute("pageCondition"));
		}
		
		return result;
	}
	
	@RequestMapping("/trainingPassedList")
	@ResponseBody
	public Object trainingPassedList(final HttpServletRequest request, final HttpServletResponse response) {
		
		String pageState = request.getParameter("pageState");

		String trainingName = request.getParameter("trainingName");
		
		String buId = request.getParameter("buId");

		String currentPage = null;

		int countPage = 0;

		EmpPageCondition pageCondition = new EmpPageCondition();

		String trainingId = "";
		if (!"".equals(trainingName) && !trainingName.contains("--")) {
			trainingId = trainningService.queryTrainingByName(trainingName).get(0).getTrainningId();
		}
		
		pageCondition.setTrainingId(trainingId);
		pageCondition.setBuId(buId);

		if ("".equals(pageState) || pageState == null) {
			currentPage = "0";
			pageCondition.setCurrentPage(currentPage);
			countPage = trainingInfoService.countPage(pageCondition);
			pageCondition.setPageCount(countPage + "");
			request.getSession().setAttribute("pageCondition", pageCondition);
		} else if ("frist".equals(pageState)) {
			currentPage = "0";
			pageCondition = (EmpPageCondition) request.getSession().getAttribute("pageCondition");
			pageCondition.setCurrentPage(currentPage);
			request.getSession().setAttribute("pageCondition", pageCondition);
		} else if ("next".equals(pageState)) {
			pageCondition = (EmpPageCondition) request.getSession().getAttribute("pageCondition");
			currentPage = Integer.parseInt(pageCondition.getCurrentPage()) + 10 + "";
			pageCondition.setCurrentPage(currentPage);
			request.getSession().setAttribute("pageCondition", pageCondition);
		} else if ("previous".equals(pageState)) {
			pageCondition = (EmpPageCondition) request.getSession().getAttribute("pageCondition");
			currentPage = Integer.parseInt(pageCondition.getCurrentPage()) - 10 + "";
			pageCondition.setCurrentPage(currentPage);
			request.getSession().setAttribute("pageCondition", pageCondition);
		} else if ("last".equals(pageState)) {
			pageCondition = (EmpPageCondition) request.getSession().getAttribute("pageCondition");
			currentPage = (Integer.parseInt(pageCondition.getPageCount()) - 1) * 10 + "";
			pageCondition.setCurrentPage(currentPage);
			request.getSession().setAttribute("pageCondition", pageCondition);
		}

		
		Map<String, Object> result = new HashMap<String, Object>();
		
		if (!"".equals(trainingName) && !trainingName.contains("--")) {
			
			List<TrainingInfo> listE=trainingInfoService
					.querySpecificTrainingPassedPersonList((EmpPageCondition) request.getSession().getAttribute("pageCondition"));
			
			result.put("data", listE);
			result.put("pageInfo", request.getSession().getAttribute("pageCondition"));
		} else {
			
			List<TrainingInfo> listE=trainingInfoService
					.queryAllEmpPassedTrainingInfoList((EmpPageCondition) request.getSession().getAttribute("pageCondition"));
			
			result.put("data", listE);
			result.put("pageInfo", request.getSession().getAttribute("pageCondition"));
		}

		return result;
	}
		

	@RequestMapping("/exportExcel")
	public HttpServletResponse exportExcel(HttpServletRequest request, HttpServletResponse response) {
		try {
			EmpPageCondition pageCondition = (EmpPageCondition) request.getSession().getAttribute("pageCondition");

			String trainingId = pageCondition.getTrainingId();

			String fileName = "";

			Trainning training = null;

			if (trainingId != null && !trainingId.equals("")) {
				training = trainingService.queryTrainingById(trainingId);

				fileName = Constants.PATH + "" + Utils.getUUID() + ".xls";
			}

			WritableWorkbook wwb = null;

			// 鍒涘缓鍙啓鍏ョ殑Excel宸ヤ綔绨�

			File file = new File(fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			// 浠ileName涓烘枃浠跺悕鏉ュ垱寤轰竴涓猈orkbook
			wwb = Workbook.createWorkbook(file);

			// 鍒涘缓宸ヤ綔琛�
			WritableSheet ws = wwb.createSheet("Shee 1", 0);

			// 鏌ヨ鏁版嵁搴撲腑鎵�鏈夌殑鏁版嵁
			List<EmployeeInfo> listE = employeeInfoService.queryEmpList(trainingId);

			// 绗竴琛屽煿璁悕绉�
			WritableCellFormat headerFormat = new WritableCellFormat();
			headerFormat.setAlignment(Alignment.CENTRE);
			ws.mergeCells(0, 0, 7, 0);
			Label labelTraningName = new Label(0, 0,
					training.getTime().substring(0, 10) + "" + training.getCourseName() + "鍩硅", headerFormat);
			ws.addCell(labelTraningName);

			// 绗簩琛屽煿璁甯�
			ws.mergeCells(0, 1, 1, 1);
			ws.mergeCells(2, 1, 7, 1);
			Label labelTeacher = new Label(0, 1, "鍩硅璁插笀");
			ws.addCell(labelTeacher);
			Label labelTeacherName = new Label(2, 1, training.getTeacher());
			ws.addCell(labelTeacherName);

			// 绗笁琛屽煿璁椂闂�
			ws.mergeCells(0, 2, 1, 2);
			ws.mergeCells(2, 2, 7, 2);
			Label labelTrainingTime = new Label(0, 2, "鍩硅鏃堕棿");
			ws.addCell(labelTrainingTime);
			Label labelTime = new Label(2, 2, training.getTime());
			ws.addCell(labelTime);

			// 绗洓琛屽煿璁湴鐐�
			ws.mergeCells(0, 3, 1, 3);
			ws.mergeCells(2, 3, 7, 3);
			Label labelTrainingLocation = new Label(0, 3, "鍩硅鍦扮偣");
			ws.addCell(labelTrainingLocation);
			Label labelLocation = new Label(2, 3, training.getLocation());
			ws.addCell(labelLocation);

			// 瑕佹彃鍏ュ埌鐨凟xcel琛ㄦ牸鐨勮鍙凤紝榛樿浠�0寮�濮�
			Label labelEr = new Label(0, 4, "Er");// 琛ㄧず绗�
			Label labelHr = new Label(1, 4, "Hr");
			Label labelName = new Label(2, 4, "涓枃鍚�");
			Label labelEName = new Label(3, 4, "鑻辨枃鍚�");
			Label labelBu = new Label(4, 4, "浜や粯閮�");
			Label labelProject = new Label(5, 4, "椤圭洰");

			ws.addCell(labelEr);
			ws.addCell(labelHr);
			ws.addCell(labelName);
			ws.addCell(labelEName);
			ws.addCell(labelBu);
			ws.addCell(labelProject);
			for (int i = 4, j = 0; j < listE.size(); i++, j++) {
				Label labelEr_i = new Label(0, i + 1, listE.get(j).getEr() + "");
				Label labelHr_i = new Label(1, i + 1, listE.get(j).getHr());
				Label labelName_i = new Label(2, i + 1, listE.get(j).getName());
				Label labelEName_i = new Label(3, i + 1, listE.get(j).geteName() + "");
				Label labelBu_i = new Label(4, i + 1, listE.get(j).getBuName());
				Label labelProject_i = new Label(5, i + 1, listE.get(j).getProjectName() + "");
				ws.addCell(labelEr_i);
				ws.addCell(labelHr_i);
				ws.addCell(labelName_i);
				ws.addCell(labelEName_i);
				ws.addCell(labelBu_i);
				ws.addCell(labelProject_i);
			}

			// 鍐欒繘鏂囨。
			wwb.write();
			// 鍏抽棴Excel宸ヤ綔绨垮璞�
			wwb.close();

			String filename = training.getTime().substring(0, 10) + "" + training.getCourseName() + ".xls";

			// 浠ユ祦鐨勫舰寮忎笅杞芥枃浠躲��
			InputStream fis = new BufferedInputStream(new FileInputStream(fileName));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 娓呯┖response
			response.reset();
			// 璁剧疆response鐨凥eader
			response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
			// response.setContentType("application/octet-stream");
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-Length", "" + file.length());
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());

			toClient.write(buffer);
			toClient.flush();
			toClient.close();

			file.delete();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}
	
	@RequestMapping("/viewTrainings")
    @ResponseBody
    public Object viewTrainings(final HttpServletRequest request,
            final HttpServletResponse response)
    {

		String erId =  request.getParameter("erId");
		String trainingName = request.getParameter("trName");

		Map<String, Object> result = new HashMap<String, Object>();
		if (trainingName.contains(",")) {
		    List<TrainingInfo> listE = trainingInfoService.queryEmpUncompletedTrainingsDetailInfo(erId);
		    result.put("data", listE);
		}else {
			 List<TrainingInfo> listE = trainingInfoService.queryEmpUncompletedTrainingsDetailInfoByManyConditions(erId,trainingName);
			result.put("data", listE);
		}		
		return result;
    }
 
		

	@RequestMapping("/configRule")
    @ResponseBody
	public Object configRule(final HttpServletRequest request,
            final HttpServletResponse response){
		boolean result = false;
		String er = request.getParameter("er");
		String ruleid = request.getParameter("rule");
		if (employeeInfoService.configRule(er, ruleid) == 1){
			result = true;
		}
		return result;
	}
	
	@RequestMapping("/getRule")
	@ResponseBody
	public Object getRule(final HttpServletRequest request,
            final HttpServletResponse response){
		String er = request.getParameter("er");
		String ruls = employeeInfoService.queryRuleByEr(er);
		Map<String, String> data = new HashMap<String, String>();
		data.put("rule", ruls);
		return data;
	}
	

	@RequestMapping("/viewEmpPassedTrainingsDetailInfo")
    @ResponseBody
    public Object viewEmpPassedTrainingsDetailInfo(final HttpServletRequest request,
            final HttpServletResponse response)
    {

		String erId =  request.getParameter("erId");
		String trainingName=request.getParameter("trName");
		Map<String, Object> result = new HashMap<String, Object>();
		if (trainingName.contains(",")) {
		    List<TrainingInfo> listE = trainingInfoService.queryEmpPassedTrainingsDetailInfo(erId);
		    result.put("data", listE);
		}else {
			 List<TrainingInfo> listE = trainingInfoService.queryEmpPassedTrainingsDetailInfoByManyConditions(erId,trainingName);
			result.put("data", listE);
		}		
		return result;
    }
}

