<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>手机端扫描登录页</title>
</head>
<style>
    .l_m_l {
        float: left;
        font-size: 14px;
        padding: 5px 0 0 0;
        width: 330px;
        color: #414141;
    }

    .l_m_linput {
        height: 31px;
        position: relative;
        width: 300px;
        margin-bottom: 21px;
    }

    .l_m_linput span {
        float: left;
        width: 78px;
        text-align: right;
        line-height: 31px;
    }

    input {
        float: left;
        width: 195px;
        height: 24px;
        line-height: 24px;
        background: #f2f2f2;
        border: 1px solid #c4c4c4;
        padding: 2px 22px 2px 2px;
    }

    .l_m_lload a {
        display: block;
        width: 154px;
        height: 40px;
        margin: 0 auto;
        line-height: 40px;
        text-align: center;
        font-size: 18px;
        color: #52340c;
        text-decoration: none;
    }
</style>
<body>
<div class="l_m_l">
    <p class="l_m_linput">
        <span>用户名：</span><input type="text" id="login_name" value="admin">
    </p>
    <p class="l_m_linput">
        <span>密码：</span><input type="password" id="login_psw" value="123456">
    </p>
    <div class="l_m_lload">
        <a href="javascript:login();">登录</a>
    </div>
</div>
</body>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">
    //登录
    function login(){
        $.post("/QrCodeLogin/login/PhoneLogin", {
            uuid : $.getUrlParam('uuid'),
            uname:$("#login_name").val(),
            upwd:$("#login_psw").val()
        }, function(data) {
            if(data == ""){
                alert("登录失败");
            }else{
                alert("登录成功");
            }
        });
    }

    //获取网页参数
    (function($){
        $.getUrlParam = function(name){
            var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if (r!=null) return unescape(r[2]); return null;
        }
    })(jQuery);
</script>
</html>