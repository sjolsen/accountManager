package accountManager.view.util.money;

import accountManager.controller.Controller;
import accountManager.model.account.Account;

public class EURMoneyManager extends MoneyManager
{
	public EURMoneyManager (Controller controller, Account account)
	{
		super (controller, account);
	}

	@Override
	protected double exchangeRate ()
	{
		return 0.72;
	}

	@Override
	public String currencyLongSymbol ()
	{
		return "EUR";
	}

	@Override
	public String currencyShortSymbol ()
	{
		return "€";
	}

}
