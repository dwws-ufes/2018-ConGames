package br.ufes.informatica.congames.core.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.ufes.inf.nemo.jbutler.ejb.controller.JSFController;
import br.ufes.informatica.congames.core.application.PublishGamesService;
import br.ufes.informatica.congames.core.domain.Game;
import br.ufes.informatica.congames.core.domain.User;
import br.ufes.informatica.congames.core.exception.GameAlreadyPublishedException;

@Named
@ViewScoped
public class PublishGamesController extends JSFController {
	private static final long serialVersionUID = 1L;

	@EJB
	PublishGamesService publishGamesService;

	@Inject
	private SessionController sessionController;

	private User currentUser;

	private List<Game> games;

	private Game selectedGame;

	@PostConstruct
	public void init() {
		currentUser = sessionController.getCurrentUser();
	}

	public void searchPublisherGames() {
		games = publishGamesService.searchGamesByPublisher(currentUser.getName());

		if (games == null) {
			getFacesContext().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Nothing!",
							"No games found for " + currentUser.getName()));
		}
		else {
			getFacesContext().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							games.size() + " games found!",
							"Searched for " + currentUser.getName() + "'s games"));

		}
	}

	public void publishGame() {
		try {
			
			selectedGame.setPublisher(currentUser);
			selectedGame.setPrice(0);
			selectedGame.setPublishDate(new java.util.Date());

			publishGamesService.publishGame(selectedGame);

			getExternalContext().getFlash().setKeepMessages(true);
			getFacesContext().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Published!",
							selectedGame.getName() + " published successfully"));
			
			getExternalContext().redirect(getExternalContext()
					.getRequestContextPath() + "/core/manageGames/index.xhtml");
		
		} catch(GameAlreadyPublishedException e) {
			getFacesContext().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Denied.",
							"This game is already published"));

		} catch (Exception e) {
			getFacesContext().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Something went wrong",
							"Unexpected error when trying to publish your game"));

		}
	}


	public List<Game> getGames() {
		return games;
	}

	public Game getSelectedGame() {
		return selectedGame;
	}

	public void setSelectedGame(Game selectedGame) {
		this.selectedGame = selectedGame;
	}

}
