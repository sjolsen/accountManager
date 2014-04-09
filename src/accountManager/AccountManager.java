package accountManager;

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import accountManager.controller.Controller;
import accountManager.model.Model;
import accountManager.model.account.DuplicateAccountException;
import accountManager.model.account.serialize.MalformedAccountException;
import accountManager.view.View;

public class AccountManager
{
	public static void runApp (File file)
	{
		Model model = null;
		try
		{
			model = new Model (file);
		}
		catch (MalformedAccountException e)
		{
			JOptionPane.showMessageDialog (null, String.format ("Malformed account: \"%s\"", e.getMessage ()));
		}
		catch (IOException e)
		{
			JOptionPane.showMessageDialog (null, "Could not read from file");
		}
		catch (DuplicateAccountException e)
		{
			JOptionPane.showMessageDialog (null, String.format ("Found duplicate account with ID %d", e.old_account.getID ()));
		}

		final Controller controller = new Controller (model);

		try
		{
			SwingUtilities.invokeAndWait (new Runnable () {
				@Override
				public void run ()
				{
					new View (controller);
				}
			});
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void main (String [] args)
	{
		if (args.length < 1)
		{
			System.err.println ("Must specify an account file");
			return;
		}

		try
		{
			UIManager.setLookAndFeel (UIManager.getSystemLookAndFeelClassName ());
		}
		catch (Exception e)
		{
		}

		runApp (new File (args [0]));
	}
}
