public class List {
	private ListNode firstNode,lastNode;
	private int jtime;
	int totalTime=0;
	int size=0;

	public List()
	{
		this(0);
	}

	public List( int listTime) {
		jtime = listTime;
		firstNode = lastNode = null;
	}

	public void insertAtBack( int insertItem ) {
		size++;
		totalTime+=insertItem;
		ListNode node = new ListNode( insertItem );
		if ( isEmpty() ) // firstNode and lastNode refer to same Object
		firstNode = lastNode = node;
		else { // lastNode's nextNode refers to new node
			lastNode.nextNode = node;
			lastNode = node;
			//you can replace the two previous lines with this line: lastNode = lastNode.nextNode = new ListNode( insertItem );
		}
	}

	public int totalTimeOfJobs(){return  totalTime;}

	public int getSize(){return size;}

	public ListNode getFirstNode(){return firstNode;}

	public boolean isEmpty(){return firstNode == null;}

	public void print() {
		if ( isEmpty() ) {
			System.out.printf( "Empty %s\n", "list " );
			return;
		}
		System.out.printf( "The %s is: ", "list" );
		ListNode current = firstNode;
		// while not at end of list, output current node's data
		while ( current != null ) {
			System.out.printf( "%s ", current.data );
			current = current.nextNode;
		}
		System.out.println( "\n" );
	}
} 
