package com.spidernet.dashboard.entity;

import java.io.Serializable;
import java.util.Date;;


public class KnowledgePoint  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String knowledgePointId;
	private String pointTitle;
	private String pid;
	private int leaf;
	private String icon;
	private String createUser;
	private Date createDate;
	private Date updateDate;
	private String description;
	private int status;
	private String name;
	private int sort;
	
	public KnowledgePoint(){
		
	}
	
	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public String getKnowledgePointId() {
		return knowledgePointId;
	}

	public void setKnowledgePointId(String knowledgePointId) {
		this.knowledgePointId = knowledgePointId;
	}

	public String getPointTitle() {
		return pointTitle;
	}

	public void setPointTitle(String pointTitle) {
		this.pointTitle = pointTitle;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public int getLeaf() {
		return leaf;
	}

	public void setLeaf(int leaf) {
		this.leaf = leaf;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public KnowledgePoint(String knowledgePointId, String pointTitle, String pid, int leaf, String icon,
			String createUser, Date createDate, Date updateDate, String description) {
		super();
		this.knowledgePointId = knowledgePointId;
		this.pointTitle = pointTitle;
		this.pid = pid;
		this.leaf = leaf;
		this.icon = icon;
		this.createUser = createUser;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.description = description;
	}

	@Override
	public String toString() {
		return "KnowledgePoint [knowledgePointId=" + knowledgePointId + ", pointTitle=" + pointTitle + ", pid=" + pid
				+ ", leaf=" + leaf + ", icon=" + icon + ", createUser=" + createUser + ", createDate=" + createDate
				+ ", updateDate=" + updateDate + ", description=" + description + "]";
	}
}
