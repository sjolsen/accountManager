package accountManager.view.ui;

import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import accountManager.model.account.Account;
import accountManager.view.View;
import accountManager.view.util.money.Currency;
import accountManager.view.util.money.MoneyManager;

@SuppressWarnings ("serial")
public class EditWindow extends Window
{
	private final Account account;
	private final Currency currency;
	private final MoneyManager manager;

	private JButton deposit_button;
	private JButton withdraw_button;

	private JPanel panel;

	public EditWindow (View view, Account account, Currency currency)
	{
		super ("Dummy edit window", view);
		this.account = account;
		this.currency = currency;
		this.manager = currency.makeManager (view.getController (), account);

		deposit_button = new CallbackButton ("Deposit") {
			@Override
			public void onClicked ()
			{
				manager.deposit (10);
			}
		};

		withdraw_button = new CallbackButton ("Withdraw") {
			@Override
			public void onClicked ()
			{
				manager.withdraw (10);
			}
		};

		buildUI ();
	}

	private void buildUI ()
	{
		panel = new JPanel ();
		panel.setLayout (new BoxLayout (panel, BoxLayout.X_AXIS));
		panel.setBorder (BorderFactory.createEmptyBorder (BORDER, BORDER, BORDER, BORDER));

		panel.add (withdraw_button);
		panel.add (Box.createRigidArea (new Dimension (BORDER, 0)));
		panel.add (deposit_button);

		getContentPane ().add (panel);
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
