package com.lala.dao;

/**
 * Created by 20901 on 2018/4/17.
 */

import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;


import com.lala.bean.User;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;




public class UserDAO {
    public static final String DBurl = "jdbc:mysql://127.0.0.1/test?characterEncoding=utf8&useSSL=true";
    public static final String DBname = "com.mysql.jdbc.Driver";
    public static final String DBuser = "root";
    public static final String DBpassword = "root";


    public  User  login(int id,String password) {

        User u = null;
        Connection connection =null;
        PreparedStatement pstmt=null;
        ResultSet resultSet=null;

        //���
        try {
            try {
                Class.forName(DBname);//���w�s�u���O
                connection = (Connection)DriverManager.getConnection(DBurl, DBuser, DBpassword);//����s�u
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            //�R�Asql�y�y
            String sql = "select * from user where id=? and password=?";
            pstmt = (PreparedStatement) connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setString(2, password);
            resultSet = pstmt.executeQuery();

            if(resultSet.next()){
                u=new User();
                u.setId(resultSet.getInt("id"));
                u.setName(resultSet.getString("name"));
                u.setPassword(resultSet.getString("password"));
                u.setRole(resultSet.getInt("role"));
                System.out.println("�n�J���\�I");
            }else{
                System.out.println("�ϥΪ̦W�٩Ϊ̱K�X���~�I");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(pstmt !=null){
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            if(connection !=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }
        return u;

    }
    public boolean addUser(User u) {
        Connection connection = null;
        PreparedStatement psmt = null;
        boolean boo = false;

        try {
            try {
                Class.forName(DBname);//���w�s�u���O
                connection = (Connection)DriverManager.getConnection(DBurl, DBuser, DBpassword);//����s�u
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            String sql  ="insert into user(id,name,password,role)values(?,?,?,?);";

            psmt = (PreparedStatement) connection.prepareStatement(sql);

            //�B�ι��骫��i��޼ƽ��
            psmt.setInt(1, u.getId());
            psmt.setString(2, u.getName());
            psmt.setString(3,u.getPassword());
            psmt.setInt(4, u.getRole());

            int result = psmt.executeUpdate();
            if(result > 0){
                boo = true;
                System.out.println("���J��Ʀ��\");
            }else{
                System.out.println("�����J���");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            try {
                connection.close();
                psmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return boo;
    }
}