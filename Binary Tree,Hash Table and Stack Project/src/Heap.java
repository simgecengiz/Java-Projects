public class Heap {
    private Node[] heap_Dizisi;
    private final int maxSize;
    private int currentSize;

    public Heap(int boyut){
        maxSize = boyut;
        currentSize = 0;
        heap_Dizisi = new Node[maxSize];
    }

    public boolean isEmpty() {
        return currentSize == 0; }

    public void insert(MilliPark millipark){
        if(currentSize == maxSize){
            return;}
        Node newNode = new Node(millipark);
        heap_Dizisi[currentSize] = newNode;
        trickleUp(currentSize++);
    }

    public void trickleUp(int index){
        int parent = (index-1) / 2;
        Node bottom = heap_Dizisi[index];
        while( index > 0 && heap_Dizisi[parent].getMilliPark().getYuzOlcumu() < bottom.getMilliPark().getYuzOlcumu()){
            heap_Dizisi[index] = heap_Dizisi[parent];
            index = parent;
            parent = (parent-1) / 2;
        }
        heap_Dizisi[index] = bottom;
    }

    public Node remove(){
        Node root = heap_Dizisi[0];
        heap_Dizisi[0] = heap_Dizisi[--currentSize];
        trickleDown(0);
        return root;
    }
    public void trickleDown(int index){
        int gecici;
        Node top = heap_Dizisi[index];
        while(index < currentSize/2){
            int leftChild = 2*index+1;
            int rightChild = leftChild+1;
            if(rightChild < currentSize && heap_Dizisi[leftChild].getMilliPark().getYuzOlcumu() < heap_Dizisi[rightChild].getMilliPark().getYuzOlcumu()){
                gecici = rightChild;}
            else{
                gecici = leftChild;}
            if(top.getMilliPark().getYuzOlcumu() >= heap_Dizisi[gecici].getMilliPark().getYuzOlcumu()){
                break;}
            heap_Dizisi[index] = heap_Dizisi[gecici];
            index = gecici;
        }
        heap_Dizisi[index] = top;
    }



}



