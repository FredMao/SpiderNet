package com.spidernet.dashboard.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.spidernet.dashboard.dao.MenuMapper;
import com.spidernet.dashboard.entity.Menu;
import com.spidernet.dashboard.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService{
	
	@Resource
    private MenuMapper menuMapper;


	@Override
	public List<Menu> queryMenuList() {
		List<Menu> list = menuMapper.queryMenuList();
		return list;
	}

	@Override
	public int getSortByParentId(int parentId) {
		String sortStr  = menuMapper.getSortByParentId(parentId);
		int sort = 0;
		if(sortStr != null){
			sort = Integer.parseInt(sortStr);;
		} 
		return sort + 1;
	}

	@Override
	public boolean addMenu(Menu menu) {
		
		if (menuMapper.addMenu(menu) > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
	}

	@Override
	public boolean updateMenu(Menu menu) {

        if (menuMapper.updateMenu(menu) > 0)
        {
            return true;
        }
        else
        {
            return false;
        }

	}

	@Override
	public boolean deleteMenu(Menu menu) {
		if (menuMapper.deleteMenu(menu) > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
	}

	@Override
	public Menu getMenuById(int id) {
		return menuMapper.getMenuById(id);
	}

	@Override
	public String getNameById(int id) {
		return menuMapper.getNameById(id);
	}

	@Override
	public int getMaxId() {
		return menuMapper.getMaxId();
	}

}
