package com.spidernet.schedule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spidernet.dashboard.controller.BuController;
import com.spidernet.dashboard.entity.Bu;
import com.spidernet.dashboard.entity.Employee;
import com.spidernet.dashboard.entity.SysExec;
import com.spidernet.dashboard.service.BuService;
import com.spidernet.dashboard.service.EmployeeService;
import com.spidernet.dashboard.service.SysExecService;
import com.spidernet.schedule.http.HttpMessage;
import com.spidernet.schedule.http.ISendRequest;
import com.spidernet.schedule.request.RequestObj;
import com.spidernet.util.Constants;
import com.spidernet.util.DateUtil;
import com.spidernet.util.Utils;



public class EmployeeSchedule extends AbstractSchedule {

	private static Logger logger = LoggerFactory
	            .getLogger(BuController.class);
	    
    @Resource
    private BuService buService;
    
    @Resource
    private SysExecService sysExecService;
    
    @Resource
	private EmployeeService userService;
	    
	@Resource
	private ISendRequest iSendRequest;
	
	private String employeeUrl;
	
	private String operatorcode;
	
	private String syncusername;
	
	private String syncuserpwd;
	
	private String systemId;
	
	@Override
	public void handle() {
		
		logger.info("New Timer Start");
		if (null == employeeUrl || "".equals(employeeUrl)) {
			logger.error("miss sync employee url config.");
			return;
		}
		
		RequestObj reqObj = new RequestObj();
		reqObj.setOperationCode(operatorcode);
		reqObj.setUserName(syncusername);
		reqObj.setPassword(syncuserpwd);
		reqObj.setSystemId(systemId);
		
		List<SysExec> sysExecs = sysExecService.getSysExecByAttrName(Constants.SYS_EXEC_ATTR.SYNC_EMPLOYEE_TIME);
		String lastUpdateTime = DateUtil.currentDateTimeString();
		
		SysExec sysExec = null;
		if(null != sysExecs && sysExecs.size()>0){
			sysExec = sysExecs.get(0);
			lastUpdateTime = sysExec.getAttrValue();
		}else{
			sysExec = new SysExec();
			sysExec.setRunId(Utils.getUUID());
			sysExec.setAttrName(Constants.SYS_EXEC_ATTR.SYNC_EMPLOYEE_TIME);
			sysExec.setAttrValue(lastUpdateTime);
			sysExecService.addSysExec(sysExec);
		}
		sysExec.setAttrValue(DateUtil.currentDateTimeString());
		sysExecService.editSysExec(sysExec);
		
		reqObj.setLastUpdateTime(lastUpdateTime);
		logger.info("request:"+reqObj.toString());
		HttpMessage message = null;
		try {
			//message = iSendRequest.doPost(employeeUrl, reqObj.getDataForMap());
			message = iSendRequest.doPostJson(employeeUrl, reqObj.toString());
			String data = message.getBody();
			JSONObject jsonObj = new JSONObject(data);
			String resultcode  = jsonObj.getString(Constants.SyncEmployeeResponse.RESULT);
			if (resultcode.equals(Constants.RESULT_CODE.ERROR)){
				logger.error("Receive Employee Error, errorcode:"+resultcode);
				
				JSONArray errList = jsonObj.getJSONArray(Constants.SyncEmployeeResponse.ERROR_LIST);
				for (Object errorCode : errList){
					logger.error("Error code:"+errorCode);
				}
				
			}else{
				List datas = this.parse(jsonObj);
				logger.info("Receive Employee data success, size["+datas.size()+"]");
				this.operator(datas);
				logger.info("Operator Employee data success");
			}
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}
	
	public List<Employee> parse(JSONObject data){
		logger.info("Parse Data start.");
		List<Employee> result = new ArrayList<Employee>();
		JSONArray dataList = data.getJSONArray(Constants.SyncEmployeeResponse.DATA_LIST);
		JSONObject item = new JSONObject();
		Employee employee = null;
		Bu bu = null;
		try{
			for (int i=0; i<dataList.length(); i++){
				item = dataList.getJSONObject(i);
				employee = new Employee();
				employee.setErNumber(!item.has(Constants.SyncEmployee.ER_NUM)?"":item.getString(Constants.SyncEmployee.ER_NUM));
				employee.setHrNumber(!item.has(Constants.SyncEmployee.HR_NUM)?"":item.getString(Constants.SyncEmployee.HR_NUM));
				employee.seteName((!item.has(Constants.SyncEmployee.E_USER_NAME) || "null".equals(item.get(Constants.SyncEmployee.E_USER_NAME).toString()))?"":item.getString(Constants.SyncEmployee.E_USER_NAME));
				employee.setName(!item.has(Constants.SyncEmployee.C_USER_NAME)?"":item.getString(Constants.SyncEmployee.C_USER_NAME));
				employee.setSkill(!item.has(Constants.SyncEmployee.SKILL)?"":item.getString(Constants.SyncEmployee.SKILL));
				bu = new Bu();
				bu.setOrgName(!item.has(Constants.SyncEmployee.BU_NAME)?"":item.getString(Constants.SyncEmployee.BU_NAME));
				bu.setBuName(!item.has(Constants.SyncEmployee.ORG_NAME)?"":item.getString(Constants.SyncEmployee.ORG_NAME));
				employee.setBu(bu);
				result.add(employee);
			}
		}catch(Exception e){
			logger.error("Parse JSONObect to Object Error["+e.getMessage()+"]");
		}
		logger.info("Parse Data done.");
		return result;
	}
	
	public void operator(List<Employee> datas){
		logger.info("Operator Data start.");
		Bu bu = null;
		List list = null;
		int noErNumCount = 0;
		for (Employee employee : datas){
			bu = employee.getBu();
			list = buService.queryBus(bu);
			String bu_id = "";
			
			if (null != list && list.size() > 0){
				// if the bu already exists, get the id
				String orgName = bu.getOrgName();
				bu = (Bu) list.get(0);
				bu_id = bu.getBuId();
			}else{
				// if the bu no exists, create a new bu
				bu_id = Utils.getUUID();
				bu.setBuId(bu_id);
				buService.addBu(bu);
			}
			employee.setBuId(bu_id);
			
			if ("".equals(employee.getErNumber())){
				noErNumCount ++;
				logger.error("The user has no ErNum and HrNum:["+employee.getName() +"],["+employee.geteName()+"]");
			}else if (!userService.checkErExists(employee.getErNumber())){
				//exists
				Employee emp = userService.fetchByErNumber(employee.getErNumber());
				emp.setBuId(bu.getBuId());
				emp.setHrNumber(employee.getHrNumber());
				emp.seteName(employee.geteName());
				emp.setName(employee.getName());
				emp.setSkill(employee.getSkill());
				userService.updEmployee(emp);
			}else{
				employee.setEmployeeId(Utils.getUUID());
				userService.addEmployee(employee);
			}
		}
		logger.info("Operator Data Done.total size:"+datas.size()+", Skip "+noErNumCount+" with that has no ErNum");
	}

	public String getEmployeeUrl() {
		return employeeUrl;
	}

	public void setEmployeeUrl(String employeeUrl) {
		this.employeeUrl = employeeUrl;
	}

	public String getSyncusername() {
		return syncusername;
	}

	public void setSyncusername(String syncusername) {
		this.syncusername = syncusername;
	}

	public String getSyncuserpwd() {
		return syncuserpwd;
	}

	public void setSyncuserpwd(String syncuserpwd) {
		this.syncuserpwd = syncuserpwd;
	}

	public String getOperatorcode() {
		return operatorcode;
	}

	public void setOperatorcode(String operatorcode) {
		this.operatorcode = operatorcode;
	}

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}
	
}
