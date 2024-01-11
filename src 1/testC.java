/**
 * Created by Grigoriadou on 3/11/2017.
 */
public class testC {
    public static void main(String args[]) {
        CharQueueImpl array = new CharQueueImpl();

        array.put('t');
        array.put('z');
        array.put('i');
        array.put('n');
        array.put('a');
        array.put('g');
        array.get();
        System.out.println(array.peek());
        System.out.println(array.size());
        array.printQueue(System.out);






    }
}
