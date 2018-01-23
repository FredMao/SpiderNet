package com.spidernet.dashboard.dao;

import java.util.List;

import com.spidernet.dashboard.entity.KnowledgePoint;
import com.spidernet.dashboard.entity.KnowledgePointCondition;
import com.spidernet.dashboard.entity.PageCondition;

public interface KnowledgePointMapper {

	    List<KnowledgePoint> queryKnowledgePointByPid(String pid);
	    List<KnowledgePoint> queryKnowledgePoints(KnowledgePointCondition condition);
	    int addKnowledgePoint(KnowledgePoint knowledgePoint);
	    int updateKnowledgePoint(KnowledgePoint knowledgePoint);
	    KnowledgePoint queryKnowledgePointById(String knowledgePointId);
	    int countPage(PageCondition pageCondition);
}
