 import java.io.PrintStream;
import java.util.NoSuchElementException;

public class CharQueueImpl implements  CharQueue {
    private Node head,tail;
    private int n;

    /**
     * Defines the methods for a FIFO queue that handles characters
     */
    public CharQueueImpl(){

    }


    /**
     * @return true if the queue is empty*/
    public boolean isEmpty(){
        return n==0;
    }

    /**
     * insert a single character to the queue
     */
    public void put(char item){
        Node t=tail;
        tail=new Node(item);
        if(isEmpty()) {
            head = tail;
        }
        else{
            t.next=tail;
        }
        n++;
    }

    /**
     * remove and return the oldest item of the queue
     * @return oldest item of the queue
     * @throws NoSuchElementException if the queue is empty
     */
    public char get() {
        char c=head.item;
        if(isEmpty()) {
            throw new NoSuchElementException();
        }
        else{
            Node t = head.next;
            head=t;
            n--;
        }
        return  c;
    }

    /**
     * return without removing the oldest item of the queue
     * @return oldest item of the queue
     * @throws NoSuchElementException if the queue is empty
     */
    public char peek(){
        if(isEmpty()){
           throw new NoSuchElementException();
        }
        else{
            return head.item;
        }
    }


    /**
     * print the elements of the queue, starting from the oldest
     * item, to the print stream given as argument. For example, to
     * print the elements to the
     * standard output, pass System.out as parameter. E.g.,
     * printQueue(System.out);
     */
    public void printQueue(PrintStream stream){
        if ( isEmpty() )
        {
            System.out.printf( "Empty %s\n", stream );
            return;
        } // end if

        System.out.printf( "The %s is: ", stream );
        Node current = head;

        // while not at end of list, output current node's data
        while ( current != null )
        {
            System.out.printf( "%s ", current.item );
            current = current.next;
        } // end while

        System.out.println( "\n" );
    }

    /**
     * return the size of the queue, 0 if it is empty
     * @return number of elements in the queue
     */
    public int size(){
        return n;
    }
}






