package accountManager.controller;

import java.util.Observer;

import accountManager.model.account.Account;
import accountManager.model.account.AccountUnderflowException;
import accountManager.model.money.Money;

public class WithdrawAgent extends Agent implements Observer
{
	public WithdrawAgent (Controller controller, int id, Money step, Account account)
	{
		super (controller, id, step, account);
	}
	
	@Override
	public boolean doTransaction ()
	{
			if (account.getMoney ().minus (step).getAmount () < 0)
			{
				setState (State.blocked);
				return false;
			}
			else
			{
				try
				{
					return controller.withdraw (account, account.getMoney (), step);
				}
				catch (AccountUnderflowException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			return false;
	}
}
