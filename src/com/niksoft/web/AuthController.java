package com.niksoft.web;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.niksoft.dao.User;
import com.niksoft.dao.UserDAService;
import com.niksoft.dao.Users;

@ManagedBean
@SessionScoped
public class AuthController implements Serializable  {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(AuthController.class.getName());
	
	public AuthController(){
		
	}
	
	public String logout() {
		log.warning("logout: logging - out.");
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		request.getSession().invalidate();
		return "/index.html?faces-redirect=true&";
	}
	public String login() throws Exception {
		FacesContext context = FacesContext.getCurrentInstance();

		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		String landingPage = "/index.html";
		try{
		request.getSession().setMaxInactiveInterval(240);
		
		request.login(username, password);
		}catch(Exception ex){
			
		}
		if (request.getUserPrincipal() == null) {
			context.addMessage(null, new FacesMessage("Unknown login"));
			log.info(request.getSession().getId());

		} else {
			landingPage = "/index.html?faces-redirect=true&";
			
			log.info(String.format("HTTP_REFERER -- %s", request.getHeader("referer")));
			
			HttpSession session = request.getSession();
			
			UserDAService service = new UserDAService();
			Users users = service.findAllByIdentity(request.getUserPrincipal().getName(), "");
			
			log.warning(String.format("!! Authenticated !! %s session: %s", request.getUserPrincipal().getName(), request.getSession().getId()));
			
			User user =  users.get(0);
			
			session.setAttribute("user", user);
		}

		return landingPage;
	}
	public static String getUserRole(FacesContext context){

		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		boolean result = request.getUserPrincipal() != null;
		String role = "nobody";
		if (result & request.isUserInRole("users")){
			role = "users";
		}else if(result & request.isUserInRole("vendors")){
			role = "vendors";
		}else if(result & request.isUserInRole("manager")){
			role = "manager";
		}
		return role;
	}
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private String username;
	private String password;
	
}
