package com.campsite_owner.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.campsite_owner.model.Campsite_ownerService;
import com.campsite_owner.model.Campsite_ownerVO;
import com.member.model.MemberService;
import com.member.model.MemberVO;

@MultipartConfig
@WebServlet("/campsite_owner/campsite_owner.do")
public class Campsite_ownerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String str = req.getParameter("cso_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�s�i�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/campsite_owner/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				Integer cso_no = null;
				try {
					cso_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("�s�i�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/campsite_owner/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				Campsite_ownerService campsite_ownerSvc = new Campsite_ownerService();
				Campsite_ownerVO campsite_ownerVO = campsite_ownerSvc.getOneCampsite_owner(cso_no);
				if (campsite_ownerVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/campsite_owner/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("campsite_ownerVO", campsite_ownerVO); // ��Ʈw���X��member_rankVO����,�s�Jreq
				String url = "/front-end/campsite_owner/listOnecampsite_owner.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOnecampsite_owner.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/campsite_owner/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllcampsite_owner.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				Integer cso_no = new Integer(req.getParameter("cso_no"));

				/*************************** 2.�}�l�d�߸�� ****************************************/
				Campsite_ownerService campsite_ownerSvc = new Campsite_ownerService();
				Campsite_ownerVO campsite_ownerVO = campsite_ownerSvc.getOneCampsite_owner(cso_no);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("campsite_ownerVO", campsite_ownerVO); // ��Ʈw���X��campsite_ownerVO����,�s�Jreq
				String url = "/front-end/campsite_owner/update_campsite_owner_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_campsite_owner_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/campsite_owner/listAllcampsite_owner.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // �Ӧ�addMember_rank.jsp���ШD
			HttpSession session = req.getSession();
			
			Part part = req.getPart("sticker");
			InputStream in = part.getInputStream();
			byte[] sticker = new byte[in.available()];
			in.read(sticker);
			in.close();
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
				String acc = req.getParameter("acc");
				Campsite_ownerService campsite_ownerSvc = new Campsite_ownerService();
				List<Campsite_ownerVO> csolist = campsite_ownerSvc.getAll();
				if (acc == null || acc.trim().length() == 0) {
					errorMsgs.add("�b���ФŪť�");
				}
				for (Campsite_ownerVO csoVO : csolist) {
					if (acc.equals(csoVO.getAcc())) {
						errorMsgs.add("���b���w���H�ϥ�");
					}
				}
				String pwd = req.getParameter("pwd");
				if (pwd == null || pwd.trim().length() == 0) {
					errorMsgs.add("�K�X�ФŪť�");
				}
				String id = req.getParameter("id");
				if (id == null || id.trim().length() == 0) {
					errorMsgs.add("������:�ФŪť�");
				}
				if (!checkId(id)) {
					errorMsgs.add("������:�榡���~");
				}
				String name = req.getParameter("name");
				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,60}$";
				if (name == null || name.trim().length() == 0) {
					errorMsgs.add("�m�W: �ФŪť�");
				} else if (!name.trim().matches(nameReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�m�W: �u��O���B�^��letter");
				}

				java.sql.Date bday = java.sql.Date.valueOf(req.getParameter("bday").trim());
				int sex = new Integer(req.getParameter("sex"));
				int mobile = new Integer(req.getParameter("mobile"));
				String mail = req.getParameter("mail");
				if (mail == null || mail.trim().length() == 0) {
					errorMsgs.add("�H�c: �ФŪť�");
				}
				String city = req.getParameter("county");
				String dist = req.getParameter("district");
				String add = req.getParameter("address");
				if (add == null || add.trim().length() == 0) {
					errorMsgs.add("�a�}: �ФŪť�");
				}

				int bank_no = new Integer(req.getParameter("bank_no"));
				String bank_acc = req.getParameter("bank_acc");
				Campsite_ownerVO campsite_ownerVO = new Campsite_ownerVO();
				campsite_ownerVO.setAcc(acc);
				campsite_ownerVO.setPwd(pwd);
				campsite_ownerVO.setId(id);
				campsite_ownerVO.setName(name);
				campsite_ownerVO.setBday(bday);
				campsite_ownerVO.setSex(sex);
				campsite_ownerVO.setMobile(mobile);
				campsite_ownerVO.setMail(mail);
				campsite_ownerVO.setCity(city);
				campsite_ownerVO.setDist(dist);
				campsite_ownerVO.setAdd(add);
				campsite_ownerVO.setBank_no(bank_no);
				campsite_ownerVO.setBank_acc(bank_acc);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("campsite_ownerVO", campsite_ownerVO); // �t����J�榡���~��member_rankVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/campsite_owner/addCampsite_owner.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.�}�l�s�W��� ***************************************/
				campsite_ownerVO = campsite_ownerSvc.addCampsite_owner(acc, pwd, id, name, bday, sex, mobile, mail,
						city, dist, add, sticker, bank_no, bank_acc);
				mail(req, campsite_ownerVO);
				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				session.setAttribute("enable","�нT�{�H�c�O�_����ҥΫH");
				String url = "/front-end/campsite_owner/login.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllMember_rank.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/campsite_owner/addCampsite_owner.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // �Ӧ�listAllMember_rank.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ***************************************/
				Integer cso_no = new Integer(req.getParameter("cso_no"));

				/*************************** 2.�}�l�R����� ***************************************/
				Campsite_ownerService campsite_ownerSvc = new Campsite_ownerService();
				campsite_ownerSvc.deleteCampsite_owner(cso_no);

				/*************************** 3.�R������,�ǳ����(Send the Success view) ***********/
				String url = "/front-end/campsite_owner/listAllcampsite_owner.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/campsite_owner/listAllcampsite_owner.jsp");
				failureView.forward(req, res);
			}
		}
		if ("login".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String acc = req.getParameter("acc");
				if (acc == null || (acc.trim()).length() == 0) {
					errorMsgs.add("�п�J�b��");
				}

				Campsite_ownerService campsite_ownerSvc = new Campsite_ownerService();
				Campsite_ownerVO campsite_ownerVO = null;
				List<Campsite_ownerVO> csolist = campsite_ownerSvc.getAll();
				for (Campsite_ownerVO csoVO : csolist) {
					if (acc.equals(csoVO.getAcc())) {
						campsite_ownerVO = csoVO;
					}
				}
				if (campsite_ownerVO == null) {
					errorMsgs.add("���b���L��");
				} else {
					String pwd = req.getParameter("pwd");
					if (pwd == null || (pwd.trim()).length() == 0) {
						errorMsgs.add("�п�J�K�X");
					}
					if (pwd.equals(campsite_ownerVO.getPwd())) {
						HttpSession session = req.getSession();
						session.setAttribute("campsite_ownerVO", campsite_ownerVO);
						String url = "/campion_campsiteOwner.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� campion_front.jsp
						successView.forward(req, res);
					} else {
						errorMsgs.add("�K�X���~");
					}
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/campsite_owner/login.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/campsite_owner/login.jsp");
				failureView.forward(req, res);
			}
		}
	}
	
	public void mail (HttpServletRequest req, Campsite_ownerVO campsite_ownerVO){

	      String to = campsite_ownerVO.getMail();
	      
	      String subject = "Campion��D�ҥ�";
	      String messageText = "http://localhost:8081/" + req.getContextPath() + "/campion_campsiteOwner.jsp?cso_no=" + campsite_ownerVO.getCso_no(); 
	       
	      Campsite_ownerServlet mailService = new Campsite_ownerServlet();
	      mailService.sendMail(to, subject, messageText);

	}
	
	public void sendMail(String to, String subject, String messageText) {
		try {
			// �]�w�ϥ�SSL�s�u�� Gmail smtp Server
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");

			// ���]�w gmail ���b�� & �K�X (�N�ǥѧA��Gmail�ӶǰeEmail)
			// �����NmyGmail���i�w���ʸ��C�����ε{���s���v�j���}
			final String myGmail = "campion20210219@gmail.com";
			final String myGmail_password = "campion0219";
			Session session = Session.getInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(myGmail, myGmail_password);
				}
			});

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myGmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			// �]�w�H�����D��
			message.setSubject(subject);
			// �]�w�H�������e
			message.setText(messageText);

			Transport.send(message);
			System.out.println("�ǰe���\!");
		} catch (MessagingException e) {
			System.out.println("�ǰe����!");
			e.printStackTrace();
		}
	}

	public Boolean checkId(String id) {
		Boolean checkid = true;
		int[] reference = { 10, 11, 12, 13, 14, 15, 16, 17, 34, 18, 19, 20, 21, 22, 35, 23, 24, 25, 26, 27, 28, 29, 30,
				31, 32, 33, 34 };
		int length = id.length();
		id = id.toUpperCase();
		if (!Character.isLetter(id.charAt(0))) {
			checkid = false;
		}
		if (length != 10) {
			checkid = false;
		}
		if (id.charAt(1) != '1' && id.charAt(1) != '2') {
			checkid = false;
		}
		char num;
		int sum = 0, number, letter;
		letter = reference[id.charAt(0) - 65];
		sum = (letter / 10) + ((letter % 10) * 9);

		for (int i = 1; i <= length - 2; ++i) {
			num = id.charAt(i);
			number = num - 48;
			sum += number * (9 - i);
		}

		num = id.charAt(9);
		number = num - 48;

		if (10 != (sum % 10) + number) {
			checkid = false;
		}
		return checkid;
	}

}
