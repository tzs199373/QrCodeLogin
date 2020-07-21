# 快速示范操作

1、进入LoginController将GetQrCode方法中，两处ip换成你电脑的ip，端口不动，不要是127.0.0.1，而是192.168.xx.xx。因为后续手机端扫码，是根据这个地址发请求的。

2、进入WebMvcConfig将你的img路径根据你的实际磁盘路径调整下，这个是存放二维码图片的位置

3、启动springboot，访问http://127.0.0.1:8080/QrCodeLogin/qrcode.htm

4、手机扫码，确保手机与电脑是同一wifi网段，手机上出现登录页面login.htm，点登录

5、PC端自动进入index.htm

# 实现流程

## pc端

1:打开二维码网页qrcode.htm

2:qrcode.htm调用GetQrCode

3:GetQrCode干2件事

　　a:生成随机的uuid,是一个唯一标识，该标识贯穿整个流程

　　b:生成二维码图片，二维码信息：http://xx.xx.xx.xx:8080/QrCodeLogin/login.htm?uuid=" + uuid

4:qrcode.htm页面展示二维码

5:qrcode.htm页面调用LongConnectionCheck进行长连接轮询操作，参数为uuid

6:LongConnectionCheck只干1件事

　　a:拿到uuid后循环检查loginUserMap中uuid是否不为null。

7:如果为null则代表没有登录，qrcode.htm将继续进行轮询

 

　　ps: LongConnectionCheckServlet 一个长连接请求检测登录状态

　　　　loginUserMap 是一个静态的map结构的登录池，uuid为key , 登录信息为value

## 手机端

1:扫描pc端的二维码

2:打开二维码中的网页 http://xx.xx.xx.xx:8080/QrCodeLogin/login.htm?uuid=" + uuid

3:登录，将uname upwd uuid 传递给登录程序PhoneLogin

4:PhoneLogin干2件事

　　a:检测登录

　　b:登录成功后将登录信息插入到loginUserMap中去，uuid为key

## pc端

　　1:继续轮询检测uuid中是否为null

　　2:登录后的uuid中就不为null了，此时LongConnectionCheck停止循环，返回登录状态。
