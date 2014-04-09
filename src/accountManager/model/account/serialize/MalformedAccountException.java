package accountManager.model.account.serialize;

import java.text.ParseException;

@SuppressWarnings ("serial")
public class MalformedAccountException extends ParseException
{
	public MalformedAccountException (String text)
	{
		super (text, 0);
	}
}
