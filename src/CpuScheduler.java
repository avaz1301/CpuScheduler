/**
 * Created by AngeloZ on 3/3/16.
 */
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CpuScheduler {
    public static void main(String args[]) throws FileNotFoundException{
        int id=0,arrive=0, num_bursts=0, total_processing=0;
        int[] brst_array=null;
        FCFSScheduler lt1=new FCFSScheduler();
        SJFScheduler lt2=new SJFScheduler();
        RRScheduler lt3=new RRScheduler(1);
        String algo="RR";

        Scanner input = new Scanner(new File(args[0]));
        while(input.hasNextLine()){
            String line = input.nextLine();
            Scanner temp = new Scanner(line);
            while(temp.hasNext()){
                id=temp.nextInt();
                arrive=temp.nextInt();
                num_bursts=temp.nextInt();
                brst_array=new int[num_bursts];
                for(int i=0;i<num_bursts;i++){
                    brst_array[i]=temp.nextInt();
                    total_processing+=brst_array[i];
                }//for
            }//while temp
            if(algo.equals("FCFS")){
                lt1.fillJobQueue(new PCB(id,arrive,total_processing,brst_array));
            }else if(algo.equals("SJF")){
                lt2.fillJobQueue(new PCB(id,arrive,total_processing,brst_array));
            }else if(algo.equals("RR")){
                lt3.fillJobQueue(new PCB(id,arrive,total_processing,brst_array));
            }
            total_processing=0;
        }//while input
        if(algo.equals("FCFS")){
            while (lt1.readyQ_Max < 10){
                lt1.addtoReady();
            }
            while(lt1.jobs_completed != 526){
                lt1.start_Burst();
            }
            lt1.complete();

        }else if(algo.equals("SJF")){
            while(lt2.readyQ_Max<10){
                lt2.addtoReady();
            }
            while(lt2.jobs_completed != 526){
                lt2.start_Burst();
            }
            lt2.complete();

        }else if(algo.equals("RR")){
            while(lt3.readyQ_Max<10){
                lt3.addtoReady();
            }
            while(lt3.jobs_completed != 526){
                lt3.start_Burst();
            }
            lt3.complete();
        }//else if
    }//main
}//Driver
