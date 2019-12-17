package com.qf.oa.mapper;

import com.qf.oa.dao.IBaseDao;
import com.qf.oa.entity.SysMenu;

import java.util.List;

public interface SysMenuMapper extends IBaseDao<SysMenu> {

    List<SysMenu> getAllMenuList();

    List<SysMenu> getRoleMenuList(Long roleId);

    List<Long> getRoleMenuIdList(Long roleId);
}