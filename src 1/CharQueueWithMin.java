import java.io.PrintStream;
import java.util.NoSuchElementException;

public interface CharQueueWithMin  {

    public void put(char item);

    public char get() throws NoSuchElementException;

    public char peek() throws NoSuchElementException;

    public void printQueue(PrintStream stream);

    public int size();

    public char min();
}



