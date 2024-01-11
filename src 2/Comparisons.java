import java.io.*;
import java.util.Random;

public class Comparisons {
    protected static int  AvGr=0;
    protected static int AvGrDec=0;

    public static void main(String args[]) {
        createFile(100);
        createFile(500);
        createFile(1000);

        System.out.println("N=100 ");
        for(int i=0;i<10;i++){
            Greedy("100_"+i+".txt");
            GreedyDec("100_"+i+".txt");
        }
        int AvGr100=AvGr/100;
        int AvGrDec100=AvGrDec/100;
        System.out.println("AvGr, N=100 : "+ AvGr100);
        System.out.println("AvGrDec, N=100 : "+ AvGrDec100);
        System.out.println();
        System.out.println("N=500 ");
        for(int i=0;i<10;i++){
            Greedy("500_"+i+".txt");
            GreedyDec("500_"+i+".txt");
        }
        int AvGr500=AvGr/500;
        int AvGrDec500=AvGrDec/500;
        System.out.println("AvGr, N=500 : "+ AvGr500);
        System.out.println("AvGrDec, N=500 : "+ AvGrDec500);
        System.out.println();
        System.out.println("N=1000 ");
        for(int i=0;i<10;i++){
            Greedy("1000_"+i+".txt");
            GreedyDec("1000_"+i+".txt");
        }
        int AvGr1000=AvGr/1000;
        int AvGrDec1000=AvGrDec/1000;
        System.out.println("AvGr, N=1000 : "+ AvGr1000);
        System.out.println("AvGrDec, N=1000 : "+ AvGrDec1000);
        System.out.println();

    }//end main

    public static void createFile(int N) {
        for(int i=0; i<10; i++) {
            String path =N+"_"+i+".txt";
            File f = null;
            BufferedWriter writer = null;
            try {
                f = new File(path);
            } catch (NullPointerException e) {
                System.err.println("File not found.");
            }
            try {
                writer = new BufferedWriter(new OutputStreamWriter
                        (new FileOutputStream(f)));
            } catch (FileNotFoundException e1) {
            }
            try {
                int job = N;
                int pro = (int) Math.sqrt(job);
                writer.write(Integer.toString(pro));
                writer.newLine();
                writer.write(Integer.toString(job));
                writer.newLine();
                for (int j = 0; j < job; j++) {
                    writer.write(Integer.toString(new Random().nextInt(110)));
                    writer.newLine();
                }
                writer.flush();
                writer.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }//end for
    }//end createFile

    public static void Greedy(String path){
        String filename = path;
        File f = null;
        String line;
        BufferedReader reader = null;
        int count_of_pro;
        int count_of_job;
        try {
            f = new File(filename);
        }
        catch (NullPointerException e) {
            System.err.println("File not found.");
        }
        try {
            reader = new BufferedReader(new FileReader(f));
        }
        catch (FileNotFoundException e) {
            System.out.println("Error opening file!");
        }
        try {
            while ((line = reader.readLine()) != null) {
                try {
                    count_of_pro = Integer.parseInt(line);
                    Processor processors[] = new Processor[count_of_pro];
                    line = reader.readLine();
                    count_of_job = Integer.parseInt(line);
                    MaxPQ pq = new MaxPQ(count_of_job);
                    for (int i = 0; i < processors.length; i++) {
                        processors[i] = new Processor();
                        pq.insert(processors[i]);
                    }
                    int arr[] = new int[count_of_job];
                    for (int i = 0; i < count_of_job; i++) {
                        line = reader.readLine();
                        arr[i]=Integer.parseInt(line);
                    }
                    int j = 0;
                    for (int i = 0; i < count_of_job; i++) {
                        if (j == count_of_pro) {
                            j = 0;
                        }
                        pq.getMin();
                        pq.getMin().addJobs(arr[i]);
                        j++;
                    }
                    System.out.println("Greedy Makespan : " + pq.makespan());
                    AvGr+=pq.makespan();
                }
                catch (NumberFormatException e) {
                    System.out.println("the file contains characters");
                    break;
                }
            }//end while
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        try {
            reader.close();
        }
        catch (IOException e) {
            System.err.println("Error closing file.");
        }
    } //end Greedy

    public static void GreedyDec(String path){
        String filename = path;
        File f = null;
        String line;
        BufferedReader reader = null;
        int count_of_pro;
        int count_of_job;
        try {
            f = new File(filename);
        }
        catch (NullPointerException e) {
            System.err.println("File not found.");
        }
        try {
            reader = new BufferedReader(new FileReader(f));
        }
        catch (FileNotFoundException e) {
            System.out.println("Error opening file!");
        }
        try {
            while ((line = reader.readLine()) != null) {
                try {
                    count_of_pro = Integer.parseInt(line);
                    Processor processors[] = new Processor[count_of_pro];
                    line = reader.readLine();
                    count_of_job = Integer.parseInt(line);
                    MaxPQ pq = new MaxPQ(count_of_job);
                    for (int i = 0; i < processors.length; i++) {
                        processors[i] = new Processor();
                        pq.insert(processors[i]);
                    }
                    int arr[] = new int[count_of_job];
                    for (int i = 0; i < count_of_job; i++) {
                        line = reader.readLine();
                        arr[i] = Integer.parseInt(line);
                    }
                    int j = 0;
                    Sort s = new Sort();
                    s.sort(arr);
                    for (int i = 0; i < count_of_job; i++) {
                        if (j == count_of_pro) {
                            j = 0;
                        }
                        pq.getMin();
                        pq.getMin().addJobs(arr[i]);
                        j++;
                    }
                    System.out.println("GreedyDec Makespan : " + pq.makespan());
                    AvGrDec+=pq.makespan();
                }
                catch (NumberFormatException e) {
                    System.out.println("the file contains characters");
                    break;
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        try {
            reader.close();
        }
        catch (IOException e) {
            System.err.println("Error closing file.");
        }
    }//end GreedyDec
} //end Comparisons


