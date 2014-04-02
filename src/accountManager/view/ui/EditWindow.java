package accountManager.view.ui;

import java.awt.event.WindowEvent;

import accountManager.model.account.Account;
import accountManager.view.View;
import accountManager.view.util.money.Currency;

@SuppressWarnings ("serial")
public class EditWindow extends Window
{
	private final Account account;
	private final Currency currency;
	
	public EditWindow (View view, Account account, Currency currency)
        {
		super ("Dummy edit window", view);
		this.account = account;
		this.currency = currency;
		
		getContentPane ().add (new CallbackButton ("Dummy") {
			@Override
			public void onClicked ()
			{
				System.out.println ("Dummy");
			}
		});
		
		addWindowListener (new NullWindowListener () {
			@Override
			public void windowClosing (WindowEvent e)
			{
				close ();
			}
		});

		pack ();
		setVisible (true);
        }
	
	public Account getAccount ()
	{
		return account;
	}
	
	public Currency getCurrency ()
	{
		return currency;
	}
}
