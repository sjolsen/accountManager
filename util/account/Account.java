package accountManager.util.account;

import accountManager.util.money.*;

public class Account
{
	private int ID;
	private String name;
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
}
