package com.place_order.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.campsite.model.*;
import com.place.model.*;
@WebServlet("/test.do")
public class test extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
    	PlaceService abc = new PlaceService();
    	List<PlaceVO> placelist = abc.getAll();
    	List<Object> list = new ArrayList();  	
    	CampService camp = new CampService();
    	List<CampVO> camplist = camp.getAll(); 
    	list.addAll(camplist);
    	list.addAll(placelist);
    }
}
