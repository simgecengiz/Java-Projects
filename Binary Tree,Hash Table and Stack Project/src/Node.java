public class Node {
    // Heap class'ında kullanmak için oluşturduğum node sınıfı
    private MilliPark iData;
    public Node(MilliPark mp){
        this.iData = mp; }

    public MilliPark getMilliPark(){
        return iData; }

    public void setMilliPark(MilliPark id){
        iData = id; }
}

