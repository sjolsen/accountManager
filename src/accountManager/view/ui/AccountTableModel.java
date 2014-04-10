package accountManager.view.ui;

import java.util.Vector;

import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import accountManager.model.account.Account;
import accountManager.model.account.AccountList;
import accountManager.model.money.Money;

/**
 * Responsible for interfacing Swing's table abstraction with the database
 */
public class AccountTableModel implements TableModel
{
	private final Vector <TableModelListener> listeners;
	private final AccountList account_list;

	private static String formatName (String name)
	{
		name = name.replaceAll ("^\\s+|\\s+$", "");
		name = name.replaceAll ("\\s+", " ");
		name = name.replaceFirst (" ", ", ");
		return name;
	}

	private static String formatMoney (Money money)
	{
		return String.format ("$%.2f", money.getAmount ());
	}

	public AccountTableModel (AccountList account_list)
	{
		this.listeners = new Vector <TableModelListener> ();
		this.account_list = account_list;

		this.account_list.addListDataListener (new ListDataListener () {
			@Override
			public void contentsChanged (ListDataEvent arg0)
			{
				TableModelEvent e = new TableModelEvent (AccountTableModel.this, arg0.getIndex0 (), arg0.getIndex1 ());
				for (TableModelListener listener : listeners)
					listener.tableChanged (e);
			}

			@Override
			public void intervalAdded (ListDataEvent arg0)
			{
				contentsChanged (arg0);
			}

			@Override
			public void intervalRemoved (ListDataEvent arg0)
			{
				contentsChanged (arg0);
			}
		});
	}

	@Override
	public void addTableModelListener (TableModelListener arg0)
	{
		listeners.add (arg0);
	}

	@Override
	public Class <?> getColumnClass (int column)
	{
		switch (column)
		{
		case 0: return Integer.class;
		case 1: return String.class;
		case 2: return String.class;
		default: return null;
		}
	}

	@Override
	public int getColumnCount ()
	{
		return 3;
	}

	@Override
	public String getColumnName (int column)
	{
		switch (column)
		{
		case 0: return "ID";
		case 1: return "Name";
		case 2: return "Balance";
		default: return null;
		}
	}

	@Override
	public int getRowCount ()
	{
		return account_list.getSize ();
	}

	@Override
	public Object getValueAt (int row, int column)
	{
		Account account = account_list.getElementAt (row);

		switch (column)
		{
		case 0: return account.getID ();
		case 1: return formatName (account.getName ());
		case 2: return formatMoney (account.getMoney ());
		default: return null;
		}
	}

	@Override
	public boolean isCellEditable (int arg0, int arg1)
	{
		return false;
	}

	@Override
	public void removeTableModelListener (TableModelListener listener)
	{
		listeners.remove (listener);
	}

	@Override
	public void setValueAt (Object arg0, int arg1, int arg2)
	{
		return;
	}
}
