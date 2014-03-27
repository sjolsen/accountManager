package accountManager.util.money;

public class YuanMoney extends AbstractMoney
{
	private static final String long_symbol = "CNY";
	private static final String short_symbol = "¥";
	private static final double exchange_rate = 6.2;
	
	public YuanMoney (Money amount)
	{
		super (amount);
	}
	
	public YuanMoney (double amount)
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
