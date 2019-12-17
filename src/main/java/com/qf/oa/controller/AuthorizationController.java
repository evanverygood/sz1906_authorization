package com.qf.oa.controller;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.qf.oa.common.Page;
import com.qf.oa.common.SysResult;
import com.qf.oa.entity.SysRole;
import com.qf.oa.entity.SysUser;
import com.qf.oa.service.IMenuService;
import com.qf.oa.service.IRoleService;
import com.qf.oa.service.IUserService;
import com.qf.oa.vo.SysMenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author chenzetao
 * @Date 2019/9/3
 */
@Controller
@RequestMapping("auth")
public class AuthorizationController {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IMenuService menuService;

    @RequestMapping("showAuthPage")
    public String toAuthPage(ModelMap map){
        List<SysRole> roleList = roleService.getRoleList();
        map.put("roleList",roleList);
        return "auth/authorization";
    }

    //查询当前角色下所有的用户信息
    @RequestMapping("queryAuthUserByRoleId")
    public String queryAuthUserByRoleId(Long roleId, Page page,ModelMap map){
       PageInfo<SysUser> pageInfo = userService.queryAuthUserByRoleId(roleId,page);
        map.put("pageInfo",pageInfo);
        map.put("roleId",roleId);
        map.put("url","auth/queryAuthUserByRoleId");

        Gson gson = new Gson();
        Map<String,Object> parmaMap = new HashMap<String,Object>();
        parmaMap.put("roleId",roleId);
        map.put("params",gson.toJson(parmaMap));
        return "auth/auth_user";
    }

    //查询当前角色下没有的用户信息
    @RequestMapping("queryNoAuthUserByRoleId")
    public String queryNoAuthUserByRoleId(Long roleId,String userName,Page page,ModelMap map){
        PageInfo<SysUser> pageInfo = userService.queryNoAuthUserByRoleId(page,roleId,userName);
        map.put("pageInfo",pageInfo);
        map.put("roleId",roleId);
        map.put("userName",userName);
        map.put("url","auth/queryNoAuthUserByRoleId");

        Gson gson = new Gson();
        Map<String,Object> parmaMap = new HashMap<String,Object>();
        parmaMap.put("roleId",roleId);
        parmaMap.put("userName",userName);
        map.put("params",gson.toJson(parmaMap));
        return "auth/no_auth_user";
    }

    //批量授权用户，实际上往角色用户关系表中添加数据
    @RequestMapping("batchAddUser")
    @ResponseBody
    public SysResult batchAddUser(@RequestParam("ids") List<Long> idsList,Long roleId){
       return roleService.batchAddUser(idsList,roleId);
    }

    //解除授权用户,实际上往角色用户关系 表中删除一条数据
    @RequestMapping("delAuthUser")
    @ResponseBody
    public SysResult delAuthUser(Long userId,Long roleId){
        return roleService.delAuthUser(userId,roleId);
    }

    //授权菜单
    @RequestMapping("queryAuthMenuByRoleId")
    public String queryAuthMenuByRoleId(Long roleId,ModelMap map){
        //查询菜单树展示的数据
       List<SysMenuVO> menuVOList =  menuService.getMenuList(roleId);
       map.put("menuVOList",new Gson().toJson(menuVOList));
       return "auth/auth_menu";
    }

}
