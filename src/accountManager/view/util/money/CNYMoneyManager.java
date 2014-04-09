package accountManager.view.util.money;

import accountManager.controller.Controller;
import accountManager.model.account.Account;

public class CNYMoneyManager extends MoneyManager
{
	public CNYMoneyManager (Controller controller, Account account)
	{
		super (controller, account);
	}

	@Override
	protected double exchangeRate ()
	{
		return 6.2;
	}

	@Override
	public String currencyLongSymbol ()
	{
		return "CNY";
	}

	@Override
	public String currencyShortSymbol ()
	{
		return "¥";
	}

}
