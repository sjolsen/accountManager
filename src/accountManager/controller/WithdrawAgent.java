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
	
	public void run ()
	{
		setState (State.running);
		while (true)
		{
			if (state == State.stopped)
				return;
			if (account.getMoney ().minus (step).getAmount () < 0)
			{
				setState (State.blocked);
				synchronized (this)
				{
					try 
					{
						this.wait ();
					}
					catch (InterruptedException e) {}
				}
			}
			else
			{
				try {
					if (controller.withdraw (account, account.getMoney (), step))
					{
						synchronized (this)
						{
							++operations_performed;
							amount_transferred = amount_transferred.plus (step);
						}
						try {
							Thread.sleep (1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} catch (AccountUnderflowException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
