package com.follow.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.article.model.ArticleService;
import com.article.model.ArticleVO;
import com.follow.model.FollowService;
import com.follow.model.FollowVO;
import com.member.model.MemberService;
import com.member.model.MemberVO;

import redis.clients.jedis.Jedis;


@WebServlet("/follow/follow.do")

public class FollowServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
	
		if ("getbyflwed_no".equals(action)) { // �Ӧ�select_page.jsp���ШD�A�C�X�o�ӤH�����ǯ���

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("flwed_mbr_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�|���s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/follow/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				

				Integer flwed_mbr_no = null;
				try {
					flwed_mbr_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("�|���s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/follow/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				FollowService followSvc = new FollowService();
				List<FollowVO> followVO = followSvc.findbyflwed(flwed_mbr_no);

				if (followVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/follow/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("followVO", followVO); // ��Ʈw���X��followVO����,�s�Jreq
				String url = "/back-end/follow/listOneByFlwed.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneByFlwed.jsp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/follow/select_page.jsp");
				failureView.forward(req, res);
			}
		}		
	
	
		
		
		if ("getbyflw_no".equals(action)) { // �Ӧ�select_page.jsp���ШD�A�C�X�o�ӤH�O���ǤH������

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("flw_mbr_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�|���s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/follow/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				

				Integer flw_mbr_no = null;
				try {
					flw_mbr_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("�|���s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/follow/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				System.out.println(flw_mbr_no);			
				/***************************2.�}�l�d�߸��*****************************************/
				FollowService followSvc = new FollowService();
				List<FollowVO> followVO = followSvc.findbyflw(flw_mbr_no);
				if (followVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/follow/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("followVO", followVO); // ��Ʈw���X��followVO����,�s�Jreq
				String url = "/back-end/follow/listOneByFlw.jsp";
				System.out.println("hello");
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneByFlw.jsp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/follow/select_page.jsp");
				failureView.forward(req, res);
			}
		}
	
		
		
		
		
		
		if ("getProfile".equals(action)) { 
			// �Ӧ�listOneArticle���ШD�A�C�X�o�ӤH���H�U��T
			//1. ���h�֯����A���ǯ����O��  2. �l�ܦh�֤H�A�l�ܪ��H�O���ǤH  3.�K��� 4.�ӷ|���P�ӵo��̪������B�l�ܪ̪����Y


			//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				
				String str = req.getParameter("mbr_no");
				Integer mbr_no = new Integer(str);
				System.out.println("mbr_no:"+mbr_no);
				
				/*
				 �q�Y�g�峹�I�o���
				�ǻ��|����Mbr_no�i�JServlet��
				�q�ӷ|����Mbr_no���o�ӷ|�����l�ܪ��H
				�åB��o��̪������B�l�ܪ̶i�����Y���A�s����ӼƦr��list
				0�N��S�l�ܹ�� 1:�N���l�ܹ��
				�blistOneProfile��for�j�餤
				�p�G�O0  �N��ܡu�l�ܡv
				�p�G�O1  �N��ܡu�l�ܤ��v*/
				
//				HttpSession session = req.getSession();  //for Session
//				String str2 = (String)session.getAttribute("mbr_no");
//				Integer mbr_no_self = new Integer(str2);
				String mbr_no_mine = req.getParameter("mbr_no_mine");
				Integer mbr_no_self = new Integer(mbr_no_mine);
				/***************************2.�}�l�d�߸��*****************************************/
				
				ArticleService articleSvc = new ArticleService();
				List<ArticleVO> articleVO = articleSvc.getByMbr_no(mbr_no); //���Y�ӤH�o���Ҧ��峹
				int article_num = articleVO.size();//�Y�H�o���峹�ƶq
				
				FollowService followSvc = new FollowService();
				List<FollowVO> followVO_fans = followSvc.findbyflwed(mbr_no); //���Y�ӤH�����ǯ���
				int fans_num = followVO_fans.size(); //�����ƶq���h�֤H
				System.out.println("fans_num:"+fans_num);
				List<FollowVO> followVO_follows = followSvc.findbyflw(mbr_no); //���Y�ӤH�l�ܭ��ǤH
				int follows_num = followVO_follows.size(); //�@�l�ܦh�֤H
				System.out.println("follows_num:"+follows_num);
				
				
				List<FollowVO> followVO_mine = followSvc.findbyflw(mbr_no_self); //���o�ڰl�ܨ��ǤH��VO
				List<Integer>  followVO_mine1 = new ArrayList<Integer>(); //VO�ন�¼Ʀr��arrayList�x�s  ��Jreq
			    for(FollowVO element : followVO_mine) {
			        followVO_mine1.add(element.getFlwed_mbr_no());
			    }
			    
				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/follow/select_page.jsp");
//					failureView.forward(req, res);
//					return;//�{�����_
//				}
				
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("articleVO", articleVO);      //�Y�H�o���峹
				req.setAttribute("article_num",article_num);   //�o���峹�ƶq
				
				req.setAttribute("followVO_fans", followVO_fans); //�Y�H������
				req.setAttribute("fans_num",fans_num);            //�����ƶq
				
				req.setAttribute("followVO_follows", followVO_follows); //�Y�H���l�ܪ�
				req.setAttribute("follows_num",follows_num); //�l�ܼƶq
				
				req.setAttribute("followVO_mine", followVO_mine1); //�ڰl�ܪ��H
				
				req.setAttribute("mbr_no",mbr_no);
				String url = "/front-end/follow/listOneProfile.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneProfile.jsp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("�L�k���o���:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/follow/select_page.jsp");
//				failureView.forward(req, res);
//			}
		}
		
		
		if ("getFollowers".equals(action)) { 
			// �Ӧ�listOneProfile���ШD�A�C�X�o�ӤH���H�U��T 1. ���h�֯����A���ǯ����O��
			

				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("mbr_no");
				Integer mbr_no = new Integer(str);
				System.out.println("mbr_no:"+mbr_no);
				
				/***************************2.�}�l�d�߸��*****************************************/
         		FollowService followSvc = new FollowService();
				List<FollowVO> followVO_fans = followSvc.findbyflwed(mbr_no); //���Y�ӤH�����ǯ���
				int fans_num = followVO_fans.size(); //�����ƶq���h�֤H
				System.out.println("fans_num:"+fans_num);
				
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/			
				req.setAttribute("followVO_fans", followVO_fans); //�Y�H������
				req.setAttribute("fans_num",fans_num);            //�����ƶq

				req.setAttribute("mbr_no",mbr_no);
				String url = "/front-end/follow/listOneFollowers.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneProfile.jsp.jsp
				successView.forward(req, res);
		}
		
		
		
		
		
		if ("getFollowing".equals(action)) { 
			// �Ӧ�listOneProfile���ШD�A�C�X�o�ӤH���H�U��T 1.�l�ܦh�֤H�A�l�ܪ��H�O���ǤH

				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("mbr_no");
				Integer mbr_no = new Integer(str);
				System.out.println("mbr_no:"+mbr_no);
				
				/***************************2.�}�l�d�߸��*****************************************/
				FollowService followSvc = new FollowService();

				List<FollowVO> followVO_follows = followSvc.findbyflw(mbr_no); //���Y�ӤH�l�ܭ��ǤH
				int follows_num = followVO_follows.size(); //�@�l�ܦh�֤H

				
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				
				req.setAttribute("followVO_follows", followVO_follows); //�Y�H���l�ܪ�
				req.setAttribute("follows_num",follows_num); //�l�ܼƶq
				
				req.setAttribute("mbr_no",mbr_no);
				String url = "/front-end/follow/listOneFollowing.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneProfile.jsp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/

		}
	
		
		
		if ("add_follow".equals(action)) { // �s�W�@���q�\���Y
				
				String str = new String(req.getParameter("flwed_mbr_no"));
				Integer flwed_mbr_no = new Integer(str);
				System.out.println("flwed_mbr_no:"+flwed_mbr_no);
				String str2 = new String(req.getParameter("flw_mbr_no"));
				Integer flw_mbr_no = new Integer(str2);
				System.out.println("flw_mbr_no1:"+flw_mbr_no);
				FollowVO followVO = new FollowVO();
				followVO.setFlwed_mbr_no(flwed_mbr_no);
				followVO.setFlw_mbr_no(flw_mbr_no);
				/***************************2.�}�l�s�W���*****************************************/
				FollowService followSvc = new FollowService();
				followVO = followSvc.addFollow(flwed_mbr_no, flw_mbr_no);

		}
		
		
		if ("add_follow1".equals(action)) { // �s�W�@���q�\���Y
			
			String str = new String(req.getParameter("flwed_mbr_no")); //�ثe���o�O�b��: lavida
			MemberService memberSvc = new MemberService();
			List<MemberVO> memberVO_all = memberSvc.getAll(); //�������|��
			int flwed_mbr_no=0 ; //�Q�l�ܪ��H�����X
			
			for(MemberVO vo_count : memberVO_all) {
			if(vo_count.getAcc()==str) {
				flwed_mbr_no=vo_count.getMbr_no();
				}	
			}
			
			MemberVO memberVO = new MemberVO();
			memberVO = memberSvc.getOneMember(flwed_mbr_no);
			
			
			System.out.println("flwed_mbr_no:"+flwed_mbr_no);
			
			
			String str2 = new String(req.getParameter("flw_mbr_no"));
			Integer flw_mbr_no = new Integer(str2);
			System.out.println("flw_mbr_no:"+flw_mbr_no);
			FollowVO followVO = new FollowVO();
			followVO.setFlwed_mbr_no(flwed_mbr_no);
			followVO.setFlw_mbr_no(flw_mbr_no);
			/***************************2.�}�l�s�W���*****************************************/
			FollowService followSvc = new FollowService();
			followVO = followSvc.addFollow(flwed_mbr_no, flw_mbr_no);

	}
		
		
		if ("cancel_follow".equals(action)) { // �s�W�@���q�\���Y
			
			String str = new String(req.getParameter("flwed_mbr_no"));
			Integer flwed_mbr_no = new Integer(str);
			System.out.println("flwed_mbr_no:"+flwed_mbr_no);
			String str2 = new String(req.getParameter("flw_mbr_no"));
			Integer flw_mbr_no = new Integer(str2);
			System.out.println("flw_mbr_no:"+flw_mbr_no);
			/***************************2.�}�l�s�W���*****************************************/
			FollowService followSvc = new FollowService();
			followSvc.deleteFollow(flwed_mbr_no, flw_mbr_no);

	}
		
		
		
		if ("modify_profile".equals(action)) { 
				

				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String mbr_no = req.getParameter("mbr_no");
				System.out.println("mbr_no:"+mbr_no);
				String new_profile = req.getParameter("new_profile");
				
				System.out.println("new_profile:"+new_profile);
				
				/***************************2.�}�l�s�W���*****************************************/
				Jedis jedis = new Jedis("localhost", 6379);
				jedis.auth("123456");
				jedis.select(6);
				jedis.set("user:"+mbr_no+":profile", new_profile);
				jedis.close();// Redis�s�W����				
				

		}
		
		
	}	
	
}
