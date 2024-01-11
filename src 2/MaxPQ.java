import java.lang.*;

public class MaxPQ {
    private Processor[] heap;
    private int size;
    protected Comparable cmp;
    /**
     * Creates heap with a given capacity and default comparator.
     * param capacity The capacity of the heap being created.
     */
    public MaxPQ(int capacity) {
        this(capacity, null);
    }
    /**
     * Creates heap with a given capacity and comparator.
     * param capacity The capacity of the heap being created.
     * param cmp The comparator that will be used.
     */
    public MaxPQ(int capacity, Comparable cmp) {
        if (capacity < 1) {throw new IllegalArgumentException();}
        this.heap = new Processor[capacity + 1];
        this.size = 0;
        this.cmp = cmp;
    }
    /**
     * Inserts an object in this heap.
     * throws IllegalStateException if heap capacity is exceeded.
     * param object The object to insert.
     */
    public void insert(Processor processor) {
        if (processor == null){ throw new IllegalArgumentException();}
        if (size == heap.length-1) throw new IllegalStateException();
        heap[++size] = processor;
        swim(size);
    }
    /**
     * throws IllegalStateException if heap is empty.
     * return the object at the root of this heap.
     */
    public Processor getMin() {
        if (size == 0) throw new IllegalStateException();
        // Keep a reference to the root object
        Processor processor = heap[1];
        sink(1);
        return processor;
    }

    public int makespan(){
        if (size == 0) throw new IllegalStateException();
        this.getMin();
        return heap[size].getActiveTime();
    }
    /**
     * Shift up.
     */
    private void swim(int i) {
        while(i > 1){
            if(heap[i/2].compareTo(heap[i]) >0) {
                swap(i, i/2);
            }
            i = i/2;
        }
    }
    /**
     * Shift down.
     */
    private void sink(int i){
        while(2*i <= size){
            int md = 2*i;
            if(md < size && heap[md].compareTo(heap[md+1]) > 0) {
                md++;
            }
            if(heap[i].compareTo(heap[md]) <= 0) {return;}
            swap(i, md);
            i = md;
        }
    }
    /**
     * Interchanges two array elements.
     */
    private void swap(int i, int j) {
        Processor tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    public void print() {
        for (int i=1; i<=size; i++) {
            heap[i].print();
        }
    }
    boolean empty(){
        return size == 0;
    }
}