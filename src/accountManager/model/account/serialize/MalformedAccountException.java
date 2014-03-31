package accountManager.model.account.serialize;

import java.text.ParseException;

public class MalformedAccountException extends ParseException
{
	private static final long serialVersionUID = 588896319899698948L;

	public MalformedAccountException (String text)
        {
	        super (text, 0);
        }

}
