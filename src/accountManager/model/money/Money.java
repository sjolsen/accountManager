package accountManager.model.money;

public class Money
{
	private final double dollars;
	
	public Money (double dollars)
	{
		this.dollars = dollars;
	}
	
	public double getAmount ()
	{
		return dollars;
	}
	
	public Money plus (Money other)
	{
		return new Money (dollars + other.dollars);
	}

	public Money minus (Money other)
	{
		return new Money (dollars - other.dollars);
	}
}
