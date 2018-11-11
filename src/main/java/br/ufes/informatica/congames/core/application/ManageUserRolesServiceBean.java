package br.ufes.informatica.congames.core.application;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.informatica.congames.core.domain.UserRole;
import br.ufes.informatica.congames.core.persistence.UserRoleDAO;

@Stateless
@PermitAll
public class ManageUserRolesServiceBean extends CrudServiceBean<UserRole> implements ManageUserRolesService {
	private static final long serialVersionUID = 1L;

	@EJB
	private UserRoleDAO userRoleDAO;

	@Override
	public BaseDAO<UserRole> getDAO() {
		return userRoleDAO;
	}

}