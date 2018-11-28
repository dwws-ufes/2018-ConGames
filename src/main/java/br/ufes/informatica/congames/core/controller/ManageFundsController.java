package br.ufes.informatica.congames.core.controller;


import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufes.informatica.congames.core.application.ManageFundsService;

@Named @SessionScoped
public class ManageFundsController implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private ManageFundsService manageFundsService;

	@Inject
	private SessionController sessionController;
	
	private int fundsInput;
	
	public double getUserFunds() throws Exception
	{
		return sessionController.getCurrentUser().getFunds();
		
	}
	
	public String addUserFunds()
	{
		manageFundsService.addFunds(sessionController.getCurrentUser().getId(), fundsInput);

		//TODO: instead of adding funds to both, try adding only to the DB and reload the currentUser
		sessionController.getCurrentUser().setFunds(sessionController.getCurrentUser().getFunds() + fundsInput);

		return null;
	}

	public String withdrawUserFunds()
	{
		double postTransactionFunds = sessionController.getCurrentUser().getFunds() - fundsInput;
		if(postTransactionFunds >= 0)
		{
			sessionController.getCurrentUser().setFunds(postTransactionFunds);
			
			//TODO: instead of adding funds to both, try adding only to the DB and reload the currentUser
			manageFundsService.withdrawFunds(sessionController.getCurrentUser().getId(), fundsInput);

		}
		return null;
	}
	
	public int getfundsInput()
	{
		return fundsInput;
	}
	public void setfundsInput(int _fundsInput)
	{
		fundsInput = _fundsInput;
	}
	
	
	
}
