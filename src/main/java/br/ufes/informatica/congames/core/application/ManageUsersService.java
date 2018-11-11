package br.ufes.informatica.congames.core.application;

import javax.ejb.Local;
import javax.servlet.ServletException;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.informatica.congames.core.domain.User;

@Local
public interface ManageUsersService extends CrudService<User> {
	
	/**
	 * Find a User given it's username and password.
	 * @param username User's username.
	 * @return The existing User entity (or null).
	 */
	User find(String username);
	
	/**
	 * Find a User given it's username and password.
	 * @param username User's username.
	 * @param password User's password.
	 * @return The existing User entity (or null).
	 */
	User find(String username, String password);
}