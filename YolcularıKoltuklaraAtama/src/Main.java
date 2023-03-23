import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Main {
     static Random random = new Random();
    public static void main(String[] args) throws IOException {
        //Uzaklık matrisi oluşturuluyor.
        double [] [] distance_matrix  =calculate_distance_matrix();
        double []  seat_distance_array= new double[40];
        String [] name_surname_array=create_name_surname();
        int [] [] seat_array =place_on_seats(distance_matrix,seat_distance_array);
        //distance array yazdırılıyor
        print_distance_array(distance_matrix);
        //C şıkkı için yazdırma yapılıyor
        print_seats(seat_array,seat_distance_array,name_surname_array,"c");
        //D şıkkı için yazdırma yapılıyor
        print_seats(seat_array,seat_distance_array,name_surname_array,"d");

    }
    public static double [][] calculate_distance_matrix() {
        //Uzaklık matrisi random sınıfından rastgele sayılarla oluşturuluyor.
        double [] [] distance_array = new double[40][40];
        for (int i=0; i<distance_array.length;i++) {
            for (int j = 0; j < distance_array.length; j++) {
                if (i == j) {
                    //Aynı indisin kendisine uzaklığı 0 olacağı için 0 atanıyor.
                    distance_array[i][j] = 0;
                } else if (distance_array[i][j] == 0 && distance_array[j][i] == 0) {
                    double sayi = random.nextDouble(0.0, 10.0);
                    distance_array[i][j] = sayi;
                } else if (distance_array[i][j] == 0 && distance_array[j][i] != 0) {
                    //Birbirinin simetriği olan uzaklıklar birbirine eşit olacağından atama yapılıyor.
                    distance_array[i][j] = distance_array[j][i];
                }
            }
        }
        return distance_array;
    }
    public static String [] create_name_surname() throws IOException {
        //Yolcu isimleri txt dosyasından okunarak atanıyor ve dizi olarak döndürülüyor.
        String  [] name_surname_array= new String[40];
        try {
            FileReader fr = new FileReader("adsoyad.txt");
            BufferedReader br = new BufferedReader(fr);
            for (int i = 0; i <name_surname_array.length ; i++) {
                name_surname_array[i]=br.readLine();
            }
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return name_surname_array;
    }
    public static int [] [] place_on_seats(double [] [] distance_array,double [] seat_distance_array) {
        int selected = random.nextInt(0,40);
        //10x4 Dizi oluşturuluyor
        int [][] seat_array= new int[10][4];
        ArrayList<Integer> oturtulanlar = new ArrayList<Integer>();
        //ilk koltuğa oturan kişi atanıyor.
        seat_array[0][0]=selected;
        oturtulanlar.add(selected);
        //İlk oturan için solunda kimse olmadığından toplam uzaklık değeri 0.0 atanıyor.
        seat_distance_array[selected]=0.0;

        for (int i = 1; i < 40 ; i++) {
                    //İlk 4 sıra için oturma düzeni ayarlanıyor.
                    if(i<4) {
                        int new_seater_index = find_min_distance_first(selected,distance_array,seat_distance_array,oturtulanlar);
                         selected=new_seater_index;
                        seat_array[0][i]=selected;
                        oturtulanlar.add(selected);
                    }
                    //Sol cam kenarı için oturma düzeni ayarlanıyor.
                    else if (i %4 ==0) {
                        int selected_1 =seat_array[i/4-1][0];
                        int selected_2 =seat_array[i/4-1][1];

                        int new_seater_index=find_min_distance_left_window(selected_1,selected_2,distance_array,seat_distance_array,oturtulanlar);
                        selected=new_seater_index;
                        seat_array[i/4][0]=selected;
                        oturtulanlar.add(selected);
                    }
                    //Sol tarafın orta kısmına bakan koltuk için oturma düzeni
                    else if (i%4 ==1) {
                        int selected_1 =seat_array[i/4][0];
                        int selected_2 =seat_array[i/4-1][0];
                        int selected_3 =seat_array[i/4-1][1];
                        int selected_4 =seat_array[i/4-1][2];

                        int new_seater_index=find_min_distance_middle(selected_1,selected_2,selected_3,selected_4,distance_array,seat_distance_array,oturtulanlar);
                        selected=new_seater_index;
                        seat_array[i/4][1]=selected;
                        oturtulanlar.add(selected);
                    }
                    //Sağ tarafın orta kısmına bakan koltuk için oturma düzeni
                    else if (i%4 ==2) {
                        int selected_1 =seat_array[i/4][1];
                        int selected_2 =seat_array[i/4-1][1];
                        int selected_3 =seat_array[i/4-1][2];
                        int selected_4 =seat_array[i/4-1][3];

                        int new_seater_index=find_min_distance_middle(selected_1,selected_2,selected_3,selected_4,distance_array,seat_distance_array,oturtulanlar);
                        selected=new_seater_index;
                        seat_array[i/4][2]=selected;
                        oturtulanlar.add(selected);
                    }
                    //Sağ cam tarafına oturan kişinin oturma düzeni
                    else  {
                        int selected_1 =seat_array[i/4][2];
                        int selected_2 =seat_array[i/4-1][2];
                        int selected_3 =seat_array[i/4-1][3];

                        int new_seater_index=find_min_distance_right_window(selected_1,selected_2,selected_3,distance_array,seat_distance_array,oturtulanlar);
                        selected=new_seater_index;
                        seat_array[i/4][3]=selected;
                        oturtulanlar.add(selected);
                    }
        }
        return seat_array;
    }
    public static int find_min_distance_first(int selected,double [] [] distance_array,double []  seat_distance_array,ArrayList<Integer> oturtulanlar) {
        //İlk 4 oturulan kişi için uzaklık bulma
        //En küçük uzaklığı aradığımız için büyük bir uzaklık atanıyor. 50 atanmasındaki sebep maksimum 2 kişi arası uzaklık 10 olabilir. Bunun sonucunda 50 imkansız bir değerdir.
        double distance=50.0;
        int minimum_distance_index=selected;
        //Uzaklıklar üzerinde dönülüyor.
        for (int i = 0; i < distance_array.length ; i++) {
            //Uzaklık değeri distance değerinden küçük ve yolcu daha önce oturmadıysa distance değeri ve index değeri atanıyor.
            if (distance_array[selected][i]<distance && !oturtulanlar.contains(i)) {
                distance=distance_array[selected][i];
                minimum_distance_index=i;
            }
        }
        seat_distance_array[minimum_distance_index]=distance;
        return minimum_distance_index;
    }
    public static int find_min_distance_left_window(int selected_1,int selected_2,double [] [] distance_array,double []  seat_distance_array,ArrayList<Integer> oturtulanlar) {
        //Sol taraf pencere kenarı için uzaklık bulma
        //En küçük uzaklığı aradığımız için büyük bir uzaklık atanıyor. 50 atanmasındaki sebep maksimum 2 kişi arası uzaklık 10 olabilir. Bunun sonucunda 50 imkansız bir değerdir.
        double distance=50.0;
        int minimum_distance_index=0;

        for (int i = 0; i < distance_array.length ; i++) {
            //Uzaklık değerleri toplamı distance değerinden küçük ve yolcu daha önce oturmadıysa distance değeri ve index değeri atanıyor.
            if (distance_array[selected_1][i]+distance_array[selected_2][i]<distance && !oturtulanlar.contains(i)) {
                distance=distance_array[selected_1][i]+distance_array[selected_2][i];
                minimum_distance_index=i;
            }
        }
        seat_distance_array[minimum_distance_index]=distance;
        return minimum_distance_index;
    }
    public static int find_min_distance_middle(int selected_1,int selected_2,int selected_3,int selected_4,double [] [] distance_array,double []  seat_distance_array,ArrayList<Integer> oturtulanlar){
        //Orta taraf için uzaklık bulma
        //En küçük uzaklığı aradığımız için büyük bir uzaklık atanıyor. 50 atanmasındaki sebep maksimum 2 kişi arası uzaklık 10 olabilir. Bunun sonucunda 50 imkansız bir değerdir.
        double distance=50.0;
        int minimum_distance_index=0;
        for (int i = 0; i < distance_array.length ; i++) {
            //Uzaklık değerleri toplamı distance değerinden küçük ve yolcu daha önce oturmadıysa distance değeri ve index değeri atanıyor.
            if (distance_array[selected_1][i]+distance_array[selected_2][i]+distance_array[selected_3][i]+distance_array[selected_4][i]<distance
                    && !oturtulanlar.contains(i) ) {
                distance=distance_array[selected_1][i]+distance_array[selected_2][i]+distance_array[selected_3][i]+distance_array[selected_4][i];
                minimum_distance_index=i;
            }
        }
        seat_distance_array[minimum_distance_index]=distance;
        return minimum_distance_index;
    }
    public static int find_min_distance_right_window(int selected_1,int selected_2,int selected_3 ,double [] [] distance_array,double []  seat_distance_array,ArrayList<Integer> oturtulanlar) {
        //Sağ taraf pencere kenarı için uzaklık bulma
        //En küçük uzaklığı aradığımız için büyük bir uzaklık atanıyor. 50 atanmasındaki sebep maksimum 2 kişi arası uzaklık 10 olabilir. Bunun sonucunda 50 imkansız bir değerdir.
        double distance=50.0;
        int minimum_distance_index=0;

        for (int i = 0; i < distance_array.length ; i++) {
            //Uzaklık değerleri toplamı distance değerinden küçük ve yolcu daha önce oturmadıysa distance değeri ve index değeri atanıyor.
            if (distance_array[selected_1][i]+distance_array[selected_2][i]+distance_array[selected_3][i]<distance && !oturtulanlar.contains(i)) {
                distance=distance_array[selected_1][i]+distance_array[selected_2][i]+distance_array[selected_3][i];
                minimum_distance_index=i;
            }
        }
        seat_distance_array[minimum_distance_index]=distance;
        return minimum_distance_index;
    }
    public static void print_seats(int [] [] seat_array,double [] seat_distance_array,String []  name_surname_array,String secenek) {
        System.out.println("--------------Otobüs Koltuk Düzeni--------------");
        int count=0; //koltuk indeksleri için
        double total_distances=0; //uzaklıklar toplamı için

        for (int i = 0; i <seat_array.length ; i++) {
            for (int j = 0; j <seat_array[i].length ; j++) {
                int selected_passenger=seat_array[i][j]; //İlgili koltuktaki yolcunun indeksi alınıyor
                //C şıkkı yazdırma için if bloğu çalışıyor
                //D şıkkı yazdırma için else bloğu çalışıyor. Uzaklıklar yolcularla birlikte yazılacak.
                //Alınan indeks name_surname_array'de yerine yazılarak yolcunun ismi çekiliyor.
                if(secenek.equals("c")) System.out.printf("%-2s // %-25s",(count+1),name_surname_array[selected_passenger]);
                //D şıkkı
                else {
                    System.out.printf("%-2s // %-25s (%.2f)",(count+1),name_surname_array[selected_passenger],seat_distance_array[selected_passenger]);
                    //total_distances hesaplanıyor
                    total_distances+=seat_distance_array[count];
                }
                //Koltuklara arasına çizgi çekiliyor
                if (j==0 || j ==2) System.out.print(" || ");
                //2li koltuklar arasına tam karşılığıyla otobüste koltuklar arası koridora gösterim için boşluk bırakılıyor.
                else System.out.print("\t\t\t");
                //koltuk indeksi arttırılıyor.
                count+=1;
            }
            System.out.println("");
        }
        //D seçeneği için total_distances yazdırılıyor.
        if(secenek.equals("d")) System.out.println("Total distances = "+String.format("%.2f",total_distances));
    }
    public static void print_distance_array(double [] [] distance_array) {
        //Matris ekrana yazdırılıyor
        for (int i = 0; i <distance_array.length ; i++) {
            for (int j = 0; j <distance_array.length ; j++) {
                //Daha iyi bir görünüm için .2f formatında ve arada çizgiyle yazılıyor.
                System.out.printf("%.2f | ",distance_array[i][j]);
            }
            //Her 40 veriden sonra satır atlatılıyor.
            System.out.println("");
        }
    }
}