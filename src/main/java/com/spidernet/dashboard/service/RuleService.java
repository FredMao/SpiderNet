package com.spidernet.dashboard.service;
/**
 * rule
 * @author Lulu
 *
 */
import java.util.List;

import com.spidernet.dashboard.entity.Rule;
import com.spidernet.dashboard.entity.RuleMenu;
import com.spidernet.dashboard.entity.RulePageCondition;


public interface RuleService
{
    boolean addRule(Rule rule);
    List<Rule> queryRuleInfo(RulePageCondition rulePageCondition);
    int countRulePage(RulePageCondition rulePageCondition);
    List<Rule> queryRuleByName(String name);
    List<Rule> queryRuleName();
    Rule queryRuleById(String id);
    Boolean editRule(Rule rule);
    Boolean deleteRule(Rule rule);
    Boolean checkNameExists(String name);    
    int accountRuleMenu(String ruleId);
    Boolean deleteRuleMenu(RuleMenu ruleMenu);
    Boolean addRuleMenu(RuleMenu ruleMenu);
    List<RuleMenu> queryRuleMenu(String ruleId);
    public List<Rule> queryRuleInfoAll();
}
