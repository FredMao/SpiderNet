package com.spidernet.dashboard.dao;

import java.util.List;

import com.spidernet.dashboard.entity.Menu;

public interface MenuMapper {
	

	List<Menu> queryMenuList();

	String getSortByParentId(int parentId);

	int addMenu(Menu menu);

	int updateMenu(Menu menu);
	
	int deleteMenu(Menu menu);

	Menu getMenuById(int id);

	String getNameById(int id);

	int getMaxId();

	List<Menu> menuListByEmp(String empId);

}
