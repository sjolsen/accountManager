package accountManager.view.ui;

import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import accountManager.model.account.Account;
import accountManager.model.account.AccountList;

/**
 * The UI element which presents a selectable list of accounts to the user
 */
public class AccountSelector
{
	private final AccountList accounts;

	private final JTable account_table;
	private final JScrollPane scroll_pane;

	public AccountSelector (AccountList accounts)
	{
		this.accounts = accounts;

		DefaultTableCellRenderer align_left = new DefaultTableCellRenderer ();
		DefaultTableCellRenderer align_right = new DefaultTableCellRenderer ();
		align_left.setHorizontalAlignment (JLabel.LEFT);
		align_right.setHorizontalAlignment (JLabel.RIGHT);

		account_table = new JTable (new AccountTableModel (this.accounts));
		account_table.getColumnModel ().getColumn (0).setCellRenderer (align_left);
		account_table.getColumnModel ().getColumn (2).setCellRenderer (align_right);
		account_table.setRowSelectionInterval (0, 0);

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
