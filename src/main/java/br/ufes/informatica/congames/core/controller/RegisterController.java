package br.ufes.informatica.congames.core.controller;

import br.ufes.inf.nemo.jbutler.ejb.controller.JSFController;
import br.ufes.informatica.congames.core.application.RegisterService;
import br.ufes.informatica.congames.core.domain.User;
import br.ufes.informatica.congames.core.domain.UserRole;
import br.ufes.informatica.congames.core.exception.EmailTakenException;
import br.ufes.informatica.congames.core.exception.UsernameTakenException;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class RegisterController extends JSFController implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	RegisterService registerService;

	@NotNull(message = "Type your username.")
	private String username;

	@NotNull(message = "Type your password.")
	@Size(min = 6, message = "Password is at least 6 characters.")
	private String password;

	@NotNull(message = "Type your e-mail.")
	@Email(message = "Enter a valid e-mail")
	private String email;
	
	@NotNull(message = "Type your real name.")
	private String name;

	@NotNull(message = "Select account type.")
	private UserRole userRole;

	public void register() throws IOException {

		User newUser = new User();
		newUser.setEmail(email);
		newUser.setUsername(username);
		newUser.setPassword(password);
		newUser.setUserRole(userRole);
		newUser.setName(name);

		getExternalContext().getFlash().setKeepMessages(true);

		try {
			registerService.register(newUser);

			clearFields();

			getFacesContext().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Registration complete! Please sign in below.", ""));
			
			getExternalContext().redirect(getExternalContext().getRequestContextPath() + "/sign-in.xhtml");

		} catch (UsernameTakenException e) {
			getFacesContext().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username is taken. Try another one!", ""));

		} catch (EmailTakenException e) {
			getFacesContext().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email is taken. Try another one!", ""));

		} catch (Exception e) {
			getFacesContext().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error trying to register :'(", ""));

		} finally {
			getExternalContext().redirect(getExternalContext().getRequestContextPath() + "/sign-up.xhtml");
		}

	}

	public void clearFields() {
		email = null;
		username = null;
		password = null;
		userRole = null;
		name = null;
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

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public List<UserRole> getUserRoleOptions() {
		return registerService.getUserRoleOptions();
	}

}
