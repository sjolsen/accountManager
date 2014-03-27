package accountManager.util.money;

public abstract class AbstractMoney implements Money
{
	private final double amount;
	
	AbstractMoney (double amount)
	{
		this.amount = amount;
	}
	
	AbstractMoney (Money amount)
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
}
