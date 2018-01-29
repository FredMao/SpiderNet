package com.spidernet.dashboard.dao;

import java.util.List;

import com.spidernet.dashboard.entity.SysExec;

public interface SysExecMapper {

	List<SysExec> getSysExecByAttrName(String attrName);
	int editSysExec(SysExec sysExec);
	int addSysExec(SysExec sysExec);
}
