package br.ufes.informatica.congames.core.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO;
import br.ufes.informatica.congames.core.domain.Role;

@Stateless
public class RoleJPADAO extends BaseJPADAO<Role> implements RoleDAO {
		private static final long serialVersionUID = 1L;
		
		@PersistenceContext
		private EntityManager entityManager;
		
		@Override
		protected EntityManager getEntityManager() {
				return entityManager;
		}

}