package accountManager.view.util.money;

import accountManager.controller.Controller;
import accountManager.model.account.Account;

public class USDMoneyManager extends MoneyManager
{
	public USDMoneyManager (Controller controller, Account account)
	{
		super (controller, account);
	}

	@Override
	protected double exchangeRate ()
	{
		return 1;
	}

	@Override
	public String currencyLongSymbol ()
	{
		return "USD";
	}

	@Override
	public String currencyShortSymbol ()
	{
		return "$";
	}

}
