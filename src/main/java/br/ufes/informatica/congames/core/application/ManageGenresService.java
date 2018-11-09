package br.ufes.informatica.congames.core.application;

import javax.ejb.Local;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.informatica.congames.core.domain.Genre;

@Local
public interface ManageGenresService extends CrudService<Genre> {

}