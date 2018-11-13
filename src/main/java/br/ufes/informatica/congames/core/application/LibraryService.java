package br.ufes.informatica.congames.core.application;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Local;
import br.ufes.informatica.congames.core.domain.Game;

@Local
public interface LibraryService extends Serializable {
		
	public List<Game> retrieveUserGames(long userId);
	
}