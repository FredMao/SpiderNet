package com.spidernet.dashboard.service;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:conf/spring-mybatis.xml" })
public class EmployeeInfoServiceTest
{

    @Resource
    EmployeeInfoService employeeInfoService;

    @Test
    public void configRuleTest()
    {
       
    	int c = employeeInfoService.configRule("qqqq", "dddd,eee");
    	assertEquals(1, c);
    }
    

}
