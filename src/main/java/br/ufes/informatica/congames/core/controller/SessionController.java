package br.ufes.informatica.congames.core.controller;

import br.ufes.inf.nemo.jbutler.ejb.controller.JSFController;
import br.ufes.informatica.congames.core.application.SessionService;
import br.ufes.informatica.congames.core.domain.User;
import br.ufes.informatica.congames.core.exception.InvalidLoginException;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.IOException;
import java.io.Serializable;

@Named
@SessionScoped
public class SessionController extends JSFController implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	SessionService sessionService;

	private User currentUser;

	@NotNull(message = "Type your username.")
	private String username;

	@NotNull(message = "Type your password.")
	@Size(min = 6, message = "Password is at least 6 characters.")
	private String password;

	private boolean remember;

	public void login() throws IOException {
		getExternalContext().getFlash().setKeepMessages(true);

		try {
			currentUser = sessionService.login(username, password);

			username = null;

			// LOGIN SUCCESS
			getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Welcome back, " + currentUser.getUsername() + "!", ""));
			
			getExternalContext().redirect(getExternalContext().getRequestContextPath() + "/index.xhtml");

		} catch (InvalidLoginException e) {
			getFacesContext().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid username/password", ""));

		} catch (Exception e) {
			getFacesContext().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error trying to sign in :(", ""));

		} finally {
			getExternalContext().redirect(getExternalContext().getRequestContextPath() + "/sign-in.xhtml");

		}
	}
	
	public void checkPublisherRole() throws IOException {
		if(currentUser == null || currentUser.getUserRole().getId() != 1) {
			getExternalContext().getFlash().setKeepMessages(true);
			getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "You don't have permission for this resource.", ""));
			getExternalContext().redirect(getExternalContext().getRequestContextPath() + "/index.xhtml");
		}
	}
	
	public void checkClientRole() throws IOException {
		if(currentUser == null || currentUser.getUserRole().getId() != 2) {
			getExternalContext().getFlash().setKeepMessages(true);
			getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "You don't have permission for this resource.", ""));
			getExternalContext().redirect(getExternalContext().getRequestContextPath() + "/index.xhtml");
		}
	}
	
	public void checkUserLoggedIn() throws IOException {
		if(currentUser == null) {
			getExternalContext().redirect(getExternalContext().getRequestContextPath() + "/sign-in.xhtml");
		}
	}

	public void logout() throws IOException {
		currentUser = null;
		username = null;
		password = null;

		getExternalContext().getFlash().setKeepMessages(true);
		getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "See you later!", ""));
		getExternalContext().redirect(getExternalContext().getRequestContextPath() + "/sign-in.xhtml");

	}

	public boolean getIsLoggedIn() {

		return currentUser != null;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isRemember() {
		return remember;
	}

	public void setRemember(boolean remember) {
		this.remember = remember;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

}
