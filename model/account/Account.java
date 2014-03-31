package accountManager.model.account;

import java.util.Comparator;

import accountManager.util.money.Money;

public class Account
{
	private final int ID;
	private final String name;
	private Money money;

	public Account (int ID, String name, Money money)
	{
		this.ID = ID;
		this.name = name;
		this.money = money;
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
