public class Kelime {
    // Bili cümlelerindeki kelimeleri ağacı eklerken kolaylık olması için yarattığım bir sınıf
    // Bili cümlelerindeki her bir kelimeyi eklerken kelime sınıfından bir nesne oluşturuyorum.
    // Oluşturuğum kelime nesnesinin datasına bilgi vümlelerinden çektiğim kelimeyi eşitliyorum.
    // Eğer aynı keimeyi bir daha eklemek istersem bunu direkt olarak kelimenin sayacını arttırarak yapıyorum.
    private String data;
    public int sayac = 1;

    public Kelime(){
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


}
