package com.spidernet.dashboard.service;

import java.util.List;

import com.spidernet.dashboard.entity.SysExec;

public interface SysExecService {

	List<SysExec> getSysExecByAttrName(String attrName);
	int editSysExec(SysExec sysExec);
	int addSysExec(SysExec sysExec);
}
