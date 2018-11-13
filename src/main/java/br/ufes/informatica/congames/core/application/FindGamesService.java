package br.ufes.informatica.congames.core.application;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;
import br.ufes.informatica.congames.core.domain.Game;
import br.ufes.informatica.congames.core.domain.Genre;
import br.ufes.informatica.congames.core.domain.User;

@Local
public interface FindGamesService extends Serializable {
		
	public User buyGame(long userId, Game game) throws Exception;
	
	public List<Game> retrieveAllGames();
	
	public List<Genre> retrieveAllGenres();
	
	public List<User> retrieveAllPublishers();
	
	List<Game> filterByGenre(List<Game> games, Genre genre);

	List<Game> filterByPublisher(List<Game> games, User publisher);
	
}