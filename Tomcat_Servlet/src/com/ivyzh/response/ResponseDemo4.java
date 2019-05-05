package com.ivyzh.response;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/response4")
public class ResponseDemo4 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("验证码");
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

        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(code.length() - 1);
            graphics.drawString(code.charAt(index)+"",20*(i+1),25);
        }
        // 将图片输出到页面展示
        ImageIO.write(image,"jpg",resp.getOutputStream());




    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
