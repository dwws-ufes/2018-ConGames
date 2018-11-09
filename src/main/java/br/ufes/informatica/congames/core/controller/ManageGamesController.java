package br.ufes.informatica.congames.core.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;
import br.ufes.informatica.congames.core.application.ManageGamesService;
import br.ufes.informatica.congames.core.application.ManageGenresService;
import br.ufes.informatica.congames.core.domain.Game;
import br.ufes.informatica.congames.core.domain.Genre;

@Named @SessionScoped
public class ManageGamesController extends CrudController<Game> {
	private static final long serialVersionUID = 1L;

	@EJB
	private ManageGamesService manageGamesService;
	
	@EJB
	private ManageGenresService manageGenresService;
		
	@Override
	protected CrudService<Game> getCrudService() {
		return manageGamesService;
	}
	
	@Override
	protected void initFilters() { }

	public List<Genre> getGenreOptions() {
		return manageGenresService.getDAO().retrieveAll();
	}

}