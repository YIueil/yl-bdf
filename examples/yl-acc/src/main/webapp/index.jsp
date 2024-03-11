<%@ page import="cc.yiueil.util.SpringContextUtils" %>
<%@ page import="org.springframework.core.env.Environment" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>yl-acc</title>
</head>
<body>
    <%= SpringContextUtils.getBean(Environment.class).getProperty("application.name") %> Started Success<br/>
    <img src="https://s2.loli.net/2024/03/12/gxt1MwlUsXJ4LGr.png"/>
</body>
</html>
