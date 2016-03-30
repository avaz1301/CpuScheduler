/**
 * Created by Angelo Zamudio, Yat Sze So
 */
import java.util.*;

public class Scheduler {
	public int globalTimer = 0;
	public LinkedList<PCB> ReadyQueue;
	public LinkedList<PCB> JobQueue;
	public LinkedList<PCB> BlockedQueue;
	public int readyQ_Max      = 0;
	public int jobs = 0, jobs_completed  = 0;
    public int blocktime=10;
    public int s_processing_time = 0, s_waiting_time = 0, s_turnaround_time = 0;
    public int quantum;


    /**
     * This is the constructor for the super calss Scheduler from which
     * each of the 3 distinct schedulers will inherit their methods from.
     *
     * @param x This is the JobQ passed into the constructor from the main
     *          when the Scheduler is instantiated.
     * @param n_jobs int variable represents amount of jobs in JobQ.
     */
	public Scheduler(LinkedList<PCB> x, int n_jobs){
        JobQueue=x;
		ReadyQueue = new LinkedList<PCB>();
		BlockedQueue=new LinkedList<PCB>();
        jobs=n_jobs;
        //the following while fills the ReadyQueue witht he first 10
        //jobs in the JobQueue.
        while (readyQ_Max < 10 && !JobQueue.isEmpty()){
            addtoReady();
        }
        //this loop starts the cpu simulation and continues until all
        //jobs that were initially in job JobQueue have executed completely.
        while(jobs_completed != jobs){
            start_Burst();
        }
        //print summary of scheduling algorithm.
        complete();
	}//constructor

    /**
     * Method which checks if there are still jobs present in JobQueue
     * before adding the head PCB of JobQueue to ReadyQueue.
     */
	public void addtoReady(){
        if(!JobQueue.isEmpty()){
            JobQueue.peek().ready_arrival=globalTimer;
            ReadyQueue.add(JobQueue.remove());
            readyQ_Max++;
        }//if
	}//addtoReady

    /**
     * method to simulate cpu processing. To be overridden in inheriting
     * classes.
     */
    public void start_Burst(){};

    /**
     * This method simulates a PCB object in Blocked/IO Queue where they
     * will wait for 10 time units as specified and be returned to Ready
     * Queue if there are more remaining bursts.
     */
	public void ioBurst() {
        //first check if there is PCB in BlockedQueue.If Blocked Queue is
        //empty do nothing.
        if (!BlockedQueue.isEmpty()){
            PCB x = BlockedQueue.peek();
            //If time in BlockQueue is 10 PCB is popped from Blocked
            //Queue and added to the end of ReadyQueue.Else cycle one
            //more time unit.
            if (blocktime == 0) {
                BlockedQueue.peek().ready_arrival = globalTimer;
                blocktime=10;
                ReadyQueue.add(BlockedQueue.remove());
            }else{
               blocktime=(blocktime-1);
            }//else
        }//if
	}//burst

    /**
     * This is the method which increments globalTimer by one for every simulated
     * time unit of CPU processing. Every 200 time units a summary is printed. This
     * class also drives/checks Blocked Queue by calling ioBurst().
     */
	public void updateTimer(){
        //If time is a multiple of 200 print out update summary.
		if(globalTimer%200==0){
            System.out.println();
            System.out.println("=================TIMER UPDATE=====================");
			System.out.println("Global Timer = "+globalTimer);
			System.out.println("Ready Queue Size = "+ ReadyQueue.size());
            //if block queue empty at time of summary print 0 else print BlockQueue size
            if(!BlockedQueue.isEmpty()){
				System.out.println("Blocked Queue Size = "+ BlockedQueue.size());
			}else{
				System.out.println("Blocked Queue Size = 0");
			}//else
            System.out.println("Jobs Completed: "+ jobs_completed);
            System.out.println("=================TIMER UPDATE=====================");
            System.out.println();
            //check BlockedQueue
            ioBurst();
            //increment timer
            globalTimer++;
		}else{
            ioBurst();
            globalTimer++;
		}//else
	}//updateTimer

    /**
     * Method will print out completed execution summary for respective scheduling
     * algorithm. To be overridden in inheriting classes.
     */
    public void complete(){};

    /**
     * This Method takes in the completely executed PCB object recording all necessary
     * time information. and adding the PCB processing/waiting/turnaround time to the
     * global total
     * @param x Is te PCB that has completed execution and will not be reentering
     *          the system
     */
    public void completionExec(PCB x){
        x.completion_time=globalTimer;
        //print necessary PCB information
        x.printPCBCompleted();
        s_processing_time += x.processing_time;
        s_waiting_time += x.wait_time;
        s_turnaround_time += x.turnaround_time;
        jobs_completed=jobs_completed+1;
        readyQ_Max-=1;
        addtoReady();
    }//completionExec
}//LTScheduler
