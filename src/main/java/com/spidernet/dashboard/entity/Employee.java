package com.spidernet.dashboard.entity;

import java.io.Serializable;

public class Employee implements Serializable
{
    private String employeeId;
    private String buId;
    private String projectId;
    private String empTypeId;
    private String empLevelId;
    private String erNumber;
    private String hrNumber;
    private String name;
    private String eName;
    private String password = "123456";
    private String wechatOpenId;
    private String skill;
    private Bu bu;
    public String getEmployeeId()
    {
        return employeeId;
    }
    public void setEmployeeId(String employeeId)
    {
        this.employeeId = employeeId;
    }
    public String getBuId()
    {
        return buId;
    }
    public void setBuId(String buId)
    {
        this.buId = buId;
    }
    public String getProjectId()
    {
        return projectId;
    }
    public void setProjectId(String projectId)
    {
        this.projectId = projectId;
    }
    public String getEmpTypeId()
    {
        return empTypeId;
    }
    public void setEmpTypeId(String empTypeId)
    {
        this.empTypeId = empTypeId;
    }
    public String getEmpLevelId()
    {
        return empLevelId;
    }
    public void setEmpLevelId(String empLevelId)
    {
        this.empLevelId = empLevelId;
    }
    public String getErNumber()
    {
        return erNumber;
    }
    public void setErNumber(String erNumber)
    {
        this.erNumber = erNumber;
    }
    public String getHrNumber()
    {
        return hrNumber;
    }
    public void setHrNumber(String hrNumber)
    {
        this.hrNumber = hrNumber;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String geteName()
    {
        return eName;
    }
    public void seteName(String eName)
    {
        this.eName = eName;
    }
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public String getWechatOpenId()
    {
        return wechatOpenId;
    }
    public void setWechatOpenId(String wechatOpenId)
    {
        this.wechatOpenId = wechatOpenId;
    }
    
    public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	
	public Bu getBu() {
		return bu;
	}
	public void setBu(Bu bu) {
		this.bu = bu;
	}
	public Employee()
    {
        super();
        // TODO Auto-generated constructor stub
    }
    public Employee(String employeeId, String buId, String projectId,
            String empTypeId, String empLevelId, String erNumber,
            String hrNumber, String name, String eName, String password,
            String wechatOpenId)
    {
        super();
        this.employeeId = employeeId;
        this.buId = buId;
        this.projectId = projectId;
        this.empTypeId = empTypeId;
        this.empLevelId = empLevelId;
        this.erNumber = erNumber;
        this.hrNumber = hrNumber;
        this.name = name;
        this.eName = eName;
        this.password = password;
        this.wechatOpenId = wechatOpenId;
    }
    
    

   
}
