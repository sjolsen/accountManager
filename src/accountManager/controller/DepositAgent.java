package accountManager.controller;

import accountManager.model.account.Account;
import accountManager.model.money.Money;

public class DepositAgent extends Agent
{
	public DepositAgent (Controller controller, int id, Money step, Account account) 
	{
		super (controller, id, step, account);
	}

	@Override
	public boolean doTransaction () 
	{
		return controller.deposit (account, account.getMoney (), step);
	}

}
