package com.spidernet.dashboard.service;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spidernet.dashboard.entity.SysExec;
import com.spidernet.util.Constants;
import com.spidernet.util.DateUtil;
import com.spidernet.util.Utils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:conf/spring-mybatis.xml" })
public class SysExecTest {

	@Resource
	public SysExecService sysExecService;
	
	@Test
	public void addSysExecTest(){
		List<SysExec> sysExecs = sysExecService.getSysExecByAttrName(Constants.SYS_EXEC_ATTR.SYNC_EMPLOYEE_TIME);
		String lastUpdateTime = DateUtil.currentDateTimeString();
		
		SysExec sysExec = null;
		if(null != sysExecs && sysExecs.size()>0){
			sysExec = sysExecs.get(0);
			lastUpdateTime = sysExec.getAttrValue();
		}else{
			sysExec = new SysExec();
			sysExec.setRunId(Utils.getUUID());
			sysExec.setAttrName(Constants.SYS_EXEC_ATTR.SYNC_EMPLOYEE_TIME);
			sysExec.setAttrValue(lastUpdateTime);
			assertEquals(1, sysExecService.addSysExec(sysExec));
		}
		sysExec.setAttrValue(DateUtil.currentDateTimeString());
		assertEquals(1, sysExecService.editSysExec(sysExec));
		
	}
	
	@Test
	public void editSysExecTest(){
		SysExec sysExec = new SysExec();
		sysExec.setRunId("8538f510137543c6b6b6b9ef399d83cc");
		sysExec.setAttrName(Constants.SYS_EXEC_ATTR.SYNC_EMPLOYEE_TIME);
		sysExec.setAttrValue(DateUtil.currentDateTimeString());
		assertEquals(1, sysExecService.editSysExec(sysExec));
		
	}
	
	@Test
    public void getSysExecByAttrName()
    {
		List<SysExec> sysExec = sysExecService.getSysExecByAttrName(Constants.SYS_EXEC_ATTR.SYNC_EMPLOYEE_TIME);
        assertNotNull(sysExec);
    }
	
}
