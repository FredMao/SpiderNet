package com.spidernet.dashboard.service;

import java.util.List;

import com.spidernet.dashboard.entity.KnowledgePoint;
import com.spidernet.dashboard.entity.KnowledgePointCondition;
import com.spidernet.dashboard.entity.PageCondition;

public interface KnowledgePointService {

	List<KnowledgePoint> queryKnowledgePointByPid(String pid);
	int countPage(PageCondition pageCondition);
	List<KnowledgePoint> queryKnowledgePoints(KnowledgePointCondition condition);
    int addKnowledgePoint(KnowledgePoint knowledgePoint);
    int updateKnowledgePoint(KnowledgePoint knowledgePoint);
    KnowledgePoint queryKnowledgePointById(String knowledgePointId);
}
