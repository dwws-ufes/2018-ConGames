package br.ufes.informatica.congames.core.application;

import java.io.Serializable;

import javax.ejb.Local;

@Local
public interface ManageFundsService extends Serializable {
	public double getUserFunds(long userID);
	public void withdrawFunds(long userID, double qnty);
	public void addFunds(long userID, double qnty);

}
