package accountManager.model.account.serialize;

import java.text.ParseException;

/**
 * Indicates a failure in database parsing
 */
@SuppressWarnings ("serial")
public class MalformedAccountException extends ParseException
{
	public MalformedAccountException (String text)
	{
		super (text, 0);
	}
}
