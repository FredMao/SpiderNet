package com.spidernet.dashboard.entity;

public class SysExec {

	private String runId;
	private String attrName;
	private String attrValue;
	
	public SysExec(){
		
	}
	
	public SysExec(String runId, String attrName, String attrValue) {
		super();
		this.runId = runId;
		this.attrName = attrName;
		this.attrValue = attrValue;
	}
	public String getRunId() {
		return runId;
	}
	public void setRunId(String runId) {
		this.runId = runId;
	}
	public String getAttrName() {
		return attrName;
	}
	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}
	public String getAttrValue() {
		return attrValue;
	}
	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}
	
	
}
