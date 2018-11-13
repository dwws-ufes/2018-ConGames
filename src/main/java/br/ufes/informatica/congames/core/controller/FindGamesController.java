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
import br.ufes.informatica.congames.core.domain.Game;
import br.ufes.informatica.congames.core.domain.Genre;
import br.ufes.informatica.congames.core.domain.User;
import br.ufes.informatica.congames.core.exception.AlreadyBoughtGameException;
import br.ufes.informatica.congames.core.exception.InsufficientFundsException;

@Named
@ViewScoped
public class FindGamesController extends JSFController {
	private static final long serialVersionUID = 1L;

	@EJB
	FindGamesService findGamesService;

	@Inject
	private SessionController sessionController;

	private List<Game> games;

	private Game selectedGame;

	private Genre selectedGenre;

	private User selectedPublisher;

	private User currentUser;

	@PostConstruct
	public void init() {
		games = findGamesService.retrieveAllGames();
		currentUser = sessionController.getCurrentUser();
	}

	public void applyFilters() {
		games = findGamesService.retrieveAllGames();

		if (selectedGenre != null) {
			games = findGamesService.filterByGenre(games, selectedGenre);
		}

		if (selectedPublisher != null) {
			games = findGamesService.filterByPublisher(games, selectedPublisher);
		}
	}

	public void buyGame() throws IOException {
		getExternalContext().getFlash().setKeepMessages(true);

		try {

			currentUser = findGamesService.buyGame(currentUser.getId(), selectedGame);

			getFacesContext().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Purchase successful!", null));

		} catch (AlreadyBoughtGameException e) {
			
			getFacesContext().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "You already have this game!", ""));

		} catch (InsufficientFundsException e) {
			getFacesContext().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Insufficient funds", ""));

		} catch (Exception e) {
			getFacesContext().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error trying to buy game :(", ""));
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

	public Genre getSelectedGenre() {
		return selectedGenre;
	}

	public void setSelectedGenre(Genre selectedGenre) {
		this.selectedGenre = selectedGenre;
	}

	public User getSelectedPublisher() {
		return selectedPublisher;
	}

	public void setSelectedPublisher(User selectedPublisher) {
		this.selectedPublisher = selectedPublisher;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public List<Genre> getGenreOptions() {
		return findGamesService.retrieveAllGenres();
	}

	public List<User> getPublisherOptions() {
		return findGamesService.retrieveAllPublishers();
	}

}
