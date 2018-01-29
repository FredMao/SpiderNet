package com.spidernet.dashboard.dao;
/**
 * rule
 * @author Lulu
 *
 */
import java.util.List;


import com.spidernet.dashboard.entity.Rule;
import com.spidernet.dashboard.entity.RulePageCondition;

public interface RuleMapper
{
    int addRule(Rule rule);
    List<Rule> queryRuleInfo(RulePageCondition rulePageCondition);
    int countRulePage(RulePageCondition rulePageCondition);
    List<Rule> queryRuleByName(String name);
    List<Rule> queryRuleName();
    Rule queryRuleById(String id);
    int editRule(Rule rule);
    int deleteRule(Rule rule);
    int checkNameExists(String name);
    List<Rule> queryRuleInfoAll();
}

