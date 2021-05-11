package com.lala.service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.lala.bean.User;
import com.lala.dao.UserDAO;

/**
 * Created by 20901 on 2018/4/18.
 */
@WebServlet(name = "RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        int role = Integer.parseInt(request.getParameter("role"));

        User u = new User();
        u.setId(id);
        u.setName(name);
        u.setPassword(password);
        u.setRole(role);

        UserDAO userdao = new UserDAO();
        boolean boo = userdao.addUser(u);

        if (boo == true) {
            request.getRequestDispatcher("WEB-INF/pages/Rsuccess.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("WEB-INF/pages/Rerror.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}