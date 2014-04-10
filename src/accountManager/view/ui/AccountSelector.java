package accountManager.view.ui;

import java.util.List;

import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JScrollPane;

import accountManager.model.account.Account;

public class AccountSelector
{
	private final JScrollPane scroll_pane;
	private final JList <Account> account_list;

	public AccountSelector (JList <Account> accounts)
	{
		scroll_pane = new JScrollPane ();

		account_list = accounts;
		account_list.setSelectedIndex (0);

		account_list.setCellRenderer (new AccountCellRenderer ());
		scroll_pane.setViewportView (account_list);
	}

	public JComponent getJComponent ()
	{
		return scroll_pane;
	}

	public List <Account> getSelectedAccounts ()
	{
		return account_list.getSelectedValuesList ();
	}
}
