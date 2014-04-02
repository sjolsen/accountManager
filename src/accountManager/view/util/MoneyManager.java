package accountManager.view.util;

import java.util.Observer;

import accountManager.controller.Controller;
import accountManager.model.account.Account;
import accountManager.model.money.Money;

public abstract class MoneyManager
{
	private final Controller controller;
	private final Account account;
	
	protected abstract double exchangeRate ();
	public abstract String currencyLongSymbol ();
	public abstract String currencyShortSymbol ();
	
	protected double fromUSD (Money money)
	{
		return money.getAmount () * exchangeRate ();
	}
	
	protected Money toUSD (double amount)
	{
		return new Money (amount / exchangeRate ());
	}

	public MoneyManager (Controller controller, Account account)
	{
		this.controller = controller;
		this.account = account;
	}
	
	public void addAccountObserver (Observer o)
	{
		account.addObserver (o);
	}
	
	public double getAmount ()
	{
		return fromUSD (account.getMoney ());
	}
	
	public void withdraw (double amount)
	{
		controller.withdraw (account, toUSD (amount));
	}
	
	public void deposit (double amount)
	{
		controller.deposit (account, toUSD (amount));
	}
	
}
