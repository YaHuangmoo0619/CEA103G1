package com.lala.service;

/**
 * Created by 20901 on 2018/4/17.
 */

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lala.bean.User;
import com.lala.dao.UserDAO;

public class LoginServlet extends  HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }


    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        int id = Integer.parseInt(request.getParameter("id"));
        String password = request.getParameter("password");
        UserDAO userDAO = new UserDAO();
        User user = userDAO.login(id, password);

        if (user != null) {
            request.getRequestDispatcher("WEB-INF/pages/Lsuccess.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("WEB-INF/pages/Lerror.jsp").forward(request, response);
        }
    }
}