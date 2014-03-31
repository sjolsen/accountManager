package accountManager.controller;

import java.io.IOException;

import accountManager.model.Model;
import accountManager.model.account.Account;
import accountManager.model.money.Money;

public class Controller
{
	private Model model;
	
	public Controller (Model model)
	{
		this.model = model;
	}

	public void withdraw (Account account, Money amount)
	{
		account.setMoney (account.getMoney ().minus (amount));
	}

	public void deposit (Account account, Money amount)
	{
		account.setMoney (account.getMoney ().plus (amount));
	}

	public void save ()
	{
		try
                {
	                model.syncWithFile ();
                }
                catch (IOException e)
                {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
                }
	}

	public void exit ()
	{
	}
}
