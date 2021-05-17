package com.board_class.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.article.model.ArticleService;
import com.article.model.ArticleVO;
import com.board_class.model.Board_ClassService;
import com.board_class.model.Board_ClassVO;

import redis.clients.jedis.Jedis;


@WebServlet("/board_class/board_class.do")
public class Board_ClassServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllBoard_Class.jsp���ШD
			System.out.println(req.getParameter("bd_cl_no"));
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				System.out.println("hi0");
				Integer bd_cl_no = new Integer(req.getParameter("bd_cl_no"));
				System.out.println("hi1");
				
				/***************************2.�}�l�d�߸��****************************************/
				Board_ClassService board_classSvc = new Board_ClassService();
				System.out.println("hi2");
				Board_ClassVO board_classVO = board_classSvc.getOneBoard_Class(bd_cl_no);
				System.out.println("hi3");
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("board_classVO", board_classVO);         // ��Ʈw���X��board_classVO����,�s�Jreq
				String url = "/back-end//board_class/update_board_class_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_board_class_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end//board_class/listAllBoard_class.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // �Ӧ�update_board_class_input.jsp���ШD
			System.out.println(req.getParameter("bd_cl_no"));
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/		
				Integer bd_cl_no = new Integer(req.getParameter("bd_cl_no").trim());
				
				String bd_name = req.getParameter("bd_name");
				String bd_nameReg = "^.{2,15}$";
				if (bd_name == null || bd_name.trim().length() == 0) {
					errorMsgs.add("�ݪO�W��: �ФŪť�");
				} 
				else if(!bd_name.trim().matches(bd_nameReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�ݪO�W��: ���ץ����b2��15����");
	            }
				
				Integer bd_stat = null;
				try {
					bd_stat = new Integer(req.getParameter("bd_stat").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("�峹���A�ж�Ʀr0 or 1.");
				} 
								
				Board_ClassVO board_classVO = new Board_ClassVO();
				board_classVO.setBd_cl_no(bd_cl_no);
				board_classVO.setBd_name(bd_name);
				board_classVO.setBd_stat(bd_stat);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("board_classVO",board_classVO); // �t����J�榡���~��board_classVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end//board_class/update_board_class_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				Board_ClassService board_classSvc = new Board_ClassService();
				board_classVO = board_classSvc.updateBoard_Class(bd_cl_no,bd_name,bd_stat);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("board_classVO", board_classVO); // ��Ʈwupdate���\��,���T����board_classVO����,�s�Jreq
				String url = "/back-end/board_class/listOneBoard_class.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneBoard_class.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/board_class/update_board_class_input.jsp");
				failureView.forward(req, res);
			}
		}

		
//        if ("insert".equals(action)) { // �Ӧ�addArticle.jsp���ШD  
//			
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
//				Integer bd_cl_no = null;
//				try {
//					bd_cl_no = new Integer(req.getParameter("bd_cl_no").trim());
//				} catch (NumberFormatException e) {
//					bd_cl_no = 0;
//					errorMsgs.add("�ݪO�s���ж�Ʀr.");
//				}
//				
//				Integer mbr_no = null;
//				try {
//					mbr_no = new Integer(req.getParameter("mbr_no").trim());
//				} catch (NumberFormatException e) {
//					mbr_no = 0;
//					errorMsgs.add("�|���s���ж�Ʀr.");
//				}
//				
//				String art_title = req.getParameter("art_title");
//				String art_titleReg = "^.{2,30}$";
//				if (art_title == null || art_title.trim().length() == 0) {
//					errorMsgs.add("�峹���D: �ФŪť�");
//				} 
//				else if(!art_title.trim().matches(art_titleReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
//					errorMsgs.add("�峹���D: ���ץ����b2��30����");
//	            }
//				
//	
//				Timestamp art_rel_time = new Timestamp(System.currentTimeMillis());
//				
//				String art_cont = req.getParameter("art_cont");
//				String art_contReg = "^.{10,10000}$";
//				
//				if (art_cont == null || art_cont.trim().length() == 0) {
//					errorMsgs.add("�峹���e: �ФŪť�");
//				} 
//				else if(!art_cont.trim().matches(art_contReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
//					errorMsgs.add("�峹���e: �����b10��10000�Ӧr����");
//	            }
//				
//				Integer likes = null;
//				try {
//					likes = new Integer(req.getParameter("likes").trim());
//				} catch (NumberFormatException e) {
//					likes = 0;
//					errorMsgs.add("�g�ƽж�Ʀr.");
//				}
//				
//				Integer art_stat = null;
//				try {
//					art_stat = new Integer(req.getParameter("art_stat").trim());
//				} catch (NumberFormatException e) {
//					art_stat = 0;
//					errorMsgs.add("�峹���A�ж�Ʀr0 or 1.");
//				}
//				
//
//				Board_ClassVO board_classVO = new Board_ClassVO();
//				board_classVO.setBd_cl_no(bd_cl_no);
//				board_classVO.setMbr_no(mbr_no);
//				board_classVO.setArt_rel_time(art_rel_time);
//				board_classVO.setArt_title(art_title);
//				board_classVO.setArt_cont(art_cont);
//				board_classVO.setLikes(likes);
//				board_classVO.setArt_stat(art_stat);
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("board_classVO", board_classVO); // �t����J�榡���~��board_classVO����,�]�s�Jreq
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/board_class/addArticle.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//				
//				/***************************2.�}�l�s�W���***************************************/
//				Board_ClassService board_classSvc = new Board_ClassService();
//				board_classVO = board_classSvc.addArticle(bd_cl_no, mbr_no,art_rel_time,art_title,art_cont, likes,art_stat);
//				
//				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
//				String url = "/board_class/listAllArticle.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllArticle.jsp
//				successView.forward(req, res);				
//				
//				/***************************��L�i�઺���~�B�z**********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/board_class/addArticle.jsp");
//				failureView.forward(req, res);
//			}
//		}
		
		
		if ("delete".equals(action)) { // �Ӧ�listAllBoard_Class.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				Integer bd_cl_no = new Integer(req.getParameter("bd_cl_no"));
				
				/***************************2.�}�l�R�����***************************************/
				Board_ClassService board_classSvc = new Board_ClassService();
				board_classSvc.deleteBoard_Class(bd_cl_no);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/back-end/board_class/listAllBoard_class.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/board_class/listAllBoard_class.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("bd_cl_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�ݪO�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/board_class/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				Integer bd_cl_no = null;
				try {
					bd_cl_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("�ݪO�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/board_class/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				Board_ClassService board_classSvc = new Board_ClassService();
				Board_ClassVO board_classVO = board_classSvc.getOneBoard_Class(bd_cl_no);
				if (board_classVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/board_class/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("board_classVO", board_classVO); // ��Ʈw���X��articleVO����,�s�Jreq
				String url = "/back-end/board_class/listOneBoard_class.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneArticle.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/article/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("subscribe".equals(action)) { // �Ӧ�update_board_class_input.jsp���ШD

				/***************************1.�����ШD�Ѽ� **********************/		
				String bd_cl_no = req.getParameter("bd_cl_no"); //�n�q�\���ݪO
				Integer mbr_no   = new Integer(req.getParameter("mbr_no").trim());   //�n�q�\���|��
								
				/***************************2.�}�l�ק���*****************************************/
				Jedis jedis = new Jedis("localhost", 6379);
				jedis.auth("123456");
				jedis.select(6);
				jedis.sadd("board:"+mbr_no+":subscribe", bd_cl_no);
				jedis.close();
			}
		
		
		
	}
}
