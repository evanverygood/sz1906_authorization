package com.qf.oa.service;

import com.qf.oa.entity.SysMenu;
import com.qf.oa.vo.SysMenuVO;

import java.util.List;

/**
 * @Author chenzetao
 * @Date 2019/9/4
 */
public interface IMenuService extends IBaseService<SysMenu> {
    List<SysMenuVO> getMenuList(Long roleId);
}
