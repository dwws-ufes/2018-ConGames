package br.ufes.informatica.congames.core.application;

import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import br.ufes.informatica.congames.core.domain.Role;
import br.ufes.informatica.congames.core.domain.User;
import br.ufes.informatica.congames.core.domain.UserRole;
import br.ufes.informatica.congames.core.persistence.RoleDAO;
import br.ufes.informatica.congames.core.persistence.UserDAO;
import br.ufes.informatica.congames.core.persistence.UserRoleDAO;

public class InitialSeedService implements ServletContextListener {

	@EJB
	private UserDAO userDAO;
	
	@EJB
	private RoleDAO roleDAO;
	
	@EJB
	private UserRoleDAO userRoleDAO;

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
		if (roleDAO.retrieveCount() == 0) {
			Role rolePublisher = new Role();
			rolePublisher.setName("Publisher");
			roleDAO.save(rolePublisher);
			
			Role roleClient = new Role();
			roleClient.setName("Client");
			roleDAO.save(roleClient);
		}
		
		if (userDAO.retrieveCount() == 0) {
			Role publisherRole = roleDAO.retrieveById((long)1);
			Role clientRole = roleDAO.retrieveById((long)2);
			
			User userPublisher = new User();
			userPublisher.setEmail("publisher@congames.com");
			userPublisher.setFunds(0);
			userPublisher.setUsername("publisher");
			userPublisher.setPassword("publisher");
			userDAO.save(userPublisher);

			UserRole userRolePublisher = new UserRole();
			userRolePublisher.setUser(userPublisher);
			userRolePublisher.setRole(publisherRole);
			userRoleDAO.save(userRolePublisher);
			
			User userClient = new User();
			userClient.setEmail("client@gmail.com");
			userClient.setFunds(0);
			userClient.setUsername("client");
			userClient.setPassword("client");
			userDAO.save(userClient);
			
			UserRole userRoleClient = new UserRole();
			userRoleClient.setUser(userClient);
			userRoleClient.setRole(clientRole);
			userRoleDAO.save(userRoleClient);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}
}
