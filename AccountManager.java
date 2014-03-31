package accountManager;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.UIManager;

import accountManager.controller.Controller;
import accountManager.model.account.Account;
import accountManager.model.account.AccountList;
import accountManager.model.account.file.AccountFile;
import accountManager.model.account.serialize.AccountSerializer;
import accountManager.model.account.serialize.MalformedAccountException;

public class AccountManager
{
	public static void test (String [] args) throws MalformedAccountException, IOException
	{
		AccountFile file = new AccountFile (new File (args [0]));
		AccountList list = file.readAllAccounts ();
		
		for (Account account : list.getAccounts ())
			AccountSerializer.serialize (account, new PrintWriter (System.out));
	}
	
	public static void windowTest (String [] args) throws MalformedAccountException, IOException
	{
		Controller controller = new Controller (new File (args [0]));
	}
	
	public static void main (String [] args)
	{
		try
                {
	                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	                windowTest (args);
                }
                catch (Exception e)
                {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
                }
	}
}
