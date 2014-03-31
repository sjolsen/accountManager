package accountManager.view;

import javax.swing.JFrame;

import accountManager.controller.Controller;
import accountManager.model.Model;

@SuppressWarnings ("serial")
public abstract class Window extends JFrame
{
	protected Controller controller;
	protected Model model;

	public Window (String title, Controller controller, Model model)
	{
		super (title);
		this.controller = controller;
		this.model = model;
	}

	public void cleanup ()
        {
	        setVisible (false);
	        dispose ();
        }
}
