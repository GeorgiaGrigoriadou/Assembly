public class Greedy {

    public static void main( String args[] ) {
        List list = new List().loadFile("textFile.txt");
        list.print();
        int count_of_pros=list.removeFromFront();
        MaxPQ pq=new MaxPQ(count_of_pros);

        int count_of_jobs=list.removeFromFront();

        Processor processors []=new Processor[count_of_pros];
        for(int i=0;i<processors.length;i++){
            processors[i]=new Processor();
            processors[i].addJobs(list.removeFromFront());
            pq.insert(processors[i]);
        }
        pq.print();
        System.out.println(pq.getMin());
        for(int i=0; i<count_of_jobs; i++){
            Processor min=pq.getMin();
            min.addJobs(list.removeFromFront());
        }

        pq.print();









    }


}