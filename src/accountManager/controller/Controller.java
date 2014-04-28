package accountManager.controller;

import java.io.IOException;
import java.util.Vector;

import accountManager.model.Model;
import accountManager.model.account.Account;
import accountManager.model.account.AccountUnderflowException;
import accountManager.model.money.Money;

/**
 * Provides a programmatic interface to the account database
 */
public class Controller
{
	int id = 0;
	private final Model model;
	private final Vector <Agent> agents = new Vector <Agent> ();

	public Controller (Model model)
	{
		this.model = model;
	}

	public Model getModel ()
	{
		return model;
	}

	/**
	 * Performs an atomic withdraw operation
	 * @param account The account from which to withdraw
	 * @param old_balance The previous account balance
	 * @param amount The amount of money to withdraw
	 * @return Whether the withdrawal was successful
	 * @throws AccountUnderflowException
	 */
	public boolean withdraw (Account account, Money old_balance, Money amount) throws AccountUnderflowException
	{
		return account.casSetMoney (old_balance, old_balance.minus (amount));
	}

	/**
	 * Performs an atomic deposit operation
	 * @param account the account to which to deposit
	 * @param old_balance The previous account balance
	 * @param amount The amount of money to deposit
	 * @return Whether the deposit was successful
	 * @throws AccountUnderflowException
	 */
	public boolean deposit (Account account, Money old_balance, Money amount) throws AccountUnderflowException
	{
		return account.casSetMoney (old_balance, old_balance.plus (amount));
	}
	
	public Agent spawnWithdrawAgent (Account account, Money step)
	{
		final Agent agent = new WithdrawAgent (this, id++, step, account);
		agents.add (agent);
		
		new Thread (new Runnable () {
			@Override
			public void run ()
			{
				agent.run ();
			}
		}).start ();
		
		return agent;
	}

	public void save () throws IOException
	{
		model.syncWithFile ();
	}

	public void cleanup() {
		for (Agent a : agents)
			a.stop ();
	}
}
