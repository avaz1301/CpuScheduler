import java.util.Collections;

/**
 * Created by AngeloZ on 3/16/16.
 */
public class SJFScheduler extends Scheduler {

    public SJFScheduler(){
        super();
    }//constructor

    public void start_Burst(){//removed PCB x
        if(ReadyQueue.isEmpty() && !BlockedQueue.isEmpty()){
            updateTimer();
            ioBurst();
        }else {
            shortestJobInReady();
            PCB x=ReadyQueue.remove();
            x.wait_time+=(globalTimer-x.ready_arrival);
            int i=0;
            if(x.currentBurst < x.bursts.length){
                x.setBurstTime(x.currentBurst);
                System.out.println("PCD id #: "+x.jobID+" is in CPU...");
                while(i < x.curr_cpu_burst){
                    updateTimer();
                    ioBurst();
                    i++;
                }//while
                System.out.println("PCD id #: "+x.jobID+" finished CPU BURST, entering I/0...");
                BlockedQueue.add(x);
            }else{
                completionExec(x);
            }//else
        }//else
    }//start_burst

    public int shortestJobInReady(){
        Collections.sort(ReadyQueue, new PcbComparator());
        return 1;
    }//shortestJobInReady

    public void complete(){
        System.out.println("=================Scheduling End Report=====================");
        System.out.println("Scheduling Algorithm: Shortest Job First");
        System.out.println("Cpu Clock Value: " + globalTimer);
        System.out.println("Average Processing time: " + (s_processing_time / 526));
        System.out.println("Average Wait Time: " + (s_waiting_time / 526));
        System.out.println("Average Turnaround Time: " + (s_turnaround_time / 526));
        System.out.println("Total jobs Completed: " + jobs_completed);
        System.out.println("=================Scheduling End Report=====================");
    }//complete

}//SJFScheduler
