package com.spidernet.util;

public interface Constants
{
    public final static String ER_PATTERN = "^E[0-9]{9}";
    public final static String HR_PATTERN = "^\\d{5,12}$";
    public final static String WECHAT_PATTERN = "";
    public final static int ONE = 1;
    public final static int TWO = 2;
    public final static int ZERO = 0;
    public final static String TRAINNING_STATUS_REGISTED = "0";
    public final static String EXAM_STATUS_REGISTED = "2";
    public final static String PATH = "D:/Excel/";
    
    public static interface SYS_EXEC_ATTR{
    	
    	String SYNC_EMPLOYEE_TIME = "sync_employee_pre_time";
    }
    
    public  static interface SyncEmployeeResponse{
    	String RESULT = "result"; 
    	String ERROR_LIST = "errorList";
    	String DATA_LIST = "dataList";
    }
    
    public static interface RESULT_CODE{
    	String ERROR = "00001";
    	String SUCCESS = "00000";
    	
    	String SYSTEM_ERROR = "00001";
    	String VERIFY_ERROR = "00010";
    	
    }
    
    public  static interface SyncEmployee{
    	String ER_NUM = "erNum"; 
    	String HR_NUM = "hrNum"; 
    	String C_USER_NAME = "cUserName"; 
    	String E_USER_NAME = "eUserName"; 
    	String SKILL = "skill"; 
    	String ORG_NAME = "orgName"; 
    	String BU_NAME = "buName"; 
    }


}
