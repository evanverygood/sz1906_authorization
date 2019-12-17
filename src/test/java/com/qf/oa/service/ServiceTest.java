package com.qf.oa.service;

import com.qf.oa.entity.SysUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author chenzetao
 * @Date 2019/9/2
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext-*.xml")
public class ServiceTest {

    @Autowired
    private IUserService userService;

    @Test
    public void testQuery(){
        SysUser sysUser = userService.selectByPrimaryKey(1L);
        System.out.println(sysUser);
    }
}
