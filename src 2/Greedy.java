import java.io.*;

public class Greedy {

    public static void main(String args[]) {
        String filename ="textFile.txt";
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
                    } //end for
                    int j = 0;
                    for (int i = 0; i < count_of_job; i++) {
                        if (j == count_of_pro) {
                            j = 0;
                        } //end for
                        pq.getMin();
                        pq.getMin().addJobs(arr[i]);
                        j++;
                    } //end for
                    pq.print();
                    System.out.println("Greedy Makespan : " + pq.makespan());
                } //end try
                catch (NumberFormatException e) {
                    System.out.println("the file contains characters");
                    break;
                }
            }//end while
        }//end try
        catch (IOException e) {
            e.printStackTrace();
        }
        try {
            reader.close();
        }
        catch (IOException e) {
            System.err.println("Error closing file.");
        }
    }
}