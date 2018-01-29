package com.spidernet.dashboard.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.spidernet.dashboard.dao.SysExecMapper;
import com.spidernet.dashboard.entity.SysExec;
import com.spidernet.dashboard.service.SysExecService;

@Service
public class SysExecServiceImpl implements SysExecService {

	
	@Resource
	private SysExecMapper sysExecMapper;
	
	@Override
	public List<SysExec> getSysExecByAttrName(String attrName) {
		// TODO Auto-generated method stub
		return sysExecMapper.getSysExecByAttrName(attrName);
	}

	@Override
	public int editSysExec(SysExec sysExec) {
		// TODO Auto-generated method stub
		return sysExecMapper.editSysExec(sysExec);
	}

	@Override
	public int addSysExec(SysExec sysExec) {
		// TODO Auto-generated method stub
		return sysExecMapper.addSysExec(sysExec);
	}

}
