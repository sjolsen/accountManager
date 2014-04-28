package accountManager.view.util.money;

import java.util.Observer;

import accountManager.controller.Controller;
import accountManager.model.account.Account;
import accountManager.model.account.AccountUnderflowException;
import accountManager.model.money.Money;

/**
 * Provides a UI-friendly abstract interface to money, presenting it in some currency
 */
public abstract class MoneyManager
{
	private final Controller controller;
	private final Account account;

	protected abstract double exchangeRate ();
	public abstract String currencyLongSymbol ();
	public abstract String currencyShortSymbol ();

	protected final double fromUSD (Money money)
	{
		return money.getAmount () * exchangeRate ();
	}

	protected final Money toUSD (double amount)
	{
		return new Money (amount / exchangeRate ());
	}

	public MoneyManager (Controller controller, Account account)
	{
		this.controller = controller;
		this.account = account;
	}

	public final double getAmount ()
	{
		return fromUSD (account.getMoney ());
	}

	public final void withdraw (double amount) throws AccountUnderflowException
	{
		while (!controller.withdraw (account, account.getMoney(), toUSD (amount)))
		{
		}
	}

	public final void deposit (double amount) throws AccountUnderflowException
	{
		while (!controller.deposit (account, account.getMoney(), toUSD (amount)))
		{
		}
	}

	public final void addAccountObserver (Observer o)
	{
		account.addObserver (o);
	}
}
