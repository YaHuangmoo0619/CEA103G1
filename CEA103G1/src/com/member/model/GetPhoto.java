package com.member.model;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import com.announcement.model.AnnouncementService;
import com.announcement.model.AnnouncementVO;



@WebServlet("/member/GetPhoto")
@MultipartConfig
public class GetPhoto extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	Connection con;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		
		try {
			Statement stmt = con.createStatement();
			String mbr_no = req.getParameter("mbr_no").trim();
			ResultSet rs = stmt.executeQuery(
					"SELECT sticker FROM campion.member WHERE mbr_no = " + mbr_no);
			
			if(rs.next()) {
				BufferedInputStream bInput= new BufferedInputStream(rs.getBinaryStream("sticker"));
				byte[] buf = new byte[4*1024];
				int len;
				while((len = bInput.read(buf)) != -1) {
					out.write(buf,0,len);
				}
				bInput.close();
			}else {
				InputStream in = getServletContext().getResourceAsStream("/images/campionLogoLong.png");
				byte[] b = new byte[in.available()];
				in.read(b);
				out.write(b);
				in.close();
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
//			System.out.print(e.getMessage());
			InputStream in = getServletContext().getResourceAsStream("/images/campionLogoCircle.png");
			byte[] b = new byte[in.available()];
			in.read(b);
			out.write(b);
			in.close();
		}
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("updatePic".equals(action)) { // �Ӧ�update_announcement_input.jsp���ШD
				
			try {
				Integer mbr_no = new Integer(req.getParameter("mbr_no"));
				Part pic = req.getPart("sticker");
				InputStream in = pic.getInputStream();
				byte[] stickerNew = new byte[in.available()];
				in.read(stickerNew);
									
				MemberService memberSvc = new MemberService();
				MemberVO memberVOOld = memberSvc.getOneMember(mbr_no);
				Integer rank_no = memberVOOld.getRank_no();
				String acc = memberVOOld.getAcc();
				String pwd = memberVOOld.getPwd();
				String name = memberVOOld.getName();
				Date bday = memberVOOld.getBday();
				Integer sex = memberVOOld.getSex();
				String id = memberVOOld.getId();
				String mobile = memberVOOld.getMobile();
				String mail = memberVOOld.getMail();
				String city = memberVOOld.getCity();
				String dist = memberVOOld.getDist();
				String add = memberVOOld.getAdd();
				Timestamp join_time = memberVOOld.getJoin_time();
				String card = memberVOOld.getCard();
				Integer pt = memberVOOld.getPt();
				Integer acc_stat = memberVOOld.getAcc_stat();
				Integer exp = memberVOOld.getExp();
				byte[] sticker = stickerNew;
				String rmk = memberVOOld.getRmk();
				/***************************2.�}�l�ק���*****************************************/
				MemberVO memberVO = memberSvc.updateMember(mbr_no, rank_no, acc, pwd, id, name, bday, sex, mobile, mail, city, dist, add, join_time, card, pt, acc_stat, exp, sticker, rmk);
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("memberVO", memberVO); // ��Ʈwupdate���\��,���T����announcementVO����,�s�Jreq
				String url = "/front-end/member/viewMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneAnnouncement.jsp
				successView.forward(req, res);
		
				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				System.out.println("faile");
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/member/viewMember.jsp");
				failureView.forward(req, res);
			}
		}
	}


	public void init() throws ServletException{
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Campion");
			con = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void destroy() {		
		try {
			if(con != null)	con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}