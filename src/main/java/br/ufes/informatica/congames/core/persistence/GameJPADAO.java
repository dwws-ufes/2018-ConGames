package br.ufes.informatica.congames.core.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO;
import br.ufes.informatica.congames.core.domain.Game;

@Stateless
public class GameJPADAO extends BaseJPADAO<Game> implements GameDAO {
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}
	
	@Override
	public Game retrieveByName(String name) {	
		for(Game game : retrieveAll()) {
			if(game.getName().equals(name)) {
				return game;
			}
		}
		
		return null;
	}

}