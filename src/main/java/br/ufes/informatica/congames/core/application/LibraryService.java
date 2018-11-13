package br.ufes.informatica.congames.core.application;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.informatica.congames.core.domain.Game;
import br.ufes.informatica.congames.core.domain.User;
import br.ufes.informatica.congames.core.persistence.GameDAO;
import br.ufes.informatica.congames.core.persistence.UserDAO;

@Stateless
@PermitAll
public class LibraryService implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private GameDAO gameDAO;
	
	@EJB
	private UserDAO userDAO;
		
	public List<Game> getGames(long userId) {
		User user = userDAO.retrieveById(userId);
		
		//user.setBoughtGames(userDAO.merge(user).getBoughtGames());
		user.getBoughtGames().size();
		return user.getBoughtGames();
	}
	
}