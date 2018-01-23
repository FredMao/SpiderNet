package com.spidernet.dashboard.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spidernet.dashboard.entity.KnowledgePoint;
import com.spidernet.util.Utils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:conf/spring-mybatis.xml" })
public class KnowledgePointServiceTest {

	
	@Resource
	KnowledgePointService knowledgePointService;
	
    @Before
    public void setUp() throws Exception
    {
    }
    
    @Test
    public void testAddKnowledgePoint()
    {
    	KnowledgePoint kpoint = new KnowledgePoint();
    	kpoint.setKnowledgePointId(Utils.getUUID());
    	kpoint.setPointTitle("Title_Test");
    	kpoint.setCreateDate(new Date());
    	kpoint.setUpdateDate(new Date());
    	int result = knowledgePointService.addKnowledgePoint(kpoint);
    	assertNotNull(result);
    }
    
    @Test
    public void testUpdateKnowledgePoint()
    {
    	
    	KnowledgePoint kpoint = new KnowledgePoint();
    	kpoint.setKnowledgePointId(Utils.getUUID());
    	kpoint.setPointTitle("Title_Test");
    	kpoint.setCreateDate(new Date());
    	kpoint.setUpdateDate(new Date());
    	int result = knowledgePointService.addKnowledgePoint(kpoint);
    	
    	kpoint.setPointTitle("Update test");
    	int updateresult = knowledgePointService.updateKnowledgePoint(kpoint);
    	assertTrue(result == updateresult);
    }
    
    @Test
    public void queryKnowledgePointByPid(){
    	List<KnowledgePoint> datas = knowledgePointService.queryKnowledgePointByPid("");
    	assertNotNull(datas);
    }
    
    @Test
    public void queryKnowledgePointById(){
    	KnowledgePoint data = knowledgePointService.queryKnowledgePointById("e629e27a7abd433785dc8310594c1e6b");
    	assertNotNull(data);
    }
}
