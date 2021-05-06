package com.helloweenvsfei.petstore.web.action;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.helloweenvsfei.petstore.entity.UserEO;
import com.helloweenvsfei.petstore.util.BOClient;
import com.helloweenvsfei.petstore.web.util.StringUtil;

public class UserAction extends BaseAction {

	private static final long serialVersionUID = 2683928407831291840L;

	public static final String REGISTER = "register";

	public static final String LOGOUT = "logout";

	private UserEO userEO;

	private String confirmPassword;

	public String execute() {
		if (REGISTER.equals(action))
			return register();
		if (LOGIN.equals(action))
			return login();
		if (LOGOUT.equals(action))
			return logout();
		return login();
	}

	public String register() {

		setTitle("�ϥΪ̵��U");

		if (userEO == null) {
			return REGISTER;
		}
		if (StringUtil.isNull(userEO.getLogin())
				|| StringUtil.isNull(userEO.getPassword())) {
			setMessage("�ж�g���U��T");
			return REGISTER;
		}
		if (!userEO.getPassword().equals(confirmPassword)) {
			setMessage("�K�X���@�P");
			return REGISTER;
		}

		try {
			userEO = BOClient.lookupIUser().createUser(userEO);

			HttpSession session = ServletActionContext.getRequest().getSession(
					true);

			session.setAttribute("userEO", userEO);

			setMessage("���U���\");

			return SUCCESS;

		} catch (Exception e) {
			setMessage("�o�Ϳ��~�G" + e.getMessage());
		}

		return REGISTER;
	}

	public String login() {

		setTitle("�ϥΪ̵n�J");

		if (userEO == null)
			return LOGIN;
		if (StringUtil.isNull(userEO.getLogin())
				|| StringUtil.isNull(userEO.getPassword())) {
			setMessage("�п�J�㸹�K�X");
			return LOGIN;
		}

		try {
			UserEO user = BOClient.lookupIUser().findUser(userEO.getLogin(),
					userEO.getPassword());

			if (user == null) {
				setMessage("�ϥΪ̦W�ٱK�X���~");
				return LOGIN;
			} else {
				HttpSession session = ServletActionContext.getRequest()
						.getSession(true);
				session.setAttribute("userEO", user);
				setMessage("�w��^�ӡA" + user.getLogin());
				return SUCCESS;
			}

		} catch (Exception e) {
			setMessage("�o�Ϳ��~�G" + e.getMessage());
		}

		return LOGIN;
	}

	public String logout() {

		HttpSession session = ServletActionContext.getRequest()
				.getSession(true);
		session.setAttribute("userEO", null);

		setMessage("���P���\");

		return login();
	}

	public UserEO getUserEO() {
		return userEO;
	}

	public void setUserEO(UserEO userEO) {
		this.userEO = userEO;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
