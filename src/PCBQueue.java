import java.util.LinkedList;
import java.util.Queue;

public class PCBQueue{
	public Queue<PCB> tQueue;
	private int counter;
	
	public PCBQueue(int c){
		tQueue= new LinkedList<PCB>();
		counter=c;
	}//constructor
	
	public PCBQueue(){
		tQueue=new LinkedList<PCB>();
	}//constructor
	
	
	public void addElement(PCB x){
		tQueue.add(x);
		
	}//addElement
	
	public void removeElement(){
		tQueue.remove();
	}//removeElement
	
	public PCB getHeadPCB(){
		return tQueue.remove();
	}//getHeadPCB
	
}//PCBQueue
