package accountManager.view.util;

import java.util.HashMap;
import java.util.Map;

import accountManager.model.account.Account;
import accountManager.view.View;
import accountManager.view.ui.EditWindow;
import accountManager.view.util.money.Currency;

/**
 * Responsible for maintaining handles to open edit windows, ensuring uniqueness for a given account/currency combination
 */
public class EditWindowMap
{
	private static class Key
	{
		private final Account account;
		private final Currency currency;

		public Key (Account account, Currency currency)
		{
			this.account = account;
			this.currency = currency;
		}

		@Override
		public boolean equals (Object other)
		{
			Key other_key = (Key) other;
			return account == other_key.account && currency == other_key.currency;
		}

		@Override
		public int hashCode ()
		{
			return account.hashCode () + currency.ordinal ();
		}
	}

	private final View view;
	private final Map <Key, EditWindow> windows = new HashMap <Key, EditWindow> ();

	public EditWindowMap (View view)
	{
		this.view = view;
	}

	public void raiseWindow (Account account, Currency currency)
	{
		Key key = new Key (account, currency);
		EditWindow window;

		if (!windows.containsKey (key))
		{
			window = new EditWindow (view, account, currency);
			windows.put (key, window);
		}
		else
			window = windows.get (key);

		window.toFront ();
	}

	public void closeWindow (Account account, Currency currency)
	{
		Key key = new Key (account, currency);

		if (windows.containsKey (key))
		{
			EditWindow window = windows.get (key);
			windows.remove (key);
			window.cleanup ();
		}
	}

	public void closeWindow (EditWindow window)
	{
		closeWindow (window.getAccount (), window.getCurrency ());
	}

	public void closeAllWindows ()
	{
		for (EditWindow window : windows.values ())
			window.cleanup ();
		windows.clear ();
	}
}
