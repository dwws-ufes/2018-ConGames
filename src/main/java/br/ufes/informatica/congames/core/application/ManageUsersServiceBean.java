package br.ufes.informatica.congames.core.application;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletException;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.informatica.congames.core.domain.User;
import br.ufes.informatica.congames.core.persistence.UserDAO;

@Stateless
@PermitAll
public class ManageUsersServiceBean extends CrudServiceBean<User> implements ManageUsersService {
	private static final long serialVersionUID = 1L;

	@EJB
	private UserDAO userDAO;

	@Override
	public BaseDAO<User> getDAO() {
		return userDAO;
	}

	@Override
	public User find(String username) {	
		for(User user : userDAO.retrieveAll())
		{
			if(user.getUsername().equals(username)) {
				return user;
			}
		}
		
		return null;
	}
	
	@Override
	public User find(String username, String password) {	
		for(User user : userDAO.retrieveAll())
		{
			if(user.getUsername().equals(username) &&
			   user.getPassword().equals(password)) {
				return user;
			}
		}
		
		return null;
	}
}