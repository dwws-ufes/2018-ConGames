package br.ufes.informatica.congames.core.persistence;

import javax.ejb.Local;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.informatica.congames.core.domain.Genre;

@Local
public interface GenreDAO extends BaseDAO<Genre> {
	
	public Genre retrieveByName(String name);

}
