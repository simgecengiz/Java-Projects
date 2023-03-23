import java.util.Arrays;
import java.util.Collections;

public class NeuralNetwork {
    private Neuron N1;
    private Neuron N2;
    private Neuron N3;

    private double [] uzunluklar ;
    public NeuralNetwork(Neuron N1,Neuron N2,Neuron N3) {
        this.N1=N1;
        this.N2=N2;
        this.N3=N3;
    }
    public void cıktılar(double value1,double value2,double value3) {
            Double [] array = {value1,value2,value3};
            //Collections sınıfından max değer hesaplanıyor
            double max = Collections.max(Arrays.asList(array));
            //Hesaplanan max değere göre setIsMax değişkeni uyarlanıyor.
            if(value1 == max) {
                this.N1.setIsMax(1);
                 this.N2.setIsMax(0);
                 this.N3.setIsMax(0);
             }
             else if(value2 ==max) {

                this.N1.setIsMax(0);
                 this.N2.setIsMax(1);
                 this.N3.setIsMax(0);
             }
             else if(value3 == max) {

                this.N1.setIsMax(0);
                 this.N2.setIsMax(0);
                 this.N3.setIsMax(1);
             }

    }
    public void egitim( double katsayı) {
            //En büyük value değerimiz ısMax olarak tanımlı eğer ısMax 1 değilse ve target değerimiz 1 ise verimizin eğitilmesi gerekir.

            if (this.N1.getTarget()==1 && this.N1.getIsMax()!=1) {
                //Çıkması gereken nöronun değerleri arttırılıyor.
                this.N1.increaseWeight(katsayı,this.uzunluklar);
                //Çıkmasını beklemediğimiz fakat max değere sahip olan nöronun değerleri azaltılıyor.
                if (this.N2.getIsMax() == 1) {
                    this.N2.decreaseWeight(katsayı,this.uzunluklar);
                }
                else {
                    this.N3.decreaseWeight(katsayı,this.uzunluklar);
                }
            }
            else if (this.N2.getTarget()==1 && this.N2.getIsMax()!=1){
                //Çıkması gereken nöronun değerleri arttırılıyor.
                this.N2.increaseWeight(katsayı,this.uzunluklar);
                //Çıkmasını beklemediğimiz fakat max değere sahip olan nöronun değerleri azaltılıyor.
                if (this.N1.getIsMax() ==1) {
                    this.N1.decreaseWeight(katsayı,this.uzunluklar);
                }
                else {
                    this.N3.decreaseWeight(katsayı,this.uzunluklar);
                }

            }
            else if (this.N3.getTarget()==1 && this.N3.getIsMax()!=1) {
                //Çıkması gereken nöronun değerleri arttırılıyor.
                this.N3.increaseWeight(katsayı,this.uzunluklar);
                //Çıkmasını beklemediğimiz fakat max değere sahip olan nöronun değerleri azaltılıyor.
                if (this.N1.getIsMax() ==1) {
                    this.N1.decreaseWeight(katsayı,this.uzunluklar);
                }
                else {
                    this.N2.decreaseWeight(katsayı,this.uzunluklar);
                }
            }
    }
    public int cıktı_al(int sayac) {
        //Beklenen değerimiz ile max değerden gelen verimiz uyuşuyorsa doğru sonuç demektir.
        //Koşul sağlandığında sayaç arttırılır
        if (this.N1.getIsMax()==1 && (this.N1.getTarget()==1)) {
            sayac+=1;
        }
        else if(this.N2.getIsMax()==1 && this.N2.getTarget()==1) {

            sayac+=1;
        }
        else if (this.N3.getIsMax()==1 && this.N3.getTarget()==1) {
            sayac+=1;
        }
        return sayac;
    }
    public double calculateValue(String [] list,double [] weights) {
        //Çiceklerin yaprak uzunluk ve genişliklerini tutmak için dizi oluşturuluyor.
        this.uzunluklar = new double[4];

        //Uzunluklar dizinin ilgili indisine atanıyor.
        this.uzunluklar[0]=Double.parseDouble(list[0])/10.0; //canak yaprak uzunluğu
        this.uzunluklar[1]=Double.parseDouble(list[1])/10.0; //tac yaprak uzunluğu
        this.uzunluklar[2]=Double.parseDouble(list[2])/10.0; //canak yaprak genisliği
        this.uzunluklar[3]=Double.parseDouble(list[3])/10.0; //tac yaprak genisliği

        //Formüle göre değerimiz ağırlıklarla işleme girerek hesaplanıyor.
        double value=(this.uzunluklar[0]*weights[0])+(this.uzunluklar[1]*weights[1])+(this.uzunluklar[2]*weights[2])+(this.uzunluklar[3]*weights[3]);

        return value;
    }

}
