package com.qf.oa.service;

import com.qf.oa.entity.SysUser;

/**
 * @Author chenzetao
 * @Date 2019/9/2
 */
public interface IBaseService<T> {
    int deleteByPrimaryKey(Long id);

    int insert(T t);

    int insertSelective(T t);

    T selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(T t);

    int updateByPrimaryKey(T t);
}
