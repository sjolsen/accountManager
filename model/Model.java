package accountManager.model;

import java.io.File;
import java.io.IOException;

import accountManager.util.account.AccountList;
import accountManager.util.account.file.AccountFile;
import accountManager.util.account.serialize.MalformedAccountException;

public class Model
{
	private AccountFile file;
	private AccountList accounts;

	public Model (File filename) throws MalformedAccountException, IOException
	{
		this.file = new AccountFile (filename);
		this.accounts = file.readAllAccounts ();
	}
	
	public AccountList getAccounts ()
	{
		return accounts;
	}
}
