package accountManager;

import java.io.File;

import javax.swing.UIManager;

import accountManager.controller.Controller;
import accountManager.model.Model;
import accountManager.model.account.Account;
import accountManager.model.money.USDMoney;

public class AccountManager
{
	public static void windowTest (File file)
	{
                try
                {
	                UIManager.setLookAndFeel (UIManager.getSystemLookAndFeelClassName ());
			Model model = new Model (file);
			Controller controller = new Controller (model);
                }
                catch (Exception e)
                {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
                }
	}
	
	public static void depositTest (File file)
	{
		try
                {
	                Model model = new Model (file);
			Controller controller = new Controller (model);
			
			for (Account account : model.getAccounts ())
				controller.deposit (account, new USDMoney (10));
			controller.save ();
                }
                catch (Exception e)
                {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
                }
	}
	
	public static void withdrawTest (File file)
	{
		try
                {
	                Model model = new Model (file);
			Controller controller = new Controller (model);
			
			for (Account account : model.getAccounts ())
				controller.withdraw (account, new USDMoney (10));
			controller.save ();
                }
                catch (Exception e)
                {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
                }
	}
	
	public static void main (String [] args)
	{
		if (args.length < 1)
		{
			System.err.println ("Must specify an account file");
			return;
		}
		
		withdrawTest (new File (args [0]));
	}
}
