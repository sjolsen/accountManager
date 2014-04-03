package accountManager.view.ui;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import accountManager.model.account.Account;
import accountManager.model.money.Money;

public class AccountCellRenderer implements ListCellRenderer <Account>
{
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

	@Override
	public Component getListCellRendererComponent (JList <? extends Account> account_list,
	                                               Account account,
	                                               int index,
	                                               boolean is_selected,
	                                               boolean has_focus)
	{
		DefaultListCellRenderer renderer = new DefaultListCellRenderer ();

		int ID = account.getID ();
		String name = formatName (account.getName ());
		String money = formatMoney (account.getMoney ());
		String display_string = String.format ("%d | %s | %s", ID, name, money);

		return renderer.getListCellRendererComponent (account_list, display_string, index, is_selected, has_focus);
	}

}
