package accountManager.model.account;

import java.util.Comparator;
import java.util.Observable;

import accountManager.model.money.Money;

/**
 * Represents an account and its attributes, satisfying the observer pattern
 */
public class Account extends Observable
{
	private final int ID;
	private final String name;
	private Money money;

	public Account (int ID, String name, Money money) throws AccountUnderflowException
	{
		this.ID = ID;
		this.name = name;
		this.money = new Money (0);
		while (!casSetMoney (this.money, money))
		{
		}
	}

	public int getID ()
	{
		return ID;
	}

	public String getName ()
	{
		return name;
	}

	public Money getMoney ()
	{
		return money;
	}

	public boolean casSetMoney (Money old_money, Money new_money) throws AccountUnderflowException
	{
		synchronized (this)
		{
			if (old_money.getAmount() != this.money.getAmount())
				return false;
			if (new_money.getAmount () < 0)
				throw new AccountUnderflowException (new_money, this.money);
			this.money = new_money;
		}

		setChanged ();
		notifyObservers ();
		return true;
	}

	public static class CompareByID implements Comparator <Account>
	{
		@Override
		public int compare (Account a, Account b)
		{
			return a.getID () - b.getID ();
		}
	}
}
