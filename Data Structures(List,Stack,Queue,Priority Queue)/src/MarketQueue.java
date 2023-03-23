import java.util.ArrayList;

public class MarketQueue {
    private final ArrayList<Integer> marketQ;
    private int islemTamamlanmaSuresi = 3;
    private int islemSuresi = 0;
    private int ortSure = 0;

    private int genelToplamIslemSuresi = 0;

    public MarketQueue(){
        this.marketQ = new ArrayList<>();
    }

    public void insert(int item){
        marketQ.add(item);
    }

    public int remove(){
        if(marketQ.isEmpty()){
            return 0;
        }
        else{
            islemSuresi += (marketQ.get(0)*islemTamamlanmaSuresi);
            System.out.println("İşlem Tamamlandı. İşlem Tamamlanma Süresi : " + islemSuresi);
            genelToplamIslemSuresi += islemSuresi;
            return marketQ.remove(0);
        }
    }

    public boolean isEmpty(){
        return marketQ.isEmpty();
    }

    public int size(){
        return marketQ.size();
    }

    public void ortSureBulma(int boyut){
        ortSure = genelToplamIslemSuresi / boyut;
        System.out.println("Ortalama Süre : " + ortSure);
    }
}
