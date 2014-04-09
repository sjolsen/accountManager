package accountManager.view;

import java.io.IOException;

import javax.swing.JList;
import javax.swing.JOptionPane;

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
	
	public enum Currency
	{
		USD,
		EUR,
		CNY
	}
	
	public void edit (Account account, Currency currency)
	{
		System.out.format ("Edit account %d in %s\n", account.getID (), currency.name ());
	}
	
	public void save ()
	{
		try
                {
	                controller.save ();
                }
                catch (IOException e)
                {
	                JOptionPane.showMessageDialog (null, "Failed to save the database");
                }
	}

	public void exit ()
	{
		save ();
		main_window.close ();
	}
}