import java.util.*;

/**
 * Created by AngeloZ on 3/16/16.
 */

public class Scheduler {
	public int globalTimer = 0;
	public LinkedList<PCB> ReadyQueue;
	public LinkedList<PCB> JobQueue;
	public LinkedList<PCB> BlockedQueue;
	public int readyQ_Max      = 0;
	public int jobs_completed  = 0;
    public int blocktime=10;
    public int s_processing_time = 0, s_waiting_time = 0, s_turnaround_time = 0;
    public int quantum;

	public Scheduler(){
		ReadyQueue = new LinkedList<PCB>();
		JobQueue=new LinkedList<PCB>();
		BlockedQueue=new LinkedList<PCB>();
	}//constructor
	
	public void fillJobQueue(PCB x){
        JobQueue.add(x);
	}//fillJobQueue
	
	public void addtoReady(){
        if(!JobQueue.isEmpty()){
            JobQueue.peek().ready_arrival=globalTimer;
            ReadyQueue.add(JobQueue.remove());
            readyQ_Max++;
        }//if
	}//addtoReady

	public void ioBurst() {//removed PCB x
        if (!BlockedQueue.isEmpty()){
            PCB x = BlockedQueue.peek();
            if (blocktime == 1) {
                System.out.println("PCB id #: " + x.jobID + " finished i/o....");
                System.out.println("PCB id #: " + x.jobID + " returning to ReadyQ....");
                BlockedQueue.peek().ready_arrival = globalTimer;
                blocktime=10;
                ReadyQueue.add(BlockedQueue.remove());
            }else{
               blocktime=(blocktime-1);
            }
        }//if Blocked
	}//burst

	public void updateTimer(){
		if(globalTimer%200==0){
            System.out.println();
            System.out.println("=================TIMER UPDATE=====================");
			System.out.println("Global Timer = "+globalTimer);
			System.out.println("Ready Queue Size = "+ ReadyQueue.size());
            if(!BlockedQueue.isEmpty()){
				System.out.println("Blocked Queue Size = "+ BlockedQueue.size());
			}else{
				System.out.println("Blocked Queue Size = 0");
			}
            System.out.println("Jobs Completed: "+ jobs_completed);
            System.out.println("=================TIMER UPDATE=====================");
            System.out.println();
            globalTimer++;
		}else{
           //System.out.println(globalTimer++);
            globalTimer++;
		}//else
	}//updateTimer

    public void completionExec(PCB x){
        System.out.println("PCB id #: " +x.jobID +" HAS EXECUTED COMPLETELY");
        x.completion_time=globalTimer;
        x.printPCBCompleted();
        s_processing_time += x.processing_time;
        s_waiting_time += x.wait_time;
        s_turnaround_time += x.turnaround_time;
        jobs_completed++;
        readyQ_Max-=1;
        addtoReady();
    }



///////////////////////////////////////////////////////////////////////////////////////

	public void printJob() {
        while (JobQueue.peek() != null) {
            JobQueue.remove().printPCB();
        }
    }
	public void printRQueue(){
		LinkedList<PCB> temp= ReadyQueue;
		if(temp.peek()!=null){
			System.out.println("----------readyQ---------------");
			while(temp.peek()!=null){
				temp.remove().printPCB();
			}
			System.out.println("----------readyQ---------------");
		}//while
	}//PrintQueue

    public void printBQueue(){
        LinkedList<PCB> temp= BlockedQueue;
        if(temp.peek()!=null){
            System.out.println("----------BlockedQ---------------");
            while(temp.peek()!=null){
                temp.remove().printPCB();
            }
            System.out.println("----------BlockedQ---------------");
        }//while
    }//PrintQueue
	
}//LTScheduler
