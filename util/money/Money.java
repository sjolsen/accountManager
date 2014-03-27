package accountManager.util.money;

public interface Money
{
	USDMoney getCanonicalAmount ();
	String currencyLongSymbol ();
	String currencyShortSymbol ();
	double getAmount ();
}
