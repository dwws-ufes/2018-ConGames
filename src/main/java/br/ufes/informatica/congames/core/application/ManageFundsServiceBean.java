package br.ufes.informatica.congames.core.application;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.informatica.congames.core.domain.User;
import br.ufes.informatica.congames.core.persistence.UserDAO;

@Stateless
@PermitAll
public class ManageFundsServiceBean implements ManageFundsService {
	private static final long serialVersionUID = 1L;

	@EJB
	private UserDAO userDAO;
	
	private User user;
	
	
	public double getUserFunds(long userID)
	{
		return	userDAO.retrieveById(userID).getFunds();
	}
	
	public void withdrawFunds(long userID, double qnty)
	{
		user = userDAO.retrieveById(userID);
		if(user != null)
		{
			double d = user.getFunds() - qnty;
			if(d >= 0)
			{
				user.setFunds(user.getFunds() - qnty);
				userDAO.save(user);
			}
			else
			{
				//Insufficient funds
			}
				
		}
	}

	public void addFunds(long userID, double qnty)
	{

		user = userDAO.retrieveById(userID);
		if(user != null)
		{
			user.setFunds(user.getFunds() + qnty);
			userDAO.save(user);	
		}
		
	}
}
