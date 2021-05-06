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

		setTitle("使用者註冊");

		if (userEO == null) {
			return REGISTER;
		}
		if (StringUtil.isNull(userEO.getLogin())
				|| StringUtil.isNull(userEO.getPassword())) {
			setMessage("請填寫註冊資訊");
			return REGISTER;
		}
		if (!userEO.getPassword().equals(confirmPassword)) {
			setMessage("密碼不一致");
			return REGISTER;
		}

		try {
			userEO = BOClient.lookupIUser().createUser(userEO);

			HttpSession session = ServletActionContext.getRequest().getSession(
					true);

			session.setAttribute("userEO", userEO);

			setMessage("註冊成功");

			return SUCCESS;

		} catch (Exception e) {
			setMessage("發生錯誤：" + e.getMessage());
		}

		return REGISTER;
	}

	public String login() {

		setTitle("使用者登入");

		if (userEO == null)
			return LOGIN;
		if (StringUtil.isNull(userEO.getLogin())
				|| StringUtil.isNull(userEO.getPassword())) {
			setMessage("請輸入賬號密碼");
			return LOGIN;
		}

		try {
			UserEO user = BOClient.lookupIUser().findUser(userEO.getLogin(),
					userEO.getPassword());

			if (user == null) {
				setMessage("使用者名稱密碼錯誤");
				return LOGIN;
			} else {
				HttpSession session = ServletActionContext.getRequest()
						.getSession(true);
				session.setAttribute("userEO", user);
				setMessage("歡迎回來，" + user.getLogin());
				return SUCCESS;
			}

		} catch (Exception e) {
			setMessage("發生錯誤：" + e.getMessage());
		}

		return LOGIN;
	}

	public String logout() {

		HttpSession session = ServletActionContext.getRequest()
				.getSession(true);
		session.setAttribute("userEO", null);

		setMessage("註銷成功");

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
