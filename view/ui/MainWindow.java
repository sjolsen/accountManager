package accountManager.view.ui;

import javax.swing.JFrame;
import javax.swing.JList;

import accountManager.model.account.Account;
import accountManager.model.account.AccountList;

public class MainWindow extends JFrame
{
	private JList <Account> account_list;

	public MainWindow (AccountList accounts)
	{
		
		super ("Account Manager");

		this.account_list = new JList <Account> (accounts.getAccounts ());
 		getContentPane ().add (account_list);

		setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		pack ();
		setVisible (true);
	}
}
