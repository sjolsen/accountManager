package accountManager.view.ui;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import accountManager.model.account.Account;
import accountManager.model.account.AccountUnderflowException;
import accountManager.view.View;
import accountManager.view.util.money.Currency;
import accountManager.view.util.money.MoneyManager;

@SuppressWarnings ("serial")
public class EditWindow extends Window
{
	private final Account account;
	private final Currency currency;
	private final MoneyManager manager;

	private final JLabel balance_label;
	private final JLabel balance_symbol;
	private final JTextField balance_field;
	private final JLabel diff_symbol;
	private final JTextField diff_field;
	private final JButton deposit_button;
	private final JButton withdraw_button;
	private final JButton dismiss_button;

	private JPanel panel;
	private JPanel balance_panel;
	private JPanel diff_panel;
	private JPanel withdraw_deposit_panel;
	private JPanel dismiss_panel;

	public EditWindow (View view, Account account, Currency currency)
	{
		super ("", view);
		this.account = account;
		this.currency = currency;
		this.manager = currency.makeManager (view.getController (), account);
		super.setTitle (String.format ("%s - %d - %s", account.getName (), account.getID (), manager.currencyLongSymbol ()));

		balance_label = new JLabel ("Current balance");
		balance_symbol = new JLabel (manager.currencyShortSymbol ());
		balance_field = new JTextField (String.format ("%.2f", manager.getAmount ()));
		balance_field.setEditable (false);
		balance_field.setHorizontalAlignment (SwingConstants.RIGHT);
		manager.addAccountObserver (new Observer () {
			@Override
			public void update (Observable o, Object arg)
			{
				balance_field.setText (String.format ("%.2f", manager.getAmount ()));
			}
		});

		diff_symbol = new JLabel (manager.currencyShortSymbol ());
		diff_field = new JTextField ("0.00");
		diff_field.setHorizontalAlignment (SwingConstants.RIGHT);

		deposit_button = new CallbackButton ("Deposit") {
			@Override
			public void onClicked ()
			{
				try
				{
					Double amount = Double.parseDouble (diff_field.getText ());
					if (amount < 0)
						JOptionPane.showMessageDialog (EditWindow.this, "Cannot deposit a negative amount");
					manager.deposit (amount);
				}
				catch (AccountUnderflowException e)
				{
					JOptionPane.showMessageDialog (EditWindow.this, "Cannot deposit that much");
				}
				catch (NumberFormatException e)
				{
					JOptionPane.showMessageDialog (EditWindow.this, String.format ("\"%s\" is not a valid amount", diff_field.getText ()));
				}
			}
		};

		withdraw_button = new CallbackButton ("Withdraw") {
			@Override
			public void onClicked ()
			{
				try
				{
					Double amount = Double.parseDouble (diff_field.getText ());
					if (amount < 0)
						JOptionPane.showMessageDialog (EditWindow.this, "Cannot withdraw a negative amount");
					manager.withdraw (amount);
				}
				catch (AccountUnderflowException e)
				{
					JOptionPane.showMessageDialog (EditWindow.this, "Cannot withdraw that much");
				}
				catch (NumberFormatException e)
				{
					JOptionPane.showMessageDialog (EditWindow.this, String.format ("\"%s\" is not a valid amount", diff_field.getText ()));
				}
			}
		};

		dismiss_button = new CallbackButton ("Dismiss") {
			@Override
			public void onClicked ()
			{
				close ();
			}
		};

		buildUI ();
	}

	private void buildUI ()
	{
		balance_panel = new JPanel ();
		balance_panel.setLayout (new BoxLayout (balance_panel, BoxLayout.X_AXIS));
		balance_panel.add (balance_symbol);
		balance_panel.add (Box.createRigidArea (new Dimension(BORDER, 0)));
		balance_panel.add (balance_field);

		diff_panel = new JPanel ();
		diff_panel.setLayout (new BoxLayout (diff_panel, BoxLayout.X_AXIS));
		diff_panel.add (diff_symbol);
		diff_panel.add (Box.createRigidArea (new Dimension(BORDER, 0)));
		diff_panel.add (diff_field);

		withdraw_deposit_panel = new JPanel ();
		withdraw_deposit_panel.add (withdraw_button);
		withdraw_deposit_panel.add (Box.createRigidArea (new Dimension (BORDER, 0)));
		withdraw_deposit_panel.add (deposit_button);

		dismiss_panel = new JPanel ();
		dismiss_panel.setLayout (new BoxLayout (dismiss_panel, BoxLayout.X_AXIS));
		dismiss_panel.add (Box.createHorizontalGlue ());
		dismiss_panel.add (dismiss_button);

		panel = new JPanel ();
		panel.setLayout (new BoxLayout (panel, BoxLayout.Y_AXIS));
		panel.setBorder (BorderFactory.createEmptyBorder (BORDER, BORDER, BORDER, BORDER));
		panel.add (balance_label);
		panel.add (balance_panel);
		panel.add (diff_panel);
		panel.add (withdraw_deposit_panel);
		panel.add (dismiss_panel);

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
