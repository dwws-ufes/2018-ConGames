package br.ufes.informatica.congames.core.controller;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;
import br.ufes.informatica.congames.core.application.ManageGenresService;
import br.ufes.informatica.congames.core.domain.Genre;

@Named @SessionScoped
public class ManageGenresController extends CrudController<Genre> {
	private static final long serialVersionUID = 1L;

	@EJB
	private ManageGenresService manageGenresService;
	
	@Override
	protected CrudService<Genre> getCrudService() {
		return manageGenresService;
	}

	@Override
	protected void initFilters() { }
}