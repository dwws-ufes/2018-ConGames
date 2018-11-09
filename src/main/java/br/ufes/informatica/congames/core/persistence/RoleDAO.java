package br.ufes.informatica.congames.core.persistence;

import javax.ejb.Local;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.informatica.congames.core.domain.Role;

@Local
public interface RoleDAO extends BaseDAO<Role> {

}
