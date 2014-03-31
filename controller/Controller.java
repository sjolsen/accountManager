package accountManager.controller;

import java.io.File;
import java.io.IOException;

import accountManager.model.Model;
import accountManager.model.account.serialize.MalformedAccountException;
import accountManager.view.View;

public class Controller
{
	private Model model;
	private View view;
	
	public Controller (File filename) throws MalformedAccountException, IOException
	{
		this.model = new Model (filename);
		this.view = new View (this, model);
	}

	public void save () throws IOException
	{
		model.getFile ().writeAllAccounts (model.getAccounts ());
	}

	public void exit ()
	{
		try
		{
			save ();
		}
		catch (IOException e)
		{
		}
		model.cleanup ();
		view.cleanup ();
	}
}
