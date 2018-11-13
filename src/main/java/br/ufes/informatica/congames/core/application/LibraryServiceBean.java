package br.ufes.informatica.congames.core.application;

import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.informatica.congames.core.domain.Game;
import br.ufes.informatica.congames.core.domain.User;
import br.ufes.informatica.congames.core.persistence.GameDAO;
import br.ufes.informatica.congames.core.persistence.UserDAO;

@Stateless
@PermitAll
public class LibraryServiceBean implements LibraryService {
	private static final long serialVersionUID = 1L;

	@EJB
	private GameDAO gameDAO;

	@EJB
	private UserDAO userDAO;

	public List<Game> retrieveUserGames(long userId) {
		User user = userDAO.retrieveById(userId);

		// user.setBoughtGames(userDAO.merge(user).getBoughtGames());
		user.getBoughtGames().size();
		return user.getBoughtGames();
	}
}
