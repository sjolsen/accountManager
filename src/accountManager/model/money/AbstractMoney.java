package accountManager.model.money;

public abstract class AbstractMoney implements Money
{
	private final double amount;
	
	public AbstractMoney (double amount)
	{
		this.amount = amount;
	}
	
	public AbstractMoney (Money amount)
	{
		this.amount = amount.getCanonicalAmount ().getAmount () * this.exchangeRate ();
	}
	
	public abstract double exchangeRate ();
	
	public USDMoney getCanonicalAmount ()
	{
		return new USDMoney (this.amount / this.exchangeRate ());
	}
	
	public double getAmount ()
	{
		return amount;
	}
	
	public Money plus (Money other)
	{
		return new USDMoney (getCanonicalAmount ().getAmount () + other.getCanonicalAmount ().getAmount ());
	}

	public Money minus (Money other)
	{
		return new USDMoney (getCanonicalAmount ().getAmount () - other.getCanonicalAmount ().getAmount ());
	}
}
