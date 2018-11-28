package br.ufes.informatica.congames.core.application;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.informatica.congames.core.domain.Game;
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
	
}