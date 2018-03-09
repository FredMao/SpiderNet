package com.spidernet.dashboard.entity;

public class PlanPersonalTrainning
{
    private String trainningId;
    private String employeeId;
    private String status;
    private String planId;

    public String getTrainningId()
    {
        return trainningId;
    }
    public void setTrainningId(String trainningId)
    {
        this.trainningId = trainningId;
    }
    public String getEmployeeId()
    {
        return employeeId;
    }
    public void setEmployeeId(String employeeId)
    {
        this.employeeId = employeeId;
    }
    public String getStatus()
    {
        return status;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }
    public String getPlanId()
    {
        return planId;
    }
    public void setPlanId(String planId)
    {
        this.planId = planId;
    }
}
