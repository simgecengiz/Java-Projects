import java.util.ArrayList;

public class QueueX {
    private  final ArrayList<MilliPark> queue;
    public QueueX(){
        queue = new ArrayList<>();
    }
    public void insert(MilliPark mp){
        int kuyrukSonu = queue.size();
        queue.add(kuyrukSonu, mp);
    }

    public MilliPark remove(){

        if(queue.isEmpty()){
            return null;
        }
        else{
            return this.queue.remove(0);
        }
    }
    public boolean isEmpty(){
        return(queue.isEmpty());
    }

    public int size(){
        return queue.size();
    }
}
