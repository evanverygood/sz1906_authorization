package com.qf.oa.controller;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.qf.oa.common.Page;
import com.qf.oa.entity.SysUser;
import com.qf.oa.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author chenzetao
 * @Date 2019/9/2
 */
@Controller
@RequestMapping("sysUser")
public class SysUserController {

    @Autowired
    private IUserService userService;

    //xx/sysUser/getUserById?userId=1
    @RequestMapping("getUserById")
    @ResponseBody
    public SysUser query(Long userId){
        SysUser sysUser = userService.selectByPrimaryKey(userId);
        return sysUser;
    }

    //查询用户分页信息
    @RequestMapping("page")
    public String page(Page page, ModelMap map){
        PageInfo<SysUser> pageInfo = userService.getUserPage(page);
        //存起来
        map.put("pageInfo",pageInfo);
        return "user/userlist";
    }

    //按条件查询用户信息
    @RequestMapping("selectByCondition")
    public String selectByConditon(Page page,String userName,ModelMap map){
        PageInfo<SysUser> pageInfo = userService.getUserByCondition(page,userName);
        //存起来
        map.put("userName",userName);//保存的是查询的内容
        map.put("pageInfo",pageInfo);
        map.put("url","sysUser/selectByCondition");

        Gson gson = new Gson();
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("userName",userName);//
        map.put("params",gson.toJson(paramMap));
        return "user/userlist";
    }
}
