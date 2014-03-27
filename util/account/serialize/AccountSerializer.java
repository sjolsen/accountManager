package accountManager.util.account.serialize;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import accountManager.util.account.Account;
import accountManager.util.account.LineNumberedAccount;
import accountManager.util.money.USDMoney;

public class AccountSerializer
{
	private static final Pattern format = Pattern.compile ("^\\s*(\\d+)\\s*\\|\\s*([\\sa-zA-Z]+)\\s*\\|\\s*(\\d+\\.\\d+)\\s*$");
	
	public static void serialize (Account account, BufferedWriter writer)
	{
		
	}
	
	public static Account deserialize (Reader reader) throws IOException, MalformedAccountException
	{
		return deserialize (new LineNumberReader (reader));
	}
	
	public static Account deserialize (LineNumberReader reader) throws IOException, MalformedAccountException
	{
		int line_number = reader.getLineNumber ();
		String line = reader.readLine ();
		Matcher tokenizer = format.matcher (line);
		
		String ID_string = tokenizer.group (1);
		if (ID_string == null)
			throw new MalformedAccountException (line);
		
		String name = tokenizer.group (2);
		if (name == null)
			throw new MalformedAccountException (line);
		
		String amount_string = tokenizer.group (3);
		if (amount_string == null)
			throw new MalformedAccountException (line);
		
		int ID = Integer.parseInt (ID_string);
		double amount = Double.parseDouble (amount_string);
		return new LineNumberedAccount (line_number, ID, name, new USDMoney (amount));
	}
	
	
}
