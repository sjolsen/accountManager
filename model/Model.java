package accountManager.model;

import java.io.File;
import java.io.IOException;

import accountManager.model.account.AccountList;
import accountManager.model.account.file.AccountFile;
import accountManager.model.account.serialize.MalformedAccountException;

public class Model
{
	private AccountFile file;
	private AccountList accounts;

	public Model (File filename) throws MalformedAccountException, IOException
	{
		this.file = new AccountFile (filename);
		this.accounts = file.readAllAccounts ();
	}
	
	public AccountFile getFile ()
	{
		return file;
	}
	
	public AccountList getAccounts ()
	{
		return accounts;
	}

	public void cleanup ()
        {
	        // TODO Auto-generated method stub
        }
}
