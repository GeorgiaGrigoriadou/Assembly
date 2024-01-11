import java.util.NoSuchElementException;

public class List {
    private ListNode firstNode,lastNode;
    int size=0;

    public List() {this(null);}

    public List( BookInfo book) {
        firstNode = lastNode = null;
    }

    void sortedInsert(ListNode new_node){
        ListNode current;
         /* Special case for head node */
        if(firstNode == null || firstNode.data.getISBN() >= new_node.data.getISBN()){
            new_node.nextNode = firstNode;
            firstNode = new_node;
        }
        else{
            /* Locate the node before point of insertion. */
            current = firstNode;
            while (current.nextNode != null && current.nextNode.data.getISBN() < new_node.data.getISBN()){
                current = current.nextNode;
            }
            new_node.nextNode = current.nextNode;
            current.nextNode = new_node;
        }
    }

    public void addBook( BookInfo insertItem ){
        size++;
        ListNode node = new ListNode( insertItem );
        if (isEmpty()){ // firstNode and lastNode refer to same Object
            firstNode = lastNode = node;
        }
        else{sortedInsert(node);}
    }

    public void remBook(int isbn){
        ListNode current=firstNode;
        ListNode prev= null;
        if ( isEmpty() ) {
            new NoSuchElementException();
        }
        else if(current!=null&&current.getBookInfoIsbn()==isbn){
            firstNode=current.nextNode;
            return;
        }
        else {
            while (current!=null&&current.getBookInfoIsbn()!=isbn){
                prev=current;
                current=current.nextNode;
                if(current==null){return;}
                prev.nextNode=current.nextNode;
            }
        }
    }

    public boolean searchBook(int isbn){
        boolean bool=false;
        if ( isEmpty() ) {return bool;}
        ListNode current = firstNode;
        while ( current != null ) {
            if(current.data.getISBN()==isbn){
                bool=true;
                break;
            }
            current = current.nextNode;
        }
        return bool;
    }

    public void addCopy(int c,int isbn){
        ListNode current = firstNode;
        while ( current != null ) {
            if (current.getBookInfoIsbn() == isbn) {
                current.data.addCopies(c);
                break;
            }
            current = current.nextNode;
        }
    }

    public int subCopy(int isbn){
        ListNode current = firstNode;
        int c=current.data.copies;
        while ( current != null ) {
            if (current.getBookInfoIsbn() == isbn) {
                current.data.subCopies();
                if(current.data.copies==0){
                    c=0;
                }
                return c;
            }
            current = current.nextNode;
        }
        return c;
    }

    public ListNode getFirstNode(){return firstNode;}

    public boolean isEmpty(){return firstNode == null;}

    public void printBook(int isbn) {
        ListNode current = firstNode;
        while ( current != null ) {
            if(current.getBookInfoIsbn()==isbn) {
                System.out.printf("Book: %s, Copies : %d \n", current.data.getISBN(), current.data.getCopies());
                return;
            }
            current = current.nextNode;
        }
        System.out.println( "\n" );
    }

    public void printCopies(int isbn) {
        ListNode current = firstNode;
        while ( current != null ) {
            if(current.getBookInfoIsbn()==isbn) {
                System.out.printf(" Copies : %d \n",  current.data.getCopies());
                return;
            }
            current = current.nextNode;
        }
        System.out.println( "\n" );
    }


    public void print() {
        if ( isEmpty() ) {new NoSuchElementException();}
        System.out.printf( "The books from this warehouse are : \n");
        ListNode current = firstNode;
        // while not at end of list, output current node's data
        while ( current != null ) {
            System.out.printf( "Book: %s, Copies : %d \n", current.data.getISBN(),current.data.getCopies());
            current = current.nextNode;
        }
        System.out.println( "\n" );
    }
}



