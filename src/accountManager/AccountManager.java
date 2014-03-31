package accountManager;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import accountManager.controller.Controller;
import accountManager.model.Model;
import accountManager.model.account.Account;
import accountManager.model.account.AccountList;
import accountManager.model.account.file.AccountFile;
import accountManager.model.account.serialize.AccountSerializer;
import accountManager.model.account.serialize.MalformedAccountException;
import accountManager.view.ui.MainWindow;

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
		Model model = new Model (new File (args [0]));
		Controller controller = new Controller (model);
		new MainWindow (controller, model);
	}
	
	public static void main (String [] args)
	{
		try
                {
	                windowTest (args);
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
