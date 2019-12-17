<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/9/2
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<base href="${pageContext.request.contextPath}/">
<link href="css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
<head>
    <title>Title</title>
</head>
<body id="userBody">
<div class="pd-20">
    <div class="text-c">
        <div class="row cl ">
            <div class="formControls col-3">
                登录名: <input type="text" class="input-text" style="width: 250px" id="userName" value="${userName}">
            </div>
        </div>
        <div class="row cl">
            <div class="cl pd-5">
                <button type="button"
                        class="btn btn-success radius" id="" name="" onclick="selectByCondition()">
                    <i class="Hui-iconfont">&#xe665;</i> 搜用户
                </button>
            </div>
        </div>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
        <span class="l">
            <a href="javascript:;" onclick="batchAddUser()" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 批量授权</a>
        </span>
    <table class="table table-border table-bordered table-bg">
        <thead>
        <tr>
            <th scope="col" colspan="9">员工列表</th>
        </tr>
        <tr class="text-c">
            <th width="25"><input type="checkbox" name="" value=""></th>
            <th width="40">ID</th>
            <th width="150">用户名</th>
            <th width="90">手机</th>
            <th width="150">邮箱</th>
            <th width="150">生日</th>
            <th width="130">个人介绍</th>
            <th width="100">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pageInfo.list}" var="user">
            <tr class="text-c">
                <td><input type="checkbox" value="${user.userId}" class="checkBox"></td>
                <td>${user.userId}</td>
                <td>${user.userName}</td>
                <td>${user.phone}</td>
                <td>${user.email}</td>
                <td>
                    <fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd"/>
                </td>
                <td>${user.introduce}</td>
                <td class="td-manage"></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <%--引入分页的导航条--%>
    <jsp:include page="/application/common/page.jsp">
    <jsp:param name="bodyId" value="userBody"></jsp:param>
</jsp:include>
</div>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
<script type="text/javascript" src="lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="js/H-ui.js"></script>
<script type="text/javascript" src="js/H-ui.admin.js"></script>
<script type="text/javascript">
    function selectByCondition(){
        //得到输入的用户名
        var userName = $("#userName").val();

        //发送异步请求按条件查询用户信息
        $("#userBody").load("auth/queryNoAuthUserByRoleId",{"userName":userName,"roleId":${roleId}});
    }

    /*
        参数解释：
        title	标题
        url		请求的url
        id		需要操作的数据id
        w		弹出层宽度（缺省调默认值）
        h		弹出层高度（缺省调默认值）
    */
    /*管理员-增加*/
    function admin_add(title,url,w,h){
        layer_show(title,url,w,h);
    }
    /*管理员-删除*/
    function batchAddUser(){
        layer.confirm('确认要授权这些用户吗？',function(index){
           //找到所有选中的复选框
            var checkes = $(".checkBox:checked");
            //判断是否选择授权用户，如果没有，给予提示
            if(checkes.length==0){
                layer.msg('请选择用户!',{icon: 5,time:1000});
                return;
            }
            //定义一个数组，用来装id
            var ids = [];
            for(var i=0;i<checkes.length;i++){
                //往数组中装id
                ids.push(checkes[i].value);
            }
            //发送异步请求，到后台加一个新增的操作
            $.ajax({
                url:"auth/batchAddUser",
                type:'GET',
                data:"ids="+ids+"&roleId="+${roleId},
                success:function(data){
                    //{result:true,data:null}
                    //给予操作提示
                    if(data.result){
                        //新增成功
                        layer.msg('批量授权用户成功!',{icon: 6,time:2000},function(){
                            //刷新页面
                            location.reload();
                        });
                    }else{
                        //新增成功
                        layer.msg('批量授权用户失败!',{icon: 5,time:2000});
                    }
                }
            })
        });
    }
    /*管理员-编辑*/
    function admin_edit(title,url,id,w,h){
        layer_show(title,url,w,h);
    }
    /*管理员-停用*/
    function admin_stop(obj,id){
        layer.confirm('确认要停用吗？',function(index){
            //此处请求后台程序，下方是成功后的前台处理……

            $(obj).parents("tr").find(".td-manage").prepend('<a onClick="admin_start(this,id)" href="javascript:;" title="启用" style="text-decoration:none"><i class="Hui-iconfont">&#xe615;</i></a>');
            $(obj).parents("tr").find(".td-status").html('<span class="label label-default radius">已禁用</span>');
            $(obj).remove();
            layer.msg('已停用!',{icon: 5,time:1000});
        });
    }

    /*管理员-启用*/
    function admin_start(obj,id){
        layer.confirm('确认要启用吗？',function(index){
            //此处请求后台程序，下方是成功后的前台处理……


            $(obj).parents("tr").find(".td-manage").prepend('<a onClick="admin_stop(this,id)" href="javascript:;" title="停用" style="text-decoration:none"><i class="Hui-iconfont">&#xe631;</i></a>');
            $(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
            $(obj).remove();
            layer.msg('已启用!', {icon: 6,time:1000});
        });
    }
</script>
</body>
</html>
