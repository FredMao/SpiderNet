package com.spidernet.schedule.request;

import java.util.HashMap;
import java.util.Map;

public class RequestObj {

	/**
	 * default 1 sync user information
	 */
	private String operationCode = "1";
	private String userName;
	private String password;
	private String systemId;
	private String lastUpdateTime;
	
	public RequestObj(){
		
	}
	
	public RequestObj(String operationCode, String userName, String password, String systemId, String lastUpdateTime) {
		this.operationCode = operationCode;
		this.userName = userName;
		this.password = password;
		this.systemId = systemId;
		this.lastUpdateTime = lastUpdateTime;
	}
	
	public String getOperationCode() {
		return operationCode;
	}
	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSystemId() {
		return systemId;
	}
	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}
	public String getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	
	public Map<String, String> getDataForMap(){
		Map<String, String> data = new HashMap<String, String>();
		data.put("operationCode", operationCode);
		data.put("userName", userName);
		data.put("password", password);
		data.put("systemId", systemId);
		data.put("lastUpdateTime", lastUpdateTime);
		return data;
	}

	@Override
	public String toString() {
		
		return "{operationCode:'"+operationCode+"', userName:'"+userName+"',password:'"+password+"',systemId:'"+systemId+"',lastUpdateTime:'"+lastUpdateTime+"' }";
	}
	
	


}
