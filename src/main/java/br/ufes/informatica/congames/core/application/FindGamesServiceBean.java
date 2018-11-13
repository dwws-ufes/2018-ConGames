package br.ufes.informatica.congames.core.application;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.informatica.congames.core.domain.Game;
import br.ufes.informatica.congames.core.domain.Genre;
import br.ufes.informatica.congames.core.domain.User;
import br.ufes.informatica.congames.core.exception.AlreadyBoughtGameException;
import br.ufes.informatica.congames.core.exception.InsufficientFundsException;
import br.ufes.informatica.congames.core.persistence.GameDAO;
import br.ufes.informatica.congames.core.persistence.GenreDAO;
import br.ufes.informatica.congames.core.persistence.UserDAO;

@Stateless
@PermitAll
public class FindGamesServiceBean implements FindGamesService {
	private static final long serialVersionUID = 1L;

	@EJB
	private GameDAO gameDAO;

	@EJB
	private UserDAO userDAO;

	@EJB
	private GenreDAO genreDAO;

	@Override
	public List<Game> retrieveAllGames() {
		return gameDAO.retrieveAll();
	}

	@Override
	public List<Genre> retrieveAllGenres() {
		return genreDAO.retrieveAll();
	}

	@Override
	public List<User> retrieveAllPublishers() {
		List<User> publishers = userDAO.retrieveAll();

		publishers = publishers.stream()
				.filter(user -> user.getUserRole().getId() == 1)
				.collect(Collectors.toList());
		
		return publishers;
	}

	@Override
	public List<Game> filterByGenre(List<Game> games, Genre genre) {

		games = games.stream().filter(game -> game.getGenre().getId() == genre.getId()).collect(Collectors.toList());

		return games;
	}

	@Override
	public List<Game> filterByPublisher(List<Game> games, User publisher) {

		games = games.stream().filter(game -> game.getPublisher().getId() == publisher.getId())
				.collect(Collectors.toList());

		return games;
	}

	@Override
	public User buyGame(long userId, Game game) throws Exception {
		User user = userDAO.retrieveById(userId);

		if (user.getBoughtGames().contains(game)) {
			throw new AlreadyBoughtGameException();
		}

		if (user.getFunds() < game.getPrice()) {
			throw new InsufficientFundsException();
		}

		user.setFunds(user.getFunds() - game.getPrice());
		user.getBoughtGames().add(game);

		userDAO.save(user);

		return user;
	}
}
