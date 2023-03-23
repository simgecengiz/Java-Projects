import java.util.Random;
public class Neuron {
    private final Random random = new Random();
    private final double [] weights= new double[4];
    private int target=0;
    private int isMax=0;
    public Neuron() {
            setWeights();
    }
    public void setWeights(){
        //Random ağırlıklar oluşturuluyor.
        for(int i=0;i<weights.length;i++) {
            this.weights[i]=random.nextDouble(0.0,1.0);
        }
    }
    public double [] getWeights() {
        //Ağırlıklar dizisi döndürülüyor.
        return this.weights;
    }
    public void setTarget(int deger) {
        //Target değeri değiştiriliyor.
        this.target=deger;
    }
    public int getTarget() {
        //Target değeri döndürülüyor.
        return this.target;
    }

    public void increaseWeight(double katsayı,double [] uzunluklar) {
        //Eğitimde arttırılması gereken ağırlıklar için arttırma yapılıyor.
        for(int i=0;i<this.weights.length;i++) {
            this.weights[i]=this.weights[i]+(katsayı*uzunluklar[i]);
        }
    }
    public void decreaseWeight(double katsayı,double [] uzunluklar) {
        //Eğitimde azaltılması gereken ağırlıklar için azaltma yapılıyor.
        for(int i=0;i<this.weights.length;i++) {
            this.weights[i]=this.weights[i]-(katsayı*uzunluklar[i]);
        }
    }
    public static void changeWeights(Neuron N1,Neuron N2, Neuron N3) {
        //Noron ağırlıkları kendi fonksiyonuyla değiştiriliyor.
        N1.setWeights();
        N2.setWeights();
        N3.setWeights();
    }
    public void setIsMax(int deger) {
        //IsMax değeri atanıyor.
        this.isMax=deger;
    }
    public int getIsMax() {
        //IsMax değeri döndürülüyor.
        return this.isMax;
    }
}
