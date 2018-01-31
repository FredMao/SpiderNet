package com.spidernet.dashboard.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spidernet.dashboard.entity.Menu;
import com.spidernet.dashboard.service.MenuService;
import com.spidernet.util.Utils;

@Controller
@RequestMapping("/menuInfo")
public class MenuController {
	
	@Resource
	MenuService menuService;
	
	private static Logger logger = LoggerFactory
            .getLogger(MenuController.class);
	
	
	public static Map<String,Object> mapArray = new LinkedHashMap<String, Object>();  
    public List<Menu> menuCommon;  
    
    
    @RequestMapping("/menuManagement")
    public String top(final HttpServletRequest request,
            final HttpServletResponse response)
    {
        return "menuPage/menuManagement";
    }
    
    @RequestMapping("/getMenuById")
    @ResponseBody
    public Menu getMenuById(final HttpServletRequest request,
            final HttpServletResponse response){
    	
    	int id = Integer.parseInt(request.getParameter("id"));
		Menu menu = menuService.getMenuById(id);
		int pId = menu.getParentId();
		String pName = getNameById(pId);
		menu.setParentName(pName);
		
		return menu;
	}
    
    @RequestMapping("/menu")
    public String update2(final HttpServletRequest request,
            final HttpServletResponse response)
    {
        return "menu/menu";
    }
	
    @RequestMapping("/showMenu")
    @ResponseBody
    public List<Object> showMenu(final HttpServletRequest request,
            final HttpServletResponse response){
		
		List<Menu> menuList = menuService.queryMenuList();
		
		return menuService.menuList(menuList);
	}
    
    @RequestMapping("/updateMenu")
    @ResponseBody
    public boolean updateMenu(final HttpServletRequest request,
            final HttpServletResponse response, Menu menu){
		
		boolean resultFlag = menuService.updateMenu(menu);
		
		return resultFlag;
	}
    
    @RequestMapping("/deleteMenu")
    @ResponseBody
    public boolean deleteMenu(final HttpServletRequest request,
            final HttpServletResponse response,
            Menu menu){
    	boolean resultFlag = menuService.deleteMenu(menu);
		
		return resultFlag;
    }
    
    
    @RequestMapping("/addMenu")
    @ResponseBody
    public boolean addMenu(final HttpServletRequest request,
            final HttpServletResponse response){
    	
    	int id = menuService.getMaxId();
    	String name = request.getParameter("name");
    	int parentId = Integer.parseInt(request.getParameter("id"));
        String picUrl = request.getParameter("picUrl");
        String remark = request.getParameter("remark");
        int sort = menuService.getSortByParentId(parentId);
        String url = request.getParameter("url");
        
        Menu menu = new Menu();
        
        menu.setId(id);
        menu.setName(name);
        menu.setParentId(parentId);
        menu.setPicUrl(picUrl);
        menu.setRemark(remark);
        menu.setSort(sort);
        menu.setUrl(url);
        
        
        boolean resultFlag = menuService.addMenu(menu);
        
        return resultFlag;
    }
	
	
	private String getNameById(int id) {
		return menuService.getNameById(id);
	}  
     		 
      
  
	

}
