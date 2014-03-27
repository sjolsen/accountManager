package accountManager.util.account.serialize;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import accountManager.util.account.Account;

public class AccountSerializer
{
	private static final Pattern format = Pattern.compile ("^\\s*(\\d+)\\s*\\|\\s*([\\sa-zA-Z]+)\\s*\\|\\s*(\\d+\\.\\d+)\\s*$");
	
	public static void serialize (Account account, Writer stream)
	{
		
	}
	
	public static Account deserialize (Reader stream) throws IOException
	{
		BufferedReader reader = new BufferedReader (stream);
		return deserialize (reader);
	}
	
	public static Account deserialize (BufferedReader reader) throws IOException
	{
		String line = reader.readLine ();
		Matcher tokenizer = format.matcher (line);
		
		String ID_string = tokenizer.group (1);
		String name = tokenizer.group (2);
		String amount = tokenizer.group (3);
	}
}
