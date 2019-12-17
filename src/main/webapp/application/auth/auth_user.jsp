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
                <td><input type="checkbox" value="1" name=""></td>
                <td>${user.userId}</td>
                <td>${user.userName}</td>
                <td>${user.phone}</td>
                <td>${user.email}</td>
                <td>
                    <fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd"/>
                </td>
                <td>${user.introduce}</td>
                <td class="td-manage"><a style="text-decoration:none" onClick="admin_stop(this,'10001')" href="javascript:;" title="停用"><i class="Hui-iconfont">&#xe631;</i>
                </a> <a title="删除" href="javascript:;" onclick="del_auth_user(this,${user.userId})" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <%--引入分页的导航条--%>
    <jsp:include page="/application/common/page.jsp">
        <jsp:param name="bodyId" value="authorization"></jsp:param>
    </jsp:include>
</div>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
<script type="text/javascript" src="lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="js/H-ui.js"></script>
<script type="text/javascript" src="js/H-ui.admin.js"></script>
<script type="text/javascript">

    /*
        参数解释：
        title	标题
        url		请求的url
        id		需要操作的数据id
        w		弹出层宽度（缺省调默认值）
        h		弹出层高度（缺省调默认值）
    */

    /*管理员-删除*/
    function del_auth_user(obj,userid){
        layer.confirm('确认要解除授权用户吗？',function(index){
            //发送异步请求，去后台解析授权用户
            $.ajax({
                url:"auth/delAuthUser",
                type:"GET",
                data:"userId="+userid+"&roleId="+${roleId},
                success:function(data){
                    //{result:true,data:"xx"}
                    if(data.result){
                        layer.msg('解除授权用户成功!',{icon: 6,time:2000});
                    }else{
                        layer.msg('解除授权用户失败!',{icon: 5,time:2000});
                    }
                }
            })

        });
    }


</script>
</body>
</html>
