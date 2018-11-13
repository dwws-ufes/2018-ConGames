package br.ufes.informatica.congames.core.controller;


import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.ufes.inf.nemo.jbutler.ejb.controller.JSFController;
import br.ufes.informatica.congames.core.application.FindGamesService;
import br.ufes.informatica.congames.core.application.LibraryService;
import br.ufes.informatica.congames.core.application.ManageGamesService;
import br.ufes.informatica.congames.core.application.ManageGenresService;
import br.ufes.informatica.congames.core.application.ManageUsersService;
import br.ufes.informatica.congames.core.domain.Game;
import br.ufes.informatica.congames.core.domain.Genre;
import br.ufes.informatica.congames.core.domain.User;
import br.ufes.informatica.congames.core.exception.AlreadyBoughtGameException;
import br.ufes.informatica.congames.core.exception.InsufficientFundsException;

@Named @ViewScoped
public class LibraryController extends JSFController {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private LibraryService libraryService;
	
	@Inject
	private SessionController sessionController;
	  
    
    public List<Game> getGames() {
    	return libraryService.getGames(
    			sessionController.getCurrentUser().getId());
    }
	
}
