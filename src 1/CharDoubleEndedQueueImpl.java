import  java.io.PrintStream;
import java.util.NoSuchElementException;
public class CharDoubleEndedQueueImpl implements  CharDoubleEndedQueue {

    private doubleNode head, tail, node1, d;
    //private char c;
    int n;

    public CharDoubleEndedQueueImpl(){

    }
    /**
     * @return true if the queue is empty
     */
    public boolean isEmpty() {return n == 0;}
    /**
     * insert a character at the front of the queue
     */
    public void addFirst(char item) {
        d = head;
        head =new doubleNode(item);
        if(isEmpty()) {
            tail = head;
        }
        else {
            d.previous=head;
            head.next=d;

        }
        n++;

    }
    /**
     * remove and return a character from the front of the queue
     * @return character from the front of the queue
     * @throws NoSuchElementException if the queue is empty
     */
    public char removeFirst() {


        if (isEmpty()) {
            System.out.println("fsdfs");
            throw new NoSuchElementException();
        }
        else {
            char removedItem = head.item;
            node1 = head.next;
            if (head == tail) {
                head = tail = null;
            }
            else {
                head.next = null;
                node1.previous = null;
                head=node1;
            }
            n--;
            return removedItem;
        }
    }
    /**
     * insert a character at the end of the queue
     */
    public void addLast(char item){
        d = tail;
        tail =new doubleNode(item);
        if(isEmpty()) {
            head = tail;
        }
        else {
            d.next=tail;
            tail.previous=d;
        }
        n++;

    }
    /**
     * remove and return a character from the end of the queue
     * @return character from the end of the queue
     * @throws NoSuchElementException if the queue is empty
     */
     public char removeLast(){
         char removedItem = tail.item;
         node1=tail.previous;
         if (isEmpty()) {
             throw new NoSuchElementException();
         }
         else {
             if (head == tail) {
                 head = tail = null;
             }
             else {
                 tail.previous=null;
                 node1.next=null;
                 tail=node1;
             }
             n--;
         }
         return removedItem;

     }
    /**
     * return without removing the first item in the queue
     * @return character from the front of the queue
     * @throws NoSuchElementException if the queue is empty
     */
     public char getFirst() {

         if (isEmpty()) {
             throw new NoSuchElementException();
         }
         char c = head.item;
         return  c;
     }
    /**
     * return without removing the last item in the queue
     * @return character from the end of the queue
     * @throws NoSuchElementException if the queue is empty
     */
    public char getLast(){
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        char c = tail.item;
        return  c;
    }
    /**
     * print the elements of the queue, starting from the front,
     * to the print stream given as argument. For example, to
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
        doubleNode current = head;

        // while not at end of list, output current node's data
        while ( current != null )
        {
            System.out.printf( "%s ", current.item );
            current = current.next;
        } // end while

        System.out.println( "\n" );
    }
    /**
     * return the size of the queue, 0 if empty
     * @return number of elements in the queue
     */
    public int size(){
        return  n;
    }

}
