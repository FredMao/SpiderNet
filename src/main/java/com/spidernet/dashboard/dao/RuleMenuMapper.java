package com.spidernet.dashboard.dao;
/**
 * ruleMenu
 * @author Lulu
 *
 */
import java.util.List;

import com.spidernet.dashboard.entity.RuleMenu;

public interface RuleMenuMapper
{
    int accountRuleMenu(String ruleId);  
    int deleteRuleMenu(RuleMenu ruleMenu);
	int addRuleMenu(RuleMenu ruleMenu);
	List<RuleMenu> queryRuleMenu(String ruleId);
}
