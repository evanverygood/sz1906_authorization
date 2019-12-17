package com.qf.oa.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.oa.common.Page;
import com.qf.oa.dao.IBaseDao;
import com.qf.oa.entity.SysUser;
import com.qf.oa.mapper.SysUserMapper;
import com.qf.oa.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author chenzetao
 * @Date 2019/9/2
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<SysUser> implements IUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public IBaseDao getDao() {
        return sysUserMapper;
    }

    @Override
    public PageInfo<SysUser> getUserPage(Page page) {
        PageHelper.startPage(page.getCurrentPage(),page.getPageSize());
        List<SysUser> userList = sysUserMapper.getUserList();
        return new PageInfo<>(userList);
    }

    @Override
    public PageInfo<SysUser> getUserByCondition(Page page, String userName) {
        PageHelper.startPage(page.getCurrentPage(),page.getPageSize());
        List<SysUser> userList = sysUserMapper.getUserByCondtion(userName);
        return new PageInfo<>(userList);
    }

    @Override
    public PageInfo<SysUser> queryAuthUserByRoleId(Long roleId, Page page) {
        PageHelper.startPage(page.getCurrentPage(),page.getPageSize());
        List<SysUser> userList = sysUserMapper.queryUserListByRoleId(roleId);
        return new PageInfo<>(userList);
    }

    @Override
    public PageInfo<SysUser> queryNoAuthUserByRoleId(Page page, Long roleId,String userName) {
        PageHelper.startPage(page.getCurrentPage(),page.getPageSize());
        List<SysUser> userList = sysUserMapper.queryNoAuthUserByRoleId(roleId,userName);
        return new PageInfo<>(userList);
    }
}
