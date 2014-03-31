package accountManager.view.ui;

import javax.swing.JFrame;

import accountManager.controller.Controller;
import accountManager.model.Model;
import accountManager.view.View;

@SuppressWarnings ("serial")
public abstract class Window extends JFrame implements View
{
	protected Controller controller;
	protected Model model;

	public Window (String title, Controller controller, Model model)
	{
		super (title);
		this.controller = controller;
		this.model = model;
	}

	@Override
        public void close ()
        {
	        setVisible (false);
	        dispose ();
        }
}
