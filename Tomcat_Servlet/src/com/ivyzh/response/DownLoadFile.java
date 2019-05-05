package com.ivyzh.response;

import com.ivyzh.utils.DownLoadUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

/**
 * * 文件下载需求：
 * 	1. 页面显示超链接
 * 	2. 点击超链接后弹出下载提示框
 * 	3. 完成图片文件下载
 *
 *
 * 		2. 定义Servlet
 * 		1. 获取文件名称
 * 		2. 使用字节输入流加载文件进内存（ServletContext获取真实路径）
 * 		3. 指定response的响应头： content-disposition:attachment;filename=xxx
 * 		4. 将数据写出到response输出流
 */
@WebServlet("/download")
public class DownLoadFile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filename = req.getParameter("filename");
        String mimeType = this.getServletContext().getMimeType(filename);

        System.out.println(filename);

        // 获取真实路径
        String realPath = getServletContext().getRealPath("/imgs/" + filename);



        // 中文文件名问题
        resp.setHeader("content-type",mimeType);
        resp.setHeader("content-disposition","attachment;filename="+DownLoadUtils.getFileName(req.getHeader("User-Agent"),filename));

        FileInputStream fis = new FileInputStream(realPath);
        ServletOutputStream sos = resp.getOutputStream();
        byte[] buff = new byte[1024 * 4];
        int len  = 0;
        while ((len = fis.read(buff))!=-1){
            sos.write(buff,0,len);
        }
        fis.close();
        sos.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
