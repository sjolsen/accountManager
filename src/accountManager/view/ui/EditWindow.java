package accountManager.view.ui;

import accountManager.controller.Controller;
import accountManager.model.Model;
import accountManager.model.account.Account;

public class EditWindow extends Window
{
	private final Account account;

	public EditWindow (Controller controller, Model model, Account account)
	{
		super (String.format ("Account %d", account.getID ()), controller, model);
		this.account = account;
	}
}
