package accountManager.view.ui;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

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

	private final JLabel field_label;
	private final JTextField amount_field;
	private final JButton deposit_button;
	private final JButton withdraw_button;
	private final JButton dismiss_button;

	private JPanel panel;
	private JPanel text_panel;
	private JPanel edit_panel;
	private JPanel dismiss_panel;

	public EditWindow (View view, Account account, Currency currency)
	{
		super ("Dummy edit window", view);
		this.account = account;
		this.currency = currency;
		this.manager = currency.makeManager (view.getController (), account);

		field_label = new JLabel (manager.currencyShortSymbol ());
		amount_field = new JTextField ("0.00");
		amount_field.setHorizontalAlignment (JTextField.RIGHT);

		deposit_button = new CallbackButton ("Deposit") {
			@Override
			public void onClicked ()
			{
				try
				{
					manager.deposit (Double.parseDouble (amount_field.getText ()));
				}
				catch (AccountUnderflowException e)
				{
					JOptionPane.showMessageDialog (EditWindow.this, "Cannot deposit that much");
				}
				catch (NumberFormatException e)
				{
					JOptionPane.showMessageDialog (EditWindow.this, String.format ("\"%s\" is not a valid amount", amount_field.getText ()));
				}
			}
		};

		withdraw_button = new CallbackButton ("Withdraw") {
			@Override
			public void onClicked ()
			{
				try
				{
					manager.withdraw (Double.parseDouble (amount_field.getText ()));
				}
				catch (AccountUnderflowException e)
				{
					JOptionPane.showMessageDialog (EditWindow.this, "Cannot withdraw that much");
				}
				catch (NumberFormatException e)
				{
					JOptionPane.showMessageDialog (EditWindow.this, String.format ("\"%s\" is not a valid amount", amount_field.getText ()));
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
		text_panel = new JPanel ();
		text_panel.setLayout (new BoxLayout (text_panel, BoxLayout.X_AXIS));
		text_panel.add (field_label);
		text_panel.add (Box.createRigidArea (new Dimension(BORDER, 0)));
		text_panel.add (amount_field);
		
		edit_panel = new JPanel ();
		edit_panel.add (withdraw_button);
		edit_panel.add (Box.createRigidArea (new Dimension (BORDER, 0)));
		edit_panel.add (deposit_button);
		
		dismiss_panel = new JPanel ();
		dismiss_panel.setLayout (new BoxLayout (dismiss_panel, BoxLayout.X_AXIS));
		dismiss_panel.add (Box.createHorizontalGlue ());
		dismiss_panel.add (dismiss_button);

		panel = new JPanel ();
		panel.setLayout (new BoxLayout (panel, BoxLayout.Y_AXIS));
		panel.setBorder (BorderFactory.createEmptyBorder (BORDER, BORDER, BORDER, BORDER));
		panel.add (text_panel);
		panel.add (edit_panel);
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
