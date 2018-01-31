package com.spidernet.dashboard.service;

import java.util.List;

import com.spidernet.dashboard.entity.Menu;

public interface MenuService {
	
	List<Menu> queryMenuList();

	int getSortByParentId(int parentId);

	boolean addMenu(Menu menu);

	boolean updateMenu(Menu menu);

	boolean deleteMenu(Menu menu);

	Menu getMenuById(int id);

	String getNameById(int id);

	int getMaxId();
	
	List<Object> menuList(List<Menu> menu);

	List<Object> menuListByEmp(String empId);
}
