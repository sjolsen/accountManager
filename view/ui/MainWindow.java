package accountManager.view.ui;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JList;

import accountManager.controller.Controller;
import accountManager.model.Model;
import accountManager.model.account.Account;

public class MainWindow
{
	private Controller controller;
	private Model model;
	
	private JFrame window;
	private Container window_contents;
	private JList <Account> account_list;

	public MainWindow (Controller controller, Model model)
	{
		this.controller = controller;
		this.model = model;
		
		this.window = new JFrame ("Account Manager");
		this.window_contents = window.getContentPane ();
		window.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		
		this.account_list = new JList <Account> (model.getAccounts ().getAccounts ());
		window_contents.add (account_list);
		
		window.pack ();
		window.setVisible (true);
	}
}
