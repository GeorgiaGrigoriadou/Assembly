/**
 * EmptyListException is a simple class that extends RuntimeException. It 
 * is thrown by {@link List} when get methods are called on an empty list. 
 */
public class EmptyListException extends RuntimeException
{
	/**
	 * Default constructor, calls constructor with parameter name set to "List"
	 */
	public EmptyListException()
	{
		this( 0 ); // call other EmptyListException constructor
	} // end EmptyListException no-argument constructor

	/**
	 * Constructor with list's name, calls RuntimeException(String)
	 * @param time the list name
	 */
	public EmptyListException( int time ) {
		super( time + " is empty" ); // call superclass constructor
	} 
} 

