/**
 * Created by Angelo Zamudio, Yat Sze So
 */
import java.util.LinkedList;

public class FCFSScheduler extends Scheduler {

    /**
     * FCFSScheduler is created calling the super constructor
     * from class Scheduler.
     * @param q is the JobQ passed in from main class.
     * @param n_jobs is the number of jobs in the JobQ.
     */
    public FCFSScheduler(LinkedList<PCB> q, int n_jobs){
        super(q, n_jobs);
    }//constructor

    public void start_Burst(){//removed PCB x
        if(ReadyQueue.isEmpty() && !BlockedQueue.isEmpty()) {
            updateTimer();
        }else {
            PCB x=ReadyQueue.pop();
            System.out.println("PCD id #: "+x.jobID);
            System.out.println("READY SIZE: "+ReadyQueue.size());
            x.wait_time+=(globalTimer-x.ready_arrival);
            int i=0;
            if(x.currentBurst < x.bursts.length){
                x.setBurstTime(x.currentBurst);
                System.out.println("PCD id #: "+x.jobID+" is in CPU...");
                while(i < x.curr_cpu_burst ){

                    updateTimer();
                    i++;
                    //ioBurst();

                }//while
                //System.out.println("PCD id #: "+x.jobID+" finished CPU BURST, entering I/0....."+globalTimer);
                if(x.currentBurst == x.bursts.length-1){
                    completionExec(x);
                    System.out.println("JOBS COMPLETED: "+ jobs_completed);
                }else{
                    BlockedQueue.add(x);
                }//else

            }//if
        }//else
    }//start_burst

    public void complete(){
        System.out.println("=================Scheduling End Report=====================");
        System.out.println("Scheduling Algorithm: FCFS");
        System.out.println("Cpu Clock Value: "+(globalTimer-1));
        System.out.println("Average Processing time: "+ (s_processing_time / jobs));
        System.out.println("Average Wait Time: "+ (s_waiting_time / jobs));
        System.out.println("Average Turnaround Time: "+ (s_turnaround_time / jobs));
        System.out.println("Total jobs Completed: "+jobs_completed);
        System.out.println("=================Scheduling End Report=====================");
    }//complete

}//FCFSScheduler
