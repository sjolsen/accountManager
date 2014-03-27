package accountManager.util.account;

import accountManager.util.money.*;

public class Account
{
	private String name;
	private int ID;
	private Money money;
	
	Account (String name, int ID, Money money)
	{
		this.name = name;
		this.ID = ID;
		this.money = money;
	}
	
	public String getName ()
	{
		return name;
	}
	
	public int getID ()
	{
		return ID;
	}
	
	public Money getMoney ()
	{
		return money;
	}
	
	public void setMoney (Money money)
	{
		this.money = money;
	}
}
