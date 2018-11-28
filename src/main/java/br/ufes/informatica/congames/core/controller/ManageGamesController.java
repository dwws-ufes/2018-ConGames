package br.ufes.informatica.congames.core.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import java.util.stream.Collectors;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;
import br.ufes.informatica.congames.core.application.ManageGamesService;
import br.ufes.informatica.congames.core.application.ManageGenresService;
import br.ufes.informatica.congames.core.domain.Game;
import br.ufes.informatica.congames.core.domain.Genre;

@Named
@SessionScoped
public class ManageGamesController extends CrudController<Game> {
	private static final long serialVersionUID = 1L;

	private List<Game> publishedGames;

	@EJB
	private ManageGamesService manageGamesService;

	@EJB
	private ManageGenresService manageGenresService;

	@Inject
	private SessionController sessionController;

	@Override
	protected CrudService<Game> getCrudService() {
		return manageGamesService;
	}

	@Override
	protected void initFilters() {
	}

	public List<Genre> getGenreOptions() {
		return manageGenresService.getDAO().retrieveAll();
	}

	@Override
	public String create() {
		// Sets the data as read-write.
		readOnly = false;

		// Resets the entity so we can create a new one.
		selectedEntity = createNewEntity();
		selectedEntity.setPublisher(sessionController.getCurrentUser());
		// Goes to the form.
		return getViewPath() + "form.xhtml?faces-redirect=" + getFacesRedirect();

	}

	public List<Game> getPublishedGames() {

		publishedGames = manageGamesService.getDAO().retrieveAll();

		publishedGames = publishedGames.stream().filter(
				game -> game.getPublisher().getUsername().equals(sessionController.getCurrentUser().getUsername()))
				.collect(Collectors.toList());

		return publishedGames;
	}

	public void setPublishedGames(List<Game> publishedGames) {
		this.publishedGames = publishedGames;
	}

}
