package com.example.controller;

import com.example.util.TwoDimensionCode;
import com.example.vo.LoginUserVo;
import com.example.vo.UserVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@Controller
@RequestMapping("/login")
public class LoginController {
    private static final String projectRoot = System.getProperty("user.dir");
    /**
     * 生成二维码图片以及uuid
     */
    @RequestMapping("GetQrCode")
    public void GetQrCode(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        //生成唯一ID
        int uuid = (int) (Math.random() * 100000);
        //二维码内容

        String content = "http://192.168.0.103:8080/QrCodeLogin/login.htm?uuid=" + uuid;
        //生成二维码
        String imgName =  uuid + "_" + (int) (new Date().getTime() / 1000) + ".png";
        String imgPath = projectRoot+ "\\src\\main\\resources\\static\\img\\"+imgName;
        System.out.println(imgPath);
        TwoDimensionCode handler = new TwoDimensionCode();
        handler.encoderQRCode(content, imgPath, "png");

        //生成的图片访问地址
        String qrCodeImg = "http://192.168.0.103:8080/QrCodeLogin/img/" + imgName;
        String jsonStr = "{\"uuid\":" + uuid + ",\"qrCodeImg\":\"" + qrCodeImg + "\"}";
        out.print(jsonStr);
        out.flush();
        out.close();
    }

    /**
     * 用长连接，检查登录状态
     */
    @RequestMapping("LongConnectionCheck")
    public void LongConnectionCheck(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uuid = request.getParameter("uuid");
        String jsonStr = "";
        System.out.println("uuid:" + uuid);
        long inTime = new Date().getTime();
        Boolean bool = true;
        while (bool) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //检测登录
            UserVo userVo = LoginUserVo.getLoginUserMap().get(uuid);
            System.out.println("userVo:" + userVo);
            if(userVo != null){
                bool = false;
                jsonStr = "{\"uname\":\""+userVo.getUname()+"\"}";
                LoginUserVo.getLoginUserMap().remove(uuid);
            }else{
                if(new Date().getTime() - inTime > 5000){
                    bool = false;
                }
            }
        }
        System.out.println("login ok : " + jsonStr);
        PrintWriter out = response.getWriter();
        out.print(jsonStr);
        out.flush();
        out.close();
    }

    /**
     * 二维码手机端登录
     */
    @RequestMapping("PhoneLogin")
    public void PhoneLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uuid = request.getParameter("uuid");
        String uname = request.getParameter("uname");
        String upwd = request.getParameter("upwd");
        System.out.println(uuid);
        System.out.println(uname);
        System.out.println(upwd);
        //TODO 验证登录
        boolean bool = true;
        if(bool){
            //将登陆信息存入map
            UserVo userVo = LoginUserVo.getLoginUserMap().get(uuid);
            if(userVo == null){
                userVo = new UserVo();
                userVo.setUname(uname);
                userVo.setUpwd(upwd);
                LoginUserVo.getLoginUserMap().put(uuid, userVo);
            }
        }
        PrintWriter out = response.getWriter();
        out.print(bool);
        out.flush();
        out.close();
    }
}
