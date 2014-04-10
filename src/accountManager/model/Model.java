package accountManager.model;

import java.io.File;
import java.io.IOException;

import accountManager.model.account.AccountList;
import accountManager.model.account.DuplicateAccountException;
import accountManager.model.account.file.AccountFile;
import accountManager.model.account.serialize.MalformedAccountException;

/**
 * Encapsulates the database as a dumb data interface
 */
public class Model
{
	private final AccountFile file;
	private final AccountList accounts;

	public Model (File filename) throws MalformedAccountException, IOException, DuplicateAccountException
	{
		this.file = new AccountFile (filename);
		this.accounts = file.readAllAccounts ();
	}

	public AccountList getAccounts ()
	{
		return accounts;
	}

	public String getFileName ()
	{
		return file.getPathname ();
	}

	public void syncWithFile () throws IOException
	{
		file.writeAllAccounts (accounts);
	}
}
