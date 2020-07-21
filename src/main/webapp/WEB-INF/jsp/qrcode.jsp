<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<div id="divCon">
    <img src="" id="QrCodeImg" />
</div>
</body>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        var uuid;
        $.get("/QrCodeLogin/login/GetQrCode", function(data) {
            var obj = eval("(" + data + ")");
            //存储UUID
            uuid = obj.uuid;
            //显示二维码
            $("#QrCodeImg").attr("src", obj.qrCodeImg);
            //开始验证登录
            validateLogin();
        });

        function validateLogin(){
            $.get("/QrCodeLogin/login/LongConnectionCheck?uuid=" + uuid , function(data) {
                if(data == ""){
                    validateLogin();
                }else{
                    var obj = eval("(" + data + ")");
                    window.location.href = "/QrCodeLogin/index.htm?uname="+ obj.uname;
                }
            });
        }
    });
</script>
</html>