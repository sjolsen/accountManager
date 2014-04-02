package accountManager.model.account;

import java.util.Collections;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

public class AccountList implements Observer, ListModel <Account>, Iterable <Account>
{
	private final Vector <Account> accounts = new Vector <Account> ();
	private final Vector <ListDataListener> listeners = new Vector <ListDataListener> ();
	
	private void notifyListeners (int type, int begin, int end)
	{
		ListDataEvent event = new ListDataEvent (this, type, begin, end);
		for (ListDataListener listener : listeners)
			listener.contentsChanged (event);
	}

	public void addAccount (Account account) throws DuplicateAccountException
	{
		int index = Collections.binarySearch (accounts, account, new Account.CompareByID ());
		if (index >= 0) // Account already exists
			throw new DuplicateAccountException (account, accounts.elementAt (index));
		index = -(index + 1); // binarySearch returns -index - 1
		
		account.addObserver (this);
		accounts.add (index, account);
		notifyListeners (ListDataEvent.INTERVAL_ADDED, index, index + 1);
	}

	@Override
	public void update (Observable o, Object arg)
	{
		Account account = (Account) o;
		int index = accounts.indexOf (account);
		notifyListeners (ListDataEvent.CONTENTS_CHANGED, index, index + 1);
	}

	@Override
	public int getSize ()
	{
		return accounts.size ();
	}

	@Override
	public Account getElementAt (int index)
	{
		return accounts.elementAt (index);
	}

	@Override
	public void addListDataListener (ListDataListener l)
	{
		listeners.add (l);
	}

	@Override
	public void removeListDataListener (ListDataListener l)
	{
		listeners.remove (l);
	}

	@Override
        public Iterator <Account> iterator ()
        {
	        return accounts.iterator ();
        }
}
