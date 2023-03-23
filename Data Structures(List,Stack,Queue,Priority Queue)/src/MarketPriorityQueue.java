import java.util.ArrayList;

public class MarketPriorityQueue {

    private final ArrayList<Integer> marketpq;

    private int islemTamamlanmaSuresi = 3;

    private int pq_islemSuresi = 0;

    private int pq_genelToplamIslemSuresi = 0;

    private int pq_ortSure = 0;


    public MarketPriorityQueue() {
        this.marketpq = new ArrayList<>();
    }

    public void insert(int item) {
        int son_eleman=this.marketpq.size()-1;
        if (marketpq.isEmpty() == true) {
            marketpq.add(item);
        }
        else if (item >this.marketpq.get(son_eleman)) {
            this.marketpq.add(item);
        }
        else {
            for(int i = 0; i < marketpq.size() ; i++){
                if(marketpq.get(i) > item){
                    marketpq.add(i,item);
                    break;
                }
            }
        }
    }

    public int remove() {
        if (marketpq.isEmpty()) {
            return 0;
        }
        else {
            pq_islemSuresi += (marketpq.get(0)*islemTamamlanmaSuresi);
            System.out.println("İşlem Tamamlandı. İşlem Tamamlanma Süresi : " + pq_islemSuresi);
            pq_genelToplamIslemSuresi += pq_islemSuresi;
            return marketpq.remove(0);
        }
    }

    public boolean isEmpty() {
        return marketpq.isEmpty();
    }

    public int size() {
        return marketpq.size();
    }

    public void ortSureBulma(int boyut){
        pq_ortSure = pq_genelToplamIslemSuresi / boyut;
        System.out.println("Ortalama Süre : " + pq_ortSure);
    }


}



