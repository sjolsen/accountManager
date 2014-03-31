package accountManager.model.account;

import java.util.Comparator;
import java.util.Observable;

import accountManager.model.money.Money;

public class Account extends Observable
{
	private final int ID;
	private final String name;
	private Money money;

	public Account (int ID, String name, Money money) throws AccountUnderflowException
	{
		this.ID = ID;
		this.name = name;
		setMoney (money);
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

	public void setMoney (Money money)
	{
		if (money.getAmount () < 0)
			throw new AccountUnderflowException (money, this.money);
		this.money = money;
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
