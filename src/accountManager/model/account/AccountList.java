package accountManager.model.account;

import java.util.Collections;
import java.util.Vector;

public class AccountList
{
	private final Vector <Account> accounts = new Vector <Account> ();
	
	private void sort ()
	{
		Collections.sort (accounts, new Account.CompareByID ());
	}

	public void addAccount (Account account)
	{
		accounts.add (account);
		sort ();
	}

	public Vector <Account> getAccounts ()
	{
		return accounts;
	}
}
