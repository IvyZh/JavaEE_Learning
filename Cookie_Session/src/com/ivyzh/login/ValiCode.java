package com.ivyzh.login;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/valicode")
public class ValiCode extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("生成验证码");
        resp.setContentType("image/jpg;charset=utf-8");
        int w = 100;
        int h = 30;
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        graphics.setColor(Color.PINK);
        graphics.fillRect(0,0,w,h);

        graphics.setColor(Color.BLUE);
        graphics.drawRect(0,0,w-1,h-1);

        // 写验证码
        String code = "ABCDEFKJHK1234354563JGJGKJUIsasjdjhgsajdhgw";
        Random random = new Random();

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(code.length() - 1);
            String c = code.charAt(index) + "";
            sb.append(c);
            graphics.drawString(c,20*(i+1),25);
        }
//        req.setAttribute("valicode",sb.toString());
//        this.getServletContext().setAttribute("valicode",sb.toString());

        // 注意： 1、req.setAttribute 适用于转发，代表一次请求的范围，一般用于请求转发的多个资源中共享数据 所以在LoginServlet中获取值为null
        // 2、this.getServletContext().setAttribute("valicode",sb.toString()); 代表整个项目，不建议使用，
        // 所以这里只能用cookie或session存储,sesion操作起来方便，这里就用session来做
        req.getSession().setAttribute("valicode",sb.toString());
        System.out.println("valicode:"+sb.toString());


        // 将图片输出到页面展示
        ImageIO.write(image,"jpg",resp.getOutputStream());


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
