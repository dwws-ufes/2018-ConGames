package br.ufes.informatica.congames.core.application;

import java.util.List;

import javax.ejb.Local;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.informatica.congames.core.domain.Game;
import br.ufes.informatica.congames.core.domain.Genre;
import br.ufes.informatica.congames.core.domain.User;

@Local
public interface ManageGamesService extends CrudService<Game> {

	public List<Game> retrieveAll();
	
	List<Game> filterByGenre(List<Game> games, Genre genre);

	List<Game> filterByPublisher(List<Game> games, User publisher);

}