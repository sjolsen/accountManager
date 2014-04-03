package accountManager.view.util.money;

import accountManager.controller.Controller;
import accountManager.model.account.Account;

public enum Currency
{
	USD,
	EUR,
	CNY;

	public MoneyManager makeManager (Controller controller, Account account)
	{
		switch (this)
		{
		case USD:
			return new USDMoneyManager (controller, account);
		case EUR:
			return new EURMoneyManager (controller, account);
		case CNY:
			return new CNYMoneyManager (controller, account);
		}
		// FIXME
		return null;
	}
}