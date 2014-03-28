package accountManager.view.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

import accountManager.model.account.Account;
import accountManager.model.account.AccountList;

public class MainWindow extends JFrame
{
	private static final long serialVersionUID = -2025399850666074771L;
	
	private JPanel panel;
	private AccountSelector selector;
	private JList <Account> account_list;

	public MainWindow (AccountList accounts)
	{
		super ("Account Manager");
		
		panel = new JPanel (new BorderLayout ());
		getContentPane ().add (panel);

		selector = new AccountSelector (accounts);
		account_list = selector.getAccountList ();
		panel.add (selector.getJComponent ());

		setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		pack ();
		setVisible (true);
	}
}
