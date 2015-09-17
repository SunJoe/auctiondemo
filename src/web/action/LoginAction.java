package web.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import web.dao.ICommonDao;
import web.entity.User;

import com.opensymphony.xwork2.ActionContext;

public class LoginAction {
	private User user;
	private String userName;
	private String password;

	protected ICommonDao dao;

	@Resource(name = "dao")
	public void setDao(ICommonDao dao) {
		this.dao = dao;
	}

	public String login() {
		userName = user.getUserName();
		password = user.getPassword();

		String hql = "from User where userName = ? and password = ?";
		List userList = dao.findByHql(hql, userName, password);

		// ´´½¨session
		ActionContext ctx = ActionContext.getContext();
		Map session = ctx.getSession();

		if (userList.size() == 0) {
			return "false";
		}
		// if(userList.size() != 0){
		// return "true";
		// }
		else {
			session.put("userName", userName);
			return "true";
		}

	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
