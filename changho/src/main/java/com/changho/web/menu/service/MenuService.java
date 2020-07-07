package com.changho.web.menu.service;

import java.util.List;

import com.changho.web.menu.model.MenuVO;

public interface MenuService {
	
	public List<MenuVO> getMenuList() throws Exception;
	
	public void saveMenu(MenuVO menuVO) throws Exception;
	
	public void updateMenu(MenuVO menuVO) throws Exception;
	
	public void deleteMenu(String code) throws Exception;
	
}