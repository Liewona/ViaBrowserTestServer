package com.liuono.browser.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.liuono.browser.dao.RecordDao;

@WebServlet("/RecordServlet")
public class RecordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");

        RecordDao recordDao = new RecordDao();
        try {
            List list = recordDao.getData("");
            String result = JSON.toJSONString(list);
            resp.getWriter().print(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
