package accountManager.util.account;

import accountManager.util.money.Money;

public class LineNumberedAccount extends Account
{
	private final int line_number;

	public LineNumberedAccount (int line_number, int ID, String name, Money money)
	{
		super (ID, name, money);
		this.line_number = line_number;
	}

	public int getLineNumber ()
	{
		return line_number;
	}
	
}
