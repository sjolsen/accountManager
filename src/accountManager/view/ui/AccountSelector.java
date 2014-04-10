package accountManager.view.ui;

import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import accountManager.model.account.Account;
import accountManager.model.account.AccountList;

public class AccountSelector
{
	private final AccountList accounts;

	private final JTable account_table;
	private final JScrollPane scroll_pane;

	public AccountSelector (AccountList accounts)
	{
		this.accounts = accounts;

		account_table = new JTable (new AccountTableModel (this.accounts));
		scroll_pane = new JScrollPane ();
		scroll_pane.setViewportView (account_table);
	}

	public JComponent getJComponent ()
	{
		return scroll_pane;
	}

	public Vector <Account> getSelectedAccounts ()
	{
		Vector <Account> selection = new Vector <Account> ();
		for (int index : account_table.getSelectedRows ())
			selection.add (accounts.getElementAt (index));
		return selection;
	}
}
