package com.lala.dispatcherServlet;

import java.io.IOException;

/**
 * Created by 20901 on 2018/4/17.
 */
public class LoginPageServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request, response);
}

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String url=request.getParameter("url");
        request.getRequestDispatcher(url).forward(request, response);
    }
}