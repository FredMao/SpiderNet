package com.spidernet.dashboard.entity;

public class TrainPlan {

	private int allocationPlanId;
	private String parentTrainingName;
	private String childTrainName;
	private String trainTimeStart;
	private String trainTimeEnd;
	private String trainRoom;
	private int participants;
	private String active;
	private String TrainCourseId;
	public int getAllocationPlanId() {
		return allocationPlanId;
	}
	public void setAllocationPlanId(int allocationPlanId) {
		this.allocationPlanId = allocationPlanId;
	}
	public String getParentTrainingName() {
		return parentTrainingName;
	}
	public void setParentTrainingName(String parentTrainingName) {
		this.parentTrainingName = parentTrainingName;
	}
	public String getChildTrainName() {
		return childTrainName;
	}
	public void setChildTrainName(String childTrainName) {
		this.childTrainName = childTrainName;
	}
	public String getTrainTimeStart() {
		return trainTimeStart;
	}
	public void setTrainTimeStart(String trainTimeStart) {
		this.trainTimeStart = trainTimeStart;
	}
	public String getTrainTimeEnd() {
		return trainTimeEnd;
	}
	public void setTrainTimeEnd(String trainTimeEnd) {
		this.trainTimeEnd = trainTimeEnd;
	}
	public String getTrainRoom() {
		return trainRoom;
	}
	public void setTrainRoom(String trainRoom) {
		this.trainRoom = trainRoom;
	}
	public int getParticipants() {
		return participants;
	}
	public void setParticipants(int participants) {
		this.participants = participants;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getTrainCourseId() {
		return TrainCourseId;
	}
	public void setTrainCourseId(String trainCourseId) {
		TrainCourseId = trainCourseId;
	}

	

}
