package br.ufes.informatica.congames.core.application;

import java.io.Serializable;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.informatica.congames.core.domain.User;
import br.ufes.informatica.congames.core.exception.InvalidLoginException;

@Stateless
@PermitAll
public class SessionService implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ManageUsersService userService;
	
	public User login(String username, String password) throws InvalidLoginException {
		
		User user = userService.find(username, password);
				
		if(user == null) throw new InvalidLoginException();
		
		return user;
	}
	
}