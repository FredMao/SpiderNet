package com.spidernet.dashboard.entity;

import java.sql.Timestamp;

public class Trainning
{
    private String trainningId;
    private String courseName;
    private String time;
    private String location;
    private String teacher;
    private String url;
    private String status;
    private String knowledgePoint;
    private String SubTopic;
    private Timestamp createTime;


    public String getTrainningId()
    {
        return trainningId;
    }
    public void setTrainningId(String trainningId)
    {
        this.trainningId = trainningId;
    }
    public String getCourseName()
    {
        return courseName;
    }
    public void setCourseName(String courseName)
    {
        this.courseName = courseName;
    }
    public String getTime()
    {
        return time;
    }
    public void setTime(String time)
    {
        this.time = time;
    }
    public String getLocation()
    {
        return location;
    }
    public void setLocation(String location)
    {
        this.location = location;
    }
    public String getTeacher()
    {
        return teacher;
    }
    public void setTeacher(String teacher)
    {
        this.teacher = teacher;
    }
    public String getUrl()
    {
        return url;
    }
    public void setUrl(String url)
    {
        this.url = url;
    }
    public String getStatus()
    {
        return status;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getKnowledgePoint() {
        return knowledgePoint;
    }

    public void setKnowledgePoint(String knowledgePoint) {
        this.knowledgePoint = knowledgePoint;
    }

    public String getSubTopic() {
        return SubTopic;
    }

    public void setSubTopic(String subTopic) {
        SubTopic = subTopic;
    }
    public Timestamp getCreateTime()
    {
        return createTime;
    }
    public void setCreateTime(Timestamp createTime)
    {
        this.createTime = createTime;
    }
}
