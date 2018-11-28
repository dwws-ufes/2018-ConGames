package br.ufes.informatica.congames.core.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.ufes.inf.nemo.jbutler.ejb.controller.JSFController;
import br.ufes.informatica.congames.core.application.FindGamesService;
import br.ufes.informatica.congames.core.application.PublishGamesService;
import br.ufes.informatica.congames.core.domain.Game;
import br.ufes.informatica.congames.core.domain.Genre;
import br.ufes.informatica.congames.core.domain.User;
import br.ufes.informatica.congames.core.exception.AlreadyBoughtGameException;
import br.ufes.informatica.congames.core.exception.InsufficientFundsException;

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
		games = publishGamesService.SearchGamesByPublisher(currentUser.getName());
		
		if(games == null) {
			getFacesContext().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "No games found for " + currentUser.getName(), null));
		}
	}

//	public void buyGame() throws IOException {
//		getExternalContext().getFlash().setKeepMessages(true);
//
//		try {
//
//			currentUser = publishGamesService.buyGame(currentUser.getId(), selectedGame);
//
//			getFacesContext().addMessage(null,
//					new FacesMessage(FacesMessage.SEVERITY_INFO, "Purchase successful!", null));
//
//		} catch (AlreadyBoughtGameException e) {
//
//			getFacesContext().addMessage(null,
//					new FacesMessage(FacesMessage.SEVERITY_ERROR, "You already have this game!", ""));
//
//		} catch (InsufficientFundsException e) {
//			getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Insufficient funds", ""));
//
//		} catch (Exception e) {
//			getFacesContext().addMessage(null,
//					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error trying to buy game :(", ""));
//		}
//	}

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
