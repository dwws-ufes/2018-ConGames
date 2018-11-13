package br.ufes.informatica.congames.core.application;

import java.io.Serializable;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.jbutler.ejb.application.filters.SimpleFilter;
import br.ufes.informatica.congames.core.domain.Game;
import br.ufes.informatica.congames.core.domain.User;
import br.ufes.informatica.congames.core.domain.UserRole;
import br.ufes.informatica.congames.core.exception.AlreadyBoughtGameException;
import br.ufes.informatica.congames.core.exception.EmailTakenException;
import br.ufes.informatica.congames.core.exception.InsufficientFundsException;
import br.ufes.informatica.congames.core.exception.UsernameTakenException;
import br.ufes.informatica.congames.core.persistence.UserDAO;

@Stateless
@PermitAll
public class FindGamesService implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private UserDAO userDAO;
		
		
	public User buyGame(long userId, Game game) throws Exception {
		User user = userDAO.retrieveById(userId);
		
		if(user.getBoughtGames().contains(game)) {
			throw new AlreadyBoughtGameException();
		}

		if(user.getFunds() < game.getPrice()) {
			throw new InsufficientFundsException();
		}
		
		user.setFunds(user.getFunds() - game.getPrice());
		user.getBoughtGames().add(game);
		
		userDAO.save(user);
		
		return user;
	}
	
}