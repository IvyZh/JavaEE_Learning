package com.ivyzh.province.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ivyzh.province.domain.Province;
import com.ivyzh.province.service.impl.ProvinceServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 获取全国省的Servlet
 */
@WebServlet("/getProvinceServlet")
public class GetProvinceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("getProviceServlet.......");
        resp.setCharacterEncoding("utf-8");
        // 设置json格式
        resp.setContentType("application/json;charset=utf-8");

        ProvinceServiceImpl provinceService = new ProvinceServiceImpl();
        List<Province> allProvinces = provinceService.getAllProvinces();
        // 将list转成json
        ObjectMapper mapper = new ObjectMapper();
        String data = mapper.writeValueAsString(allProvinces);
        System.out.println(data);

        resp.getWriter().write(data);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
