package com.spidernet.dashboard.entity;

import com.spidernet.dashboard.entity.PageCondition;

public class KnowledgePointCondition extends PageCondition {

	private String pid;
	private String pointTitle;
	private int status = 0;
	
	public KnowledgePointCondition(){
		
	}
	
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	public String getPointTitle() {
		return pointTitle;
	}

	public void setPointTitle(String pointTitle) {
		this.pointTitle = pointTitle;
	}
	
	
	
}
