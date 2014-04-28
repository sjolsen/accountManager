package accountManager.controller;

import java.util.Observable;
import java.util.Observer;

import accountManager.model.account.Account;
import accountManager.model.account.AccountUnderflowException;
import accountManager.model.money.Money;

public class WithdrawAgent implements Observer
{
	final Controller controller;
	final int id;
	final Money step;
	final Account account;
	int operations_performed;
	Money amount_transferred;
	volatile State state;
	
	public enum State
	{
		running,
		blocked, 
		stopped
	}
	
	private void setState (State state)
	{
		this.state = state;
		System.err.format ("Agent %d entering state %s\n", id, state.name ());
	}
	
	public WithdrawAgent (Controller controller, int id, Money step, Account account)
	{
		this.controller = controller;
		this.id = id;
		this.step = step;
		this.account = account;
		account.addObserver (this);
		
		operations_performed = 0;
		amount_transferred = new Money (0);
		state = State.stopped;
	}
	
	public void run () throws AccountUnderflowException
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
			}
		}
	}
	
	public void stop ()
	{
		setState (State.stopped);
	}

	@Override
	public void update(Observable arg0, Object arg1)
	{
		if (state == State.blocked)
		{
			setState (State.running);
			this.notifyAll ();
		}
	}
}
