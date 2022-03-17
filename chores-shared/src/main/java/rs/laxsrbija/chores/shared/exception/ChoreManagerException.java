package rs.laxsrbija.chores.shared.exception;

public class ChoreManagerException extends RuntimeException
{
	public ChoreManagerException(final String message)
	{
		super(message);
	}

	public ChoreManagerException(final String message, final Throwable cause)
	{
		super(message, cause);
	}
}
