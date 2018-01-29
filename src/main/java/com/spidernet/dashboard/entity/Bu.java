package com.spidernet.dashboard.entity;
/**
 * Bu information
 * @author nick
 *
 */
public class Bu
{
    
    private String buId;
    
    private String buName;
    
    private String description;
    
    private String sort;
    
    private String contactNumber;
    
    private String mangerName;
    
    private String orgName;

    public String getBuId()
    {
        return buId;
    }

    public void setBuId(String buId)
    {
        this.buId = buId;
    }

    public String getBuName()
    {
        return buName;
    }

    public void setBuName(String buName)
    {
        this.buName = buName;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getSort()
    {
        return sort;
    }

    public void setSort(String sort)
    {
        this.sort = sort;
    }

    public String getContactNumber()
    {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber)
    {
        this.contactNumber = contactNumber;
    }

    public String getMangerName()
    {
        return mangerName;
    }

    public void setMangerName(String mangerName)
    {
        this.mangerName = mangerName;
    }

    public Bu()
    {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	@Override
    public String toString()
    {
        return "Bu [buId=" + buId + ", buName=" + buName + ", description="
                + description + ", sort=" + sort + ", contactNumber="
                + contactNumber + ", mangerName=" + mangerName + "]";
    }
    
    

}
