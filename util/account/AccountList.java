package accountManager.util.account;

import java.util.Vector;

public class AccountList
{
	private final Vector <Account> accounts = new Vector <Account> ();
	
	public void addAccount (Account account)
	{
		accounts.add (account);
	}
	
	public Vector <Account> getAccounts ()
	{
		return accounts;
	}
}
