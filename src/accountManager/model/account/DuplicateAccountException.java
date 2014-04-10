package accountManager.model.account;

/**
 * Indicates conflicting account entries in the database
 */
@SuppressWarnings ("serial")
public class DuplicateAccountException extends Exception
{
	public final Account new_account;
	public final Account old_account;

	public DuplicateAccountException (Account new_account, Account old_account)
	{
		this.new_account = new_account;
		this.old_account = old_account;
	}
}
