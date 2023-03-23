import java.util.ArrayList;

public class MilliPark {

    private String milliparkAdi;
    private int ilanYili;
    private ArrayList<String> ilAdlari;
    double yuzOlcumu;
    private  String sehirler;

    public double getYuzOlcumu() {
        return yuzOlcumu;
    }

    // constructor
    public MilliPark(String milliparkAdi, int ilanYili, ArrayList<String> ilAdlari, double yuzOlcumu){
        this.milliparkAdi = milliparkAdi;
        this.ilanYili = ilanYili;
        this.ilAdlari = ilAdlari;
        this.yuzOlcumu = yuzOlcumu;
        this.sehirler="";
        for(int i=0; i<this.ilAdlari.size() ; i++){
            this.sehirler += this.ilAdlari.get(i) +" ";
        }
    }

    public String toString() {
        return "MilliPark = {" +
                "Ad ='" + this.milliparkAdi + '\'' +
                ",İlan Yılı =" + this.ilanYili +
                ", İl Adları =" + this.sehirler +
                ", Yüz Ölçümü =" + this.yuzOlcumu +
                '}';
    }
}
