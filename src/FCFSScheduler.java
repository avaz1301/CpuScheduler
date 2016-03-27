/**
 * Created by AngeloZ on 3/16/16.
 */
public class FCFSScheduler extends Scheduler {

    public FCFSScheduler(){
        super();
    }//constructor

    public void start_Burst(){//removed PCB x
        if(ReadyQueue.isEmpty() && !BlockedQueue.isEmpty()){
            updateTimer();
            ioBurst();
        }else {
            PCB x=ReadyQueue.pop();
            x.wait_time+=(globalTimer-x.ready_arrival);
            int i=0;
            if(x.currentBurst < x.bursts.length/*x.burstCount*/){
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

    public void complete(){
        System.out.println("=================Scheduling End Report=====================");
        System.out.println("Scheduling Algorithm: FCFS");
        System.out.println("Cpu Clock Value: "+globalTimer);
        System.out.println("Average Processing time: "+ (s_processing_time / 526));
        System.out.println("Average Wait Time: "+ (s_waiting_time / 526));
        System.out.println("Average Turnaround Time: "+ (s_turnaround_time / 526));
        System.out.println("Total jobs Completed: "+jobs_completed);
        System.out.println("=================Scheduling End Report=====================");
    }//complete

}//FCFSScheduler
