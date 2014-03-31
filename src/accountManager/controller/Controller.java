package accountManager.controller;

import java.io.File;
import java.io.IOException;

import accountManager.model.Model;
import accountManager.model.account.Account;
import accountManager.model.account.serialize.MalformedAccountException;
import accountManager.view.ui.MainWindow;

public class Controller
{
	private Model model;
	private MainWindow main_window;
	
	public Controller (File filename) throws MalformedAccountException, IOException
	{
		this.model = new Model (filename);
		this.main_window = new MainWindow (this, model);
	}

	public void edit (Account account)
	{

	}

	public void save ()
	{
		try
                {
	                model.getFile ().writeAllAccounts (model.getAccounts ());
                }
                catch (IOException e)
                {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
                }
	}

	public void exit ()
	{
		save ();
		model.cleanup ();
		main_window.cleanup ();
	}
}
