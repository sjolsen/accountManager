package accountManager.view;

import java.io.IOException;

import javax.swing.JList;

import accountManager.controller.Controller;
import accountManager.model.account.Account;
import accountManager.view.ui.MainWindow;

public class View
{
	private final Controller controller;
	private JList <Account> account_list;
	
	private MainWindow main_window;
	
	public View (Controller controller)
	{
		this.controller = controller;
		this.account_list = new JList <Account> (controller.getModel ().getAccounts ());
		this.main_window = new MainWindow (this, controller.getModel ().getFileName ());
	}
	
	public JList <Account> getAccounts ()
	{
		return account_list;
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

	public void exit ()
	{
		save ();
		main_window.close ();
	}
}