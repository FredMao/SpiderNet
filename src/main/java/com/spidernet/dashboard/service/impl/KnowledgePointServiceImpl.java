package com.spidernet.dashboard.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.spidernet.dashboard.dao.KnowledgePointMapper;
import com.spidernet.dashboard.entity.KnowledgePoint;
import com.spidernet.dashboard.entity.KnowledgePointCondition;
import com.spidernet.dashboard.entity.PageCondition;
import com.spidernet.dashboard.service.KnowledgePointService;

@Service
public class KnowledgePointServiceImpl implements KnowledgePointService {

	@Resource
	private KnowledgePointMapper knowledgePointMapper;

	@Override
	public List<KnowledgePoint> queryKnowledgePointByPid(String pid) {
		
		return knowledgePointMapper.queryKnowledgePointByPid(pid);
	}

	@Override
	public int addKnowledgePoint(KnowledgePoint knowledgePoint) {
		return knowledgePointMapper.addKnowledgePoint(knowledgePoint);
	}

	@Override
	public int updateKnowledgePoint(KnowledgePoint knowledgePoint) {
		return knowledgePointMapper.updateKnowledgePoint(knowledgePoint);
	}

	@Override
	public KnowledgePoint queryKnowledgePointById(String knowledgePointId) {
		return knowledgePointMapper.queryKnowledgePointById(knowledgePointId);
	}

	@Override
	public List<KnowledgePoint> queryKnowledgePoints(KnowledgePointCondition condition) {
		// TODO Auto-generated method stub
		return knowledgePointMapper.queryKnowledgePoints(condition);
	}

	@Override
	public int countPage(PageCondition pageCondition) {
		// TODO Auto-generated method stub
		return knowledgePointMapper.countPage(pageCondition);
	}
	 
	 
}
