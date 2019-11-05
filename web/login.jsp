<%--
  Created by IntelliJ IDEA.
  User: leikuan
  Date: 2019/10/20
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>欢迎登录后台管理系统</title>
    <link href="css/style.css" rel="stylesheet" type="text/css" />
    <script language="JavaScript" src="js/jquery.js"></script>
    <script src="js/cloud.js" type="text/javascript"></script>

    <script language="javascript">
        $(function(){
            $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
            $(window).resize(function(){
                $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
            })
        });
    </script>

</head>

<body style="background-color:#df7611; background-image:url(images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">



<div id="mainBody">
    <div id="cloud1" class="cloud"></div>
    <div id="cloud2" class="cloud"></div>
</div>


<div class="logintop">
    <span>欢迎登录后台管理界面平台</span>
</div>

<div class="loginbody">

    <span class="systemlogo"></span>
    <br/>
    <c:choose>
        <c:when test="${flag==0}">
            <div style="text-align: center;">
            <span style="font-size: 15px;color: darkred;font-weight: bold;">用户名或密码错误</span>
            </div>
        </c:when>
        <c:when test="${flag==1}">
            <div style="text-align: center;">
            <span style="font-size: 15px;color: darkred;font-weight: bold;">密码修改成功</span>
            </div>
        </c:when>
        <c:when test="${flag==2}">
            <div style="text-align: center;">
            <span style="font-size: 15px;color: darkred;font-weight: bold;">账号注册成功</span>
            </div>
        </c:when>
    </c:choose>
    <c:remove var="flag" scope="session"></c:remove>
    <%--<%--%>
        <%--//声明代码块判断错误提示语是否显示：--%>
        <%--Object obj =  request.getAttribute("flag");--%>
        <%--if(obj!=null){--%>
    <%--%>--%>
    <%--<div style="text-align: center;">--%>
        <%--<span style="font-size: 15px;color: darkred;font-weight: bold;">用户名或密码错误</span>--%>
    <%--</div>--%>
    <%--<%--%>
        <%--}--%>
    <%--%>--%>

    <%--<%--%>
        <%--//密码修改--%>
        <%--Object o =  session.getAttribute("pwd");--%>
        <%--if(o!=null){--%>
    <%--%>--%>
    <%--<div style="text-align: center;">--%>
        <%--<span style="font-size: 15px;color: darkred;font-weight: bold;">密码修改成功</span>--%>
    <%--</div>--%>
    <%--<%--%>
        <%--}--%>
        <%--session.removeAttribute("pwd");--%>
    <%--%>--%>

    <%--<%--%>
        <%--//密码修改--%>
        <%--Object reg =  session.getAttribute("reg");--%>
        <%--if(reg!=null){--%>
    <%--%>--%>
    <%--<div style="text-align: center;">--%>
        <%--<span style="font-size: 15px;color: darkred;font-weight: bold;">账号注册成功</span>--%>
    <%--</div>--%>
    <%--<%--%>
        <%--}--%>
        <%--session.removeAttribute("reg");--%>
    <%--%>--%>
    <div class="loginbox">
 <form action="user" method="post">
     <input type="hidden" name="oper" value="login"/>
        <ul>
            <li><input name="uname" type="text" placeholder="用户名" class="loginuser  "/></li>
            <li><input name="pwd" type="password" placeholder="密码" class="loginpwd" /></li>
            <li><input name="" type="submit" class="loginbtn" value="登录"  onclick="javascript:window.location='main.html'"  /><label><a href="user/reg.jsp">注册</a></label><label><a href="#">忘记密码？</a></label></li>
        </ul>
 </form>

    </div>

</div>


</body>

</html>

