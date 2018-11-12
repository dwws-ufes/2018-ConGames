package br.ufes.informatica.congames.core.application;

import java.io.Serializable;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.jbutler.ejb.application.filters.SimpleFilter;
import br.ufes.informatica.congames.core.domain.User;
import br.ufes.informatica.congames.core.domain.UserRole;
import br.ufes.informatica.congames.core.exception.EmailTakenException;
import br.ufes.informatica.congames.core.exception.UsernameTakenException;

@Stateless
@PermitAll
public class RegisterService implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ManageUsersService manageUsersService;
	
	@EJB
	private ManageUserRolesService manageUserRolesService;
	
		
	public void register(User newUser) throws Exception {
		long matchedUsersCount = 
				manageUsersService.countFiltered(
						new SimpleFilter(null, "username", null),
						newUser.getUsername());
		
		if(matchedUsersCount > 0)
		{
			throw new UsernameTakenException();
		}
		
		matchedUsersCount = manageUsersService.countFiltered(
				new SimpleFilter(null, "email", null),
				newUser.getEmail());
		
		if(matchedUsersCount > 0)
		{
			throw new EmailTakenException();
		}
		
		manageUsersService.create(newUser);
	}
	
	public List<UserRole> getUserRoleOptions() {
		return manageUserRolesService.getDAO().retrieveAll();
	}
	
}