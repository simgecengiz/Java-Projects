import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // milli parka ait bilgiler
        String isim = null;
        String ilanyili = null;
        String sehir = null;
        double yuzOlcumu = 0.0;

        int sayac = 0;

        // Biligiler.txt'yi okuduktan sora isimleri bu arraylis'e ekliyorum.
        ArrayList<String> mpIsimListesi = new ArrayList<>();

        // Her bir milliparka ait bilgileri içeren arraylistlerden oluşan bir arraylist
        // Yani 48 tane arraylist içeriyor
        // 48 tane millipark var
        ArrayList<ArrayList<String>> milliparkBilgi_GenelListe = new ArrayList<>();

        // Oluşturduğum millipark nesnelerini eklediğim arraylist
        ArrayList<MilliPark> milliParklar = new ArrayList<>();

        // Dosya okurken kullanıyorum
        String[] parkBilgileriSatiri;
        String line;
        String line_2;

        //MilliPark için tree oluşturuyorum
        BinarySearchTree_millipark tree = new BinarySearchTree_millipark();

        // Milliparklar hakkındaki bilgilerdeki kelimelerin tümünü yeni bir tree'ye ekliyorum
        BinarySearchTree_millipark tree_kelimeler = new BinarySearchTree_millipark();

        String[] kelimeDizisi;

        // Hashtable oluşturuyorum
        Hashtable<String , MilliPark> hashtable = new Hashtable();

        // Heap oluşturuyorum , oluştururken dizi kullanıyorum
        // Bu yüzden millipark sayısını heap oluştururken belirtiyorum.
        Heap theHeap = new Heap(48);

        // Sıralamak istediğim dizi
        int numbers[] = {11, 16, 8, 7, 20, 4, 14};
        // Sıralama algoritmamdan nesne oluşturuyorum.
        SelectionSort selectionSort = new SelectionSort();


        FileReader readFile = new FileReader("bilgiler.txt");
        BufferedReader readBuff = new BufferedReader(readFile);
        while ( (line = readBuff.readLine()) != null){

            // ## ayırıyor ve bana iki boyutlu bir dizi döndürüyor
            String[] parts = line.split("##");
            // parts listesinin 0. indeksinde isimler var
            // isimleri arraylist'e ekliyoruz
            mpIsimListesi.add(parts[0]);
            // parts listesinin 1.indeksinde millipark hakkında bilgi var
            // ordaki bilgileri elde etmek için && ile ayırıyoruz.
            String[] parts_2 = parts[1].split("&&");
            // bu da bize her cümleyi eleman yapıp bir dizi döndürüyor
            // biz de döndürülen dizideki cümleleri tek tek arraylistimize ekliyoruz.
            // her bir millipark için o milli parka ait bilgiler içeren arraylist oluşturuyoruz.
            // daha sonra bu arraylistleri genel bilgi arraylistine ekliyoruz.
            // Her bir milliparka ait bilgileri içeren özel arraylist
            ArrayList<String> milliparkBilgi_Ozel= new ArrayList<>();
            milliparkBilgi_Ozel.addAll(Arrays.asList(parts_2));
            milliparkBilgi_GenelListe.add(milliparkBilgi_Ozel);
            }

        // MilliPark listesini ayıklıyoruz
        FileReader readFile_2 =new FileReader("parklar.txt");
        BufferedReader readBuff_2 = new BufferedReader(readFile_2);
        while ((line_2 = readBuff_2.readLine()) != null) {
            parkBilgileriSatiri = line_2.split(","); // Dosyanın satırındaki bilgileri virgülle ayırıyor
            for (int i = 0; i < parkBilgileriSatiri.length; i++) {
                if (i == 0) {
                    isim = parkBilgileriSatiri[0];
                } else if (i == 1) {
                    sehir = parkBilgileriSatiri[1];
                } else if (i == 2) {
                    yuzOlcumu = Double.parseDouble(parkBilgileriSatiri[2]);
                } else {
                    ilanyili = parkBilgileriSatiri[3];
                }
            }

            MilliPark millipark = new MilliPark(isim, ilanyili, sehir, yuzOlcumu , milliparkBilgi_GenelListe.get(sayac));

            // Ağaca ekliyorum
            tree.insert(millipark);

            // MiiliPark arraylist'ine ekliyorum
            milliParklar.add(millipark);

            // Milliparkları heap'e ekliyorum.
            theHeap.insert(millipark);

            //Oluşturduğum genel arraylistten o her bir milliparka ait bilgiler içeren özel arraylisteyi çağırıyorum
            // Özel listedeki stringleri split metoduyla bazı noktalama işaretlerinden ayıklıyorum.
            for (String bilgiCumlesi: milliparkBilgi_GenelListe.get(sayac)) {
                kelimeDizisi = bilgiCumlesi.split("[,.()%+/ ';-]");
                // Bana döndürdüğü dizideki her bir elemanı oluşturduğum tree_kelieler ağacına ekliyorum.
                for(String i : kelimeDizisi){
                    Kelime kelime = new Kelime();
                    kelime.setData(i);
                    tree_kelimeler.insert(kelime);
                }
            }
            sayac+=1;

            // dosyada okuduğum milliparkları hashtable'a ekliyorum.
            hashtable.put(isim , millipark);
        }


        // Tüm Bilgileri Ekrana Listeleyen Method
        tumBilgileriEkranaListeleyenMethod(tree);
        // İlk 3 harfi verilen milliparka ait il veya ilçeyi gösteren method
        ilk3HarfiverilenMilliParkiBul(milliParklar);
        // Tüm ağaçtaki Vikipedi’den alınan bilgi cümlelerindeki kelimeleri bu ağacı dolaşırken
        // alarak ayrı bir ikili arama ağacına yerleştirerek saydıran metod
        System.out.println("******************************");
        System.out.println("  Bilgi Cümlelerindeki Kelimeler ve Ağacın İçinde Kaç Defa Yer Aldığını Gösteren Sayaç  ");
        tree_kelimeler.kelime_inOrder(tree_kelimeler.getRoot());
        System.out.println("******************************");
        //Hashtable'daki milliparkları listeleyen method
        Enumeration<String> enumeration = hashtable.keys();
        while(enumeration.hasMoreElements()) {
           String i = enumeration.nextElement();
            System.out.println("Milli Park: "+ i);
        }
        //Hashtable'a eklediğim milliparkta yüzölçümünü değiştiren method
        update(hashtable);
        // Oluşturduğum heap'teki yüz ölçümü fazla en büyük 3 milliparkın özelliklerini yazdırıyorum
        System.out.println("****************************************************");
        System.out.println("Heap'teki yüzölçümü en büyük 3 milli park ve özellikleri : ");
        System.out.println(theHeap.remove().getMilliPark().toString());
        System.out.println(theHeap.remove().getMilliPark().toString());
        System.out.println(theHeap.remove().getMilliPark().toString());
        System.out.println("****************************************************");

        System.out.println("        Selection Sort         ");
        System.out.println("Sıralamadan önce dizideki sayılar:");
        System.out.println(Arrays.toString(numbers));
        selectionSort.sort(numbers);
        System.out.println("Sıralamadan sonra dizideki sayılar:");
        System.out.println(Arrays.toString(numbers));

      }

    public static void update(Hashtable<String , MilliPark> hashtable){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Tarihini güncellemek istediğiniz milliparkın adını yazınız : ");
        String güncellenecekMilliParkAdi = scanner.nextLine();
        System.out.println("Milliparkın yeni ilan yılını yazınız : ");
        String yeniIlanYili = scanner.nextLine();
        MilliPark güncellenecekMilliPark = hashtable.get(güncellenecekMilliParkAdi);
        güncellenecekMilliPark.setIlanYili(yeniIlanYili);
        System.out.println("İlan Yılı güncellendikten sonra millipark ve özellikleri : " + hashtable.get(güncellenecekMilliParkAdi).toString());
    }
    public static void ilk3HarfiverilenMilliParkiBul(ArrayList<MilliPark> milliParklar){
        System.out.println("Hakkında bilgi sahibi olmak istediğiniz milliparkın ilk 3 harfini giriniz : ");
        Scanner scanner = new Scanner(System.in);
        String ilk3harf = scanner.nextLine();
        for(MilliPark mp : milliParklar){
            if(mp.getMilliparkAdi().startsWith(ilk3harf)) {
                System.out.println("Milli Parkın Adı : " + mp.getMilliparkAdi());
                System.out.println("MiiliParkın Bulunduğu İl : " + mp.getSehir());
            }
        }
    }

    public static void tumBilgileriEkranaListeleyenMethod(BinarySearchTree_millipark tree){
        // Ağaca eklenen milli parkları ve milli parka ait bilgileri ekrana gösteriyor.
        System.out.println("   İkili Arama Ağacındaki MilliParklar  ");
        tree.inOrder(tree.getRoot());

        System.out.println("Ağacın derinliği : " + tree.maxDepth(tree.getRoot()));

        int dugumSayisi = tree.countNode(tree.getRoot());
        System.out.println("Ağacın düğüm sayısı : " + dugumSayisi);

        if(tree.isBalanced(tree.getRoot()) == true){
            System.out.println("Ağaç dengeli.");
        }
        else{
            System.out.println("Ağaç dengeli değil.");
            double dengeliDerinlik = Math.floor(Math.log10(dugumSayisi)/Math.log10(2));
            System.out.println("Eğer ağaç dengeli bir ağaç olsaydı derinliği : " + dengeliDerinlik + " olurdu");
        }
    }

    }










