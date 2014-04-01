package accountManager.model.account.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import accountManager.model.account.Account;
import accountManager.model.account.AccountList;
import accountManager.model.account.DuplicateAccountException;
import accountManager.model.account.serialize.AccountSerializer;
import accountManager.model.account.serialize.MalformedAccountException;

public class AccountFile
{
	private final File filename;
	private final String pathname;
	
	public AccountFile (File filename) throws IOException
	{
		this.filename = filename;
		this.pathname = filename.getCanonicalPath ();
	}
	
	public String getPathname ()
	{
		return pathname;
	}
	
	public AccountList readAllAccounts () throws MalformedAccountException, IOException, DuplicateAccountException
	{
		AccountList accounts = new AccountList ();
		BufferedReader reader = new BufferedReader (new FileReader (filename));
		
		Account account;
		while ((account = AccountSerializer.deserialize (reader)) != null)
       			accounts.addAccount (account);
        	
		return accounts;
	}
	
	public void writeAllAccounts (AccountList accounts) throws IOException
	{
		PrintWriter writer = new PrintWriter (new FileWriter (filename));
		
		for (Account account : accounts)
			AccountSerializer.serialize (account, writer);
	}
}
