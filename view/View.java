package accountManager.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import accountManager.controller.Controller;
import accountManager.model.Model;
import accountManager.view.ui.MainWindow;

public class View implements ActionListener
{
	private Controller controller;
	private Model model;
	
	private MainWindow main_window;
	
	public View (Controller controller, Model model)
	{
		this.controller = controller;
		this.model = model;

		this.main_window = new MainWindow (this, model.getAccounts ());
	}

	@Override
        public void actionPerformed (ActionEvent e)
        {
		String estring = e.getActionCommand ();
		
	        if (estring.equals ("MainWindow.save"))
	                try
                        {
	                	// FIXME
	                        controller.save ();
                        }
                        catch (IOException e1)
                        {
                        }
                else if (estring.equals ("MainWindow.exit"))
	        	controller.exit ();
                else
                	// FIXME
                	;
        }

	public void cleanup ()
        {
	        main_window.setVisible (false);
	        main_window.dispose ();
        }
}
