package accountManager.model.account.serialize;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import accountManager.model.account.Account;
import accountManager.model.money.USDMoney;

public class AccountSerializer
{
	private static final Pattern format = Pattern.compile ("^\\s*(\\d+)\\s*\\|\\s*([\\sa-zA-Z]+?)\\s*\\|\\s*(\\d*\\.?\\d+)\\s*$");

	public static void serialize (Account account, PrintWriter writer)
	{
		writer.printf ("%d | %s | %f\n", account.getID (), account.getName (), account.getMoney ().getCanonicalAmount ().getAmount ());
		writer.flush ();
	}

	public static Account deserialize (BufferedReader reader) throws IOException, MalformedAccountException
	{
		String line = reader.readLine ();
		if (line == null)
			return null;

		Matcher tokenizer = format.matcher (line);
		tokenizer.matches ();

		String ID_string = tokenizer.group (1);
		if (ID_string == null)
			throw new MalformedAccountException (line);
		int ID = Integer.parseInt (ID_string);

		String name = tokenizer.group (2);
		if (name == null)
			throw new MalformedAccountException (line);
		name = name.replaceAll ("\\s+", " ");

		String amount_string = tokenizer.group (3);
		if (amount_string == null)
			throw new MalformedAccountException (line);
		double amount = Double.parseDouble (amount_string);

		return new Account (ID, name, new USDMoney (amount));
	}	
}
