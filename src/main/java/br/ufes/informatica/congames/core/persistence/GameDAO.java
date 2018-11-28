package br.ufes.informatica.congames.core.persistence;

import javax.ejb.Local;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.informatica.congames.core.domain.Game;

@Local
public interface GameDAO extends BaseDAO<Game> {
	
	public Game retrieveByName(String name);
}
