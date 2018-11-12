package br.ufes.informatica.congames.core.controller;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.ufes.inf.nemo.jbutler.ejb.controller.JSFController;
import br.ufes.informatica.congames.core.application.ManageUsersService;
import br.ufes.informatica.congames.core.domain.User;

@Named @RequestScoped
public class MyProfileController extends JSFController {
	private static final long serialVersionUID = 1L;

	@EJB
	private ManageUsersService manageUsersService;

	@Inject
	private SessionController sessionController;
	
	
	@NotNull(message = "Username is required.")
	private String username;

	@NotNull(message = "Password is required.")
	@Size(min = 6, message = "Password is at least 6 characters.")
	private String password;

	@NotNull(message = "Email is required.")
	@Email(message = "Enter a valid e-mail")
	private String email;
	
	@NotNull(message = "Name is required.")
	private String name;
	
	private String website;
	
	
	@PostConstruct
	public void init() {
		User user = sessionController.getCurrentUser();
		email = user.getEmail();
		username = user.getUsername();
		password = user.getPassword();
		name = user.getName();
		website = user.getWebsite();
	}
	
	public String updateUserProfile()
	{
		User user = sessionController.getCurrentUser();
		user.setEmail(email);
		user.setUsername(username);
		user.setPassword(password);
		user.setName(name);
		user.setWebsite(website);
		
		manageUsersService.update(user);
		
		getFacesContext().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Profile updated",""));

		
		return null;
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}
	
}
