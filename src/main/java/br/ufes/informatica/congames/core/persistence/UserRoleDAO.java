package br.ufes.informatica.congames.core.persistence;

import javax.ejb.Local;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.informatica.congames.core.domain.UserRole;

@Local
public interface UserRoleDAO extends BaseDAO<UserRole> {

}
