package br.ufes.informatica.congames.core.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;
import javax.validation.constraints.PositiveOrZero;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

@Entity
public class User extends PersistentObjectSupport {

	private static final long serialVersionUID = 1L;

	@NotNull
	private String username;
	
	@NotNull
	@Size(min = 6)
	private String password;
	
	@NotNull
	@Email
	private String email;
	
	@NotNull
	private String name;
	
	private String website;

	@PositiveOrZero
	private double funds;
	
	@ManyToOne
	private UserRole userRole;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "publisher")
	private Set<Game> publishedGames;
	
	@ManyToMany
	private Set<Game> boughtGames;
	
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

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public Set<Game> getPublishedGames() {
		return publishedGames;
	}

	public void setPublishedGames(Set<Game> publishedGames) {
		this.publishedGames = publishedGames;
	}
	
	public Set<Game> getBoughtGames() {
		return boughtGames;
	}

	public void setBoughtGames(Set<Game> boughtGames) {
		this.boughtGames = boughtGames;
	}

	@Override
	public String toString()
	{
	    return this.name;
	}

}