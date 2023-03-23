import java.util.ArrayList;
public class MilliPark {
    private String milliparkAdi;
    private String ilanYili;
    private String sehir;
    private double yuzOlcumu;

    private String bilgiParagrafi =" ";
    private ArrayList<String> info;

    public double getYuzOlcumu() {
        return yuzOlcumu;
    }

    public String getSehir() {
        return sehir;
    }

    public String getMilliparkAdi() {
        return milliparkAdi;
    }

    public void setIlanYili(String ilanYili) {
        this.ilanYili = ilanYili;
    }

    public String getIlanYili() {
        return ilanYili;
    }

    // constructor
    public MilliPark(String milliparkAdi, String ilanYili, String sehir, double yuzOlcumu , ArrayList<String>info) {
        this.milliparkAdi = milliparkAdi;
        this.ilanYili = ilanYili;
        this.yuzOlcumu = yuzOlcumu;
        this.sehir = sehir;
        this.info = info;
        for (String s: info) {
            bilgiParagrafi += s;
        }
    }

    public String toString() {
        return "MilliPark = {" +
                "Ad = " + this.milliparkAdi +
                ",İlan Yılı =" + this.ilanYili +
                ", Şehir =" + this.sehir +
                ", Yüz Ölçümü =" + this.yuzOlcumu +
                ",Milli Park Hakkında Bilgiler :" + this.bilgiParagrafi +
                "}";
    }

}
