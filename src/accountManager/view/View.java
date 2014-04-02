package accountManager.view;

import java.io.IOException;

import javax.swing.JList;

import accountManager.controller.Controller;
import accountManager.model.account.Account;
import accountManager.view.ui.EditWindow;
import accountManager.view.ui.MainWindow;
import accountManager.view.ui.Window;
import accountManager.view.util.EditWindowMap;
import accountManager.view.util.money.Currency;

public class View
{
	private final Controller controller;
	private final JList <Account> account_list;
	
	private final MainWindow main_window;
	private final EditWindowMap edit_windows;
	
	public View (Controller controller)
	{
		this.controller = controller;
		this.account_list = new JList <Account> (controller.getModel ().getAccounts ());
		this.main_window = new MainWindow (this, controller.getModel ().getFileName ());
		this.edit_windows = new EditWindowMap (this);
	}
	
	public Controller getController ()
	{
		return controller;
	}
	
	public JList <Account> getAccounts ()
	{
		return account_list;
	}
	
	public void edit (Account account, Currency currency)
	{
		edit_windows.raiseWindow (account, currency);
	}
	
	public void save ()
	{
		try
                {
	                controller.save ();
                }
                catch (IOException e)
                {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
                }
	}
	
	public void close (Window window)
	{
		if (!window.isDisplayable ())
			return;
		if (window == main_window)
		{
			main_window.cleanup ();
			exit ();
		}
		else if (window instanceof EditWindow)
			edit_windows.closeWindow ((EditWindow) window);
	}

	public void exit ()
	{
		save ();
		close (main_window);
		edit_windows.closeAllWindows ();
	}
}