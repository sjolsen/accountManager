package accountManager.view.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import accountManager.model.account.Account;
import accountManager.view.View;
import accountManager.view.util.money.Currency;

@SuppressWarnings ("serial")
public class MainWindow extends Window
{
	private class EditButton extends CallbackButton
	{
		private final Currency currency;

		public EditButton (String designator, Currency currency)
		{
			super (String.format ("Edit in %s", designator));
			this.currency = currency;
		}

		@Override
		public void onClicked ()
		{
			for (Account a : selector.getSelectedAccounts ())
				MainWindow.this.view.edit (a, currency);
		}
	}

	private final AccountSelector selector;

	private final JButton USD_button;
	private final JButton EUR_button;
	private final JButton CNY_button;

	private final JButton save_button;
	private final JButton exit_button;

	private JPanel panel;
	private JPanel control_area;
	private JPanel edit_panel;
	private JPanel button_panel;

	public MainWindow (View view, String pathname)
	{
		super (String.format ("Account Manager - %s", pathname), view);

		selector = new AccountSelector (view.getAccounts ());

		USD_button = new EditButton ("USD", Currency.USD);
		EUR_button = new EditButton ("EUR", Currency.EUR);
		CNY_button = new EditButton ("CNY", Currency.CNY);

		save_button = new CallbackButton ("Save") {
			@Override
			public void onClicked ()
			{
				MainWindow.this.view.save ();
			}
		};
		getRootPane ().setDefaultButton (save_button);
		exit_button = new CallbackButton ("Exit") {
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
		edit_panel = new JPanel ();
		edit_panel.setLayout (new BoxLayout (edit_panel, BoxLayout.Y_AXIS));
		edit_panel.add (USD_button);
		edit_panel.add (EUR_button);
		edit_panel.add (CNY_button);

		button_panel = new JPanel ();
		button_panel.setLayout (new BoxLayout (button_panel, BoxLayout.X_AXIS));
		button_panel.add (Box.createHorizontalGlue ());
		button_panel.add (exit_button, BorderLayout.EAST);
		button_panel.add (Box.createRigidArea (new Dimension(BORDER, 0)));
		button_panel.add (save_button, BorderLayout.WEST);

		control_area = new JPanel ();
		control_area.setLayout (new BorderLayout (BORDER, BORDER));
		control_area.add (edit_panel, BorderLayout.CENTER);
		control_area.add (button_panel, BorderLayout.SOUTH);

		panel = new JPanel ();
		panel.setLayout (new BoxLayout (panel, BoxLayout.X_AXIS));
		panel.setBorder (BorderFactory.createEmptyBorder (BORDER, BORDER, BORDER, BORDER));
		panel.add (selector.getJComponent ());
		panel.add (Box.createRigidArea (new Dimension (BORDER, 0)));
		panel.add (control_area);

		getContentPane ().add (panel);
		pack ();
		setVisible (true);
	}
}
