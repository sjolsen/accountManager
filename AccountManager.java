package accountManager;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import accountManager.util.account.Account;
import accountManager.util.account.AccountList;
import accountManager.util.account.file.AccountFile;
import accountManager.util.account.serialize.AccountSerializer;
import accountManager.util.account.serialize.MalformedAccountException;

public class AccountManager
{
	public static void test (String [] args) throws MalformedAccountException, IOException
	{
		AccountFile file = new AccountFile (new File (args [0]));
		AccountList list = file.readAllAccounts ();
		
		for (Account account : list.getAccounts ())
			AccountSerializer.serialize (account, new PrintWriter (System.out));
	}
	
	public static void main (String [] args)
	{
		try
                {
	                test (args);
                }
                catch (MalformedAccountException e)
                {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
                }
                catch (IOException e)
                {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
                }
	}
}
