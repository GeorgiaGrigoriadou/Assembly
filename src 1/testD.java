/**
 * Created by Grigoriadou on 5/11/2017.
 */
public class testD {
    public static void main(String args[]) {
        CharQueueWithMinImpl min = new CharQueueWithMinImpl();
        min.put('t');
        min.put('a');
        min.put('w');
        min.put('s');
        min.put('b');
        System.out.println(min.min());
        System.out.println(min.get());
        min.printQueue(System.out);
        min.size();

//        System.out.println(min.get());
//        System.out.println(min.get());
//        System.out.println(min.get());
       
    }
}
