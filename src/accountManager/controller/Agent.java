package accountManager.controller;

import java.util.Observable;
import java.util.Observer;

import accountManager.model.account.Account;
import accountManager.model.money.Money;

public abstract class Agent implements Observer {

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

	protected void setState(State state) {
		this.state = state;
		System.err.format ("Agent %d entering state %s\n", id, state.name ());
	}

	public void stop() {
		setState (State.stopped);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if (state == State.blocked)
		{
			setState (State.running);
			this.notifyAll ();
		}
	}

	public abstract void run ();

}