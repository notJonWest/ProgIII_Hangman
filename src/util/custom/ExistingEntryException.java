package util.custom;

public class ExistingEntryException extends Exception
{
	public ExistingEntryException()
	{
		super();
	}
	public ExistingEntryException(String message)
	{
		super(message);
	}
}
