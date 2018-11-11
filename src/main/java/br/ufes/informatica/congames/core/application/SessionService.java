package br.ufes.informatica.congames.core.application;

import java.io.Serializable;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.informatica.congames.core.domain.User;

@Stateless
@PermitAll
public class SessionService implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private UserService userSerivce;
	
	@EJB
	private ManageUserRolesService manageUserRolesService;
		
	public User login(String username, String password) throws Exception {
		
		User user = userSerivce.find(username, password);
		
		if(user == null) throw new Exception();
		
		return user;
	}
	
}