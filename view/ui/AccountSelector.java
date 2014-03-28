package accountManager.view.ui;

import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JScrollPane;

import accountManager.model.account.Account;
import accountManager.model.account.AccountList;

public class AccountSelector
{
	private JScrollPane scroll_pane;
	private JList <Account> account_list;

	public AccountSelector (AccountList accounts)
	{
		scroll_pane = new JScrollPane ();
		
		account_list = new JList <Account> (accounts.getAccounts ());
		account_list.setCellRenderer (new AccountCellRenderer ());
 		scroll_pane.setViewportView (account_list);
	}
	
	public JComponent getJComponent ()
	{
		return scroll_pane;
	}

	public JList <Account> getAccountList ()
	{
		return account_list;
	}
}
