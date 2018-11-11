package br.ufes.informatica.congames.core.controller;

import br.ufes.inf.nemo.jbutler.ejb.controller.JSFController;
import br.ufes.informatica.congames.core.application.SessionService;
import br.ufes.informatica.congames.core.domain.User;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by rmpestano on 12/20/14.
 *
 * This is just a login example.
 *
 * AdminSession uses isLoggedIn to determine if user must be redirect to login page or not.
 * By default AdminSession isLoggedIn always resolves to true so it will not try to redirect user.
 *
 * If you already have your authorization mechanism which controls when user must be redirect to initial page or logon
 * you can skip this class.
 */
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
        //currentUser = username;
    	try {
    		
			currentUser = sessionService.login(username, password);
			
			//LOGIN SUCCESS
			getExternalContext().getFlash().setKeepMessages(true);
			getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome back, " + username + " as " + currentUser.getUserRole().getName(), ""));

			getExternalContext().redirect(getExternalContext().getRequestContextPath() + "/index.xhtml");		
			getExternalContext().getFlash().setKeepMessages(true);
	        
		} catch (Exception e) {
			// LOGIN FAILED
			getExternalContext().getFlash().setKeepMessages(true);
			getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid credentials", ""));
			getExternalContext().redirect(getExternalContext().getRequestContextPath() + "/blah.xhtml");		
		}
    	
    }
    
    public void logout() throws IOException {
    	currentUser = null;
    	username = null;
    	password = null;
    	
		getExternalContext().getFlash().setKeepMessages(true);
		getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Logged out", ""));
		getExternalContext().redirect(getExternalContext().getRequestContextPath() + "/blah.xhtml");		

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
