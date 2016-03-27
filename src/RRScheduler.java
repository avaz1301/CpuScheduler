/**
 * Created by AngeloZ on 3/16/16.
 */
public class RRScheduler extends Scheduler {

    public RRScheduler(int q){
        super();
        quantum=q;
    }//constructor

    public void start_Burst(){//removed PCB x
        if(ReadyQueue.isEmpty() && !BlockedQueue.isEmpty()){
            updateTimer();
            ioBurst();
        }else {
            PCB x=ReadyQueue.pop();

            x.wait_time+=(globalTimer-x.ready_arrival);

            if(x.currentBurst < x.bursts.length/*x.burstCount*/){
                System.out.println("PCD id #: "+x.jobID+" is in CPU...");

                while(x.curr_cpu_burst>0 /*x.curr_cpu_burst*/){

                    if(quantum==0){
                        System.out.println("QUANTUM EXPIRED>>>");
                        break;
                    }else{
                        updateTimer();
                        ioBurst();
                        x.curr_cpu_burst=(x.curr_cpu_burst-1);
                        quantum=quantum-1;
                    }//else

                }//while
                if(x.curr_cpu_burst==0){
                    quantum=1;
                    x.setBurstTime(x.currentBurst);
                    System.out.println("PCD id #: "+x.jobID+" finished CPU BURST, entering I/0...");
                    BlockedQueue.add(x);
                }else{
                    quantum=1;
                    x.ready_arrival=globalTimer;
                    ReadyQueue.add(x);
                }//else
            }else{
                completionExec(x);
            }//else
        }//BIG else
    }//start_burst

    public void complete(){
        System.out.println("=================Scheduling End Report=====================");
        System.out.println("Scheduling Algorithm: RR");
        System.out.println("Cpu Clock Value: " + globalTimer);
        System.out.println("Average Processing time: " + (s_processing_time / 526));
        System.out.println("Average Wait Time: " + (s_waiting_time / 526));
        System.out.println("Average Turnaround Time: " + (s_turnaround_time / 526));
        System.out.println("Total jobs Completed: " + jobs_completed);
        System.out.println("=================Scheduling End Report=====================");
    }//complete

}//RRScheduler
