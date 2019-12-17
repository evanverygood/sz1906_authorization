package com.qf.oa.service.impl;

import com.qf.oa.common.SysResult;
import com.qf.oa.dao.IBaseDao;
import com.qf.oa.entity.SysRole;
import com.qf.oa.mapper.SysRoleMapper;
import com.qf.oa.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author chenzetao
 * @Date 2019/9/3
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<SysRole> implements IRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public IBaseDao<SysRole> getDao() {
        return sysRoleMapper;
    }

    @Override
    public List<SysRole> getRoleList() {
        return sysRoleMapper.getRoleList();
    }

    @Override
    public SysResult batchAddUser(List<Long> idsList, Long roleId) {
       int count = sysRoleMapper.batchAddUser(idsList,roleId);
       SysResult sysResult = new SysResult();
       if(count>0){
           //新增成功
           sysResult.setResult(true);
       }else{
            sysResult.setResult(false);
       }
        return sysResult;
    }

    @Override
    public SysResult delAuthUser(Long userId, Long roleId) {
        int count = sysRoleMapper.delAuthUser(userId,roleId);
        SysResult sysResult = new SysResult();
        if(count>0){
            //新增成功
            sysResult.setResult(true);
        }else{
            sysResult.setResult(false);
        }
        return sysResult;
    }
}
