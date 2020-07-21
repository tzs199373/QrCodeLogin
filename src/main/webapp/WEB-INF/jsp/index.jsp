<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>手机端扫描登录页</title>
</head>
<body>
</body>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">
    $(function () {
        var uname = getQueryVariable("uname");
        $("body").html("欢迎"+uname+"来到首页")
    });



    function getQueryVariable(variable){
        var query = window.location.search.substring(1);
        var vars = query.split("&");
        for (var i=0;i<vars.length;i++) {
            var pair = vars[i].split("=");
            if(pair[0] == variable){return pair[1];}
        }
        return("");
    }
</script>
</html>