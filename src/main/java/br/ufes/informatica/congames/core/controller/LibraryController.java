package br.ufes.informatica.congames.core.controller;


import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.ufes.inf.nemo.jbutler.ejb.controller.JSFController;
import br.ufes.informatica.congames.core.application.LibraryService;
import br.ufes.informatica.congames.core.domain.Game;

@Named @ViewScoped
public class LibraryController extends JSFController {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private LibraryService libraryService;
	
	@Inject
	private SessionController sessionController;
	  
    
    public List<Game> getGames() {
    	return libraryService.retrieveUserGames(
    			sessionController.getCurrentUser().getId());
    }
	
}
