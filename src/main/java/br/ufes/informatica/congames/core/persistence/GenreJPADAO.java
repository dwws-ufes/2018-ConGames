package br.ufes.informatica.congames.core.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO;
import br.ufes.informatica.congames.core.domain.Genre;

@Stateless
public class GenreJPADAO extends BaseJPADAO<Genre> implements GenreDAO {
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public Genre retrieveByName(String name) {	
		for(Genre genre : retrieveAll()) {
			if(genre.getName().equals(name)) {
				return genre;
			}
		}
		
		return null;
	}


}