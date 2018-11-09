package br.ufes.informatica.congames.core.domain;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

@Entity
public class UserRole extends PersistentObjectSupport {

	private static final long serialVersionUID = 1L;
	
	@OneToOne
	private User user;
	
	@OneToOne
	private Role role;
	

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}