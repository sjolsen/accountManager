package accountManager.util.account;

import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

public class AccountList
{
	private final SortedMap <Integer, Account> accounts = new TreeMap <Integer, Account> ();
	
	public void addAccount (Account account)
	{
		accounts.put (account.getID (), account);
	}
	
	public Account getAccountByID (int ID)
	{
		return accounts.get (ID);
	}
	
	public Collection <Account> getAccounts ()
	{
		return accounts.values ();
	}
}
