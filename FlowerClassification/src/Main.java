import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        //Neuronlar oluşturuluyor.
        Neuron N1 = new Neuron();
        Neuron N2 = new Neuron();
        Neuron N3 = new Neuron();
        //NeuralNetwork sınıfı oluşturuluyor.
        NeuralNetwork neuralNetwork = new NeuralNetwork(N1, N2, N3);
        //Dosyadan okuma yapılabilmesi için RandomAccessFile nesnesi oluşturuluyor.
        RandomAccessFile raf = new RandomAccessFile("iris.data","r");

        //3 tablo verileri için döngü oluşturuluyor
        for (int i = 0; i <3 ; i++) {
            System.out.printf("---------- Eğitim Numarası: %2s ---------- \n",i+1);
            epochs(N1, N2, N3, neuralNetwork, raf,0.01);
            epochs(N1, N2, N3, neuralNetwork, raf,0.005);
            epochs(N1, N2, N3, neuralNetwork, raf,0.025);
            System.out.println("*******************");
        }
    }
    private static void epochs(Neuron n1, Neuron n2, Neuron n3, NeuralNetwork neuralNetwork, RandomAccessFile raf,double katsayı) throws IOException {
        //Donguler methodu kullanılarak epok,katsayı,noronlar ağa gönderilerek eğitimler yapılıyor.
        System.out.println("Katsayı: "+katsayı);
        donguler(raf,50,neuralNetwork, n1, n2, n3,katsayı);
        //Her epoktan sonra ağırlıklar baştan random olarak atanıyor
        Neuron.changeWeights(n1, n2, n3);
        donguler(raf,20,neuralNetwork, n1, n2, n3,katsayı);
        Neuron.changeWeights(n1, n2, n3);
        donguler(raf,100,neuralNetwork, n1, n2, n3,katsayı);
        Neuron.changeWeights(n1, n2, n3);
    }
    public static void donguler(RandomAccessFile raf,int epok,NeuralNetwork neuralNetwork,Neuron N1,Neuron N2,Neuron N3,double katsayı) throws IOException {
        String [] array;
        String line;
        String tur;
        int sayac=0;
        double value_of_n1;
        double value_of_n2 ;
        double value_of_n3 ;
        //Epok döngüsü oluşuturuluyor.
        for (int i = 0; i <epok+1 ; i++) {
            //Raf satır okuması için while döngüsü açılıyor.
            while((line=raf.readLine())!=null) {
                //Veriler array ve tur veri yapılarına atılarak tutuluyor.
                array = line.split(",");
                tur = array[4];

                //Target değerleri tur değişkenine göre oluşturuluyor.

                if(tur.equals("Iris-setosa")) {
                    N1.setTarget(1);
                    N2.setTarget(0);
                    N3.setTarget(0);
                }
                else if (tur.equals("Iris-versicolor")) {
                    N1.setTarget(0);
                    N2.setTarget(1);
                    N3.setTarget(0);
                }
                else{
                    N1.setTarget(0);
                    N2.setTarget(0);
                    N3.setTarget(1);
                }
                //Max girdi değerleri hesaplanması için ağa ağırlıklar ve uzunluklar gönderiliyor.
                value_of_n1 = neuralNetwork.calculateValue(array,N1.getWeights());
                value_of_n2 = neuralNetwork.calculateValue(array,N2.getWeights());
                value_of_n3 = neuralNetwork.calculateValue(array,N3.getWeights());
                //Ağda max değerler hesaplanıyor.
                neuralNetwork.cıktılar(value_of_n1, value_of_n2, value_of_n3);
                if(i!=epok) {
                    //epok genişliği boyunca eğitim yapılıyor.
                    neuralNetwork.egitim(katsayı);
                }
                //Epok eğitimi bittiğinde doğruluk hesaplıyor
                else {
                    sayac=neuralNetwork.cıktı_al(sayac);
                }
            }
            //Veri okuduğumuz dosyanın imleci dosya sonuna gittiği için imleç dosya başına alınıyor.
            raf.seek(0);
        }
        //Epok sonucu eğitim verileri ekrana yazdırılıyor.
        System.out.printf("  %2s epoktan sonra :  %.2f \n",epok,(sayac/150.0)*100);
    }
}