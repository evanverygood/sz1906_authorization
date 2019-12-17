package com.qf.oa.service;

import com.github.pagehelper.PageInfo;
import com.qf.oa.common.Page;
import com.qf.oa.entity.SysUser;

/**
 * @Author chenzetao
 * @Date 2019/9/2
 */
public interface IUserService extends IBaseService<SysUser> {
    PageInfo<SysUser> getUserPage(Page page);

    PageInfo<SysUser> getUserByCondition(Page page, String userName);

    PageInfo<SysUser> queryAuthUserByRoleId(Long roleId,Page page);

    PageInfo<SysUser> queryNoAuthUserByRoleId(Page page, Long roleId,String userName);
}
