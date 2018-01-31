package com.spidernet.dashboard.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
		if(list.size() == 0){
			Menu homeMenu = new Menu();
			homeMenu.setId(0);
			homeMenu.setName("Home Page");
			homeMenu.setParentId(-1);
			homeMenu.setPicUrl("glyphicon glyphicon-home");
			homeMenu.setRemark("This is <Home Page> Information !");
			homeMenu.setUrl("#");
			homeMenu.setValid("Y");
			homeMenu.setSort(1);
			addMenu(homeMenu);
			list.add(homeMenu);
		}
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

	@Override
	public List<Object> menuList(List<Menu> menu) {
		List<Object> list = new ArrayList<Object>(); 
		  for (Menu x : menu) {     
	          Map<String,Object> mapArr = new LinkedHashMap<String, Object>(); 
	          mapArr.put("id", x.getId());  
	          mapArr.put("name", x.getName());
	          mapArr.put("MenuUrl", x.getUrl());
	          mapArr.put("picUrl", x.getPicUrl());
	          mapArr.put("pId", x.getParentId()); 
	          String pName = getNameById(x.getParentId());
	          mapArr.put("pName", pName);  
	          mapArr.put("remark", x.getRemark());
	          mapArr.put("sort", x.getSort());
	          list.add(mapArr);  
	      }     
	      return list;
	}

	@Override
	public List<Object> menuListByEmp(String empId) {
		List<Menu>  menuList =  menuMapper.menuListByEmp(empId);
		List<Object>  menuJson = menuList(menuList);
		
		return menuJson;
	}
}
