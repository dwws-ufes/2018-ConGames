package br.ufes.informatica.congames.core.application;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;
import br.ufes.informatica.congames.core.domain.Game;
import br.ufes.informatica.congames.core.exception.GameAlreadyPublishedException;

@Local
public interface PublishGamesService extends Serializable {
	
	public void publishGame(Game game) throws GameAlreadyPublishedException;
	
	public List<Game> searchGamesByPublisher(String name);
	
}