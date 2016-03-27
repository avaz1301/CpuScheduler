import java.util.*;
/**
 * Created by AngeloZ on 3/16/16.
 */

public class PCB {
	protected int jobID=0;
	protected boolean ready=false, running=false, blocked=false;
	protected int[] bursts;
	protected int currentBurst=0;
    protected int curr_cpu_burst;
	protected int arrival_time=0, completion_time=0, ready_arrival=0, processing_time=0,
               wait_time=0, turnaround_time=0, ioTime=0;

	public PCB(int id, int ar, int tp, int[] brst){
		arrival_time = ar;
		jobID = id;
		bursts = brst;
        ioTime = (10*(bursts.length-1));
        processing_time = (tp + ioTime);
		currentBurst = 0;
        setBurstTime(currentBurst);
	}//constructor

    public void setBurstTime(int x){
        curr_cpu_burst= bursts[x];
        currentBurst+=1;
    }
	
	public void printPCB(){
		System.out.println("id: " + jobID);
		System.out.println("arrival time: " + arrival_time);
		System.out.println("burst Count: " + bursts.length);
		System.out.println("At burst = " + bursts[currentBurst]);
		System.out.println("IO TIME REMAINING : "+ioTime);
		System.out.print("Burst Array :");
		for(int i=0;i < bursts.length;i++){
			System.out.print(bursts[i]+", ");
		}
		System.out.println();	
	}//printPCB

    public void printPCBCompleted(){
        turnaround_time = (completion_time-arrival_time);
        System.out.println();
        System.out.println("=================JOB COMPLETED=====================");
        System.out.println("id: " + jobID);
        System.out.println("arrival time: " + arrival_time);
        System.out.println("Completion Time: " + completion_time);
        System.out.println("Processing Time: " + processing_time);
        System.out.println("Waiting Time: " + wait_time);
        System.out.println("Turnaround Time: " + turnaround_time);
        System.out.println("=================JOB COMPLETED=====================");
        System.out.println();
    }//printPCB
	
	public void setReady(){
		if(ready){
			ready=false;
		}else{
			ready=true;
		}
	}//setReady
	public void setRunning(){
		if(running){
			running=false;
		}else{
			running=true;
		}
	}//setRunning
	public void setBlocked(){
		if(blocked){
			blocked=false;
		}else{
			blocked=true;
		}
	}//setBlocked

}//PCB
