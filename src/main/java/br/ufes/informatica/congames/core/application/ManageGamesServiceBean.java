package br.ufes.informatica.congames.core.application;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.informatica.congames.core.domain.Game;
import br.ufes.informatica.congames.core.domain.Genre;
import br.ufes.informatica.congames.core.domain.User;
import br.ufes.informatica.congames.core.persistence.GameDAO;

@Stateless
@PermitAll
public class ManageGamesServiceBean extends CrudServiceBean<Game> implements ManageGamesService {
	private static final long serialVersionUID = 1L;

	@EJB
	private GameDAO gameDAO;

	@Override
	public BaseDAO<Game> getDAO() {
		return gameDAO;
	}
	
	@Override
	public List<Game> retrieveAll() {
		return gameDAO.retrieveAll();
	}
	
	@Override
	public List<Game> filterByGenre(List<Game> games, Genre genre) {

		games = games.stream()
				.filter(game -> game.getGenre().getId() == genre.getId())
				.collect(Collectors.toList());
		
		return games;
	}
	
	@Override
	public List<Game> filterByPublisher(List<Game> games, User publisher) {

		games = games.stream()
				.filter(game -> game.getPublisher().getId() == publisher.getId())
				.collect(Collectors.toList());
		
		return games;
	}
}