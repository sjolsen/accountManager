package accountManager.util.money;

public class EuroMoney extends AbstractMoney
{
	private static final String long_symbol = "EUR";
	private static final String short_symbol = "€";
	private static final double exchange_rate = 0.72;
	
	EuroMoney (Money amount)
	{
		super (amount);
	}
	
	EuroMoney (double amount)
	{
		super (amount);
	}
	
	@Override
	public String currencyLongSymbol ()
	{
		return long_symbol;
	}

	@Override
	public String currencyShortSymbol ()
	{
		return short_symbol;
	}

	@Override
        public double exchangeRate ()
        {
		return exchange_rate;
        }
}
