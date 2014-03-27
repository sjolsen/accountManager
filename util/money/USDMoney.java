package accountManager.util.money;

public class USDMoney extends AbstractMoney
{
	private static final String long_symbol = "USD";
	private static final String short_symbol = "$";
	private static final double exchange_rate = 1;
	
	public USDMoney (Money amount)
	{
		super (amount);
	}
	
	public USDMoney (double amount)
	{
		super (amount);
	}
	
	@Override
	public USDMoney getCanonicalAmount ()
	{
		return this;
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
