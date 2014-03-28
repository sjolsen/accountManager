package accountManager.view;

import accountManager.controller.Controller;
import accountManager.model.Model;
import accountManager.view.ui.MainWindow;

public class View
{
	private Controller controller;
	private Model model;
	
	private MainWindow main_window;
	
	public View (Controller controller, Model model)
	{
		this.controller = controller;
		this.model = model;

		this.main_window = new MainWindow (model.getAccounts ());
	}
}
