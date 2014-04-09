package accountManager.model.account;

import accountManager.model.money.Money;

@SuppressWarnings ("serial")
public class AccountUnderflowException extends Exception
{
	public final Money new_money;
	public final Money old_money;

	public AccountUnderflowException (Money new_money, Money old_money)
	{
		this.new_money = new_money;
		this.old_money = old_money;
	}
}
