import java.io.PrintStream;
import java.util.NoSuchElementException;

public class CharQueueWithMinImpl implements  CharQueueWithMin {
    CharQueueImpl F = new CharQueueImpl();
    CharDoubleEndedQueueImpl D =new CharDoubleEndedQueueImpl();

    /**
     * Defines the methods for a FIFO queue that handles characters
     */
    public CharQueueWithMinImpl(){}

    public void put(char item){
        F.put(item);
        if(D.isEmpty()){
            D.addFirst(item);
        }
        else{
            if(D.getLast()<= item){
                D.addFirst(item);
            }
            else{
                D.addLast(item);
            }
        }
    }

    public char peek(){
        if(F.isEmpty()){
            throw new NoSuchElementException();
        }
        else{
            return F.peek();
        }
    }

    public int size(){
        return F.size();
    }


    public char get() {
        char c = F.peek();
        F.get();
        if(D.isEmpty()){
            throw new NoSuchElementException();
        }
        else{
            D.removeFirst();
        }
        return c;
    }

    public void printQueue(PrintStream stream){
        F.printQueue(System.out);
    }

    public char min(){
        return D.getLast();
    }
}



