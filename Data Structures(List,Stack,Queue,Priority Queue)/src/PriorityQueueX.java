import java.util.ArrayList;
public class PriorityQueueX {
    private final ArrayList<MilliPark> pq;

    public PriorityQueueX() {
        this.pq = new ArrayList<>();
    }
    public void insert(MilliPark mp) {
        pq.add(mp);
    }
    public MilliPark remove(){
        if(pq.isEmpty()){
            return null;
        }
        else{
            double min = pq.get(0).getYuzOlcumu();
            int index = 0;
            for(MilliPark mp : pq){
                if(mp.getYuzOlcumu() < min){
                    min = mp.getYuzOlcumu();
                    index = pq.indexOf(mp);
                }
            }
            return pq.remove(index);
        }
    }
    public boolean isEmpty(){
        return (pq.isEmpty());
    }
}















