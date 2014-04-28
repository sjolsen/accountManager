package accountManager.controller;

import java.util.Observable;
import java.util.Observer;

import accountManager.model.account.Account;
import accountManager.model.money.Money;

public abstract class Agent implements Observer
{
	protected final Controller controller;
	protected final int id;
	protected final Money step;
	protected final Account account;
	protected int operations_performed;
	protected Money amount_transferred;
	protected volatile State state;

	public enum State
	{
		running,
		blocked, 
		stopped
	}

	public Agent (Controller controller, int id, Money step, Account account)
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

	protected void setState(State state) 
	{
		synchronized (this)
		{
			State oldstate = this.state;
			this.state = state;
			if (oldstate == State.blocked)
				this.notifyAll ();
		}
		System.err.format ("Agent %d entering state %s\n", id, state.name ());
	}

	public void stop() {
		setState (State.stopped);
	}

	@Override
	public void update(Observable arg0, Object arg1)
	{
		if (state == State.blocked)
			setState (State.running);
	}
	
	public abstract boolean doTransaction ();

	public void run ()
	{
		setState (State.running);
		while (true)
		{
			if (state == State.stopped)
				return;
			if (doTransaction ())
			{
				synchronized (this)
				{
					++operations_performed;
					amount_transferred = amount_transferred.plus (step);
				}
				try
				{
					Thread.sleep (1000);
				}
				catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
			else if (state == State.blocked)
			{
				synchronized (this)
				{
					try 
					{
						this.wait ();
					}
					catch (InterruptedException e) 
					{
					}
				}
			}
		}
	}
}