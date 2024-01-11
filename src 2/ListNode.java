class ListNode {
	int data;
	ListNode nextNode;
	/**
	 * Constructor. It initializes data and sets next node to null 
	 * @param object a reference to node's data
	 */
	ListNode( int object )
	{
		this( object, null );
	}
	/**
	 * constructor creates ListNode with passed data and next node
	 * @param object the reference to node's data
	 * @param node the next node in the list
	 */
	ListNode( int object, ListNode node ) {
		data = object;
		nextNode = node;
	}
	/**
	 * Returns this node's data
	 * @return the reference to node's data
	 */
	Object getObject()
	
	{
		return data; 
	}
	/**
	 * Get reference to next node
	 * @return the next node
	 */
	ListNode getNext()
	{
		return nextNode; 
	} 
} 