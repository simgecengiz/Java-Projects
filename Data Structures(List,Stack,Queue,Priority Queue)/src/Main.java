import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        final double karsilastirmasiniri = 15.000;
        String isim = null;
        double yuzOlcumu = 0.0;
        int ilanyili = 0;
        double kucukMilliParkYuzOlcumuToplami = 0.0;
        double buyukMilliParkYuzOlcumuToplami = 0.0;
        String[] parkBilgileriSatiri;
        String satir;

        // Milli Parkları tutacağım 2 boyutlu dizi oluşturuyorum
        // Dizinin her bir elemanı bir arraylist tutuyor.
        // Bu arraylistler de millipark nesnesini tutuyor.
        ArrayList<MilliPark>[] kucukveBuyukmilliparklar = new ArrayList[2];

        // Milli Parkın bulunduğu illeri tutmak için bir arraylist oluşturuyorum
        ArrayList<String> sehirAdlari = new ArrayList<>();

        // Milli Parkları tuttuğum listenin 0. indeksindeki arraylistte 15.000 hektardan küçük milli parkları tutuyorum.
        kucukveBuyukmilliparklar[0] = new ArrayList<>();
        // Milli Parkları tuttuğum listenin 1. indeksindeki listede 15.000 hektardan büyük milli parkları tutuyorum.
        kucukveBuyukmilliparklar[1] = new ArrayList<>();

        // Stack oluşturuyorum
        StackX stackx = new StackX();
        // Queue oluşturuyorum
        QueueX queuex = new QueueX();
        // PriorityQueue oluşturuyorum
        PriorityQueueX pqueuex = new PriorityQueueX();

        // Market sorusu için Queue oluşturuyorum.
        MarketQueue marketqX = new MarketQueue();
        // Market sorusu için Priority Queue oluşturuyorum.
        MarketPriorityQueue marketpqX = new MarketPriorityQueue();

        try {
            // Dosyayı okuyor
            FileReader readFile = new FileReader("dosya.txt");
            BufferedReader readBuff = new BufferedReader(readFile);
            while ((satir = readBuff.readLine()) != null) { // dosya boş değilse döngüye giriyor
                parkBilgileriSatiri = satir.split(","); // Dosyanın satırındaki bilgileri virgülle ayırıyor
                for (int i = 0; i < parkBilgileriSatiri.length; i++) {
                    if (i == 0) {
                        isim = parkBilgileriSatiri[i]; // isim yerleştirme
                    } else if (i == (parkBilgileriSatiri.length - 1)) {
                        yuzOlcumu = Double.parseDouble(parkBilgileriSatiri[i]); // yüzölçümü yerleştirme
                    } else if (i == (parkBilgileriSatiri.length - 2)) {
                        ilanyili = Integer.parseInt(parkBilgileriSatiri[i]); // ilan yılı yerleştirme
                    } else {
                        sehirAdlari.add(parkBilgileriSatiri[i]); // geriye kalan kısma şehirleri yerleştirme
                    }
                }

                MilliPark millipark = new MilliPark(isim, ilanyili, sehirAdlari, yuzOlcumu);

                // Oluşturduğum millipark nesnesini stack'e ekliyorum.
                stackx.push(millipark);

                // Oluşturduğum millipark nesnesini queue'e ekliyorum.
                queuex.insert(millipark);

                // Oluşturduğum millipark nesnesini priority queue'e ekliyorum.
                pqueuex.insert(millipark);

                if (millipark.yuzOlcumu < karsilastirmasiniri) {
                    // Yüz ölçümleri 15.000'den küçük olan milli parkları ekliyorum.
                    kucukveBuyukmilliparklar[0].add(millipark);
                }
                else {
                    // Yüz ölçümleri 15.000'den büyük olan milli parkları ekliyorum.
                    kucukveBuyukmilliparklar[1].add(millipark);
                }

                // Sadece o milli parka ait şehirlerin listelenmesi için işim bittikten sonra
                // şehir adları listesini temizliyorum.
                sehirAdlari.clear();
            }
        }
         catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("----------------------------------------");
        System.out.println("KÜÇÜK MİLLİ PARK LİSTESİ");
        for(MilliPark mp: kucukveBuyukmilliparklar[0]) {
            System.out.println(mp.toString());
            kucukMilliParkYuzOlcumuToplami += mp.yuzOlcumu;
        }
        System.out.println("Küçük Milli Parkların Yüz ölçümünün Toplamı : " + kucukMilliParkYuzOlcumuToplami);
        System.out.println("                                       ");
        System.out.println("BÜYÜK MİLLİ PARKLARIN LİSTESİ");
        for(MilliPark mp2: kucukveBuyukmilliparklar[1]) {
            System.out.println(mp2.toString());
            buyukMilliParkYuzOlcumuToplami += mp2.getYuzOlcumu();
        }
        System.out.println("Büyük Milli Parkların Yüz ölçümünün Toplamı : " + buyukMilliParkYuzOlcumuToplami);
        System.out.println("----------------------------------------");
        System.out.println("                                       ");
        System.out.println("*******************");
        System.out.println(":: MİLLİ PARK STACK ::");
        while(!stackx.isEmpty()) {
            System.out.println(stackx.pop());
        }
        System.out.println("*******************");
        System.out.println("                                       ");
        System.out.println("-------------------");
        System.out.println(":: MİLLİ PARK QUEUE ::");
        while(!queuex.isEmpty()){
            System.out.println(queuex.remove());
        }
        System.out.println("-------------------");
        System.out.println("                                       ");
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^");
        System.out.println(":: MİLLİ PARK PRİORİTY QUEUE ::");
        while(!pqueuex.isEmpty()){
            System.out.println(pqueuex.remove());
        }
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^");
        System.out.println("                                       ");


        System.out.println("4.SORUNUN CEVABI");
        //Bir marketteki tek kasada bekleyen müşterilerin sepetlerinde ürünleri oluşturduğum kuyruğa ekledim.
        System.out.println("Bir marketteki tek kasada bekleyen müşterilerin sepetlerindeki ürünleri kuyruğa ekleyelim.");
        marketqX.insert(8);
        marketqX.insert(9);
        marketqX.insert(6);
        marketqX.insert(7);
        marketqX.insert(10);
        marketqX.insert(1);
        marketqX.insert(11);
        marketqX.insert(5);
        marketqX.insert(3);
        marketqX.insert(4);
        marketqX.insert(2);
        System.out.println("                                       ");
        int marketqboyut= marketqX.size();
        // Marketteki sırada her bir müşterinin işlemi tamamlanıyor.
        // Tamamlanan müşterileri listeden çıkarıyorum.
        while(!marketqX.isEmpty()){
            marketqX.remove();
        }
        //Bu kasa için müşterilerin ortalama işlem tamamlanma süresini bulup yazdırıyorum.
        marketqX.ortSureBulma(marketqboyut);
        System.out.println("                                       ");
        //Bir marketteki tek kasada bekleyen müşterilerin sepetlerinde ürünleri oluşturduğum öncelikli kuyruğa artan sırada ekledim.
        System.out.println("Bir marketteki tek kasada bekleyen müşterilerin sepetlerindeki ürünleri  öncelikli kuyruğa ekleyelim.");
        marketpqX.insert(8);
        marketpqX.insert(9);
        marketpqX.insert(6);
        marketpqX.insert(7);
        marketpqX.insert(10);
        marketpqX.insert(1);
        marketpqX.insert(11);
        marketpqX.insert(5);
        marketpqX.insert(3);
        marketpqX.insert(4);
        marketpqX.insert(2);
        System.out.println("                                       ");

        // Markette her bir müşterinin işlemi tamamlanıyor.
        // Tamamlanan müşterileri listeden çıkarıyorum.
        int marketpqBoyut = marketpqX.size();
        while(!marketpqX.isEmpty()){
            marketpqX.remove();
        }
        //Bu kasa için müşterilerin ortalama işlem tamamlanma süresini bulup yazdırıyorum.
        marketpqX.ortSureBulma(marketpqBoyut);

    }}
