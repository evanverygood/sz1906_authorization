package com.qf.oa.service;

import com.qf.oa.common.SysResult;
import com.qf.oa.entity.SysRole;

import java.util.List;

/**
 * @Author chenzetao
 * @Date 2019/9/3
 */
public interface IRoleService extends IBaseService<SysRole> {
    List<SysRole> getRoleList();

    SysResult batchAddUser(List<Long> idsList, Long roleId);

    SysResult delAuthUser(Long userId, Long roleId);
}
