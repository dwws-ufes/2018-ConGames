package br.ufes.informatica.congames.core.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;
import javax.validation.constraints.PositiveOrZero;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

@Entity
public class User extends PersistentObjectSupport {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Size(max = 15)
	private String username;
	
	@NotNull
	@Size(min = 6, max = 20)
	private String password;
	
	@NotNull
	@Email
	private String email;

	@PositiveOrZero
	private double funds;
	
	@ManyToOne
	private UserRole userRole;

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

	public double getFunds() {
		return funds;
	}

	public void setFunds(double funds) {
		this.funds = funds;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

}