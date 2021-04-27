package com.employee.model;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@WebServlet("/employee/GetRandomCode")
public class GetRandomCode extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		    String to = "klfm1224@hotmail.com.tw";
		      
		    String subject = "�K�X�q��";
		      
		    String pwd = genAuthCode();
		    String messageText = " ���԰O���K�X: " + pwd + "\n" +" (�w�g�ҥ�)";
		    
		    sendMail(to, subject, messageText);
		    	    
		    req.setAttribute("pwd", pwd);
		    res.sendRedirect(req.getContextPath() + "/back-end/employee/addEmployee.jsp");

	}

	public static String genAuthCode() {
//		�C�X�n�h�����Ÿ��A�Ÿ��������Ʀr�Ӧ�Unicode��A�NUnicode��16�i��Ʀr��10�i�줧��A�ϥ�
		int[] check = { 58, 59, 60, 61, 62, 63, 64, 91, 92, 93, 94, 95, 96 };
//		�إ߭n��J�ýX���r���}�C
		char[] code = new char[8];
//		���o�üơA���ۤ��O�_���n�׶}���Ÿ��A�Ҥ��O���ܦA��J�}�C�A�O���ܴN�����@���üơA�æA�����A���ưʧ@����}�C�񺡬���
		for (int i = 0; i < 8; i++) {
			int num = (int) ((Math.random() * 75) + 48);
			for (int j = 0; j < check.length; j++) {
				if (num == check[j]) {
					num = (int) ((Math.random() * 75) + 48);
					j = -1;
				}
			}
			code[i] = (char) num;
		}
//		�ˬd�O�_�P�ɾ֦��^��r���j�p�g�μƦr�A�S�������H����@�Ӧ�m����
//		�ˬd�Ʀr
		int countNum = 0;
		out: for (int i = 0; i < 8; i++) {
			for (int j = 48; j < 58; j++) {
				if ((int) code[i] != j) {
					countNum += 1;
				} else {
					break out;
				}
			}

		}
		int a1 = (int) ((Math.random() * 8));
		if (countNum == 80) {
			code[a1] = (char) ((int) ((Math.random() * 10) + 48));
		}
//		�ˬd�^��r���j�g
		int countUpper = 0;
		out: for (int i = 0; i < 8; i++) {
			for (int j = 65; j < 91; j++) {
				if ((int) code[i] != j) {
					countUpper += 1;
				} else {
					break out;
				}
			}
		}
//		�קK��W�����P�˪���m
		int a2 = (int) ((Math.random() * 8));
		while (true) {
			if (a2 == a1) {
				a2 = (int) ((Math.random() * 8));
			} else {
				break;
			}
		}
		if (countUpper == 208) {
			code[a2] = (char) ((int) ((Math.random() * 26) + 65));
		}
//		�ˬd�^��p�g�r��
		int countLower = 0;
		out: for (int i = 0; i < 8; i++) {
			for (int j = 97; j < 123; j++) {
				if ((int) code[i] != j) {
					countLower += 1;
				} else {
					break out;
				}
			}
		}
//		�קK��W�����P�˪���m
		int a3 = (int) ((Math.random() * 8));
		while (true) {
			if (a3 == a1 || a3 == a2) {
				a3 = (int) ((Math.random() * 8));
			} else {
				break;
			}
		}
		if (countLower == 208) {
			code[a3] = (char) ((int) ((Math.random() * 26) + 97));
		}
//		��String���󤤪�valueOf(char[])��k�A�N�r���}�C�ର�r��æ^��
		return String.valueOf(code);

	}

	// �]�w�ǰe�l��:�ܦ��H�H��Email�H�c,Email�D��,Email���e

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
			final String myGmail = "ixlogic.wu@gmail.com";
			final String myGmail_password = "CCC45678CCC";
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

}
