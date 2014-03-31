package accountManager.model.money;

public interface Money
{
	USDMoney getCanonicalAmount ();
	String currencyLongSymbol ();
	String currencyShortSymbol ();
	double getAmount ();
	
	Money plus (Money other);
	Money minus (Money other);
}
