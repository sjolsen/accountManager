package accountManager.util.account.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import accountManager.util.account.Account;
import accountManager.util.account.AccountList;
import accountManager.util.account.serialize.AccountSerializer;
import accountManager.util.account.serialize.MalformedAccountException;

public class AccountFile
{
	private final File filename;
	
	public AccountFile (File filename)
	{
		this.filename = filename;
	}
	
	public AccountList readAllAccounts () throws MalformedAccountException, IOException
	{
		AccountList accounts = new AccountList ();
		BufferedReader reader = new BufferedReader (new FileReader (filename));
		
        	try
        	{
        		while (true)
        			accounts.addAccount (AccountSerializer.deserialize (reader));
        	}
        	catch (IOException e) // End of file
        	{
        	}
				
		return accounts;
	}
	
	public void writeAllAccounts (AccountList accounts) throws IOException
	{
		PrintWriter writer = new PrintWriter (new FileWriter (filename));
		
		for (Account account : accounts.getAccounts ())
			AccountSerializer.serialize (account, writer);
	}
}
