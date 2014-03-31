package accountManager.model;

import java.io.File;
import java.io.IOException;

import accountManager.model.account.AccountList;
import accountManager.model.account.DuplicateAccountException;
import accountManager.model.account.file.AccountFile;
import accountManager.model.account.serialize.MalformedAccountException;

public class Model
{
	private AccountFile file;
	private AccountList accounts;

	public Model (File filename) throws MalformedAccountException, IOException, DuplicateAccountException
	{
		this.file = new AccountFile (filename);
		this.accounts = file.readAllAccounts ();
	}

	public AccountList getAccounts ()
	{
		return accounts;
	}

	public void syncWithFile () throws IOException
        {
	        file.writeAllAccounts (accounts);
        }
}
