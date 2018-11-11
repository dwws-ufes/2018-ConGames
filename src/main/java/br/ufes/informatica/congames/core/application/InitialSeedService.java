package br.ufes.informatica.congames.core.application;

import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import br.ufes.informatica.congames.core.domain.User;
import br.ufes.informatica.congames.core.domain.UserRole;
import br.ufes.informatica.congames.core.persistence.UserDAO;
import br.ufes.informatica.congames.core.persistence.UserRoleDAO;

public class InitialSeedService implements ServletContextListener {

	@EJB
	private UserDAO userDAO;

	@EJB
	private UserRoleDAO userRoleDAO;

	@Override
	public void contextInitialized(ServletContextEvent arg0) {

		if (userRoleDAO.retrieveCount() == 0) {
			UserRole rolePublisher = new UserRole();
			rolePublisher.setName("Publisher");
			userRoleDAO.save(rolePublisher);

			UserRole roleClient = new UserRole();
			roleClient.setName("Client");
			userRoleDAO.save(roleClient);
		}

		if (userDAO.retrieveCount() == 0) {
			UserRole publisherRole = userRoleDAO.retrieveById((long) 1);
			UserRole clientRole = userRoleDAO.retrieveById((long) 2);

			User userPublisher = new User();
			userPublisher.setEmail("publisher@congames.com");
			userPublisher.setFunds(0);
			userPublisher.setUsername("publisher");
			userPublisher.setPassword("publisher");
			userPublisher.setUserRole(publisherRole);
			userDAO.save(userPublisher);

			User userClient = new User();
			userClient.setEmail("client@gmail.com");
			userClient.setFunds(0);
			userClient.setUsername("client");
			userClient.setPassword("client");
			userClient.setUserRole(clientRole);
			userDAO.save(userClient);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}
}
