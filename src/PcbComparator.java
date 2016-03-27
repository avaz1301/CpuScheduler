import java.util.Comparator;

/**
 * Created by AngeloZ on 3/16/16.
 */
public class PcbComparator implements Comparator<PCB> {
    public int compare(PCB x, PCB y){
        return x.curr_cpu_burst-y.curr_cpu_burst;
    }//compare
}//PcbComparator
