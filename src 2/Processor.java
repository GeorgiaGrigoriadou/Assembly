
public class Processor implements Comparable<Processor> {
    static int UNIQUE_ID = 0;
    int uid = ++UNIQUE_ID;
    int job_time;
    List processed_jobs=new List();
    Processor(){}
    public int hashCode() {return uid;}
    public void addJobs(int time){
        processed_jobs.insertAtBack(time);
    }
    public int getActiveTime(){return processed_jobs.totalTimeOfJobs();}
    public int compareTo(Processor pro){
        final int BEFORE = -1;
        final int EQUAL = 0;
        final int AFTER = 1;
        if (this.getActiveTime() == pro.getActiveTime()){ return EQUAL;}
        if (this.getActiveTime()< pro.getActiveTime()){return BEFORE;}
        return AFTER;
    }
    public void print(){
        System.out.print("id "+ this.hashCode() + ", load= "+ this.processed_jobs.totalTimeOfJobs()+ " : ");
        ListNode data = this.processed_jobs.getFirstNode();
        for(int i=0;i<this.processed_jobs.getSize();i++){
            System.out.print(data.getObject() + " ");
            data = data.getNext();
        }
        System.out.println();
    }
}